package com.sanatandigitizers.plustworoomsadmin.activity;
        import android.content.CursorLoader;
        import android.content.Intent;
        import android.database.Cursor;
        import android.graphics.Bitmap;
        import android.graphics.BitmapFactory;
        import android.net.Uri;
        import android.provider.MediaStore;
        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import android.util.Base64;
        import android.view.View;
        import android.widget.Button;
        import android.widget.ImageView;
        import android.widget.TextView;
        import android.widget.Toast;
        import com.sanatandigitizers.plustworoomsadmin.R;
        import com.sanatandigitizers.plustworoomsadmin.model.Room;
        import org.parceler.Parcels;
        import java.io.ByteArrayOutputStream;
        import java.io.File;
        import java.io.FileInputStream;
        import java.io.FileNotFoundException;
        import okhttp3.MultipartBody;


public class AddImageActivity extends AppCompatActivity {


    private TextView roomidTv;
    private Button selectImageBtn,uploadImageBtn;
    private ImageView selectImageIV;

    private static final int SELECT_PHOTO = 100;
    Uri selectedImage;
    private FileInputStream fis;
    private Room room;
    File imagePath;
    MultipartBody.Part filePart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_image);


        roomidTv = (TextView)findViewById(R.id.roomid_tv);
        selectImageIV = (ImageView)findViewById(R.id.setimage_iv);
        selectImageBtn = (Button)findViewById(R.id.select_image_btn);
        uploadImageBtn = (Button)findViewById(R.id.upload_image_btn);

        uploadImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        if(room!=null) {
            room = Parcels.unwrap(getIntent().getParcelableExtra("Room"));
            roomidTv.setText(room.getId());
        }
        selectImageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectImage(v);
            }
        });

    }

    private void selectImage(View v) {
        Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
        photoPickerIntent.setType("image/*");
        startActivityForResult(photoPickerIntent, SELECT_PHOTO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case SELECT_PHOTO:
                if (resultCode == RESULT_OK) {
                    Toast.makeText(AddImageActivity.this, "Image selected, click on upload button", Toast.LENGTH_SHORT).show();

                    selectedImage = imageReturnedIntent.getData();
                    imagePath = new File(getRealPathFromURI(selectedImage));
                    if (imagePath.length() <= 1048576) {
                        try {
                            fis = new FileInputStream(imagePath);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }
                        Toast.makeText(AddImageActivity.this, "" + fis, Toast.LENGTH_SHORT).show();

                        Bitmap bm = BitmapFactory.decodeStream(fis);
                        selectImageIV.setImageBitmap(bm);
                        ByteArrayOutputStream baos = new ByteArrayOutputStream();
                        bm.compress(Bitmap.CompressFormat.JPEG, 100, baos);
                        byte[] b = baos.toByteArray();


                        String encImage = Base64.encodeToString(b, Base64.DEFAULT);
                        Toast.makeText(AddImageActivity.this, "" + encImage, Toast.LENGTH_SHORT).show();


                    }else{
                        Toast.makeText(AddImageActivity.this,"Image  size is too large please sleect 1 Mb or less  ",Toast.LENGTH_SHORT).show();
                    }
                }
        }
    }

    private String getRealPathFromURI(Uri contentUri) {
        String[] proj = { MediaStore.Images.Media.DATA };
        CursorLoader loader = new CursorLoader(AddImageActivity.this, contentUri, proj, null, null, null);
        Cursor cursor = loader.loadInBackground();
        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
        cursor.moveToFirst();
        String result = cursor.getString(column_index);
        cursor.close();
        return result;
    }
}
