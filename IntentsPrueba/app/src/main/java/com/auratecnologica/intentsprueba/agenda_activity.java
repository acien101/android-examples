package com.auratecnologica.intentsprueba;

import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
/*
Activity de la Agenda.
 */
public class agenda_activity extends AppCompatActivity {

    Dialog dialog;

    //Adaptador que utilizaremos para cargar el ListView
    private MiAdapter adaptador;
    //Campo de Texto con el nombre
    private EditText edtNombre;
    //Campo de Texto con el telefono.
    private EditText edttelefono;

    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agenda_activity);

        dialog = new Dialog(this);
        LayoutInflater inflater = (LayoutInflater)this.getSystemService(LAYOUT_INFLATER_SERVICE);
        View layout = inflater.inflate(R.layout.layoutinflater, (ViewGroup)findViewById(R.id.dialogSketch));
        dialog.setContentView(layout);

        //Obtenemos la lista y le asignamos el adaptador.
        ListView lista =(ListView)findViewById(R.id.listatelefonos);
        adaptador = new MiAdapter(this);
        lista.setAdapter(adaptador);



        edtNombre =(EditText)layout.findViewById(R.id.edtnombre);
        edttelefono =(EditText)layout.findViewById(R.id.edttelef);
        //Boton enviar que añade un nuevo telefono a la lista.
        Button btnEnviar=(Button)layout.findViewById(R.id.btnadd);
        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = edtNombre.getText().toString();
                String telefono = edttelefono.getText().toString();
                Persona p = new Persona();

                p.setNombre(nombre);
                p.setTelefono(telefono);
                adaptador.addPersona(p);

                dialog.dismiss();
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        MenuItem añadir = menu.findItem(R.id.añadir);
        añadir.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {

                dialog.show();
                return true;

            }
        });
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();



        return super.onOptionsItemSelected(item);
    }
}
