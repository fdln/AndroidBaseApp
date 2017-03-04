package com.weekendinc.baseapp.presenter;

import com.weekendinc.baseapp.net.ApiManager;
import com.weekendinc.baseapp.view.DataView;

/**
 * Created by Fadhlan on 3/2/17.
 */

public interface Presenter {
    ApiManager apiManager = ApiManager.getInstance();

    void setDataView(DataView dataView);

}
