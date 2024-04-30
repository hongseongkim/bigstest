서버 포트는 8095 입니다

 기상청_단기예보 ((구)_동네예보)는 3일

API
  sync 
    -Post요청시 기상청_단기예보 ((구)_동네예보) 조회서비스 를 사용하여
    - 송산1동,2동의 X,Y 좌표값으로 날씨를 조회후 데이터를 db에 저장합니다
    - ex) http://localhost:8095/sync?baseDate=20240414

    조회 날짜는 최근 3일이여야하며 api에서 data 가 제공되지않는 날도 존재합니다.


  inquire  
    - GET 요청시,db에서 fcstDate에 해당하는 날짜의 데이터 조회
    - ex) http://localhost:8095/inquire?fcstDate=20240414
    - 데이터가 없을 경우, Http status 204 오류를 응답.


    

