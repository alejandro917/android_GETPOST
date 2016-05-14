package uao.cali.com.pg2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import util.WebUtilDomi;


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
        HttpThread hilo = new HttpThread();
        hilo.execute(ETusuario.getText().toString(),ETpassword.getText().toString());
    }

    //creo un nuevo hilo en segundo plano
    public class HttpThread extends AsyncTask<String, String, String>{
        @Override
        protected String doInBackground(String... params) {
            //sobre donde se meten los parametros post
            Uri.Builder sobre = new Uri.Builder();
            //se adicionan las variables al sobre
            sobre.appendQueryParameter("usuario",params[0]).appendQueryParameter("password",params[1]);
            //se hace la peticion y se envian las variables con post
            try {
                String respuesta = WebUtilDomi.POSTrequest("http://192.168.173.1:8080/WebService/webresources/service/getuser", sobre);
                return respuesta;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
        }
    }
}
