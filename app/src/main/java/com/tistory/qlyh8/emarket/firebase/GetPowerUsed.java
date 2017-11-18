package com.tistory.qlyh8.emarket.firebase;

import android.util.Log;

//사용량 자가진단 정보
public class GetPowerUsed {

    public static int totalPowerUsed;       //사용량
    public static int totalPowerProvide;    //수전량
    public static int powerTrade;           //거래량

    public static int baseMoney;    //기본요금
    public static Double powerMoney;   //전력량요금
    public static Double basePowerMoney;   //요금합계 (수전량기준)
    public static Double basePowerMoney2;   //요금합계 (상계량기준)
    public static Double earnMoney;    //수입
    public static Double etc1Money;    //전력산업기반기금
    public static Double etc2Money;    //부가가치세
    public static Double totalMoney;   //월청구요금

    public static void calculatePowerUsed(){

        totalPowerUsed = GetUserDB.thisUserDB.powerUse;
        totalPowerProvide = GetUserDB.thisUserDB.powerProvide;
        powerTrade = GetUserDB.thisUserDB.powerTrade;

        if(GetUserDB.thisUserDB.getType().equals("prosumer")){
            //프로슈머
            if(totalPowerProvide <= 200){
                //기본요금: 910원, 전력량요금: 93.3원
                baseMoney = 910;
                powerMoney = (totalPowerProvide - powerTrade) * 93.3;
                basePowerMoney = baseMoney + totalPowerProvide * 93.3;
                basePowerMoney2 = baseMoney + powerMoney;
                etc1Money = basePowerMoney * 0.037;
                etc2Money = basePowerMoney * 0.1;
                totalMoney = basePowerMoney2 + basePowerMoney * 0.037 + basePowerMoney * 0.1;
            }
            else if ((201 <= totalPowerProvide) && (totalPowerProvide <= 400)) {
                //기본요금: 1600원, 전력량요금: 187.9원
                baseMoney = 1600;
                if(totalPowerProvide > powerTrade){
                    powerMoney = (totalPowerProvide - powerTrade) * 93.3;
                }
                else{
                    powerMoney = Double.valueOf(0);
                }
                basePowerMoney = baseMoney + 200 * 93.3 + (totalPowerProvide - 200) * 187.9;
                basePowerMoney2 = baseMoney + powerMoney;
                etc1Money = basePowerMoney * 0.037;
                etc2Money = basePowerMoney * 0.1;
                totalMoney = basePowerMoney2 + basePowerMoney * 0.037 + basePowerMoney * 0.1;
            }
            else{
                //기본요금: 7,300원, 전력량요금: 280.6원
                baseMoney = 7300;
                if(totalPowerProvide > powerTrade){
                    powerMoney = (totalPowerProvide - powerTrade) * 93.3;
                }
                else{
                    powerMoney = Double.valueOf(0);
                }
                basePowerMoney = baseMoney + 200 * 93.3 + 200 * 187.9 + (totalPowerProvide - 400) * 93.3;
                basePowerMoney2 = baseMoney + powerMoney;
                etc1Money = basePowerMoney * 0.037;
                etc2Money = basePowerMoney * 0.1;
                totalMoney = basePowerMoney2 + basePowerMoney * 0.037 + basePowerMoney * 0.1;
            }
        }
        else{
            //컨슈머
            if(totalPowerUsed <= 200){
                //기본요금: 910원, 전력량요금: 93.3원
                baseMoney = 910;
                powerMoney = 200 * 93.3;
                basePowerMoney = baseMoney + powerMoney;
                etc1Money = basePowerMoney * 0.037;
                etc2Money = basePowerMoney * 0.1;
                totalMoney = basePowerMoney + etc1Money + etc2Money;
            }
            else if ((201 <= totalPowerUsed) && (totalPowerUsed <= 400)){
                //기본요금: 1600원, 전력량요금: 187.9원
                baseMoney = 1600;
                powerMoney = 200 * 93.3 + (totalPowerUsed - 200) * 187.9;
                basePowerMoney = baseMoney + powerMoney;
                etc1Money = basePowerMoney * 0.037;
                etc2Money = basePowerMoney * 0.1;
                totalMoney = basePowerMoney + etc1Money + etc2Money;
            }
            else {
                //기본요금: 7,300원, 전력량요금: 280.6원
                baseMoney = 7300;
                powerMoney = 200 * 93.3 + 200 * 187.9 + (totalPowerUsed - 400) * 280.6;
                basePowerMoney = baseMoney + powerMoney;
                etc1Money = basePowerMoney * 0.037;
                etc2Money = basePowerMoney * 0.1;
                totalMoney = basePowerMoney + etc1Money + etc2Money;
            }
        }
    }

