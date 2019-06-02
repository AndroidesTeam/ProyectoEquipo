package com.example.informa_tec.Fragments;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.informa_tec.Adapter.RVComentariosAdapter;
import com.example.informa_tec.Modelo.Datos;
import com.example.informa_tec.Modelo.ModeloComentarios;
import com.example.informa_tec.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.example.informa_tec.Servicio.Queue;


public class Comentarios extends Fragment {
    private RecyclerView recyclerView;
    private RVComentariosAdapter adapter;
    List<ModeloComentarios> comentarios;
    private Queue queue;
    private int curso;
    private Datos datosObtenidos;

    public Comentarios(){

    }
    @SuppressLint("ValidFragment")
    public Comentarios(Datos datosObtenidos){
     this.datosObtenidos=datosObtenidos;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_comentarios, container, false);
        queue = Queue.getInstance(getContext());
        recyclerView = vista.findViewById(R.id.rv_comentarios);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        curso=datosObtenidos.getId_curso();
        llenarLista();
        return vista;
    }

    private void llenarLista() {
        StringRequest request = new StringRequest(
                Request.Method.GET,
                "http://192.168.1.104:8000/api/comentario/comentarios-profesor?id_curso="+this.curso,
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
                //params.put("id_curso", String.valueOf(1));
                return params;
            }
        };
        queue.addToQueue(request);
        /* DATOS DE PRUEBA
        comentarios.add(new ModeloComentarios("25/05/2019", "El profesor es muy bueno"));
        comentarios.add(new ModeloComentarios("22/05/2019", "Excelente profesor"));
        comentarios.add(new ModeloComentarios("10/05/2019", "Sin ofender a mi no me parece bueno, ya que es demasiado flexible con los alumnos"));
        comentarios.add(new ModeloComentarios("29/04/2019", "Bastante flexible, aunque en ocasiones los alumnos abusan de la misma"));
        comentarios.add(new ModeloComentarios("25/04/2019", "Muy buen maestro"));
        comentarios.add(new ModeloComentarios("13/04/2019", "Excelente profesor"));*/
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void responseHandler(String res) {
        //Toast.makeText(getContext(), res, Toast.LENGTH_SHORT).show();
        try {
            JSONObject respons = new JSONObject(res);
            // Toast.makeText(getContext(),  respons.getJSONArray("result").toString(), Toast.LENGTH_SHORT).show();
            JSONArray response;
            response = new JSONArray(respons.getJSONArray("result").toString());
            boolean r = (boolean) respons.getBoolean("success");
            if (r) {
                comentarios = new ArrayList<>();
                for(int i = 0, e = response.length(); i < e; i++){
                    JSONObject comentario;
                    comentario = (JSONObject) response.get(i);
                    String fecha = comentario.get("fecha").toString();
                    String texto = comentario.get("texto").toString();
                    comentarios.add(new ModeloComentarios(fecha, texto));
                }
            }
            adapter = new RVComentariosAdapter(getContext(), comentarios);
            recyclerView.setAdapter(adapter);
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
    }

    public void errorHandler(VolleyError error) {
        Toast.makeText(getContext(), "No hay comentarios", Toast.LENGTH_SHORT).show();
    }
}