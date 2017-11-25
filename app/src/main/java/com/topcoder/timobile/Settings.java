package com.topcoder.timobile;

import android.app.AlertDialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.topcoder.timobile.profile.Profile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Settings extends AppCompatActivity {

    final int REQ_CODE_PICK_IMAGE = 1;
    ImageView UserImage;
    TextView changeImage;
    private String profileLocation;
    final String PROFILE_IMAGE_LOCATTION ="jdhffuhd";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    public static final int PICK_IMAGE_REQUEST = 1;
    static final int REQUEST_TAKE_PHOTO= 13;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        preferences = getSharedPreferences("myprefs",MODE_PRIVATE);
        editor = preferences.edit();


        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbarSettings);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setTitle("");


        UserImage = (ImageView) findViewById(R.id.ImageViewSettings);
        changeImage=(TextView)findViewById(R.id.change_image);
        loadImageFromStorage(preferences.getString(PROFILE_IMAGE_LOCATTION,""));
        changeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               showImagePickerDialog();
            }
        });

        findViewById(R.id.backButtonSettings).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplication(), Profile.class));
                finish();
            }
        });

        final Button edit = (Button) findViewById(R.id.EditButtonSettings);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (edit.getText().equals("Edit"))
                    edit.setText("Save");
                else
                    edit.setText("Edit");
            }
        });
    }


    private void showImagePickerDialog(){
        // 1. Instantiate an AlertDialog.Builder with its constructor
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);

// 2. Chain together various setter methods to set the dialog characteristics
        builder.setTitle(R.string.dialog_title);
        builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                // User cancelled the dialog
            }
        });

//        builder.setView(R.layout.custom_dialog);
        View v =getLayoutInflater().inflate(R.layout.custom_dialog_imagepicker, null);
        builder.setView(v);
// 3. Get the AlertDialog from create()
        final AlertDialog dialog = builder.create();
        dialog.show();

        Button mGalleryPhoto=(Button)dialog.findViewById(R.id.gallery_photo_button);
        mGalleryPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "Select Picture"), PICK_IMAGE_REQUEST);

            }
        });
        Button mCameraPhoto=(Button)dialog.findViewById(R.id.camera_photo_button);
        mCameraPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                galleryAddPic();
                Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(cameraIntent,REQUEST_TAKE_PHOTO);

                dialog.dismiss();

            }
        });

    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Bitmap bitmap;

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            //TODO: action
            Uri uri = data.getData();

            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), uri);
                // Log.d(TAG, String.valueOf(bitmap));
                profileLocation = saveToInternalStorage(bitmap);
                editor.putString(PROFILE_IMAGE_LOCATTION,profileLocation);
                editor.commit();

                UserImage.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (requestCode == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK) {

            Bundle extras = data.getExtras();
            bitmap = (Bitmap) extras.get("data");
            UserImage.setImageBitmap(bitmap);
            // grantUriPermission();
            //saveImageToExternalStorage(imageBitmap);
            profileLocation = saveToInternalStorage(bitmap);
            editor.putString(PROFILE_IMAGE_LOCATTION,profileLocation);
            editor.commit();


        }

    }





    private String saveToInternalStorage(Bitmap bitmapImage){
        ContextWrapper cw = new ContextWrapper(getApplicationContext());
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("imageDir", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,"profile.jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.PNG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return directory.getAbsolutePath();
    }
    private void loadImageFromStorage(String path)
    {

        try {
            File f=new File(path, "profile.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            UserImage.setImageBitmap(b);
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

    }
}
