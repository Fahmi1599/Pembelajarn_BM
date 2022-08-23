package com.skiripsi.pembelajaranbm.ui.quiz9;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.skiripsi.pembelajaranbm.ui.Question;
import com.skiripsi.pembelajaranbm.ui.QuizContract;

import java.util.ArrayList;
import java.util.List;

public class QuizDBHelper9 extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "materi9.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDBHelper9(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizContract9.QuestionsTable.TABLE_NAME + " ( " +
                QuizContract9.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract9.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract9.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract9.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract9.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuizContract9.QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuizContract9.QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Berikut merpuakan pengertian SINR yang benar adalah "
                , "A. Merupakan parameter yang menentukan kualitas dari sinyal yang diterima"
                , "B. Merupakan power sinyal yang diterima user dalamrentang frekuensi tertentutermasuk noise dan interferensi"
                , "C. Merupakan kualitas dari sebuah channel downlink "
                , "D. Merupakan rasio perbandingan antara sinyal utama yang dipancarkan dengan interferensi dan noise yang timbul ( tercampur dengan sinyal utama ) ", 4);
        addQuestion(q1);
        Question q2 = new Question("Berikut merupakan pengertian RSRP yang benar adalah"
                , "A. Merupakan parameter yang menentukan kualitas dari sinyal yang diterima"
                , "B. Merupakan power sinyal yang diterima user dalamrentang frekuensi tertentutermasuk noise dan interferensi"
                , "C. Merupakan kualitas dari sebuah channel downlink"
                ,"D. merupakan rasio perbandingan antara sinyal utama yang dipancarkan dengan interferensi dan noise yang timbul ( tercampur dengan sinyal utama )",2);
        addQuestion(q2);
        Question q3 = new Question("Berikut merupakan pengertian RSRQ yang benar adalah"
                , "A. Merupakan parameter yang menentukan kualitas dari sinyal yang diterima"
                , "B. Merupakan power sinyal yang diterima user dalamrentang frekuensi tertentutermasuk noise dan interferensiR"
                , "C. Merupakan kualitas dari sebuah channel downlink "
                ,"D. Merupakan rasio perbandingan antara sinyal utama yang dipancarkan dengan interferensi dan noise yang timbul ( tercampur dengan sinyal utama )"
                , 1);
        addQuestion(q3);
        Question q4 = new Question("Berikut merupakan PCI memiliki beberapa aturan dalam perancangannya yaitu , kecuali"
                , "A. Kode PCI tiap cell dalam suatu area harus unik. kondisi ini terjadi ketika dua site tetanggamemiliki kode PCI yang berbeda / tidak sama. "
                , "B. Sebuah kode PCI tidak boleh sama atau berdekatan diantara 2 site atau lebih. sehinggajarak pun perlu dipertimbangkan apabila kita ingin memberikan kode PCI yang serupa. "
                , "C. Jika kode PCI sama antara site yang berdekatan, maka bisa terjadi failure HandOver. ( perpindahan serving cell ) "
                ,"D. PCI dapat diperoleh dari user yang melakukan pemberian informasi terhadap site berupa modulasi yang digunakan, code rate, dan efficiency."
                , 4);
        addQuestion(q4);
        Question q5 = new Question("Berapa jarak pengunaan ulang minimum yang dapat dicapai untuk PCI Planning, kecuali"
                , "A. 504 PCI"
                , "B. 168 different sites"
                , "C. 168 Radius sites"
                ,"D. 204 PCI"
                , 4);
        addQuestion(q5);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract9.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizContract9.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract9.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuizContract9.QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuizContract9.QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuizContract9.QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuizContract9.QuestionsTable.TABLE_NAME, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContract.QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    @SuppressLint("Range")
    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContract.QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract9.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract9.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract9.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract9.QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuizContract9.QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuizContract9.QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

}

