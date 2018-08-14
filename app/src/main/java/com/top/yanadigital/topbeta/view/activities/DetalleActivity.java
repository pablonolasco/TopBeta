package com.top.yanadigital.topbeta.view.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.top.yanadigital.topbeta.R;
import com.top.yanadigital.topbeta.model.vo.Artista;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DetalleActivity extends AppCompatActivity {

    @BindView(R.id.image_foto)
    AppCompatImageView imageFoto;
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.toolbar_layout)
    CollapsingToolbarLayout toolbarLayout;
    @BindView(R.id.app_bar)
    AppBarLayout appBar;
    @BindView(R.id.edNombre)
    TextInputEditText edNombre;
    @BindView(R.id.edApellido)
    TextInputEditText edApellido;
    @BindView(R.id.edfechaNacimiento)
    TextInputEditText edfechaNacimiento;
    @BindView(R.id.edEdad)
    TextInputEditText edEdad;
    @BindView(R.id.edEstatura)
    TextInputEditText edEstatura;
    @BindView(R.id.edOrden)
    TextInputEditText edOrden;
    @BindView(R.id.edLugarNacimiento)
    TextInputEditText edLugarNacimiento;
    @BindView(R.id.edNotas)
    TextInputEditText edNotas;
    @BindView(R.id.contentMain)
    NestedScrollView contentMain;
    @BindView(R.id.fab)
    FloatingActionButton fab;

    private Artista mArtista;
    private Calendar mCalendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        ButterKnife.bind(this);
        configArtista();
        configActionBar();
        configImageView(mArtista.getFotoURL());
        configCalendar();




    }

    private void configImageView(String fotoUrl) {
        if (fotoUrl != null) {
            RequestOptions options = new RequestOptions();
            options.diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop();

            //Descargar img

            Glide.with(this)
                    .load(fotoUrl)
                    .apply(options)
                    .into(imageFoto);
        } else {
            //Si es vacia colocar imagen por defecto
            imageFoto.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_photo_size_select_actual));

        }
        mArtista.setFotoURL(fotoUrl);

    }

    private void configArtista() {
        mArtista= TopActivity.sARTISTA;

        edNombre.setText(mArtista.getNombre());
        edApellido.setText(mArtista.getApellidos());
        edfechaNacimiento.setText(new SimpleDateFormat("dd/MM/yyy",Locale.ROOT)
        .format(mArtista.getFechaNacimiento()));
        edEdad.setText(getEdad(mArtista.getFechaNacimiento()));
        edEstatura.setText(String.valueOf(mArtista.getEstatura()));
        edOrden.setText(String.valueOf(mArtista.getOrden()));
        edLugarNacimiento.setText(mArtista.getLugarNacimiento());
        edNotas.setText(mArtista.getNotas());


    }

    private String getEdad(long fechaNacimiento) {
        long time=Calendar.getInstance().getTimeInMillis()/1000-fechaNacimiento/1000;
        final int years=Math.round(time)/31536000;


        return String.valueOf(years);
    }

    private void configActionBar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            /**
             *Si es diferente de null mostrar la flecha de regreso
             */
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        configTittle();
    }

    private void configTittle() {
        toolbarLayout.setTitle(mArtista.getNombreCompleto());
    }

    private void configCalendar() {
        //Obtiene la fecha del dispositivo
         mCalendar = Calendar.getInstance(Locale.ROOT);
        //Coloca formato en fecha
        edfechaNacimiento.setText(new SimpleDateFormat("dd/MM/yyyy", Locale.ROOT).format(System.currentTimeMillis()));
    }

}
