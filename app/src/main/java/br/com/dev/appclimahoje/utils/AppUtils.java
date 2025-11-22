package br.com.dev.appclimahoje.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

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

    private static final String EMAIL_PADRAO = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final Pattern padraoAceito = Pattern.compile(EMAIL_PADRAO,Pattern.CASE_INSENSITIVE);

    private static final String SENHA_SIMPLES = "^.{8,}$";
    //private static final String SENHA_FORTE = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)(?=.*[^A-Za-z\d]).{10,}$";
    private static final Pattern padraoSenhaSimples = Pattern.compile(SENHA_SIMPLES);

    public static SharedPreferences pref;
    public static Context context;

    /**
     * Função para validar se senha digitada corresponde com a senha cadastrada
     * @return
     */
    public boolean validaSenhaCadastro(String senha, String confirmarSenha){
        //Verifica se a senha está nula
        if (senha.isEmpty()){
            return false;
        }

        // validação de senha
        Matcher senhaValida = padraoSenhaSimples.matcher(senha);
        if(senhaValida.matches() && senha.equals(confirmarSenha)){
            return true;
        }

        return false;
    }

    /**
     * Método para validar se a senha digitada no Login é válida
     * @return
     */
    public boolean validaSenhaLogin(String senhaInformada){
        pref = context.getSharedPreferences(PREF,Context.MODE_PRIVATE);

        if(pref.getString("senha",null).equals(senhaInformada)){
            return true;
        }

        return false;

    }

    /**
     * Função criada para validação de Email
     * @return
     */
    public boolean validaEmail(String emailInformado){

        if (emailInformado.isEmpty()){
            return false;
        }else {
            Matcher matcher = padraoAceito.matcher(emailInformado);

            return matcher.matches();
        }
    }

    /**
     * Método criado para padronização das mensagens
     * @param contexto
     * @param mensagem
     */
    public void retornaMensagem(Context contexto, String mensagem, char tipoMensagem){

        /**
         * E -> ERRO
         * I -> INFORMACAO
         * A -> ALERTA
         */
        switch(tipoMensagem){
            case 'E':
                Toast.makeText(contexto, "Erro ao realizar a operação: "+mensagem,Toast.LENGTH_LONG).show();
                Log.e(TAG,mensagem);
                break;
            case 'I':
                Toast.makeText(contexto, mensagem,Toast.LENGTH_LONG).show();
                Log.i(TAG,mensagem);
                break;
            case 'A':
                Toast.makeText(contexto, mensagem,Toast.LENGTH_LONG).show();
                Log.w(TAG,mensagem);
                break;
            default:
                Log.d(TAG, mensagem);
        }
    }

    /**
     *
     * @return Retorna a data no formato "dd/MM/yyyy 00:00:00"
     */
    public static String getDataFormat(){

        String dataAtualFormat;

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault());
        Date date = new Date();

        dataAtualFormat = dateFormat.format(date);

        return dataAtualFormat;

    }
}
