package com.test.bank;

public class BankInfo {
    private String id;
    private String bankName;
    private String type;
    private String city;
    private String state;
    private String zipCode;

    @Override
    public String toString() {
        String res = "";
        res += "id: " + id + "\n";
        res += "bankName: " + bankName + "\n";
        res += "type: " + type + "\n";
        res += "city: " + city + "\n";
        res += "state: " + state + "\n";
        res += "zipCode: " + zipCode + "\n";

        return res;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }
}
