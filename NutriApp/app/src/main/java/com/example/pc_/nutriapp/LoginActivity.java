package com.example.pc_.nutriapp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import javax.microedition.khronos.egl.EGLDisplay;

public class LoginActivity extends Activity implements Constants {

    private String[] peso = new String[]{"Kg.", "Lb."};
    private String[] estatura = new String[]{"cm.", "pulg."};

    private EditText editEdad;
    private EditText editPeso;
    private EditText editEstatura;
    private boolean sexo;

    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.login);

        editEdad = (EditText) findViewById(R.id.edit_edad);
        editEstatura = (EditText) findViewById(R.id.edit_estatura);
        editPeso = (EditText) findViewById(R.id.edit_peso);
        sexo = true;

        Button btnAccept = (Button) findViewById(R.id.btn_accept);
        btnAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor = getSharedPreferences(PREFERENCES, MODE_PRIVATE).edit();
                editor.putString(EDAD, String.valueOf(editEdad.getText()));
                editor.putString(ESTATURA, String.valueOf(editEstatura.getText()));
                editor.putString(PESO, String.valueOf(editPeso.getText()));
                editor.putBoolean(SEXO, sexo);
                editor.putBoolean(IS_LOGGED_IN, true);
                editor.commit();

                startMainActivity();
            }
        });

        if(getSharedPreferences(PREFERENCES, MODE_PRIVATE).getBoolean(IS_LOGGED_IN, false)){
            startMainActivity();
        }


    }

    public void startMainActivity(){
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }



    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();
        switch (view.getId()) {
            case R.id.radioMujer:
                if (checked)
                    sexo = true;
                break;
            case R.id.radioHombre:
                if (checked)
                    sexo = false;
                break;
        }
    }
}
