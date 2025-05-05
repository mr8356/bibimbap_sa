# 📝 Spring Layered Architecture 실습: 메모장 API 서버 만들기

이 프로젝트는 Spring Boot 기반으로 **Layered Architecture**를 적용한 간단한 메모장 API 서버입니다.  
`Controller`, `Service`, `Repository` 계층으로 나누고, Spring의 **의존성 주입(DI)** 및 **스프링 빈 컨테이너(IoC)** 를 활용하여  
객체를 자동 생성하고 **싱글톤으로 관리**합니다.

/h2-console 로 DB 콘솔에 간편하게 접속합니다.

---

## 📁 프로젝트 구성

- **Controller**: HTTP 요청 처리
- **Service**: 비즈니스 로직
- **Repository**: 데이터 접근 계층 (`JpaRepository` 사용)
- **DTO**: `dto` 패키지 내 정의된 DTO 사용
- **Entity**: `entity` 패키지 내 정의된 Entity 활용

---

## ⚙️ Gradle 설정

Gradle을 사용하여 의존성 및 라이브러리를 관리합니다.  
아래의 `build.gradle` 설정을 기반으로 프로젝트를 구성하고, 동기화(Sync) 버튼을 눌러 라이브러리를 다운로드합니다.

```groovy
plugins {
    id 'java'
    id 'application'
    id 'org.springframework.boot' version '3.2.1'
    id 'io.spring.dependency-management' version '1.1.4'
}

group = 'org.sopt'
version = '1.0-SNAPSHOT'

repositories {
    mavenCentral() // Maven Central을 사용해 외부 의존성 다운로드
}

dependencies {
    implementation 'org.springframework.boot:spring-boot-starter-web'
    implementation 'org.springframework.boot:spring-boot-starter'
    implementation 'org.springframework.boot:spring-boot-starter-logging' // 기본 로깅 설정
    // Lombok
    compileOnly 'org.projectlombok:lombok'
    annotationProcessor 'org.projectlombok:lombok'

    // JPA & DataBase
    implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
    implementation 'org.hibernate:hibernate-core:6.2.8.Final'
    runtimeOnly 'com.h2database:h2'

    // JUnit 5 (Jupiter)
    testImplementation 'org.junit.jupiter:junit-jupiter:5.10.0'
    testImplementation 'com.h2database:h2'
}

tasks.test {
    useJUnitPlatform()
}
```

---

## ⚙️ application.yaml 설정

`resources/application.yaml` 파일을 통해 **톰캣 스레드, 데이터베이스, JPA** 관련 설정을 구성합니다.

```yaml
server:
  port: 8080
  tomcat:
    threads:
      max: 300           # 최대 스레드 수
      min-spare: 20      # 최소 예비 스레드 수
    accept-count: 100    # 큐에 대기 가능한 요청 수
    max-connections: 10000  # 최대 커넥션 수

spring:
  jpa:
    properties:
      hibernate:
        globally_quoted_identifiers: true
        ddl-auto: create
        show-sql: true
  datasource:
    driver-class-name: org.h2.Driver
    url: jdbc:h2:file:~/test;IFEXISTS=FALSE
    username: sa
    password: 
  # ✅ H2 콘솔 활성화
  h2:
    console:
      enabled: true
      path: /h2-console
```

---

## 🚀 메인 클래스

다음 코드를 통해 Spring Boot 애플리케이션을 시작할 수 있습니다.

```java
package memo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MemoApplication {
	public static void main(String[] args){
		SpringApplication.run(MemoApplication.class, args);
	}
}
```

---

# API 명세서
# 📑 메모장 + 유저 API 명세서

## ✅ 공통 응답 형식 (ApiResponse)

```json
{
  "status": 200,
  "message": "요청이 성공했습니다.",
  "data": { ... }
}
```

---

## 📘 Memo API

### 📌 [POST] /memos
**메모 생성**

- 요청 바디:

```json
{
  "title": "회의 메모",
  "content": "회의는 오후 2시에 시작됨"
}
```

- 응답:

```json
{
  "status": 201,
  "message": "정상적으로 생성되었습니다.",
  "data": {
    "id": 1,
    "title": "회의 메모",
    "content": "회의는 오후 2시에 시작됨"
  }
}
```

---

### 📌 [GET] /memos
**모든 메모 조회**

- 응답:

```json
{
  "status": 200,
  "message": "요청이 성공했습니다.",
  "data": [
    {
      "id": 1,
      "title": "회의 메모",
      "content": "회의는 오후 2시에 시작됨"
    }
  ]
}
```

---

