package OOP.JFRAME;

public class Hospital {
    private String hospital_name;
    private String address;
    private String no_telp;

    public Hospital(String hospital_name, String address, String no_telp) {
        this.hospital_name = hospital_name;
        this.address = address;
        this.no_telp = no_telp;
    }

    public String getHospital_name() {
        return hospital_name;
    }

    public void setHospital_name(String hospital_name) {
        this.hospital_name = hospital_name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNo_telp() {
        return no_telp;
    }

    public void setNo_telp(String no_telp) {
        this.no_telp = no_telp;
    }
}
