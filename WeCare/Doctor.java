package OOP.JFRAME;

public class Doctor {
    private String doctorName;
    private String nomorStr;
    private String phoneNum;
    private String hospitalPlace;
    private String pengalamanKerja;

    public Doctor(String doctorName, String nomorStr, String phoneNum, String hospitalPlace, String pengalamanKerja) {
        this.doctorName = doctorName;
        this.nomorStr = nomorStr;
        this.phoneNum = phoneNum;
        this.hospitalPlace = hospitalPlace;
        this.pengalamanKerja = pengalamanKerja;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getNomorStr() {
        return nomorStr;
    }

    public void setNomorStr(String nomorStr) {
        this.nomorStr = nomorStr;
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public String getHospitalPlace() {
        return hospitalPlace;
    }

    public void setHospitalPlace(String hospitalPlace) {
        this.hospitalPlace = hospitalPlace;
    }

    public String getPengalamanKerja() {
        return pengalamanKerja;
    }

    public void setPengalamanKerja(String pengalamanKerja) {
        this.pengalamanKerja = pengalamanKerja;
    }
}
