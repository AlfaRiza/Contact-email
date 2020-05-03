package com.alfa_fiki.api.view.presenter;

import android.os.AsyncTask;
import android.util.Log;

import com.alfa_fiki.api.entity.AppDatabase;
import com.alfa_fiki.api.entity.DataDiri;
import com.alfa_fiki.api.view.Contract.AccountContract;

import java.util.List;

public class AccountPresenter implements AccountContract.presenter{
    private AccountContract.view viewContract;

    public AccountPresenter(AccountContract.view viewContract) {
        this.viewContract = viewContract;
    }

    /***
     * InserData dibutuhkan untuk menambahkan data. Class {@link InsertData}
     * digunakan untuk proses penambahan data ke daabase menggunakan AsyncTask .
     * Sementara fungsi insertData digunaakan untuk memanggil class {@link InsertData}
     */

    class InsertData extends AsyncTask<Void, Void, Long> {
        private AppDatabase database;
        private DataDiri dataDiri;

        public InsertData(AppDatabase database, DataDiri dataDiri) {
            this.database = database;
            this.dataDiri = dataDiri;
        }

        @Override
        protected Long doInBackground(Void... voids) {
            return database.dao().insertData(dataDiri);
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);
            viewContract.successAdd();
        }

    }

    @Override
    public void insertData(String nama, String alamat,
                           final AppDatabase database) {
        final DataDiri dataDiri = new DataDiri();
        dataDiri.setEmail(alamat);
        dataDiri.setName(nama);
        new InsertData(database, dataDiri).execute();
    }

    /////////////////////////////////////////////////////////////////


    /***
     * Pada fungsi readData, kita mencoba untuk membaca isi database tanpa menggunakan
     * AsyncTask . Yaitu dengan cara langsung memanggil perintah untuk membaca databaae
     * (database.dao().getData() )
     *
     * Sebenernya disarankan untuk menggunakan AsyncTask seperti pada fungsi InsertData
     */

    @Override
    public void readData(AppDatabase database) {
        List<DataDiri> list;
        list = database.dao().getData();
        viewContract.getData(list);
    }

    //////////////////////////////////////////////////////////////////////



    class EditData extends AsyncTask<Void, Void, Integer> {
        private AppDatabase database;
        private DataDiri dataDiri;

        public EditData(AppDatabase database, DataDiri dataDiri) {
            this.database = database;
            this.dataDiri = dataDiri;
        }

        @Override
        protected Integer doInBackground(Void... voids) {
            return database.dao().updateData(dataDiri);
        }

        @Override
        protected void onPostExecute(Integer integer) {
            super.onPostExecute(integer);
            Log.d("integer db", "onPostExecute: " + integer);
            viewContract.successAdd();
        }
    }

    @Override
    public void editData(String nama, String email, int id,
                         final AppDatabase database) {
        final DataDiri dataDiri = new DataDiri();
        dataDiri.setEmail(email);
        dataDiri.setName(nama);
        dataDiri.setId(id);

        new EditData(database, dataDiri).execute();
    }

    /////////////////////////////////////////////////////////////////////



    class DeleteData extends AsyncTask<Void, Void, Void>{
        private AppDatabase database;
        private DataDiri dataDiri;

        public DeleteData(AppDatabase database, DataDiri dataDiri) {
            this.database = database;
            this.dataDiri = dataDiri;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            database.dao().deleteData(dataDiri);
            return  null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            viewContract.successDelete();
        }

    }

    @Override
    public void deleteData(final DataDiri dataDiri,
                           final AppDatabase database) {
        new DeleteData(database, dataDiri).execute();
    }
}
