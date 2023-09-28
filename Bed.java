class Bed {
    public String bedName;
    public boolean isOccupied;
    public String department;
    public String category;
    public String roomName;
    public String specialism;
    public Bed(String bedName, boolean isOccupied, String department, String categroy, String roomName, String specialism) {

        this.bedName = bedName;
        this.isOccupied = isOccupied;
        this.department = department;
        this.category = categroy;
        this.roomName = roomName;
        this.specialism = specialism;
    }

    public String getBedName() {

        return bedName;
    }

    public void setBedName(String bedName) {

        this.bedName = bedName;
    }
}