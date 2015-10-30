package com.example.pc_.nutriapp;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.pc_.nutriapp.rest.Food;

import java.util.List;


public class FoodAdapter extends BaseAdapter {
    private Context context;
    private List<Food> list;

    public FoodAdapter(Context context, List<Food> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if(list != null)
            return list.size();
        return 0;
    }

    @Override
    public Object getItem(int position) {
        if(list != null)
            return list.get(position);
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.food_adapter, parent, false);
        }
        final Food food = list.get(position);
       final Food.FoodFields fields = food.fields;

        TextView txtTitle = (TextView) convertView.findViewById(R.id.txt_title);
        txtTitle.setText(fields.getItem_name());

        TextView txtCalories = (TextView) convertView.findViewById(R.id.txt_calories);
        txtCalories.setText(String.valueOf(fields.getNf_calories()));

        TextView txtServing = (TextView) convertView.findViewById(R.id.txt_serving);
        txtServing.setText(String.valueOf(fields.getNf_serving_size_qty()));

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = fields.getItem_id();
                ((SearchActivity) context).startFoodActivity(id);
            }
        });
        return convertView;
    }
}
