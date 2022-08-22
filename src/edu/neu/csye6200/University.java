
package edu.neu.csye6200;

import edu.neu.csye6200.classRoom.ClassRoomController;
import edu.neu.csye6200.classRoom.ClassRoomModel;
import edu.neu.csye6200.classRoom.ClassRoomView;
import edu.neu.csye6200.classRoomGroup.ClassRoomGroupController;
import edu.neu.csye6200.classRoomGroup.ClassRoomGroupModel;
import edu.neu.csye6200.classRoomGroup.ClassRoomGroupView;
import edu.neu.csye6200.health.ImmunizationController;
import edu.neu.csye6200.health.ImmunizationModel;
import edu.neu.csye6200.health.ImmunizationView;
import edu.neu.csye6200.review.RatingController;
import edu.neu.csye6200.review.RatingModel;
import edu.neu.csye6200.review.RatingView;
import edu.neu.csye6200.student.StudentController;
import edu.neu.csye6200.student.StudentModel;
import edu.neu.csye6200.student.StudentView;
import edu.neu.csye6200.teacher.TeacherController;
import edu.neu.csye6200.teacher.TeacherModel;
import edu.neu.csye6200.teacher.TeacherView;

/*
 * @author dheeraj
 */

public class University {
    private final String STUDENT_CSV = "src/edu/neu/csye6200/inputData/studentData.txt"; 
    private final String TEACHER_CSV = "src/edu/neu/csye6200/inputData/teacherData.txt";
    private final String CLASSGROUP_CSV = "src/edu/neu/csye6200/inputData/classGroupData.txt";
    private final String CLASS_CSV = "src/edu/neu/csye6200/inputData/classData.txt";
    private final String VACCINE_CSV = "src/edu/neu/csye6200/inputData/vaccineData.txt";
    private final String IMMUNIZATION_CSV = "src/edu/neu/csye6200/inputData/immunizationData.txt";
    private final String RENEWAL_CSV = "src/edu/neu/csye6200/inputData/renewalData.txt";
    
    private String name;
    private final StudentController studentController;
    private final TeacherController teacherController ;
    private final ClassRoomGroupController classRoomGroupController;
    private final ClassRoomController classRoomController ;
    private final ImmunizationController immunizationController;
    private final RatingController ratingController;
    
    public University(String name){
        
        this.name = name;
        
        StudentModel studentModel = new StudentModel(STUDENT_CSV);
        StudentView studentView = new StudentView(); //need to add proper input;
        studentController = new StudentController(studentModel,studentView);
        
        TeacherModel teacherModel = new TeacherModel(TEACHER_CSV);
        TeacherView teacherView = new TeacherView();//need to add proper input;
        teacherController = new TeacherController(teacherModel,teacherView);
               
        ClassRoomGroupModel classRoomGroupModel = new ClassRoomGroupModel(CLASSGROUP_CSV,teacherController,studentController);
        ClassRoomGroupView classRoomGroupView = new ClassRoomGroupView();//need to add proper input;
        classRoomGroupController = new ClassRoomGroupController(classRoomGroupModel,classRoomGroupView);       
        
        ClassRoomModel classRoomModel = new ClassRoomModel(CLASS_CSV,classRoomGroupController);
        ClassRoomView classRoomView = new ClassRoomView();//need to add proper input;
        classRoomController  = new ClassRoomController(classRoomModel,classRoomView);
        
        ImmunizationModel immunizationModel = new ImmunizationModel(VACCINE_CSV,IMMUNIZATION_CSV,studentController);
        ImmunizationView immunizationView = new ImmunizationView();//need to add proper input;
        immunizationController = new ImmunizationController(immunizationModel, immunizationView);   
        
        RatingModel ratingModel = new RatingModel(RENEWAL_CSV,studentController,teacherController);
        RatingView ratingView = new RatingView();
        this.ratingController = new RatingController(ratingModel, ratingView);
        
        
        
    }

    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public StudentController getStudentController() {
        return this.studentController;
    }

    public TeacherController getTeacherController() {
        return this.teacherController;
    }

    public ClassRoomGroupController getClassRoomGroupController() {
        return this.classRoomGroupController;
    }

    public ClassRoomController getClassRoomController() {
        return this.classRoomController;
    }

    public ImmunizationController getImmunizationController() {
        return this.immunizationController;
    }

    public RatingController getRatingController() {
        return ratingController;
    }
    
}
