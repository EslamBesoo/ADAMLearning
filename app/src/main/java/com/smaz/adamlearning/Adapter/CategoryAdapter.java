package com.smaz.adamlearning.Adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.smaz.adamlearning.Util.AppStatus;
import com.smaz.adamlearning.Model.Category;
import com.smaz.adamlearning.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by mac on 3/24/17.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.ViewHolder> {

    private ArrayList<Category> CategoryArray;
    private Context context;

    public CategoryAdapter(Context context, ArrayList<Category> Category) {
        this.context = context;
        this.CategoryArray = Category;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.grid_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.categoryName.setText(CategoryArray.get(position).getName());

        if (AppStatus.getInstance(context).isOnline()) {
            Picasso.with(context).load(CategoryArray.get(position).getImageUrl()).resize(150, 220).into(holder.categoryImg);
        } else {

        }
    }

    @Override
    public int getItemCount() {
        return CategoryArray.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView categoryName;
        private ImageView categoryImg;



        public ViewHolder(View view) {
            super(view);
            categoryName = (TextView) view.findViewById(R.id.categoryName);
            categoryImg = (ImageView) view.findViewById(R.id.categoryImg);

        }

    }
}
