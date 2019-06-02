package com.example.informa_tec.Controller;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.informa_tec.Adapter.RVMaestroAdapter;
import com.example.informa_tec.Modelo.Datos;
import com.example.informa_tec.Modelo.ModeloMaestro;
import com.example.informa_tec.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.informa_tec.Servicio.Queue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ControladorMaestro extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RVMaestroAdapter adapter;
    List<ModeloMaestro> maestros;
    private Queue queue;
    private int id_materia;
    private Datos datosObtenidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controlador_maestro);
        queue = Queue.getInstance(this);
        recyclerView = findViewById(R.id.rv_maestro);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        Bundle objetoEnviado=getIntent().getExtras();
        datosObtenidos= (Datos) objetoEnviado.getSerializable("datos"); //no se si este bien
        id_materia = datosObtenidos.getMateria();

        llenarLista();

    }

    private void llenarLista() {

        StringRequest request = new StringRequest(
                Request.Method.GET,
                "http://192.168.1.104:8000/api/profesor/listar?id_materia=" + this.id_materia,
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void responseHandler(String res) {

        try {
            JSONObject respons = new JSONObject(res);

            JSONArray response;
            response = new JSONArray(respons.getJSONArray("result").toString());
            boolean r = (boolean) respons.getBoolean("success");
            if (r) {
                maestros = new ArrayList<>();
                for (int i = 0, e = response.length(); i < e; i++) {
                    JSONObject maestro;
                    maestro = (JSONObject) response.get(i);

                    //datosObtenidos.setId_curso(Integer.parseInt(maestro.get("id").toString()));
                    int id_maestro=Integer.parseInt(maestro.get("id_profesor").toString());
                    String nombre = maestro.get("nombre").toString();
                    String apellidoP = maestro.get("ap_paterno").toString();
                    String apellidoM = maestro.get("ap_materno").toString();
                    maestros.add(new ModeloMaestro(id_maestro,nombre,apellidoP,apellidoM));
                }
            }
            adapter = new RVMaestroAdapter(this, maestros,datosObtenidos);
            recyclerView.setAdapter(adapter);

        } catch (JSONException e1) {
            e1.printStackTrace();
        }
    }

    public void errorHandler(VolleyError error) {
        Toast.makeText(this, "No hay maestros", Toast.LENGTH_SHORT).show();
    }
}
