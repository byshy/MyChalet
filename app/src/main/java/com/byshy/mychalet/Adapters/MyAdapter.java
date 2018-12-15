package com.byshy.mychalet.Adapters;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.byshy.mychalet.Models.Chalet;
import com.byshy.mychalet.R;


import java.util.List;

/**
 * Created by M.Eslim on 09/03/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {

    private List<Chalet> myListItem;
    private Context context;

    public MyAdapter(List<Chalet> chaletList, Context context) {
        this.myListItem = chaletList;
        this.context = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Chalet listItem = myListItem.get(position);
        holder.chaletName.setText(listItem.getName());
        holder.chaletAddress.setText(listItem.getAddress());
    }


    @Override
    public int getItemCount() {
        return myListItem.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView chaletAddress;
        public TextView chaletName;
        public CardView linearLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            chaletName = (TextView) itemView.findViewById(R.id.chalet_name);
            chaletAddress = (TextView) itemView.findViewById(R.id.chalet_address);

            linearLayout = itemView.findViewById(R.id.main_activity_card_view_list_item);
        }
    }


}
