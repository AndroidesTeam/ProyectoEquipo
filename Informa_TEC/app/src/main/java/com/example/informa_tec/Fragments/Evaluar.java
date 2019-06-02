package com.example.informa_tec.Fragments;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.informa_tec.Modelo.Conexion;
import com.example.informa_tec.Modelo.Datos;
import com.example.informa_tec.R;
import com.example.informa_tec.Servicio.Queue;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.Map;

public class Evaluar extends Fragment{
    private LinearLayout options,evaluar;
    private Queue queue;
    private Datos datosObtenidos;

    public Evaluar(){

    }
    @SuppressLint("ValidFragment")
    public Evaluar(Datos datosObtenidos){
        this.datosObtenidos = datosObtenidos;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_evaluar, container, false);
        options = (LinearLayout) view.findViewById(R.id.options);
        evaluar = (LinearLayout) view.findViewById(R.id.evaluar);

        queue = Queue.getInstance(getContext());

        validate();
        return view;
    }

    public void validate (){
        StringRequest request = new StringRequest(
                Request.Method.POST,
                Conexion.servidor +"evaluacion/por-usuario",
                new Response.Listener<String>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject result = new JSONObject(response);
                            if(result.getBoolean("success")){
                                options.setVisibility(View.VISIBLE);
                                evaluar.setVisibility(View.GONE);
                            }else{
                                options.setVisibility(View.GONE);
                                evaluar.setVisibility(View.VISIBLE);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("error",error.getMessage());
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("id_user", String.valueOf(datosObtenidos.getId_usuario()));
                params.put("id_curso", String.valueOf(datosObtenidos.getId_curso()));
                return params;
            }
        };
        queue.addToQueue(request);
    }

}