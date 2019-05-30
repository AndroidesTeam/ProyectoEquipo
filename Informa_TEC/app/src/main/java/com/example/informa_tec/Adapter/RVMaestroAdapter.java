package com.example.informa_tec.Adapter;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.informa_tec.Modelo.ModeloMaestro;
import com.example.informa_tec.R;
import com.example.informa_tec.Vistas;


import java.util.List;

public class RVMaestroAdapter extends RecyclerView.Adapter<RVMaestroAdapter.RVMessageAdapterViewHolder> {
    private Context context;
    private List<ModeloMaestro> maestros;

    public RVMaestroAdapter(Context context, List<ModeloMaestro> maestros) {
        this.context = context;
        this.maestros = maestros;
    }

    @NonNull
    @Override
    public RVMessageAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_maestros, viewGroup, false);
        return new RVMessageAdapterViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVMessageAdapterViewHolder rvMessageAdapterViewHolder, int i) {
        final ModeloMaestro maestro = maestros.get(i);
        rvMessageAdapterViewHolder.textViewMaestro.setText(maestro.getNombre()+" "+maestro.getApellidoP()+" "+ maestro.getGetApellidoM());
        rvMessageAdapterViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context, Vistas.class);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        try{
            return maestros.size();
        }catch (Exception e){
            return 0;
        }
    }

    public class RVMessageAdapterViewHolder extends RecyclerView.ViewHolder {
        TextView textViewMaestro;

        public RVMessageAdapterViewHolder(View view) {
            super(view);
            textViewMaestro = view.findViewById(R.id.tv_maestro);
        }
    }
}