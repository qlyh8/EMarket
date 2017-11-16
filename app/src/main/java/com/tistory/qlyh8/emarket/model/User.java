package com.tistory.qlyh8.emarket.model;

//유저 정보 클래스
public class User {
    //알트 + insert 누르면 클래스 함수 자동으로 만들어주는 단축키
    //파이어베이스용으로 쓰려면 public 멤버 변수들이랑 public 생성자, pubiic get, set 함수들만 있으면 충분
    /*
        user
            ㄴTsfgwslfdrgfklsgwle(user uid)
                ㄴ address: ""
                ㄴ initialDay: ""
                ㄴ initialMonth: ""
                ㄴ initialYear: ""
                ㄴ latitude: ""
                ㄴ longitude: ""
                ㄴ phone: ""
                ㄴ powerNumber: ""
                ㄴ powerTrade: ""
                ㄴ powerUsed: ""
                ㄴ type: ""
                ㄴ username: ""
     */
    public String address;
    public int initialDay;
    public int initialMonth;
    public int initialYear;
    public double latitude;
    public double longitude;
    public String phone;
    public String powerNumber;
    public int powerTrade;
    public int powerUsed;
    public String type;
    public String username;

    //빈 생성자가 없으면 에러발생
    public User(){
    }

    public User(String address, int initialDay, int initialMonth, int initialYear, double latitude, double longitude, String phone, String powerNumber, int powerTrade, int powerUsed, String type, String username) {
        this.address = address;
        this.initialDay = initialDay;
        this.initialMonth = initialMonth;
        this.initialYear = initialYear;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phone = phone;
        this.powerNumber = powerNumber;
        this.powerTrade = powerTrade;
        this.powerUsed = powerUsed;
        this.type = type;
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getInitialDay() {
        return initialDay;
    }

    public void setInitialDay(int initialDay) {
        this.initialDay = initialDay;
    }

    public int getInitialMonth() {
        return initialMonth;
    }

    public void setInitialMonth(int initialMonth) {
        this.initialMonth = initialMonth;
    }

    public int getInitialYear() {
        return initialYear;
    }

    public void setInitialYear(int initialYear) {
        this.initialYear = initialYear;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPowerNumber() {
        return powerNumber;
    }

    public void setPowerNumber(String powerNumber) {
        this.powerNumber = powerNumber;
    }

    public int getPowerTrade() {
        return powerTrade;
    }

    public void setPowerTrade(int powerTrade) {
        this.powerTrade = powerTrade;
    }

    public int getPowerUsed() {
        return powerUsed;
    }

    public void setPowerUsed(int powerUsed) {
        this.powerUsed = powerUsed;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
