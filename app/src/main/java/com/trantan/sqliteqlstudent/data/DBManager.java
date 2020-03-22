package com.trantan.sqliteqlstudent.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import com.trantan.sqliteqlstudent.model.Class;
import com.trantan.sqliteqlstudent.model.Student;

import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "dbStudent";

    private static final String TABLE_CLASS = "class_manager";
    private static final String ID_CLASS = "id_class";
    private static final String CLASS_CODE = "class_code";
    private static final String CLASS_NAME = "name";

    private static final String TABLE_STUDENT = "student_manager";
    private static final String ID_STUDENT = "id_student";
    private static final String STUDENT_NAME = "name";
    private static final String STUDENT_CODE = "student_code";
    private static final String PHONE = "phone";

    private static final String TAG = "DBManager";

    private String createClass = "CREATE TABLE IF NOT EXISTS " + TABLE_CLASS + " ("
            + ID_CLASS + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + CLASS_CODE + " TEXT,"
            + CLASS_NAME + " TEXT)";

    private String createStudent = "CREATE TABLE IF NOT EXISTS " + TABLE_STUDENT + " ("
            + ID_STUDENT + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + STUDENT_CODE + "TEXT, "
            + STUDENT_NAME + " TEXT, "
            + PHONE + " INTEGER)";

    public DBManager(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

        Log.d(TAG, "DBManager: ");
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(createClass);
        db.execSQL(createStudent);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_CLASS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_STUDENT);

        switch (oldVersion){
            case 1:
                db.execSQL(createClass);
                db.execSQL(createStudent);
            case 2:

        }
        onCreate(db);
    }


    public void addClass(Class pClass) {
        SQLiteDatabase database = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(CLASS_CODE, pClass.getmCode());
        contentValues.put(CLASS_NAME, pClass.getmName());

        database.insert(TABLE_CLASS, null, contentValues);
        database.close();

        Log.d(TAG, "addClass: ");
    }

    public void addStudent(Student pStudent) {
        SQLiteDatabase database = getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(STUDENT_CODE, pStudent.getmCode());
        contentValues.put(STUDENT_NAME, pStudent.getmName());
        contentValues.put(PHONE, pStudent.getmPhone());

        database.insert(TABLE_STUDENT, null, contentValues);
        database.close();

        Log.d(TAG, "addStudent: ");
    }

    public List<Class> getAllClass() {
        List<Class> listClass = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_CLASS;
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String code = cursor.getString(1);
                String name = cursor.getString(2);

                Class c = new Class(id, code, name);
                listClass.add(c);

            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();

        return listClass;
    }

    public List<Student> getAllStudent() {
        List<Student> listStudent = new ArrayList<>();

        String selectQuery = "SELECT * FROM " + TABLE_STUDENT;
        SQLiteDatabase database = getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String code = cursor.getString(1);
                String name = cursor.getString(2);
                int phone = cursor.getInt(3);

                Student s = new Student(id, code, name, phone);
                listStudent.add(s);

            } while (cursor.moveToNext());
        }
        cursor.close();
        database.close();

        return listStudent;
    }

    public int deleteClass(Class pClass) {
        SQLiteDatabase database = this.getWritableDatabase();

        return database.delete(TABLE_CLASS, ID_CLASS + "=?", new String[]{String.valueOf(pClass.getmId())});
    }

    public int deleteStudent(Student pStudent) {
        SQLiteDatabase database = this.getWritableDatabase();

        return database.delete(TABLE_STUDENT, ID_STUDENT + "=?", new String[]{String.valueOf(pStudent.getmId())});
    }
}
