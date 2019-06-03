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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.informa_tec.Modelo.Conexion;
import com.example.informa_tec.Modelo.Datos;
import com.example.informa_tec.R;
import com.example.informa_tec.Servicio.Queue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Evaluar extends Fragment{
    private TextView titulo;
    private RadioGroup pregunta1,pregunta2,pregunta3,pregunta4,pregunta5,pregunta6,pregunta7,pregunta8;
    private Button evaluarB, modificarB, eliminarB;
    private EditText comentario;

    private LinearLayout options,evaluarL;
    private Queue queue;
    private Datos datosObtenidos;
    private RadioButton selected;
    ArrayList<Integer> puntuaciones;

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
        evaluarL = (LinearLayout) view.findViewById(R.id.evaluar);
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

        evaluarB = (Button) view.findViewById(R.id.evaluar2);
        evaluarB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluar(0);
            }
        });
        modificarB = (Button) view.findViewById(R.id.modificar);
        modificarB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluar(1);
            }
        });
        comentario = (EditText) view.findViewById(R.id.comentario);

        eliminarB = (Button)view.findViewById(R.id.eliminar);
        eliminarB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarEvaluacion();
            }
        });
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
                                evaluarL.setVisibility(View.GONE);
                                cargarRespuestas();
                                obtenerComentario();
                            }else{
                                titulo.setText("Evalua el curso");
                                options.setVisibility(View.GONE);
                                evaluarL.setVisibility(View.VISIBLE);
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
        selected = (RadioButton) pregunta8.findViewById(pregunta8.getCheckedRadioButtonId());
        Log.d("prueba",String.valueOf(pregunta8.indexOfChild(selected)+1));
    }

    public void evaluar(int action){
        String peticion= "";
        if(action==0){
            peticion = Conexion.servidor +"evaluacion/insertar";
        }else if(action==1){
            peticion = Conexion.servidor +"evaluacion/actualizar";
        }
        StringRequest request = new StringRequest(
                Request.Method.POST,
                peticion,
                new Response.Listener<String>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(String response) {
                        titulo.setText("Evaluación realizada");
                        options.setVisibility(View.VISIBLE);
                        evaluarL.setVisibility(View.GONE);Log.d("insertado",response.toString());
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        try{
                            Log.e("error",error.getMessage());
                        }catch(Exception e){
                            Log.e("error",e.getMessage());
                        }
                    }
                }
        ) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("calificacion", String.valueOf(10));
                params.put("id_usuario", String.valueOf(datosObtenidos.getId_usuario()));
                Calendar fecha = Calendar.getInstance();
                params.put("fecha",fecha.get(Calendar.YEAR)+"-"+fecha.get(Calendar.MONTH)+"-"+fecha.get(Calendar.DATE));
                params.put("id_curso", String.valueOf(datosObtenidos.getId_curso()));
                params.put("comentario",comentario.getText().toString());
                puntuaciones = new ArrayList();

                    selected = (RadioButton) pregunta1.findViewById(pregunta1.getCheckedRadioButtonId());
                    puntuaciones.add(pregunta1.indexOfChild(selected)+1);

                    selected = (RadioButton) pregunta2.findViewById(pregunta2.getCheckedRadioButtonId());
                    puntuaciones.add(pregunta2.indexOfChild(selected)+1);

                    selected = (RadioButton) pregunta3.findViewById(pregunta3.getCheckedRadioButtonId());
                    puntuaciones.add(pregunta3.indexOfChild(selected)+1);

                    selected = (RadioButton) pregunta4.findViewById(pregunta4.getCheckedRadioButtonId());
                    puntuaciones.add(pregunta4.indexOfChild(selected)+1);

                    selected = (RadioButton) pregunta5.findViewById(pregunta5.getCheckedRadioButtonId());
                puntuaciones.add(pregunta5.indexOfChild(selected)+1);

                    selected = (RadioButton) pregunta6.findViewById(pregunta6.getCheckedRadioButtonId());
                puntuaciones.add(pregunta6.indexOfChild(selected)+1);

                    selected = (RadioButton) pregunta7.findViewById(pregunta7.getCheckedRadioButtonId());
                puntuaciones.add(pregunta7.indexOfChild(selected)+1);

                    selected = (RadioButton) pregunta8.findViewById(pregunta8.getCheckedRadioButtonId());
                puntuaciones.add(pregunta8.indexOfChild(selected)+1);

                    for(int i=0;i<8;i++) {
                        params.put("set[" + i + "]",
                                "{\"id_pregunta\":" + (i + 1) + ",\"puntuacion\":" + puntuaciones.get(i) + "}");
                    }
                return params;
            }
        };
        queue.addToQueue(request);
    }

    public void cargarRespuestas(){
        StringRequest request2 = new StringRequest(
                Request.Method.POST,
                Conexion.servidor +"evaluacion/mostrar",
                new Response.Listener<String>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject respuesta = new JSONObject(response);
                            JSONArray result = new JSONArray(respuesta.getJSONArray("result").toString());
                            for(int u=0;u<8;u++){
                                if(result.getJSONObject(u).getInt("id_pregunta")==1){
                                    selected = (RadioButton) pregunta1.getChildAt(result.getJSONObject(u).getInt("puntuacion")-1);
                                    selected.setChecked(true);
                                }else if(result.getJSONObject(u).getInt("id_pregunta")==2){
                                    selected = (RadioButton) pregunta2.getChildAt(result.getJSONObject(u).getInt("puntuacion")-1);
                                    selected.setChecked(true);
                                }else if(result.getJSONObject(u).getInt("id_pregunta")==3){
                                    selected = (RadioButton) pregunta3.getChildAt(result.getJSONObject(u).getInt("puntuacion")-1);
                                    selected.setChecked(true);
                                }else if(result.getJSONObject(u).getInt("id_pregunta")==4){
                                    selected = (RadioButton) pregunta4.getChildAt(result.getJSONObject(u).getInt("puntuacion")-1);
                                    selected.setChecked(true);
                                }else if(result.getJSONObject(u).getInt("id_pregunta")==5){
                                    selected = (RadioButton) pregunta5.getChildAt(result.getJSONObject(u).getInt("puntuacion")-1);
                                    selected.setChecked(true);
                                }else if(result.getJSONObject(u).getInt("id_pregunta")==6){
                                    selected = (RadioButton) pregunta6.getChildAt(result.getJSONObject(u).getInt("puntuacion")-1);
                                    selected.setChecked(true);
                                }else if(result.getJSONObject(u).getInt("id_pregunta")==7){
                                    selected = (RadioButton) pregunta7.getChildAt(result.getJSONObject(u).getInt("puntuacion")-1);
                                    selected.setChecked(true);
                                }else if(result.getJSONObject(u).getInt("id_pregunta")==8){
                                    selected = (RadioButton) pregunta8.getChildAt(result.getJSONObject(u).getInt("puntuacion")-1);
                                    selected.setChecked(true);
                                }
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
                params.put("id_usuario", String.valueOf(datosObtenidos.getId_usuario()));
                params.put("id_curso", String.valueOf(datosObtenidos.getId_curso()));
                return params;
            }
        };
        queue.addToQueue(request2);
    }
    public void eliminarEvaluacion(){
        StringRequest request2 = new StringRequest(
                Request.Method.POST,
                Conexion.servidor +"evaluacion/eliminar",
                new Response.Listener<String>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(String response) {
                        titulo.setText("Evalua el curso");
                        options.setVisibility(View.GONE);
                        evaluarL.setVisibility(View.VISIBLE);
                        pregunta1.clearCheck();
                        pregunta2.clearCheck();
                        pregunta3.clearCheck();
                        pregunta4.clearCheck();
                        pregunta5.clearCheck();
                        pregunta6.clearCheck();
                        pregunta7.clearCheck();
                        pregunta8.clearCheck();
                        comentario.setText("");
                        Toast eliminar = Toast.makeText(getContext(),"Evaluacion eliminada", Toast.LENGTH_SHORT);
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
                params.put("id_usuario", String.valueOf(datosObtenidos.getId_usuario()));
                params.put("id_curso", String.valueOf(datosObtenidos.getId_curso()));
                return params;
            }
        };
        queue.addToQueue(request2);
    }

    public void obtenerComentario(){

        StringRequest request2 = new StringRequest(
                Request.Method.POST,
                Conexion.servidor +"comentario/mostrar",
                new Response.Listener<String>() {
                    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject res = new JSONObject(response);
                            JSONObject result = res.getJSONObject("result");
                            comentario.setText(result.getString("texto"));
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
                params.put("id_usuario", String.valueOf(datosObtenidos.getId_usuario()));
                params.put("id_curso", String.valueOf(datosObtenidos.getId_curso()));
                return params;
            }
        };
        queue.addToQueue(request2);
    }
}