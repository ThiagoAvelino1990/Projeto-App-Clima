package br.com.dev.appclimahoje.utils;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationCallback;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationResult;
import com.google.android.gms.location.LocationServices;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class AppUtils {

    public static final String TAG = "LOG_APPCLIMAHOJE";
    public static final int TIME_SPLASH = 4 * 1000;
    public static final String PREF = "app_clima_hoje_pref";
    public static final String VERSION = "v1.0.0";
    public static final int REQUEST_CODE_APP = 2025;

    private static final String EMAIL_PADRAO = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final Pattern padraoAceito = Pattern.compile(EMAIL_PADRAO, Pattern.CASE_INSENSITIVE);

    private static final String SENHA_SIMPLES = "^.{8,}$";
    //private static final String SENHA_FORTE = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[^A-Za-z\d]).{10,}$";
    private static final Pattern padraoSenhaSimples = Pattern.compile(SENHA_SIMPLES);

    public static SharedPreferences pref;
    public static Context context;


    /**
     * Função para validar se senha digitada corresponde com a senha cadastrada
     *
     * @return
     */
    public static boolean validaSenhaCadastro(String senha, String confirmarSenha) {
        //Verifica se a senha está nula
        if (senha.isEmpty()) {
            return false;
        }

        // validação de senha
        Matcher senhaValida = padraoSenhaSimples.matcher(senha);
        if (senhaValida.matches() && senha.equals(confirmarSenha)) {
            return true;
        }

        return false;
    }

    /**
     * Método para validar se a senha digitada no Login é válida
     *
     * @return
     */
    public static boolean validaSenhaLogin(String senhaInformada) {
        pref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);

        if (pref.getString("senha", null).equals(senhaInformada)) {
            return true;
        }

        return false;

    }

    /**
     * Função criada para validação de Email
     *
     * @return
     */
    public static boolean validaEmail(String emailInformado) {

        if (emailInformado.isEmpty()) {
            return false;
        } else {
            Matcher matcher = padraoAceito.matcher(emailInformado);

            return matcher.matches();
        }
    }

    /**
     * Método criado para padronização das mensagens
     *
     * @param contexto
     * @param mensagem
     */
    public static void retornaMensagem(Context contexto, String mensagem, char tipoMensagem) {

        /**
         * E -> ERRO
         * I -> INFORMACAO
         * A -> ALERTA
         */
        switch (tipoMensagem) {
            case 'E':
                Toast.makeText(contexto, "Erro ao realizar a operação: " + mensagem, Toast.LENGTH_LONG).show();
                Log.e(TAG, mensagem);
                break;
            case 'I':
                Toast.makeText(contexto, mensagem, Toast.LENGTH_LONG).show();
                Log.i(TAG, mensagem);
                break;
            case 'A':
                Toast.makeText(contexto, mensagem, Toast.LENGTH_LONG).show();
                Log.w(TAG, mensagem);
                break;
            default:
                Log.d(TAG, mensagem);
        }
    }

    /**
     * @return Retorna a data no formato "dd/MM/yyyy 00:00:00"
     */
    public static String getDataFormat() {

        String dataAtualFormat;

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();

        dataAtualFormat = dateFormat.format(date);

        return dataAtualFormat;

    }

    public static String getSharedPrefs(String key) {
        pref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);

        return pref.getString(key, "");
    }

    public static void setSharedPrefs(String key, String valor) {
        pref = context.getSharedPreferences(PREF, Context.MODE_PRIVATE);
        SharedPreferences.Editor SetDados = pref.edit();

        SetDados.putString(key, valor);

    }



    public interface LocationCallback {
        void onLocationReceived(Double lat, Double lon);
    }

    /**
     * Método para verificar a localização e solicitar permissão caso não tenha.
     * Porém, na tela splash é solicitado a permissão de acesso a logalização
     * @param activity
     * @param requestCode
     * @param callback
     */
    public static void solicitarLocalizacao(Activity activity, int requestCode, LocationCallback callback) {

        /**
         * Verifica se possui a permissão, caso não irá solicitar novamente para o usuário
         */
        if ((ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)
            &&
        (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED)
        &&
                (ActivityCompat.checkSelfPermission(activity, Manifest.permission.ACCESS_BACKGROUND_LOCATION) != PackageManager.PERMISSION_GRANTED)){

            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_BACKGROUND_LOCATION},
                    requestCode);

        } else {
            /**
             * Caso já possua acesso, faz a busca para pegar a localização
             */
            getLocalizacao(activity, callback);
        }
    }


    /**
     * Este método irá obeter a localização (latitude e longitute)
     * @param context
     * @param callback
     */
    @SuppressLint("MissingPermission")
    private static void getLocalizacao(Context context, LocationCallback callback) {

        FusedLocationProviderClient fusedLocationClient =
                LocationServices.getFusedLocationProviderClient(context);

        fusedLocationClient.getLastLocation()
                .addOnSuccessListener(location -> {
                    if (location != null) {
                        callback.onLocationReceived(location.getLatitude(), location.getLongitude());
                    } else {
                        callback.onLocationReceived(null, null);
                    }
                })
                .addOnFailureListener(e -> callback.onLocationReceived(null, null));
    }


}
