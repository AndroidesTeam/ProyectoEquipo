package com.example.informa_tec;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.informa_tec.Fragments.Comentarios;
import com.example.informa_tec.Fragments.Evaluar;
import com.example.informa_tec.Fragments.Perfil;


public class Vistas extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vistas);

        BottomNavigationView bottomBar = findViewById(R.id.bottombar);
        final Intent intent=new Intent(this,MainActivity.class);

        Fragment fragmentSeleccionado = new Perfil();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentSeleccionado).commit();

        bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment fragmentSeleccionado = null;
                switch (menuItem.getItemId()){
                    case R.id.item_perfil:
                        fragmentSeleccionado = new Perfil();
                        break;
                    case R.id.item_comentarios:
                        fragmentSeleccionado = new Comentarios();
                        break;
                    case R.id.item_evaluacion:
                        fragmentSeleccionado = new Evaluar();
                        break;
                    case R.id.item_salir:
                        startActivity(intent);
                        return true;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentSeleccionado).commit();
                return true;
            }
        });
    }
}