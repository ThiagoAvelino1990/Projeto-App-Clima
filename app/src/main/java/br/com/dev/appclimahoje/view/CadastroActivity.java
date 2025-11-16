package br.com.dev.appclimahoje.view;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import br.com.dev.appclimahoje.R;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_cadastro);
        
        initComponentes();
    }

    private void initComponentes() {
        TextView txtCadastro = findViewById(R.id.txtCadastro);
        EditText editCadastroNome = findViewById(R.id.editCadastroNome);
        EditText editCadastroEmail = findViewById(R.id.editCadastroEmail);
        EditText editCadastroSenha = findViewById(R.id.editCadastroSenha);
        EditText editCadastroConfirmarSenha = findViewById(R.id.editCadastroConfirmarSenha);
        CheckBox chkLembrarDados = findViewById(R.id.chkLembrarDados);
        ImageView imgAppCliente = findViewById(R.id.imgAppCliente);
    }


}
