package fr.unice.iut.resto;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

class FoodAdapter extends BaseAdapter implements AdapterView.OnItemClickListener {

    private Context context;
    private ArrayList<Food> list = new ArrayList<>();

    FoodAdapter(Context context, ArrayList<Food> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Food getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return list.indexOf(getItem(position));
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {}

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        MyViewHolder viewHolder;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.food, parent, false);
            viewHolder = new MyViewHolder();
            viewHolder.txtName = (TextView) view.findViewById(R.id.txtName);
            viewHolder.txtPrice = (TextView) view.findViewById(R.id.txtPrice);
            viewHolder.txtDetail = (TextView) view.findViewById(R.id.txtDetail);
            viewHolder.imgFood = (ImageView) view.findViewById(R.id.imgFood);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (MyViewHolder) view.getTag();
        }
        Food item = getItem(position);
        viewHolder.txtName.setText(item.getName());
        viewHolder.txtPrice.setText(String.valueOf(item.getPrice()));
        viewHolder.txtDetail.setText(item.getDetail());
        Picasso.with(context).load(item.getPicture()).into(viewHolder.imgFood);
        return view;
    }

    private class MyViewHolder {
        TextView txtName, txtPrice, txtDetail;
        ImageView imgFood;
    }
}
