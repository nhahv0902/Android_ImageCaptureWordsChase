package com.nhahv.imagecapturewordschase.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.nhahv.imagecapturewordschase.R;
import com.nhahv.imagecapturewordschase.models.Models;
import com.nhahv.imagecapturewordschase.models.Question;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private static final int SIZE_IMAGE = 30;
    private static final int SIZE_ANSWER = 16;

    private final String TAG = getClass().getSimpleName();

    private List<Question> mListQuestion = new ArrayList<>(SIZE_IMAGE);
    private List<String> mListNameImage = new ArrayList<>(SIZE_IMAGE);

    private Button mListBtnQuestion[] = new Button[SIZE_ANSWER];
    private Button mListBtnAnswer[] = new Button[SIZE_ANSWER];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getListNameImages();
        getListQuestion();

        initViews();

    }

    private void initViews() {

        for (int i = 0; i < SIZE_ANSWER; i++) {
            mListBtnQuestion[i] = (Button) findViewById(R.id.image_question01 + i);
            mListBtnQuestion[i].setOnClickListener(new Events());

            mListBtnAnswer[i] = (Button) findViewById(R.id.image_answer01 + i);
        }
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
        Random random = new Random();
        int index = random.nextInt(SIZE_IMAGE);
        Question question = mListQuestion.get(index);
        int size = question.getName().length();



    }

    private class Events implements View.OnClickListener {
        @Override
        public void onClick(View v) {

        }
    }


}
