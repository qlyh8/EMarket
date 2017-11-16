package com.tistory.qlyh8.emarket.dataManager;

import android.app.Activity;
import android.content.Context;

import java.util.HashMap;

/* < USEMONTH 소비패턴(월) >
    번호 년도   월 발전량  수전량 잉여발전량 사용량(kWh)
    1   2017    1   167	426	88	505
    2   2017    2   258	300	161	397
    3   2017    3   322	283	195	410

     UM_ID          (식별번호)
     UM_YEAR        (년도)
     UM_MONTH       (월)
     UM_USE         (사용량(kWh))
     으로 구성 */

public class UseMonthExcelFile extends Activity {

    LoadExcelFiles load = null;

    int idColumnIndex = 0;              //식별번호 칼럼번호
    int yearColumnIndex = 1;            //년도 칼럼번호
    int monthColumnIndex = 2;           //월 칼럼번호
    int useColumnIndex = 6;             //사용량 칼럼번호

    int nRowTotal = 0;                  //전체 ROW 갯수
    int nColTotal = 0;                  //전체 COLUMN 갯수

    public UseMonthExcelFile(){}

    public UseMonthExcelFile(Context context){

        load = new LoadExcelFiles(context, "useMonth.xls");
        nRowTotal = load.nRowTotal;
        nColTotal = load.nColTotal;
    }

    //UM_YEAR(년도)로 해당 소비패턴(월) 정보 가져오기
    public HashMap<String, String> selectByYear(int year){

        HashMap<String, String> hashMap = new HashMap<>();

        for(int i=1 ; i<load.nRowTotal ; i++){
            if(year == Integer.parseInt(load.getCell(yearColumnIndex, i))){
                hashMap.put("UM_USE"+i, load.getCell(useColumnIndex, i));
            }
        }
        return hashMap;
    }

    //UM_YEAR(년도)와 UM_MONTH(월)으로 해당 소비패턴(월) 정보 가져오기
    public HashMap<String, Object> selectByYearMonth(int year, int month){

        HashMap<String, Object> hashMap = new HashMap<>();

        for(int i=1 ; i<load.nRowTotal ; i++){
            if(year == Integer.parseInt(load.getCell(yearColumnIndex, i)) && month == Integer.parseInt(load.getCell(monthColumnIndex, i))){
                hashMap.put("UM_ID", load.getCell(idColumnIndex, i));
                hashMap.put("UM_YEAR", load.getCell(yearColumnIndex, i));
                hashMap.put("UM_MONTH", load.getCell(monthColumnIndex, i));
                hashMap.put("UM_USE", load.getCell(useColumnIndex, i));
                break;
            }
        }
        return hashMap;
    }

    //정보 출력(임시)
    public String setText(int year, int month){

        String text = "";
        HashMap <String, Object> target = selectByYearMonth(year, month);

        if(target.get("UM_YEAR") != null && target.get("UM_MONTH") != null){
            text += "년도: " + target.get("UM_YEAR");
            text += "\n월: " + target.get("UM_MONTH");
            text += "\n사용량: " + target.get("UM_USE");
        }
        else{
            text = "해당 정보 없음";
        }

        return text;
    }
}
