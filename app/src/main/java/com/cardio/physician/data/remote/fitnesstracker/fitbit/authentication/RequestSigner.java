package com.cardio.physician.data.remote.fitnesstracker.fitbit.authentication;


import com.cardio.physician.data.remote.fitnesstracker.fitbit.basichttp.BasicHttpRequestBuilder;

/**
 * Created by jboggess on 9/26/16.
 */

public interface RequestSigner {

    void signRequest(BasicHttpRequestBuilder builder);

}
