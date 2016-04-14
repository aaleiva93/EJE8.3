package es.samsung.techinstitute.eje83;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Alumno on 19/11/2015.
 */
public class NuevaActividad extends Activity {

    private TextView resultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nueva_actividad);

        resultado = (TextView) findViewById(R.id.resultado);

        Intent intent= getIntent();
        resultado.setText("Nombre: "+intent.getStringExtra(MainActivity.CLAVE_NOMBRE)+"\n"+"Apellidos: "+intent.getStringExtra(MainActivity.CLAVE_APELLIDOS)+"\n"+"Sexo: "+intent.getStringExtra(MainActivity.CLAVE_SEXO)+"\n"+intent.getStringExtra(MainActivity.CLAVE_FUMADOR)+"\n"+intent.getStringExtra(MainActivity.CLAVE_COCHE));
    }
}
