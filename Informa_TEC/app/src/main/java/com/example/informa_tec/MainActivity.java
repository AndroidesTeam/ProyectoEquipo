package com.example.informa_tec;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.informa_tec.Servicio.Queue;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.Map;

import com.example.informa_tec.Controller.ControladorSemestre;

public class MainActivity extends AppCompatActivity {

    private EditText usuario, clave;
    private Button btn_login;
    private Queue queue;
    private String usuarioMandar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue =Queue.getInstance(this);
        usuario = (EditText) findViewById(R.id.usuario);
        clave = (EditText) findViewById(R.id.clave);
        btn_login = (Button) findViewById(R.id.login);

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login();
            }
        });
    }

    public void login() {
        StringRequest request = new StringRequest(
                Request.Method.POST,
                "http://192.168.1.104:8000/api/auth/login",
                new Response.Listener<String>() {
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
                usuarioMandar=String.valueOf(usuario.getText());
                params.put("email", usuarioMandar);
                params.put("password", String.valueOf(clave.getText()));
                return params;
            }
        };
        queue.addToQueue(request);
    }

    public void responseHandler(String res) {
        //Toast.makeText(this, res, Toast.LENGTH_SHORT).show();
        try {
            JSONObject response = new JSONObject(res);
            boolean r = (boolean) response.getBoolean("success");
            if (r) {
                Intent enviar=new Intent(getApplicationContext(),ControladorSemestre.class);
                enviar.putExtra("usuario",usuarioMandar);
                startActivity(enviar);
            }
        } catch (JSONException e1) {
            e1.printStackTrace();
        }
    }

    public void errorHandler(VolleyError error) {
        Toast.makeText(this, "Tu usuario o contraseña no coinciden", Toast.LENGTH_SHORT).show();
    }
}
