package fr.unice.iut.resto;

import android.os.Parcel;
import android.os.Parcelable;

class Food implements Parcelable {

    private int id;
    private String name;
    private String detail;
    private double price;

    Food(int id, String name, String detail, double price) {
        this.id = id;
        this.name = name;
        this.detail = detail;
        this.price = price;
    }

    int getId() {
        return id;
    }

    String getName() {
        return name;
    }

    String getDetail() {
        return detail;
    }

    double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return name + "\n" + price + "\n" + detail;
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

    private Food(Parcel in) {
        this.id = Integer.valueOf(in.readString());
        this.name = in.readString();
        this.detail = in.readString();
        this.price = Double.valueOf(in.readString());
    }
}
