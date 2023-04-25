package cl.mmoscoso.appviews;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Date;

import cl.mmoscoso.appviews.entity.Product;

public class CreateProductActivity extends AppCompatActivity {

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