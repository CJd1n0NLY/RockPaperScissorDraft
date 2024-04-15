package com.rockpaperscissorgame;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
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

    private void displayUserSelected(String handSign) {
        Toast.makeText(this, "Selected Hand Sign: " + handSign, Toast.LENGTH_SHORT).show();
    }

    private void playGame() {
        if (selectedHandSign == null) {
            Toast.makeText(this, "Please select a hand sign first", Toast.LENGTH_SHORT).show();
            return;
        }

        Random random = new Random();
        int computerIndex = random.nextInt(handSigns.length);
        String computerHandSign = handSigns[computerIndex];

        int computerImage = getImage(computerHandSign);

        ImageView dispHands = findViewById(R.id.dispHands);
        dispHands.setImageResource(computerImage);

        selectedHandSign = null;

    }

    private int getImage(String handSign) {
        int image;
        switch (handSign) {
            case "Rock":
                image = R.drawable.rock;
                break;
            case "Paper":
                image = R.drawable.paper;
                break;
            case "Scissors":
                image = R.drawable.scissors;
                break;
            default:
                image = R.drawable.default_image;
        }
        return image;
    }

}