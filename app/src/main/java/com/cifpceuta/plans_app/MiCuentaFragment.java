package com.cifpceuta.plans_app;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MiCuentaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MiCuentaFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private String nombre,email,turno,grupo;


    public MiCuentaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment MiCuentaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MiCuentaFragment newInstance(String nombre, String email,String turno, String grupo) {
        MiCuentaFragment fragment = new MiCuentaFragment();
        Bundle args = new Bundle();
        args.putString("nombre", nombre);
        args.putString("email", email);
        args.putString("turno",turno);
        args.putString("grupo",grupo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            nombre = getArguments().getString("nombre");
            email = getArguments().getString("email");
            grupo = getArguments().getString("grupo");
            turno = getArguments().getString("turno");

        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        TextView tvNombre,tvCorreo,tvTurno,tvGrupo;
        View rootView = inflater.inflate(R.layout.fragment_mi_cuenta, container, false);

        // Obtener el correo electrónico de los argumentos
        if (getArguments() != null) {
            nombre = getArguments().getString("nombre");
            email = getArguments().getString("email");
            grupo = getArguments().getString("grupo");
            turno = getArguments().getString("turno");        }

        // Vincular el correo electrónico al TextView en el layout
        tvNombre = rootView.findViewById(R.id.tvNombreMiCuenta);
        tvCorreo = rootView.findViewById(R.id.tvEmailMiCuenta);
        tvTurno = rootView.findViewById(R.id.tvTurnoMiCuenta);
        tvGrupo = rootView.findViewById(R.id.tvGrupoMiCuenta);

        tvNombre.setText(nombre);
        tvCorreo.setText(email);
        tvTurno.setText(turno);
        tvGrupo.setText(grupo);

        // Inflate the layout for this fragment
        return rootView;
    }
}