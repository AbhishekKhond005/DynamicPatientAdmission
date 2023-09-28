public class Patient {

    String patientName;
    int age;
    String gender;
    String  departmentNeeded;
    String preferred_room;
    public String specialism_needed;

    public Patient(){
        //empty constructor
    }

    public Patient(String name, int age, String gender, String d, String preferred, String specialism_needed){  //patient cunstructor

        this.age = age;
        this.gender = gender;
        this.patientName = name;
        this.departmentNeeded = d;
        this.preferred_room = preferred;
        this.specialism_needed = specialism_needed;

    }

    public static void addP(String name, int age, String gender, String d, String preferred, String specialism_needed){

        Patient p = new Patient(name, age, gender, d, preferred, specialism_needed);
        DataBase.patientList.add(p);

    }
}
