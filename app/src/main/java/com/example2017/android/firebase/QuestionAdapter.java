package com.example2017.android.firebase;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by M7moud on 25-Nov-17.
 */
//MoviesAdapter is the Adapter_class
//movieHolder is the holder_class
class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.movieHolder> {

    List<questionmodel> list;
    //cnstructr
    public MoviesAdapter(List<questionmodel> list){
        this.list=list;
    }


    @Override
    public movieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View row= LayoutInflater.from(parent.getContext()).inflate(R.layout.question,parent,false);
        movieHolder hold=new movieHolder(row);

        return hold;
    }

    @Override
    public void onBindViewHolder(movieHolder holder, int position)
    {

        //instace from moviemodel
        questionmodel model=list.get(position);
        holder.txt_question.setText(model.question);
        holder.txt_answer.setText(model.answer);

    }

    @Override
    public int getItemCount()
    {
        //to return all images
        //if zero it won't appeat any thing
        return list.size();
    }

    //holder_class
    class movieHolder extends RecyclerView.ViewHolder
    {
        TextView txt_question,txt_answer;


        public movieHolder(View itemView)
        {
            super(itemView);

            txt_question=(TextView)itemView.findViewById(R.id.question);
            txt_answer=(TextView)itemView.findViewById(R.id.id_Shop_adress);

        }

    }

}