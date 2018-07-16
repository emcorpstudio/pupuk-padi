package emcorp.studio.pupukpadi;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import emcorp.studio.pupukpadi.Library.DBHandler;
import emcorp.studio.pupukpadi.Library.DataPupuk;

public class HasilActivity extends AppCompatActivity {
    Button btnHitung, btnUlangi, btnSimpan;
    EditText edtPanjang, edtLebar;
    TextView tvScore;
    double nilai = 0;
    double hasilAkhir = 0;
    DBHandler dbHandler;
    double luas = 0;
    double hektar = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hasil);
        Bundle extras = getIntent().getExtras();
        if(extras!=null){
            nilai = Double.valueOf(extras.getString("nilai"));
        }
//        nilai = 100;
        dbHandler = new DBHandler(HasilActivity.this, null, null, 1);
        btnHitung = (Button)findViewById(R.id.btnHitung);
        btnUlangi = (Button)findViewById(R.id.btnUlangi);
        btnSimpan = (Button)findViewById(R.id.btnSimpan);
        edtPanjang = (EditText)findViewById(R.id.edtPanjang);
        edtLebar = (EditText)findViewById(R.id.edtLebar);
        tvScore = (TextView)findViewById(R.id.tvScore);

        btnHitung.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                luas = (Double.valueOf(edtPanjang.getText().toString())) * (Double.valueOf(edtLebar.getText().toString()));
                hektar = luas / 10000;
                hasilAkhir = nilai * hektar;
                //;
                tvScore.setText(String.format("%.2f", hasilAkhir)+" KG");
            }
        });

        btnUlangi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(HasilActivity.this,ProsesActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String datenow = (DateFormat.format("dd/MM/yyyy", new java.util.Date()).toString());
                DataPupuk data = new DataPupuk(
                        edtPanjang.getText().toString(),
                        edtLebar.getText().toString(),
                        String.valueOf(luas),
                        String.valueOf(hektar),
                        String.valueOf(nilai),
                        String.valueOf(hasilAkhir),
                        datenow
                        );
                dbHandler.addData(data);
                Intent i = new Intent(HasilActivity.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }
}
