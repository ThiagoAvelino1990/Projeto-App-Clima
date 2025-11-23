package br.com.dev.appclimahoje.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.com.dev.appclimahoje.R;
import br.com.dev.appclimahoje.utils.AppUtils;

public class CadastroActivity extends AppCompatActivity {

    TextView txtCadastro;
    EditText editCadastroNome;
    EditText editCadastroEmail;
    EditText editCadastroSenha;
    EditText editCadastroConfirmarSenha;
    CheckBox chkLembrarDados;
    ImageView imgAppCliente;
    Button btnCadastro;
    Button btnVoltar;

    public SharedPreferences cadastroPrefs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro);
        
        initComponentes();
        gravaDados();

        /**
         * Desenvolvimento dos Botões
         */
        btnCadastro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gravaDados();
            }
        });

        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;

                intent = new Intent(CadastroActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void initComponentes() {
        txtCadastro = findViewById(R.id.txtCadastro);
        editCadastroNome = findViewById(R.id.editCadastroNome);
        editCadastroEmail = findViewById(R.id.editCadastroEmail);
        editCadastroSenha = findViewById(R.id.editCadastroSenha);
        editCadastroConfirmarSenha = findViewById(R.id.editCadastroConfirmarSenha);
        chkLembrarDados = findViewById(R.id.chkLembrarDados);
        imgAppCliente = findViewById(R.id.imgAppCliente);
        btnCadastro = findViewById(R.id.btnCadastro);
        btnVoltar = findViewById(R.id.btnVoltar);

        cadastroPrefs = getSharedPreferences(AppUtils.PREF,MODE_PRIVATE);
    }

    private void gravaDados() {
        SharedPreferences.Editor gravarDadosCadastro = cadastroPrefs.edit();
        boolean isDadosOK = true;


        if(editCadastroNome.getText().toString().isEmpty()){
            isDadosOK = false;
            AppUtils.retornaMensagem(CadastroActivity.this,"Campo não pode estar vazio",'A');
            editCadastroNome.setError("*");
            editCadastroNome.requestFocus();
        }

        if(editCadastroEmail.getText().toString().isEmpty() || !AppUtils.validaEmail(editCadastroEmail.getText().toString())){
            isDadosOK = false;
            AppUtils.retornaMensagem(CadastroActivity.this,"Email inválido. Favor verificar",'A');
            editCadastroEmail.setError("*");
            editCadastroEmail.requestFocus();
        }

        if(editCadastroSenha.getText().toString().isEmpty() || !AppUtils.validaEmail(editCadastroSenha.getText().toString())){
            isDadosOK = false;
            AppUtils.retornaMensagem(CadastroActivity.this,"Senha informada inválida",'A');
            editCadastroSenha.setError("*");
            editCadastroSenha.requestFocus();
        }

        if(!editCadastroSenha.getText().toString().equals(editCadastroConfirmarSenha.getText().toString())){
            isDadosOK = false;
            AppUtils.retornaMensagem(CadastroActivity.this,"Senhas não coincidem",'A');
            editCadastroConfirmarSenha.setError("*");
            editCadastroConfirmarSenha.requestFocus();
            editCadastroSenha.setText("");
            editCadastroConfirmarSenha.setText("");
        }

        if (isDadosOK){
            gravarDadosCadastro.putString("nome",editCadastroNome.getText().toString());
            gravarDadosCadastro.putString("email",editCadastroEmail.getText().toString());
            gravarDadosCadastro.putString("senha",editCadastroSenha.getText().toString());
            gravarDadosCadastro.putString("confirmar_senha",editCadastroConfirmarSenha.getText().toString());
            // Salver checkBox
            if(chkLembrarDados.isChecked()){
                gravarDadosCadastro.putBoolean("chk_lembrar_dados",true);
            }else{
                gravarDadosCadastro.putBoolean("chk_lembrar_dados",false);
            }
        }

    }





}
