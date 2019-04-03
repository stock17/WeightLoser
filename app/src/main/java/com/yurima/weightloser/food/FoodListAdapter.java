package com.yurima.weightloser.food;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.yurima.weightloser.R;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FoodListAdapter extends RecyclerView.Adapter <FoodListAdapter.ViewHolder> {

    List<Food> foods = new ArrayList<>();

    public FoodListAdapter(List<Food> foods){
        this.foods = foods;
    }

    @Override
    public FoodListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_food_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.nameTextView.setText(foods.get(position).getTitle());
        holder.unitTextView.setText(foods.get(position).getStringUnit());
        holder.valueTextView.setText(String.valueOf(foods.get(position).getValue()));
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_food_list_name) TextView nameTextView;
        @BindView(R.id.tv_food_list_unit) TextView unitTextView;
        @BindView(R.id.tv_food_list_value) TextView valueTextView;

        ViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    public void onChangeDataSet(List<Food> foods){
        this.foods = foods;
        notifyDataSetChanged();
    }
}
