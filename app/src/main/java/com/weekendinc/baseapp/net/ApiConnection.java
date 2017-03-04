package com.weekendinc.baseapp.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.util.Log;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.picasso.LruCache;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;
import com.weekendinc.baseapp.Application;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

/**
 * Created by Fadhlan on 2/16/17.
 */
public class ApiConnection {

    private static final String LOG_TAG = ApiConnection.class.getSimpleName();
    private static final int KB = 1024;
    private static final int MB = 1024 * KB;
    private static final int MAX_HEAP_SIZE = (int) Runtime.getRuntime().maxMemory();
    private static final int MAX_DISK_CACHE_SIZE = 100 * MB;
    private static final int MAX_MEMORY_CACHE_SIZE = MAX_HEAP_SIZE / 4;
    private static final int MAX_CACHE_STALE_TIME = 60 * 60 * 24;
    private static final int MAX_CACHE_AGE = 60 * 10;

    private GithubService githubService;

    private static ApiConnection instance = new ApiConnection();

    public static ApiConnection getInstance() {
        return instance;
    }


    private ApiConnection() {
        Retrofit apiAdapter = buildApiAdapter(buildHttpClient(), Endpoint.BASE_URL);
        Retrofit githubApiAdapter = buildApiAdapter(buildHttpClient(), GithubService.SERVICE_ENDPOINT);
        Picasso.setSingletonInstance(buildPicassoInstance());
        this.githubService = apiAdapter.create(GithubService.class);
    }

    public GithubService getGithubService() {
        return githubService;
    }

    /**
     * Create Http client
     *
     * @return
     */
    private OkHttpClient buildHttpClient() {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        return new OkHttpClient.Builder()
                .connectTimeout(1, TimeUnit.MINUTES)
                .readTimeout(1, TimeUnit.MINUTES)
                .writeTimeout(1, TimeUnit.MINUTES)
                .retryOnConnectionFailure(true)
                .cache(buildCache(MAX_DISK_CACHE_SIZE))
                .addInterceptor(loggingInterceptor)
                .addNetworkInterceptor(rewriteCacheControl())
                .build();
    }

    /**
     * Set file for caching usage
     *
     * @param filesize
     * @return
     */
    private Cache buildCache(long filesize) {
        Cache cache = null;
        try {
            String directory = Application.getInstance().getCacheDir().getAbsolutePath();
            String filename = "http.cache";
            File file = new File(directory, filename);
            cache = new Cache(file, filesize);
        } catch (Exception ex) {
            Log.e(LOG_TAG, "buildCache: ", ex);
        }
        return cache;
    }

    /**
     * Caching for everty `GET` Http request
     *
     * @return
     */
    private Interceptor rewriteCacheControl() {
        final String pragma = "Pragma";
        final String hCache = "Cache-control";
        final String vCache = "only-if-cached";
        final String vStall = "public, max-stale=" + MAX_CACHE_STALE_TIME;
        final String max = "max-age" + MAX_CACHE_AGE;

        return new Interceptor() {

            @Override public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Context context = Application.getInstance().getApplicationContext();
                return chain.proceed(
                        request.method().equals("GET")
                        ? isNetworkAvailable(context)
                          ? request.newBuilder().removeHeader(pragma).header(hCache, vCache).build()
                          : request.newBuilder().removeHeader(pragma).header(hCache, vStall).build()
                        : request).newBuilder().removeHeader(pragma)
                        .header(hCache, max).build();
            }
        };
    }

    /**
     * Create Retrofit object as api adapter;
     * Configure Jackson object mapper for handle json unconventional format
     *
     * @param httpClient
     * @return
     */
    private Retrofit buildApiAdapter(OkHttpClient httpClient, String baseUrl) {
        Executor executor = Executors.newCachedThreadPool();

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
        objectMapper.configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true);
        objectMapper.configure(DeserializationFeature.USE_BIG_INTEGER_FOR_INTS, true);

        return new Retrofit.Builder()
                .callbackExecutor(executor)
                .baseUrl(baseUrl)
                .client(httpClient)
                .addConverterFactory(JacksonConverterFactory.create(objectMapper))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }

    /**
     * As Picasso logger
     *
     * @return
     */
    private Picasso.Listener getPicassoListener() {
        return new Picasso.Listener() {
            @Override public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
                Log.e("PICASSO", uri.toString(), exception);
            }
        };
    }

    /**
     * Prepare for singleton picasso, set disk cache and mem cache
     *
     * @return
     */
    private Picasso buildPicassoInstance() {
        Context context = Application.getInstance().getApplicationContext();
        return new Picasso.Builder(context)
                .downloader(new OkHttp3Downloader(context, MAX_DISK_CACHE_SIZE))
                .memoryCache(new LruCache(MAX_MEMORY_CACHE_SIZE))
                .listener(getPicassoListener())
                .build();
    }

    private static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return ((activeNetworkInfo != null) && (activeNetworkInfo.isConnected()));
    }
}
