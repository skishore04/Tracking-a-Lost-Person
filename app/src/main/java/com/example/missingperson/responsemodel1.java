package com.example.missingperson;

public class responsemodel1 {
    String idno, name, gender,age,date,time,address,number,marks,img;

    public responsemodel1() {
    }

    public responsemodel1(String idno, String name, String gender, String age, String date, String time, String address, String number, String marks, String img) {
        this.idno = idno;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.date = date;
        this.time = time;
        this.address = address;
        this.number = number;
        this.marks = marks;
        this.img = img;
    }

    public String getIdno() {
        return idno;
    }

    public void setIdno(String idno) {
        this.idno = idno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMarks() {
        return marks;
    }

    public void setMarks(String marks) {
        this.marks = marks;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
