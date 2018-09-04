package hvcnbcvt_uddd.study_english.Data.Model;

import java.io.Serializable;

public class Word implements Serializable{
    private int mId;
    private String mWordEnglish;
    private String mWordVietnamese;
    private String mQuestionA;
    private String mQuestionB;
    private String mQuestionC;
    private int mUnit;
    private String mResult;


    public Word() {
    }

    public Word(int mId, String mWordEnglish, String mWordVietnamese, String mQuestionA, String mQuestionB, String mQuestionC, int mUnit, String mResult) {
        this.mId = mId;
        this.mWordEnglish = mWordEnglish;
        this.mWordVietnamese = mWordVietnamese;
        this.mQuestionA = mQuestionA;
        this.mQuestionB = mQuestionB;
        this.mQuestionC = mQuestionC;
        this.mUnit = mUnit;
        this.mResult = mResult;
    }

    public int getId() {
        return mId;
    }

    public void setId(int id) {
        mId = id;
    }

    public String getWordEnglish() {
        return mWordEnglish;
    }

    public void setWordEnglish(String wordEnglish) {
        mWordEnglish = wordEnglish;
    }

    public String getWordVietnamese() {
        return mWordVietnamese;
    }

    public void setWordVietnamese(String wordVietnamese) {
        mWordVietnamese = wordVietnamese;
    }

    public String getQuestionA() {
        return mQuestionA;
    }

    public void setQuestionA(String questionA) {
        mQuestionA = questionA;
    }

    public String getQuestionB() {
        return mQuestionB;
    }

    public void setQuestionB(String questionB) {
        mQuestionB = questionB;
    }

    public String getQuestionC() {
        return mQuestionC;
    }

    public void setQuestionC(String questionC) {
        mQuestionC = questionC;
    }

    public int getUnit() {
        return mUnit;
    }

    public void setUnit(int unit) {
        mUnit = unit;
    }

    public String getmResult() {
        return mResult;
    }

    public void setmResult(String mResult) {
        this.mResult = mResult;
    }
}
