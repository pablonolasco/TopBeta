package com.top.yanadigital.topbeta.view.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.DialogInterface;
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
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.raizlabs.android.dbflow.sql.language.SQLite;
import com.top.yanadigital.topbeta.R;
import com.top.yanadigital.topbeta.model.vo.Artista;
import com.top.yanadigital.topbeta.model.vo.Artista_Table;
import com.top.yanadigital.topbeta.model.vo.DialogSelectedFecha;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetalleActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    // TODO: 17/11/18 Numero que queramos
    private static final int RC_PHOTO_PICKER =4 ;
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
    @BindView(R.id.imageDeleteFoto)
    AppCompatImageView imageDeleteFoto;
    @BindView(R.id.imageFromGallery)
    AppCompatImageView imageFromGallery;
    @BindView(R.id.imageFromUrl)
    AppCompatImageView imageFromUrl;

    private Artista mArtista;
    private Calendar mCalendar;
    private MenuItem mMenuItem;
    private boolean mIsEdit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);
        ButterKnife.bind(this);
        //TODO obtiene el elemento intent enviado por la vista top activity
        configArtista(getIntent());
        // TODO: 17/11/18 metodo que configura el action bar
        configActionBar();
        // TODO: 17/11/18 metodo que obtiene la imagen
        configImageView(mArtista.getFotoURL());
        // TODO: 17/11/18 metodo que configura el calendario del vista
        configCalendar();


    }

    /**
     * TODO METODO QUE DESCARGA LA IMAGEN DE INTERNET
     *
     * @param fotoUrl
     */
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
            //TODO Si es vacia colocar imagen por defecto
            imageFoto.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_photo_size_select_actual));

        }
        mArtista.setFotoURL(fotoUrl);

    }

    /**
     * TODO METODO QUE SE UTILIZA PARA INFLAR EL MENU
     *
     * @param menu
     * @return menu de save
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_save, menu);
        mMenuItem = menu.findItem(R.id.action_save);
        mMenuItem.setVisible(false);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * TODO METODO PARA SELECCIONAR EL MENU SAVE Y GUARDAR LA EDICION
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_save:
                saveOrEdit();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * TODO METODO QUE RECIBE EL PARAMENTRO ID POR MEDIO DEL INTENT
     *
     * @param intent
     */
    private void configArtista(Intent intent) {
        //TODO obtiene el id y lo envia al metodo para buscar el registro
        getArtist(intent.getLongExtra(Artista.ID, 0));
        edNombre.setText(mArtista.getNombre());
        edApellido.setText(mArtista.getApellidos());
        edfechaNacimiento.setText(new SimpleDateFormat("dd/MM/yyy", Locale.ROOT)
                .format(mArtista.getFechaNacimiento()));
        edEdad.setText(getEdad(mArtista.getFechaNacimiento()));
        edEstatura.setText(String.valueOf(mArtista.getEstatura()));
        edOrden.setText(String.valueOf(mArtista.getOrden()));
        edLugarNacimiento.setText(mArtista.getLugarNacimiento());
        edNotas.setText(mArtista.getNotas());
    }

    /**
     * TODO METODO QUE OBTIENE EL ARTISTA DE LA TABLA SQLITE
     *
     * @param id_artist valor que viene desde top activity
     */
    private void getArtist(long id_artist) {
        try {
            mArtista = SQLite
                    .select()
                    .from(Artista.class)
                    .where(Artista_Table.id.is(id_artist))
                    .querySingle();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getEdad(long fechaNacimiento) {
        long time = Calendar.getInstance().getTimeInMillis() / 1000 - fechaNacimiento / 1000;
        final int years = Math.round(time) / 31536000;


        return String.valueOf(years);
    }

    /**
     * TODO METODO QUE CONFIGURA EL ACTION BAR DEL ACTIVITY
     */
    private void configActionBar() {
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            //TODO Si es diferente de null mostrar la flecha de regreso
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        // TODO: 17/11/18 manda a llamar el titulo que llevara el action bar
        configTittle();
    }

    /**
     * TODO METODO QUE CONFIGURA EL TITULO DEL ACTIONBAR
     */
    private void configTittle() {
        toolbarLayout.setTitle(mArtista.getNombreCompleto());
    }

    /**
     * TODO METODO QUE CONFIGURA EL CALENDARIO
     */
    private void configCalendar() {
        //Obtiene la fecha del dispositivo
        mCalendar = Calendar.getInstance(Locale.ROOT);
        //Coloca formato en fecha
        edfechaNacimiento.setText(new SimpleDateFormat("dd/MM/yyyy", Locale.ROOT).format(System.currentTimeMillis()));
    }

    /**
     * TODO METODO QUE GUARDA EL ARTISTA MODIFICADO EN LA TABLA
     */
    @OnClick(R.id.fab)
    public void saveOrEdit() {
        try {
            if (mIsEdit) {
                // TODO: 17/11/18 salvar datos
                fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_account_edit));
                // TODO: 17/11/18 metodo que deshabilita elementos de la ui
                enableUIElements(false);
                mIsEdit = false;
                mArtista.setNombre(edNombre.getText().toString().trim());
                mArtista.setApellidos(edApellido.getText().toString().trim());
                mArtista.setEstatura(Short.valueOf(edEstatura.getText().toString().trim()));
                mArtista.setLugarNacimiento(edLugarNacimiento.getText().toString().trim());
                mArtista.setNotas(edNotas.getText().toString().trim());
                try {
                    boolean response = mArtista.update();
                    if (response) {
                        configTittle();
                        showMessage(R.string.detalle_message_update_succes, true);

                        Log.i("DBFLOW", "Edicion Correcta");
                    }
                } catch (Exception e) {
                    showMessage(R.string.detalle_message_update_error, false);

                    Log.i("DBFLOW", "Edicion Incorrecta");
                    e.printStackTrace();
                }


            } else {
                mIsEdit = true;
                fab.setImageDrawable(ContextCompat.getDrawable(this, R.drawable.ic_account_check));
                enableUIElements(true);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * TODO METODO QUE VALIDA CAMPOS NO VACIOS
     *
     * @return
     */
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


    private void enableUIElements(boolean enable) {
        edNombre.setEnabled(enable);
        edApellido.setEnabled(enable);
        edfechaNacimiento.setEnabled(enable);
        edEstatura.setEnabled(enable);
        edLugarNacimiento.setEnabled(enable);
        edNotas.setEnabled(enable);
        mMenuItem.setVisible(enable);
        appBar.setExpanded(!enable);
        contentMain.setNestedScrollingEnabled(!enable);
    }

    @Override
    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
        mCalendar.setTimeInMillis(System.currentTimeMillis());
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, day);

        edfechaNacimiento.setText(new SimpleDateFormat("dd/MM/yyyy", Locale.ROOT).format(mCalendar.getTimeInMillis()));

        mArtista.setFechaNacimiento(mCalendar.getTimeInMillis());
        edEdad.setText(getEdad(mCalendar.getTimeInMillis()));
    }

    @OnClick(R.id.edfechaNacimiento)
    public void onSetFecha() {
        DialogSelectedFecha dialogSelectedFecha = new DialogSelectedFecha();
        dialogSelectedFecha.setmListener(DetalleActivity.this);

        Bundle args = new Bundle();
        args.putLong(dialogSelectedFecha.FECHA, mArtista.getFechaNacimiento());
        dialogSelectedFecha.setArguments(args);
        dialogSelectedFecha.show(getSupportFragmentManager(), DialogSelectedFecha.SELECTED_DATE);
    }

    private void showMessage(int resource, boolean type) {
        if (type) {
            Snackbar.make(contentMain, resource, Snackbar.LENGTH_SHORT).show();
        } else {
            Snackbar.make(contentMain, resource, Snackbar.LENGTH_SHORT).show();
        }

    }

    /**
     * TODO METODO PARA SELECCIONAR LA IMAGEN
     * @param view
     */
    @OnClick({R.id.imageDeleteFoto, R.id.imageFromGallery, R.id.imageFromUrl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.imageDeleteFoto:
                AlertDialog.Builder builder= new AlertDialog.Builder(this)
                        .setTitle(R.string.tittle_alert_dialog)
                        .setMessage(String.format(Locale.ROOT,getString(R.string.msg_delete_detalle_dialog),mArtista.getNombre()))
                        .setPositiveButton(R.string.accion_delete_dialog, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                savePhotoUrl(null);
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
                break;
        }
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
                            savePhotoUrl(data.getDataString());
                        break;
                }
            }
            super.onActivityResult(requestCode, resultCode, data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * TODO METODO QUE ALMACENA LA IMAGEN DESDE LA GALERIA
     * @param dataString
     */
    private void savePhotoUrl(String dataString) {
        try {
            mArtista.setFotoURL(dataString);
            mArtista.update();
            configImageView(dataString);
            showMessage(R.string.detalle_message_update_succes,true);
        } catch (Exception e) {
            e.printStackTrace();
            showMessage(R.string.detalle_message_update_error,false);
        }
    }
}
