package com.example.demo.services.facebook.client;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

/**
 * Created by jerry on 2017/7/21.
 */
@Slf4j
public class FacebookGraphApiBuilder {

    private String endPoint = "https://graph.facebook.com/v2.10/";

    private long connectTimeout = 30_000;
    private long readTimeout = 30_000;
    private long writeTimeout = 30_000;

    private List<Interceptor> interceptors = new ArrayList<>();

    private OkHttpClient.Builder okHttpClientBuilder;
    private Retrofit.Builder retrofitBuilder;

    /**
     * construct
     */
    public FacebookGraphApiBuilder(List<Interceptor> interceptors) {
        this.interceptors.addAll(interceptors);
    }

    /**
     * Create Singleton
     *
     * @return
     */
    public static FacebookGraphApiBuilder create() {
        return new FacebookGraphApiBuilder(defaultInterceptor());
    }

    /**
     * 預設的 Interceptor
     *
     * @return
     */
    public static List<Interceptor> defaultInterceptor() {
        final HttpLoggingInterceptor httpLoggingInterceptor =
                new HttpLoggingInterceptor(message -> log.info("{}", message));
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        return Arrays.asList(
                httpLoggingInterceptor
        );
    }

    public FacebookGraphApiBuilder addInterceptor(Interceptor interceptor) {
        this.interceptors.add(interceptor);
        return this;
    }

    public FacebookGraphApiBuilder apiEndPoint(@NonNull String endPoint) {
        this.endPoint = endPoint;
        return this;
    }

    public FacebookGraphApiBuilder okHttpClientBuilder(@NonNull OkHttpClient.Builder okHttpClientBuilder) {
        this.okHttpClientBuilder = okHttpClientBuilder;
        return this;
    }

    public FacebookGraphApiBuilder retrofitBuilder(@NonNull Retrofit.Builder retrofitBuilder) {
        this.retrofitBuilder = retrofitBuilder;
        return this;
    }

    public Retrofit.Builder defaultRetrofitBuilder() {
        final ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.configure(DeserializationFeature.READ_DATE_TIMESTAMPS_AS_NANOSECONDS, false);
        objectMapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES, false);

        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        objectMapper.registerModule(new JodaModule());

        return new Retrofit.Builder()
                .addConverterFactory(JacksonConverterFactory.create(objectMapper));
    }

    public FacebookService build() {
        if (Objects.isNull(okHttpClientBuilder)) {
            this.okHttpClientBuilder = new OkHttpClient().newBuilder();
        }

        interceptors.forEach(okHttpClientBuilder::addInterceptor);

        okHttpClientBuilder
                .connectTimeout(connectTimeout, TimeUnit.MILLISECONDS)
                .readTimeout(readTimeout, TimeUnit.MILLISECONDS)
                .writeTimeout(writeTimeout, TimeUnit.MILLISECONDS);

        final OkHttpClient okHttpClient = okHttpClientBuilder.build();

        if (Objects.isNull(retrofitBuilder)) {
            retrofitBuilder = defaultRetrofitBuilder();
        }

        retrofitBuilder.client(okHttpClient);
        retrofitBuilder.baseUrl(endPoint);

        final Retrofit retrofit = retrofitBuilder.build();

        return retrofit.create(FacebookService.class);
    }
}
