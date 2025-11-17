package br.com.dev.appclimahoje.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AppUtils {

    public static final String TAG = "LOG_APPCLIMAHOJE";
    public static final int TIME_SPLASH = 4 * 1000;
    public static final String PREF = "appclimahojepref";

    private static final String EMAIL_PADRAO = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$";
    private static final Pattern padraoAceito = Pattern.compile(EMAIL_PADRAO,Pattern.CASE_INSENSITIVE);


    /**
     * Função para validar se senha digita corresponde com a senha cadastrada
     * @return
     */
    public boolean validarSenhaCadastro(String senha, String confirmarSenha){
        //Verifica se a senha está nula
        if (senha.isEmpty()){
            return false;
        }

        // Criar validação para senha - https://stackoverflow.com/questions/3802192/regexp-java-for-password-validation




        return true;
    }

    /**
     * Função criada para validação de Email
     * @return
     */
    public boolean valirdarEmail(String emailInformado){

        if (emailInformado.isEmpty()){
            return false;
        }else {
            Matcher matcher = padraoAceito.matcher(emailInformado);

            return matcher.matches();
        }
    }
}
