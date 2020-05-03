package com.alfa_fiki.api.view.fragment;

import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alfa_fiki.api.R;
import com.alfa_fiki.api.adapter.AccountDiscoverAdapter;
import com.alfa_fiki.api.entity.AppDatabase;
import com.alfa_fiki.api.entity.DataDiri;
import com.alfa_fiki.api.model.DataItem;
import com.alfa_fiki.api.view.Contract.AccountContract;
import com.alfa_fiki.api.view.presenter.AccountPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class AccountFragment extends Fragment implements AccountContract.view{

    private AppDatabase appDatabase;
    private AccountPresenter presenter;
    private AccountDiscoverAdapter adapter;

    private Button btnOK;
    private RecyclerView recyclerView;
    private EditText tvNama, tvEmail;

    private boolean edit = false;
    private int id = 0;
    public AccountFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        appDatabase = AppDatabase.iniDb(getContext());

        btnOK = view.findViewById(R.id.btn_submit);
        btnOK.setOnClickListener(this);
        tvNama = view.findViewById(R.id.et_nama);
        tvEmail = view.findViewById(R.id.et_email);
        recyclerView = view.findViewById(R.id.fragmentaccount_rv);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter = new AccountPresenter(this);

        presenter.readData(appDatabase);
    }


    @Override
    public void successAdd() {
        Toast.makeText(getContext(), "Berhasil", Toast.LENGTH_SHORT).show();
        presenter.readData(appDatabase);
    }

    @Override
    public void successDelete() {
        Toast.makeText(getContext(), "Berhasil menghapus data", Toast.LENGTH_SHORT).show();
        presenter.readData(appDatabase);
    }

    @Override
    public void resetForm() {
        tvNama.setText("");
        tvEmail.setText("");
        btnOK.setText("submit");
    }

    @Override
    public void getData(List<DataDiri> list) {
        adapter = new AccountDiscoverAdapter(getContext(), list, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void editData(DataDiri item) {
        tvNama.setText(item.getName());
        tvEmail.setText(item.getEmail());
        id = item.getId();

        // var edit untuk memberi tahu bahwa button nya sedang mode edit
        // cek penggunaannya dibagian fungsi onClick
        edit = true;
        btnOK.setText("Update");
    }

    @Override
    public void deleteData(final DataDiri item) {
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(getContext(), android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(getContext());
        }
        builder.setTitle("Menghapus Data")
                .setMessage("Anda yakin ingin menghapus data ini?")
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        resetForm();
                        presenter.deleteData(item, appDatabase);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_info)
                .show();
    }

    @Override
    public void onClick(View v) {
        if(v == btnOK){
            if(tvNama.getText().toString().equals("") || tvEmail.getText().toString().equals("") ) {
                Toast.makeText(getContext(), "Harap isi semua data", Toast.LENGTH_SHORT).show();
            } else {



                if(!edit) presenter.insertData(tvNama.getText().toString(), tvEmail.getText().toString() , appDatabase);
                else{
                    // Jika mode edit, panggil fungsi edit DB
                    presenter.editData(tvNama.getText().toString(), tvEmail.getText().toString(), id, appDatabase);
                    edit = false;
                }
                resetForm();
            }
        }
    }
}

