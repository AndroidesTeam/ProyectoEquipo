package com.example.informa_tec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.informa_tec.Controller.ControladorSemestre;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void login(View vista) {
        Intent intent = new Intent(this, ControladorSemestre.class);
        startActivity(intent);
    }
}
