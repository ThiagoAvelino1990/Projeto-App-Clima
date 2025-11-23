package br.com.dev.appclimahoje.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import br.com.dev.appclimahoje.R;
import br.com.dev.appclimahoje.utils.AppUtils;

public class LoginActivity extends AppCompatActivity {

    TextView txtLogin;
    EditText editLoginEmail;
    EditText editLoginSenha;
    CheckBox chkLembrarSenha;
    ImageView imgAppCliente;
    TextView txtVersao;
    Button btnLogin;
    Button btnSair;
    SharedPreferences Loginprefs;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        initComponentes();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor gravarDadosLogin = Loginprefs.edit();

                Boolean isDadosOk = true;

                if(editLoginEmail.getText().toString().isEmpty() || !(Loginprefs.getString("email",null).equals(editLoginEmail.getText().toString()))){
                    isDadosOk = false;
                   AppUtils.retornaMensagem(LoginActivity.this,"Login não cadastrado",'A');
                   editLoginEmail.setError("*");
                   editLoginEmail.requestFocus();
                }

                if(editLoginSenha.getText().toString().isEmpty() || !(Loginprefs.getString("senha",null).equals(editLoginSenha.getText().toString()))){
                    isDadosOk = false;
                    AppUtils.retornaMensagem(LoginActivity.this,"Senha incorreta",'A');
                    editLoginSenha.setError("*");
                    editLoginSenha.requestFocus();
                }

                if(isDadosOk){
                    // Salver checkBox
                    if(chkLembrarSenha.isChecked()){
                        gravarDadosLogin.putBoolean("chk_lembrar_dados",true);
                    }else{
                        gravarDadosLogin.putBoolean("chk_lembrar_dados",false);
                    }
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent;

                            intent = new Intent(LoginActivity.this, MainActivity.class);
                            startActivity(intent);
                            AppUtils.retornaMensagem(LoginActivity.this,"Login realizado com Sucesso !",'I');
                            finish();
                        }
                    }, AppUtils.TIME_SPLASH);
                }




            }
        });

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finishAffinity();
            }
        });

    }

    private void initComponentes() {
        txtLogin = findViewById(R.id.txtLogin);
        editLoginEmail = findViewById(R.id.editLoginEmail);
        editLoginSenha = findViewById(R.id.editLoginSenha);
        chkLembrarSenha = findViewById(R.id.chkLembrarSenha);
        imgAppCliente = findViewById(R.id.imgAppCliente);
        txtVersao = findViewById(R.id.txtVersao);
        btnLogin = findViewById(R.id.btnLogin);
        btnSair = findViewById(R.id.btnSair);

        Loginprefs = getSharedPreferences(AppUtils.PREF,MODE_PRIVATE);
    }
}
