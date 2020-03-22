package com.trantan.sqliteqlstudent.model;

public class Class {
    private int mId;
    private String mCode;
    private String mName;

    public Class(int mId, String mCode, String mName) {
        this.mId = mId;
        this.mCode = mCode;
        this.mName = mName;
    }

    public Class(String mCode, String mName) {
        this.mCode = mCode;
        this.mName = mName;
    }

    public Class() {
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmCode() {
        return mCode;
    }

    public void setmCode(String mCode) {
        this.mCode = mCode;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
