package cl.mmoscoso.appviews;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Date;

import cl.mmoscoso.appviews.controller.DBManager;
import cl.mmoscoso.appviews.entity.Product;

public class CreateProductActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText name;
    private EditText amount;
    private EditText quantity;

    private DBManager dbManager;

    private Button create;


    Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_product);


        name = (EditText)findViewById(R.id.editName);
        amount = (EditText)findViewById(R.id.editAmount);
        quantity = (EditText)findViewById(R.id.editQuantity);

        create = (Button) findViewById(R.id.id_create);

        // check if the product class hasExtra
        if (getIntent().hasExtra("PRODUCT")) {
            product = getIntent().getParcelableExtra("PRODUCT");
        }

        if (product != null) {
            name.setText(product.getName());
            amount.setText(Integer.toString(product.getAmount()));
            quantity.setText(Integer.toString(product.getQuantity()));
        }

        dbManager = new DBManager(this);
        dbManager.open();



    }

    public void getInformationFromEditText(View view){
        Product newProduct = new Product();
        newProduct.setName(name.getText().toString());
        newProduct.setAmount(Integer.valueOf(amount.getText().toString()));
        newProduct.setQuantity(Integer.valueOf(quantity.getText().toString()));
        newProduct.setExpiration(new Date());

        Toast.makeText(this,"Agregado:"+newProduct.getName(), Toast.LENGTH_LONG).show();
        Toast.makeText(this,"Intentaremos guardar en DB:"+newProduct.getName(), Toast.LENGTH_LONG).show();
        create.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.id_create:
                Long rowId = dbManager.insert(name.getText().toString(), Integer.valueOf(amount.getText().toString()),Integer.valueOf(quantity.getText().toString()));
                if (rowId != null){
                    Toast.makeText(this,"Product saved",Toast.LENGTH_LONG).show();
                    this.finish();
                }
                break;
        }
    }
}