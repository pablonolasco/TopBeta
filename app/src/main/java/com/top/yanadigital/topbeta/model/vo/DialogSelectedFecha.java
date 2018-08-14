package com.top.yanadigital.topbeta.model.vo;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;

import java.util.Calendar;
import java.util.Locale;

public class DialogSelectedFecha  extends DialogFragment{
    public static final String FECHA="fecha";
    public static final String SELECTED_DATE="selectDate";
    private DatePickerDialog.OnDateSetListener mListener;

    public void setmListener(DatePickerDialog.OnDateSetListener mListener) {
        this.mListener = mListener;
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Calendar calendar= Calendar.getInstance(Locale.ROOT);
        //Verficar argumentos que pasaron desde la actividad
        Bundle args=this.getArguments();
        if (args != null){
            long fecha=args.getLong(FECHA);
            calendar.setTimeInMillis(fecha);
        }
        int anio=calendar.get(Calendar.YEAR);
        int mes=calendar.get(Calendar.MONTH);
        int dia=calendar.get(Calendar.DAY_OF_MONTH);
        return new DatePickerDialog(getActivity(),mListener,anio,mes,dia);
    }
}
