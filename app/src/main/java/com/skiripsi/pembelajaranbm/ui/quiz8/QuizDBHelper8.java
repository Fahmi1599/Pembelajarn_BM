package com.skiripsi.pembelajaranbm.ui.quiz8;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.skiripsi.pembelajaranbm.ui.Question;
import com.skiripsi.pembelajaranbm.ui.QuizContract;
import com.skiripsi.pembelajaranbm.ui.quiz9.QuizContract9;

import java.util.ArrayList;
import java.util.List;

 public class QuizDBHelper8 extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "materi8.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDBHelper8(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizContract8.QuestionsTable.TABLE_NAME + " ( " +
                QuizContract8.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract8.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract8.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract8.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract8.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuizContract8.QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuizContract8.QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Berikut yang merupakan LTE KPI’s adalah , kecuali"
                , "A. Mobility"
                , "B. Retainibility"
                , "C. Identity "
                , "D. Avaibility", 3);
        addQuestion(q1);
        Question q2 = new Question("Kepanjangan dari OSI Layer ?"
                , "A. Organisation Standar Internasional"
                , "B. Operation System Interconnection"
                , "C. Open System Interconecction"
                ,"D. Open Standar Interconecction",3);
        addQuestion(q2);
        Question q3 =
                new Question("Berikut yang merupakan physical paramter adalah "
                , "A. Traffic Sharing"
                , "B. Upgrade Carrier"
                , "C.  Cell Splitting"
                ,"D. Tinggi Antenna"
                , 4);
        addQuestion(q3);
        Question q4 =
                new Question("Berikut yang merupakan Traffic Blocking adalah "
                        , "A. Tinggi Antenna"
                        , "B. Tipe Antenna"
                        , "C.  Azimuth Antenna"
                        ,"D. Traffic Sharing"
                        , 4);
        addQuestion(q4);
        Question q5 = new Question("Berikut yang merupakan Drivetest tools adalah , kecuali"
                , "A. TEMS"
                , "B. Probe"
                , "C. Kompas"
                ,"D. Investigation"
                , 4);
        addQuestion(q5);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract8.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizContract8.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract8.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuizContract8.QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuizContract8.QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuizContract8.QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
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
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract8.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract8.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract8.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract8.QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuizContract8.QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuizContract8.QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

}