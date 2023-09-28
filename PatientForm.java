//import java.awt.*;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;
//import java.io.IOException;
//import java.util.ArrayList;
//import javax.swing.*;
//
//public class PatientForm {
//
//    public static JFrame frame;
//    private JPanel panel;
//    private JLabel nameLabel;
//    private JLabel ageLabel;
//    private JLabel genderLabel;
//    private JLabel roomLabel;
//    private JLabel departmentLabel;
//    private JLabel specialismLabel;
//    public static JTextField nameField;
//    public static JTextField ageField;
//    private JComboBox<String> genderBox;
//    private JComboBox<String> roomBox;
//    private JComboBox<String> departmentBox;
//    private JComboBox<String> specialism;
//    private JButton submitButton;
//
//    private JButton findBedButton;
//    //private ArrayList<Patient> patientList;
//
//    private final Color primaryColor = new Color(195, 10, 229);
//    private final Color secondaryColor = new Color(255, 255, 255);
//    private final Font labelFont = new Font("Arial", Font.PLAIN, 16);
//    private final Font buttonFont = new Font("Arial", Font.BOLD, 16);
//    private final Font comboFont = new Font("Arial", Font.PLAIN, 16);
//
//    public PatientForm() {
//        frame = new JFrame("Patient Information Form");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(1500, 500);
//        frame.setLocationRelativeTo(null);
//        frame.getContentPane().setBackground(primaryColor);
//
//
//
//        panel = new JPanel();
//        panel.setBackground(secondaryColor);
//        panel.setLayout(new GridBagLayout());
//        GridBagConstraints c = new GridBagConstraints();
//        c.insets = new Insets(10, 20, 10, 20);
//
//        nameLabel = new JLabel("Name:");
//        nameLabel.setFont(labelFont);
//        c.gridx = 0;
//        c.gridy = 0;
//        c.weightx = 1.0;
//        panel.add(nameLabel, c);
//
//
//        nameField = new JTextField(50);
//        nameField.setFont(comboFont);
//        c.gridx = 0;
//        c.gridy = 1;
//        c.weightx = 1.0;
//        c.fill = GridBagConstraints.HORIZONTAL;
//        panel.add(nameField, c);
//
//        ageLabel = new JLabel("Age:");
//        ageLabel.setFont(labelFont);
//        c.gridx = 2;
//        c.gridy = 0;
//        panel.add(ageLabel, c);
//
//        ageField = new JTextField(50);
//        ageField.setFont(labelFont);
//        c.gridx = 3;
//        c.gridy = 0;
//        c.weightx = 1.0;
//        c.fill = GridBagConstraints.HORIZONTAL;
//        panel.add(ageField, c);
//
//        genderLabel = new JLabel("Gender:");
//        genderLabel.setFont(labelFont);
//        c.gridx = 0;
//        c.gridy = 2;
//        panel.add(genderLabel, c);
//
//        String[] genders = {"Male", "Female"};
//        genderBox = new JComboBox<>(genders);
//        genderBox.setFont(comboFont);
//        c.gridx = 1;
//        c.gridy = 2;
//        panel.add(genderBox, c);
//        genderBox.setBackground(Color.pink);
////        genderBox.setBorder();
//
//
//        roomLabel = new JLabel("Preferred Room:");
//        roomLabel.setFont(labelFont);
//        c.gridx = 0;
//        c.gridy = 3;
//        panel.add(roomLabel, c);
//        roomLabel.setBackground(Color.pink);
//
//
//        String[] rooms = DataBase.finalRoomsCategoryList.toArray(new String[DataBase.finalRoomsCategoryList.size()]);
//        roomBox = new JComboBox<>(rooms);
//        roomBox.setFont(comboFont);
//        c.gridx = 1;
//        c.gridy = 3;
//        panel.add(roomBox, c);
//        roomBox.setBackground(Color.pink);
//
//        departmentLabel = new JLabel("Department:");
//        departmentLabel.setFont(labelFont);
//        c.gridx = 0;
//        c.gridy = 4;
//        panel.add(departmentLabel, c);
//        departmentLabel.setBackground(Color.pink);
//
//        specialismLabel = new JLabel("Specialism:");
//        specialismLabel.setFont(labelFont);
//        c.gridx = 0;
//        c.gridy = 5;
//        panel.add(specialismLabel, c);
//        specialismLabel.setBackground(Color.pink);
//
//        String spe[] = DataBase.uniqueSpecialism.toArray(new String[DataBase.uniqueSpecialism.size()]);
//        specialism = new JComboBox<>(spe);
//        c.gridx = 1;
//        c.gridy = 5;
//        panel.add(specialism, c);
//        specialism.setBackground(Color.pink);
//
//
////        ArrayList<String> departments = new ArrayList<String>();
////        departments.add("Cardiology");
////        departments.add("Neurology");
////        departments.add("Oncology");
////        departments.add("Orthopedics");
//
//        String[] deptName = new String[DataBase.uniqueDepartment.size()];
//        int i = 0;
//        for (Department d : DataBase.uniqueDepartment){
//            deptName[i] = d.departmentName;
//            i++;
//        }
//
//
//        departmentBox = new JComboBox<>(deptName);
//        departmentBox.setFont(comboFont);
//        c.gridx = 1;
//        c.gridy = 4;
//        panel.add(departmentBox, c);
//        departmentBox.setBackground(Color.pink);
//
//        submitButton = new JButton("Submit");
//        submitButton.setFont(buttonFont);
//        submitButton.setBackground(Color.WHITE);
//        submitButton.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                String name = nameField.getText();
//                int age = Integer.parseInt(ageField.getText());
//                String gender = genderBox.getSelectedItem().toString();
//                String room = roomBox.getSelectedItem().toString();
//                String department = departmentBox.getSelectedItem().toString();
//                String spe = specialism.getSelectedItem().toString();
//
//                Department dep = null;
//                for (Department d : DataBase.department){
//                    if (d.departmentName.equals(department)){
//                        dep = d;
//                    }
//                }
//
//                Patient patient = new Patient(name, age, gender, dep.departmentName , room, spe);
//                DataBase.patientList.add(patient);
//
//
//                JOptionPane.showMessageDialog(frame, "Patient information added successfully!");
//
//                nameField.setText("");
//                ageField.setText("");
//
//
//            }
//        });
//        c.gridx = 0;
//        c.gridy = 6;
//        c.gridwidth = 2;
//        c.fill = GridBagConstraints.HORIZONTAL;
//        panel.add(submitButton, c);
//        submitButton.setBackground(Color.cyan);
//
//        findBedButton = new JButton("Find Bed");
//        findBedButton.setFont(buttonFont);
//        findBedButton.setBackground(Color.WHITE);
//        findBedButton.addActionListener(e -> {
//            try {
//                AssignBed.assignbed();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        });
//        c.gridx = 0;
//        c.gridy = 7;
//        c.gridwidth = 2;
//        c.fill = GridBagConstraints.HORIZONTAL;
//        panel.add(findBedButton, c);
//        findBedButton.setBackground(Color.orange);
//
//
//
//
//        frame.add(panel);
//        frame.setVisible(true);
//
//
////        patientList = new ArrayList<Patient>();
//    }
//
////    public static void main(String[] args) {
////        new PatientForm();
////    }
//}