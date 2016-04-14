package es.samsung.techinstitute.eje83;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Shader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    SharedPreferences preferencias;
    public static final String CLAVE_NOMBRE = "Nombre";
    public static final String CLAVE_APELLIDOS = "Apellidos";
    public static final String CLAVE_SEXO = "Sexo";
    public static final String CLAVE_FUMADOR = "Fumador";
    public static final String CLAVE_COCHE = "Coche";
    private EditText nombre;
    private EditText apellidos;
    private RadioButton mujer;
    private Button aceptar;
    private Button cancelar;
    private CheckBox fumador;
    private Switch coche;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        preferencias = getSharedPreferences("mispreferencias", MODE_PRIVATE);

        nombre = (EditText)findViewById(R.id.nombre);
        apellidos= (EditText)findViewById(R.id.apellido);
        mujer= (RadioButton)findViewById(R.id.mujer);
        aceptar= (Button)findViewById(R.id.buttonAceptar);
        cancelar= (Button)findViewById(R.id.buttonCancelar);
        fumador= (CheckBox)findViewById(R.id.fumador);
        coche=(Switch)findViewById(R.id.coche);

        nombre.setText(preferencias.getString("nombre",""));
        apellidos.setText(preferencias.getString("apellidos",""));
        mujer.setChecked(preferencias.getBoolean("sexo", true));
        fumador.setChecked(preferencias.getBoolean("fumador",false));
        coche.setChecked(preferencias.getBoolean("coche",false));

        mujer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MainActivity.this, isChecked?"Mujer":"Hombre", Toast.LENGTH_LONG).show();
            }
        });

        fumador.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MainActivity.this, isChecked ? "Fumador" : "No Fumador", Toast.LENGTH_LONG).show();
            }
        });

        coche.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                Toast.makeText(MainActivity.this, isChecked ? "Tiene Coche" : "No tiene Coche", Toast.LENGTH_LONG).show();
            }
        });

        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombrePersona = nombre.getText().toString();
                String apellidosPersona = apellidos.getText().toString();
                String sexo = mujer.isChecked() ? "Mujer" : "Hombre";
                String noFumador = fumador.isChecked() ? "Fumador" : "No Fumador";
                String tieneCoche = coche.isChecked() ? "Tiene Coche" : "No tiene Coche";

                Intent intent = new Intent(MainActivity.this, NuevaActividad.class);
                intent.putExtra(CLAVE_NOMBRE, nombrePersona);
                intent.putExtra(CLAVE_APELLIDOS, apellidosPersona);
                intent.putExtra(CLAVE_SEXO, sexo);
                intent.putExtra(CLAVE_FUMADOR, noFumador);
                intent.putExtra(CLAVE_COCHE, tieneCoche);


                startActivity(intent);
            }
        });

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }

    @Override
    protected void onPause(){
        super.onPause();
        preferencias = getSharedPreferences("mispreferencias", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferencias.edit();
        editor.putString("nombre", nombre.getText().toString());
        editor.putString("apellidos", apellidos.getText().toString());
        editor.putBoolean("sexo", mujer.isChecked());
        editor.putBoolean("fumador", fumador.isChecked());
        editor.putBoolean("coche", coche.isChecked());
        editor.commit();
    }

}
