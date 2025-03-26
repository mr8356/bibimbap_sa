# 과제 안내: Dispatcher 아키텍처 기반 HTTP 서버 구현

## 🔍 과제 개요

이번 과제의 핵심은 **기존 소켓 프로그래밍 기반의 if 조건 분기 방식**을 벗어나, **Dispatcher 아키텍처 패턴**을 적용한 HTTP 서버 구조로 리팩토링하는 것입니다.

기존에는 `Socket`과 `InputStream`을 통해 직접 요청을 파싱하고, `if`나 `switch` 조건문으로 URL에 따라 응답을 처리했습니다. 예:

```java
if (path.equals("/hello")) {
    // hello 처리
} else if (path.equals("/bye")) {
    // bye 처리
}
```

이 방식은 간단하지만 확장성과 유지보수성이 떨어집니다.

---

## 🧱 리팩토링 목표: Dispatcher 아키텍처 패턴 적용

이번 숙제에서는 `Dispatcher` 객체가 요청 URL에 따라 알맞은 `Handler` 객체에게 처리를 위임하도록 구조를 변경해야 합니다. 이때 `Handler`는 각각의 경로(URL)를 담당하는 객체입니다. **Spring의 Controller 역할과 유사**합니다.

### 🔄 구조 개요

```text
[클라이언트 요청] → [Dispatcher] → [Handler]
```

- `Dispatcher`: 요청 URI를 보고 적절한 Handler를 선택하여 위임
- `Handler`: 각 URI별 처리 로직을 가지고 있는 클래스 (예: HelloHandler, ByeHandler)

### 🔧 예시

```java
public class Dispatcher {
    private Map<String, Handler> handlerMapping = new HashMap<>();

    public Dispatcher() {
        handlerMapping.put("/hello", new HelloHandler());
        handlerMapping.put("/bye", new ByeHandler());
        ... 등등
    }

    public void dispatch(String path, OutputStream out) {
        Handler handler = handlerMapping.get(path);
        if (handler != null) {
            handler.handle(out);
        } else {
            new NotFoundHandler().handle(out);
        }
    }
}
```

---

## 🧪 주의사항

- 반드시 **Spring 없이** 구현합니다. (Spring Boot, @RestController 등 금지)
- 수업 맨 마지막에는 Spring으로 전환하는 예시를 보여줬지만, **이번 과제에서는 하지 않습니다.**
- 다음 시간에 Spring으로 다시 다뤄볼 예정이니, 이번에는 Dispatcher 패턴에 집중해주세요.

---

## ✅ 기대 결과

- `Dispatcher`가 요청 경로에 따라 `Handler`로 위임
- `Handler`는 응답을 책임지고 작성
- 코드 확장성 개선 (새로운 URI 추가 시 `Handler`만 추가하면 됨)

---

## 💡 참고

- Dispatcher는 라우터처럼 생각하면 됩니다.
- Handler는 Spring의 `@Controller`와 비슷한 역할입니다.
- 응답 포맷은 간단한 HTTP Response 형식을 유지하세요 (`200 OK`, `404 Not Found` 등)
