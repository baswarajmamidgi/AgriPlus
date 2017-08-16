package com.alltronics.baswarajmamidgi.agriplus;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Created by baswarajmamidgi on 10/03/17.
 */

public class CardViewAdapter extends RecyclerView.Adapter<CardViewAdapter.MyViewHolder> {
    public Context context;
    private List<CardViewClass> cardViewClassList;

    class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;

        MyViewHolder(View view){
            super(view);
            imageView = (ImageView) view.findViewById(R.id.cardview_thumnail);
            textView = (TextView) view.findViewById(R.id.cardview_content);


        }
    }
    public CardViewAdapter(Context context, List<CardViewClass> cardViewClassList){
        this.context=context;
        this.cardViewClassList=cardViewClassList;

    }

    @Override
    public CardViewAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemview= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view,parent,false);
        return new MyViewHolder(itemview);
    }

    @Override
    public void onBindViewHolder(final CardViewAdapter.MyViewHolder holder, int position) {
        final CardViewClass cardViewClass=cardViewClassList.get(position);
        Glide.with(context).load(cardViewClass.getImageurl())
                .placeholder(R.drawable.ic_home)
                .into(holder.imageView);


        holder.textView.setText(cardViewClass.getContent());



    }

    @Override
    public int getItemCount() {
        return cardViewClassList.size();
    }

}

