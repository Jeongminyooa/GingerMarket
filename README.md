# 🏡 생강마켓
- 동덕여자대학교 소프트웨어 시스템 개발 솜생솜사 팀의 생강마켓
<div align="center">
  <img width="600px" alt="image" src="https://user-images.githubusercontent.com/78305431/212347403-a4332408-35e4-4d14-b665-5f6cd046f3da.png">
</div>

> 생필품 나눔 / 공동구매 / 교체주기 알림 서비스

# 프로젝트 개요
생필품 구매의 부담을 느끼는 자취생이 증가하였다. 이를 해결하기 위해 생필품 나눔이 가능하고, 대량의 생필품을 공동 구매할 수 있는 서비스를 고안했다. 더하여 생필품 교체 주기를 알려주어 효용성을 높였다. 합리적 소비와 지나친 생필품 소비를 줄여 환경 보호 효과를 기대한다. 

### 👥 회원관리
<div align="center">
  <img width="300px" alt="image" src="https://user-images.githubusercontent.com/78305431/212347663-26e3d99a-c9c1-41eb-bae6-dc7413e45840.png">
  <img width="300px" alt="image" src="https://user-images.githubusercontent.com/78305431/212347791-743540d5-e9cd-43df-bdfe-766826aa14f0.png">
</div>

- 세션을 활용한 회원 관리 기능 제공

### ➗ 나눔 기능
<div align="center">
  <img width="400px" alt="image" src="https://user-images.githubusercontent.com/78305431/212349500-9ecabdc3-0cb4-45ee-886f-0d9e65388b61.png">
  <img width="400px" alt="image" src="https://user-images.githubusercontent.com/78305431/212349624-7706f709-ccc4-44d8-902f-8d66e84f841b.png">
  <img width="400px" alt="image" src="https://user-images.githubusercontent.com/78305431/212349760-d87d71c3-2a74-4500-a21a-fe4bb855d8f5.png">
  <img width="400px" alt="image" src="https://user-images.githubusercontent.com/78305431/212349973-a90f7cf0-4b11-4b14-9212-a7a13256e631.png">
</div>

- 생필품 나눔을 위한 게시글 업로드/수정/조회/삭제/검색/이미지 업로드 기능 제공

<div align="center">
  <img width="400px" alt="image" src="https://user-images.githubusercontent.com/78305431/212350106-14555e22-69f1-4d68-bdbc-344f40bf13f1.png">
  <img width="400px" alt="image" src="https://user-images.githubusercontent.com/78305431/212350198-298f0343-f0bb-4eb3-9ffd-e4f3e9d02859.png">
</div>

- 비동기 통신을 활용한 실시간 쪽지 보내기 기능 제공

### ➕ 공동구매 기능
<div align="center">
  <img width="300px" alt="image" src="https://user-images.githubusercontent.com/78305431/212350354-76c88beb-ab16-4d74-8067-396fef2ea9e9.png">
  <img width="300px" alt="image" src="https://user-images.githubusercontent.com/78305431/212350639-4747a7fe-ee96-4f46-b79a-ed7cf1606cbc.png">
  <img width="300px" alt="image" src="https://user-images.githubusercontent.com/78305431/212350783-d0b30357-52d5-484b-ac43-d066e89f5634.png">
  <img width="300px" alt="image" src="https://user-images.githubusercontent.com/78305431/212350878-fa726846-fc28-4c67-9d51-91999c66e0f7.png">
  <img width="300px" alt="image" src="https://user-images.githubusercontent.com/78305431/212351028-ab9e0416-a47f-4ade-91ba-cc18e46db02d.png">
  <img width="300px" alt="image" src="https://user-images.githubusercontent.com/78305431/212351763-fb537e81-785d-48b6-ae11-68f699a830b9.png">
  <img width="300px" alt="image" src="https://user-images.githubusercontent.com/78305431/212351830-7c690136-0a18-489b-bb3c-da9c190bf16f.png">
</div>

- 공동구매를 위한 게시글 업로드/수정/조회/삭제/검색/이미지 업로드 기능 제공

<div align="center">
  <img width="300px" alt="image" src="https://user-images.githubusercontent.com/78305431/212351150-243c5a05-5627-44f6-a8ba-afcf1ff9512e.png">
  <img width="300px" alt="image" src="https://user-images.githubusercontent.com/78305431/212351251-0a91955f-abda-495c-a433-0f6aee9d87de.png">
  <img width="300px" alt="image" src="https://user-images.githubusercontent.com/78305431/212351649-051600b1-3ea9-4608-82f5-2d1711a2aaf3.png">
