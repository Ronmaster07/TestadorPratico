package com.ronmaster.testadorpratico;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class CategoriaRecyclerViewAdapter extends RecyclerView.Adapter<CategoriaRecyclerViewAdapter.ViewHolder>{

    private static final String TAG = "CategoriaRecyclerViewAdapter";

    //vars
    private ArrayList<String> mNomes = new ArrayList<>();
    private ArrayList<Integer> mImagensURL = new ArrayList<>();
    private Context context;

    public CategoriaRecyclerViewAdapter( Context context, ArrayList<String> mNomes, ArrayList<Integer> mImagensURL) {
        this.mNomes = mNomes;
        this.mImagensURL = mImagensURL;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categoria_list, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        Glide.with(context)
                .asBitmap()
                .load(mImagensURL.get(position))
                .into(viewHolder.circleimage);

        viewHolder.textname.setText( mNomes.get(position));
        viewHolder.circleimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,mNomes.get(position), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mImagensURL.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        CircleImageView circleimage;
        TextView textname;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            circleimage = itemView.findViewById(R.id.image_category);
            textname = itemView.findViewById(R.id.txt_name);
        }
    }

}
