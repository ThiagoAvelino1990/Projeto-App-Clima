package br.com.dev.appclimahoje;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import br.com.dev.appclimahoje.utils.ClimaHojeUtils;

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

                intent = new Intent(SplashActivity.this,MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, ClimaHojeUtils.TIME_SPLASH);
    }



}


