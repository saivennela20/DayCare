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
public class ClassRoomGroupController {

    private final ClassRoomGroupModel model;
    private final ClassRoomGroupView view;

    public ClassRoomGroupController(ClassRoomGroupModel model, ClassRoomGroupView view) {
        this.model = model;
        this.view = view;
    }

    public ClassRoomGroup getClassRoomGroupById() {
        return null;
    }

    public ClassRoomGroup getClassRoomGroupById(int id) {
        return this.model.getClassRoomGroup().stream().filter(x -> x.getId() == id).findFirst().get();
    }

    public void addClassRoomGroup(ClassRoomGroup classRoomGroup) {
        this.model.addClassRoomGroup(classRoomGroup);
        this.view.updateView();
    }
    
    public void addClassRoomGroup(int id, Teacher teacher, List<Person> students, AgeGroup ageGroup) {
       
        this.model.addClassRoomGroup( id,  teacher,  students,ageGroup);
        this.view.updateView();
    }

    public void removeClassRoomGroup(ClassRoomGroup classRoomGroup) {
        this.model.removeClassGroup(classRoomGroup);
        this.view.updateView();
    }

    public void modifyClassRoomGroup(ClassRoomGroup classRoomGroup, int id, Teacher teacher, List<Person> students,AgeGroup ageGroup) {
        ClassRoomGroup tempClassRoomGroup = new ClassRoomGroup(id, teacher, students,ageGroup);
        this.model.updateClassRoomGroup(classRoomGroup, tempClassRoomGroup);
        this.view.updateView();
    }

    public void loadClassRoomGroupFromCSV(String filePath, TeacherController teacherController, StudentController studentController) {
        FileUtil.getFileData(filePath).stream().forEach(x -> this.model.addClassRoomGroup(new ClassRoomGroup(x, teacherController, studentController)));
        this.view.updateView();
    }

    public List<ClassRoomGroup> getUnassignedClassRoomGroup() {
        List<ClassRoomGroup> list = new ArrayList<>();
        this.model.getClassRoomGroup().stream().filter(x -> !x.getIsAssigned()).forEach(list::add);
        return list;
    }
    
    public List<ClassRoomGroup> getAllClassRoomGroup() {
        return this.model.getClassRoomGroup();
    }
    
    public List<ClassRoomGroup> getUnAssignedClassRoomGroupBasedOnAgeGroup(AgeGroup ageGroup)
    {
        List<ClassRoomGroup> list = new ArrayList();
        this.model.getUnassignedClassRoomGroup().stream().filter(x -> x.getAgeGroup() == ageGroup).forEach(list::add);
        return list;
    }
}
