package com.alfa_fiki.api.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alfa_fiki.api.R;
import com.alfa_fiki.api.model.DataItem;
import com.alfa_fiki.api.view.activity.MainActivity;
import com.bumptech.glide.Glide;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class EmailDiscoverAdapter extends RecyclerView.Adapter<EmailDiscoverAdapter.ViewHolder> {

    private ArrayList<DataItem> emailDiscoverItem = new ArrayList<>();
    private Context context;

    public EmailDiscoverAdapter(Context context) {
        this.context = context;
    }

    public void setData(ArrayList<DataItem> items){
        emailDiscoverItem.clear();
        emailDiscoverItem.addAll(items);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        Glide.with(context).load(emailDiscoverItem.get(position)
                .getAvatar())
                .into(holder.ivThumbnail);

        holder.tvTitle.setText(emailDiscoverItem.get(position).getFirstName());
        holder.tvDesc.setText(emailDiscoverItem.get(position).getEmail());
        holder.cvItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] email = {emailDiscoverItem.get(position).getEmail()};
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.setData(Uri.parse("mailto:"));
                intent.putExtra(Intent.EXTRA_EMAIL, email);
                intent.putExtra(Intent.EXTRA_SUBJECT, "");
                intent.putExtra(Intent.EXTRA_TEXT, "");
                intent.setType("text/plain");
                context.startActivity(Intent.createChooser(intent, "Pilih Aplikasi untuk mengirim Email"));
            }
        });
    }

    @Override
    public int getItemCount() {
        return emailDiscoverItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivThumbnail;
        TextView tvTitle,tvDesc;
        CardView cvItem;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            cvItem = itemView.findViewById(R.id.itemlist_cv);
            ivThumbnail = itemView.findViewById(R.id.itemlist_iv_thumbnail);
            tvTitle = itemView.findViewById(R.id.itemlist_tv_title);
            tvDesc = itemView.findViewById(R.id.itemlist_tv_desc);

        }
    }
}
