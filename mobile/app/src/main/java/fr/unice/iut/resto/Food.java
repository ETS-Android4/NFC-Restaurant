package fr.unice.iut.resto;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Classe pour gérer la nourriture d'un restaurant
 * @author ER
 * @version 1.0
 */
class Food implements Parcelable {

    private int id;
    private String name;
    private String detail;
    private double price;
    private String picture;
    private String type;

    /**
     * Constructeur de base d'un objet Food
     * @param id Identifiant d'une nourriture
     * @param name Nom d'une nourriture
     * @param detail Composant d'une nourriture
     * @param price Prix d'une nourriture
     * @param picture URL de l'image d'une nourriture
     * @param type Type d'une nourriture (Entrée, Plat, Dessert, Boisson)
     */
    Food(int id, String name, String detail, double price, String picture, String type) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.price = price;
        this.picture = picture;
        this.type = type;
    }

    /**
     * Récupérer l'identifiant d'une nourriture
     * @return un identifiant sous forme d'entier
     */
    int getCode() {
        return id;
    }

    /**
     * Récupérer le nom d'une nourriture
     * @return un nom sous forme de chaîne de caractère
     */
    String getName() {
        return name;
    }

    /**
     * Récupérer les composants d'une nourriture
     * @return un identifiant sous forme d'entier
     */
    String getDetail() {
        return detail;
    }

    /**
     * Récupérer le prix d'une nourriture
     * @return un prix sous forme de réel
     */
    double getPrice() {
        return price;
    }

    /**
     * Récupérer l'URL de l'image d'une nourriture
     * @return un URL sous forme de chaîne de caractère
     */
    String getPicture() {
        return picture;
    }

    /**
     * Récupérer le type d'une nourriture
     * @return un type sous forme de chaîne de caractère
     */
    String getType() {
        return type;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(String.valueOf(id));
        out.writeString(name);
        out.writeString(detail);
        out.writeString(String.valueOf(price));
        out.writeString(picture);
        out.writeString(type);
    }

    public static final Parcelable.Creator<Food> CREATOR = new Parcelable.Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }
        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    /**
     * Passer l'objet Food entre les activités
     * @param in Données concernant un Food
     */
    private Food(Parcel in) {
        this.id = Integer.valueOf(in.readString());
        this.name = in.readString();
        this.detail = in.readString();
        this.price = Double.valueOf(in.readString());
        this.picture = in.readString();
        this.type = in.readString();
    }
}
