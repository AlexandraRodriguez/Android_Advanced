package com.example.pc_.nutriapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.example.pc_.nutriapp.rest.ApiHelper;
import com.example.pc_.nutriapp.rest.FoodData;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class FoodActivity extends Activity {
    private TextView txtTittle;
    private TextView txtCalories;
    private TextView txtServing;
    private TextView txtProteinas;
    private TextView txtCarbs;
    private TextView txtFibras;
    private TextView txtSugar;
    private TextView txtSaturatedFat;
    private TextView txtNoSaturatedFat;
    private String id;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.food_activity);

        id = getIntent().getStringExtra("ID");
        txtTittle = (TextView) findViewById(R.id.txtTitle);
        txtCalories = (TextView) findViewById(R.id.txtCalories);
        txtServing = (TextView) findViewById(R.id.txtServing);
        txtProteinas = (TextView) findViewById(R.id.txtProteinas);
        txtCarbs = (TextView) findViewById(R.id.txtCarbs);
        txtFibras = (TextView) findViewById(R.id.txtFiber);
        txtSugar = (TextView) findViewById(R.id.txtSugar);
        txtSaturatedFat = (TextView) findViewById(R.id.txtSaturada);
        txtNoSaturatedFat = (TextView) findViewById(R.id.txtNoSaturada);
        if (id != null)
            ApiHelper.getService().getFoodData(id, getItemCB);
    }

    private Callback<FoodData> getItemCB = new Callback<FoodData>() {
        @Override
        public void success(FoodData foodData, Response response) {
            txtTittle.setText(foodData.getItem_name());
            txtCalories.setText("Calories: " + (int) foodData.getNf_calories());
            txtServing.setText("Serving size: "+foodData.getNf_serving_size_qty());
            txtProteinas.setText("Protein: "+foodData.getNf_protein()+"");
            txtCarbs.setText("Carbohydrates: "+foodData.getNf_total_carbohydrate()+"");
            txtFibras.setText("Dietary Fiber: "+foodData.getNf_dietary_fiber()+"");
            txtSugar.setText("Sugar: "+foodData.getNf_sugars()+"");
            txtSaturatedFat.setText("Saturated fat: "+foodData.getNf_saturated_fat()+"");
            txtNoSaturatedFat.setText("Total fat: "+foodData.getNf_total_fat()+"");

        }

        @Override
        public void failure(RetrofitError error) {
            Log.e("tag", "error");
        }
    };
}
