package com.feicui.game.mygame2048;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.feicui.game.mygame2048.util.ShareUtil;
import com.feicui.game.mygame2048.view.GameView;

public class MainActivity extends AppCompatActivity {
    private TextView tvScore, tvRestart, tvMaxScore;
    private static MainActivity mainActivity = null;
    private int score, maxScore;
    private GameView gameView;

    public MainActivity() {
        mainActivity = this;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tvScore = (TextView) findViewById(R.id.tvScore);
        gameView = (GameView) findViewById(R.id.gameView);
        tvRestart = (TextView) findViewById(R.id.tvRestart);
        tvMaxScore = (TextView) findViewById(R.id.tvMaxScore);

        maxScore = ShareUtil.getMaxScore(this);
        tvMaxScore.setText(maxScore + "");
        tvRestart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameView.reStart();
            }
        });
    }

    public static MainActivity getMainActivity() {
        return mainActivity;
    }

    public void clearScore() {
        score = 0;
        showScore();
    }

    public void showScore() {
        tvScore.setText(score + "");
    }

    public void addScore(int s) {
        score += s;
        showScore();
    }

    public void showMaxScore() {
        if (score>maxScore) {
            maxScore = score;
            ShareUtil.saveMaxScore(this,maxScore);
            tvMaxScore.setText(maxScore + "");
        }
    }
}
