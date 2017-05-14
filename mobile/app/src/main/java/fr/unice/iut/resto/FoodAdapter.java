package fr.unice.iut.resto;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Classe pour créer un adaptateur customiser
 * @author ER
 * @version 1.0
 */
class FoodAdapter extends BaseAdapter {

    private static LayoutInflater inflater = null;
    private ArrayList<Food> list = new ArrayList<>();
    private Context context;

    /**
     * Créer une vue customiser
     */
    private class CustomViewHolder {
        TextView txtName, txtPrice, txtDetail;
        ImageView imgFood;
    }

    /**
     * Constructeur de base d'un adaptateur pour une liste
     * @param context Activité actuelle
     * @param list Une liste qu'on associera à l'adaptateur
     */
    FoodAdapter(Context context, ArrayList<Food> list) {
        this.context = context;
        this.list = list;
        inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
    }

    /**
     * Calculer la taille de la liste
     * @return une taille de type entier
     */
    @Override
    public int getCount() {
        return list.size();
    }

    /**
     * Récupérer un élément d'une liste
     * @param position La position de l'élément voulu dans la liste
     * @return un élément de type Food
     */
    @Override
    public Food getItem(int position) {
        return list.get(position);
    }

    /**
     * Récupérer l'identifiant d'un élément d'une liste
     * @param position La position de l'élément voulu dans la liste
     * @return un identifiant de type entier long
     */
    @Override
    public long getItemId(int position) {
        return list.indexOf(getItem(position));
    }

    /**
     * Récupérer la vue qui affiche l'élément à la position définie
     * @param position La Position de l'élément voulu dans la liste
     * @param view Une vue à utiliser
     * @param parent Un parent à qui la vue sera attacher
     * @return une vue qui correspond à l'élément spécifié de type View
     */
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        CustomViewHolder viewHolder;
        if (view == null) {
            view = inflater.inflate(R.layout.food, parent, false);
            viewHolder = new CustomViewHolder();
            viewHolder.txtName = (TextView) view.findViewById(R.id.txtName);
            viewHolder.txtPrice = (TextView) view.findViewById(R.id.txtPrice);
            viewHolder.txtDetail = (TextView) view.findViewById(R.id.txtDetail);
            viewHolder.imgFood = (ImageView) view.findViewById(R.id.imgFood);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (CustomViewHolder) view.getTag();
        }
        Food item = getItem(position);
        viewHolder.txtName.setText(item.getName());
        viewHolder.txtPrice.setText(item.getPrice() + "" + context.getResources().getString(R.string.gold));
        viewHolder.txtDetail.setText(item.getDetail());
        Picasso.with(context).load(item.getPicture()).into(viewHolder.imgFood);
        return view;
    }
}
