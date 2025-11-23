package br.com.dev.appclimahoje.view;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import org.w3c.dom.Text;

import br.com.dev.appclimahoje.R;

public class MainActivity extends AppCompatActivity {

    TextView txtTitulo;
    TextView txtCidade;
    TextView txtHoraAtualizacao;
    ImageView imgClima;
    TextView txtTemperatura;
    TextView txtSensacao;
    TextView txtCondicao;
    TextView txtVento;
    TextView txtUmidade;
    EditText editBusca;
    Button btnBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        initComponentes();

    }

    private void initComponentes(){
        txtTitulo = findViewById(R.id.txtTitulo);
        txtCidade = findViewById(R.id.txtCidade);
        txtHoraAtualizacao = findViewById(R.id.txtHoraAtualizacao);
        imgClima = findViewById(R.id.imgClima);
        txtTemperatura = findViewById(R.id.txtTemperatura);
        txtSensacao = findViewById(R.id.txtSensacao);
        txtCondicao = findViewById(R.id.txtCondicao);
        txtVento = findViewById(R.id.txtVento);
        txtUmidade = findViewById(R.id.txtUmidade);
        editBusca = findViewById(R.id.editBusca);
        btnBuscar = findViewById(R.id.btnBuscar);
    }

}