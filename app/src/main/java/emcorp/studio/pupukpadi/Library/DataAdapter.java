package emcorp.studio.pupukpadi.Library;

/**
 * Created by ASUS on 27/11/2015.
 */

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import emcorp.studio.pupukpadi.R;

public class DataAdapter extends ArrayAdapter<String> {
    private final Activity context;
    List<String> listDATA_PANJANG = new ArrayList<String>();
    List<String> listDATA_LEBAR = new ArrayList<String>();
    List<String> listDATA_LUAS = new ArrayList<String>();
    List<String> listDATA_HEKTAR = new ArrayList<String>();
    List<String> listDATA_FUZZY = new ArrayList<String>();
    List<String> listDATA_PUPUK = new ArrayList<String>();
    List<String> listDATA_TANGGAL = new ArrayList<String>();
    public DataAdapter(Activity context,
                       List<String> listDATA_PANJANG,List<String> listDATA_LEBAR,List<String> listDATA_LUAS,List<String> listDATA_HEKTAR,List<String> listDATA_FUZZY,List<String> listDATA_PUPUK,List<String> listDATA_TANGGAL) {
        super(context, R.layout.hasil_list, listDATA_PANJANG);
        this.context = context;
        this.listDATA_PANJANG = listDATA_PANJANG;
        this.listDATA_LEBAR = listDATA_LEBAR;
        this.listDATA_LUAS = listDATA_LUAS;
        this.listDATA_HEKTAR = listDATA_HEKTAR;
        this.listDATA_FUZZY = listDATA_FUZZY;
        this.listDATA_PUPUK = listDATA_PUPUK;
        this.listDATA_TANGGAL = listDATA_TANGGAL;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.hasil_list, null, true);
        TextView tvTgl = (TextView) rowView.findViewById(R.id.tvTgl);
        TextView tvLahan = (TextView) rowView.findViewById(R.id.tvLahan);
        TextView tvNilai = (TextView) rowView.findViewById(R.id.tvNilai);
        TextView tvPupuk = (TextView) rowView.findViewById(R.id.tvPupuk);
        tvTgl.setText(listDATA_TANGGAL.get(position));
        tvLahan.setText(listDATA_PANJANG.get(position)+" m X "+listDATA_LEBAR.get(position)+" = "+listDATA_LUAS.get(position)+" m2 / "+listDATA_HEKTAR.get(position)+" Ha");
        tvNilai.setText("Fuzzy : " + listDATA_FUZZY.get(position));
        tvPupuk.setText("Pupuk : " + listDATA_PUPUK.get(position)+" Kg");
        return rowView;
    }


}