package com.rockpaperscissorgame;

import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
   private static String selectedHandSign;
    private static final String[] handSigns = {"Rock", "Paper", "Scissors"};
    private ImageView[] pictures;
    private int wins = 0;
    private int losses = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        ImageView rockImage = findViewById(R.id.rockImage);
        ImageView paperImage = findViewById(R.id.paperImage);
        ImageView scissorsImage = findViewById(R.id.scissorsImage);
        Button playButton = findViewById(R.id.playButton);
//        Button historyButton = findViewById(R.id.historyButton);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        pictures = new ImageView[]{
                findViewById(R.id.rockImage),
                findViewById(R.id.paperImage),
                findViewById(R.id.scissorsImage)
        };

        rockImage.setOnClickListener(v -> {
            selectedHandSign = "Rock";
            displayUserSelected(selectedHandSign);

        });

        paperImage.setOnClickListener(v -> {
            selectedHandSign = "Paper";
            displayUserSelected(selectedHandSign);
        });

        scissorsImage.setOnClickListener(v -> {
            selectedHandSign = "Scissors";
            displayUserSelected(selectedHandSign);
        });

        playButton.setOnClickListener(v -> {
            playGame();
        });
    }

    private void displayToast(String message, int duration) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(MainActivity.this, "", Toast.LENGTH_SHORT).cancel();
            }
        }, 500);
    }


    private void displayUserSelected(String handSign) {
        displayToast("Selected Hand Sign: " + handSign, 1000);    }

    private void playGame() {
        if (selectedHandSign == null) {
            displayToast("Please select a hand sign first", 1000);
            return;
        }

        Random random = new Random();
        int computerIndex = random.nextInt(handSigns.length);
        String computerHandSign = handSigns[computerIndex];

        int computerImage = getImage(computerHandSign);

        ImageView dispHands = findViewById(R.id.dispHands);
        dispHands.setImageResource(computerImage);

        if (selectedHandSign.equals(computerHandSign)) {
            displayToast("It's a tie!", 1000);
        } else if ((selectedHandSign.equals("Rock") && computerHandSign.equals("Scissors")) ||
                (selectedHandSign.equals("Paper") && computerHandSign.equals("Rock")) ||
                (selectedHandSign.equals("Scissors") && computerHandSign.equals("Paper"))) {
            displayToast("User Wins!", 1000);
            wins++;
        } else {
            displayToast("Computer Wins!", 1000);
            losses++;
        }

        TextView lblWin = findViewById(R.id.lblWin);
        lblWin.setText(String.valueOf(wins));

        TextView lblLose = findViewById(R.id.lblLose);
        lblLose.setText(String.valueOf(losses));

        selectedHandSign = null;

    }

    private int getImage(String handSign) {
        int image;
        switch (handSign) {
            case "Rock":
                image = R.drawable.rock1;
                break;
            case "Paper":
                image = R.drawable.paper1;
                break;
            case "Scissors":
                image = R.drawable.scissors1;
                break;
            default:
                image = R.drawable.default_image;
        }
        return image;
    }

}