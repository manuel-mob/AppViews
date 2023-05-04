package cl.mmoscoso.appviews;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.PreferenceManager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import cl.mmoscoso.appviews.adapters.ProductAdapter;
import cl.mmoscoso.appviews.adapters.ProductoBaseAdapter;
import cl.mmoscoso.appviews.controller.DBManager;
import cl.mmoscoso.appviews.entity.Product;

public class ProductInventary extends AppCompatActivity {

    ListView productList;
    ArrayList<Product> listProducts = new ArrayList<>();

    ArrayList<Product> shoppingList = new ArrayList<>();


    Product tempProduct;

    private DBManager dbManager;

    int option = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_inventary);

        productList = (ListView) findViewById(R.id.products);

        tempProduct = new Product(1,"Coca Cola 2L",1590,4,new Date(),"Lider Express");
        this.listProducts.add(tempProduct);

        tempProduct = new Product(1,"Leche",1230,12,new Date(),"Lider Express");
        this.listProducts.add(tempProduct);


        //Getting information from DATABASE
        dbManager = new DBManager(this);
        dbManager.open();
        Cursor cursor = dbManager.fetch();

        while (cursor.moveToNext()) {
            int id = cursor.getInt(cursor.getColumnIndexOrThrow("id"));
            String name = cursor.getString(cursor.getColumnIndexOrThrow("name"));
            int amount = cursor.getInt(cursor.getColumnIndexOrThrow("amount"));
            int quantity = cursor.getInt(cursor.getColumnIndexOrThrow("quantity"));

            tempProduct = new Product(id, name, amount, quantity, new Date());
            // add the product to a list or do something with it
            this.listProducts.add(tempProduct);
        }



        ProductAdapter adapter = new ProductAdapter(this,listProducts);
        productList.setAdapter(adapter);

        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tempProduct = (Product) parent.getItemAtPosition(position);

                if ( shoppingList.contains(tempProduct) ) {
                    Log.i("ProductInventary","Product exists:" + tempProduct.getName());
                    int quantity = shoppingList.get(shoppingList.indexOf(tempProduct)).getQuantity();
                    quantity = quantity + 1;
                    shoppingList.get(shoppingList.indexOf(tempProduct)).setQuantity(quantity);
                }
                else {
                    Log.i("ProductInventary","Product not exists:" + tempProduct.getName());
                    //Cantidad = 1
                    tempProduct.setQuantity(1);
                    shoppingList.add(tempProduct);
                }

                //Toast.makeText(ProductInventary.this,"Product for Shopping is:" + tempProduct.getName(),Toast.LENGTH_LONG).show();
                //Toast.makeText(ProductInventary.this,"I will shopping "+ String.valueOf(shoppingList.size()) + " products",Toast.LENGTH_LONG).show();
                Log.i("ProductInventary","Detail:" + getFullDetailOfShopping());
            }
        });


        //Get preferences
        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        String day = myPreferences.getString("day_preference","0");
        Log.i("ProductInventory","Product preferences day:" + day );
        Toast.makeText(this,"Day : " + day, Toast.LENGTH_LONG).show();


    }

    private String getFullDetailOfShopping(){
        String resultText = "";
        int index = 0;
        while (index < shoppingList.size()) {
            resultText = resultText + "P:"+ shoppingList.get(index).getName() +"("+String.valueOf(shoppingList.get(index).getQuantity()) +"), ";
            index = index + 1;
        }
        return resultText;
    }

    public void createProduct(View view){
        Intent createProduct = new Intent(this,CreateProductActivity.class);
        tempProduct = new Product(1,"Coca Cola 3L",1590,4,new Date(),"Lider Express");
        createProduct.putExtra("PRODUCT", tempProduct);
        startActivity(createProduct);
    }
    public void changeAdapter(View view){
        if (option == 1 ) {
            ProductoBaseAdapter adapter = new ProductoBaseAdapter(this,listProducts);
            productList.setAdapter(adapter);
            option = 0;
        }
        else {
            ProductAdapter adapter = new ProductAdapter(this,listProducts);
            productList.setAdapter(adapter);
            option = 1;
        }
    }
}