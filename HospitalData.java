import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.awt.dnd.DropTarget;
import java.io.*;
import java.util.*;

public class HospitalData {
    public static void readHospitalData() {
        try {

            FileInputStream file = new FileInputStream(new File("hospital_data.xlsx"));
            XSSFWorkbook workbook = new XSSFWorkbook(file);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row> rowIterator = sheet.iterator();

            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                int departmentid = row.getCell(0).getColumnIndex();
                String departmentName = row.getCell(1).getStringCellValue();
                String specialism = row.getCell(2).getStringCellValue();
                String roomName = row.getCell(3).getStringCellValue();
                String roomCategory = row.getCell(4).getStringCellValue();
                String bedName = row.getCell(5).getStringCellValue();

                Room r = new Room(roomName, roomCategory, departmentName, specialism);
                Department d = new Department(departmentName, departmentid);
                Bed b = new Bed(bedName, false, departmentName, roomCategory, roomName, specialism);

                DataBase.department.add(d);
                DataBase.finalRooms.add(r);
                DataBase.bedList.add(b);
                DataBase.specialismm.add(specialism);

                if(!DataBase.deptSpe.containsKey(d.departmentName)){
                    DataBase.deptSpe.put(d.departmentName, new ArrayList<>());
                }

                if (!DataBase.deptSpe.get(d.departmentName).contains(specialism)){
                    DataBase.deptSpe.get(d.departmentName).add(specialism);
                }

            }

            DataBase.finalRooms.remove(0);
            DataBase.bedList.remove(0);
            DataBase.specialismm.remove(0);

            for (Department d : DataBase.department){
                if (!DataBase.allDeptValName.contains(d.departmentName)){
                    DataBase.allDeptValName.add(d.departmentName);
                    DataBase.allDeptValId.add(d.departmentId);
                }
            }

            for (int i = 0; i<DataBase.allDeptValId.size(); i++){
                Department d = new Department(DataBase.allDeptValName.get(i), DataBase.allDeptValId.get(i));
                DataBase.uniqueDepartment.add(d);
            }

            DataBase.uniqueDepartment.remove(0);

//            int i = 0;
//            for (Row r : sheet){
//                if(r.getCell(1).getStringCellValue().equals(DataBase.uniqueDepartment.get(i))){
//                    DataBase.uniqueSpecialism.add(DataBase.uniqueDepartment.get(i), r.getCell())
//                }
//            }

            for (String s : DataBase.specialismm){
                if (!DataBase.uniqueSpecialism.contains(s)){
                    DataBase.uniqueSpecialism.add(s);
                }
            }

            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}





