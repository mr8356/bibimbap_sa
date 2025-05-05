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