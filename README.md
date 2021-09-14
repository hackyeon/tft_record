## TFT 전적검색 앱

### 개발기간
2021-09-13 ~<br>
2021-09-13: 기획 초안 완성, 라이브러리 추가, 인터넷 권한

### 개발인원
총 인원: 2명<br>
기획/개발: 김학연<br>
디자인: 윤재원

### 개발환경
사용도구: Android Studio4.2.0, PostMan<br>
사용언어: Kotlin1.5.20<br>
버전관리: Git Hub<br>
min api level: 23

#### 그날그날 한거 일기장
2021-09-14<br>
소환사 검색기능 개발<br>
키보드 검색 시 다운<br>
검색 할 경우 키보드다운<br>
// eidtText의 inputType을 text로 바꿔줘야 imeOption이 바뀐다 이거거때메 시간이 좀 걸림ㅠ<br>
Retrofit baseUrl 설정 및 GET summoner 콜백 생성<br>
// base url이 소환사는 kr 전적은 asia 이렇게 바뀌는데 이거 알아보고 베이스 url 변경하는거 찾아보기<br>
okhttp 헤더 추가 및 인터셉터 완료<br>
현재 통신 완료 시 DataObject변수에 값을 넣어주고 MatchActivity가 그값을 사용하여 view를 그림<br>
DataObject에 넣어서 사용할지 intent로 데이터를 쏘는게 좋을지 고민중<br>
