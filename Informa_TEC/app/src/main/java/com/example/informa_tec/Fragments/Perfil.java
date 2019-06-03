package com.example.informa_tec.Fragments;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.informa_tec.Adapter.RVComentariosAdapter;
import com.example.informa_tec.Modelo.Conexion;
import com.example.informa_tec.Modelo.Datos;
import com.example.informa_tec.Modelo.ModeloComentarios;
import com.example.informa_tec.R;
import com.example.informa_tec.Servicio.Queue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Perfil extends Fragment {
    private RVComentariosAdapter adapter;
    private Queue queue;
    private Datos datosObtenidos;
    private int curso;
    private EditText npvP1R1, npvP1R2, npvP1R3, npvP1R4,
            npvP2R1, npvP2R2, npvP2R3, npvP2R4,
            npvP3R1, npvP3R2, npvP3R3, npvP3R4,
            npvP4R1, npvP4R2, npvP4R3, npvP4R4,
            npvP5R1, npvP5R2, npvP5R3, npvP5R4,
            npvP6R1, npvP6R2, npvP6R3, npvP6R4,
            npvP7R1, npvP7R2, npvP7R3, npvP7R4,
            npvP8R1, npvP8R2, npvP8R3, npvP8R4;

    List npv;

    public Perfil() {

    }

    @SuppressLint("ValidFragment")
    public Perfil(Datos datosObtenidos) {
        this.datosObtenidos = datosObtenidos;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View vista = inflater.inflate(R.layout.fragment_perfil, container, false);
        queue = Queue.getInstance(getContext());
        curso = datosObtenidos.getId_curso();

        npvP1R1 = (EditText) vista.findViewById(R.id.et_p2_r1_np);
        npvP1R2 = (EditText) vista.findViewById(R.id.et_p2_r2_np);
        npvP1R3 = (EditText) vista.findViewById(R.id.et_p2_r3_np);
        npvP1R4 = (EditText) vista.findViewById(R.id.et_p2_r4_np);

        npvP2R1 = (EditText) vista.findViewById(R.id.et_p3_r1_np);
        npvP2R2 = (EditText) vista.findViewById(R.id.et_p3_r2_np);
        npvP2R3 = (EditText) vista.findViewById(R.id.et_p3_r3_np);
        npvP2R4 = (EditText) vista.findViewById(R.id.et_p3_r4_np);

        npvP3R1 = (EditText) vista.findViewById(R.id.et_p4_r1_np);
        npvP3R2 = (EditText) vista.findViewById(R.id.et_p4_r2_np);
        npvP3R3 = (EditText) vista.findViewById(R.id.et_p4_r3_np);
        npvP3R4 = (EditText) vista.findViewById(R.id.et_p4_r4_np);

        npvP4R1 = (EditText) vista.findViewById(R.id.et_p5_r1_np);
        npvP4R2 = (EditText) vista.findViewById(R.id.et_p5_r2_np);
        npvP4R3 = (EditText) vista.findViewById(R.id.et_p5_r3_np);
        npvP4R4 = (EditText) vista.findViewById(R.id.et_p5_r4_np);

        npvP5R1 = (EditText) vista.findViewById(R.id.et_p6_r1_np);
        npvP5R2 = (EditText) vista.findViewById(R.id.et_p6_r2_np);
        npvP5R3 = (EditText) vista.findViewById(R.id.et_p6_r3_np);
        npvP5R4 = (EditText) vista.findViewById(R.id.et_p6_r4_np);

        npvP6R1 = (EditText) vista.findViewById(R.id.et_p7_r1_np);
        npvP6R2 = (EditText) vista.findViewById(R.id.et_p7_r2_np);
        npvP6R3 = (EditText) vista.findViewById(R.id.et_p7_r3_np);
        npvP6R4 = (EditText) vista.findViewById(R.id.et_p7_r4_np);

        npvP7R1 = (EditText) vista.findViewById(R.id.et_p8_r1_np);
        npvP7R2 = (EditText) vista.findViewById(R.id.et_p8_r2_np);
        npvP7R3 = (EditText) vista.findViewById(R.id.et_p8_r3_np);
        npvP7R4 = (EditText) vista.findViewById(R.id.et_p8_r4_np);

        npvP8R2 = (EditText) vista.findViewById(R.id.et_p9_r2_np);
        npvP8R3 = (EditText) vista.findViewById(R.id.et_p9_r3_np);
        npvP8R4 = (EditText) vista.findViewById(R.id.et_p9_r4_np);
        npvP8R1 = (EditText) vista.findViewById(R.id.et_p9_r5_np);

        npv = new ArrayList();

        llenarLista();

        return vista;
    }

    private void llenarLista() {
        StringRequest request = new StringRequest(
                Request.Method.GET,
                Conexion.servidor + "evaluacion/obtenerPromedio?id_curso=" + this.curso,
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
            JSONObject response = new JSONObject(res);
            JSONObject result = new JSONObject(response.getString("result"));
            boolean r = (boolean) response.getBoolean("success");
            if (r) {
                //JSONObject pregunta = (JSONObject) responseArray.get(1);
                JSONArray preguntaArray;
                preguntaArray = new JSONArray(result.getJSONArray("preguntas").toString());
                for (int j = 0; j < preguntaArray.length(); j++) {
                    JSONObject valores;
                    valores = (JSONObject) preguntaArray.get(j);
                    npv.add(valores.get("respondio_1"));
                    npv.add(valores.get("respondio_2"));
                    npv.add(valores.get("respondio_3"));
                    npv.add(valores.get("respondio_4"));
                }
                llenarTv();
            }

        } catch (JSONException e1) {
            e1.printStackTrace();
        }

    }

    public void errorHandler(VolleyError error) {
        Toast.makeText(getContext(), "No hay promedios", Toast.LENGTH_SHORT).show();
    }

    public void llenarTv() {
        npvP1R1.setText(npv.get(0).toString());
        npvP1R2.setText(npv.get(1).toString());
        npvP1R3.setText(npv.get(2).toString());
        npvP1R4.setText(npv.get(3).toString());
        npvP2R1.setText(npv.get(4).toString());
        npvP2R2.setText(npv.get(5).toString());
        npvP2R3.setText(npv.get(6).toString());
        npvP2R4.setText(npv.get(7).toString());
        npvP3R1.setText(npv.get(8).toString());
        npvP3R2.setText(npv.get(9).toString());
        npvP3R3.setText(npv.get(10).toString());
        npvP3R4.setText(npv.get(11).toString());
        npvP4R1.setText(npv.get(12).toString());
        npvP4R2.setText(npv.get(13).toString());
        npvP4R3.setText(npv.get(14).toString());
        npvP4R4.setText(npv.get(15).toString());
        npvP5R1.setText(npv.get(16).toString());
        npvP5R2.setText(npv.get(17).toString());
        npvP5R3.setText(npv.get(18).toString());
        npvP5R4.setText(npv.get(19).toString());
        npvP6R1.setText(npv.get(20).toString());
        npvP6R2.setText(npv.get(21).toString());
        npvP6R3.setText(npv.get(22).toString());
        npvP6R4.setText(npv.get(23).toString());
        npvP7R1.setText(npv.get(24).toString());
        npvP7R2.setText(npv.get(25).toString());
        npvP7R3.setText(npv.get(26).toString());
        npvP7R4.setText(npv.get(27).toString());
        npvP8R1.setText(npv.get(28).toString());
        npvP8R2.setText(npv.get(29).toString());
        npvP8R3.setText(npv.get(30).toString());
        npvP8R4.setText(npv.get(31).toString());
    }
}