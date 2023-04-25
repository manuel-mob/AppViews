package cl.mmoscoso.appviews.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import cl.mmoscoso.appviews.R;
import cl.mmoscoso.appviews.entity.Product;

public class ProductAdapter extends ArrayAdapter<Product> {
    private final Context context;
    private final List<Product> products;

    public ProductAdapter(Context context, List<Product> products) {
        super(context, R.layout.product_list_row, products);
        this.context = context;
        this.products = products;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.product_list_row, parent, false);

        TextView name = rowView.findViewById(R.id.idProductName);
        TextView amount = rowView.findViewById(R.id.idProductAmount);
        TextView expiration = rowView.findViewById(R.id.idProductExpirationDate);
        TextView quantity = rowView.findViewById(R.id.idProductQuantity);

        Product product = products.get(position);
        name.setText(product.getName());
        amount.setText(String.valueOf(product.getAmount()));
        expiration.setText(String.valueOf(product.getExpiration()));
        quantity.setText(String.valueOf(product.getQuantity()));

        return rowView;
    }
}
