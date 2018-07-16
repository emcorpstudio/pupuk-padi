package emcorp.studio.pupukpadi;

import android.Manifest;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;

public class ProsesActivity extends AppCompatActivity {
    ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9,img10;
    private final int GALLERY_ACTIVITY_CODE=200;
    private final int RESULT_CROP = 400;
    File file;
    Uri uri;
    Intent CamIntent, GalIntent, CropIntent ;
    public  static final int RequestPermissionCode  = 1 ;
    DisplayMetrics displayMetrics ;
    int width, height;
    int imageview_num;
    String[][] role = new String [][] {
            {"2","2","2","75"},
            {"2","2","3","75"},
            {"2","2","4","50"},
            {"2","2","5","50"},
            {"2","3","2","75"},
            {"2","3","3","75"},
            {"2","3","4","50"},
            {"2","3","5","0"},
            {"2","4","2","75"},
            {"2","4","3","50"},
            {"2","4","4","0"},
            {"2","4","5","0"},
            {"2","5","2","75"},
            {"2","5","3","50"},
            {"2","5","4","0"},
            {"2","5","5","0"},
            {"3","2","2","75"},
            {"3","2","3","50"},
            {"3","2","4","50"},
            {"3","2","5","0"},
            {"3","3","2","75"},
            {"3","3","3","50"},
            {"3","3","4","50"},
            {"3","3","5","0"},
            {"3","4","2","75"},
            {"3","4","3","50"},
            {"3","4","4","0"},
            {"3","4","5","0"},
            {"3","5","2","75"},
            {"3","5","3","50"},
            {"3","5","4","0"},
            {"3","5","5","0"},
            {"4","2","2","75"},
            {"4","2","3","50"},
            {"4","2","4","0"},
            {"4","2","5","0"},
            {"4","3","2","75"},
            {"4","3","3","50"},
            {"4","3","4","0"},
            {"4","3","5","0"},
            {"4","4","2","50"},
            {"4","4","3","50"},
            {"4","4","4","0"},
            {"4","4","5","0"},
            {"4","5","2","50"},
            {"4","5","3","50"},
            {"4","5","4","0"},
            {"4","5","5","0"},
            {"5","2","2","75"},
            {"5","2","3","50"},
            {"5","2","4","50"},
            {"5","2","5","75"},
            {"5","3","2","75"},
            {"5","3","3","50"},
            {"5","3","4","0"},
            {"5","3","5","0"},
            {"5","4","2","75"},
            {"5","4","3","50"},
            {"5","4","4","0"},
            {"5","4","5","0"},
            {"5","5","2","50"},
            {"5","5","3","50"},
            {"5","5","4","0"},
            {"5","5","5","0"},
    };

    double[][] roleValue = new double [][] {
            {72,81,90},
            {88,96.5,105},
            {93,101,109},
            {98,113,128},
            {57,75,93},
            {50,59,68},
            {40,50,60},
            {30,43,56},
            {61,69.5,80},
            {50,55.5,61},
            {41,45.5,50},
            {22,31.5,41}
    };

    String paramH, paramS, paramV;
    ArrayList<Integer> roleMatch = new ArrayList<>();
    ArrayList<Double> zValue = new ArrayList<>();
    ArrayList<Double> zDosisValue = new ArrayList<>();
    double[] sumValue = new double [10];
    Button btnProses;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proses);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("PROSES KLASIFIKASI");
        EnableRuntimePermission();
        if(Build.VERSION.SDK_INT>=24){
            try{
                Method m = StrictMode.class.getMethod("disableDeathOnFileUriExposure");
                m.invoke(null);
            }catch(Exception e){
                e.printStackTrace();
            }
        }
        img1 = (ImageView)findViewById(R.id.img1);
        img2 = (ImageView)findViewById(R.id.img2);
        img3 = (ImageView)findViewById(R.id.img3);
        img4 = (ImageView)findViewById(R.id.img4);
        img5 = (ImageView)findViewById(R.id.img5);
        img6 = (ImageView)findViewById(R.id.img6);
        img7 = (ImageView)findViewById(R.id.img7);
        img8 = (ImageView)findViewById(R.id.img8);
        img9 = (ImageView)findViewById(R.id.img9);
        img10 = (ImageView)findViewById(R.id.img10);
        btnProses = (Button) findViewById(R.id.btnProses);

        for(int i=0;i<sumValue.length;i++){
            sumValue[i] = 1000000;
        }

        //https://www.android-examples.com/android-image-cropping-example-tutorial-pick-gallery-camera/
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageview_num = 1;
                dialogOpsi(imageview_num);