    public static void calculateTradePowerUsed(){

        totalPowerUsed = GetUserDB.thisUserDB.powerUse;
        totalPowerProvide = GetUserDB.thisUserDB.powerProvide;
        powerTrade = GetUserDB.thisUserDB.powerTrade;

        if(GetUserDB.thisUserDB.getType().equals("prosumer")){
            //프로슈머
            if(totalPowerProvide <= 200){
                //기본요금: 910원, 전력량요금: 93.3원
                baseMoney = 910;
                powerMoney = totalPowerProvide * 93.3;
                basePowerMoney = baseMoney + powerMoney;
                etc1Money = basePowerMoney * 0.037;
                etc2Money = basePowerMoney * 0.1;
                earnMoney = powerTrade * 187.9;
                totalMoney = (basePowerMoney + etc1Money + etc2Money) - earnMoney;
            }
            else if ((201 <= totalPowerProvide) && (totalPowerProvide <= 400)) {
                //기본요금: 1600원, 전력량요금: 187.9원
                baseMoney = 1600;
                powerMoney = totalPowerProvide * 93.3;
                basePowerMoney = baseMoney + powerMoney;
                etc1Money = basePowerMoney * 0.037;
                etc2Money = basePowerMoney * 0.1;
                earnMoney = 0 * 187.9;
                totalMoney = (basePowerMoney + etc1Money + etc2Money) - earnMoney;
            }
            else{
                //기본요금: 7,300원, 전력량요금: 280.6원
                baseMoney = 7300;
                powerMoney = totalPowerProvide * 93.3;
                basePowerMoney = baseMoney + powerMoney;
                etc1Money = basePowerMoney * 0.037;
                etc2Money = basePowerMoney * 0.1;
                earnMoney = 0 * 187.9;
                totalMoney = (basePowerMoney + etc1Money + etc2Money) - earnMoney;
            }
        }
        else{
            //컨슈머
            if(totalPowerUsed <= 200){
                //기본요금: 910원, 전력량요금: 93.3원
                baseMoney = 910;
                powerMoney = 200 * 93.3;
                basePowerMoney = baseMoney + powerMoney;
                etc1Money = basePowerMoney * 0.037;
                etc2Money = basePowerMoney * 0.1;
                totalMoney = basePowerMoney + etc1Money + etc2Money;
            }
            else if ((201 <= totalPowerUsed) && (totalPowerUsed <= 400)){
                //기본요금: 1600원, 전력량요금: 187.9원
                baseMoney = 1600;
                powerMoney = 200 * 93.3 + (totalPowerUsed - 200) * 187.9;
                basePowerMoney = baseMoney + powerMoney;
                etc1Money = basePowerMoney * 0.037;
                etc2Money = basePowerMoney * 0.1;
                totalMoney = basePowerMoney + etc1Money + etc2Money;
            }
            else {
                //기본요금: 7,300원, 전력량요금: 280.6원
                baseMoney = 7300;
                powerMoney = 200 * 93.3 + 200 * 187.9 + (totalPowerUsed - 400) * 187.9;
                basePowerMoney = baseMoney + powerMoney;
                etc1Money = (7300 + (200 * 93.3 + 200 * 187.9 + (totalPowerUsed - 400) * 280.6)) * 0.037;
                etc2Money = (7300 + (200 * 93.3 + 200 * 187.9 + (totalPowerUsed - 400) * 280.6)) * 0.1;
                totalMoney = basePowerMoney + etc1Money + etc2Money;
            }
        }
    }
}
