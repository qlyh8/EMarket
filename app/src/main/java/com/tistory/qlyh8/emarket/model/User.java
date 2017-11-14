package com.tistory.qlyh8.emarket.model;

public class User {
    //알트 + insert 누르면 클래스 함수 자동으로 만들어주는 단축키
    //파이어베이스용으로 쓰려면 public 멤버 변수들이랑 public 생성자, pubiic get, set 함수들만 있으면 충분
    /*
        user
            ㄴTsfgwslfdrgfklsgwle(user uid)
                ㄴ address: ""
                ㄴ phone: ""
                ㄴ powerNumber: ""
                ㄴ type: ""
     */
    public String address;
    public String phone;
    public String powerNumber;
    public String type;


    //빈 생성자가 없으면 에러발생
    public User(){
    }

    public User(String address, String phone, String powerNumber, String type) {
        this.address = address;
        this.phone = phone;
        this.powerNumber = powerNumber;
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
