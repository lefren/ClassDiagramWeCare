package OOP.JFRAME;

public class Appointment {
    private int id;
    private String hospitalName;
    private String doctorName;

    public Appointment(int id, String hospitalName, String doctorName) {
        this.id = id;
        this.hospitalName = hospitalName;
        this.doctorName = doctorName;
    }

    public int getId() {
        return id;
    }

    public String getHospitalName() {
        return hospitalName;
    }

    public String getDoctorName() {
        return doctorName;
    }
}

