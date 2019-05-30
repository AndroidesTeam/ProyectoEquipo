package com.example.informa_tec.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import com.example.informa_tec.R;
import com.example.informa_tec.Modelo.ModeloComentarios;

public class RVComentariosAdapter extends RecyclerView.Adapter<RVComentariosAdapter.RVMessageAdapterViewHolder> {
    private Context context;
    private List<ModeloComentarios> comentarios;

    public RVComentariosAdapter(Context context, List<ModeloComentarios> comentarios) {
        this.context = context;
        this.comentarios = comentarios;
    }

    @NonNull
    @Override
    public RVMessageAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_comentarios, viewGroup, false);
        return new RVMessageAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVMessageAdapterViewHolder rvMessageAdapterViewHolder, int i) {
        final ModeloComentarios comentario = comentarios.get(i);
        rvMessageAdapterViewHolder.textViewFecha.setText(comentario.getFecha());
        rvMessageAdapterViewHolder.textViewComentario.setText(comentario.getComentario());
    }

    @Override
    public int getItemCount() {
        try{
            return comentarios.size();
        }catch (Exception e){
            return 0;
        }

    }

    public class RVMessageAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView textViewFecha;
        TextView textViewComentario;

        public RVMessageAdapterViewHolder(View view) {
            super(view);
            textViewFecha = view.findViewById(R.id.tv_fecha);
            textViewComentario = view.findViewById(R.id.tv_comentario);
        }
    }
}
