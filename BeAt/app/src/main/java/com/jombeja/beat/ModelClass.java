package com.jombeja.beat;


public class ModelClass {
    private String Id;
    private String Name;
    private String IdNumber;
    private String EmailAddress;
    private String DeviceName;
    private String MacAddress;
    private String Time;

    public ModelClass(String id, String name, String idNumber, String emailaddress, String deviceName, String macAddress, String time) {
        this.Id = id;
        this.Name = name;
        this.IdNumber = idNumber;
        this.EmailAddress = emailaddress;
        this.DeviceName = deviceName;
        this.MacAddress = macAddress;
        this.Time = time;
    }

    public ModelClass() {

    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }
    public void setName(String name) {
        this.Name = name;
    }

    public String getIdNumber() {
        return IdNumber;
    }

    public void setIdNumber(String idNumber) {
        this.IdNumber = idNumber;
    }

    public String getDeviceName() {
        return DeviceName;
    }

    public void setDeviceName(String deviceName) {
        this.DeviceName = deviceName;
    }

    public String getEmailAddress() {
        return EmailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        EmailAddress = emailAddress;
    }

    public String getMacAddress() {
        return MacAddress;
    }

    public void setMacAddress(String macAddress) {
        this.MacAddress = macAddress;
    }

    public String getTime() {
        return Time;
    }

    public void setTime(String time) {
        this.Time = time;
    }
}
