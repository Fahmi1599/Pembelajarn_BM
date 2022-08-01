package com.skiripsi.pembelajaranbm.ui;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.skiripsi.pembelajaranbm.ui.QuizContract.*;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper  extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "qqqqs.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuestionsTable.TABLE_NAME + " ( " +
                QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Enode B mempunyai 2 fungsi yaitu", "A. Sebagai radio transmitter dan receiver, Mengontrol low level operation Mobile User dengan mengirim pesan seperti saat handover"
                , "B. memiliki fungsi menangani sisi radio akses dari UE ke jaringan core, Sebagai radio transmitter dan receiver"
                , "Sebagai radio transmitter dan receiver, fungsinya mendukung akses komunikasi dan penerusan paket traffik saat UE melakukan handover" , "D. Mengontrol low level operator Mobile user dengan mengirim pesan seperti saat", 1);
        addQuestion(q1);
        Question q2 = new Question("Kepanjangan dari SRVCC", "A. Single Radio Voice Call Continuity", "B. Single Radiasi Voice Call community", "C. System radio Voice Call Continuty","D. Single Radio Voice Call Comunnit",1);
        addQuestion(q2);
        Question q3 = new Question("Apa itu VoLTE", "A. Suatu informasi dibawa oleh suatu symbol yang berisikan bit-bit informasi", "B. Bisa diartikan sebagai teknologi yang memungkinkan kita untuk melakukan panggilan suara dengan menggunakan jaringan 4G LTE"
                , "C. Suatu sinyal yang ditransmisikan dapat dipetakan kedalam beberapa domain, baik domain waktu maupun domain frekuensi" ,"D. Merupakan jenis modulasi multicarrier yang memiliki efisiensi frekuensi yang lebih bedar dibandingkan dengan modulasi"
                , 2);
        addQuestion(q3);
        Question q4 = new Question("Keunggulan dari OFDMA", "A. Pemakaian rentang frekuensi yang lebih kecil, Kuat terhadap frequency selective fading, tidak sensitive terhadap delay spread" ,
                "B. Memiliki sensitivikasi yang tinggi terhadap CFO yang disebabkan oleh jitter, Teknologi OFDMA menggunakan system multi-frekuensi dan multi-amplitudo, Menentukan start poin memulai operasi fast fourier transform (FFT)"
                , "C. Kuat terhadap frequency selective fading, memiliki sensitifikasi yang tinggi terhadap CFO yang disebabkan oleh jiter, tahan terhadap ISI dan ICI akibat multipath delay untuk meningkatkan level QoS multipath delay untuk meningkatkan level QoS"
                ,"D. Mempermudah processing sinyal pada kondisi multipath, yaitu saat beberapa sinyal datang pada penerima antenna. Tidak sensitive terhadap delay spread,memiliki sensitivikasi yang tinggi terhadap CFO yang disebabkan oleh jitter."
                , 1);
        addQuestion(q4);
        Question q5 = new Question("Sebutkan pengertian dari RSRP", "A. Merupakan parameter yang menyatakan tingkat kualitas sinyal yang di terima oleh user dalam satuan Db."
                , "B. Merupakan perbandingan antara RSRP DAN RSSI", "C. Merupakan sinyal LTE power yang diterima oleh user dalam frekuensi tertentu. semakin jauh jarak antara site dan user, maka semakin kecil pula RSRP yang diterima oleh user"
                ,"D. Merupakan parameter yang menyatakan keseluruhan daya sinyal yang diterima" + "oleh user dalam suatu dBm.", 2);
        addQuestion(q5);
        Question q6 = new Question("Suatu sistem FM siaran dengan frekuensi carrier 100 MHz, simpangan frekuensi" +
                "maksimumnya 75 kHz, dan indeks modulasi 5, maka bandwidth FM yang dipancarkan" +
                "menurut aturan Carson dari sistem tersebut adalah ?", "A. 30 kHz", "B. 75 kHz", "C. 120 kHz" ,"D. 180 kHz" , 4);
        addQuestion(q6);
        Question q7 = new Question("Kepanjangan dari OSI Layer ?", "A. Organisation Standar Internasional", "B. Operation System Interconnection", "C. Open System Interconecction" ,"D. Open Standar Interconecction" , 3);
        addQuestion(q7);
        Question q8 = new Question("Apa itu 4G LTE ?", "A. Merupakan teknologi berbasis IP yang dikeluarkan oleh 3GPP sebagai standar" +
                "untuk komunikasi data nirkabel berkecepatan tinggi.", "B. Memperkenalkan layanan data seluler yang aman dan mampu" +
                "mengirimkan pesan teks (SMS) dan pesan multimedia (MMS).", "C. Menawarkan kecepatan yang lebih dan koneksi yang jauh lebih baik" +
                "daripada generasi sebelumnya baik di smartphone atau pun perangkat lainnya." ,"D. Menetapkan standar untuk sebagian besar teknologi nirkabel yang telah\n" +
                "kita kenal dan gunakan saat ini." , 1);
        addQuestion(q8);
        Question q9 = new Question("Sistem modulasi digital yang menggunakan perbedaan fasa untuk membedakan" +
                "antar symbol adalah?", "A. ASK", "B. FSK", "C. PSK" ,"D. AM" , 3);
        addQuestion(q9);
        Question q10 = new Question("Yang termasuk dalam protocol-protokol pada layer physical yaitu?", "A. IEEE 802 (Ethernet Standard), repeater, ISDN, TDR", "B. IEEE 802.2 (Ethernet standard), media accesss control, logical link control," +
                "controls the type of media", "C. IEEE 802, IEEE 802.2, ISO 2110, ISDN" ,"D. ISO 2110, ISDN, media access control, repeater" , 2);
        addQuestion(q10);
    }

    private void addQuestion(Question question) {
        ContentValues cv = new ContentValues();
        cv.put(QuestionsTable.COLUMN_QUESTION, question.getQuestion());
        cv.put(QuestionsTable.COLUMN_OPTION1, question.getOption1());
        cv.put(QuestionsTable.COLUMN_OPTION2, question.getOption2());
        cv.put(QuestionsTable.COLUMN_OPTION3, question.getOption3());
        cv.put(QuestionsTable.COLUMN_OPTION4, question.getOption4());
        cv.put(QuestionsTable.COLUMN_ANSWER_NR, question.getAnswerNr());
        db.insert(QuestionsTable.TABLE_NAME, null, cv);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + QuestionsTable.TABLE_NAME);
        onCreate(db);
    }

    @SuppressLint("Range")
    public List<Question> getAllQuestions() {
        List<Question> questionList = new ArrayList<>();
        db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + QuestionsTable.TABLE_NAME, null);

        if (c.moveToFirst()) {
            do {
                Question question = new Question();
                question.setQuestion(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_QUESTION)));
                question.setOption1(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION1)));
                question.setOption2(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION2)));
                question.setOption3(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION3)));
                question.setOption4(c.getString(c.getColumnIndex(QuestionsTable.COLUMN_OPTION4)));
                question.setAnswerNr(c.getInt(c.getColumnIndex(QuestionsTable.COLUMN_ANSWER_NR)));
                questionList.add(question);
            } while (c.moveToNext());
        }

        c.close();
        return questionList;
    }

}
