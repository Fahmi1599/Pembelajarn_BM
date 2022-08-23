package com.skiripsi.pembelajaranbm.ui.quiz4;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.skiripsi.pembelajaranbm.ui.Question;
import com.skiripsi.pembelajaranbm.ui.QuizContract;
import com.skiripsi.pembelajaranbm.ui.quiz2.QuizContract2;

import java.util.ArrayList;
import java.util.List;

public class QuizDbHelper4 extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "materi4.db";
    private static final int DATABASE_VERSION = 1;

    private SQLiteDatabase db;

    public QuizDbHelper4(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        this.db = db;

        final String SQL_CREATE_QUESTIONS_TABLE = "CREATE TABLE " +
                QuizContract4.QuestionsTable.TABLE_NAME + " ( " +
                QuizContract4.QuestionsTable._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                QuizContract4.QuestionsTable.COLUMN_QUESTION + " TEXT, " +
                QuizContract4.QuestionsTable.COLUMN_OPTION1 + " TEXT, " +
                QuizContract4.QuestionsTable.COLUMN_OPTION2 + " TEXT, " +
                QuizContract4.QuestionsTable.COLUMN_OPTION3 + " TEXT, " +
                QuizContract4.QuestionsTable.COLUMN_OPTION4 + " TEXT, " +
                QuizContract4.QuestionsTable.COLUMN_ANSWER_NR + " INTEGER" +
                ")";

        db.execSQL(SQL_CREATE_QUESTIONS_TABLE);
        fillQuestionsTable();
    }

    private void fillQuestionsTable() {
        Question q1 = new Question("Berikut , yang merupakan akses transmisi yang digunakan berdasar dari 3GPP sistem adalah ,kecuali "
                , "A. Frequency Division Multiple Access(FDMA)"
                , "B. Time Division Multiple Access (TDMA)"
                , "C. Code Division Multiple Access ( CDMA)"
                , "D. Multi Multiple Access (MMA)", 4);
        addQuestion(q1);
        Question q2 = new Question("Berikut yang merupakan penjelasan Dari FDMA adalah"
                , "A. Dengan menggunakan teknik ini, saluran khusus dapatdialokasikan ke pengguna, sedangkan pengguna lainmenempati saluran atau frekuensi lain"
                , "B. Jumlah timeslots dalam bingkai TDMA tergantung padasistem."
                , "C. Penggunaan kode dan bandwidth yang digunakan olehbeberapa teknologi berbeda."
                ,"D. Dapat diimplementasikan pada berbagai spektrum frekuensidengan sedikit saja modifikasi pada sistem",1);
        addQuestion(q2);
        Question q3 = new Question("Berikut yang merupakan keuntungan dari OFDMA?"
                , "A.  Pemakaian rentang frekuensi yang lebih kecil, Kuat threads frequency selective fading, tidak sensitive terhadap delay spread."
                , "B. Memiliki sensitivikasi yang tinggi terhadap CFO yang disebabkan oleh jitter, Teknologi OFDMA menggunakan system multi-frekuensi dan multi-amplitudo, Menentukan start poin memulai operasi fast fourier transform (FFT)."
                , "C. Kuat terhadap frequency selective fading, memiliki sensitifikasi yang tinggi terhadap CFO yang disebabkan oleh jiter, tahan terhadap ISI dan ICI akibat multipath delay untuk meningkatkan level QoS."
                ,"D. Mempermudah processing sinyal pada kondisi multipath, yaitu saat beberapa sinyal datang pada penerima antenna. Tidak sensitive terhadap delay spread, memiliki sensitivikasi yang tinggi terhadap CFO yang disebabkan oleh jitter."
                , 1);
        addQuestion(q3);
        Question q4 = new Question("Berikut Penjelasan yang benar tentang Code Division Multiple Access (CDMA) adalah"
                , "A. Dukungan perangkat dengan band frekuensi yang berbedadidorong oleh kemampuan hardware."
                , "B. Dapat mengurangi efek dari Multipath Fading yangmerugikan"
                , "C. Menggunakan sistem pada saat yang samamenggunakan frekuensi dan waktu secara bersamaan."
                ,"D. Jumlah timeslots dalam bingkai TDMA tergantung padasistem."
                , 3);
        addQuestion(q4);
        Question q5 = new Question("Berikut Penjelasan yang benar tentang Time Division Multiple Access (TDMA) adalah"
                , "A. Diberikan tambahan guard band antara saluran sehinggaakan mampu mengurangi interference"
                , "B. Setiap transmisi dipisahkan menggunakan kodepenyaluran unik yang direpresentasikan oleh power"
                , "C. Dapat diimplementasikan pada berbagai spektrum frekuensidengan sedikit saja modifikasi pada sistem"
                ,"D. Bandwidth saluran dibagi dalam domain waktu. Inimemberikan alokasi spektrum sempit untuk setiappengguna."
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

