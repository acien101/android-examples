package com.auratecnologica.intentsprueba;

import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Activity Principal
 */
public class MainActivity extends AppCompatActivity {

    EditText edtTexto;
    TextView txtRespuesta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);//Asignamos al activity nuestro XML.

        //Para poder obtener cada boton de nuestro XML debemos obtener a traves
        // del identificador y usando la funcion findViewById.
        Button btnactivity=(Button)findViewById(R.id.btnactivity);
        Button btntwitter=(Button)findViewById(R.id.btntwitter);
        Button btnurl=(Button)findViewById(R.id.btnurl);

        edtTexto = (EditText)findViewById(R.id.edtTexto);
        Button btnEnviar =(Button)findViewById(R.id.btnenviar);
        txtRespuesta =(TextView)findViewById(R.id.txtrespuesta);

        btnEnviar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String texto = edtTexto.getText().toString();
                txtRespuesta.setText("Hola "+texto);
            }
        });


        //Creamos nuestro Objeto Listener.
        MiListener listener = new MiListener();
        //Establecemos que cada boton use dicho listener.
        btnactivity.setOnClickListener(listener);
        btntwitter.setOnClickListener(listener);
        btnurl.setOnClickListener(listener);


    }

    /*
     * Clase Listener que sera la encargada de manejar las acciones a realizar cuando
     *  se haga click en un boton.
     */
    private class MiListener implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            if(v.getId()== R.id.btnactivity){
                //Al pulsar el boton de abrir activity se va a la
                // segunda activity.
                Intent intentactivity = new Intent(MainActivity.this,agenda_activity.class);
                startActivity(intentactivity);
            }else{
                if(v.getId()==R.id.btntwitter){
                    try{
                        //Intent Explicito. LLamada al telefono
                        Intent intent = new Intent(Intent.ACTION_DIAL);

                        startActivity(intent);
                    }catch(ActivityNotFoundException e){
                        Log.e("ERROR", "Error; la aplicación no esta instalada");
                    }
                }else{
                    //Abrir una URL.
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(Uri.parse("http://auratecnologica.com"));
                    startActivity(intent);
                }
            }
        }
    }
}
