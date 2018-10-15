package fr.wildcodeschool.galaxian;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class RankingActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);
        final FirebaseDatabase database = FirebaseDatabase.getInstance();

        ListView ranking = findViewById(R.id.ranking_player);
        final ArrayList<ScoreModel> results = new ArrayList<>();
        final ArrayList<ScoreModel> resultsFinal = new ArrayList<>();
        final RankingAdapter adapter = new RankingAdapter(this, resultsFinal);
        final DatabaseReference scoreRef = database.getReference("HighScore");
        scoreRef.orderByChild("score").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                resultsFinal.clear();
                results.clear();
                for(DataSnapshot scoreSnapshot : dataSnapshot.getChildren()){
                        results.add(scoreSnapshot.getValue(ScoreModel.class));
                }

                for (int i = 1; i<=results.size(); i++){
                    resultsFinal.add(results.get(results.size()-i));
                }

                adapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        ranking.setAdapter(adapter);

    }
}

