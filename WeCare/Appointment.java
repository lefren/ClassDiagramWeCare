package OOP.JFRAME;

public class Appointment extends Apptmt{
    private int id;
    private String hospitalName;
    private String doctorName;
    private String status;


    public Appointment(int id, String hospitalName, String doctorName, String status, String patientName) {
        super(patientName, doctorName, hospitalName);
        this.id = id;
        this.hospitalName = hospitalName;
        this.doctorName = doctorName;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public void setHospitalName(String hospitalName) {
        this.hospitalName = hospitalName;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

