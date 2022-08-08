package com.example.group16.computer_model;

public class computerModel {
    private String serial;
    private  String name;
    private String ram;
    private String size;

//    public computerModel() {
//    }

    public computerModel(String serial, String name, String ram, String size) {
        this.serial = serial;
        this.name = name;
        this.ram = ram;
        this.size = size;
    }


    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRam() {
        return ram;
    }

    public void setRam(String ram) {
        this.ram = ram;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
