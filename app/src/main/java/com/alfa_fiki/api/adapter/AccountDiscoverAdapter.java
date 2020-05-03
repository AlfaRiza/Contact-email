package com.alfa_fiki.api.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.alfa_fiki.api.R;
import com.alfa_fiki.api.entity.AppDatabase;
import com.alfa_fiki.api.entity.DataDiri;
import com.alfa_fiki.api.model.DataItem;
import com.alfa_fiki.api.view.Contract.AccountContract;
import com.alfa_fiki.api.view.presenter.AccountPresenter;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class AccountDiscoverAdapter extends RecyclerView.Adapter<AccountDiscoverAdapter.ViewHolder> {
    Context context;
    List<DataDiri> list;
    AccountContract.view view;


    public AccountDiscoverAdapter(Context context, List<DataDiri> list, AccountContract.view view) {
        this.context = context;
        this.list = list;
        this.view = view;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user,parent,false);
        return new AccountDiscoverAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final AccountDiscoverAdapter.ViewHolder holder, final int position) {
        final DataDiri item = list.get(position);
        holder.tvDesc.setText(item.getEmail());
        holder.tvTitle.setText(item.getName());
        holder.btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] email = {list.get(position).getEmail()};
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, email);
                intent.putExtra(Intent.EXTRA_SUBJECT, "");
                intent.putExtra(Intent.EXTRA_TEXT, "");
                intent.setType("text/plain");
                context.startActivity(Intent.createChooser(intent, "Pilih Aplikasi untuk mengirim Email"));
            }
        });
        holder.cvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] email = {list.get(position).getEmail()};
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, email);
                intent.putExtra(Intent.EXTRA_SUBJECT, "");
                intent.putExtra(Intent.EXTRA_TEXT, "");
                intent.setType("text/plain");
                context.startActivity(Intent.createChooser(intent, "Pilih Aplikasi untuk mengirim Email"));
            }
        });

        holder.cvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                view.editData(item);
            }
        });
        holder.cvItem.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                view.deleteData(item);
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle,tvDesc;
        ImageButton btn_send;
        CardView cvItem;
//        ImageView ivThumbnail;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cvItem = itemView.findViewById(R.id.itemlist_cv);
            tvTitle = itemView.findViewById(R.id.itemlist_tv_title);
            tvDesc = itemView.findViewById(R.id.itemlist_tv_desc);
            btn_send = itemView.findViewById(R.id.imgbtn_send);
//            ivThumbnail = itemView.findViewById(R.id.itemlist_iv_thumbnail);
        }
    }
}
