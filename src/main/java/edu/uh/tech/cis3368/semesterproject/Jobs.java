package edu.uh.tech.cis3368.semesterproject;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Jobs {
    private int id;
    private String customerFirstName;
    private String customerLastName;
    private String customerPhone;
    private String customerAddress;
    private String jobName;
    private String jobDescription;
    private String jobStage;


    public Jobs(String firstName, String lastName, String phone, String address, String name, String description, String stage)
    {
        this.customerFirstName=firstName;
        this.customerLastName=lastName;
        this.customerPhone=phone;
        this.customerAddress=address;
        this.jobName=name;
        this.jobDescription=description;
        this.jobStage=stage;
    }


    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "CUSTOMER_FIRSTNAME", nullable = false, length = 24)
    public String getCustomerFirstname() {
        return customerFirstName;
    }

    public void setCustomerFirstname(String customerFirstName) {
        this.customerFirstName = customerFirstName;
    }

    @Basic
    @Column(name = "CUSTOMER_LASTNAME", nullable = false, length = 24)
    public String getCustomerLastname() {
        return customerLastName;
    }

    public void setCustomerLastname(String customerLastName) {
        this.customerLastName = customerLastName;
    }

    @Basic
    @Column(name = "CUSTOMER_PHONE", nullable = false, length = 15)
    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    @Basic
    @Column(name = "CUSTOMER_ADDRESS", nullable = false, length = 100)
    public String getCustomerAddress() {
        return customerAddress;
    }

    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    @Basic
    @Column(name = "JOB_NAME", nullable = false, length = 50)
    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    @Basic
    @Column(name = "JOB_DESCRIPTION", nullable = false, length = 100)
    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    @Basic
    @Column(name = "JOB_STAGE", nullable = false, length = 20)
    public String getJobStage() {
        return jobStage;
    }

    public void setJobStage(String jobStage) {
        this.jobStage = jobStage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Jobs jobs = (Jobs) o;
        return id == jobs.id &&
                Objects.equals(customerFirstName, jobs.customerFirstName) &&
                Objects.equals(customerLastName, jobs.customerLastName) &&
                Objects.equals(customerPhone, jobs.customerPhone) &&
                Objects.equals(customerAddress, jobs.customerAddress) &&
                Objects.equals(jobName, jobs.jobName) &&
                Objects.equals(jobDescription, jobs.jobDescription) &&
                Objects.equals(jobStage, jobs.jobStage);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, customerFirstName, customerLastName, customerPhone, customerAddress, jobName, jobDescription, jobStage);
    }
}
