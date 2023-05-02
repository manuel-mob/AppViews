package cl.mmoscoso.appviews.entity;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.Date;

import cl.mmoscoso.appviews.R;

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
    public Product(int id, String name, int amount, int quantity, Date expiration) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.quantity = quantity;
        this.expiration = expiration;
        this.lastPlace = "";
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

    public static class CreateProductActivityDB extends AppCompatActivity {

        private EditText name;
        private EditText amount;
        private EditText quantity;

        Product product;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_create_product);


            name = (EditText)findViewById(R.id.editName);
            amount = (EditText)findViewById(R.id.editAmount);
            quantity = (EditText)findViewById(R.id.editQuantity);


            // check if the product class hasExtra
            if (getIntent().hasExtra("PRODUCT")) {
                product = getIntent().getParcelableExtra("PRODUCT");
            }

            if (product != null) {
                name.setText(product.getName());
                amount.setText(Integer.toString(product.getAmount()));
                quantity.setText(Integer.toString(product.getQuantity()));
            }

        }

        public void getInformationFromEditText(View view){
            Product newProduct = new Product();
            newProduct.setName(name.getText().toString());
            newProduct.setAmount(Integer.valueOf(amount.getText().toString()));
            newProduct.setQuantity(Integer.valueOf(quantity.getText().toString()));
            newProduct.setExpiration(new Date());

            Toast.makeText(this,"Agregado:"+newProduct.getName(), Toast.LENGTH_LONG).show();


        }
    }
}
