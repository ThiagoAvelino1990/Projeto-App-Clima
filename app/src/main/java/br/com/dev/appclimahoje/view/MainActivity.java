package br.com.dev.appclimahoje.view;

import static br.com.dev.appclimahoje.utils.AppUtils.*;

import android.annotation.SuppressLint;
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

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;

import br.com.dev.appclimahoje.R;
import br.com.dev.appclimahoje.utils.AppUtils;

public class MainActivity extends AppCompatActivity {

    public TextView txtTitulo;
    public TextView txtCidade;
    public TextView txtHoraAtualizacao;
    public ImageView imgClima;
    public TextView txtTemperatura;
    public TextView txtSensacao;
    public TextView txtCondicao;
    public TextView txtVento;
    public TextView txtUmidade;
    public EditText editBusca;
    public Button btnBuscar;

    private static final int LOCATION_REQUEST_CODE = 200;

    public FusedLocationProviderClient localizacao;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);

            /**
             * Chamada para o método retornar latitude e longitude
             */
            AppUtils.solicitarLocalizacao(this, LOCATION_REQUEST_CODE, (lat, lon) -> {
                if (lat != null) {
                    //Colocar chamada para webService(Node)
                    System.out.println("Latitude: " + lat);
                    System.out.println("Longitude: " + lon);
                } else {
                    AppUtils.retornaMensagem(MainActivity.this,"Não foi possível obter a localização",'A');
                }
            });

            return insets;

        });

        initComponentes();


    }

    private void initComponentes() {
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

        localizacao = LocationServices.getFusedLocationProviderClient(MainActivity.this);
    }

}