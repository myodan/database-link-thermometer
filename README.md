코로나 사태로 인해 학교에서 선생님들이 온도계로 학생들의 온도를 재고 수기로 기록을 하는 것을 보고 온도를 재면 자동으로 데이터베이스에
올라가게 하여 관리하기 편하게 만들어보고 싶었습니다.

그래서 학교 창업 동아리에서 친구와 프로젝트를 시작했습니다.
친구는 아두이노를 이용하여 온도계를 제작하였고 저는 어느 플랫폼에서도 편하게 관리할 수 있게 웹사이트를 제작하였습니다.

## 웹사이트 (담당: 홍종호)

웹사이트는 Spring Boot를 이용하여 제작하였습니다.
제가 잘하는 Java를 이용하여 만들 수 있어 선택하게 되었습니다.

웹 디자인은 Bootstrap을 이용하여 어느 플랫폼에서 접근하여도 깔끔한 웹 레이아웃을 볼 수 있게 만들었습니다.

데이터베이스는 MariaDB, JPA를 이용하여 구현하였습니다.

### 웹사이트 - 메인

![웹사이트-메인](https://user-images.githubusercontent.com/41239679/103797208-865f4980-508b-11eb-9d8f-c7248f5a4710.png)

**<span style="color: #ff4c4c">①</span> 필터:** 필터는 학생 데이터베이스에서 최대 학년, 반을 검색하여 Thymeleaf에서 자동으로 필터를 생성하게 구현하였습니다. (예로 반이 3반까지있다면 자동으로 3반까지만 필터가 생성됩니다) 필터는 학년, 반에서 한 개씩 선택이 가능합니다. <br/>

**<span style="color: #ffba4c">②</span> 학생 목록:** 학생 데이터베이스에서 필터에 맞는 학생들을 20명씩 불러와 나열해줍니다. 만약 최근 측정한 날짜가 같으면 이름 옆에 최근 측정 온도가 표시됩니다.<br/>

**<span style="color: #aad73f">③</span> 페이징:** JPA에 있는 Paging 클래스를 이용하여 Thymeleaf에서 자동으로 페이지가 나뉘도록 구현하였습니다. <br/>

### 웹사이트 - 상세기록

학생 목록에서 i 버튼을 누르면 이동되는 상세기록 페이지입니다.

![웹사이트-메인-1](https://user-images.githubusercontent.com/41239679/103797407-c7575e00-508b-11eb-8884-59bb7636cecd.png)

**<span style="color: #ff4c4c">①</span> 학생 정보:** 학생의 상세한 정보를 확인할 수 있습니다. <br/>

**<span style="color: #ffba4c">②</span> 측정 기록:** 학생의 측정기록을 확인할 수 있습니다. 최근 20개까지 표시됩니다. <br/>

**<span style="color: #aad73f">③</span> 돌아가기:** 조금 전 화면으로 돌아갑니다. 돌아갈 때는 필터, 페이지가 그대로 저장되어있는 상태로 돌아갑니다. <br/>

## REST API

REST API를 구현하여 좀 더 편리하게 사용할 수 있게 하였습니다.
반환 데이터는 JSON으로 반환됩니다.

### POST 요청

**/api/temperature:** Body 값을 데이터베이스에 입력합니다. (Body는 JSON형태의 데이터) <br/>

### GET 요청

**/api/students:** 모든 학생 정보를 반환합니다. <br/>
**/api/students/{id}:** 해당 학번의 학생 정보를 반환합니다. <br/>
**/api/temperature:** 모든 측정 기록을 반환합니다. <br/>
**/api/temperature/{id}:** 해당 ID의 측정기록을 반환합니다. <br/>
**/api/temperature/student/{id}:** 해당 학번을 가진 학생의 측정기록을 반환합니다. <br/>

### DELETE 요청

**/api/temperature/{id}:** 해당 ID의 측정 기록을 삭제합니다.<br/>

## 온도계 (담당: 장민성)

![온도계](https://user-images.githubusercontent.com/41239679/103776420-c403a880-5072-11eb-9c88-d8cbb1ddd81e.jpg)

위 사진에서와같이 온도계를 만들었습니다. 기본적인 바디는 3D 프린터를 이용해 출력하였습니다.
OLED 디스플레이를 달아서 좀 더 시인성을 높였습니다.
처음에 학년, 반, 학번을 입력하고 온도를 재고 Send 버튼을 누르면 데이터베이스에 무선 주파수를 이용하여
데이터를 전송합니다. Retry 버튼을 이용하여 다시 온도를 잴 수 있습니다.

Send를 하고 난 뒤에는 자동으로 같은 학년, 반에 전 번호의 다음 번호가 자동으로 입력됩니다.