//                ClickImageFromCamera() ;
//                GetImageFromGallery();
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageview_num = 2;
                dialogOpsi(imageview_num);
//                GetImageFromGallery();
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageview_num = 3;
                dialogOpsi(imageview_num);
//                GetImageFromGallery();
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageview_num = 4;
                dialogOpsi(imageview_num);
//                GetImageFromGallery();
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageview_num = 5;
                dialogOpsi(imageview_num);
//                GetImageFromGallery();
            }
        });
        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageview_num = 6;
                dialogOpsi(imageview_num);
//                GetImageFromGallery();
            }
        });
        img7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageview_num = 7;
                dialogOpsi(imageview_num);
//                GetImageFromGallery();
            }
        });
        img8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageview_num = 8;
                dialogOpsi(imageview_num);
//                GetImageFromGallery();
            }
        });
        img9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageview_num = 9;
                dialogOpsi(imageview_num);
//                GetImageFromGallery();
            }
        });
        img10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageview_num = 10;
                dialogOpsi(imageview_num);
//                GetImageFromGallery();
            }
        });

        btnProses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(checkComplete()){
                    Intent i = new Intent(ProsesActivity.this,HasilActivity.class);
                    i.putExtra("nilai",String.valueOf(averageValue()));
                    startActivity(i);
                    finish();
//                    Toast.makeText(getApplicationContext(),String.valueOf(averageValue()),Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(),"Gambar belum lengkap!",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void dialogOpsi(final int number){
        final CharSequence[] options = {"Ambil Foto", "Pilih dari Galeri","Batal"};
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(ProsesActivity.this);
        builder.setTitle("Pilih Opsi");
        builder.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int item) {
                if (options[item].equals("Ambil Foto")) {
                    dialog.dismiss();
                    imageview_num = number;
                    ClickImageFromCamera() ;
//                GetImageFromGallery();
                } else if (options[item].equals("Pilih dari Galeri")) {
                    dialog.dismiss();
                    imageview_num = number;
                    GetImageFromGallery();
                } else if (options[item].equals("Batal")) {
                    dialog.dismiss();
                }
            }
        });
        builder.show();
    }

    public void ClickImageFromCamera() {

        CamIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

        file = new File(Environment.getExternalStorageDirectory(),
                "file" + String.valueOf(System.currentTimeMillis()) + ".jpg");
        uri = Uri.fromFile(file);

        CamIntent.putExtra(android.provider.MediaStore.EXTRA_OUTPUT, uri);

        CamIntent.putExtra("return-data", true);

        startActivityForResult(CamIntent, 0);

    }

    public void EnableRuntimePermission(){
        
        if (ActivityCompat.shouldShowRequestPermissionRationale(ProsesActivity.this,
                Manifest.permission.CAMERA))
        {

//            Toast.makeText(ProsesActivity.this,"CAMERA permission allows us to Access CAMERA app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(ProsesActivity.this,new String[]{
                    Manifest.permission.CAMERA}, RequestPermissionCode);

        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case RequestPermissionCode:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

//                    Toast.makeText(ProsesActivity.this,"Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(ProsesActivity.this,"Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }    

    public double averageValue(){
        double sum = 0;
        for(int i=0;i<sumValue.length;i++){
            sum = sum + sumValue[i];
            Log.d("PADI",String.valueOf(sumValue[i]));
        }
        sum = sum/10;
        return sum;
    }

    public boolean checkComplete(){
        boolean complete = true;
        for(int i=0;i<sumValue.length;i++){
            if(sumValue[i]==1000000){
                complete = false;
                break;
            }
        }
        return complete;
    }

    public void GetImageFromGallery(){

        GalIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(Intent.createChooser(GalIntent, "Select Image From Gallery"), 2);

    }

    public void GetImageFromCamera(){

        Intent GalIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File pics = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        File cameraPhotoFile = new File(pics, System.currentTimeMillis()+".jpg");
        GalIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(cameraPhotoFile));
        startActivityForResult(GalIntent, 2);


