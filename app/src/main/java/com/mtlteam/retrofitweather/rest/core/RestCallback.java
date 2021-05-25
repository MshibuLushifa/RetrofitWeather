package com.mtlteam.retrofitweather.rest.core;

import com.mtlteam.retrofitweather.rest.core.base.BaseResponseEvent;

import org.greenrobot.eventbus.EventBus;

import retrofit2.Response;
import retrofit2.Call;
import retrofit2.Callback;

public class RestCallback implements Callback {
    private RestRequest restRequest;

    /**
     * Constructor.
     *
     * @param restRequest instance of {@link RestRequest} that holds request and response data.
     */
    public RestCallback(RestRequest restRequest) {
        this.restRequest = restRequest;
    }

    @Override
    public void onResponse(Call call, Response response) {
        if (restRequest != null && restRequest.hasBaseResponseEvent()) {
            restRequest.getBaseResponseEvent().setCode(response.code());
            restRequest.getBaseResponseEvent().setResponse(response.body());
            postEvent();
        }
    }

    @Override
    public void onFailure(Call call, Throwable t) {
        if (restRequest != null && !call.isCanceled()) {
            postEvent();
        }
    }

    /**
     * Post {@link BaseResponseEvent} with flag TRUE that request has been canceled.
     */
    public void cancel() {
        if (restRequest != null && restRequest.hasBaseResponseEvent()) {
            restRequest.getBaseResponseEvent().setCanceled(true);
            postEvent();
        }
    }

    /**
     * Post {@link BaseResponseEvent} event of {@link RestRequest#getBaseResponseEvent}
     * via {@link EventBus#post(Object)}. If {@link RestRequest#isUseStickyIntent()} is TRUE then
     * post sticky one via {@link EventBus#postSticky(Object)}.
     */
    private void postEvent() {
        if (restRequest.isUseStickyIntent()) {
            EventBus.getDefault().postSticky(restRequest.getBaseResponseEvent());
        } else {
            EventBus.getDefault().post(restRequest.getBaseResponseEvent());
        }
    }
}
