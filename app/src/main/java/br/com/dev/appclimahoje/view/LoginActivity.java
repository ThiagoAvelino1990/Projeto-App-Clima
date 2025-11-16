package br.com.dev.appclimahoje.view;

import android.os.Bundle;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import br.com.dev.appclimahoje.R;

public class LoginActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        initComponentes();
    }

    private void initComponentes() {
        TextView txtLogin = findViewById(R.id.txtLogin);
        EditText editLoginEmail = findViewById(R.id.editLoginEmail);
        EditText editLoginSenha = findViewById(R.id.editLoginSenha);
        CheckBox chkLembrarSenha = findViewById(R.id.chkLembrarSenha);
        ImageView imgAppCliente = findViewById(R.id.imgAppCliente);
        TextView txtVersao = findViewById(R.id.txtVersao);
    }
}
