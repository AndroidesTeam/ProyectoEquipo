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
import android.widget.RadioButton;
import android.widget.RadioGroup;
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

import java.util.HashMap;
import java.util.Map;

public class Evaluar extends Fragment{
    private TextView titulo;
    private RadioGroup pregunta1,pregunta2,pregunta3,pregunta4,pregunta5,pregunta6,pregunta7,pregunta8;
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
        titulo = (TextView) view.findViewById(R.id.titulo);
        pregunta1 = (RadioGroup) view.findViewById(R.id.pregunta1);
        pregunta2 = (RadioGroup) view.findViewById(R.id.pregunta2);
        pregunta3 = (RadioGroup) view.findViewById(R.id.pregunta3);
        pregunta4 = (RadioGroup) view.findViewById(R.id.pregunta4);
        pregunta5 = (RadioGroup) view.findViewById(R.id.pregunta5);
        pregunta6 = (RadioGroup) view.findViewById(R.id.pregunta6);
        pregunta7 = (RadioGroup) view.findViewById(R.id.pregunta7);
        pregunta8 = (RadioGroup) view.findViewById(R.id.pregunta8);

        setEvents();

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
                                titulo.setText("Evaluación realizada");
                                options.setVisibility(View.VISIBLE);
                                evaluar.setVisibility(View.GONE);
                            }else{
                                titulo.setText("Evalua el curso");
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

    public void setEvents(){
        pregunta1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selected = (RadioButton) pregunta1.findViewById(pregunta1.getCheckedRadioButtonId());
                Log.d("prueba",String.valueOf(pregunta1.indexOfChild(selected)+1));
            }
        });
        pregunta2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selected = (RadioButton) pregunta2.findViewById(pregunta2.getCheckedRadioButtonId());
                Log.d("prueba",String.valueOf(pregunta2.indexOfChild(selected)+1));
            }
        });
        pregunta3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selected = (RadioButton) pregunta3.findViewById(pregunta3.getCheckedRadioButtonId());
                Log.d("prueba",String.valueOf(pregunta3.indexOfChild(selected)+1));
            }
        });
        pregunta4.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selected = (RadioButton) pregunta4.findViewById(pregunta4.getCheckedRadioButtonId());
                Log.d("prueba",String.valueOf(pregunta4.indexOfChild(selected)+1));
            }
        });
        pregunta5.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selected = (RadioButton) pregunta5.findViewById(pregunta5.getCheckedRadioButtonId());
                Log.d("prueba",String.valueOf(pregunta5.indexOfChild(selected)+1));
            }
        });
        pregunta6.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selected = (RadioButton) pregunta6.findViewById(pregunta6.getCheckedRadioButtonId());
                Log.d("prueba",String.valueOf(pregunta6.indexOfChild(selected)+1));
            }
        });
        pregunta7.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selected = (RadioButton) pregunta7.findViewById(pregunta7.getCheckedRadioButtonId());
                Log.d("prueba",String.valueOf(pregunta7.indexOfChild(selected)+1));
            }
        });
        pregunta8.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                RadioButton selected = (RadioButton) pregunta8.findViewById(pregunta8.getCheckedRadioButtonId());
                Log.d("prueba",String.valueOf(pregunta8.indexOfChild(selected)+1));
            }
        });
    }
}