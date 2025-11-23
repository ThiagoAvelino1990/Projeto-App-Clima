package br.com.dev.appclimahoje.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import br.com.dev.appclimahoje.R;
import br.com.dev.appclimahoje.utils.AppUtils;

public class SplashActivity extends AppCompatActivity {

    TextView txtAppCliente;
    ImageView imgAppCliente;
    TextView txtVersao;
    SharedPreferences splashPrefs;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        initComponentes();
        inicializarApp();
    }

    private void initComponentes(){
        txtAppCliente = findViewById(R.id.txtAppCliente);
        imgAppCliente = findViewById(R.id.imgAppCliente);
        txtVersao = findViewById(R.id.txtVersao);

        splashPrefs = getSharedPreferences(AppUtils.PREF,MODE_PRIVATE);
    }

    private void inicializarApp() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if(splashPrefs.getBoolean("chk_lembrar_dados",false)){
                    intent = new Intent(SplashActivity.this, MainActivity.class);
                    startActivity(intent);
                    AppUtils.retornaMensagem(SplashActivity.this,"Carregando dados",'I');
                    finish();
                }else{
                    intent = new Intent(SplashActivity.this, LoginActivity.class);
                    startActivity(intent);
                    AppUtils.retornaMensagem(SplashActivity.this,"Carregando dados",'I');
                    finish();
                }

            }
        }, AppUtils.TIME_SPLASH);
    }



}


