package edu.neu.csye6200.health;

import java.util.HashMap;
import java.util.Map;

public class Vaccine {

    public static enum FREQUENCY {
        DAILY(0,1),
        WEEKLY(1,7),
        MONTHLY(2,30),
        QUARTERLY(3,90),
        YEARLY(4,365),
        ONLYONCE(5,-1);

        private static Map map = new HashMap();
        static {
        for (FREQUENCY type : FREQUENCY.values()) {
            map.put(type.value, type);
        }
    }
        private final int value;
        private final int days;
        private FREQUENCY(int value,int days) {
            this.value = value;
            this.days = days;
        }

        public static FREQUENCY valueOf(int val)
        {
            System.out.print(val);
            return (FREQUENCY)map.get(val);
        }
        public int getValue() {
            return value;
        }
        public int getDays(){
            return days;
        }
    }
    private int id;
    private String name;
    private boolean isOptional;
    private FREQUENCY frequency;
    private int doseCount;

    public Vaccine(int id, String name, boolean isOptional, FREQUENCY frequency, int doseCount) {
        this.id = id;
        this.name = name;
        this.isOptional = isOptional;
        this.frequency = frequency;
        this.doseCount = doseCount;
    }

    protected Vaccine(String data) {
        this.id = -1;
        this.name = "";
        this.isOptional = false;
        this.frequency = FREQUENCY.ONLYONCE;
        this.doseCount = 0;

        String[] vals = data.split(",");

        try {
            this.id = Integer.parseInt(vals[0]);
        } catch (Exception e) {
            System.err.println("error parsing vaccine id" + vals[0]);
            e.printStackTrace();
        }
        this.name = vals[1];
        try {
            this.isOptional = Boolean.getBoolean(vals[2]);
        } catch (Exception e) {
            throw new RuntimeException("error while parsing optional in vaccine");
        }

        this.frequency = FREQUENCY.valueOf(Integer.parseInt(vals[3]));

        try {
            this.doseCount = Integer.parseInt(vals[4]);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println();
            System.out.println(data);
            throw new RuntimeException("error while parsing dose count in vaccine");
        }
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isOptional() {
        return isOptional;
    }

    public void setOptional(boolean isOptional) {
        this.isOptional = isOptional;
    }

    public FREQUENCY getFrequency() {
        return frequency;
    }

    public void setFrequency(FREQUENCY frequency) {
        this.frequency = frequency;
    }

    public boolean isIsOptional() {
        return isOptional;
    }

    public void setIsOptional(boolean isOptional) {
        this.isOptional = isOptional;
    }

    public int getDoseCount() {
        return doseCount;
    }

    public void setDoseCount(int doseCount) {
        this.doseCount = doseCount;
    }

    public String toCSV() {
        return this.getId() + "," + this.getName() + "," + this.isOptional() + "," + ((Integer)this.getFrequency().getValue()).toString()+","+this.getDoseCount();
    }

    @Override
    public String toString() {
        return ((Integer) this.getId()).toString();
    }

}
