package com.example.proyectofinal;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.example.proyectofinal.model.Producto;
import com.squareup.picasso.Picasso;

import java.util.List;


public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private List<Producto> fruits;
    private int layout;
    private Activity activity;
    private OnItemClickListener listener;

    // Pasamos el activity en vez del context, ya que nos hará falta para poder inflar en context menu
    public MainAdapter(List<Producto> fruits, int layout, Activity activity, OnItemClickListener listener) {
        this.fruits = fruits;
        this.layout = layout;
        this.activity = activity;
        this.listener = listener;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(activity).inflate(layout, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(fruits.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return fruits.size();
    }

    // Implementamos las interfaces OnCreateContextMenuListener y OnMenuItemClickListener
    // para hacer uso del context menu en RecyclerView, y sobreescribimos los métodos
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener, MenuItem.OnMenuItemClickListener {

        public TextView textViewName;
        public TextView textViewDescription;
        public TextView textViewQuantity;
        public ImageView imageViewBackground;

        public ViewHolder(View itemView) {
            super(itemView);

            textViewName = (TextView) itemView.findViewById(R.id.post_title);
            textViewDescription = (TextView) itemView.findViewById(R.id.sub_title);
            textViewQuantity = (TextView) itemView.findViewById(R.id.price);
            imageViewBackground = (ImageView) itemView.findViewById(R.id.post_image);
            // Añadimos al view el listener para el context menu, en vez de hacerlo en
            // el activity mediante el método registerForContextMenu
            itemView.setOnCreateContextMenuListener(this);
        }

        public void bind(final Producto fruit, final OnItemClickListener listener) {

            this.textViewName.setText(fruit.getNombre());
            this.textViewDescription.setText(fruit.getDescripcion());
            this.textViewQuantity.setText(fruit.getPrecio() + "");
            // Lógica aplicada para la limitación de la cantidad en cada elemento fruta
            if (fruit.getPrecio() == Producto.LIMIT_QUANTITY) {
                textViewQuantity.setTextColor(ContextCompat.getColor(activity, R.color.black));
                textViewQuantity.setTypeface(null, Typeface.BOLD);
            } else {
                textViewQuantity.setTextColor(ContextCompat.getColor(activity, R.color.black));
                textViewQuantity.setTypeface(null, Typeface.NORMAL);
            }
            // Cargamos la imagen con Picasso

            Picasso.get().load(fruit.getUrl()).into(this.imageViewBackground);

            // Añadimos el listener click para cada elemento fruta
            this.imageViewBackground.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(fruit, getAdapterPosition());
                }
            });
        }

        // Sobreescribimos onCreateContextMenu, dentro del ViewHolder,
        // en vez de hacerlo en el activity
        @Override
        public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
            // Recogemos la posición con el método getAdapterPosition
            Producto fruitSelected = fruits.get(this.getAdapterPosition());
            // Establecemos título e icono para cada elemento, mirando en sus propiedades
            contextMenu.setHeaderTitle(fruitSelected.getNombre());
             // Inflamos el menú
            MenuInflater inflater = activity.getMenuInflater();
            inflater.inflate(R.menu.menu, contextMenu);
            // Por último, añadimos uno por uno, el listener onMenuItemClick para
            // controlar las acciones en el contextMenu, anteriormente lo manejábamos
            // con el método onContextItemSelected en el activity
            for (int i = 0; i < contextMenu.size(); i++)
                contextMenu.getItem(i).setOnMenuItemClickListener(this);
        }

        // Sobreescribimos onMenuItemClick, dentro del ViewHolder,
        // en vez de hacerlo en el activity bajo el nombre onContextItemSelected
        @Override
        public boolean onMenuItemClick(MenuItem menuItem) {
            // No obtenemos nuestro objeto info
            // porque la posición la podemos rescatar desde getAdapterPosition
            switch (menuItem.getItemId()) {
                case R.id.menu_usuarios:
                    // Observa que como estamos dentro del adaptador, podemos acceder
                    // a los métodos propios de él como notifyItemRemoved o notifyItemChanged
                    fruits.remove(getAdapterPosition());
                    notifyItemRemoved(getAdapterPosition());
                    return true;

                default:
                    return false;
            }
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Producto fruit, int position);
    }
}