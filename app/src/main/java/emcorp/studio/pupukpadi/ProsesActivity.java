package emcorp.studio.pupukpadi;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import java.io.File;

public class ProsesActivity extends AppCompatActivity {
    ImageView img1,img2,img3,img4,img5,img6,img7,img8,img9,img10;
    private final int GALLERY_ACTIVITY_CODE=200;
    private final int RESULT_CROP = 400;
    File file;
    Uri uri;
    Intent GalIntent, CropIntent ;
    public  static final int RequestPermissionCode  = 1 ;
    DisplayMetrics displayMetrics ;
    int width, height;
    int imageview_num;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proses);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setTitle("PROSES KLASIFIKASI");
//        EnableRuntimePermission();
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
        //https://www.android-examples.com/android-image-cropping-example-tutorial-pick-gallery-camera/
        img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageview_num = 1;
                GetImageFromGallery();
            }
        });
        img2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageview_num = 2;
                GetImageFromGallery();
            }
        });
        img3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageview_num = 3;
                GetImageFromGallery();
            }
        });
        img4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageview_num = 4;
                GetImageFromGallery();
            }
        });
        img5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageview_num = 5;
                GetImageFromGallery();
            }
        });
        img6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageview_num = 6;
                GetImageFromGallery();
            }
        });
        img7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageview_num = 7;
                GetImageFromGallery();
            }
        });
        img8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageview_num = 8;
                GetImageFromGallery();
            }
        });
        img9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageview_num = 9;
                GetImageFromGallery();
            }
        });
        img10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                imageview_num = 10;
                GetImageFromGallery();
            }
        });
    }

    public void GetImageFromGallery(){

        GalIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

        startActivityForResult(Intent.createChooser(GalIntent, "Select Image From Gallery"), 2);

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
//                rgbValues[i][j] = bmp.getPixel(i, j);
                String rgbTeks = String.valueOf(Color.red(bmp.getPixel(i, j)))+","+Color.green(bmp.getPixel(i, j))+","+Color.blue(bmp.getPixel(i, j));
                String hsvTeks = String.valueOf(hsv[0])+","+String.valueOf(hsv[1])+","+String.valueOf(hsv[2]);
                Log.i("RGB",String.valueOf(i)+","+String.valueOf(j)+":"+ bmp.getPixel(i, j)+" -> "+rgbTeks+" -> "+hsvTeks);
            }
        }
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
