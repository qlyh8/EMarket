package com.tistory.qlyh8.emarket.model;

//유저 정보 클래스
public class User {
    //알트 + insert 누르면 클래스 함수 자동으로 만들어주는 단축키
    //파이어베이스용으로 쓰려면 public 멤버 변수들이랑 public 생성자, pubiic get, set 함수들만 있으면 충분
    /*
        user
            ㄴTsfgwslfdrgfklsgwle(user uid)
                ㄴ address: ""                   주소
                ㄴ addressNumber:                주소번호
                ㄴ dueDate:                      요금납기일
                ㄴ latitude:                     위도
                ㄴ longitude:                    경도
                ㄴ meterReadDate:                검침일
                ㄴ phone: ""                     핸드폰번호
                ㄴ possibleTradeDate: ""         거래가능일
                ㄴ powerNumber: ""               전력량계번호
                ㄴ powerProvide: ""              한전수전량
                ㄴ powerTrade:                   추천거래량
                ㄴ powerUse:                     누적사용량
                ㄴ type: ""                      유형(프로슈머/컨슈머)
                ㄴ username: ""                  이름
     */
    public String address;
    public int addressNumber;
    public int dueDate;
    public double latitude;
    public double longitude;
    public int meterReadDate;
    public String phone;
    public String possibleTradeDate;
    public String powerNumber;
    public int powerProvide;
    public int powerTrade;
    public int powerUse;
    public String type;
    public String username;

    //빈 생성자가 없으면 에러발생
    public User(){
    }

    public User(int dueDate, int meterReadDate, String possibleTradeDate) {
        this.dueDate = dueDate;
        this.meterReadDate = meterReadDate;
        this.possibleTradeDate = possibleTradeDate;
    }

    public User(String address, int addressNumber, double latitude, double longitude, String powerNumber, int powerProvide, int powerTrade, int powerUse, String username) {
        this.address = address;
        this.addressNumber = addressNumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.powerNumber = powerNumber;
        this.powerProvide = powerProvide;
        this.powerTrade = powerTrade;
        this.powerUse = powerUse;
        this.username = username;
    }

    public User(int addressNumber, double latitude, double longitude, int powerTrade, String type, String username) {
        this.addressNumber = addressNumber;
        this.latitude = latitude;
        this.longitude = longitude;
        this.powerTrade = powerTrade;
        this.type = type;
        this.username = username;
    }

    public User(String address, int addressNumber, int dueDate, double latitude, double longitude, int meterReadDate, String phone, String possibleTradeDate, String powerNumber, int powerTrade, int powerUse, String type, String username) {
        this.address = address;
        this.addressNumber = addressNumber;
        this.dueDate = dueDate;
        this.latitude = latitude;
        this.longitude = longitude;
        this.meterReadDate = meterReadDate;
        this.phone = phone;
        this.possibleTradeDate = possibleTradeDate;
        this.powerNumber = powerNumber;
        this.powerTrade = powerTrade;
        this.powerUse = powerUse;
        this.type = type;
        this.username = username;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(int addressNumber) {
        this.addressNumber = addressNumber;
    }

    public int getDueDate() {
        return dueDate;
    }

    public void setDueDate(int dueDate) {
        this.dueDate = dueDate;
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

    public int getMeterReadDate() {
        return meterReadDate;
    }

    public void setMeterReadDate(int meterReadDate) {
        this.meterReadDate = meterReadDate;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPossibleTradeDate() {
        return possibleTradeDate;
    }

    public void setPossibleTradeDate(String possibleTradeDate) {
        this.possibleTradeDate = possibleTradeDate;
    }

    public String getPowerNumber() {
        return powerNumber;
    }

    public void setPowerNumber(String powerNumber) {
        this.powerNumber = powerNumber;
    }

    public int getPowerProvide() {
        return powerProvide;
    }

    public void setPowerProvide(int powerProvide) {
        this.powerProvide = powerProvide;
    }

    public int getPowerTrade() {
        return powerTrade;
    }

    public void setPowerTrade(int powerTrade) {
        this.powerTrade = powerTrade;
    }

    public int getPowerUse() {
        return powerUse;
    }

    public void setPowerUse(int powerUse) {
        this.powerUse = powerUse;
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
