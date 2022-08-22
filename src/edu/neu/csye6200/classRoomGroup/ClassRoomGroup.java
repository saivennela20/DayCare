/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.neu.csye6200.classRoomGroup;

import edu.neu.csye6200.Person;
import edu.neu.csye6200.student.Student;
import edu.neu.csye6200.student.StudentController;
import edu.neu.csye6200.teacher.Teacher;
import edu.neu.csye6200.teacher.TeacherController;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author dheeraj
 */
public class ClassRoomGroup {
    private static final Logger LOGGER = Logger.getLogger(ClassRoomGroup.class.getName());
    private int id;
    private Person teacher;
    private List<Person> students ;
    private AgeGroup ageGroup;
    private boolean isAssigned = false;
    
    
    public static enum AgeGroup {
        SIX_TWELVE(0, "6-12",4,3,6,12),
        THIRTEEN_TWENTYFOUR(1,"13-24",5,3,13,24),
        TWNTYFIVE_THITYFIVE(2,"25-35",6,3,25,35),
        THIRTYSIX_FOURTYSEVEN(3,"36-47",8,3,36,47),
        FOURTYEIGHT_FIFTYNINE(4,"48-59",12,2,48,59),
        SIXTY_ABOVE(5,"60 on up",15,2,60,-1);

        private static Map map = new HashMap();

        static {
            for (AgeGroup type : AgeGroup.values()) {
                map.put(type.value, type);
            }
        }
        private final int value;
        private final String displayValue;
        private final int studentCount;
        private final int groupCountPerRoom;
        private final int lowerBound;
        private final int upperBound;

        private AgeGroup(int value,String displayValue, int studentCount,int maxGroupsPerRom, int lowerBound, int upperBound) {
            this.value = value;
            this.displayValue = displayValue;
            this.studentCount = studentCount;
            this.groupCountPerRoom = maxGroupsPerRom;
            this.lowerBound = lowerBound;
            this.upperBound = upperBound;
        }

        public static AgeGroup valueOf(int val) {
            System.out.print(val);
            return (AgeGroup) map.get(val);
        }
        
//        public static AgeGroup valueOf(String val) {
//            return this.valueOf(Integer.parseInt(val));
//        }

        public int getValue() {
            return value;
        }
        public String getDisplayValue()
        {
            return this.displayValue;
        }
        public int getStudentCount()
        {
            return this.studentCount;
        }
        public int getGroupCount(){
            return this.groupCountPerRoom;
        }
        
        public boolean isSatisfied(int val)
        {
            if(this.lowerBound !=-1 && val<this.lowerBound){
                return false;
            }
            else if(this.upperBound !=-1 && val> this.upperBound)
            {
                return false;
            }
            return true;
        }
    }
    
    ClassRoomGroup(String csvString,TeacherController teacherController, StudentController studentController)
    {
        this.id = -1;
        this.teacher = null;
        this.students = new ArrayList<>();
        
        String[] data = csvString.split(",");
        
        try{
            this.id = Integer.parseInt(data[0]);
        }
        catch(Exception e)
        {
            LOGGER.log(Level.SEVERE,"error while parsing the classroomgroup id",e);
        }
        
        try{
            this.teacher = teacherController.getTeacherById(Integer.parseInt(data[1]));
            this.teacher.setIsAssigned(true);
        }
        catch(Exception e)
        {
            LOGGER.log(Level.SEVERE,"error while creating the teacher ",e);
        }
        
        try
        {
            Arrays.asList(data[2].split("_")).stream()
                    .map(x -> studentController.getStudentById(Integer.parseInt(x)))
                    .forEach(x -> {x.setIsAssigned(true);students.add(x);});
        }
        catch(Exception e)
        {
            LOGGER.log(Level.SEVERE,"error while creating students",e);
        }    
        
        try{
            this.ageGroup = AgeGroup.valueOf(Integer.parseInt(data[3]));
        }
        catch(Exception e){
            throw new RuntimeException("error while getting age group");
        }
        if(ageGroup.getStudentCount()<students.size())
        {
            throw new RuntimeException("Student to teacher ratio is exceeded");
        }
    }
    protected ClassRoomGroup(int id, Teacher teacher, List<Person> students, AgeGroup ageGroup)
    {
        if(ageGroup.getGroupCount()<students.size())
        {
            throw new RuntimeException("Student to teacher ratio is exceeded");
        }
        this.id = id;
        this.teacher = teacher;
        this.students = students;
        this.ageGroup = ageGroup;
        this.teacher.setIsAssigned(true);
        students.stream().forEach(x -> x.setIsAssigned(true));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Person getTeacher() {
        return teacher;
    }

    public void setTeacher(Person teacher) {
        this.teacher = teacher;
    }

    public List<Person> getStudents() {
        return students;
    }

    public void setStudents(List<Person> students) {
        this.students = students;
    }

    public boolean getIsAssigned() {
        return isAssigned;
    }

    public void setIsAssigned(boolean isAssigned) {
        this.isAssigned = isAssigned;
    }
    @Override
    public String toString()
    {
        return ((Integer)this.getId()).toString();
    }
    public String toCSV()
    {
        StringBuilder studentIds = new StringBuilder();
        this.getStudents().stream()
                .map(x -> ((Student)x).getSId())
                .forEach(x -> studentIds.append(x).append("_"));
        String substring = studentIds.toString().substring(0, studentIds.toString().length()-1);
        return this.getId()+","+((Integer)((Teacher)this.getTeacher()).getTId()).toString()+","+substring+","+this.getAgeGroup().getValue();
    }

    public AgeGroup getAgeGroup() {
        return ageGroup;
    }

    public void setAgeGroup(AgeGroup ageGroup) {
        this.ageGroup = ageGroup;
    }
    
    
    
}
