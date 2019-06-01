package com.example.informa_tec.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.informa_tec.R;

public class Evaluar extends Fragment {
    private int opcion=1;
    public Evaluar() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if(opcion==0) {
            return inflater.inflate(R.layout.fragment_evaluar, container, false);
        }else if(opcion==1){
            return inflater.inflate(R.layout.fragment_evaluar_options, container, false);
        }else{
            return inflater.inflate(R.layout.fragment_evaluar, container, false);
        }
    }
}