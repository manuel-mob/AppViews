package cl.mmoscoso.appviews;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;

import cl.mmoscoso.appviews.adapters.ProductAdapter;
import cl.mmoscoso.appviews.entity.Product;

public class ProductInventary extends AppCompatActivity {

    ListView productList;
    ArrayList<Product> listProducts = new ArrayList<>();
    Product tempProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_inventary);

        productList = (ListView) findViewById(R.id.products);

        tempProduct = new Product(1,"Coca Cola 2L",1590,4,new Date(),"Lider Express");
        this.listProducts.add(tempProduct);

        tempProduct = new Product(1,"Leche",1230,12,new Date(),"Lider Express");
        this.listProducts.add(tempProduct);


        ProductAdapter adapter = new ProductAdapter(this,listProducts);
        productList.setAdapter(adapter);

        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                tempProduct = (Product) parent.getItemAtPosition(position);
            }
        });

    }

    public void createProduct(View view){
        Intent createProduct = new Intent(this,CreateProductActivity.class);
        tempProduct = new Product(1,"Coca Cola 3L",1590,4,new Date(),"Lider Express");
        createProduct.putExtra("PRODUCT", tempProduct);
        startActivity(createProduct);
    }

}