package cl.mmoscoso.appviews.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import java.util.List;
import cl.mmoscoso.appviews.R;
import cl.mmoscoso.appviews.entity.Product;

public class ProductoBaseAdapter extends BaseAdapter {
    private final Context context;
    private final List<Product> products;

    public ProductoBaseAdapter(Context context, List<Product> products) {
        this.context = context;
        this.products = products;
        Log.i("ProductoBaseAdapter","ProductoBaseAdapter");
    }

    @Override
    public int getCount() {

        return this.products.size();
    }

    @Override
    public Object getItem(int position) {
        return this.products.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Log.i("ProductoBaseAdapter","getView");
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.product_list_row, parent, false);
        }

        TextView nameTextView = convertView.findViewById(R.id.idProductName);
        TextView amountTextView = convertView.findViewById(R.id.idProductAmount);
        TextView quantityTextView = convertView.findViewById(R.id.idProductQuantity);

        Product product = (Product) getItem(position);

        nameTextView.setText(product.getName());
        amountTextView.setText(Integer.toString(product.getAmount()));
        quantityTextView.setText(String.valueOf(product.getQuantity()));

        return convertView;
    }
}
