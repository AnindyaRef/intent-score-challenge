package id.putraprima.skorbola;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ScorerActivity extends AppCompatActivity {

    public static final String SCORE_KEY = "score";

    private EditText scoreText;



    int scoreA =0;
    int scoreB =0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorer);
        scoreText = findViewById(R.id.editText);

    }

    public void handleScroe(View view) {

        String message = scoreText.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("SCORE_KEY", message);
        setResult(RESULT_OK, intent);
        finish();
    }
}
