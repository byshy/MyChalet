package com.byshy.mychalet.Adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.byshy.mychalet.Models.User;
import com.byshy.mychalet.R;


import java.util.List;

/**
 * Created by M.Eslim on 09/03/2018.
 */

public class MyAdapter extends  RecyclerView.Adapter<MyAdapter.ViewHolder> {


   private List<User> mylistitem ;
Context context;
Button b1,b2,b3;
Button E1,E2,E3;

    public MyAdapter(List<User> Datee, Context context) {
        this.mylistitem = Datee;
        this.context =  context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

      View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item,parent,false);

      return  new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
         final User listitem = mylistitem.get(position);
        holder.name.setText(listitem.getFirstName());
        holder.id.setText(listitem.getEmail());

        holder.myRelativ.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             //   Intent in = new Intent(context, MyData.class);
//                in.putExtra("id",listitem.getId().toString().trim());
//                in.putExtra("user",listitem.getUser().toString().trim());
//                in.putExtra("name",listitem.getName().toString().trim());
//                in.putExtra("pass",listitem.getPass().toString().trim());
//                in.putExtra("desc",listitem.getDescription().toString().trim());
        //        context.startActivity(in);

            }
        });




        holder.myRelativ.setOnLongClickListener(new View.OnLongClickListener() {

            @Override
            public boolean onLongClick(View v) {

                return false;
            }
        });
        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                Intent in = new Intent(context, MyData.class);
//                in.putExtra("id",listitem.getId().toString().trim());
//                in.putExtra("user",listitem.getUser().toString().trim());
//                in.putExtra("name",listitem.getName().toString().trim());
//                in.putExtra("pass",listitem.getPass().toString().trim());
//                in.putExtra("desc",listitem.getDescription().toString().trim());


             //   context.startActivity(in);

            }
        });
        holder.name.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {


                return false;
            }
        });


    }


    @Override
    public int getItemCount() {
        return mylistitem.size();
    }

    public class   ViewHolder extends RecyclerView.ViewHolder {

        public TextView name,user,description;
        public TextView id;
        public RelativeLayout myRelativ;






        public ViewHolder(View itemView) {
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.name);
            id = (TextView)itemView.findViewById(R.id.id);
           // user = (TextView)itemView.findViewById(R.id.name);


            myRelativ = (RelativeLayout)itemView.findViewById(R.id.myRelativ);
        }
    }



}
