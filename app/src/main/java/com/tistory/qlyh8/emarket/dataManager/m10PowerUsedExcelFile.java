package com.tistory.qlyh8.emarket.dataManager;

import android.app.Activity;
import android.content.Context;
import android.util.Log;

import java.util.HashMap;

/* < USEMONTH 소비패턴(월) >
    월   일   유저아이디  컨슈머사용량   발전량     수전량     잉여발전량   프로슈머사용량(kWh)

     UM_MONTH       (월)
     UM_DAY         (일)
     UM_UID          (유저아이디)
     UM_USE_C       (컨슈머사용량(kWh))
     UM_PRODUCE     (발전량(kWh))
     UM_PROVIDE     (수전량(kWh))
     UM_SURPLUS     (잉여량(kWh))
     UM_USE_P       (프로슈머사용량(kWh))  --> 발전량 - 수전량 + 잉여량
     으로 구성 */

public class m10PowerUsedExcelFile extends Activity {

    LoadExcelFiles load = null;

    int monthColumnIndex = 0;               //월 칼럼번호
    int dayColumnIndex = 1;                 //일 칼럼번호
    int uidColumnIndex = 2;                 //유저아이디 칼럼번호
    int useConsumerColumnIndex = 3;         //컨슈머사용량 칼럼번호
    int produceColumnIndex = 4;             //발전량 칼럼번호
    int provideColumnIndex = 5;             //수전량 칼럼번호
    int surplusColumnIndex = 6;             //잉여량 칼럼번호
    int useProsumerColumnIndex = 7;         //프로슈머사용량 칼럼번호

    int nRowTotal = 0;                      //전체 ROW 갯수
    int nColTotal = 0;                      //전체 COLUMN 갯수

    public m10PowerUsedExcelFile(){}

    public m10PowerUsedExcelFile(Context context){

        load = new LoadExcelFiles(context, "m10PowerUsed.xls");
        nRowTotal = load.nRowTotal;
        nColTotal = load.nColTotal;
    }

    //UM_UID(유저아이디)로 해당 소비패턴 정보 가져오기
    public HashMap<String, String> selectByUid(String uid){

        HashMap<String, String> hashMap = new HashMap<>();

        for(int i=1 ; i<load.nRowTotal ; i++){
            if(uid.equals(load.getCell(uidColumnIndex, i))){
                hashMap.put("UM_MONTH"+i, load.getCell(monthColumnIndex, i));
                hashMap.put("UM_DAY"+i, load.getCell(dayColumnIndex, i));
                hashMap.put("UM_UID"+i, load.getCell(uidColumnIndex, i));
                hashMap.put("UM_USE_C"+i, load.getCell(useConsumerColumnIndex, i));
                hashMap.put("UM_PRODUCE"+i, load.getCell(produceColumnIndex, i));
                hashMap.put("UM_PROVIDE"+i, load.getCell(provideColumnIndex, i));
                hashMap.put("UM_SURPLUS"+i, load.getCell(surplusColumnIndex, i));
                hashMap.put("UM_USE_P"+i, load.getCell(useProsumerColumnIndex, i));
            }
        }
        return hashMap;
    }

    //정보 출력(임시)
    public String setText(String uid){

        String text = "";
        HashMap <String, String> target = selectByUid(uid);

        if(target.get("UM_UID") != null){
            text += "1일 " + target.get("UM_DAY1");
            text += "\n유저아이디: " + target.get("UM_UID1");
            text += "\n프로슈머사용량: " + target.get("UM_USE_P");
        }
        else{
            text = "해당 정보 없음";
        }

        return text;
    }
}
