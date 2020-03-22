package com.trantan.sqliteqlstudent.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.trantan.sqliteqlstudent.R;
import com.trantan.sqliteqlstudent.model.Student;

import java.util.List;

public class StudentAdapter extends ArrayAdapter {
    private Context context;
    private int resource;
    private List<Student> listStudent;

    public StudentAdapter(@NonNull Context context, int resource, List<Student> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.listStudent = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        StudentHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_student, parent, false);
            holder = new StudentHolder();

            holder.txtCode = convertView.findViewById(R.id.txtStudentCode);
            holder.txtName = convertView.findViewById(R.id.txtStudentName);
            holder.txtPhoneNumber = convertView.findViewById(R.id.txtPhone);

            convertView.setTag(holder);
        }else {
            holder = (StudentHolder) convertView.getTag();
        }

        Student student = listStudent.get(position);

        holder.txtCode.setText(student.getmCode());
        holder.txtName.setText(student.getmName());
        holder.txtPhoneNumber.setText(student.getmPhone());

        return convertView;
    }

    public class StudentHolder {
        TextView txtCode;
        TextView txtName;
        TextView txtPhoneNumber;
    }
}
