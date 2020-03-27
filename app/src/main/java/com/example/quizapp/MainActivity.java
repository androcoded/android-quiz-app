package com.example.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final String SCORE_KEY = "SCORE";
    private static final String INDEX_KEY = "INDEX";


    private Button btnTrueAnswer, btnFalseAnswer;
    private TextView txtQuestion, txtQuestionStat;
    private int mQuestionIndex = 0;
    private int mQuestion;
    private ProgressBar mQuestionProgress;
    private int mQuestionStat;
    QuizModel[] mQuestionArray = new QuizModel[]{

            new QuizModel(R.string.q1, true),
            new QuizModel(R.string.q2, false),
            new QuizModel(R.string.q3, true),
            new QuizModel(R.string.q4, false),
            new QuizModel(R.string.q5, true),
            new QuizModel(R.string.q6, false),
            new QuizModel(R.string.q7, true),
            new QuizModel(R.string.q8, false),
            new QuizModel(R.string.q9, true),
            new QuizModel(R.string.q10, false)

    };
    private int USER_PROGRESS = (int) Math.ceil(100 / mQuestionArray.length);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState!=null){

            mQuestionStat=savedInstanceState.getInt(SCORE_KEY);
            mQuestionIndex=savedInstanceState.getInt(INDEX_KEY);

        }

        btnTrueAnswer = findViewById(R.id.btn_trueAnswer_id);
        btnFalseAnswer = findViewById(R.id.btn_falseAnswer_id);
        txtQuestion = findViewById(R.id.txt_question_id);
        mQuestionProgress = findViewById(R.id.pb_question_id);
        txtQuestionStat = findViewById(R.id.txt_question_stats_id);

        //Updating the first question at first glance
        QuizModel q1 = mQuestionArray[mQuestionIndex];
        mQuestion = q1.getmQuestions();
        txtQuestion.setText(mQuestion);
        txtQuestionStat.setText(mQuestionStat + "");

        // setting onClickListener to the true and false button
        btnTrueAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluteCurrectAnswer(true);
                updateQuestionOnClick();
                mQuestionProgress.incrementProgressBy(USER_PROGRESS);
            }
        });

        btnFalseAnswer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                evaluteCurrectAnswer(false);
                updateQuestionOnClick();
                mQuestionProgress.incrementProgressBy(USER_PROGRESS);
            }
        });

    }

    //Updating question method

    private void updateQuestionOnClick() {
        //Modulo operator for interation the whole array
        //while the first number in modulo operator is less
        // than the second num the remainder will  be that first num
        mQuestionIndex = (mQuestionIndex + 1) % (mQuestionArray.length);
        if (mQuestionIndex == 0) {
            Log.i("alert", "In The Alert Dialogue");
            AlertDialog.Builder alertQuiz = new AlertDialog.Builder(MainActivity.this);
            alertQuiz.setTitle("Your Quiz Is Finished");
            alertQuiz.setMessage("Your score is :" + mQuestionStat);
            alertQuiz.setCancelable(false);
            alertQuiz.setPositiveButton("Finish the quiz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    finish();
                }
            });
            alertQuiz.show();
        }

        QuizModel q1 = mQuestionArray[mQuestionIndex];
        mQuestion = q1.getmQuestions();
        txtQuestion.setText(mQuestion);
    }

    //giving feedback to the user using toast massage

    private void evaluteCurrectAnswer(boolean userGuess) {

        boolean currentAnswer = mQuestionArray[mQuestionIndex].ismAnswer();
        if (userGuess == currentAnswer) {
            mQuestionStat = mQuestionStat + 1;
            txtQuestionStat.setText(mQuestionStat + "");
            Toast.makeText(getApplicationContext(), R.string.correct_text, Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getApplicationContext(), R.string.incorrect_text, Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        
        outState.putInt(SCORE_KEY,mQuestionStat);
        outState.putInt(INDEX_KEY,mQuestionIndex);
        
    }
}