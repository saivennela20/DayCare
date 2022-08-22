package edu.neu.csye6200;

import java.util.ArrayList;
import java.util.List;

import edu.neu.csye6200.health.VaccineRecord;

public class  Person {

    protected String name;
    protected String parentName;
    protected long phoneNumber;
    protected String address;
    protected int age;
    protected List<VaccineRecord> vaccineRecords;
    protected boolean isAssigned = false;

    public Person() {
    }
    
    public Person(String name, String parentName, long phoneNumber, String address, int age) {
        this.name = name;
        this.parentName = parentName;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.age = age;
//		this.vaccineRecords = vacRecords;
    }

    public Person(String csvString) {
        String[] vals = csvString.split(",");
        this.name = "";
        this.parentName = "";
        this.phoneNumber = -1;
        this.address = "";
        this.age = -1;

        this.name = vals[0];

        this.parentName = vals[1];
        try {
            this.phoneNumber = Long.parseLong(vals[2]);
        } catch (Exception e) {
            System.err.println("Error while parsing phone number" + vals[2]);
        }
        this.address = vals[3];

        try {
            this.age = Integer.parseInt(vals[4]);
        } catch (Exception e) {
            System.err.println("Error while parsing age" + vals[4]);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public long getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(long phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public List<VaccineRecord> getVaccineRecords() {
        return vaccineRecords;
    }

    public void setVaccineRecords(List<VaccineRecord> vaccineRecords) {
        this.vaccineRecords = vaccineRecords;
    }

    public void addVaccineRecord(VaccineRecord record) {
        if (this.vaccineRecords == null) {
            this.vaccineRecords = new ArrayList<>();
        }
        this.vaccineRecords.add(record);
    }

    public String toCSV() {
        return this.getName()+ "," + this.getParentName() + "," + this.getPhoneNumber() + "," + this.getAddress().replace(",", "__C__") + "," + this.getAge() ;
    }

    public boolean getIsAssigned() {
        return isAssigned;
    }

    public void setIsAssigned(boolean isAssigned) {
        this.isAssigned = isAssigned;
    }
    

}