### 📌 [GET] /memos/{id}
**단일 메모 조회**

- 응답:

```json
{
  "status": 200,
  "message": "요청이 성공했습니다.",
  "data": {
    "id": 1,
    "title": "회의 메모",
    "content": "회의는 오후 2시에 시작됨"
  }
}
```

---

### 📌 [DELETE] /memos/{id}
**메모 삭제**

- 응답:

```json
{
  "status": 200,
  "message": "요청이 성공했습니다.",
  "data": null
}
```

---

## 👤 User API

### 📌 [POST] /users
**유저 생성**

- 요청 바디:

```json
{
  "name": "홍길동",
  "email": "hong@example.com"
}
```

- 응답:

```json
{
  "status": 201,
  "message": "정상적으로 생성되었습니다.",
  "data": {
    "id": 1,
    "name": "홍길동",
    "email": "hong@example.com"
  }
}
```

---

### 📌 [GET] /users
**모든 유저 조회**

- 응답:

```json
{
  "status": 200,
  "message": "요청이 성공했습니다.",
  "data": [
    {
      "id": 1,
      "name": "홍길동",
      "email": "hong@example.com"
    }
  ]
}
```

---

### 📌 [GET] /users/{id}
**단일 유저 조회**

- 응답:

```json
{
  "status": 200,
  "message": "요청이 성공했습니다.",
  "data": {
    "id": 1,
    "name": "홍길동",
    "email": "hong@example.com"
  }
}
```

# 디자인 패턴 실습 프로젝트

이 프로젝트는 다양한 디자인 패턴을 Spring Boot 환경에서 구현한 예제입니다.

## 구현된 디자인 패턴

### 1. 컴포지트 패턴 (Composite Pattern)
- **목적**: 개별 객체와 복합 객체를 동일한 방식으로 처리
- **구현**: `ProductComponent` 인터페이스와 `Product`, `Cart` 클래스
- **장점**: 
  - 단일 객체와 복합 객체를 일관되게 처리
  - 새로운 컴포넌트 추가가 용이
  - 클라이언트 코드 단순화

### 2. Active Object 패턴
- **목적**: 비동기 메서드 호출과 실행을 분리
- **구현**: `MethodRequest`, `Scheduler`, `ActiveCart` 클래스
- **장점**:
  - 메서드 호출과 실행의 비동기 처리
  - 요청의 순서 보장
  - 스레드 안전성 확보

### 3. Half-Sync/Half-Async 패턴
- **목적**: 동기/비동기 작업의 효율적인 처리
- **구현**: `AsyncTask` 클래스와 `BlockingQueue` 사용
- **장점**:
  - 동기/비동기 작업의 명확한 분리
  - 작업 큐를 통한 효율적인 처리
  - 시스템 리소스의 효율적 사용

### 4. MVC 패턴 (Model-View-Controller)
- **목적**: 사용자 인터페이스와 비즈니스 로직의 분리
- **구현**: 
  - Model: `Todo` 클래스
  - View: Thymeleaf 템플릿 (`list.html`, `calendar.html`)
  - Controller: `TodoController` 클래스
- **장점**:
  - 관심사의 분리
  - 코드 재사용성 향상
  - 유지보수 용이성
  - 동일 모델에 대한 다양한 뷰 지원

## 패턴 간의 관계

1. **컴포지트 + Active Object**
   - 장바구니 시스템에서 상품 추가를 비동기적으로 처리
   - 컴포지트 패턴의 계층 구조를 유지하면서 비동기 처리 가능

2. **MVC + 컴포지트**
   - Todo 모델을 컴포지트 패턴으로 확장 가능
   - 다양한 뷰에서 동일한 모델 구조 활용

## 실행 방법

1. Spring Boot 애플리케이션 실행
2. 다음 URL로 접속:
   - 장바구니 시스템: `/cart`
   - Todo 목록: `/todos/list`
   - Todo 캘린더: `/todos/calendar`

## 기술 스택

- Java 17
- Spring Boot
- Thymeleaf
- Maven

## 패턴 선택 이유

1. **컴포지트 패턴**
   - 상품과 장바구니의 계층적 구조 표현
   - 새로운 상품 타입 추가 용이

2. **Active Object 패턴**
   - 장바구니 작업의 비동기 처리
   - 시스템 응답성 향상

3. **Half-Sync/Half-Async 패턴**
   - 동기/비동기 작업의 효율적 관리
   - 시스템 리소스 최적화

4. **MVC 패턴**
   - 사용자 인터페이스와 비즈니스 로직 분리
   - 다양한 뷰를 통한 데이터 표현
   - 코드 유지보수성 향상