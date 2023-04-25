package cl.mmoscoso.appviews.entity;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.Date;

public class Product implements Parcelable {
    int id;
    String name;
    int amount;
    int quantity;
    Date expiration;
    String lastPlace;

    public Product() {
        this.amount = 0;
        this.expiration = new Date(2023,4,18);
        this.lastPlace = "";
    }

    public Product(int id, String name, int amount, int quantity, Date expiration, String lastPlace) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.quantity = quantity;
        this.expiration = expiration;
        this.lastPlace = lastPlace;
    }
    protected Product(Parcel in) {
        id = in.readInt();
        name = in.readString();
        amount = in.readInt();
        quantity = in.readInt();
        expiration = new Date(in.readString());
        lastPlace = in.readString();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getExpiration() {
        return expiration;
    }

    public void setExpiration(Date expiration) {
        this.expiration = expiration;
    }

    public String getLastPlace() {
        return lastPlace;
    }

    public void setLastPlace(String lastPlace) {
        this.lastPlace = lastPlace;
    }

    @Override
    public int describeContents() {

        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.name);
        dest.writeInt(this.amount);
        dest.writeInt(this.quantity);
        dest.writeString(this.expiration.toString());
        dest.writeString(this.lastPlace);
    }

    public static final Creator<Product> CREATOR = new Creator<Product>() {
        @Override
        public Product createFromParcel(Parcel in) {
            return new Product(in);
        }

        @Override
        public Product[] newArray(int size) {
            return new Product[size];
        }
    };
}
