package com.skiripsi.pembelajaranbm.ui.quiz7;

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

public class QuizDBHelper7 extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "materi7.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDBHelper7(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizContract7.QuestionsTable.TABLE_NAME + " ( " +
                QuizContract7.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract7.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract7.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract7.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract7.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuizContract7.QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuizContract7.QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Berikut merupakan step utama dalam Design LTE Planning adalah"
                , "A. Detailed Planning, Preplanning, Dimensioning"
                , "B. Dimensioning , Detailed Planning, Preplanning"
                , "C. Dimensioning , Preplanning, Detailed Planning "
                , "D. Preplanning , Detailed Planning, Dimensioning", 3);
        addQuestion(q1);
        Question q2 = new Question("Berikut yang termasuk dalam Radio Network Planning Dimensioning adalah , kecuali"
                , "A. Coverage"
                , "B. Capacity"
                , "C. Active User"
                ,"D. Calculation",4);
        addQuestion(q2);
        Question q3 =
                new Question("Berikut yang merupakan tahap yang benar dalam LTE Coverage Planning Flow adalah "
                        , "A. Customer Requirement,Link Budget,Cell Radius,Site Coverage Area"
                        , "B. Link Budget, Cell Radius, Site Coverage Area, Customer Requirement"
                        , "C. Cel Radius , Site Coverage Area, Link Budget, Customer Requirement"
                        ,"D. Customer Requirement, Cell Radius, Site Coverage Area, Link Budget"
                        , 1);
        addQuestion(q3);
        Question q4 =
                new Question("Interface yang menghubungkan antar e-Node B adalah "
                        , "A. Interface X2"
                        , "B. Interface X1"
                        , "C. Interface S1"
                        ,"D. Interface S2"
                        , 1);
        addQuestion(q4);
        Question q5 =
                new Question("Interface yang menghubungkan antara e-Node B dengan MMW-SGW adalah "
                        , "A. Interface X2"
                        , "B. Interface X1"
                        , "C. Interface S1"
                        ,"D. Interface S2"
                        , 3);
        addQuestion(q5);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract7.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizContract7.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract7.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuizContract7.QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuizContract7.QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuizContract7.QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuizContract7.QuestionsTable.TABLE_NAME, null, cv);
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
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract7.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract7.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract7.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract7.QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuizContract7.QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuizContract7.QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

}