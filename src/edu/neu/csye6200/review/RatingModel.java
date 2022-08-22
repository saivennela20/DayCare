/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.neu.csye6200.review;

import edu.neu.csye6200.FileUtil;
import edu.neu.csye6200.Person;
import edu.neu.csye6200.student.StudentController;
import edu.neu.csye6200.teacher.TeacherController;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author dheeraj
 */
public class RatingModel {
    private final String filePath;
    List<Rating> ratings = new ArrayList<Rating>();
    public RatingModel(String inputFilePath, StudentController studentController, TeacherController teacherController)
    {
        this.filePath = inputFilePath;
        FileUtil.getFileData(this.filePath).stream().filter(x -> !x.equals("")).map(x -> new Rating(x,studentController,teacherController)).forEach(ratings::add);
    }
    
    void addRating(Person student, Person teacher, int rating, Date date)
    {
        Rating ratingObj = new Rating(student, teacher,rating,date);
        this.ratings.add(ratingObj);
        FileUtil.appendEntryToFile(this.filePath, ratingObj.toString());
    }
    
    
    
    List<Rating> getRatings()
    {
        return this.ratings;
    }
}
