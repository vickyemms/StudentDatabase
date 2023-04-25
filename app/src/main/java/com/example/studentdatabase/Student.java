package com.example.studentdatabase;

public class Student {

    private int id;
    private String name;
    private String program;
    private int startYear;
    private int graduationYear;

    public Student(int id, String name, String program, int startYear, int graduationYear) {
        this.id = id;
        this.name = name;
        this.program = program;
        this.startYear = startYear;
        this.graduationYear = graduationYear;
    }

    public Student() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public int getStartYear() {
        return startYear;
    }

    public void setStartYear(int startYear) {
        this.startYear = startYear;
    }

    public int getGraduationYear() {
        return graduationYear;
    }

    public void setGraduationYear(int graduationYear) {
        this.graduationYear = graduationYear;
    }

    @Override
    public String toString() {
        return "id: " + id + " " +
                name + ", " + program + " " + startYear +
                "-" + graduationYear;
    }
}
