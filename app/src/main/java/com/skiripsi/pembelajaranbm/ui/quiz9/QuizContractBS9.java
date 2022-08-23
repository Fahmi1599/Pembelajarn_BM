package com.skiripsi.pembelajaranbm.ui.quiz9;

import android.provider.BaseColumns;

public class QuizContractBS9 {

    QuizContractBS9(){

    }

    public static class QuestionsTable implements BaseColumns {
        public static final String TABLE_NAME = "quiz_questions";
        public static final String COLUMN_QUESTION = "question";
        public static final String COLUMN_OPTION1 = "option1";
        public static final String COLUMN_OPTION2 = "option2";
        public static final String COLUMN_ANSWER_NR = "answer_nr";
    }
}