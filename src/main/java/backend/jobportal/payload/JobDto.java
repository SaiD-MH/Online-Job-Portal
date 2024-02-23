package backend.jobportal.payload;

import org.springframework.web.multipart.MultipartFile;


public class JobDto {

    private String title;
    private String companyName;
    private String jobDescription;
    private String skills;
    private String jobType;
    private String salaryRange;
    private String experience;
    private String street;
    private String city;
    private String PinCode;
    private String country;
    private MultipartFile companyLogo;

    public JobDto(String title, String companyName,
                  String jobDescription, String skills,
                  String jobType, String salaryRange, String experience,
                  String street, String city, String pinCode,
                  String country, MultipartFile companyLogo) {

        this.title = title;
        this.companyName = companyName;
        this.jobDescription = jobDescription;
        this.skills = skills;
        this.jobType = jobType;
        this.salaryRange = salaryRange;
        this.experience = experience;
        this.street = street;
        this.city = city;
        this.PinCode = pinCode;
        this.country = country;
        this.companyLogo = companyLogo;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getSkills() {
        return skills;
    }

    public void setSkills(String skills) {
        this.skills = skills;
    }

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

    public String getSalaryRange() {
        return salaryRange;
    }

    public void setSalaryRange(String salaryRange) {
        this.salaryRange = salaryRange;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPinCode() {
        return PinCode;
    }

    public void setPinCode(String pinCode) {
        PinCode = pinCode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public MultipartFile getCompanyLogo() {
        return companyLogo;
    }

    public void setCompanyLogo(MultipartFile companyLogo) {
        this.companyLogo = companyLogo;
    }
}
