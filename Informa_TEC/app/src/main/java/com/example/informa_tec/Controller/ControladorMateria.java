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
import com.example.informa_tec.Adapter.RVMateriaAdapter;
import com.example.informa_tec.Modelo.Conexion;
import com.example.informa_tec.Modelo.Datos;
import com.example.informa_tec.Modelo.ModeloMateria;
import com.example.informa_tec.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.informa_tec.Servicio.Queue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ControladorMateria extends AppCompatActivity {
    private RecyclerView recyclerView;
    private RVMateriaAdapter adapter;
    List<ModeloMateria> materias;
    private Queue queue;
    private String semestre;

    private Datos datosObtenidos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_controlador_materia);

        queue = Queue.getInstance(this);
        recyclerView = findViewById(R.id.rv_materia);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        Bundle objetoEnviado=getIntent().getExtras();

        datosObtenidos= (Datos) objetoEnviado.getSerializable("datos"); //no se si este bien
        semestre=datosObtenidos.getSemestre();

        llenarLista();
    }
    private void llenarLista() {
        StringRequest request = new StringRequest(
                Request.Method.GET,
                Conexion.servidor +"materia/listar?semestre="+this.semestre,
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
                materias = new ArrayList<>();
                for (int i = 0, e = response.length(); i < e; i++) {
                    JSONObject mate;
                    mate = (JSONObject) response.get(i);
                    int id_materia =Integer.parseInt(mate.get("id").toString());
                    String nombreMateria = mate.get("nombre").toString(); //nombre de la materia
                    materias.add(new ModeloMateria(id_materia,nombreMateria));
                }
            }
            adapter = new RVMateriaAdapter(this, materias,datosObtenidos);
            recyclerView.setAdapter(adapter);
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
    }
    public void errorHandler(VolleyError error) {
        Toast.makeText(this, "No hay materias", Toast.LENGTH_SHORT).show();
    }
}
