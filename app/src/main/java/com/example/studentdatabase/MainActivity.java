package com.example.studentdatabase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText etName, etProgram, etStartYear, etGraduationYear;
    Button btnAdd;
    ListView lvStudentList;
    StudentDao studentDao;
    ArrayAdapter studentArrayAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etName = findViewById(R.id.etName);
        etProgram = findViewById(R.id.etProgram);
        etStartYear = findViewById(R.id.etStartYear);
        etGraduationYear = findViewById(R.id.etGraduationYear);
        btnAdd = findViewById(R.id.btnAdd);
        lvStudentList = findViewById(R.id.lvStudentList);

        studentDao = new StudentDao(MainActivity.this);

        showStudentsOnListView(studentDao.getAllStudents());

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Student student;

                try {

                    student = new Student(-1, etName.getText().toString(),
                            etProgram.getText().toString(),
                            Integer.parseInt(etStartYear.getText().toString()),
                            Integer.parseInt(etGraduationYear.getText().toString()));
                }
                catch (Exception e){
                    Toast.makeText(MainActivity.this, "Missing input", Toast.LENGTH_SHORT).show();
                    student = new Student(-1, "error", "error", 0, 0);
                }

                StudentDao studentDao = new StudentDao(MainActivity.this);

                studentDao.addStudent(student);

                showStudentsOnListView(studentDao.getAllStudents());

                Toast.makeText(MainActivity.this, "Added new student", Toast.LENGTH_SHORT).show();

            }
        });

        lvStudentList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Student student = (Student) adapterView.getItemAtPosition(position);
                studentDao.deleteStudent(student);
                showStudentsOnListView(studentDao.getAllStudents());
                Toast.makeText(MainActivity.this, "Deleted student", Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showStudentsOnListView(List<Student> allStudents) {
        studentArrayAdapter = new ArrayAdapter<Student>(MainActivity.this, android.R.layout.simple_list_item_1, allStudents);
        lvStudentList.setAdapter(studentArrayAdapter);
    }
}