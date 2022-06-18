package com.example.fragedestages;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

public class FrageActivity extends Activity {
    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView textViewQuestionCount;
    private RadioGroup butGroup;
    private RadioButton but1;
    private RadioButton but2;
    private RadioButton but3;
    private Button buttonConfirmNext;
    private ColorStateList textColorDefaultRb;
    private List<Frage> frageList;
    private int questionCounter;
    private int questionCountTotal;
    private Frage currentFrage;
    private int score;
    private boolean answered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.frage);
        textViewQuestion = findViewById(R.id.text_view_question);
        textViewScore = findViewById(R.id.text_view_score);
        textViewQuestionCount = findViewById(R.id.text_view_question_count);
        butGroup = findViewById(R.id.radio_group);
        but1 = findViewById(R.id.radio_button1);
        but2 = findViewById(R.id.radio_button2);
        but3 = findViewById(R.id.radio_button3);
        buttonConfirmNext = findViewById(R.id.button_confirm_next);
        textColorDefaultRb = but1.getTextColors();
        FrageDbHelper dbHelper = new FrageDbHelper(this);
        frageList = dbHelper.getAllQuestions();
        questionCountTotal = frageList.size();
        Collections.shuffle(frageList);
        showNextQuestion();
        buttonConfirmNext.setOnClickListener(v -> {
            int ans= 0;

            if (!answered) {
                if (but1.isChecked() || but2.isChecked() || but3.isChecked() ) {
                    checkAnswer();
                }
                else {
                    Toast.makeText(FrageActivity.this, "Bitte wählen Sie eine Antwort", Toast.LENGTH_SHORT).show();
                }
            } else {
                showNextQuestion();
            }
        });
        }

    @SuppressLint("SetTextI18n")
    private void showNextQuestion() {
        but1.setTextColor(textColorDefaultRb);
        but2.setTextColor(textColorDefaultRb);
        but3.setTextColor(textColorDefaultRb);
        butGroup.clearCheck();

        if (questionCounter < questionCountTotal) {
            currentFrage = frageList.get(questionCounter);
            textViewQuestion.setText(currentFrage.getQuestion());
            but1.setText(currentFrage.getOption1());
            but2.setText(currentFrage.getOption2());
            but3.setText(currentFrage.getOption3());
            questionCounter++;
            textViewQuestionCount.setText("Frage: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            buttonConfirmNext.setText("Prüfen");
        } else {
            finishQuiz();
        }
    }

    @SuppressLint("SetTextI18n")
    private void checkAnswer() {
        answered = true;
        RadioButton rbSelected = findViewById(butGroup.getCheckedRadioButtonId());
        int answerNr = butGroup.indexOfChild(rbSelected) + 1;

        if (answerNr == currentFrage.getAnswerNr()) {
            score++;
            textViewScore.setText(" Das Ergebnis: " + score);
        }
        showSolution();
    }

    private void showSolution() {
        but1.setTextColor(Color.RED);
        but2.setTextColor(Color.RED);
        but3.setTextColor(Color.RED);

        switch (currentFrage.getAnswerNr()) {
            case 1:
                but1.setTextColor(Color.GREEN);
                textViewQuestion.setText("Die Antwort 1 ist richtig");
                break;
            case 2:
                but2.setTextColor(Color.GREEN);
                textViewQuestion.setText("Die Antwort 2 ist richtig");
                break;
            case 3:
                but3.setTextColor(Color.GREEN);
                textViewQuestion.setText("Die Antwort 3 ist richtig");
                break;
        }

        if (questionCounter < questionCountTotal) {
            buttonConfirmNext.setText("Nächste");
        } else {
            buttonConfirmNext.setText("Beenden");
        }
    }
    private void finishQuiz() {
        finish();
    }
}