# KUIT 9주차 미션 - 동아리 관리 시스템 구축

## 📋 프로젝트 개요

이번 9주차 미션은 SpringBoot 를 사용하여 동아리 관리 시스템을 구축하는 미션입니다.

## 📊 데이터베이스 스키마

### 테이블 구조
1. **Students** (학생 정보)
   - `student_id`: 학생 ID (Primary Key, Auto Increment)
   - `student_number`: 학번 (Unique)
   - `name`: 학생 이름

2. **Clubs** (동아리 정보)
   - `club_id`: 동아리 ID (Primary Key, Auto Increment)
   - `name`: 동아리 이름 (Unique)
   - `description`: 동아리 설명

3. **Club_Members** (동아리 가입 정보)
   - `club_member_id`: 가입 ID (Primary Key, Auto Increment)
   - `join_date`: 가입 날짜
   - `student_id`: 학생 ID (Foreign Key)
   - `club_id`: 동아리 ID (Foreign Key)
   - `UNIQUE(student_id, club_id)`: 한 학생이 같은 동아리에 중복 가입 불가

### 샘플 데이터

- 학생 1명 (학번: 2011111, 이름: 멤버1)
- 동아리 30개 (동아리 1 ~ 동아리 30)
- 가입 샘플: 멤버1이 동아리 3, 7, 10에 가입

## 🔧 프로젝트 구조

```
src/main/java/com/example/kuit_9week_mission/
├── domain/
│   ├── auth/                    # 인증 관련
│   │   ├── controller/
│   │   ├── dto/
│   │   └── service/
│   ├── club/                    # 동아리 관련
│   │   ├── controller/
│   │   ├── model/
│   │   ├── repository/
│   │   └── service/
│   └── student/                 # 학생 관련
│       ├── controller/
│       ├── dto/
│       ├── model/
│       ├── repository/
│       └── service/
└── global/                      # 전역 설정
    ├── common/
    │   ├── auth/               # 인증 관련 유틸
    │   ├── config/             # 설정
    │   ├── exception/          # 예외 처리
    │   ├── exception_handler/  # 전역 예외 핸들러
    │   └── response/           # 공통 응답 형식
    └── jwt/                    # JWT 유틸리티
```

## ✅ 완성된 기능

1. **인증 시스템**
   - 로그인 API (`POST /api/auth/login`)
   - JWT 토큰 기반 인증
   - `@StudentId` 어노테이션을 통한 자동 studentId 추출

2. **학생 정보 조회**
   - 내 정보 조회 API (`GET /api/students/me`)

3. **공통 응답 형식**
   - `ApiResponse<T>` 클래스를 통한 일관된 응답 구조
   - 성공/실패 응답 처리

4. **예외 처리**
   - 전역 예외 핸들러 구현
   - 커스텀 예외 클래스

## 📝 미션 목표 (TODO)

다음 기능들을 직접 구현하셔야 합니다.

### ☑️ TODO 1: 전체 동아리 목록 조회 (무한 스크롤)
- **인증**: 불필요
- **기능**: 페이지당 5개씩 동아리 목록 조회 (무한 스크롤)
- **응답 형식**
  ```json
  {
    "isSuccess": true,
    "statusCode": 20000,
    "message": "요청에 성공했습니다",
    "data": {
        "data": [
            {
                "clubId": 4,
                "name": "동아리 4",
                "description": "동아리 4 설명"
            }
        ],
        "lastId": 100,
        "hasNext": true
    },
    "timestamp": "2025-10-24T00:37:07.469931"
  }
  ```

### ☑️ TODO 2: 동아리 정보 수정
- **인증**: 불필요
- **기능**: 동아리 이름과 설명 수정

### ☑️ TODO 3: 동아리 삭제
- **인증**: 불필요
- **기능**: 동아리 삭제

### ☑️ TODO 4: 학생 이름 수정
- **인증**: 필요 (JWT 토큰)
- **기능**: 현재 로그인한 학생의 이름 수정

### ☑️ TODO 5: 동아리 가입
- **인증**: 필요 (JWT 토큰)
- **기능**: 현재 로그인한 학생이 동아리에 가입

### ☑️ TODO 6: 내가 가입한 동아리 목록 조회
- **인증**: 필요 (JWT 토큰)
- **기능**: 현재 로그인한 학생이 가입한 동아리 목록 조회
- **응답 형식**
  ```json
  {
    "isSuccess": true,
    "statusCode": 20000,
    "message": "요청에 성공했습니다",
    "data": {
        "studentName": "멤버1",
        "clubNames": ["동아리 3", "동아리 7", "동아리 10"]
    },
    "timestamp": "2025-10-24T00:37:07.469931"
  }
  ```

## 유의 사항 💁

1. **JWT 토큰 사용**: `@StudentId` 어노테이션을 사용하면 자동으로 토큰에서 studentId를 추출합니다. 학생 본인 정보 조회 예시 코드를 참고하시길 바랍니다.

2. **데이터베이스 접근**: `JdbcTemplate`을 사용하여 SQL 쿼리를 작성합니다.

3. **JOIN 쿼리**: TODO 6에서는 `Club_Members`와 `Clubs` 테이블을 **JOIN** 해야 합니다.

4. **무한 스크롤**: TODO 1에서는 `lastId`를 기준으로 다음 페이지를 조회하는 방식으로 구현해야합니다.

5. **예외 처리**: 이미 구현된 `CustomException`과 `GlobalExceptionHandler`를 활용하세요.


## 📚 학습 목표

이 미션을 통해 다음을 학습하고자합니다.

- Spring Boot REST API 개발
- JWT 기반 인증 시스템 구현
- JDBC 를 사용한 데이터베이스 연동
- 무한 스크롤 구현
- SQL JOIN 쿼리 작성
- Spring Boot Validation 활용
- 전역 예외 처리

## 🎯 평가 기준

- 각 TODO 의 기능이 정상적으로 동작하는지
- 적절한 HTTP 상태 코드 사용
- 일관된 응답 형식 (`ApiResponse` 사용)
- 적절한 예외 처리
- 코드의 가독성과 구조

