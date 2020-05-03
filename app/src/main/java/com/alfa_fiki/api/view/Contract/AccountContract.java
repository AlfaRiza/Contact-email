package com.alfa_fiki.api.view.Contract;

import android.view.View;

import com.alfa_fiki.api.entity.AppDatabase;
import com.alfa_fiki.api.entity.DataDiri;

import java.util.List;

public interface AccountContract {
    interface view extends View.OnClickListener{
        void successAdd();
        void successDelete();
        void resetForm();
        void getData(List<DataDiri> list);
        void editData(DataDiri item);
        void deleteData(DataDiri item);
    }

    // interfaace presenter digunakan untuk kodingan database nya
    interface presenter {
        void insertData(String nama, String email, AppDatabase database);
        void readData(AppDatabase database);
        void editData(String nama, String email, int id, AppDatabase database);
        void deleteData(DataDiri dataDiri, AppDatabase database);
    }
}
