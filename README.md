# 📝 Spring Layered Architecture 실습: 메모장 API 서버 만들기

이 프로젝트는 Spring Boot 기반으로 **Layered Architecture**를 적용한 간단한 메모장 API 서버입니다.  
`Controller`, `Service`, `Repository` 계층으로 나누고, Spring의 **의존성 주입(DI)** 및 **스프링 빈 컨테이너(IoC)** 를 활용하여  
객체를 자동 생성하고 **싱글톤으로 관리**합니다.

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