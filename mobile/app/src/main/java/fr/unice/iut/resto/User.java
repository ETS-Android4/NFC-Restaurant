package fr.unice.iut.resto;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

class User {

    private static final String SESSION = "session";
    private static final String LOGIN = "login";
    private static final String CHECK = "check";
    private SharedPreferences pref;
    private SharedPreferences.Editor editor;
    private Context context;

    User(Context context) {
        this.context = context;
        pref = context.getSharedPreferences(SESSION, 0);
        editor = pref.edit();
    }

    void createSession(String login) {
        editor.putBoolean(CHECK, true);
        editor.putString(LOGIN, login);
        editor.commit();
    }

    String getLogin() {
        return pref.getString(LOGIN, null);
    }

    void checkSession() {
        if(!pref.getBoolean(CHECK, false)) {
            context.startActivity(new Intent(context, LoginActivity.class));
        }
    }

    void isLogged() {
        if(pref.getBoolean(CHECK, false)) {
            context.startActivity(new Intent(context, MenuActivity.class));
        }
    }

    void exitSession() {
        editor.clear();
        editor.commit();
        System.exit(0);
    }
}
