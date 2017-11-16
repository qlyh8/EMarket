package com.tistory.qlyh8.emarket.model;

//거래 등록 클래스
public class Enroll {
    //알트 + insert 누르면 클래스 함수 자동으로 만들어주는 단축키
    //파이어베이스용으로 쓰려면 public 멤버 변수들이랑 public 생성자, pubiic get, set 함수들만 있으면 충분
    /*
          enroll
            ㄴTsfgwslfdrgfklsgwle(user uid)
	            ㄴ Gsfkrgsdfgrfdsdvfg(user enroll id)
                    ㄴ year: "2017"
                    ㄴ month: "2"
                    ㄴ day: "3"
                    ㄴ status: 1
                    ㄴ tradeUser: trade user
     */
    public String year;
    public String month;
    public String day;
    public int status;
    public String tradeUser;

    //빈 생성자가 없으면 에러발생
    public Enroll(){
    }

    public Enroll(String year, String month, String day, int status, String tradeUser) {
        this.year = year;
        this.month = month;
        this.day = day;
        this.status = status;
        this.tradeUser = tradeUser;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTradeUser() {
        return tradeUser;
    }

    public void setTradeUser(String tradeUser) {
        this.tradeUser = tradeUser;
    }
}
