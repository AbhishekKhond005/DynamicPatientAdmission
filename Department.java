import java.util.ArrayList;
import java.util.List;

class Department {
    public String departmentName;
    public int departmentId;
    public List<Room> rooms;

    public Department(String departmentName, int departmentId) {
        this.departmentName = departmentName;
        this.departmentId = departmentId;

    }

    public String getDepartmentName() {
        return departmentName;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public Room getRoom(String roomName) {
        for (Room room : rooms) {
            if (room.getRoomName().equals(roomName)) {
                return room;
            }
        }
        return null;
    }

    public void addRoom(Room room) {
        rooms.add(room);
    }
}