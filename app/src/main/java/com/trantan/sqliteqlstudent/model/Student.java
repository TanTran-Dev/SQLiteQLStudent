package com.trantan.sqliteqlstudent.model;

public class Student {
    private int mId;
    private String mCode;
    private String mName;
    private int mPhone;

    public Student(int mId, String mCode, String mName, int mPhone) {
        this.mId = mId;
        this.mCode = mCode;
        this.mName = mName;
        this.mPhone = mPhone;
    }

    public Student(String mCode, String mName, int mPhone) {
        this.mCode = mCode;
        this.mName = mName;
        this.mPhone = mPhone;
    }

    public Student() {
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

    public int getmPhone() {
        return mPhone;
    }

    public void setmPhone(int mPhone) {
        this.mPhone = mPhone;
    }
}
