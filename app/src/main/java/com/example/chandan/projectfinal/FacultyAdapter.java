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
 * Created by Chandan on 25-04-2017.
 */

public class FacultyAdapter extends RecyclerView.Adapter<FacultyAdapter.FacultyViewHolder> {
    private Context context;
    private ArrayList<Fnotice> itemList;
    public FacultyAdapter(Context context, ArrayList<Fnotice> itemList){
        this.context=context;
        this.itemList=itemList;
    }

    @Override
    public FacultyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(parent.getContext());
        View view=inflater.from(parent.getContext()).inflate(R.layout.cards_faculty,parent,false);

        FacultyAdapter.FacultyViewHolder facultyViewHolder=new FacultyAdapter.FacultyViewHolder(view);
        return facultyViewHolder;


    }

    @Override
    public void onBindViewHolder(FacultyViewHolder holder, int position) {

        Fnotice item=itemList.get(position);
        holder.fdatetime.setText(item.fdatetime);
        holder.ftitle.setText(item.ftitle);
        holder.fcontent.setText(item.fcontent);
        holder.fsender.setText(item.fsender);
        holder.fsendermail.setText(item.fsendermail);
        holder.freceiver.setText(item.freceiver);
        holder.ftype.setText(item.ftype);
        holder.fdepartment.setText(item.fdept);
        holder.fdesignation.setText(item.fdesignation);


    }

    @Override
    public int getItemCount() {
        if(itemList!=null){
            return itemList.size();
        }return 0;
    }

    public class FacultyViewHolder extends RecyclerView.ViewHolder{
        public CardView cvItemFaculty;
        public TextView fdatetime;
        public TextView ftitle;
        public TextView fcontent;
        public TextView fsender;
        public TextView fsendermail;
        public TextView freceiver;
        public TextView ftype;
        public TextView fdepartment;
        public TextView fdesignation;

        public FacultyViewHolder(View itemView) {
            super(itemView);

            cvItemFaculty=(CardView)itemView.findViewById(R.id.cvItemFaculty);
            fdatetime=(TextView)itemView.findViewById(R.id.datetime);
            ftitle=(TextView)itemView.findViewById(R.id.title);
            fcontent=(TextView)itemView.findViewById(R.id.content);
            fsender=(TextView)itemView.findViewById(R.id.sender);
            fsendermail=(TextView)itemView.findViewById(R.id.sendermail);
            freceiver=(TextView)itemView.findViewById(R.id.receiver);
            ftype=(TextView)itemView.findViewById(R.id.type);
            fdepartment=(TextView)itemView.findViewById(R.id.dept);
            fdesignation=(TextView)itemView.findViewById(R.id.designation);

        }
    }
}
