package uao.cali.com.pg2;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //runnable es como un setTimeout de javascript
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                //llamamos a las shares preferences
                SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
                String usuarion = pref.getString("usuario","NO_USER");

                if(usuarion.equals("NO_USER")){
                    //si no esta registrado entonces pide login
                    Intent i = new Intent(getApplicationContext(),Login.class);
                    startActivity(i);
                    finish();
                }else{
                    //si esta registrado entonces lanza contenido
                    Intent i = new Intent(getApplicationContext(),Contenido.class);
                    startActivity(i);
                    finish();
                }
            }
        },3000);
    }
}
