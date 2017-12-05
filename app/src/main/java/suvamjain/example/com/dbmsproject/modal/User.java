package suvamjain.example.com.dbmsproject.modal;

/**
 * Created by SUVAM JAIN on 29-03-2017.
 */

public class User {

    private int id;
    private String name;
    private String email;
    private String password;
    private String security;
    private String yoa;
    private String semester;
    private String sex;
    private String dob;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSecurity() { return security; }

    public void setSecurity(String security) { this.security = security; }

    public String getSex() { return sex; }

    public void setSex(String sex) { this.sex = sex; }

    public String getDob() { return dob; }

    public void setDob(String dob) { this.dob = dob; }

    public String getYoa() { return yoa; }

    public void setYoa(String yoa) { this.yoa = yoa; }

    public String getSem() { return semester; }

    public void setSem(String semester) { this.semester = semester; }



}
