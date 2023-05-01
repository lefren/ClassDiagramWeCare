package OOP.JFRAME;

public class Apptmt {

    private String patientname;
    private String doctorname;
    private String hospitalname;

    public Apptmt(String patientname, String doctorname, String hospitalname) {
        this.patientname = patientname;
        this.doctorname = doctorname;
        this.hospitalname = hospitalname;
    }

    public String getPatientname() {
        return patientname;
    }

    public void setPatientname(String patientname) {
        this.patientname = patientname;
    }

    public String getDoctorname() {
        return doctorname;
    }

    public void setDoctorname(String doctorname) {
        this.doctorname = doctorname;
    }

    public String getHospitalname() {
        return hospitalname;
    }

    public void setHospitalname(String hospitalname) {
        this.hospitalname = hospitalname;
    }
}
