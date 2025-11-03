package br.com.dev.appclimahoje.view;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import br.com.dev.appclimahoje.R;
import br.com.dev.appclimahoje.utils.AppUtils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        inicializarApp();
    }

    private void inicializarApp() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;

                intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                Log.i(AppUtils.TAG,"Transição da Tela Splash para a tela de Login");
                finish();
            }
        }, AppUtils.TIME_SPLASH);
    }



}


