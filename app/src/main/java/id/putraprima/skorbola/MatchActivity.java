package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.IOException;

import static id.putraprima.skorbola.MainActivity.AWAYTEAM_KEY;
import static id.putraprima.skorbola.MainActivity.HOMETEAM_KEY;
import static id.putraprima.skorbola.MainActivity.IMAGEHOME_KEY;
import static id.putraprima.skorbola.MainActivity.IMAGETEAM_KEY;

public class MatchActivity extends AppCompatActivity {

    public static final String KET1_KEY = "ket1";
    public static final String KET2_KEY = "ket2";

    private TextView hometext;
    private ImageView homeImage;
    private ImageView teamImage;
    private TextView teamtext;
    private TextView homeScore;
    private TextView teamScore;
    private TextView nameHome;
    private TextView nameAway;
    private Uri uri2;
    private Uri uri;

    int scoreA =0;
    int scoreB =0;

    private String namePlayer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        hometext = findViewById(R.id.txt_home);
        homeImage = findViewById(R.id.home_logo);
        homeScore = findViewById(R.id.score_home);
        teamImage = findViewById(R.id.away_logo);
        teamScore = findViewById(R.id.score_away);
        teamtext = findViewById(R.id.txt_away);
        nameHome= findViewById(R.id.score_name_house);
        nameAway = findViewById(R.id.score_name_away);


        Bundle extrass = getIntent().getExtras();
        if (extrass != null) {
            hometext.setText(extrass.getString(HOMETEAM_KEY));
            uri = Uri.parse(extrass.getString(IMAGEHOME_KEY));
            uri2 = Uri.parse(extrass.getString(IMAGETEAM_KEY));
            teamtext.setText(extrass.getString(AWAYTEAM_KEY));
            Bitmap bitmap = null;
            Bitmap bitmap2 = null;
            try {
                bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri);
                bitmap2 = MediaStore.Images.Media.getBitmap(this.getContentResolver(), uri2);
            } catch (IOException e) {
                e.printStackTrace();
            }
            homeImage.setImageBitmap(bitmap);
            teamImage.setImageBitmap(bitmap2);
            //TODO
            //1.Menampilkan detail match sesuai data dari main activity
            //2.Tombol add score menambahkan memindah activity ke scorerActivity dimana pada scorer activity di isikan nama pencetak gol
            //3.Dari activity scorer akan mengirim kembali ke activity matchactivity otomatis nama pencetak gol dan skor bertambah +1
            //4.Tombol Cek Result menghitung pemenang dari kedua tim dan mengirim nama pemenang beserta nama pencetak gol ke ResultActivity, jika seri di kirim text "Draw",
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if(resultCode == Activity.RESULT_OK){
                scoreA++;
                homeScore.setText(String.valueOf(scoreA));

                String returnString = data.getStringExtra("SCORE_KEY");
                namePlayer = returnString + "\n"+ nameHome.getText().toString();
                nameHome.setText(namePlayer);
            }
        }
        if(requestCode == 2){
            if(resultCode == Activity.RESULT_OK){
                scoreB++;
                teamScore.setText(String.valueOf(scoreB));

                String returnString = data.getStringExtra("SCORE_KEY");
                namePlayer = returnString + "\n"+ nameAway.getText().toString();
                nameAway.setText(returnString);
            }
        }
    }

    public void handleHome(View view) {
        Intent intent = new Intent(this, ScorerActivity.class);
        startActivityForResult(intent,1);
    }

    public void handleAway(View view) {
        Intent intent = new Intent(this, ScorerActivity.class);
        startActivityForResult(intent,2);
    }

    public void handleCek(View view) {
        String ket1 = null;
        String ket2 = null;
        if (scoreA>scoreB){
            ket1 = "Name of Winning is "+hometext.getText().toString();
            ket2 = "Name of Player are "+nameHome.getText().toString();
        }else  if (scoreB>scoreA){
            ket1 = "Name of Winning is "+teamtext.getText().toString();
            ket2 = "Name of Player are"+nameAway.getText().toString();
        }else if (scoreA==scoreB){
            ket1 = "Name of Winning is Draw";
        }
        Intent intent = new Intent(this, ResultActivity.class);
        intent.putExtra(KET1_KEY, ket1);
        intent.putExtra(KET2_KEY,ket2);
        startActivity(intent);
    }
}


