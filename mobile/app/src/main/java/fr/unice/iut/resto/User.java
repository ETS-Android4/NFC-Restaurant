package fr.unice.iut.resto;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

/**
 * Classe pour gérer la session d'un utilisateur
 * @author ER
 * @version 1.0
 */
class User {

    private static final String SESSION = "session";
    private static final String LOGIN = "login";
    private static final String CHECK = "check";
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context context;

    /**
     * Constructeur de base d'un objet User
     * @param context Activité actuelle
     */
    User(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(SESSION, 0);
        editor = pref.edit();
    }

    /**
     * Créer une session
     * @param login Téléphone d'un utilisateur
     */
    void createSession(String login) {
        editor.putBoolean(CHECK, true);
        editor.putString(LOGIN, login);
        editor.commit();
    }

    /**
     * Récupérer le téléphone d'un utilisateur
     * @return un login de type chaîne de caractère
     */
    String getLogin() {
        return pref.getString(LOGIN, null);
    }

    /**
     * Vérifier l'existance d'une session
     */
    void checkSession() {
        if(!pref.getBoolean(CHECK, false)) {
            context.startActivity(new Intent(context, LoginActivity.class));
            ((Activity) context).finish();
        }
    }

    /**
     * Vérifier si un utilisateur est toujours connecté à sa session
     */
    void isLogged() {
        if(pref.getBoolean(CHECK, false)) {
            context.startActivity(new Intent(context, MenuActivity.class));
            ((Activity) context).finish();
        }
    }

    /**
     * Détruire une session
     */
    void exitSession() {
        editor.clear();
        editor.commit();
        context.startActivity(new Intent(context, LoginActivity.class));
        ((Activity) context).finish();
    }
}
