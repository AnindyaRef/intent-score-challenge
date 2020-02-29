package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MatchActivity.class.getCanonicalName();

    public static final String HOMETEAM_KEY = "hometeam";
    public static final String IMAGEHOME_KEY = "imagehome";
    public static final String IMAGETEAM_KEY = "imageteam";
    public static final String AWAYTEAM_KEY = "awayteam";

    private EditText hometeamInput;
    private ImageView imagehome;
    private ImageView imageteam;
    private EditText awayteamInput;

    private Uri imageUri=null;
    private Uri imageUri2=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        hometeamInput = findViewById(R.id.home_team);
        imagehome = findViewById(R.id.home_logo);
        imageteam = findViewById(R.id.away_logo);
        awayteamInput = findViewById(R.id.away_team);
        //TODO
        //Fitur Main Activity
        //1. Validasi Input Home Team
        //2. Validasi Input Away Team
        //3. Ganti Logo Home Team
        //4. Ganti Logo Away Team
        //5. Next Button Pindah Ke MatchActivity
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==RESULT_CANCELED){
            return;
        }

        if(requestCode == 1){
            if (data != null){
                try {
                    imageUri = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageUri);
                    imagehome.setImageBitmap(bitmap);
                }catch (IOException e){
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
        if(requestCode == 2){
            if (data != null){
                try {
                    imageUri2 = data.getData();
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageUri2);
                    imageteam.setImageBitmap(bitmap);
                }catch (IOException e){
                    Toast.makeText(this, "Can't load image", Toast.LENGTH_SHORT).show();
                    Log.e(TAG, e.getMessage());
                }
            }
        }
    }

    public void handlenext(View view) {
        String hometeam = hometeamInput.getText().toString();
        String awayteam = awayteamInput.getText().toString();
        Intent intent = new Intent(this,MatchActivity.class);

        if (hometeam.isEmpty()){
            hometeamInput.setError("Please input name team!");
        }else if (awayteam.isEmpty()){
            awayteamInput.setError("Please input name team!");
        }else if (imageUri == null){
            Toast.makeText(this, "Input image please!", Toast.LENGTH_SHORT).show();
            cahngeAvatar(view);
        }else if (imageUri2 == null){
            Toast.makeText(this, "Input image please!", Toast.LENGTH_SHORT).show();
            cahngeAvatar2(view);
        }
        else{
            intent.putExtra(HOMETEAM_KEY,hometeam);
            intent.putExtra(AWAYTEAM_KEY,awayteam);
            intent.putExtra(IMAGEHOME_KEY,imageUri.toString());
            intent.putExtra(IMAGETEAM_KEY,imageUri2.toString());
            startActivity(intent);
        }
    }

    public void cahngeAvatar(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 1);
    }

    public void cahngeAvatar2(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, 2);
    }
}
