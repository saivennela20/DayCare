/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.neu.csye6200.review;

import edu.neu.csye6200.Person;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 *
 * @author dheeraj
 */
public class RatingController {
    
    RatingModel model;
    RatingView view;

    public RatingController(RatingModel model, RatingView view) {
        this.model = model;
        this.view = view;
    }
    
    public void addRating(Person student, Person teacher, int rating, Date date)
    {
        this.model.addRating(student, teacher, rating, date);
    }
    
    public Map<Person, Float> getAllProfessorRatingsByYear(String year)
    {
        int val = Integer.parseInt(year);
        Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("US/Eastern"));
        Map<Person, Float> ratingMap = new HashMap<>();
        Map<Person, Integer> ratingCount = new HashMap<>();
//        cal.setTime(date);
//        int year = cal.get(Calendar.YEAR);
        this.model.getRatings().stream()
                .filter(x -> {
                    cal.setTime(x.getDate());
                    return cal.get(Calendar.YEAR)==val;})
                .forEach(x -> {
                    Float rating = ratingMap.get(x.getTeacher());
                    rating = rating==null?0:rating;
                    rating+=x.getRating();
                    ratingMap.put(x.getTeacher(), rating);
                    
                    Integer count = ratingCount.get(x.getTeacher());
                    count = count==null?0:count;
                    count+=1;
                    ratingCount.put(x.getTeacher(), count);
                });
        ratingMap.keySet().stream()
                .forEach(x -> {
                    ratingMap.put(x, (ratingMap.get(x)/ratingCount.get(x)));
        });
        
        return ratingMap;
    }
    
    
}
