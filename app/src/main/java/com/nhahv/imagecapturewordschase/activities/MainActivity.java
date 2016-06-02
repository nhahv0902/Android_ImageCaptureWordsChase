package com.nhahv.imagecapturewordschase.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nhahv.imagecapturewordschase.R;
import com.nhahv.imagecapturewordschase.models.Models;
import com.nhahv.imagecapturewordschase.models.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final int SIZE_IMAGE = 30;
    private static final int SIZE_ANSWER = 16;

    private static final int MAX_ASCII = 91;
    private static final int MIN_ASCII = 65;

    private int mSize;
    private int mIndexAnswer;
    private String mAnswer;
    private Question mQuestion;

    private final String TAG = getClass().getSimpleName();

    private List<Question> mListQuestion = new ArrayList<>(SIZE_IMAGE);
    private List<String> mListNameImage = new ArrayList<>(SIZE_IMAGE);

    private Button mListBtnQuestion[] = new Button[SIZE_ANSWER];
    private Button mListBtnAnswer[] = new Button[SIZE_ANSWER];

    private Button mBtnNext;
    private ImageView mImageView;
    private TextView mTxtLive, mTxtPoint;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getListNameImages();
        getListQuestion();

        initViews();

        setListBtnQuestion();
    }

    private void initViews() {

        for (int i = 0; i < SIZE_ANSWER; i++) {
            mListBtnQuestion[i] = (Button) findViewById(R.id.image_question01 + i);
            mListBtnQuestion[i].setOnClickListener(new Events());

            mListBtnAnswer[i] = (Button) findViewById(R.id.image_answer01 + i);
        }

        mBtnNext = (Button) findViewById(R.id.btn_next);
        mBtnNext.setOnClickListener(new Events());
        mImageView = (ImageView) findViewById(R.id.image_main);
        mTxtLive = (TextView) findViewById(R.id.txt_live);
        mTxtPoint = (TextView) findViewById(R.id.txt_point);

        mTxtLive.setText("5");
        mTxtPoint.setText("0");
    }


    private int getIdOfImageByName(String name) {
        return getResources().getIdentifier(name, "drawable", getPackageName());
    }

    /**
     * set list array contain list name image
     */
    private void getListNameImages() {

        mListNameImage.add(Models.AO_MUA);
        mListNameImage.add(Models.BAO_CAO);
        mListNameImage.add(Models.CAN_THIEP);
        mListNameImage.add(Models.CAT_TUONG);
        mListNameImage.add(Models.CHIEU_TRE);
        mListNameImage.add(Models.DANH_LUA);
        mListNameImage.add(Models.DAN_ONG);
        mListNameImage.add(Models.GIAN_DIEP);
        mListNameImage.add(Models.GIANG_MAI);
        mListNameImage.add(Models.HOI_DONG);
        mListNameImage.add(Models.HONG_TAM);
        mListNameImage.add(Models.KHOAI_LANG);
        mListNameImage.add(Models.KIEM_CHUYEN);
        mListNameImage.add(Models.LAN_CAN);
        mListNameImage.add(Models.MA_SAT);
        mListNameImage.add(Models.NAM_BAN_CAU);
        mListNameImage.add(Models.OTO);
        mListNameImage.add(Models.QUY_HANG);
        mListNameImage.add(Models.SONG_SONG);
        mListNameImage.add(Models.THAT_TINH);
        mListNameImage.add(Models.THO_THE);
        mListNameImage.add(Models.TICH_PHAN);
        mListNameImage.add(Models.TO_HOAI);
        mListNameImage.add(Models.TO_TIEN);
        mListNameImage.add(Models.TRANH_THU);
        mListNameImage.add(Models.VUA_PHA_LUOI);
        mListNameImage.add(Models.VUON_BACH_THU);
        mListNameImage.add(Models.XA_KEP);
        mListNameImage.add(Models.XA_PHONG);
        mListNameImage.add(Models.XA_DAP_DIEN);
    }

    private void getListQuestion() {

        int size = mListNameImage.size();
        Question question;
        String name;
        for (int i = 0; i < size; i++) {
            name = mListNameImage.get(i);
            int id = getIdOfImageByName(name);
            question = new Question(id, name);
            mListQuestion.add(question);
        }
    }

    private void setListBtnQuestion() {

        mIndexAnswer = 0;
        mAnswer = "";


        for (int i = 0; i < SIZE_ANSWER; i++) {
            mListBtnAnswer[i].setVisibility(View.VISIBLE);
            mListBtnQuestion[i].setVisibility(View.VISIBLE);

            mListBtnAnswer[i].setEnabled(true);
            mListBtnQuestion[i].setEnabled(true);

            mListBtnQuestion[i].setText("");
            mListBtnAnswer[i].setText("");
        }

        Random random = new Random();
        int index = random.nextInt(SIZE_IMAGE);
        mQuestion = mListQuestion.get(index);
        mSize = mQuestion.getName().length();
        mImageView.setImageDrawable(getResources().getDrawable(mQuestion.getId()));

        for (int i = mSize; i < SIZE_ANSWER; i++) {
            mListBtnAnswer[i].setVisibility(View.INVISIBLE);
        }

        String string = mQuestion.getName().toLowerCase();
        int size = string.length();


        for (int i = 0; i < size; i++) {
            char c = string.charAt(i);
            int in = (int) c % 16;
            Log.d(TAG, (in % 16) + " - ");
            int indexBtnQuestion = checkButtonEmpty(in % SIZE_ANSWER);
            String text = c + "";
            mListBtnQuestion[indexBtnQuestion].setText(text);
        }

        for (int i = 0; i < SIZE_ANSWER; i++) {
            if (mListBtnQuestion[i].getText().toString().equals("")) {
                int in = new Random().nextInt(MAX_ASCII - MIN_ASCII) + MIN_ASCII;
                String text = (char) in + "";
                mListBtnQuestion[i].setText(text);
            }
        }

    }

    private int checkButtonEmpty(int i) {
        if (mListBtnQuestion[i].getText().toString().equals("")) {
            return i;
        } else if (i == 16) {
            return checkButtonEmpty(0);
        } else {
            return checkButtonEmpty(i + 1);
        }
    }


    private class Events implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.btn_next) {
                onClickNextQuestion();
            } else {
                int index = v.getId() - R.id.image_question01;
                setOnClickBtnQuestion(index);
            }
        }

        private void onClickNextQuestion() {
            setListBtnQuestion();
            mBtnNext.setVisibility(View.INVISIBLE);
        }

        public void setOnClickBtnQuestion(int index) {
            String text = mListBtnQuestion[index].getText().toString();
            mAnswer += text;
            mListBtnAnswer[mIndexAnswer].setText(text);
            mIndexAnswer++;
            mListBtnQuestion[index].setVisibility(View.INVISIBLE);
            if (mIndexAnswer == mSize) {
                for (int i = 0; i < SIZE_ANSWER; i++) {
                    mListBtnQuestion[i].setEnabled(false);
                }

                if (mAnswer.toLowerCase().equals(mQuestion.getName().toLowerCase())) {
                    getToast(getString(R.string.answer_correct));
                    Log.d(TAG, mAnswer);
                    mBtnNext.setVisibility(View.VISIBLE);
                    int point = Integer.parseInt(mTxtPoint.getText().toString()) + 100;
                    mTxtPoint.setText(point + "");

                } else {
                    getToast(getString(R.string.answer_un_correct));

                    int point = Integer.parseInt(mTxtLive.getText().toString()) - 1;

                    mTxtLive.setText(point + "");
                    mBtnNext.setVisibility(View.VISIBLE);

                }
            }

            int live = Integer.parseInt(mTxtLive.getText().toString());
            if (live == 0) {
                startActivity(new Intent(MainActivity.this, StartActivity.class));
            }
        }
    }

    private void getToast(String string) {
        Toast.makeText(MainActivity.this, string, Toast.LENGTH_SHORT).show();
    }


}
