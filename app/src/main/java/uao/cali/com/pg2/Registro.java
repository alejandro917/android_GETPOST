package uao.cali.com.pg2;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by sala03 on 14/05/2016.
 */
public class Registro extends Activity{
    EditText ETnombre, ETusuario, ETpassword;
    TextView salida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        ETnombre = (EditText) findViewById(R.id.reg_nombre);
        ETusuario = (EditText) findViewById(R.id.reg_usuario);
        ETpassword = (EditText) findViewById(R.id.reg_password);

        salida = (TextView) findViewById(R.id.salida);
    }

    public void doRegistro(View view) {
    }
}
