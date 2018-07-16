package emcorp.studio.pupukpadi;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import emcorp.studio.pupukpadi.Library.Constant;
import emcorp.studio.pupukpadi.Library.DBHandler;
import emcorp.studio.pupukpadi.Library.DataAdapter;

public class HasilKlasifikasiActivity extends AppCompatActivity {
    DBHandler dbHandler;
    Cursor Csr;
    ListView list;
    List<String> listDATA_ID = new ArrayList<String>();
    List<String> listDATA_PANJANG = new ArrayList<String>();
    List<String> listDATA_LEBAR = new ArrayList<String>();
    List<String> listDATA_LUAS = new ArrayList<String>();
    List<String> listDATA_HEKTAR = new ArrayList<String>();
    List<String> listDATA_FUZZY = new ArrayList<String>();
    List<String> listDATA_PUPUK = new ArrayList<String>();
    List<String> listDATA_TANGGAL = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil_klasifikasi);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("HASIL KLASIFIKASI");
        dbHandler = new DBHandler(HasilKlasifikasiActivity.this, null, null, 1);
        list = (ListView)findViewById(R.id.listView);
        getAllData();

    }

    public void getAllData(){
        Csr = dbHandler.selectAllTable(Constant.TABLE_DATA, Constant.DATA_ID);
        if(Csr.getCount()>0){
            Csr.moveToFirst();
            listDATA_ID.clear();
            listDATA_PANJANG.clear();
            listDATA_LEBAR.clear();
            listDATA_LUAS.clear();
            listDATA_HEKTAR.clear();
            listDATA_FUZZY.clear();
            listDATA_PUPUK.clear();
            listDATA_TANGGAL.clear();
            while(Csr.isAfterLast()==false){
                listDATA_ID.add(Csr.getString(0));
                listDATA_PANJANG.add(Csr.getString(1));
                listDATA_LEBAR.add(Csr.getString(2));
                listDATA_LUAS.add(Csr.getString(3));
                listDATA_HEKTAR.add(Csr.getString(4));
                listDATA_FUZZY.add(Csr.getString(5));
                listDATA_PUPUK.add(Csr.getString(6));
                listDATA_TANGGAL.add(Csr.getString(7));
                Csr.moveToNext();
            }
            list.setAdapter(null);
            DataAdapter adapter = new DataAdapter(HasilKlasifikasiActivity.this, listDATA_PANJANG,listDATA_LEBAR,listDATA_LUAS,listDATA_HEKTAR,listDATA_FUZZY,listDATA_PUPUK,listDATA_TANGGAL);
            list.setAdapter(adapter);
            list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(getApplicationContext(),listDATA_ID.get(position),Toast.LENGTH_SHORT).show();
                }
            });
            list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
//                dialogAdd(position);
                    return true;
                }
            });
        }else{
            Toast.makeText(getApplicationContext(),"Data tidak ada !",Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(HasilKlasifikasiActivity.this);
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(HasilKlasifikasiActivity.this,MainActivity.class);
                startActivity(i);
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(HasilKlasifikasiActivity.this,MainActivity.class);
        startActivity(i);
        finish();
    }
}
