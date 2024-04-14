서버 포트는 8095 입니다
application 모듈의 application.properties에서 설정해주시면됩니다.

db설정과 jpa 설정을 변경하고 싶으시면
internal모듈의 internal.properties 를 변경해주세요.


API
  sync 
  기상청_단기예보 ((구)_동네예보) 조회서비스 를 사용하여
  송산1동,2동의 X,Y 좌표값으로 날씨를 조회합니다
  
- api 데이터 DB에 저장

  inquire  
    - GET 요청시,db에서 fcstDate에 해당하는 날짜의 데이터 조회
    - 데이터가 없을 경우, Http status 204 오류를 응답.


URI
  sync
    ex) http://localhost:8095/sync?baseDate=20240414
  inquire
    ex) http://localhost:8095/inquire?fcstDate=20240414
