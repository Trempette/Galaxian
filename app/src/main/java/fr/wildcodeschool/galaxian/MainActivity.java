package fr.wildcodeschool.galaxian;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final EditText TextScore = findViewById(R.id.score);
        final EditText TextName = findViewById(R.id.name);
        Button sendScore = findViewById(R.id.sendScore);
        Button highscores = findViewById(R.id.all_highscores);

        highscores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, RankingActivity.class));
            }
        });

        theBestPlayer();

        sendScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(TextName.getText().toString().isEmpty() || TextScore.getText().toString().isEmpty()) {
                    Toast.makeText(MainActivity.this,"il manque un élément bâtard", Toast.LENGTH_LONG).show();
                }
                else{
                    String name = TextName.getText().toString();
                    int score = Integer.valueOf(TextScore.getText().toString());
                    ScoreModel scoreModel = new ScoreModel(name, score);
                    // Write a message to the database

                    DatabaseReference scoreRef = database.getReference("HighScore");
                    String highScoreKey = scoreRef.push().getKey();
                    scoreRef.child(highScoreKey).setValue(scoreModel);
                    theBestPlayer();
                    TextName.setText("");
                    TextScore.setText("");
                }
            }
        });
    }

    public void theBestPlayer(){
        final DatabaseReference scoreRef = database.getReference("HighScore");
        scoreRef.orderByChild("score").limitToLast(1).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                TextView bestPlayer = findViewById(R.id.bestPlayer);
                TextView bestScore = findViewById(R.id.bestScore);

                for (DataSnapshot scoreSnapshot : dataSnapshot.getChildren()) {
                    ScoreModel scoreData =
                            scoreSnapshot.getValue(ScoreModel.class);
                    bestPlayer.setText(scoreData.getName());
                    bestScore.setText(String.valueOf(scoreData.getScore()));
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
