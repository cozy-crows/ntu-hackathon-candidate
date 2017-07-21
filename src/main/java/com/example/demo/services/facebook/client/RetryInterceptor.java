package com.example.demo.services.facebook.client;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Created by jerry on 2017/7/21.
 */
@Slf4j
public class RetryInterceptor implements Interceptor {

    private int retryTimes;

    public RetryInterceptor(@NonNull int times) {
        this.retryTimes = times;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        int tryCount = 0;

        while (!response.isSuccessful() && tryCount < retryTimes) {
            tryCount++;
            log.warn("{} Retry request : {}", tryCount, request.url());

            response = chain.proceed(request);
        }

        return response;
    }
}
