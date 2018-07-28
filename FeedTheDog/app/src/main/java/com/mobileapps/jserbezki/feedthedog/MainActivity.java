package com.mobileapps.jserbezki.feedthedog;

import java.util.Random;
import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;

import static android.preference.PreferenceManager.getDefaultSharedPreferences;

public class MainActivity extends Activity implements OnClickListener, AnimationListener {

    TextView textView;
    Button retryButton;
    int countShown = 0, countClicked = 0;
    Random random = new Random();

    RelativeLayout relativeLayout;
    int displayWidth, displayHeight;

    SharedPreferences prefs;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.textView2);
        retryButton = findViewById(R.id.start_again_button);
        relativeLayout = findViewById(R.id.relativeLayout);

        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        displayWidth = size.x;
        displayHeight = size.y;

        prefs = getDefaultSharedPreferences(getApplicationContext());
        editor = prefs.edit();
        editor.apply();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();

        countClicked = countShown = 0;
        textView.setText(R.string.nothing);
        retryButton.setVisibility(View.GONE);

        showADog();
    }

    void showADog() {
        int duration = random.nextInt(prefs.getInt("averageShowTime", 1000)) + prefs.getInt("minimumShowTime", 750);

        LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);

        params.leftMargin = random.nextInt(displayWidth) * 7 / 8;
        params.topMargin = random.nextInt(displayHeight) * 4 / 5;

        ImageView dog = new ImageView(this);
        dog.setOnClickListener(this);
        dog.setLayoutParams(params);
        dog.setImageResource(R.drawable.dog);
        dog.setVisibility(View.INVISIBLE);

        relativeLayout.addView(dog);

        AlphaAnimation animation = new AlphaAnimation(0.0F, 1.0F);
        animation.setDuration(duration);
        animation.setAnimationListener(this);
        dog.startAnimation(animation);
    }

    private void showScores() {
        int highScore = prefs.getInt("highScore", 0);

        if (countClicked > highScore) {
            highScore = countClicked;
            editor.putInt("highScore", highScore);
            editor.apply();
        }

        textView.setText("Your score: " + countClicked + "\nHigh score: " + highScore);
        //textView.setText(R.string.your_score + countClicked + R.string.new_line + R.string.high_score + highScore);
        retryButton.setVisibility(View.VISIBLE);
        retryButton.setOnClickListener(this);
    }

    public void onClick(View view) {
        if (view instanceof Button) {
            onResume();
        }
        else {
            countClicked++;
            ((ImageView) view).setImageResource(R.drawable.dog_with_bone);
            view.setVisibility(View.VISIBLE);
        }
    }

    public void clearScreen() {
        for (int i = 0; i < relativeLayout.getChildCount(); i++) {
            final View child = relativeLayout.getChildAt(i);
            if (child instanceof ImageView) {
                child.animate().alpha(0).setDuration(1000).withEndAction(new Runnable() {
                    @Override
                    public void run() {
                        child.setVisibility(View.GONE);
                    }
                });
            }
        }
    }

    public void onAnimationEnd(Animation animation) {
        if (++countShown < prefs.getInt("numberOfDogs", 10)) {
            showADog();
        } else {
            clearScreen();
            showScores();
        }
    }

    public void onAnimationRepeat(Animation arg0) {
    }

    public void onAnimationStart(Animation arg0) {
    }
}
