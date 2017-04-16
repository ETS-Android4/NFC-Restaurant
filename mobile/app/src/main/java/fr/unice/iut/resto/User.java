package fr.unice.iut.resto;

import android.os.Parcel;
import android.os.Parcelable;

class User implements Parcelable {

    private String login;
    private String token;

    User(String login, String token) {
        this.login = login;
        this.token = token;
    }

    String getLogin() {
        return login;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(login);
        out.writeString(token);
    }

    public static final Parcelable.Creator<User> CREATOR = new Parcelable.Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }
        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    private User(Parcel in) {
        this.login = in.readString();
        this.token = in.readString();
    }
}
