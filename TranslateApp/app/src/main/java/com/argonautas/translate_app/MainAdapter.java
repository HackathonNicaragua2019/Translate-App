package com.argonautas.translate_app;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

class MainAdapter  extends BaseAdapter {

    private Context context;
    private LayoutInflater inflater1;

    private String [] lugarest;
    private String [] idiomas;
    private String [] descript;

    private int[] lugpics;

    public MainAdapter(Context c,String[] lugarest, int [] lugpics, String[] idiomas,String [] descript){
        context = c;
        this.lugarest = lugarest;

        this.idiomas = idiomas;
        this.descript = descript;
        this.lugpics = lugpics;
    }


    @Override
    public int getCount() {
        return lugpics.length;
    }

    @Override
    public Object getItem(int position) {
        return lugpics;
    }

    @Override
    public long getItemId(int position) {
        return lugpics[position];
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if  (inflater1 == null){
            inflater1 = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        }
        if (convertView == null){
            convertView = inflater1.inflate(R.layout.listlugs, null);

        }
        ImageView ivlugs = convertView.findViewById(R.id.ivlugs);
        TextView tvlugas = convertView.findViewById(R.id.tvlugs);
        TextView tvidioms = convertView.findViewById(R.id.tv_idiomas);
        TextView tvdescript = convertView.findViewById(R.id.tv_descript);


        Log.e("idioma",idiomas[position]);
        Log.e("posicion",String.valueOf(position));
        ivlugs.setImageResource(lugpics[position]);
        tvlugas.setText(lugarest[position]);
        //   tvidioms.setText(idiomas[position]);
        tvdescript.setText(descript[position]);

        return convertView;

    }
}
