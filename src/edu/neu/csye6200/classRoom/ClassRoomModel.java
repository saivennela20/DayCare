package edu.neu.csye6200.classRoom;

import java.util.ArrayList;
import java.util.List;

import edu.neu.csye6200.FileUtil;
import edu.neu.csye6200.classRoomGroup.ClassRoomGroup;
import edu.neu.csye6200.classRoomGroup.ClassRoomGroupController;

public class ClassRoomModel {

    private final List< ClassRoom> data = new ArrayList<>();
    private String inputData = "edu/neu/csye6200/inputData/classRoom.txt";
    

    public ClassRoomModel(String filePath) {
        this.inputData = filePath;
        FileUtil.getFileData(this.inputData).stream().map(x->new ClassRoom(x)).forEach(data::add);
    }
    
    public ClassRoomModel(String filePath, ClassRoomGroupController classRoomGroupController) {
        this.inputData = filePath;
        FileUtil.getFileData(this.inputData).stream().map(x->new ClassRoom(x,classRoomGroupController )).forEach(data::add);
    }

    protected void addClassRoom(int id, String name, List<ClassRoomGroup> groups)
    {
        ClassRoom classRoom = new ClassRoom(id, name, groups);
        this.addClassRoom(classRoom);
    }
    protected void addClassRoom(ClassRoom classRoom) {
        this.data.add(classRoom);
        FileUtil.appendEntryToFile(inputData, classRoom.toCSV());
    }
    protected List<ClassRoom> getClassRooms(){
            return data;
    }
    
    protected void removeClassRoom(ClassRoom classRoom)
    {
        this.data.remove(classRoom);
        classRoom.getGroups().stream().forEach(x -> x.setIsAssigned(false));
        FileUtil.removeEntryInFile(inputData, classRoom.toCSV());
    }
    
    protected void updateClassRoom(ClassRoom oldClass, ClassRoom newClass)
    {
        this.data.remove(oldClass);
        oldClass.getGroups().stream().forEach(x -> x.setIsAssigned(false));
        this.data.add(newClass);
        newClass.getGroups().stream().forEach(x -> x.setIsAssigned(true));
        FileUtil.modifyEntryInFile(inputData, oldClass.toCSV(), newClass.toCSV());
    }
        
    protected List<ClassRoom> getUnassignedClassRoom()
    {
        List<ClassRoom> list = new ArrayList<>();
        this.data.stream().filter(x -> !x.getIsFull()).forEach(list::add);
        return list;
    } 

    public void addClassRoom(String csvDataString, ClassRoomGroupController classRoomGroupController) 
    {
        this.addClassRoom(new ClassRoom(csvDataString, classRoomGroupController));
    }
}
