package com.skiripsi.pembelajaranbm.ui.quiz1;

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

public class QuizDbHelper1  extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "materi1six.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper1(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizContract1.QuestionsTable.TABLE_NAME + " ( " +
                QuizContract1.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract1.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract1.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract1.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract1.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuizContract1.QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuizContract1.QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Apa itu 4G LTE ?", "A. Merupakan teknologi berbasis IP yang dikeluarkan oleh 3GPP sebagai standa untuk komunikasi data nirkabel berkecepatan tinggi."
                , "B. Memperkenalkan layanan data seluler yang aman dan mampu mengirimkan pesan teks (SMS) dan pesan multimedia (MMS)."
                , "C. Menawarkan kecepatan yang lebih dan koneksi yang jauh lebih baik daripada generasi sebelumnya baik di smartphone atau pun perangkat lainnya." , "D. menetapkan standar untuk sebagian besar teknologi nirkabel yang telah kita kenal dan gunakan saat ini.", 1);
        addQuestion(q1);
        Question q2 = new Question("Apa yang dimaksud dengan HSDPA?", "A. merupakan salah satu protokol yang memper baiki proses downlink atau penurunan data dari server ke perangkat (unduh), dengan kecepatan mencapai 14.4Mbit/s. Sedangkan proses uplink dalam teknologi HSDPA mencapai 384kbit/s."
                , "B. Merupakan salah satu protokol ponsel yang memperbaiki uplink atau penaikan data dari perangkat ke server (unggah) yang mencapai 5.76Mbit/s. Dengan kecepatan ini, pengguna dapat lebih mudah mengunggah tulisan, gambar, maupun video ke blog pribadi atapun situs seperti YouTube hanya dalam waktu beberapa detik saja."
                , "C. Teknologi standar pita lebar nirkabel yang akan hadir dengan kemampuan pengiriman data mencapai 42Mbit/s untuk downlink dengan menggunakan modulasi 64QAM dan 11Mbit/s untuk uplink dengan modulasi 16QAM."
                ,"D. Suatu teknologi yang memungkinkan pengiriman data lebih cepat dibandingkan dengan penggunaan teknologi Circuit Switch Data atau CSD.",1);
        addQuestion(q2);
        Question q3 = new Question("Singkatan dari EPC?", "A. Engineering Procurement Core", "B. Evolved Packet Core"
                , "C. Evolved Prosses Core" ,"D. Engineer Packet Core"
                , 2);
        addQuestion(q3);
        Question q4 = new Question("Gambar4G"
                , "A. Arsitektur Jaringan 2G"
                , "B. Arsitektur Jaringan 3G"
                , "C. Arsitektur Jaringan 4G"
                ,"D. Arsitektur Jaringan 3.5G"
                , 3);
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
