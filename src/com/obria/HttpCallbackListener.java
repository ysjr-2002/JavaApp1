package com.obria;

public interface HttpCallbackListener {

    void onGetResponse(String response);

    void onException(Exception ex);
}
