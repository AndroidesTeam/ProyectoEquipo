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

import com.example.informa_tec.Controller.ControladorMaestro;
import com.example.informa_tec.Modelo.Datos;
import com.example.informa_tec.R;
import com.example.informa_tec.Modelo.ModeloMateria;

import java.util.List;

public class RVMateriaAdapter extends RecyclerView.Adapter<RVMateriaAdapter.RVMessageAdapterViewHolder> {
    private Context context;
    private List<ModeloMateria> materias;
    private Datos datosObtenidos;

    public RVMateriaAdapter(Context context, List<ModeloMateria> materias,Datos datosObtenidos) {
        this.context = context;
        this.materias = materias;
        this.datosObtenidos=datosObtenidos;
    }

    @NonNull
    @Override
    public RVMessageAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_materias, viewGroup, false);
        return new RVMessageAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVMessageAdapterViewHolder rvMessageAdapterViewHolder, int i) {
        final ModeloMateria materia = materias.get(i);
        rvMessageAdapterViewHolder.textViewMateria.setText(materia.getNombreMateria());
        rvMessageAdapterViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                datosObtenidos.setMateria(materia.getId_materia());
                Intent intent = new Intent(context, ControladorMaestro.class);
                Bundle bundle= new Bundle();
                bundle.putSerializable("datos", datosObtenidos);
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {

        try{
            return materias.size();
        }catch (Exception e){
            return 0;
        }
    }

    public class RVMessageAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView textViewMateria;

        public RVMessageAdapterViewHolder(View view) {
            super(view);
            textViewMateria = view.findViewById(R.id.tv_materia);
        }
    }
}