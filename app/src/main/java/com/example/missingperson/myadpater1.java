package com.example.missingperson;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class myadpater1 extends RecyclerView.Adapter<myadpater1.myviewholder> {

    List<responsemodel1> data;

    public myadpater1(List<responsemodel1> data) {
        this.data = data;
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.singlerow1,parent,false);
        return  new myviewholder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull myviewholder holder, int position) {

        holder.t1.setText(data.get(position).getName());
        holder.t2.setText(data.get(position).getGender());
        holder.t3.setText(data.get(position).getAge());
        holder.t4.setText(data.get(position).getDate());
        holder.t5.setText(data.get(position).getTime());
        holder.t6.setText(data.get(position).getNumber());
        holder.t7.setText(data.get(position).getAddress());
        holder.t8.setText(data.get(position).getMarks());
        Glide.with(holder.t1.getContext()).load("http://192.168.10.214/lostperson/images/"+data.get(position).getImg()).into(holder.img);



    }

    @Override
  public int getItemCount() {
      return data.size();
   }

    class myviewholder extends RecyclerView.ViewHolder
    {
        TextView t1,t2,t3,t4,t5,t6,t7,t8;
        ImageView img;

        public myviewholder(@NonNull View itemview)
        {
            super(itemview);
            t1=itemview.findViewById(R.id.t1);
            t2=itemview.findViewById(R.id.t2);
            t3=itemview.findViewById(R.id.t3);
            t4=itemview.findViewById(R.id.t4);
            t5=itemview.findViewById(R.id.t5);
            t6=itemview.findViewById(R.id.t6);
            t7=itemview.findViewById(R.id.t7);
            t8=itemview.findViewById(R.id.t8);
            img =itemview.findViewById(R.id.picCart);

        }
    }
}
