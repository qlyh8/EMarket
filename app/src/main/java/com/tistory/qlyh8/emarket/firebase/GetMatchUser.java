package com.tistory.qlyh8.emarket.firebase;

import android.util.Log;

//매칭유저 정보 및 예상절약금액 가져오기
public class GetMatchUser {

    public static String matchingUserUid = "";
    public static String matchingUserName = "";
    public static String matchingUserType = "";
    public static Integer matchingUserPowerTrade = 0;
    public static Double userTotalMoney = Double.valueOf(0);
    public static Double userSaveMoney = Double.valueOf(0);

    //청구요금, 예상절약요금 계산
    public static void calculateUserSaveMoney(int matchingUserPowerTrade){

          /* 프로슈머 */
        int powerProvide = GetUserDB.thisUserDB.getPowerProvide();      //수전량
        int powerTrade = GetUserDB.thisUserDB.getPowerTrade();           //추천거래량(잉여량)

        double baseMoney, powerMoney;                               //기본요금, 전력량요금,
        double temp1SumMoney, temp2SumMoney;
        double tradeTemp1SumMoney, beforeTotalSumMoney, tradeEarnMoney;

        /* 컨슈머 */
        int powerUse = GetUserDB.thisUserDB.getPowerUse();              //사용전력량

        //기본요금 + 전력량요금,
        //청구요금 = 기본요금 + 전력량요금 + 전력산업기반기금 + 부가가치세
        double tempSumMoney, totalSumMoney;
        //거래 후 기본요금+전력량요금
        //거래 후 청구요금 = 기본요금 + 전력량요금(거래후) + 전력산업기반기금(거래전) + 부가가치세(거래전)
        double tradePowerMoney, tradeTempSumMoney, tradeTotalSumMoney;
        //Log.d("qwe", String.valueOf(powerProvide));
        if(GetType.userType.equals("prosumer")){
            //프로슈머일때
            if(powerProvide <= 200){
                //기본요금: 910원, 전력량요금: 93.3원
                baseMoney = 910;
                powerMoney = (powerProvide - powerTrade) * 93.3;
                temp1SumMoney = baseMoney + powerMoney;
                temp2SumMoney = baseMoney + powerProvide * 93.3;
                totalSumMoney = temp1SumMoney + temp2SumMoney * 0.037 + temp2SumMoney * 0.1;

                tradePowerMoney = powerProvide * 93.3;
                tradeTemp1SumMoney = baseMoney + tradePowerMoney;
                beforeTotalSumMoney = tradeTemp1SumMoney + tradeTemp1SumMoney * 0.037 + tradeTemp1SumMoney * 0.1;

                tradeEarnMoney = powerTrade * 187.9;
                tradeTotalSumMoney = beforeTotalSumMoney - tradeEarnMoney;

                GetMatchUser.userTotalMoney = tradeTotalSumMoney;
                GetMatchUser.userSaveMoney =  totalSumMoney - tradeTotalSumMoney;
                //Log.d("qwe", String.valueOf(totalSumMoney));
                //Log.d("qwe", String.valueOf(tradeTotalSumMoney));
            }
            else if ((201 <= powerProvide) && (powerProvide <= 400)) {
                //기본요금: 1600원, 전력량요금: 187.9원
                baseMoney = 1600;
                if(powerProvide > powerTrade){
                    powerMoney = (powerProvide - powerTrade) * 93.3;
                    temp1SumMoney = baseMoney + powerMoney;
                    temp2SumMoney = baseMoney + powerProvide * 93.3;
                    totalSumMoney = temp1SumMoney + temp2SumMoney * 0.037 + temp2SumMoney * 0.1;
                }
                else{
                    powerMoney = 0;
                    temp1SumMoney = baseMoney + powerMoney;
                    temp2SumMoney = baseMoney + powerProvide * 93.3;
                    totalSumMoney = temp1SumMoney + temp2SumMoney * 0.037 + temp2SumMoney * 0.1;
                }
                tradePowerMoney = powerProvide * 93.3;
                tradeTemp1SumMoney = baseMoney + tradePowerMoney;
                beforeTotalSumMoney = tradeTemp1SumMoney + tradeTemp1SumMoney * 0.037 + tradeTemp1SumMoney * 0.1;

                tradeEarnMoney = powerTrade * 187.9;
                tradeTotalSumMoney = beforeTotalSumMoney - tradeEarnMoney;

                GetMatchUser.userTotalMoney = totalSumMoney;
                GetMatchUser.userSaveMoney = Double.valueOf(0);
            }
            else{
                //기본요금: 7,300원, 전력량요금: 280.6원
                baseMoney = 7300;
                if(powerProvide > powerTrade){
                    powerMoney = (powerProvide - powerTrade) * 93.3;
                    temp1SumMoney = baseMoney + powerMoney;
                    temp2SumMoney = baseMoney + powerProvide * 93.3;
                    totalSumMoney = temp1SumMoney + temp2SumMoney * 0.037 + temp2SumMoney * 0.1;
                }
                else{
                    powerMoney = 0;
                    temp1SumMoney = baseMoney + powerMoney;
                    temp2SumMoney = baseMoney + powerProvide * 93.3;
                    totalSumMoney = temp1SumMoney + temp2SumMoney * 0.037 + temp2SumMoney * 0.1;
                }
                tradePowerMoney = powerProvide * 93.3;
                tradeTemp1SumMoney = baseMoney + tradePowerMoney;
                beforeTotalSumMoney = tradeTemp1SumMoney + tradeTemp1SumMoney * 0.037 + tradeTemp1SumMoney * 0.1;

                tradeEarnMoney = powerTrade * 187.9;
                tradeTotalSumMoney = beforeTotalSumMoney - tradeEarnMoney;

                GetMatchUser.userTotalMoney = totalSumMoney;
                GetMatchUser.userSaveMoney = Double.valueOf(0);
            }
        }
        else{
            //컨슈머일때
            if(powerUse <= 200){
                //기본요금: 910원, 전력량요금: 93.3원
                baseMoney = 910;
                powerMoney = 200 * 93.3;
                tempSumMoney = baseMoney + powerMoney;
                totalSumMoney = tempSumMoney + tempSumMoney * 0.037 +  tempSumMoney * 0.1;

                GetMatchUser.userTotalMoney = totalSumMoney;
                GetMatchUser.userSaveMoney = Double.valueOf(0);
            }
            else if ((201 <= powerUse) && (powerUse <= 400)){
                //기본요금: 1600원, 전력량요금: 187.9원
                baseMoney = 1600;
                powerMoney = 200 * 93.3 + (powerUse - 200) * 187.9;
                tempSumMoney = baseMoney + powerMoney;
                totalSumMoney = tempSumMoney + tempSumMoney * 0.037 +  tempSumMoney * 0.1;

                GetMatchUser.userTotalMoney = totalSumMoney;
                GetMatchUser.userSaveMoney = Double.valueOf(0);
            }
            else {
                //기본요금: 7,300원, 전력량요금: 280.6원
                baseMoney = 7300;
                powerMoney = 200 * 93.3 + 200 * 187.9 + (powerUse - 400) * 280.6;
                tempSumMoney = baseMoney + powerMoney;
                totalSumMoney = tempSumMoney + tempSumMoney * 0.037 +  tempSumMoney * 0.1;

                if(powerUse - 400 > matchingUserPowerTrade){
                    tradePowerMoney = 200 * 93.3 + 200 * 187.9 + (matchingUserPowerTrade) * 187.9 + (powerUse - 400 - matchingUserPowerTrade) * 280.6;
                }
                else{
                    tradePowerMoney = 200 * 93.3 + 200 * 187.9 + (powerUse - 400) * 187.9;
                }
                tradeTempSumMoney = baseMoney + tradePowerMoney;
                tradeTotalSumMoney = tradeTempSumMoney +  tempSumMoney * 0.037 +  tempSumMoney * 0.1;

                GetMatchUser.userTotalMoney = tradeTotalSumMoney;
                GetMatchUser.userSaveMoney = totalSumMoney - tradeTotalSumMoney;
            }
        }
    }
}
