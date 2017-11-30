E-MARKET
===================

2017 에너지 생활기술 경진대회 **에너지톤**을 위해 개발한 어플리케이션으로,  
현재 한국전력공사에서 시행중인 소규모 전력 거래 사업에 대한 문제점을 보완하고 재구성하였다.  

[시연 동영상](https://www.youtube.com/watch?v=5YdAM6rtaPU&feature=youtu.be)  
![Alt text](https://github.com/qlyh8/EMarket/blob/master/app/src/main/demo/screenshot/1_splash.png)   

>[에너지톤](http://env.seoul.go.kr/)은 서울시에서 주최한 경진대회이며 에너지절약과 생산으로 원전하나 줄이기 목표 달성에 기여할 수 있는 서비스나 제품을 만들어내는 해커톤 방식의 대회이다.
----------


Team
----------
기획: 정지윤, 김은성, 정지수  
개발: 신윤희  


Idea
----------
태양광 발전 거래는 꾸준히 증가하고 있으며 이에 따라 소규모 전력 거래 시장에 대한 관심이 사회적으로 증대되고 있다.  
현재 진행 중인 한국전력공사(이하 한전)의 프로슈머 전력거래는 태양광 발전설비 설치자가 생산한 전기 중 자신이 사용하고 남는 전기를 한전의 중개를 통해 이웃 등에게 판매하는 제도를 말한다.  
이 거래 제도는 소비자 참여 활성화와 관련된 몇 가지 시스템 보완을 검토할 필요가 있다.  


Problem
----------
기존 프로슈머 전력거래 시스템의 문제점

1.  복잡한 거래 신청 절차
2.  중개 및 거래까지 많은 시간이 소요
3.  거래 가능 조건의 제한적 범위
4.  거래 가능 여부 정보를 한전만이 알 수 있어 소비자가 거래에 수동적으로 참여  

Feature
----------
해당 문제점을 수정보완한 애플리케이션(**E-MARKET**)의 특징  

1.  간단한 거래 신청 절차
2.  거래가 가능한 모든 잠재적 대상 추출
3.  프로슈머 및 컨슈머가 자발적으로 거래에 참여할 수 있는 정보 제공
4.  프로슈머 및 컨슈머의 과거 전력소비 이력과 예상 가능 전력 거래량 및 전력 요금에 대한 정보 제공   

  
Details - 안내
----------
생소한 거래 제도에 대한 설명 및 안내 화면  

![Alt text](https://github.com/qlyh8/EMarket/blob/master/app/src/main/demo/screenshot/2_info_1.png) 
![Alt text](https://github.com/qlyh8/EMarket/blob/master/app/src/main/demo/screenshot/3_info_2.png)
![Alt text](https://github.com/qlyh8/EMarket/blob/master/app/src/main/demo/screenshot/4_info_3.png)
![Alt text](https://github.com/qlyh8/EMarket/blob/master/app/src/main/demo/screenshot/5_info_4.png)
![Alt text](https://github.com/qlyh8/EMarket/blob/master/app/src/main/demo/screenshot/6_info_5.png)   
   
   
Details - 정보 입력
----------
최초 로그인 시 필요한 정보를 입력하는 화면  

1. 핸드폰 본인 인증   
핸드폰 번호로 회원 등록   
![Alt text](https://github.com/qlyh8/EMarket/blob/master/app/src/main/demo/screenshot/7_enroll_phone_number_1.png) 
![Alt text](https://github.com/qlyh8/EMarket/blob/master/app/src/main/demo/screenshot/8_enroll_phone_number_2.png)
![Alt text](https://github.com/qlyh8/EMarket/blob/master/app/src/main/demo/screenshot/9_enroll_phone_number_3.png)  
  
2. 전력량계 번호 입력   
회원의 전력 사용에 대한 정보를 얻기 위한 절차  
![Alt text](https://github.com/qlyh8/EMarket/blob/master/app/src/main/demo/screenshot/10_enroll_power_number.png)   

3. 유형 선택  
프로슈머와 컨슈머 중 하나 선택  
![Alt text](https://github.com/qlyh8/EMarket/blob/master/app/src/main/demo/screenshot/11_enroll_type.png)
   
   
Details - 홈
----------
상단바에 내 정보를 볼 수 있는 아이콘을 비롯해  
자가진단, 거래하기, 매칭현황, 리포트의 서비스를 제공하는 메인 화면   
  
![Alt text](https://github.com/qlyh8/EMarket/blob/master/app/src/main/demo/screenshot/12_home.png)   
   
   
Details - 프로필
----------
상단바에 위치한 내 정보 화면   
나의 유형과 핸드폰 번호, 주소 및 전력량계 번호를 볼 수 있다.  
  
![Alt text](https://github.com/qlyh8/EMarket/blob/master/app/src/main/demo/screenshot/13_profile.png)  
   
   
Details - 자가진단
----------
전월 사용량과 추천 거래량을 볼 수 있는 자가진단 화면  
전 월에 소비한 전력량과 청구된 요금을 볼 수 있는 사용량 화면 및 소비 이력을 토대로 전력 거래 시 최대로 거래할 수 있는 전력량과 그 결과 예상 가능한 절약 금액을 보여주는 추천 거래량 화면이 있다.  
  
![Alt text](https://github.com/qlyh8/EMarket/blob/master/app/src/main/demo/screenshot/14_feature1_1.png)
![Alt text](https://github.com/qlyh8/EMarket/blob/master/app/src/main/demo/screenshot/15_feature1_2.png)  
    
     
Details - 거래하기
----------
전력 거래를 신청하는 거래하기 화면   
자신의 위치를 중심으로 동일한 배전망에 위치한 현재 등록된 거래 가능자의 위치가 지도에 표시된다. 지도 하단에는 자신의 거래 가능량을 볼 수 있으며, 동일 배전망을 가진 회원들의 거래량이 리스트로 보여진다.   
매칭 시 가장 최적의 거래자를 찾고, 추천하는 거래 전력량과 실제로 거래될 전력량 및 이 때 발생할 수 있는 예상 절약 금액이 팝업창으로 띄어진다. 
  
![Alt text](https://github.com/qlyh8/EMarket/blob/master/app/src/main/demo/screenshot/16_feature2_1.png)
![Alt text](https://github.com/qlyh8/EMarket/blob/master/app/src/main/demo/screenshot/17_feature2_2.png)  
   
   
Details - 매칭현황
----------
거래를 신청한 후 보여지는 매칭현황 화면  
상단에는 거래 신청 정보 목록이 있고 하단에는 신청 절차가 보여진다.    
  
![Alt text](https://github.com/qlyh8/EMarket/blob/master/app/src/main/demo/screenshot/18_feature3.png)  
   
   
Details - 리포트
----------
청구서와 거래이력을 볼 수 있는 리포트 화면  
청구서는 거래 후 실제로 받게 되는 전력량과 청구 요금이 나타나며, 거래 이력에는 거래가 진행되었던 과거 이력을 비롯해 현재까지 누적된 절약 요금을 볼 수 있다.   
  
![Alt text](https://github.com/qlyh8/EMarket/blob/master/app/src/main/demo/screenshot/19_feature4_1.png)
![Alt text](https://github.com/qlyh8/EMarket/blob/master/app/src/main/demo/screenshot/20_feature4_2.png)  
  
