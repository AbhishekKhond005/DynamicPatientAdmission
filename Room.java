import java.util.ArrayList;
import java.util.List;

class Room {
    public String roomName;
    public String roomCategory;
    public List<Bed> beds;
    public String department;
    public String specialism;

    public Room(String roomName, String roomCategory, String roomDepartment, String specialism) {
        this.roomName = roomName;
        this.roomCategory = roomCategory;
        this.department = roomDepartment;
        this.specialism = specialism;
        this.beds = new ArrayList<>();
    }

    public String getRoomName() {
        return roomName;
    }

    public String getRoomCategory() {
        return roomCategory;
    }

    public List<Bed> getBeds() {
        return beds;
    }

    public void addBed(Bed bed) {
        beds.add(bed);
    }
}