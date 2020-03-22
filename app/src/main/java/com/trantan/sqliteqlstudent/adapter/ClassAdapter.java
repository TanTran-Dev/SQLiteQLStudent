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
import com.trantan.sqliteqlstudent.model.Class;

import java.util.List;

public class ClassAdapter extends ArrayAdapter {
    private Context context;
    private int resource;
    private List<Class> listClass;

    public ClassAdapter(@NonNull Context context, int resource, List<Class> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.listClass = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ClassHolder holder;
        if (convertView == null){
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_class, parent, false);
            holder = new ClassHolder();
            holder.txtCode = convertView.findViewById(R.id.txtClassCode);
            holder.txtName = convertView.findViewById(R.id.txtClassName);

            convertView.setTag(holder);
        }else {
            holder = (ClassHolder) convertView.getTag();
        }

        Class classStudent = listClass.get(position);
        holder.txtCode.setText(classStudent.getmCode());
        holder.txtName.setText(classStudent.getmName());

        return convertView;
    }

    public class ClassHolder{
        TextView txtCode;
        TextView txtName;
    }
}
