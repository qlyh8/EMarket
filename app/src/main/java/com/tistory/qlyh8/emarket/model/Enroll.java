package com.tistory.qlyh8.emarket.model;

import com.tistory.qlyh8.emarket.firebase.GetAuth;

public class Enroll {
    //알트 + insert 누르면 클래스 함수 자동으로 만들어주는 단축키
    //파이어베이스용으로 쓰려면 public 멤버 변수들이랑 public 생성자, pubiic get, set 함수들만 있으면 충분
    /*
        enroll
         ㄴ prosumer
            ㄴTsfgwslfdrgfklsgwle(user uid)
	            ㄴ Gsfkrgsdfgrfdsdvfg(user enroll id)
                    ㄴ year: "2017"
                    ㄴ ok: ("y" or"n")
                    ㄴ name : "고객이름"
                    ㄴ uniqueNumber : "987654"
                    ㄴ phone : "01012345678"
                    ㄴ address : "주소"
                    ㄴ power : "100"
     */
    public String year;
    public String ok;
    public String name;
    public String uniqueNumber;
    public String phone;
    public String address;
    public String power;
    public double latitude;
    public double longitude;


    //빈 생성자가 없으면 에러발생
    public Enroll(){
    }

    public Enroll(String year, String ok, String name, String uniqueNumber, String phone, String address, String power, double latitude, double longitude) {
        this.year = year;
        this.ok = ok;
        this.name = name;
        this.uniqueNumber = uniqueNumber;
        this.phone = phone;
        this.address = address;
        this.power = power;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getOk() {
        return ok;
    }

    public void setOk(String ok) {
        this.ok = ok;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUniqueNumber() {
        return uniqueNumber;
    }

    public void setUniqueNumber(String uniqueNumber) {
        this.uniqueNumber = uniqueNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

}
