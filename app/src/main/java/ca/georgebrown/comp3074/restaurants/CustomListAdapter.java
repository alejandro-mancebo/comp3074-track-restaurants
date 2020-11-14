package ca.georgebrown.comp3074.restaurants;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;

public class CustomListAdapter extends BaseAdapter {
    private ArrayList<RestaurantManager.Restaurant> listData;
    private LayoutInflater layoutInflater;

    public CustomListAdapter(Context aContext, ArrayList<RestaurantManager.Restaurant> listData) {
        this.listData = listData;
        layoutInflater = LayoutInflater.from(aContext);
    }

    @Override
    public int getCount() {
        return listData.size();
    }

    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = layoutInflater.inflate(R.layout.fragment_item, null);
            holder = new ViewHolder();
            holder.nameView = (TextView) convertView.findViewById(R.id.txtName);
            holder.typeView = (TextView) convertView.findViewById(R.id.txtType);
            holder.priceView = (TextView) convertView.findViewById(R.id.txtPrice);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.nameView.setText(listData.get(position).getName());
        holder.typeView.setText(listData.get(position).getType());
        double p = Double.parseDouble(listData.get(position).getPrice());
        NumberFormat fmt = NumberFormat.getCurrencyInstance();
        holder.priceView.setText(fmt.format(p));
        return convertView;
    }

    static class ViewHolder {
        TextView nameView;
        TextView typeView;
        TextView priceView;
    }
}
