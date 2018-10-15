package fr.wildcodeschool.galaxian;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class RankingAdapter extends ArrayAdapter<ScoreModel> {

    public RankingAdapter(Context context, ArrayList<ScoreModel> scores){
        super(context,0,scores);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ScoreModel scoreInd = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.score_item, parent, false);
        }

        ConstraintLayout constraintLayout = convertView.findViewById(R.id.constraint_layout);
        TextView rank = convertView.findViewById(R.id.rank);
        TextView score = convertView.findViewById(R.id.score);
        TextView player = convertView.findViewById(R.id.player);

        rank.setText(String.valueOf(position + 1));
        score.setText(String.valueOf(scoreInd.getScore()));
        player.setText(scoreInd.getName());

        if(position%2==0) constraintLayout.setBackgroundColor(Color.LTGRAY);

        else constraintLayout.setBackgroundColor(Color.WHITE);

        return convertView;
    }
}
