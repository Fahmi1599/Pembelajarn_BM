package com.skiripsi.pembelajaranbm.ui;

public class Essay {

    private String Jawaban;
    private String soal;
    private String userID;
    private String pushKey;
    private String skor;

    public String getJawaban() {
        return Jawaban;
    }

    public void setJawaban(String jawaban) {
        Jawaban = jawaban;
    }

    public String getSoal() {
        return soal;
    }

    public void setSoal(String soal) {
        this.soal = soal;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPushKey() {
        return pushKey;
    }

    public void setPushKey(String pushKey) {
        this.pushKey = pushKey;
    }

    public String getSkor() {
        return skor;
    }

    public void setSkor(String skor) {
        this.skor = skor;
    }
}
