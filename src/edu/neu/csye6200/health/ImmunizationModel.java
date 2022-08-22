package edu.neu.csye6200.health;


import edu.neu.csye6200.FileUtil;
import edu.neu.csye6200.Person;
import edu.neu.csye6200.health.Vaccine.FREQUENCY;
import edu.neu.csye6200.student.StudentController;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//singleton class
public class ImmunizationModel {

    private String vaccineRecordFilePath;
    private  VaccineDirectory vaccineDirectory ;
    private List<VaccineRecord> vaccineRecords ;

    public ImmunizationModel(String vaccineFilePath,String recordsPath, StudentController studentController) {
        this.vaccineRecordFilePath = recordsPath;
        this.vaccineDirectory = new VaccineDirectory(vaccineFilePath);
        vaccineRecords = new ArrayList<>();
        FileUtil.getFileData(this.vaccineRecordFilePath).stream().map(x -> new VaccineRecord(x,vaccineDirectory,studentController)).forEach(vaccineRecords::add);
    }



    class VaccineDirectory{
        private final String inputFilePath;
        List<Vaccine> vaccineList = new ArrayList<>();
        VaccineDirectory(String filePath)
        {
            this.inputFilePath = filePath;
            FileUtil.getFileData(this.inputFilePath).stream().map(x -> new Vaccine(x)).forEach(this.vaccineList::add);
        }
        
        public Vaccine getVaccineById(Integer id)
        {
            return this.vaccineList.stream().filter(x -> x.getId()==id).findFirst().get();
        }
        
        public void addVaccine(int id, String name, FREQUENCY frequency, boolean isOptional,int doseCount)
        {
            Vaccine vaccine = new Vaccine(id,name,isOptional, frequency, doseCount);
            this.vaccineList.add(vaccine);
            FileUtil.appendEntryToFile(this.inputFilePath, vaccine.toCSV());
        }
        
        public void modifyVaccine(Vaccine oldVaccine, Vaccine newVaccine)
        {
            this.vaccineList.remove(oldVaccine);
            this.vaccineList.add(newVaccine);
            FileUtil.modifyEntryInFile(this.inputFilePath, oldVaccine.toCSV(), newVaccine.toCSV());
        }
        
        public void removeVaccine(Vaccine vaccine)
        {
            this.vaccineList.remove(vaccine);
            FileUtil.removeEntryInFile(this.inputFilePath, vaccine.toCSV());
        }
        
        public List<Vaccine> getVaccineList()
        {
            return this.vaccineList;
        }
        
        
    }
    
    
    public void addVaccineRecord(int id, Vaccine vaccine, Person person, Date[] recievedDate)
    {
        VaccineRecord record = new VaccineRecord(id,vaccine,recievedDate,person);
        this.vaccineRecords.add(record);
        FileUtil.appendEntryToFile(this.vaccineRecordFilePath, record.toCSV());
    }
    
    public void modifyVaccineRecord(VaccineRecord oldVaccineRecord, VaccineRecord newVaccineRecord)
    {
        this.vaccineRecords.remove(oldVaccineRecord);
        this.vaccineRecords.add(newVaccineRecord);
        FileUtil.modifyEntryInFile(this.vaccineRecordFilePath, oldVaccineRecord.toCSV(), newVaccineRecord.toCSV());
    }
    
    public void removeVaccineRecord(VaccineRecord vaccine)
    {
        this.vaccineRecords.remove(vaccine);
        FileUtil.removeEntryInFile(this.vaccineRecordFilePath, vaccine.toCSV());
    }
    public List<VaccineRecord> getVaccineRecords()
    {
        return this.vaccineRecords;
    }
    /**
     *
     * @return
     */
    protected VaccineDirectory getVaccineDirectory()
    {
        return this.vaccineDirectory;
    }

}
