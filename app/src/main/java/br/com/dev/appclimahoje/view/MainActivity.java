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
        TextView txtTitulo = findViewById(R.id.txtTitulo);
        TextView txtCidade = findViewById(R.id.txtCidade);
        TextView txtHoraAtualizacao = findViewById(R.id.txtHoraAtualizacao);
        ImageView imgClima = findViewById(R.id.imgClima);
        TextView txtTemperatura = findViewById(R.id.txtTemperatura);
        TextView txtSensacao = findViewById(R.id.txtSensacao);
        TextView txtCondicao = findViewById(R.id.txtCondicao);
        TextView txtVento = findViewById(R.id.txtVento);
        TextView txtUmidade = findViewById(R.id.txtUmidade);
        EditText editBusca = findViewById(R.id.editBusca);
        Button btnBuscar = findViewById(R.id.btnBuscar);
    }

}