package br.com.dev.appclimahoje.view;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;
import java.util.List;

import br.com.dev.appclimahoje.R;
import br.com.dev.appclimahoje.utils.AppUtils;

public class SplashActivity extends AppCompatActivity {

    TextView txtAppCliente;
    ImageView imgAppCliente;
    TextView txtVersao;
    String[] permissoesNecessarias;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash);

        initComponentes();
        if(verificarPermissoes()){
            inicializarApp();
        }else{
            AppUtils.retornaMensagem(SplashActivity.this,"Favor verificar as permissões antes de continuar",'I');
        }

    }

    private void initComponentes() {
        txtAppCliente = findViewById(R.id.txtAppCliente);
        imgAppCliente = findViewById(R.id.imgAppCliente);
        txtVersao = findViewById(R.id.txtVersao);

        permissoesNecessarias = new String[]{
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_BACKGROUND_LOCATION
        };
    }

    private void inicializarApp() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent;
                if(AppUtils.getSharedPrefs("chk_lembrar_dados").equals("1")){
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

    private boolean verificarPermissoes(){
        List<String> permissoesNegadas = new ArrayList<>();

        int tpPermissao;

        for(String permissaoNecessaria :permissoesNecessarias){
            tpPermissao = ContextCompat.checkSelfPermission(this,permissaoNecessaria);

            if(tpPermissao != PackageManager.PERMISSION_GRANTED){
                permissoesNegadas.add(permissaoNecessaria);
            }
        }

        if(!permissoesNegadas.isEmpty()){
            ActivityCompat.requestPermissions(this, permissoesNegadas.toArray(new String[permissoesNegadas.size()]),AppUtils.REQUEST_CODE_APP);
            return false;
        }
        return true;
    }

}


