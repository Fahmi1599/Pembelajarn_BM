package com.skiripsi.pembelajaranbm.ui.quiz2;

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

public class QuizDbHelper2 extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "materi2.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper2(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizContract2.QuestionsTable.TABLE_NAME + " ( " +
                QuizContract2.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract2.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract2.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract2.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract2.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuizContract2.QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuizContract2.QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Apa itu RSRP ?", "A. Merupakan parameter yang menyatakan tingkat kualitas sinyal yang di terima oleh user dalam satuan Db."
                , "B. Merupakan perbandingan antara RSRP DAN RSSI"
                , "C. Merupakan sinyal LTE power yang diterima oleh user dalam frekuensi tertentu. Semakin jauh jarak antara site dan user, maka semakin kecil pula RSRP yang diterima oleh user."
                , "D. Merupakan parameter yang menyatakan keseluruhan daya sinyal yang diterima oleh user dalam suatu dBm.", 2);
        addQuestion(q1);
        Question q2 = new Question("Apa itu VoLTE ?"
                , "A. Suatu informasi dibawa oleh suatu symbol yang berisikan bit-bit informasi"
                , "B. Teknologi yang memungkinkan kita untuk melakukan panggilan suara dengan menggunakan jaringan 4G LTE."
                , "C. Suatu sinyal yang ditransmisikan dapat dipetakan kedalam beberapa domain, baik domain waktu maupun domain frekuensi."
                ,"D. Merupakan jenis modulasi multicarrier yang memiliki efisiensi frekuensi yang lebih bedar dibandingkan dengan modulasi.",2);
        addQuestion(q2);
        Question q3 = new Question("Kepanjangan dari SRVCC ?"
                , "A. Single Radiasi Voice Call community"
                , "B. System radio Voice Call Continuty"
                , "C. Single Radio Voice Call Continuity"
                ,"D. Single Radio Voice Call Community"
                , 3);
        addQuestion(q3);
        Question q4 = new Question("Apa yang dimaksud dengan ESRVCC?"
                , "A. Pengembangan dari SRVCC, ESRVCC akan mempercepat delau saat serah terima SRVCC. Menghemat proses signalling dan trunk tidak ada signalling. Menggunakan ATCF (controller) dan ATGW (payload)"
                , "B. Skema yang menungkinkan Inter RAT melakukan handover dari paket data (4G:PS) ke panggilan suara."
                , "C. Suatu sinyal yang ditransmisikan dapat dipetakan kedalam beberapa domain, baik domain waktu maupun domain frekuensi."
                ,"D. Suatu informasi dibawa oleh suatu symbol yang berisikan bit-bit informasi."
                , 1);
        addQuestion(q4);
        Question q5 = new Question("Apa yang dimaksud dengan 3G?", "A. Merupakan teknologi berbasis IP yang dikeluarkan oleh 3GPP sebagai standar untuk komunikasi data nirkabel berkecepatan tinggi."
                , "B. Memperkenalkan layanan data seluler yang aman dan mampu mengirimkan pesan teks (SMS) dan pesan multimedia (MMS).", "C. Menetapkan standar untuk sebagian besar teknologi nirkabel yang telah kita kenal dan gunakan saat ini."
                ,"D. 3G sebagai solusi nirkabel yang dapat memberikan kecepatan akses, di antaranya sebesar 144 Kbps dalam kondisi cepat (mobile), sebesar 384 Kbps untuk kondisi berjalan (pedestrian), dan sebesar 2 Mbps untuk kondisi statik di suatu tempat oleh user dalam suatu dBm."
                , 4);
        addQuestion(q5);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuizContract.QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuizContract.QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuizContract.QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuizContract.QuestionsTable.TABLE_NAME, null, cv);
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
                question.setQuestion(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuizContract.QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

}
