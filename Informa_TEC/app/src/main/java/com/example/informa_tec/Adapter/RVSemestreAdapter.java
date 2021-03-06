package com.example.informa_tec.Adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.informa_tec.Controller.ControladorMateria;
import com.example.informa_tec.Modelo.Datos;
import com.example.informa_tec.R;
import com.example.informa_tec.Modelo.ModeloSemestre;

import java.util.List;

public class RVSemestreAdapter extends RecyclerView.Adapter<RVSemestreAdapter.RVMessageAdapterViewHolder> {
    private Context context;
    private List<ModeloSemestre> semestres;
    private String usr;
    private Datos datosObtenidos;

    public RVSemestreAdapter(Context context, List<ModeloSemestre> semestres, Datos datosObtenidos) {
        this.context = context;
        this.semestres = semestres;
        this.datosObtenidos = datosObtenidos;
    }

    @NonNull
    @Override
    public RVMessageAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_semestres, viewGroup, false);

        return new RVMessageAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVMessageAdapterViewHolder rvMessageAdapterViewHolder, int i) {
        final ModeloSemestre semestre = semestres.get(i);
        rvMessageAdapterViewHolder.textViewSemestre.setText(semestre.getSemestre());
        rvMessageAdapterViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                datosObtenidos.setSemestre(semestre.getSemestre());
                Intent intent = new Intent(context, ControladorMateria.class);
                Bundle bundle= new Bundle();
                bundle.putSerializable("datos", datosObtenidos);
                intent.putExtras(bundle);
                context.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {

        try {
            return semestres.size();
        } catch (Exception e) {
            return 0;
        }
    }

    public class RVMessageAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView textViewSemestre;

        public RVMessageAdapterViewHolder(View view) {
            super(view);
            textViewSemestre = view.findViewById(R.id.tv_semestre);
        }
    }
}