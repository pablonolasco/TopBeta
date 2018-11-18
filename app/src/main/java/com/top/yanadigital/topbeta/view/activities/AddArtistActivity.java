package com.top.yanadigital.topbeta.view.activities;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatImageView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
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

    private static final int RC_PHOTO_PICKER = 4;
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
    @BindView(R.id.edLugarNacimiento)
    TextInputEditText edLugarNacimiento;

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
     * Inflar menu
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        return super.onCreateOptionsMenu(menu);
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
            case R.id.action_save:
                saveArtist();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveArtist() {
        try {
            mArtista.setNombre(edNombre.getText().toString().trim());
            mArtista.setApellidos(edApellido.getText().toString().trim());
            mArtista.setEstatura(Short.valueOf(edEstatura.getText().toString().trim()));
            mArtista.setLugarNacimiento(edLugarNacimiento.getText().toString().trim());
            mArtista.setNotas(edNotas.getText().toString().trim());
            long response = mArtista.insert();
            if (response > 0) {
                Log.i("DBFLOW", "Insercion correcta");
            }
            /**
             * Agregar a la actividad principal el artista agregado
             if(validarfields()) {
             TopActivity.sARTISTA.setNombre(edNombre.getText().toString().trim());
             TopActivity.sARTISTA.setApellidos(edApellido.getText().toString().trim());
             TopActivity.sARTISTA.setEstatura(Short.valueOf(edEstatura.getText().toString().trim()));
             TopActivity.sARTISTA.setLugarNacimiento(edLugarNacimiento.getText().toString().trim());
             TopActivity.sARTISTA.setNombre(edNotas.getText().toString().trim());
             TopActivity.sARTISTA.setOrden(mArtista.getOrden());
             TopActivity.sARTISTA.setFotoURL(mArtista.getFotoURL());
             //Bandera para indicar que salio bien todo y terminar la actividad
             */

            //  setResult(RESULT_OK);
            finish();
        } catch (NumberFormatException e) {
            Log.i("DBFLOW", "Insercion incorrecta");
            e.printStackTrace();
        } catch (Exception e) {
            Log.i("DBFLOW", "Insercion incorrecta");
            e.printStackTrace();
        }
    }

    private boolean validarfields() {
        boolean isValidFalse = true;

        try {
            if (edEstatura.getText().toString().trim().isEmpty() ||
                    Integer.valueOf(edEstatura.getText().toString().trim()) < getResources().getInteger(R.integer.estatura_min)) {
                edEstatura.setError(getString(R.string.addArtist_error_estaturaMin));
                edEstatura.requestFocus();
                isValidFalse = false;
            }
            if (edApellido.getText().toString().trim().isEmpty()) {
                ;
                edApellido.setError(getString(R.string.addArtist_error_required));
                edApellido.requestFocus();
                isValidFalse = false;
            }

            if (edNombre.getText().toString().trim().isEmpty()) {
                ;
                edNombre.setError(getString(R.string.addArtist_error_required));
                edNombre.requestFocus();
                isValidFalse = false;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return isValidFalse;
    }

    @OnClick(R.id.edfechaNacimiento)
    public void onSetFecha() {
        DialogSelectedFecha dialogSelectedFecha = new DialogSelectedFecha();
        dialogSelectedFecha.setmListener(AddArtistActivity.this);

        Bundle args = new Bundle();
        args.putLong(dialogSelectedFecha.FECHA, mArtista.getFechaNacimiento());
        dialogSelectedFecha.setArguments(args);
        dialogSelectedFecha.show(getSupportFragmentManager(), DialogSelectedFecha.SELECTED_DATE);
    }

    /**
     * Metodo para configurar el calendario
     *
     * @param datePicker
     * @param year
     * @param month
     * @param day
     */
    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, day);

        edfechaNacimiento.setText(new SimpleDateFormat("dd/MM/yyyy", Locale.ROOT).format(mCalendar.getTimeInMillis()));

        mArtista.setFechaNacimiento(mCalendar.getTimeInMillis());
    }

    /**
     * Eventos para agregar, eliminar img desdes galeria o url
     *
     * @param view
     */
    @OnClick({R.id.imageDeleteFoto, R.id.imageFromGallery, R.id.imageFromUrl})
    public void imageView(View view) {
        switch (view.getId()) {
            case R.id.imageDeleteFoto:
                android.app.AlertDialog.Builder builder= new android.app.AlertDialog.Builder(this)
                        .setTitle(R.string.tittle_alert_dialog)
                        .setMessage(String.format(Locale.ROOT,getString(R.string.msg_delete_detalle_dialog),mArtista.getNombre()))
                        .setPositiveButton(R.string.accion_delete_dialog, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                configImageView(null);
                            }
                        })
                        .setNegativeButton(R.string.accion_cancel_dialog,null);
                builder.show();
                break;
            case R.id.imageFromGallery:
                // TODO: 17/11/18 ACTION_GET_CONTENT le permite al usuario que tipo de datos quiere retornar
                Intent intent= new Intent(Intent.ACTION_GET_CONTENT);
                // TODO: 17/11/18 Elejimos que sean de tipo imagen
                intent.setType("image/jpeg");
                // TODO: 17/11/18 EXTRA_LOCAL_ONLY indicamos que solo sean del dispositivo
                intent.putExtra(Intent.EXTRA_LOCAL_ONLY,true);
                startActivityForResult(Intent.createChooser(intent,getString(R.string.detalle_choser_tittle)),RC_PHOTO_PICKER);

                break;
            case R.id.imageFromUrl:
                showAddPhotoGallery();
                break;
        }
    }

    private void showAddPhotoGallery() {
        /*
        Crear alerta desde codigo
        * */
        final EditText edUrlFoto = new EditText(this);
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this)
                .setTitle(R.string.label_dialog_tittle)
                .setPositiveButton(R.string.label_dialog_add, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        configImageView(edUrlFoto.getText().toString().trim());
                    }
                })
                .setNegativeButton(R.string.label_dialog_cancel, null);
        alertDialog.setView(edUrlFoto);
        alertDialog.show();
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

    /**
     * TODO METODO OVWERRIDE QUE SE EJECUTA AL PRESIONAR CASE imageFromGallery
     *
     * @param requestCode
     * @param resultCode
     * @param data
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            if(resultCode == RESULT_OK){
                switch (requestCode){
                    case RC_PHOTO_PICKER:
                        configImageView(data.getDataString());
                        break;
                }
            }
            super.onActivityResult(requestCode, resultCode, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
