package com.mtlteam.retrofitweather.rest.core.base;

import com.mtlteam.retrofitweather.rest.core.RestCall;
import com.mtlteam.retrofitweather.rest.core.RestCallback;
import com.mtlteam.retrofitweather.rest.core.RestRequest;

public class BaseRestClient {
    /**
     * Perform HTTP request based on {@link RestRequest}.
     *
     * @param restRequest instance of {@link RestRequest} which holds request parameters.
     * @return instance of {@link RestCall} which represents HTTP request.
     */
    protected RestCall call(RestRequest restRequest) {
        checkArgument(restRequest, "RestRequest argument cannot be null");
        checkArgument(restRequest.getCall(), "RestRequest->call argument cannot be null");

        RestCallback callback = new RestCallback(restRequest);
        restRequest.getCall().enqueue(callback);

        return new RestCall(restRequest.getCall(), callback);
    }

    /**
     * Cancel HTTP request call if has been executed.
     *
     * @param restCall instance of {@link RestCall} which represents HTTP request.
     */
    protected void cancelCall(RestCall restCall) {
        if (restCall != null) {
            restCall.cancel();
        }
    }

    /**
     * Method for checking if required parameter has been provided.
     *
     * @param reference of Object to check.
     * @param message thrown via {@link IllegalArgumentException}.
     */
    private void checkArgument(Object reference, String message) {
        if (reference == null) {
            throw new IllegalArgumentException(message);
        }
    }
}
