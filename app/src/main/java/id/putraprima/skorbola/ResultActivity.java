package id.putraprima.skorbola;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import static id.putraprima.skorbola.MatchActivity.KET1_KEY;
import static id.putraprima.skorbola.MatchActivity.KET2_KEY;

public class ResultActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView winnerTeam = (TextView) findViewById(R.id.textView3);
        TextView winnerPlayer = (TextView) findViewById(R.id.textView4);


        Bundle extras = getIntent().getExtras();
        if (extras != null){
            winnerTeam.setText(extras.getString(KET1_KEY));
            winnerPlayer.setText(extras.getString(KET2_KEY));
        }
    }
}
