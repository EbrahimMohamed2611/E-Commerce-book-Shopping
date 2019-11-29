package j2ee.jsf.beans;

import j2ee.jsf.classes.JDBCClass;

import java.io.Serializable;

import java.util.ArrayList;
import java.util.Date;

public class RegisterBean implements Serializable {

    private String fullName;
    private String userName;
    private String password;
    private String country;
    private ArrayList<String> countriesList = new ArrayList<String>();
    
    private String city;
    private String address;
    private Date dob;
    private String job;
    private ArrayList<String> jobsList = new ArrayList<String>();
    
    private double income;
    private String email;
    private String gender;
    private boolean egyptian;
    private String egyptianValue;
    
    private String mobile;
    
    public RegisterBean() {
        
        countriesList.add("Italy");
        countriesList.add("USA");
        countriesList.add("France");
        
        
        jobsList.add("EE Developer");
        jobsList.add("ADF Developer");
        jobsList.add("Oracle Developer");
        
        
        this.gender = "Male";
        this.egyptian = true;
        this.egyptianValue = "yes";
        
    }


    public void setCountriesList(ArrayList<String> countriesList) {
        this.countriesList = countriesList;
    }

    public ArrayList<String> getCountriesList() {
        return countriesList;
    }

    public void setJobsList(ArrayList<String> jobsList) {
        this.jobsList = jobsList;
    }

    public ArrayList<String> getJobsList() {
        return jobsList;
    }

    public void setEgyptianValue(String egyptianValue) {
        this.egyptianValue = egyptianValue;
    }

    public String getEgyptianValue() {
        return egyptianValue;
    }


    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public Date getDob() {
        return dob;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getJob() {
        return job;
    }

    public void setIncome(double income) {
        this.income = income;
    }

    public double getIncome() {
        return income;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setEgyptian(boolean egyptian) {
        this.egyptian = egyptian;
        
        if(egyptian)
               setEgyptianValue("yes"); 
        else
                setEgyptianValue("no");
        
    }

    public boolean isEgyptian() {
        return egyptian;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getMobile() {
        return mobile;
    }

    public Object registerUser() {
        // Add event code here...
                   boolean regiterOk = JDBCClass.registerCustomer();
                    if(regiterOk)
                            return "RegisterSuccess";
                    else
                            return null;
    }
}
