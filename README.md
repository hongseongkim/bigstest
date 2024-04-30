  # sync 
    -Post요청시 기상청_단기예보 ((구)_동네예보) 조회서비스 를 사용하여
    - 송산1동,2동의 X,Y 좌표값으로 날씨를 조회후 데이터를 db에 저장합니다
    - ex) http://localhost:8095/sync?baseDate=20240414
    
    조회날짜는 현재날짜를 포함한 before 3일이내여야하며 api에서 data 를 제공하지않는 날짜도 존재합니다.
    조회날짜의 기준으로 현재날짜 이후의 3일에 대한 날씨 데이터를 저장하는걸 확인했습니다.

  # inquire  
    - GET 요청시,db에서 fcstDate에 해당하는 날짜의 데이터 조회
    - ex) http://localhost:8095/inquire?fcstDate=20240414
    
    데이터가 없을 경우, Http status 204 오류를 응답합니다.
    

