import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException  {

        HospitalData.readHospitalData();
        DataBase.roomsListToRoomsCategoryList();
        PatientForm1 p = new PatientForm1();

    }
}
