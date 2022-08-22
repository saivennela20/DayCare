package edu.neu.csye6200.classRoom;

import edu.neu.csye6200.FileUtil;
import edu.neu.csye6200.classRoomGroup.ClassRoomGroup;
import edu.neu.csye6200.classRoomGroup.ClassRoomGroupController;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;

public class ClassRoomController {

    ClassRoomModel model;
    ClassRoomView view;

    public ClassRoomController(ClassRoomModel model, ClassRoomView view) {
        this.model = model;
        this.view = view;
    }

    public ClassRoom getClassRoom(int id) {
        return this.model.getClassRooms().stream().filter(x -> x.getId() == id).findFirst().get();
    }

    public void addClassRoom(ClassRoom classRoom) {
        this.model.addClassRoom(classRoom);
    }
    public void addClassRoom(int id, String name, List<ClassRoomGroup> groups) {
        this.model.addClassRoom(id,name,groups);
    }

    public void updateView() {
        this.view.updateView();
    }

    public List<ClassRoom> searchClassRoom(Map<String, String> criteria) {
        List<ClassRoom> result = new ArrayList<>();
        Predicate<ClassRoom> filter = (x) -> {
            Iterator<String> ite = criteria.keySet().iterator();
            while (ite.hasNext()) {
                String type = ite.next();
                String value = criteria.get(type);
                switch (type) {
                    case "ID":
                        if (!(Integer.toString(x.getId()).toLowerCase().contains(value))) {
                            return false;
                        }
                        break;
                    case "NAME":
                        if (!(x.getName().toLowerCase().contains(value))) {
                            return false;
                        }
                        break;
                }
            }
            return true;
        };
        this.model.getClassRooms().stream().filter(filter).forEach(result::add);
        return result;
    }

    public void removeClassRoom(ClassRoom classRoom) {
        this.model.removeClassRoom(classRoom);
        this.view.updateView();
    }

    public void updateClassRoom(ClassRoom classRoom, int id, String name, List<ClassRoomGroup> groups) {
        ClassRoom tempClassRoom = new ClassRoom(id, name, groups);
        this.model.updateClassRoom(classRoom, tempClassRoom);
    }

    public List<ClassRoom> getUnassignedClassRoom() {
        return this.model.getUnassignedClassRoom();
    }

    public void loadClassRoomFromCSV(String filePath, ClassRoomGroupController classRoomGroupController) {
        FileUtil.getFileData(filePath).stream().forEach(x -> this.model.addClassRoom(x, classRoomGroupController));
    }
}
