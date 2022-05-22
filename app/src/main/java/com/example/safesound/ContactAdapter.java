package com.example.safesound;

import android.view.ViewGroup;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.app.Activity;
import android.view.LayoutInflater;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;



import java.util.ArrayList;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder> {
    private Context context;
    private ArrayList<Contacts> contactModelArrayList;
    int lastpos= -1;


    public ContactAdapter(Context context, ArrayList<Contacts> contactModelArrayList) {
        this.context = context;
        this.contactModelArrayList = contactModelArrayList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView contactTextName,contactTextPhone;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contactTextName=itemView.findViewById(R.id.idContactName);
            contactTextPhone=itemView.findViewById(R.id.idContactPhone);
        }
    }



    @NonNull
    @Override
    public ContactAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_rv_item,parent, false);
        return new ContactAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.ViewHolder holder, int position) {

        Contacts contact1= contactModelArrayList.get(position);
        holder.contactTextName.setText(contact1.getContactName());
        holder.contactTextPhone.setText(contact1.getContactPhone());
    }

    @Override
    public int getItemCount() {
        return contactModelArrayList.size();
    }
}
