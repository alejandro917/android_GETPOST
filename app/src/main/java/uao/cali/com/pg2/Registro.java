package uao.cali.com.pg2;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

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
    }

    public void doRegistro(View view) {
    }

    public class HttpThread extends AsyncTask<Void, String, String>{

        //este metodo es necesario adicionarlo por override methods
        @Override
        protected String doInBackground(Void... params) {
            //es necesario meterlo en un try catch
            try {
                String response = WebUtilDomi.GETrequest("http://www.google.com");
                return response;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        //este metodo se ejecuta despues de que se recibe la respuesta
        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            salida.setText(s);
        }
    }
}
