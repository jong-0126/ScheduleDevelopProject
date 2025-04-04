# 일정 관리 앱 프로젝트 Develop

Spring Boot를 이용하여 개발한 일정관리 프로젝트를 JPA를 활용하여 디벨롭 하였다.

## 📅 개발 기간

- **2025-03-26 ~ 2025-04-04**

## 💻 개발 환경

- **Backend**: Java 17, Spring Boot
- **ORM**: JPA (Hibernate)
- **Database**: MySQL
- **Build Tool**: Gradle
- **IDE**: IntelliJ IDEA
- **Version Control**: Git & GitHub

> 💡 **DB는 JPA를 활용한 객체지향적 방식으로 CRUD 및 연관관계 매핑을 구현했습니다.**

---

## 🛠️ 개발 과정

### API 명세서
https://documenter.getpostman.com/view/25125875/2sB2cU9Mry

### ERD
![image](https://github.com/user-attachments/assets/fc47c33b-c3eb-4ef0-b147-7f12e064ec98)


### 프로젝트 요구사항

### **Lv 1. 일정 CRUD**

✅ **일정 관리 기능 구현**

- 일정 생성, 조회, 수정, 삭제
- 필드: `작성 유저명`, `할일 제목`, `할일 내용`, `작성일`, `수정일`
- `작성일`, `수정일`은 `JPA Auditing` 적용

---

### **Lv 2. 유저 CRUD**

✅ **유저 관리 기능 구현**

- 유저 생성, 조회, 수정, 삭제
- 필드: `유저명`, `이메일`, `작성일`, `수정일`
- `작성일`, `수정일`은 `JPA Auditing` 적용

✅ **연관관계 적용**

- 일정은 `작성 유저명` 필드 대신 `유저 고유 식별자` 필드를 가짐

---

### **Lv 3. 회원가입**

✅ **회원가입 기능 구현**

- `비밀번호` 필드 추가

---

### **Lv 4. 로그인(인증)**

✅ **로그인 기능 구현**

- `Cookie/Session`을 활용한 로그인
- `이메일`, `비밀번호`를 활용한 인증

✅ **예외처리**

- 이메일 또는 비밀번호 불일치 시 `HTTP 401` 반환
- 예외 응답 예시:
  ```json
  {
    "code": "C001",
    "message": "아이디 입력은 필수입니다",
    "status": "BAD_REQUEST"
  }
  ```

---

### **Lv 5. 다양한 예외처리 적용**

✅ **Validation을 활용한 예외처리**

- `할일 제목`은 최대 10자
- `유저명`은 최대 4자
- `@Pattern`을 사용하여 이메일 검증

---

### **Lv 6. 비밀번호 암호화**

✅ **비밀번호 암호화 적용**

- 회원가입 시 비밀번호를 암호화하여 저장
