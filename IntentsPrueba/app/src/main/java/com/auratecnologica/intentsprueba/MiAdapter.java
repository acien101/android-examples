package com.auratecnologica.intentsprueba;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by victor on 9/1/16.
 * Adaptador que utilizaremos para cargar cada fila del ListView.
 */
public class MiAdapter extends BaseAdapter{
    //Lista con las personas
    private List<Persona> lista;
    //Contexto de la aplicación.
    private Context contexto;

    public MiAdapter(Context context){
        lista= new ArrayList<Persona>();
        this.contexto=context;
    }
    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int position) {
        return lista.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0l;
    }

    /*
     * Este metodo permite cargar para cada fila un Layout con la informacion del nombre
     *  y del telefono.
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //Si la vista viene a null cargamos una nueva(primera vez)
        if(convertView==null){
            //Usando el inflater cargamos una nueva vista usando
            //el layout nuevo.
            convertView=View.inflate(contexto,R.layout.linea,null);
        }
        //Se obtienen los datos de la persona y se asignan a los Textview.
        TextView txtnombre =(TextView)convertView.findViewById(R.id.txtNombre);
        TextView txttelefono = (TextView)convertView.findViewById(R.id.txttelefono);
        Persona p = (Persona)getItem(position);
        txtnombre.setText(p.getNombre());
        txttelefono.setText(p.getTelefono());
        return convertView;
    }

    //Metodo para añadir una nueva persona.
    public void addPersona(Persona p){

        this.lista.add(p);
        //Metodo obligatorio al que hay que llamar para refrescar la lista.
        notifyDataSetChanged();
    }
}
