# 디자인 패턴 실습 프로젝트

이 프로젝트는 다양한 디자인 패턴을 Spring Boot 환경에서 구현한 예제입니다.

## 구현된 디자인 패턴

### 1. 마이크로커널 아키텍처 (Microkernel Architecture)
- **목적**: 핵심 기능과 확장 기능의 분리
- **구현**: 
  - Core: `CoreSystem` 인터페이스와 `DefaultCoreSystem` 클래스
  - Plugin: `PluginManager`와 각종 플러그인 구현체
- **장점**: 
  - 핵심 기능과 확장 기능의 명확한 분리
  - 플러그인의 동적 로딩과 언로딩
  - 시스템의 유연한 확장
- **적용 전/후 비교**:
  - 적용 전: 모든 기능이 단일 모듈에 강하게 결합
  - 적용 후: 핵심 기능은 안정적으로 유지하면서 새로운 기능을 플러그인으로 추가 가능
- **구현 예시**:
  ```java
  // 핵심 시스템 인터페이스
  public interface CoreSystem {
      void initialize();
      void shutdown();
  }

  // 플러그인 매니저
  public class PluginManager {
      private List<Plugin> plugins = new ArrayList<>();
      
      public void loadPlugin(Plugin plugin) {
          plugins.add(plugin);
      }
      
      public void runAll() {
          plugins.forEach(Plugin::execute);
      }
  }

  // 플러그인 구현
  public class LoginPlugin implements Plugin {
      @Override
      public void execute() {
          // 로그인 관련 기능 구현
      }
  }
  ```

### 2. 전략 패턴 (Strategy Pattern)
- **목적**: 알고리즘을 캡슐화하고 교체 가능하게 만듦
- **구현**: 
  - `LoginStrategy` 인터페이스
  - `GoogleLoginStrategy`, `KakaoLoginStrategy` 구현체
  - `LoginStrategyFactory`를 통한 전략 생성
- **장점**:
  - 알고리즘의 독립적인 변경
  - 새로운 로그인 방식 추가 용이
  - 런타임에 전략 교체 가능
- **적용 전/후 비교**:
  - 적용 전: if-else로 로그인 방식 구분, 새로운 로그인 추가 시 코드 수정 필요
  - 적용 후: 새로운 로그인 방식 추가 시 새로운 전략 클래스만 구현
- **구현 예시**:
  ```java
  // 전략 인터페이스
  public interface LoginStrategy {
      void login();
  }

  // 구체적인 전략들
  public class GoogleLoginStrategy implements LoginStrategy {
      @Override
      public void login() {
          // Google 로그인 구현
      }
  }

  // 전략 팩토리
  public class LoginStrategyFactory {
      public LoginStrategy createStrategy(String type) {
          switch (type) {
              case "GOOGLE":
                  return new GoogleLoginStrategy();
              case "KAKAO":
                  return new KakaoLoginStrategy();
              default:
                  throw new IllegalArgumentException("Unknown login type");
          }
      }
  }
  ```

### 3. 팩토리 패턴 (Factory Pattern)
- **목적**: 객체 생성 로직을 캡슐화하여 유연성과 확장성 제공
- **구현**:
  - `LoginStrategyFactory` 클래스
  - 전략 객체 생성 로직 캡슐화
- **장점**:
  - 객체 생성 로직의 중앙화
  - 클라이언트 코드와 구체적인 구현 분리
  - 새로운 전략 추가 시 팩토리만 수정
- **적용 전/후 비교**:
  - 적용 전: 클라이언트에서 직접 전략 객체 생성, 생성 로직 중복
  - 적용 후: 팩토리를 통한 일관된 객체 생성, 생성 로직 중앙화
- **구현 예시**:
  ```java
  public class LoginStrategyFactory {
      private static final Map<String, LoginStrategy> strategies = new HashMap<>();
      
      static {
          strategies.put("GOOGLE", new GoogleLoginStrategy());
          strategies.put("KAKAO", new KakaoLoginStrategy());
      }
      
      public static LoginStrategy getStrategy(String type) {
          LoginStrategy strategy = strategies.get(type);
          if (strategy == null) {
              throw new IllegalArgumentException("Unknown login type: " + type);
          }
          return strategy;
      }
  }
  ```

