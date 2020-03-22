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

import com.trantan.sqliteqlstudent.adapter.ClassAdapter;
import com.trantan.sqliteqlstudent.data.DBManager;
import com.trantan.sqliteqlstudent.model.Class;

import java.util.List;

public class ClassManagerActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemLongClickListener {
    private EditText edtClassCode;
    private EditText edtClassName;
    private Button btnAdd;
    private ListView lvClass;
    private ClassAdapter adapter;
    private List<Class> listClass;
    private DBManager dbManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_class_manager);

        dbManager = new DBManager(this);
        listClass = dbManager.getAllClass();

        initView();
        events();

        setAdapter();
    }

    private void events() {
        btnAdd.setOnClickListener(this);
        lvClass.setOnItemLongClickListener(this);
    }

    private void initView() {
        edtClassCode = findViewById(R.id.edtClassCode);
        edtClassName = findViewById(R.id.edtClassName);
        btnAdd = findViewById(R.id.btnAddClass);
        lvClass = findViewById(R.id.lvClass);
    }

    private Class createClass(){
        String code = edtClassCode.getText().toString();
        String name = edtClassName.getText().toString();

        Class classStudent = new Class(code, name);

        return classStudent;
    }

    private void setAdapter() {
        if (adapter == null) {
            adapter = new ClassAdapter(this, R.layout.item_class, listClass);
            lvClass.setAdapter(adapter);
        } else {
            adapter.notifyDataSetChanged();
            lvClass.setSelection(adapter.getCount() - 1);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnAddClass:
                if (edtClassCode.getText().toString().isEmpty() || edtClassName.getText().toString().isEmpty()){
                    Toast.makeText(this, "Vui long nhap day du thong tin", Toast.LENGTH_SHORT).show();
                }else {
                    Class classStudent = createClass();
                    dbManager.addClass(classStudent);
                    clearEditText();
                }
                refreshListView();
                setAdapter();
                break;
        }
    }

    private void clearEditText() {
        edtClassCode.setText("");
        edtClassName.setText("");
    }

    private void refreshListView() {
        listClass.clear();
        listClass.addAll(dbManager.getAllClass());
        if (adapter != null) {
            adapter.notifyDataSetChanged();
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
                        Class aClass = listClass.get(index);
                        int result = dbManager.deleteClass(aClass);
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
