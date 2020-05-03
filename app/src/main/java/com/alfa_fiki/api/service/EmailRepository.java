package com.alfa_fiki.api.service;

import com.alfa_fiki.api.model.DataItem;
import com.alfa_fiki.api.model.EmailDiscoverResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface EmailRepository {
    @GET("users?page=1")
    Call<EmailDiscoverResponse> getEmailDiscover();
}
