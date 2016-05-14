package uao.cali.com.pg2;

import android.app.Activity;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

import util.WebUtilDomi;

/**
 * Created by sala03 on 14/05/2016.
 */
public class Contenido extends Activity {
    TextView salida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contenido);

        salida = (TextView) findViewById(R.id.salida2);
    }

    public void pedirUsuarios(View view) {
        //el GET no requere variables en el sobre
        PedirUsuarios hilo = new PedirUsuarios();
        hilo.execute();
    }

    public void pedirNoticias(View view) {
        //traer las credenciales de las sharedpreferences
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        String usuario = prefs.getString("usuario", "NO_USER");
        String password = prefs.getString("password","NO_USER");
        //se lanza el hilo para consumir los datos
        ConsumirWebService hilo = new ConsumirWebService();
        hilo.execute(usuario, password);
    }

    public void cerrarSesion(View view) {
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        prefs.edit().clear().commit();
        //para borrar una preferencia en particular
        //prefs.edit().remove("password");
    }

    public class PedirUsuarios extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            try {
                String respuesta = WebUtilDomi.GETrequest("http://192.168.173.1:8080/WebService/webresources/service/getallusers");
                return respuesta;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            salida.setText(s);
        }
    }

    public class ConsumirWebService extends AsyncTask<String, String, String> {
        @Override
        protected String doInBackground(String... params) {
            Uri.Builder sobre = new Uri.Builder();
            //se adicionan las variables al sobre
            sobre.appendQueryParameter("usuario", params[0]).appendQueryParameter("password", params[1]);
            //se hace la peticion y se envian las variables con post
            try {
                String respuesta = WebUtilDomi.POSTrequest("http://192.168.173.1:8080/WebService/webresources/service/getallnoticas", sobre);
                return respuesta;
            } catch (IOException e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            salida.setText(s);

        }
    }

}
