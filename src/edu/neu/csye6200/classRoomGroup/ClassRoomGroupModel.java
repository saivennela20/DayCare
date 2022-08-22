/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.neu.csye6200.classRoomGroup;

import edu.neu.csye6200.FileUtil;
import edu.neu.csye6200.Person;
import edu.neu.csye6200.classRoomGroup.ClassRoomGroup.AgeGroup;
import edu.neu.csye6200.student.StudentController;
import edu.neu.csye6200.teacher.Teacher;
import edu.neu.csye6200.teacher.TeacherController;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author dheeraj
 */
public class ClassRoomGroupModel {

    private final List<ClassRoomGroup> groups;
    private final String inputFilePath;
    public ClassRoomGroupModel(String inputFilePath,TeacherController teacherController, StudentController studentController) {
        groups = new ArrayList<>();
        this.inputFilePath = inputFilePath;
        FileUtil.getFileData(inputFilePath).stream().map(x -> new ClassRoomGroup(x,teacherController, studentController)).forEach(groups::add);
    }
    
    protected void addClassRoomGroup(ClassRoomGroup group)
    {
        this.groups.add(group);
        FileUtil.appendEntryToFile(this.inputFilePath, group.toCSV());
    }
    
    protected void removeClassGroup(ClassRoomGroup group)
    {
        this.groups.remove(group);
        group.getTeacher().setIsAssigned(false);
        group.getStudents().stream().forEach(x -> x.setIsAssigned(false));
        FileUtil.removeEntryInFile(inputFilePath, group.toCSV());
    }
    
    void addClassRoomGroup(int id, Teacher teacher, List<Person> students, AgeGroup ageGroup)
    {
        ClassRoomGroup group = new ClassRoomGroup(id, teacher, students,ageGroup );
        this.groups.add(group);
        FileUtil.appendEntryToFile(this.inputFilePath, group.toCSV());
    }
    
    protected void updateClassRoomGroup(ClassRoomGroup oldGroup,ClassRoomGroup newGroup)
    {
        this.groups.remove(oldGroup);
        oldGroup.getStudents().stream().forEach(x -> x.setIsAssigned(false));
        this.groups.add(newGroup);
        newGroup.getStudents().stream().forEach(x -> x.setIsAssigned(true));
        FileUtil.modifyEntryInFile(inputFilePath, oldGroup.toCSV(), newGroup.toCSV());
    }
    
    protected List<ClassRoomGroup> getUnassignedClassRoomGroup()
    {
        List<ClassRoomGroup> list = new ArrayList<>();
        this.groups.stream().filter(x -> !x.getIsAssigned()).forEach(list::add);
        return list;
    }
    
    protected List<ClassRoomGroup> getClassRoomGroup()
    {
        return this.groups;
    }
    
}