//        GalIntent = new Intent(Intent.ACTION_PICK,
//                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
//
//        startActivityForResult(Intent.createChooser(GalIntent, "Select Image From Camera"), 2);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == RESULT_OK) {
            ImageCropFunction();
        }
        else if (requestCode == 2) {
            if (data != null) {
                uri = data.getData();
                ImageCropFunction();
            }
        }
        else if (requestCode == 1) {
            if (data != null) {
                Bundle bundle = data.getExtras();
                Bitmap bitmap = bundle.getParcelable("data");
                switch (imageview_num){
                    case 1 :
                        img1.setImageBitmap(bitmap);
                        getRgbValue(img1);
                        break;
                    case 2 :
                        img2.setImageBitmap(bitmap);
                        getRgbValue(img2);
                        break;
                    case 3 :
                        img3.setImageBitmap(bitmap);
                        getRgbValue(img3);
                        break;
                    case 4 :
                        img4.setImageBitmap(bitmap);
                        getRgbValue(img4);
                        break;
                    case 5 :
                        img5.setImageBitmap(bitmap);
                        getRgbValue(img5);
                        break;
                    case 6 :
                        img6.setImageBitmap(bitmap);
                        getRgbValue(img6);
                        break;
                    case 7 :
                        img7.setImageBitmap(bitmap);
                        getRgbValue(img7);
                        break;
                    case 8 :
                        img8.setImageBitmap(bitmap);
                        getRgbValue(img8);
                        break;
                    case 9 :
                        img9.setImageBitmap(bitmap);
                        getRgbValue(img9);
                        break;
                    case 10 :
                        img10.setImageBitmap(bitmap);
                        getRgbValue(img10);
                        break;
                }
//                img1.setImageBitmap(bitmap);
            }
        }
    }

    public void getRgbValue(ImageView img){
        BitmapDrawable drawable = (BitmapDrawable) img.getDrawable();
        Bitmap bmp = drawable.getBitmap();
        int[][] rgbValues;
        //http://www.41post.com/3719/programming/android-how-to-return-rgb-values-from-an-image-file
        rgbValues = new int[bmp.getWidth()][bmp.getHeight()];
        float sumH = 0;
        float sumS = 0;
        float sumV = 0;
        int count = 0;
        for(int i=0; i < bmp.getWidth(); i++)
        {
            for(int j=0; j < bmp.getHeight(); j++)
            {
                //This is a great opportunity to filter the ARGB values
                int r = Color.red(bmp.getPixel(i, j));
                int g = Color.green(bmp.getPixel(i, j));
                int b = Color.blue(bmp.getPixel(i, j));
                float[] hsv = new float[3];
                Color.RGBToHSV(r,g,b,hsv);
                sumH = sumH + hsv[0];
                sumS = sumS + (hsv[1]*100);
                sumV = sumV + (hsv[2]*100);
//                sumH = sumH + r;
//                sumS = sumS + g;
//                sumV = sumV + b;
                count = count + 1;
//                calculateFuzzy(hsv[0],hsv[1],hsv[2]);
//                rgbValues[i][j] = bmp.getPixel(i, j);
                String rgbTeks = String.valueOf(Color.red(bmp.getPixel(i, j)))+","+Color.green(bmp.getPixel(i, j))+","+Color.blue(bmp.getPixel(i, j));
                String hsvTeks = String.valueOf(hsv[0])+","+String.valueOf(hsv[1])+","+String.valueOf(hsv[2]);
                Log.i("RGB",String.valueOf(i)+","+String.valueOf(j)+":"+ bmp.getPixel(i, j)+" -> "+rgbTeks+" -> "+hsvTeks);
            }
        }
        sumH = sumH/count;
        sumS = sumS/count;
        sumV = sumV/count;
        int sumHint = (int)sumH;
        int sumSint = (int)sumS;
        int sumVint = (int)sumV;
        calculateFuzzy(Double.valueOf(sumHint),Double.valueOf(sumSint),Double.valueOf(sumVint));
        Log.i("RGB",String.valueOf(sumHint)+","+String.valueOf(sumSint)+","+String.valueOf(sumVint));
    }

    public void ImageCropFunction() {

        // Image Crop Code
        try {
            CropIntent = new Intent("com.android.camera.action.CROP");

            CropIntent.setDataAndType(uri, "image/*");

            CropIntent.putExtra("crop", "true");
            CropIntent.putExtra("outputX", 280);
            CropIntent.putExtra("outputY", 280);
            CropIntent.putExtra("aspectX", 3);
            CropIntent.putExtra("aspectY", 3);
            CropIntent.putExtra("scaleUpIfNeeded", true);
            CropIntent.putExtra("return-data", true);

            startActivityForResult(CropIntent, 1);

        } catch (ActivityNotFoundException e) {

        }
    }

    public void calculateFuzzy(double h, double s, double v){
        paramH = get_parameterH(h);
        paramS = get_parameterS(s);
        paramV = get_parameterV(v);
        System.out.println("HSV PARAMETER = "+paramH+" | "+paramS+" | "+paramV);
        cekRole();
        processRole(h,s,v);
        sumValue[imageview_num-1] = defuzzifikasi();
    }

    public double defuzzifikasi(){
        double sumTop = 0;
        double supBottom = 0;
        for(int i=0;i<zDosisValue.size();i++){
            sumTop = sumTop + zDosisValue.get(i);
        }
        for(int i=0;i<zValue.size();i++){
            supBottom = supBottom + zValue.get(i);
        }
        double result = sumTop / supBottom;
        System.out.println("sumTop = "+String.valueOf(sumTop)+" / "+String.valueOf(supBottom)+" = "+String.valueOf(result));
        return result;
    }

    public void processRole(double h, double s, double v){
        zValue.clear();
        zDosisValue.clear();
        for(int i=0;i<roleMatch.size();i++){
            double[] abcH = new double []{};
            double[] abcS = new double []{};
            double[] abcV = new double []{};

            switch (role[i][0]){
                case "2" :
                    abcH = roleValue[0];
                    break;
                case "3" :
                    abcH = roleValue[1];
                    break;
                case "4" :
                    abcH = roleValue[2];
                    break;
                case "5" :
                    abcH = roleValue[3];
                    break;
            }

            switch (role[i][1]){
                case "2" :
                    abcS = roleValue[4];
                    break;
                case "3" :
                    abcS = roleValue[5];
                    break;
                case "4" :
                    abcS = roleValue[6];
                    break;
                case "5" :
                    abcS = roleValue[7];
                    break;
            }

            switch (role[i][2]){
                case "2" :
                    abcV = roleValue[8];
                    break;
                case "3" :
                    abcV = roleValue[9];
                    break;
                case "4" :
                    abcV = roleValue[10];
                    break;
                case "5" :
                    abcV = roleValue[11];
                    break;
            }

            //zH
            double zH = 0;
            if((abcH[1]<h)&&(h<=abcH[2])){
                zH = (abcH[2]-h)/(abcH[2]-abcH[1]);
            }else if((abcH[0]<h)&&(h<=abcH[1])){
                zH = (h-abcH[0])/(abcH[1]-abcH[0]);
            }else{
                zH = (abcH[2]-h)/(abcH[2]-abcH[1]);
            }
            //zS
            double zS = 0;
            if((abcS[1]<s)&&(s<=abcS[2])){
                zS = (abcS[2]-s)/(abcS[2]-abcS[1]);
            }else if((abcS[0]<s)&&(s<=abcS[1])){
                zS = (s-abcS[0])/(abcS[1]-abcS[0]);
            }else{
                zS = (abcS[2]-s)/(abcS[2]-abcS[1]);
            }
            //zV
            double zV = 0;
            if((abcV[1]<v)&&(v<=abcV[2])){
                zV = (abcV[2]-v)/(abcV[2]-abcV[1]);
            }else if((abcV[0]<v)&&(v<=abcV[1])){
                zV = (v-abcV[0])/(abcV[1]-abcV[0]);
            }else{
                zV = (abcV[2]-v)/(abcV[2]-abcV[1]);
            }
            double zArray[] = {zH, zS, zV};
            Arrays.sort(zArray);
            double min = zArray[0];

            double dosis = Double.valueOf(role[roleMatch.get(i)][3]);
            zValue.add(min);
            zDosisValue.add(min*dosis);
            System.out.println("zH : "+String.valueOf(zH)+" zS : "+String.valueOf(zS)+" zV : "+String.valueOf(zV)+" Minimum : "+min+" Dosis : "+dosis);
        }
    }

    public void cekRole(){
        roleMatch.clear();
        for(int i=0;i<role.length;i++){
            char sourceH = role[i][0].charAt(0);
            char sourceS = role[i][1].charAt(0);
            char sourceV = role[i][2].charAt(0);
            if(paramH.indexOf(sourceH)>=0&&paramS.indexOf(sourceS)>=0&&paramV.indexOf(sourceV)>=0){
                roleMatch.add(i);
                System.out.println("ROLE = "+String.valueOf(i));
            }
        }
    }

    public String get_parameterH(double h){
        String result = "";
        if(h<72)result = result + "2,";
        if(h>=72&&h<=90)result = result + "2,";
        if(h>=88&&h<=105)result = result + "3,";
        if(h>=93&&h<=109)result = result + "4,";
        if(h>=98&&h<=128)result = result + "5,";
        if(h>128)result = result + "5,";
        return result;
    }

    public static String get_parameterS(double s){
        String result = "";
        if(s>=57&&s<=93)result = result + "2,";
        if(s>=50&&s<=68)result = result + "3,";
        if(s>=40&&s<=60)result = result + "4,";
        if(s>=30&&s<=56)result = result + "5,";
        if(s>93)result = result + "2,";
        if(s<30)result = result + "5,";
        return result;
    }

    public String get_parameterV(double v){
        String result = "";
        if(v>=61&&v<=80)result = result + "2,";
        if(v>=50&&v<=61)result = result + "3,";
        if(v>=41&&v<=50)result = result + "4,";
        if(v>=22&&v<=41)result = result + "5,";
        if(v<22)result = result + "5,";
        if(v>80)result = result + "2,";
        return result;
    }
    //Image Crop Code End Here

    /*
    public void EnableRuntimePermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(ProsesActivity.this,
                Manifest.permission.CAMERA))
        {

            Toast.makeText(ProsesActivity.this,"CAMERA permission allows us to Access CAMERA app", Toast.LENGTH_LONG).show();

        } else {

            ActivityCompat.requestPermissions(ProsesActivity.this,new String[]{
                    Manifest.permission.CAMERA}, RequestPermissionCode);

        }
    }

    @Override
    public void onRequestPermissionsResult(int RC, String per[], int[] PResult) {

        switch (RC) {

            case RequestPermissionCode:

                if (PResult.length > 0 && PResult[0] == PackageManager.PERMISSION_GRANTED) {

                    Toast.makeText(ProsesActivity.this,"Permission Granted, Now your application can access CAMERA.", Toast.LENGTH_LONG).show();

                } else {

                    Toast.makeText(ProsesActivity.this,"Permission Canceled, Now your application cannot access CAMERA.", Toast.LENGTH_LONG).show();

                }
                break;
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERY_ACTIVITY_CODE) {
            if(resultCode == Activity.RESULT_OK){
                picturePath = data.getStringExtra("picturePath");
                //perform Crop on the Image Selected from Gallery
                performCrop(picturePath);
            }
        }

        if (requestCode == RESULT_CROP ) {
            if(resultCode == Activity.RESULT_OK){
                Bundle extras = data.getExtras();
                Bitmap selectedBitmap = extras.getParcelable("data");
                // Set The Bitmap Data To ImageView
                img1.setImageBitmap(selectedBitmap);
                img1.setScaleType(ImageView.ScaleType.FIT_XY);
            }
        }
    }

    private void performCrop(String picUri) {
        try {
            //Start Crop Activity

            Intent cropIntent = new Intent("com.android.camera.action.CROP");
            // indicate image type and Uri
            File f = new File(picUri);
            Uri contentUri = Uri.fromFile(f);

            cropIntent.setDataAndType(contentUri, "image/*");
            // set crop properties
            cropIntent.putExtra("crop", "true");
            // indicate aspect of desired crop
            cropIntent.putExtra("aspectX", 1);
            cropIntent.putExtra("aspectY", 1);
            // indicate output X and Y
            cropIntent.putExtra("outputX", 280);
            cropIntent.putExtra("outputY", 280);

            // retrieve data on return
            cropIntent.putExtra("return-data", true);
            // start the activity - we handle returning in onActivityResult
            cropIntent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
            startActivityForResult(cropIntent, RESULT_CROP);
        }
        // respond to users whose devices do not support the crop action
        catch (ActivityNotFoundException anfe) {
            // display an error message
            String errorMessage = "your device doesn't support the crop action!";
            Toast toast = Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT);
            toast.show();
        }
    }
    */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        android.app.AlertDialog.Builder builder = new android.app.AlertDialog.Builder(ProsesActivity.this);
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(ProsesActivity.this,MainActivity.class);
                startActivity(i);
                finish();
                break;
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(ProsesActivity.this,MainActivity.class);
        startActivity(i);
        finish();
    }
}
