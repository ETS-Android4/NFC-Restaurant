package fr.unice.iut.resto;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Classe pour gérer les nourritures d'un restaurant
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
     * @param detail Composants d'une nourriture
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
     * @return un identifiant de type entier
     */
    int getCode() {
        return id;
    }

    /**
     * Récupérer le nom d'une nourriture
     * @return un nom de type chaîne de caractère
     */
    String getName() {
        return name;
    }

    /**
     * Récupérer les composants d'une nourriture
     * @return les composants de type chaîne de caractère
     */
    String getDetail() {
        return detail;
    }

    /**
     * Récupérer le prix d'une nourriture
     * @return un prix de type réel
     */
    double getPrice() {
        return price;
    }

    /**
     * Récupérer l'URL de l'image d'une nourriture
     * @return une URL de type chaîne de caractère
     */
    String getPicture() {
        return picture;
    }

    /**
     * Récupérer le type d'une nourriture
     * @return entree si la nourriture est une entrée ou plats si la nourriture est un plat
     * ou dessert si la nourriture est un dessert ou boisson si la nourriture est un boisson
     */
    String getType() {
        return type;
    }

    /**
     * Calculer le nombre d'objet spécial contenu dans le Parcelable
     * @return un nombre d'objet spécial de type entier
     */
    @Override
    public int describeContents() {
        return 0;
    }

    /**
     * Passer l'objet Food à la prochaine activité
     * @param out Transporteur de l'objet Food
     * @param flags "flags" supplémentaire sur l'écriture d'un objet
     */
    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeString(String.valueOf(id));
        out.writeString(name);
        out.writeString(detail);
        out.writeString(String.valueOf(price));
        out.writeString(picture);
        out.writeString(type);
    }

    /**
     * Générer une instance de l'objet Food à partir d'un Parcel
     */
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
     * Récupérer l'objet Food dans la nouvelle activité
     * @param in Les informations sur l'objet Food
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
