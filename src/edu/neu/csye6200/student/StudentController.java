package edu.neu.csye6200.student;

import edu.neu.csye6200.FileUtil;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

import edu.neu.csye6200.Person;
import edu.neu.csye6200.classRoomGroup.ClassRoomGroup.AgeGroup;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

public class StudentController {

    StudentModel model;
    StudentView view;

    public StudentController(StudentModel model, StudentView view) {
        this.model = model;
        this.view = view;
    }

    public void addStudent(Person p) {
        this.model.addStudent(p);
        this.view.updateView();
    }

    public List<Person> searchStudent(Map<String, String> criteria) {
        List<Person> result = new ArrayList<>();
        Predicate<Person> filter = (x) -> {
            Iterator<String> ite = criteria.keySet().iterator();
            while (ite.hasNext()) {
                String type = ite.next();
                String value = criteria.get(type);
                switch (type) {
                    case "ID":
                        if (!(Integer.toString(((Student)x).getSId()).toLowerCase().contains(value))) {
                            return false;
                        }
                        break;
                    case "NAME":
                        if (!(x.getName().toLowerCase().contains(value))) {
                            return false;
                        }
                        break;
                    case "PARENT_NAME":
                        if (!(x.getParentName().toLowerCase().contains(value))) {
                            return false;
                        }
                        break;
                    case "PHONE_NUMBER":
                        if (!(String.valueOf(x.getPhoneNumber()).contains(value))) {
                            return false;
                        }
                        break;
                    case "STATUS":
                    return (criteria.get("STATUS").equals("TRUE")) && (((Student)x).getLastRenewedDate().before(Calendar.getInstance().getTime()));

                }
            }
            return true;
        };
        this.model.getStudents().stream().filter(filter).forEach(result::add);
        return result;
    }
    
    public void removeStudent(Person s)
    {
        this.model.removeStudent(s);
        this.view.updateView();
    }
    
    public void modifyStudent(Student s, String name, String parentName, long phoneNumber, String address,int age,double gpa, int sid,Date renewedDate)
    {
        Student tempStudent = new Student(name, parentName, phoneNumber, address,age,gpa,sid,renewedDate);
        this.model.updateStudent(s, tempStudent);
        this.view.updateView();
    }
    
    public void modifyStudent(Student oldStudent, Student newStudent)
    {
        this.model.updateStudent(oldStudent, newStudent);
        this.view.updateView();
    }
    
    public List<Person> getUnassignedStudent()
    {
        return this.model.getUnassignedStudentList();
    }
    public void loadStudentFromCSV(String filePath)
    {
        FileUtil.getFileData(filePath).stream().map(x -> new Student(x)).forEach(this.model::addStudent);
    }
    
    public Person getStudentById(int id)
    {
        Optional<Person> temp = this.model.getStudents().stream().filter(x -> ((Student)x).getSId()==id).findFirst();
        return temp.get();
    }
    
    public List<Person> getStudentList()
    {
        return this.model.getStudents();
    }
    
    public List<Person> getUnAssignedStudentListBasedOnAgeGroup(AgeGroup ageGroup)
    {
        List<Person> list = new ArrayList();
        this.model.getUnassignedStudentList().stream().filter( x -> ageGroup.isSatisfied(x.getAge())).forEach((x) -> list.add(x));
        return list;
    }
    

}
