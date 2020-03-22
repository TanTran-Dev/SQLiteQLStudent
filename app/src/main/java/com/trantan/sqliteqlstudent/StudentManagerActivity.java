package com.trantan.sqliteqlstudent;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.trantan.sqliteqlstudent.adapter.StudentAdapter;
import com.trantan.sqliteqlstudent.data.DBManager;
import com.trantan.sqliteqlstudent.model.Student;

import java.util.List;

public class StudentManagerActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener {
    private EditText edtStudentCode;
    private EditText edtStudentName;
    private EditText edtPhone;
    private Button btnAdd;

    private ListView lvStudent;
    private List<Student> listStudent;
    private StudentAdapter adapter;

    private DBManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_manager);

        dbManager = new DBManager(this);
        listStudent = dbManager.getAllStudent();
        initView();
        setAdapter();
        events();


    }

    private void events() {
        btnAdd.setOnClickListener(this);
        lvStudent.setOnItemLongClickListener(this);
    }

    private void initView() {
        edtStudentCode = findViewById(R.id.edtStudentCode);
        edtStudentName = findViewById(R.id.edtStudentName);
        edtPhone = findViewById(R.id.edtPhone);
        btnAdd = findViewById(R.id.btnAddStudent);
        lvStudent = findViewById(R.id.lvStudent);
    }

    private Student createStudent() {
        String code = edtStudentCode.getText().toString();
        String name = edtStudentName.getText().toString();
        int phone = Integer.parseInt(edtPhone.getText().toString());

        Student student = new Student(code, name, phone);

        return student;
    }

    private void setAdapter() {
        if (adapter == null) {
            adapter = new StudentAdapter(this, R.layout.item_student, listStudent);
            lvStudent.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
            lvStudent.setSelection(adapter.getCount() - 1);
        }
    }

    private void clearEditText() {
        edtStudentCode.setText("");
        edtStudentName.setText("");
        edtPhone.setText("");
    }

    private void refreshListView() {
        listStudent.clear();
        listStudent.addAll(dbManager.getAllStudent());
        if (adapter != null) {
            adapter.notifyDataSetChanged();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnAddStudent:
                if (edtStudentCode.getText().toString().isEmpty()
                        || edtStudentName.getText().toString().isEmpty()
                        || edtPhone.getText().toString().isEmpty()) {
                    Toast.makeText(this, "Vui long nhap day du thong tin", Toast.LENGTH_SHORT).show();
                } else {
                    Student student = createStudent();
                    dbManager.addStudent(student);
                    clearEditText();
                }
                refreshListView();
                setAdapter();
                break;
        }
    }

    private int index = -1;

    @Override
    public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
        index = position;
        AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
        builder.setTitle("Delete!");
        builder.setIcon(android.R.drawable.ic_dialog_alert);
        builder.setMessage("Do you want delete this item")
                .setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Student building = listStudent.get(index);
                        int result = dbManager.deleteStudent(building);
                        if (result > 0) {
                            Toast.makeText(getApplicationContext(), "Delete Successfully", Toast.LENGTH_LONG).show();
                            refreshListView();
                        } else {
                            Toast.makeText(getApplicationContext(), "Delete failed", Toast.LENGTH_LONG).show();
                        }
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        builder.show();

        return false;
    }
}
