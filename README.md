# Dynamic Patient Admission

A Java-based desktop application for real-time hospital bed allocation, patient management, and data tracking.  
This project streamlines hospital operations by automating bed assignments, monitoring occupancy, and storing data for quick access and backup.

---

## 📌 Features
- **Real-time Bed Assignment** – Automatically assigns available beds to incoming patients.
- **Patient Management** – Stores and updates patient records with ease.
- **Excel Integration** – Reads and writes data to Excel (`hospital_data.xlsx`, `PatientDetails.xlsx`) for accessibility and backup.
- **Occupancy Tracking** – Monitors assigned and vacant beds.
- **Interactive Forms** – GUI-based patient entry and data management.

---

## 🛠️ Tech Stack
- **Language:** Java
- **GUI:** Swing
- **Data Storage:** Excel (via Apache POI or equivalent library)
- **File Format Support:** `.xlsx` for hospital and patient data

---

## 📂 Project Structure
├── AssignBed.java # Logic for assigning beds
├── Bed.java # Bed object model
├── DataBase.java # Handles data operations
├── Department.java # Department-related data
├── HospitalData.java # Hospital-level data management
├── Main.java # Entry point of the application
├── Patient.java # Patient object model
├── PatientForm.java # GUI form for patient entry
├── PatientForm1.java # Additional patient form
├── Room.java # Room object model
├── PatientDetails.xlsx # Sample patient data
├── hospital_data.xlsx # Sample hospital/bed data
├── META-INF/ # Metadata
├── out/production/ # Compiled files
├── JAR/ # Packaged JAR file


## 🚀 Getting Started

### Prerequisites
- Java 8 or higher
- Excel files (`hospital_data.xlsx` and `PatientDetails.xlsx`) placed in the project directory

### Running the Project
1. Clone this repository:
   ```bash
   git clone https://github.com/yourusername/hospital-bed-management.git
2. Open the project in your preferred Java IDE (e.g., IntelliJ IDEA, Eclipse).

3. Compile and run Main.java or use the provided JAR file in the JAR/ directory.





