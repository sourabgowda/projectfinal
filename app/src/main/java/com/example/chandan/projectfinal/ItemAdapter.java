package com.example.chandan.projectfinal;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Chandan on 23-04-2017.
 */

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ItemViewHolder> {

    private  Context context;
    private  ArrayList<Item> itemList;
    public ItemAdapter(Context context, ArrayList<Item> itemList){
        this.context=context;
        this.itemList=itemList;
    }
    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.from(parent.getContext()).inflate(R.layout.item_cardview_layout,parent,false);

        ItemViewHolder itemViewHolder=new ItemViewHolder(view);
        return itemViewHolder;
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Item item=itemList.get(position);
        holder.zdatetime.setText(item.datetime);
        holder.ztitle.setText(item.title);
        holder.zcontent.setText(item.content);
        holder.zsender.setText(item.sender);
        holder.zsendermail.setText(item.sendermail);
        holder.zreceiver.setText(item.receiver);
        holder.ztype.setText(item.type);
        holder.zdepartment.setText(item.dept);
        holder.zsemester.setText(item.sem);
        holder.zsection.setText(item.section);


    }

    @Override
    public int getItemCount() {
        if(itemList!=null){
            return itemList.size();
        }return 0;
    }

    //ViewHolder Class
    public  static  class ItemViewHolder extends  RecyclerView.ViewHolder{

        public CardView cvItem;
        public TextView zdatetime;
        public TextView ztitle;
        public TextView zcontent;
        public TextView zsender;
        public TextView zsendermail;
        public TextView zreceiver;
        public TextView ztype;
        public TextView zdepartment;
        public TextView zsemester;
        public TextView zsection;


        public ItemViewHolder(View itemView) {
            super(itemView);
            cvItem=(CardView)itemView.findViewById(R.id.cvItem);
            zdatetime=(TextView)itemView.findViewById(R.id.datetime);
            ztitle=(TextView)itemView.findViewById(R.id.title);
            zcontent=(TextView)itemView.findViewById(R.id.content);
            zsender=(TextView)itemView.findViewById(R.id.sender);
            zsendermail=(TextView)itemView.findViewById(R.id.sendermail);
            zreceiver=(TextView)itemView.findViewById(R.id.receiver);
            ztype=(TextView)itemView.findViewById(R.id.type);
            zdepartment=(TextView)itemView.findViewById(R.id.dept);
            zsemester=(TextView)itemView.findViewById(R.id.semester);
            zsection=(TextView)itemView.findViewById(R.id.section);


        }
    }
}
