package com.example.miniprojet.RecyclerView;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.miniprojet.Metier.Client;
import com.example.miniprojet.R;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ClientViewHolder>{

    Context context;
    List<Client> femmesList;

    public CustomAdapter(Context context, List<Client> femmesList) {
        this.context = context;
        this.femmesList = femmesList;
    }

    @NonNull
    @Override
    public ClientViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.my_row,parent,false);
        return new ClientViewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull final ClientViewHolder holder, int position) {
        Client client=femmesList.get(position);
        holder.adresserow.setText(client.getAdress());
        holder.usernamerow.setText(client.getUserName());
        holder.descriptionrow.setText(client.getDescription());
        holder.telephonerow.setText(client.getPhone());
        holder.imagerow.setImageBitmap(client.getImage());

    }

    @Override
    public int getItemCount() {
        return femmesList.size();
    }

    public static final class ClientViewHolder extends RecyclerView.ViewHolder{

        TextView descriptionrow,adresserow,telephonerow,usernamerow;
        ImageView imagerow;
        public ClientViewHolder(@NonNull View itemView) {
            super(itemView);

            usernamerow=itemView.findViewById(R.id.usernamerow);
            telephonerow=itemView.findViewById(R.id.telephonerow);
            adresserow=itemView.findViewById(R.id.adresserow);
            descriptionrow=itemView.findViewById(R.id.descriptionrow);
            imagerow=itemView.findViewById(R.id.imagerow);
        }
    }
}