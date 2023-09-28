import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

public class AssignBed {

//    public boolean isOcc(){
//        boolean occupancy = false;
//        for
//    }

    public static void assignbed() throws IOException {
        ArrayList<Bed> fittingBeds = new ArrayList<>(DataBase.bedList.size());

        Patient pa = DataBase.patientList.get(0);
        for (Bed b : DataBase.bedList) {
            if (!b.isOccupied) {
                if (pa.departmentNeeded.equals(b.department)) {
                    fittingBeds.add(b);
                }
            }
        }

        ArrayList<Bed> filteredBeds = new ArrayList<>(fittingBeds.size());
        for (Bed b : fittingBeds) {
            if (pa.departmentNeeded.equals(b.department)){
                if (pa.specialism_needed.equals(b.specialism)){
                    if (pa.preferred_room.equals((b.category))){
                        filteredBeds.add(b);
                    }
                }
            }
        }


        FileInputStream file0 = new FileInputStream(new File("PatientDetails.xlsx"));
        XSSFWorkbook workbook0 = new XSSFWorkbook(file0);
        XSSFSheet sheet0 = workbook0.getSheetAt(0);
        ArrayList<Bed> filterfitting = new ArrayList<>(fittingBeds.size());
        ArrayList<Bed> temp = new ArrayList<>();
        for( Row row : sheet0){

            String bedName = row.getCell(6).getStringCellValue();
            String dep = row.getCell(2).getStringCellValue();
            String room = row.getCell(5).getStringCellValue();
            String spec = row.getCell(3).getStringCellValue();
            String roomcat = row.getCell(4).getStringCellValue();
            boolean occ = true;

            Bed fileone = new Bed(bedName, occ, dep, roomcat, room, spec);
            filterfitting.add(fileone);

        }

        for (Bed b : filteredBeds ){
            for (Bed k : filterfitting){
                if (b.bedName.equals(k.bedName) && b.department.equals(k.department) && b.specialism.equals(k.specialism) && b.roomName.equals(k.roomName) && b.category.equals(k.category)){
                    filteredBeds.remove(b);
                }
            }
        }

        if (filteredBeds.isEmpty()){
            JOptionPane.showMessageDialog(PatientForm1.frame, "No beds available" );
        }

        Patient paa = DataBase.patientList.get(0);
        Bed ba = filteredBeds.get(0);
        DataBase.map.put(paa, ba);
        ba.isOccupied = true;
        filteredBeds.remove(ba);

        FileInputStream file = new FileInputStream(new File("hospital_data.xlsx"));
        XSSFWorkbook workbook = new XSSFWorkbook(file);
        XSSFSheet sheet = workbook.getSheetAt(0);

        for( Row r : sheet){
            if (r.getCell(1).getStringCellValue().equals(paa.departmentNeeded) && r.getCell(4).getStringCellValue().equals(paa.preferred_room)){
                r.createCell(5);
                System.out.println(paa.patientName);
                r.getCell(5).setCellValue(paa.patientName);
            }
        }

        JOptionPane.showMessageDialog(PatientForm1.frame,DataBase.map.get(paa).department + " " + DataBase.map.get(paa).roomName + " " + DataBase.map.get(paa).specialism + " " + DataBase.map.get(paa).bedName);
        DataBase.patientList.remove(0);
    }

}
