package com.weekendinc.baseapp.net;

import com.projectname.model.api.data.ImageInfo;
import com.weekendinc.baseapp.model.api.BaseResponse;

import retrofit2.http.GET;
import rx.Observable;

/**
 * Created by Fadhlan on 11/23/17.
 */

public interface HerokuService {
    String BASE_URL = "https://wkndmobile-jsonsample.herokuapp.com";

    @GET ("/base") Observable<BaseResponse<ImageInfo>> getImages();
}
