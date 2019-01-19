package sarveshchavan777.triviaquiz;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;



public class GameWon extends Activity {

    public static String result="highscore";
    public static final String mypreference = "Result";
    TextView score1,highscore1;
    Intent a = new Intent();
    SharedPreferences   sharedpreferences;
    int score=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_won);
        score1=(TextView)findViewById(R.id.score);
        highscore1=(TextView)findViewById(R.id.highscore);

        //get from main
        String a1= getIntent().getStringExtra("a");
        score=Integer.parseInt(a1);

        score1.setText("Latest Score"+a1);

        setHighscore(score);
        int last=getHighScore();
        highscore1.setText("High Score"+String.valueOf(last));


    }

    public void setHighscore(int score){
        final SharedPreferences sharedPreferences = getSharedPreferences("Result", Context.MODE_PRIVATE);
        int highScore=sharedPreferences.getInt(result,0);
        if(score > highScore){
            SharedPreferences.Editor editor=sharedPreferences.edit();
            editor.putInt(result,score);
            editor.commit();
        }
    }

    public int getHighScore(){
        final SharedPreferences sharedPreferences = getSharedPreferences("Result", Context.MODE_PRIVATE);
        return sharedPreferences.getInt(result,score);
    }

    //This is onclick listener for button
    //it will navigate from this activity to MainGameActivity
    public void PlayAgain(View view) {
        Intent intent = new Intent(GameWon.this, MainGameActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
