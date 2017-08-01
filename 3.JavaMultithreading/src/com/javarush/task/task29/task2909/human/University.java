package com.javarush.task.task29.task2909.human;

import java.util.ArrayList;
import java.util.List;

public class University {

    private String name;
    private int age;
    private List<Student> students = new ArrayList();

    public List getStudents() {
        return students;
    }

    public void setStudents(List students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public University(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public Student getStudentWithAverageGrade(double value) {

        for ( Student stud : students){
            if ( stud.getAverageGrade() == value){
                return stud;
            }
        }
        return null;
    }

    public Student getStudentWithMaxAverageGrade() {

        double max = students.get(0).getAverageGrade();
        Student student = students.get(0);
        for ( Student stud : students){
            if ( stud.getAverageGrade() > max){
                max = stud.getAverageGrade(); student = stud;
            }
        }
        return student;
    }

    public Student getStudentWithMinAverageGrade(){
        double min = students.get(0).getAverageGrade();
        Student student = students.get(0);
        for ( Student stud : students){
            if ( stud.getAverageGrade() < min){
                min = stud.getAverageGrade(); student = stud;
            }
        }
        return student;
    }

    public void expel(Student student){
        students.remove(student);
    }
}