import java.util.ArrayList;
import java.util.HashMap;

public class DataBase {

    public static ArrayList<String> allDeptValName = new ArrayList<>();
    public static ArrayList<Integer> allDeptValId = new ArrayList<>();
    public static ArrayList<Department> department = new ArrayList<>();
    public static ArrayList<Department> uniqueDepartment = new ArrayList<>(); // <=======  DEPARTMENTS ARRAY LIST. NOTHING SPECIAL. REFER Departments class FOR FURTHER UNDERSTANDING.
    public static ArrayList<Room> finalRooms = new ArrayList<Room>(); // <======== THIS IS FINAL ROOMS ARRAYLIST
    public static ArrayList<Patient> patientList = new ArrayList<>(); // <======== THIS LIST STORES PATIENT OBJECTS
    public static ArrayList<String> finalRoomsCategoryList = new ArrayList<>();// <====== LIST OF ALL THE CATEGORIES OF ROOM
    public static ArrayList<Bed> bedList = new ArrayList<>(); // <====== LIST OF ALL BEDS
    public static ArrayList<String> specialismm = new ArrayList<>();
    public static ArrayList<String> uniqueSpecialism = new ArrayList<>();
    public static HashMap<String, ArrayList<String>> deptSpe = new HashMap<>();

    public static void specialismsho(String department){
        for(Department d : uniqueDepartment){
        }
    }

    public static HashMap<Patient, Bed> map = new HashMap<>();
    public static HashMap<String, String> m = new HashMap<>();
    public static ArrayList<Patient> addmittedPatients = new ArrayList<>();
    public static ArrayList<Bed> addmittedPatientsBeds = new ArrayList<>();

    public static void roomsListToRoomsCategoryList() {

        for (Room r : finalRooms){
            if (!finalRoomsCategoryList.contains(r.roomCategory)){
                finalRoomsCategoryList.add(r.roomCategory);
            }
        }
    }

    public static void roomsListToBedsList(){

        for (Room r : finalRooms){
            for(Bed b : r.beds){
                Bed bed = new Bed(b.bedName,false, r.department, r.roomCategory, r.roomName, r.specialism);
                bedList.add(bed);
            }
        }
    }

}
