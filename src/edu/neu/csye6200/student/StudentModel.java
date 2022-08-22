package edu.neu.csye6200.student;

import java.util.ArrayList;
import java.util.List;

import edu.neu.csye6200.FileUtil;
import edu.neu.csye6200.Person;

public class StudentModel {

    private List<Person> students = null;
    private String fileInput;

    public StudentModel(String filePath) {
        this.fileInput = filePath;
        students = new ArrayList<>();
        populate();
    }

    private void populate() {
        FileUtil.getFileData(this.fileInput).stream().map((x) -> new Student(x)).forEach(students::add);
    }

    protected void addStudent(Person person) {
        students.add(person);
        FileUtil.appendEntryToFile(fileInput, person.toCSV());
    }

    protected List<Person> getStudents() {
        return students;
    }
    
    protected void removeStudent(Person s)
    {
        students.remove(s);
        FileUtil.removeEntryInFile(fileInput, s.toCSV());
    }
    
    protected void updateStudent(Person oldStudent, Person newStudent)
    {
        students.remove(oldStudent);
        students.add(newStudent);
        FileUtil.modifyEntryInFile(fileInput,oldStudent.toCSV(), newStudent.toCSV());
    }
    
    protected List<Person> getUnassignedStudentList()
    {
        List<Person> list = new ArrayList<>();
        students.stream().filter(x -> !x.getIsAssigned()).forEach(list::add);
        return list;
    }
}
