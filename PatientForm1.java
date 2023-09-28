import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.swing.*;
import java.awt.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class PatientForm1 {


     Workbook workbook;
    Sheet sheet;
     File file = new File("PatientDetails.xlsx");

     String filename = "PatientDetails.xlsx";
    public void savePatientDetailsToExcel() throws IOException {
        try {
            LocalDateTime currentDateTime = LocalDateTime.now();
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
            String formattedDate = currentDateTime.format(dateFormatter);
            String formattedTime = currentDateTime.format(timeFormatter);

            if (file.exists()) {
                FileInputStream inputStream = new FileInputStream(file);
                workbook = new XSSFWorkbook(inputStream);
                sheet = workbook.getSheetAt(0);
            } else {
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet("Patient Details");

                Row headerRow = sheet.createRow(0);
                headerRow.createCell(0).setCellValue("Name");
                headerRow.createCell(1).setCellValue("Age");
                headerRow.createCell(2).setCellValue("Department ");
                headerRow.createCell(3).setCellValue("Specialism ");
                headerRow.createCell(4).setCellValue("Room Category");
                headerRow.createCell(5).setCellValue("Room Name");
                headerRow.createCell(6).setCellValue("Bed Name");
                headerRow.createCell(7).setCellValue("Entry Date");
                headerRow.createCell(8).setCellValue("Entry Time");
                headerRow.createCell(9).setCellValue("Depart Date");
                headerRow.createCell(10).setCellValue("Depart Time");

            }

            int rowNum = sheet.getLastRowNum() + 1;

            for (Patient p : DataBase.map.keySet()) {
                boolean patientExists = false;
                for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    String patientName = row.getCell(0).getStringCellValue();
                    if (patientName.equals(p.patientName)) {
                        patientExists = true;
                        break;
                    }
                }
                if (!patientExists) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(p.patientName);
                    row.createCell(1).setCellValue(p.age);
                    row.createCell(2).setCellValue(p.departmentNeeded);
                    row.createCell(3).setCellValue(p.specialism_needed);
                    row.createCell(4).setCellValue(p.preferred_room);
                    row.createCell(5).setCellValue(DataBase.map.get(p).roomName);
                    row.createCell(6).setCellValue(DataBase.map.get(p).bedName);
                    row.createCell(7).setCellValue(formattedDate);
                    row.createCell(8).setCellValue(formattedTime);
                }
            }

            try (FileOutputStream outputStream = new FileOutputStream(file)) {
                workbook.write(outputStream);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        workbook.close();

    }

    private void SetDepartDateAndTime() throws IOException {

        if (file.exists()) {

            FileInputStream inputStream = new FileInputStream(filename);
            Workbook workbook = WorkbookFactory.create(inputStream);

            Sheet she = workbook.getSheetAt(0);
            for (Row row : she) {
                Cell cell = row.getCell(0);

                if (cell.getStringCellValue().equals(departPatient.getText())) {

                    System.out.println(departPatient.getText());
                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
                    String currentDate = now.format(dateFormatter);
                    String currentTime = now.format(timeFormatter);

                    Cell dateCell = row.getCell(9);
                    if (dateCell == null) {
                        dateCell = row.createCell(9);
                    }
                    dateCell.setCellValue(currentDate);
                    System.out.println(dateCell.getColumnIndex());

                    Cell timeCell = row.getCell(10);
                    if (timeCell == null) {
                        timeCell = row.createCell(10);
                    }
                    timeCell.setCellValue(currentTime);
                    System.out.println(timeCell.getColumnIndex());

                    for(Bed b : DataBase.bedList){
                        if (row.getCell(6).getStringCellValue().equals(b.bedName)){
                            b.isOccupied = false;
                        }
                    }
                    FileOutputStream fileOut = new FileOutputStream("PatientDetails.xlsx");
                    workbook.write(fileOut);
                    fileOut.close();
                }
            }

            JOptionPane.showMessageDialog(frame, "Patient information updated successfully!");
        }
//        else{
//            workbook = new XSSFWorkbook();
//            Sheet she = workbook.getSheetAt(0);
//            for (Row row : she) {
//                Cell cell = row.getCell(0);
//                if (PatientName.equals(cell.getStringCellValue())) {
//                    LocalDateTime now = LocalDateTime.now();
//                    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//                    DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
//                    String currentDate = now.format(dateFormatter);
//                    String currentTime = now.format(timeFormatter);
//                    Cell dateCell = row.getCell(9);
//                    if (dateCell == null) {
//                        dateCell = row.createCell(9);
//                    }
//                    dateCell.setCellValue(currentDate);
//                    Cell timeCell = row.getCell(10);
//                    if (timeCell == null) {
//                        timeCell = row.createCell(10);
//                    }
//                    timeCell.setCellValue(currentTime);
//
//                    for(Bed b : DataBase.bedList){
//                        if (row.getCell(6).getStringCellValue().equals(b.bedName)){
//                            b.isOccupied = false;
//                        }
//                    }
//                }
//                else {
////                    JOptionPane.showMessageDialog(frame, "Patient not found");
//                }
//            }
        //}

    }


    public static JFrame frame;
    private JPanel panel;
    private JLabel nameLabel;
    private JLabel ageLabel;
    private JLabel genderLabel;
    private JLabel roomLabel;
    private JLabel departmentLabel;
    private JLabel specialismLabel;
    private JLabel dPatientLabel;
    public static JTextField nameField;
    public static JTextField ageField;
    private JComboBox<String> genderBox;
    private JComboBox<String> roomBox;
    private JComboBox<String> departmentBox;
    private JComboBox<String> specialism;
    private JTextField departPatient;
    private JButton submitButton;
    private JButton Departbtn;
    private JButton findBedButton;
    private JButton saveButton;

    private final Color primaryColor = new Color(65, 65, 65);
    private final Color secondaryColor = new Color(65, 65, 65);
    private final Color zzColor = new Color(220, 140, 96);
    private final Color xxxColor = new Color(220, 140, 96);
    private final Color ccColor = new Color(78, 78, 78);
    private final Font labelFont = new Font("Poppins", Font.BOLD, 16);
    private final Font buttonFont = new Font("Poppins", Font.PLAIN, 16);
    private final Font comboFont = new Font("Poppins", Font.PLAIN, 16);

    public PatientForm1() {
        frame = new JFrame("Patient Information Form");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 650);
        frame.setLocationRelativeTo(null);
        frame.getContentPane().setBackground(primaryColor);

        panel = new JPanel();
        panel.setBackground(secondaryColor);
        panel.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(10, 40, 10, 40);

        nameLabel = new JLabel("Name:");
        nameLabel.setFont(labelFont);
        nameLabel.setForeground(Color.white);
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        panel.add(nameLabel, c);

        nameField = new JTextField(50);
        nameField.setFont(comboFont);
        c.gridx = 0;
        c.gridy = 1;
        c.weightx = 1.0;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(nameField, c);
        nameField.setForeground(Color.white);
        nameField.setBackground(ccColor);

        ageLabel = new JLabel("Age:");
        ageLabel.setFont(labelFont);
        c.gridx = 1;
        c.gridy = 0;
        panel.add(ageLabel, c);
        ageLabel.setForeground(Color.white);

        ageField = new JTextField(50);
        ageField.setFont(buttonFont);
        c.gridx = 1;
        c.gridy = 1;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(ageField, c);
        ageField.setForeground(Color.white);
        ageField.setBackground(ccColor);

        genderLabel = new JLabel("Gender:");
        genderLabel.setFont(labelFont);
        c.gridx = 0;
        c.gridy = 2;
        panel.add(genderLabel, c);
        genderLabel.setForeground(Color.white);

        String[] genders = {"Male", "Female"};
        genderBox = new JComboBox<>(genders);
        genderBox.setFont(comboFont);
        c.gridx = 0;
        c.gridy = 3;
        panel.add(genderBox, c);
        genderBox.setForeground(Color.WHITE);
        genderBox.setBackground(ccColor);

        roomLabel = new JLabel("Preferred Room:");
        roomLabel.setFont(labelFont);
        c.gridx = 1;
        c.gridy = 2;
        panel.add(roomLabel, c);
        roomLabel.setForeground(Color.white);

        String[] rooms = DataBase.finalRoomsCategoryList.toArray(new String[DataBase.finalRoomsCategoryList.size()]);
        roomBox = new JComboBox<>(rooms);
        roomBox.setFont(comboFont);
        c.gridx = 1;
        c.gridy = 3;
        panel.add(roomBox, c);
        roomBox.setBackground(ccColor);
        roomBox.setForeground(Color.white);

        departmentLabel = new JLabel("Department:");
        departmentLabel.setFont(labelFont);
        c.gridx = 0;
        c.gridy = 4;
        panel.add(departmentLabel, c);
        departmentLabel.setForeground(Color.white);

        String[] deptName = new String[DataBase.uniqueDepartment.size()];
        int i = 0;
        for (Department d : DataBase.uniqueDepartment){
            deptName[i] = d.departmentName;
            i++;
        }

        departmentBox = new JComboBox<>(deptName);
        departmentBox.setFont(comboFont);
        c.gridx = 0;
        c.gridy = 5;
        panel.add(departmentBox, c);
        departmentBox.setForeground(Color.white);
        departmentBox.setBackground(ccColor);

        specialismLabel = new JLabel("Specialism:");
        specialismLabel.setFont(labelFont);
        c.gridx = 1;
        c.gridy = 4;
        panel.add(specialismLabel, c);
        specialismLabel.setForeground(Color.white);

        String spe[] = DataBase.uniqueSpecialism.toArray(new String[DataBase.uniqueSpecialism.size()]);
        specialism = new JComboBox<>(spe);
        c.gridx = 1;
        c.gridy = 5;
        panel.add(specialism, c);
        specialism.setForeground(Color.white);
        specialism.setBackground(ccColor);

        departmentBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Get the selected department
                String selectedDepartment = (String) departmentBox.getSelectedItem();

                // Get the list of specialisms for the selected department from the HashMap
                ArrayList<String> specialisms = DataBase.deptSpe.get(selectedDepartment);

                // Clear the specialism combo box and add the specialisms for the selected department
                specialism.removeAllItems();
                for (String s : specialisms) {
                    specialism.addItem(s);
                }
            }
        });

        submitButton = new JButton("Submit");
        submitButton.setFont(buttonFont);
        submitButton.setBackground(Color.WHITE);
        submitButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                int age = Integer.parseInt(ageField.getText());
                String gender = genderBox.getSelectedItem().toString();
                String room = roomBox.getSelectedItem().toString();
                String department = departmentBox.getSelectedItem().toString();
                String spe = specialism.getSelectedItem().toString();

                Patient patient = new Patient(name, age, gender, department, room, spe);
                DataBase.patientList.add(patient);

                JOptionPane.showMessageDialog(frame, "Patient information added successfully!");

                nameField.setText("");
                ageField.setText("");

            }
        });
        c.gridx = 0;
        c.gridy = 6;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(submitButton, c);
        submitButton.setBackground(zzColor);
        submitButton.setForeground(Color.white);


        findBedButton = new JButton("Find Bed");
        findBedButton.setFont(buttonFont);
        findBedButton.setBackground(Color.WHITE);
        findBedButton.addActionListener(e -> {
            try {
                AssignBed.assignbed();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(findBedButton, c);
        findBedButton.setBackground(Color.orange);
        findBedButton.setBackground(xxxColor);
        findBedButton.setForeground(Color.white);

        saveButton = new JButton("Save");
        saveButton.setFont(buttonFont);
        saveButton.setBackground(Color.WHITE);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    savePatientDetailsToExcel();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });

        c.gridx = 0;
        c.gridy = 8;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(saveButton, c);
        saveButton.setBackground(Color.orange);
        saveButton.setBackground(xxxColor);
        saveButton.setForeground(Color.white);

        dPatientLabel = new JLabel("Depart Patient:");
        dPatientLabel.setFont(labelFont);
        c.gridx = 0;
        c.gridy = 9;
        panel.add(dPatientLabel, c);
        dPatientLabel.setForeground(Color.white);

        departPatient = new JTextField(50);
        departPatient.setFont(buttonFont);
        c.gridx = 0;
        c.gridy = 10;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(departPatient, c);
        departPatient.setForeground(Color.white);
        departPatient.setBackground(ccColor);

        Departbtn = new JButton("Depart");
        Departbtn.setFont(buttonFont);
        Departbtn.setBackground(Color.WHITE);
        Departbtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String panmettodepart = departPatient.toString();

                try {
                    SetDepartDateAndTime();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }

            }
        });

        c.gridx = 0;
        c.gridy = 11;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        panel.add(Departbtn, c);
        Departbtn.setBackground(Color.orange);
        Departbtn.setBackground(zzColor);
        Departbtn.setForeground(Color.white);

        frame.add(panel);
        frame.setVisible(true);

    }

}
