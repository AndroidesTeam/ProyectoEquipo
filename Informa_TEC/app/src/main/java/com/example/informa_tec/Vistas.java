package com.example.informa_tec;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.informa_tec.Fragments.Comentarios;
import com.example.informa_tec.Fragments.Evaluar;
import com.example.informa_tec.Fragments.Perfil;
import com.example.informa_tec.Modelo.Conexion;
import com.example.informa_tec.Modelo.Datos;
import com.example.informa_tec.Servicio.Queue;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Vistas extends AppCompatActivity {

    private Datos datosObtenidos;
    private Queue queue;
    private Context contexto;
    private Fragment fragmentSeleccionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vistas);
        queue = Queue.getInstance(this);
        contexto = getBaseContext();
        final Bundle objetoEnviado = getIntent().getExtras();
        datosObtenidos = (Datos) objetoEnviado.getSerializable("datos"); //no se si este bien
        llenarLista();

        BottomNavigationView bottomBar = findViewById(R.id.bottombar);
        final Intent intent = new Intent(this, MainActivity.class);

        bottomBar.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.item_perfil:
                        fragmentSeleccionado = new Perfil(datosObtenidos);
                        break;
                    case R.id.item_comentarios:
                        fragmentSeleccionado = new Comentarios(datosObtenidos);
                        break;
                    case R.id.item_evaluacion:
                        //ejecutar el metodo
                        fragmentSeleccionado = new Evaluar(datosObtenidos);
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

    private void llenarLista() {

        StringRequest request = new StringRequest(
                Request.Method.GET,
                Conexion.servidor + "curso/mostrar?id_profesor="
                        + datosObtenidos.getMaestro()
                        + "&id_materia=" + datosObtenidos.getMateria(),
                new Response.Listener<String>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(String response) {
                        responseHandler(response);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        errorHandler(error);
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                return params;
            }
        };
        queue.addToQueue(request);

    }

    public void responseHandler(String res) {
        try {
            JSONObject response = new JSONObject(res);
            JSONObject responseR = response.getJSONObject("result");
            boolean r = (boolean) response.getBoolean("success");
            if (r) {
                int id_curso = responseR.getInt("id");
                datosObtenidos.setId_curso(id_curso);
                fragmentSeleccionado = new Perfil(datosObtenidos);
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, fragmentSeleccionado).commit();

            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    public void errorHandler(VolleyError error) {
        //Log.d("CALAAANDOOOOO",error.getMessage());
        Toast.makeText(this, "No hay cursos", Toast.LENGTH_SHORT).show();
    }
}