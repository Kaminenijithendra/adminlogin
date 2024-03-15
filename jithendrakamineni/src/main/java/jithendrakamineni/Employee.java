package jithendrakamineni;

import java.util.Arrays;

public class Employee {
    private int EMPLOYEE_ID;
    private String NAME;
    private String EMAIL;
    private String MOBILE_NO;
    private String DESIGNATION;
    private String GENDER;
    private String[]  COURSES;
    private byte[] photo;
    
    public int getEMPLOYEE_ID() {
        return EMPLOYEE_ID;
    }

    public void setEMPLOYEE_ID(int i) {
        this.EMPLOYEE_ID = i;
    }

    public String getNAME() {
        return NAME;
    }

    public void setNAME(String NAME) {
        this.NAME = NAME;
    }

    public String getEMAIL() {
        return EMAIL;
    }

    public void setEMAIL(String EMAIL) {
        this.EMAIL = EMAIL;
    }

    public String getMOBILE_NO() {
        return MOBILE_NO;
    }

    public void setMOBILE_NO(String MOBILE_NO) {
        this.MOBILE_NO = MOBILE_NO;
    }

    public String getDESIGNATION() {
        return DESIGNATION;
    }

    public void setDESIGNATION(String DESIGNATION) {
        this.DESIGNATION = DESIGNATION;
    }

    public String getGENDER() {
        return GENDER;
    }

    public void setGENDER(String GENDER) {
        this.GENDER = GENDER;
    }

    public String[] getCOURSES() {
        return  COURSES;
    }

    public void setCourses(String[]  COURSES) {
        this. COURSES =  COURSES;
    }
    public byte[] getPhoto() { return photo; }
    public void setPhoto(byte[] photo) { 
    	this.photo = photo; 
    	}

    @Override
    public String toString() {
        return "Employee{" +
                "EMPLOYEE_ID='" + EMPLOYEE_ID + '\'' +
                ", name='" + NAME + '\'' +
                ", email='" + EMAIL + '\'' +
                ", mobile_no='" + MOBILE_NO + '\'' +
                ", designation='" + DESIGNATION + '\'' +
                ", gender='" + GENDER + '\'' +
                ", courses=" + COURSES + '\''+
                ", photo=" + Arrays.toString(photo) +
                '}';
    }



}
