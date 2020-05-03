package com.alfa_fiki.api.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.alfa_fiki.api.R;
import com.alfa_fiki.api.adapter.EmailDiscoverAdapter;
import com.alfa_fiki.api.model.DataItem;
import com.alfa_fiki.api.model.EmailDiscoverResponse;
import com.alfa_fiki.api.view.viewmodel.EmailViewModel;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class EmailFragment extends Fragment {

    private EmailDiscoverAdapter emailDiscoverAdapter;
    private RecyclerView rvEmailDiscover;
    private EmailViewModel emailViewModel;

    public EmailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_email, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        emailDiscoverAdapter = new EmailDiscoverAdapter(getContext());
        emailDiscoverAdapter.notifyDataSetChanged();

        rvEmailDiscover = view.findViewById(R.id.fragmentemail_rv);
        rvEmailDiscover.setLayoutManager(new GridLayoutManager(getContext(),1));

        emailViewModel = new ViewModelProvider(this).get(EmailViewModel.class);
        emailViewModel.setEmailDiscover();
        emailViewModel.getEmailDiscover().observe(this, getEmailDiscover);

        rvEmailDiscover.setAdapter(emailDiscoverAdapter);
    }

    private Observer<ArrayList<DataItem>> getEmailDiscover = new Observer<ArrayList<DataItem>>() {
        @Override
        public void onChanged(ArrayList<DataItem> dataItems) {
            if (dataItems != null){
                emailDiscoverAdapter.setData(dataItems);
            }
        }
    };
}
