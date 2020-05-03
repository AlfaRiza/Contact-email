package com.alfa_fiki.api.view.viewmodel;

import com.alfa_fiki.api.model.DataItem;
import com.alfa_fiki.api.model.EmailDiscoverResponse;
import com.alfa_fiki.api.service.ApiMain;

import java.util.ArrayList;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EmailViewModel extends ViewModel {
    private ApiMain apiMain;

    private MutableLiveData<ArrayList<DataItem>> listDiscoverEmail = new MutableLiveData<>();

    public void setEmailDiscover(){
        if (this.apiMain == null){
            apiMain = new ApiMain();
        }

        apiMain.getApi().getEmailDiscover().enqueue(new Callback<EmailDiscoverResponse>() {
            @Override
            public void onResponse(Call<EmailDiscoverResponse> call, Response<EmailDiscoverResponse> response) {
                EmailDiscoverResponse responseDiscover = response.body();
                if (response != null && responseDiscover.getData() != null){
                    ArrayList<DataItem> emailDiscoverItems = responseDiscover.getData();
                    listDiscoverEmail.postValue(emailDiscoverItems);
                }
            }

            @Override
            public void onFailure(Call<EmailDiscoverResponse> call, Throwable t) {

            }
        });
    }

    public LiveData<ArrayList<DataItem>> getEmailDiscover(){
        return listDiscoverEmail;
    }
}
