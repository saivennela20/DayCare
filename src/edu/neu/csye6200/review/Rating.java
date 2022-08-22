/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.neu.csye6200.review;

import edu.neu.csye6200.Person;
import edu.neu.csye6200.student.Student;
import edu.neu.csye6200.student.StudentController;
import edu.neu.csye6200.teacher.Teacher;
import edu.neu.csye6200.teacher.TeacherController;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author dheeraj
 */
public class Rating {
    private static final DateFormat DATE_FORMAT= new SimpleDateFormat("MM-dd-yyyy");
    private int rating;
    private Person teacher;
    private Person student;
    private Date date;
    Rating(Person student, Person teacher, int rating, Date date) {
        this.student = student;
        this.teacher = teacher;
        this.rating = rating;
        this.date = date;
    }

    Rating(String csvString, StudentController studentController, TeacherController teacherController) {
        
        String[] data = csvString.split(",");
        
        try{
            this.student = studentController.getStudentById(Integer.parseInt(data[0]));
        }
        catch(Exception e)
        {
            throw new RuntimeException("error while getting student");
        }
        
        try{
            this.teacher = teacherController.getTeacherById(Integer.parseInt(data[1]));
        }
        catch(Exception e)
        {
            throw new RuntimeException("error while getting teacher");
        }
        try{
            this.rating = Integer.parseInt(data[2]);
        }
        catch(Exception e)
        {
            throw new RuntimeException("error while getting ratting");
        }
        
        try{
            
            this.date = DATE_FORMAT.parse(data[3]);
        }
        catch(Exception e)
        {
            throw new RuntimeException("error while getting ratting date");
        }
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public Person getTeacher() {
        return teacher;
    }

    public void setTeacher(Person teacher) {
        this.teacher = teacher;
    }

    public Person getStudent() {
        return student;
    }

    public void setStudent(Person student) {
        this.student = student;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    @Override
    public String toString()
    {
        return ((Student)(this.getStudent())).getSId()+","+((Teacher)(this.getTeacher())).getTId()+","+this.getRating()+","+DATE_FORMAT.format(this.getDate());
    }
}
