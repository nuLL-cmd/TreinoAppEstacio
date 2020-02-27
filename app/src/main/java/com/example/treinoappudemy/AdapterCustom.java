package com.example.treinoappudemy;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.text.ParseException;

public class AdapterCustom extends ArrayAdapter {
    public AdapterCustom(@NonNull Context context, int resource) {
        super(context, resource);
    }

    @Override
    public void add(@Nullable Object object) {
        super.add(object);
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater;
        DataHandler dataHandler;
        DataProvider dataProvider;

        if(convertView == null){
            dataHandler = new DataHandler();
            layoutInflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = layoutInflater.inflate(R.layout.layout_celula,parent,false);
            dataHandler.txt_titulo = convertView.findViewById(R.id.txt_titulo);
            dataHandler.txt_letra = convertView.findViewById(R.id.txt_letra);
            dataHandler.img_fav  = convertView.findViewById(R.id.img_fav);

            convertView.setTag(dataHandler);

        }else{
            dataHandler = (DataHandler) convertView.getTag();
        }
        dataProvider = (DataProvider) getItem(position);
        dataHandler.txt_letra.setText(dataProvider.getLetra());
        dataHandler.txt_titulo.setText(dataProvider.getTreino());
        dataHandler.img_fav.setImageResource(dataProvider.getImage());

        return convertView;
    }
}
