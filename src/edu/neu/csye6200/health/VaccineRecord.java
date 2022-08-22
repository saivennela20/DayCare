package edu.neu.csye6200.health;

import java.util.Date;

import edu.neu.csye6200.Person;
import edu.neu.csye6200.student.Student;
import edu.neu.csye6200.student.StudentController;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class VaccineRecord {

    public String DATE_FORMAT = "MM-dd-yyyy";
    private int recordId;
    private Vaccine vaccine;
    private Date[] recievedDate;
    private Person person;

    public VaccineRecord(int id, Vaccine vaccine, Date[] recivedDate, Person person) {
        this.recordId = id;
        this.vaccine = vaccine;
        this.recievedDate = recivedDate;
        this.person = person;
    }

    VaccineRecord(String csvString,ImmunizationModel.VaccineDirectory vaccineDirectory ,StudentController studentController) {

        this.recordId = -1;
        this.vaccine = null;
        this.recievedDate = null;
        this.person = null;

        String[] data = csvString.split(",");

        try {
            this.recordId = Integer.parseInt(data[0]);
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("error while parsing record id");
        }
        
        try{
        this.vaccine = vaccineDirectory.getVaccineById(Integer.parseInt(data[1]));
        }
        catch(Exception e)
        {
            throw new RuntimeException("error while parsing vaccine id");
        }
        
        try{
        this.person = studentController.getStudentById(Integer.parseInt(data[2]));
        }
        catch(Exception e)
        {
            throw new RuntimeException("error while parsing student");
        }
        
        try
        {
            String dates[]= data[3].split(";");
            recievedDate = new Date[dates.length];
            SimpleDateFormat dateFormat =  new SimpleDateFormat(DATE_FORMAT);
            for (int i =0;i< dates.length;i++) {
                recievedDate[i] = dateFormat.parse(dates[i]);
            }
        }
        catch(Exception e)
        {
            throw new RuntimeException("error while parsing dates");
        }
        
    }

    public Vaccine getVaccine() {
        return vaccine;
    }

    public void setVaccine(Vaccine vaccine) {
        this.vaccine = vaccine;
    }

    public Date[] getRecievedDate() {
        return recievedDate;
    }

    public void setRecievedDate(Date[] recievedDate) {
        this.recievedDate = recievedDate;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public int getRecordId() {
        return recordId;
    }

    public void setRecordId(int recordId) {
        this.recordId = recordId;
    }

    public String toCSV() {
        String str = "";
        
        for (Date recievedDate1 : recievedDate) {
            DateFormat dateFormat =  new SimpleDateFormat(DATE_FORMAT);
            str += dateFormat.format(recievedDate1) + ";";
        }
        str = str.substring(0,str.length()-1);
        return this.getRecordId() + "," + this.getVaccine().getId()+ "," + ((Student) this.getPerson()).getSId()  + "," + str;
    }

    @Override
    public String toString() {
        return ((Integer) this.getRecordId()).toString();
    }

}
