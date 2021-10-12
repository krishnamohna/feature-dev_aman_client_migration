package com.cardio.doctor.data.remote.fitnesstracker.fitbit.authentication;


import com.cardio.doctor.network.basichttp.BasicHttpRequestBuilder;

/**
 * Created by jboggess on 9/26/16.
 */

public interface RequestSigner {

    void signRequest(BasicHttpRequestBuilder builder);

}
