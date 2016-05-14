package uao.cali.com.pg2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


/**
 * Created by sala03 on 14/05/2016.
 */
public class Login extends Activity{
    EditText ETusuario,ETpassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        ETusuario = (EditText) findViewById(R.id.log_usuario);
        ETpassword = (EditText) findViewById(R.id.log_pass);
    }

    public void registrarse(View view) {
        Intent i = new Intent(getApplicationContext(),Registro.class);
        startActivity(i);
    }

    public void doLog(View view) {
    }
}
