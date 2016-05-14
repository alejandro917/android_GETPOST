package uao.cali.com.pg2;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import util.WebUtilDomi;

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
        salida.setMovementMethod(new ScrollingMovementMethod());
    }

    public void doRegistro(View view) {
        HttpThread hilo = new HttpThread();
        hilo.execute(ETnombre.getText().toString(),ETusuario.getText().toString(),ETpassword.getText().toString());
    }

    public class HttpThread extends AsyncTask<String, String, String>{

        //este metodo es necesario adicionarlo por override methods
        @Override
        protected String doInBackground(String... params) {
            //es necesario meterlo en un try catch
            try {
                Uri.Builder sobre = new Uri.Builder();
                //se adicionan los parametros por POST con esta linea
                sobre.appendQueryParameter("nombre",params[0]).appendQueryParameter("usuario",params[1]).appendQueryParameter("password",params[2]);
                //se caputara la respuesta de la API
                String response = WebUtilDomi.POSTrequest("http://192.168.173.1:8080/WebService/webresources/service/crear_usuario", sobre);
                return response;
            } catch (IOException e) {
               return e.getMessage();
            }

        }

        //este metodo se ejecuta despues de que se recibe la respuesta
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            salida.setText(s);

            if (s.equals("SUCCESS")){
                Intent i = new Intent(getApplicationContext(),Login.class);
                startActivity(i);
            }else{
                Toast.makeText(getApplicationContext(),"Usuario existente",Toast.LENGTH_SHORT).show();
            }
        }
    }
}
