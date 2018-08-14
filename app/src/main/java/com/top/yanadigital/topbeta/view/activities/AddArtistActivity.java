package com.top.yanadigital.topbeta.view.activities;

import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.view.MenuItem;
import android.widget.DatePicker;

import com.top.yanadigital.topbeta.R;
import com.top.yanadigital.topbeta.model.vo.Artista;
import com.top.yanadigital.topbeta.model.vo.DialogSelectedFecha;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class AddArtistActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {

    @BindView(R.id.image_foto)
    AppCompatImageView imageFoto;
    @BindView(R.id.edNombre)
    TextInputEditText edNombre;
    @BindView(R.id.edApellido)
    TextInputEditText edApellido;
    @BindView(R.id.edfechaNacimiento)
    TextInputEditText edfechaNacimiento;
    @BindView(R.id.edEstatura)
    TextInputEditText edEstatura;
    @BindView(R.id.edNotas)
    TextInputEditText edNotas;

    /**
     * Comenzar con una m si no son públicos ni estáticos.
     * Comenzar con una s si son estáticos.
     * En caso de no aplicar en los anteriores, comenzar solo en minúsculas.
     * Constantes publicas, estáticas deben ser todas mayúsculas y separadas con guion bajo.
     */
    private Artista mArtista;
    private Calendar mCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_artist);
        ButterKnife.bind(this);
        /**
         * Recibir parametro del intent
         *
         */
        Intent intent = getIntent();

        configActionBar();
        configArtista(getIntent());
        configCalendar();
    }

    private void configActionBar() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            /**
             *Si es diferente de null mostrar la flecha de regreso
             */
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void configArtista(Intent intent) {
        mArtista = new Artista();
        mArtista.setFechaNacimiento(System.currentTimeMillis());
        mArtista.setOrden(intent.getIntExtra(Artista.ORDES, 0));
    }

    private void configCalendar() {
        //Obtiene la fecha del dispositivo
        mCalendar = Calendar.getInstance(Locale.ROOT);
        //Coloca formato en fecha
        edfechaNacimiento.setText(new SimpleDateFormat("dd/MM/yyyy", Locale.ROOT).format(System.currentTimeMillis()));
    }

    @Override
    public void onBackPressed() {
        setResult(RESULT_OK);
        super.onBackPressed();
    }

    /**
     * Metodo que regresa al home y cierra la actividad
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @OnClick(R.id.edfechaNacimiento)
    public void onSetFecha() {
        DialogSelectedFecha dialogSelectedFecha= new DialogSelectedFecha();
        dialogSelectedFecha.setmListener(AddArtistActivity.this);

        Bundle args= new Bundle();
        args.putLong(dialogSelectedFecha.FECHA,mArtista.getFechaNacimiento());
        dialogSelectedFecha.setArguments(args);
        dialogSelectedFecha.show(getSupportFragmentManager(),DialogSelectedFecha.SELECTED_DATE);
    }

    /**
     * Metodo para configurar el calendario
     * @param datePicker
     * @param year
     * @param month
     * @param day
     */
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
            mCalendar.setTimeInMillis(System.currentTimeMillis());
            mCalendar.set(Calendar.YEAR,year);
            mCalendar.set(Calendar.MONTH,month);
            mCalendar.set(Calendar.DAY_OF_MONTH,day);

            edfechaNacimiento.setText(new SimpleDateFormat("dd/MM/yyyy",Locale.ROOT).format(mCalendar.getTimeInMillis()));

            mArtista.setFechaNacimiento(mCalendar.getTimeInMillis());
    }
}