### 4. 컴포지트 패턴 (Composite Pattern)
- **목적**: 개별 객체와 복합 객체를 동일한 방식으로 처리
- **구현**: `ProductComponent` 인터페이스와 `Product`, `Cart` 클래스
- **장점**: 
  - 단일 객체와 복합 객체를 일관되게 처리
  - 새로운 컴포넌트 추가가 용이
  - 클라이언트 코드 단순화
- **적용 전/후 비교**:
  - 적용 전: 상품과 장바구니를 별도로 처리, 중복 코드 발생
  - 적용 후: 동일한 인터페이스로 일관된 처리 가능
- **구현 예시**:
  ```java
  // 컴포넌트 인터페이스
  public interface ProductComponent {
      double getPrice();
      String getName();
  }

  // 개별 상품
  public class Product implements ProductComponent {
      private String name;
      private double price;
      
      @Override
      public double getPrice() {
          return price;
      }
  }

  // 복합 객체 (장바구니)
  public class Cart implements ProductComponent {
      private List<ProductComponent> items = new ArrayList<>();
      
      @Override
      public double getPrice() {
          return items.stream()
              .mapToDouble(ProductComponent::getPrice)
              .sum();
      }
  }
  ```

### 5. Active Object 패턴
- **목적**: 비동기 메서드 호출과 실행을 분리
- **구현**: `MethodRequest`, `Scheduler`, `ActiveCart` 클래스
- **장점**:
  - 메서드 호출과 실행의 비동기 처리
  - 요청의 순서 보장
  - 스레드 안전성 확보
- **적용 전/후 비교**:
  - 적용 전: 동기 처리로 인한 블로킹
  - 적용 후: 비동기 처리로 시스템 응답성 향상
- **구현 예시**:
  ```java
  // 메서드 요청 인터페이스
  public interface MethodRequest {
      void execute();
  }

  // 스케줄러
  public class Scheduler implements Runnable {
      private BlockingQueue<MethodRequest> queue;
      
      @Override
      public void run() {
          while (running) {
              MethodRequest request = queue.take();
              request.execute();
          }
      }
  }
  ```

### 6. Half-Sync/Half-Async 패턴
- **목적**: 동기/비동기 작업의 효율적인 처리
- **구현**: `AsyncTask` 클래스와 `BlockingQueue` 사용
- **장점**:
  - 동기/비동기 작업의 명확한 분리
  - 작업 큐를 통한 효율적인 처리
  - 시스템 리소스의 효율적 사용
- **적용 전/후 비교**:
  - 적용 전: 모든 작업이 동기적으로 처리되어 성능 저하
  - 적용 후: 작업의 특성에 따라 동기/비동기 처리 분리
- **구현 예시**:
  ```java
  public class AsyncTask implements Runnable {
      private BlockingQueue<ProductComponent> taskQueue;
      
      @Override
      public void run() {
          while (running) {
              ProductComponent product = taskQueue.take();
              // 비동기적으로 상품 처리
          }
      }
  }
  ```

### 7. MVC 패턴 (Model-View-Controller)
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
- **적용 전/후 비교**:
  - 적용 전: UI와 비즈니스 로직이 혼재, 뷰 변경 시 전체 코드 수정 필요
  - 적용 후: 뷰 변경 시 해당 템플릿만 수정
- **구현 예시**:
  ```java
  // Model
  public class Todo {
      private String title;
      private LocalDate dueDate;
  }

  // Controller
  @Controller
  public class TodoController {
      @GetMapping("/list")
      public String listView(Model model) {
          model.addAttribute("todos", todoService.getAllTodos());
          return "todo/list";  // 목록 뷰
      }

      @GetMapping("/calendar")
      public String calendarView(Model model) {
          model.addAttribute("todos", todoService.getAllTodos());
          return "todo/calendar";  // 캘린더 뷰
      }
  }
  ```

  