</div>

- 비동기 통신을 활용한 공동구매 댓글 업로드/수정/삭제/조회 기능 제공

### 🔔 교체주기 알림 기능
<div align="center">
  <img width="400px" alt="image" src="https://user-images.githubusercontent.com/78305431/212348163-8fb0e59e-5428-44b9-bd22-3a052a17b2ba.png">
  <img width="400px" alt="image" src="https://user-images.githubusercontent.com/78305431/212348523-8e3bb476-5b01-41ca-9f70-30a38c1e60e9.png">
  <img width="400px" alt="image" src="https://user-images.githubusercontent.com/78305431/212348644-a2098ef2-cf60-494c-b1d4-ac58ec933895.png">
  <img width="400px" alt="image" src="https://user-images.githubusercontent.com/78305431/212348763-0a02ccec-a75a-4910-b136-d06e66534d7c.png">
</div>

- 마이페이지 내에서 생필품 교체주기 알림을 등록/조회/삭제 기능 제공
- Quartz Scheduler를 활용하여 교체주기가 다가오면 알림 기능 제공
- 비동기 통신을 활용한 교체주기 확인이 가능한 캘린더 기능 제공

### 💁🏻 마이페이지
<div align="center">
  <img width="400px" alt="image" src="https://user-images.githubusercontent.com/78305431/212348013-1ab053f6-bb35-403b-ae4b-6d795be1b309.png">
  <img width="400px" alt="image" src="https://user-images.githubusercontent.com/78305431/212348246-eaa0d6c2-afa6-48af-b1aa-c3ae2c36a207.png">
  <img width="400px" alt="image" src="https://user-images.githubusercontent.com/78305431/212348355-8cb7e9f5-8b70-4f65-999c-13cab567ae0b.png">
  <img width="400px" alt="image" src="https://user-images.githubusercontent.com/78305431/212348860-70ad0977-4aa7-4c6e-b935-75aff0c87e1a.png">
</div>

- 회원 정보 조회/수정 기능 제공
- 자신이 작성한 게시글 확인 기능 제공

### 🚨 주의사항

**시작 화면** 
- localhost:8080/user/login

**테스트용 ID/PW**

- 게시물 작성자 <br>
ID : dbwjd <br>
PW : 12345

<br>

- 채팅 확인용 <br>
ID : wjdals <br>
PW : 12345


작성자만 게시물 수정/삭제/진행 상황 변경 또는 신청 폼 조회가 가능합니다. 

작성자는 자신의 게시글에 쪽지를 보낼 수 없습니다. 

쪽지 보내기 기능은 다른 브라우저(다른 세션)으로 테스트 해보시는 것을 권장드립니다. 

게시글 삭제 또는 회원 탈퇴가 진행된다면 나눔포스트/공동구매 포스트/댓글/쪽지/신청폼이 모두 삭제됩니다.

이미지와 함께 업로드한 게시물은 이미지가 보이지 않을 수도 있습니다.

회원가입 시 카테고리는 3개를 필수로 선택해야하며, 회원 정보 수정에서는 3개 안에서 자유롭게 선택이 가능합니다. 

Chrome 브라우저에 최적화하여 개발을 진행했습니다.

---

# 시스템 구성도
<img width="1030" alt="image" src="https://user-images.githubusercontent.com/78305431/212343669-e9ab4bea-fb9d-42ee-ab89-d6e68cc12d1e.png">

# 유즈케이스 모델
<img width="546" alt="image" src="https://user-images.githubusercontent.com/78305431/212343712-a75092e2-93dc-4ba2-bb4f-8dcb62b37414.png">

# DB 설계
<img width="993" alt="image" src="https://user-images.githubusercontent.com/78305431/212343774-3b1120d5-ad46-46c7-9efa-0c8ad56b6fb8.png">

--- 

# 팀원

| 팀 | 이름 | 역할 |
| --- | --- | --- |
| 개발팀 | 신유정 | 공동 구매 게시글 관련 API 및 뷰 구현 |
| 개발팀 | 유정민 | 이미지 업로드, 교체주기/마이페이지/회원 정보 수정/공동구매 댓글/페이지네이션 API 및 뷰 구현, 코드 리뷰 |
| 개발팀 | 이은서 | 나눔 게시글/쪽지 API 및 뷰 구현, 코드 리뷰 |
| 개발팀 | 정승혜 | 로그인, 회원가입, 회원탈퇴, 로그아웃 API 및 뷰 구현 |

