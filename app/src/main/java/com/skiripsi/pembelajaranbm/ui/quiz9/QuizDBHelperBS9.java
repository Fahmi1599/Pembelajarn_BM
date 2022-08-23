package com.skiripsi.pembelajaranbm.ui.quiz9;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.skiripsi.pembelajaranbm.ui.Question;
import com.skiripsi.pembelajaranbm.ui.QuestionBS;

import java.util.ArrayList;
import java.util.List;

public class QuizDBHelperBS9 extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "materi9bsfix.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDBHelperBS9(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizContractBS9.QuestionsTable.TABLE_NAME + " ( " +
                QuizContractBS9.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContractBS9.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuizContractBS9.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContractBS9.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContractBS9.QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    private void fillQuestionsTable() {
        QuestionBS q1s = new QuestionBS("Apakah PCI singkatan dari Physical Cell Identity "
                , "A. Benar"
                , "B. Salah", 1);
        addQuestion(q1s);
        QuestionBS q2 = new QuestionBS("Apakah PSS singkatan dari  Physical Synchronization Signal "
                , "A. Benar"
                , "B. Salah",2);
        addQuestion(q2);
        QuestionBS q3 = new QuestionBS("Apakah Primary Synchronization Signal memiliki range angka 0-2"
                , "A. Benar"
                , "B. Salah"
                , 1);
        addQuestion(q3);
        QuestionBS q4 = new QuestionBS("Apakah SSS singkatan dari Secondary Synchronization Signal "
                , "A. Benar"
                , "B. Salah"
                , 1);
        addQuestion(q4);
        QuestionBS q5 = new QuestionBS("Apakah Secondary Synchronization Signal memiliki range nilai 167-200"
                , "A. Benar"
                , "B. Salah"
                , 2);
        addQuestion(q5);
    }

    private void addQuestion(QuestionBS question) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContractBS9.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizContractBS9.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContractBS9.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuizContractBS9.QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuizContractBS9.QuestionsTable.TABLE_NAME, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuizContractBS9.QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    @SuppressLint("Range")
    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuizContractBS9.QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuizContractBS9.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContractBS9.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContractBS9.QuestionsTable.COLUMN_OPTION2)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuizContractBS9.QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

}

