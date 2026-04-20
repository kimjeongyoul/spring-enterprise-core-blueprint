# Java/Spring Development & AI-Native Conventions

> **본 프로젝트의 일관된 코드 품질과 엔터프라이즈 확장을 위한 시니어 가이드라인**

## 🧱 1. Package by Domain Architecture
본 프로젝트는 레이어 중심이 아닌 **비즈니스 도메인 중심**으로 패키지를 구성합니다. 각 도메인은 독립된 모듈처럼 동작하며, 자기 자신을 완성하기 위한 4개의 계층을 소유합니다.

### Layering Rules (The 4-Layer Standard)
각 도메인 패키지(`com.vibe.modules.{domain}`) 내부는 반드시 다음 4개 레이어로 구분합니다.

1.  **API Layer (`api`):** 외부 노출 인터페이스.
    - `dto`: 요청(`Request`) 및 응답(`Response`) 객체.
    - `Controller`: HTTP 엔드포인트 정의 및 DTO-Command 변환.
2.  **Application Layer (`application`):** 비즈니스 흐름 제어(Orchestration).
    - `dto`: 서비스 간 데이터 전달용 `Command`, `Criteria` 객체.
    - `Service`: 트랜잭션 관리 및 도메인 로직 호출. API 레이어에 종속되지 않아야 함.
3.  **Domain Layer (`domain`):** 핵심 비즈니스 로직 및 객체.
    - `Entity`: JPA 엔티티 및 풍부한 도메인 모델(Rich Domain Model).
    - `Exception`: 도메인 전용 예외.
4.  **Infrastructure Layer (`infrastructure`):** 기술적 세부 구현.
    - `Repository`: 데이터베이스 접근 (Spring Data JPA, QueryDSL).
    - `External Client`: 외부 API(결제, 알림 등) 연동 구현체.

## 🤖 2. Senior-Level Coding Standards
*   **DTO Isolation:** 컨트롤러는 절대로 엔티티(Entity)를 직접 반환하거나 받지 않습니다. 반드시 레이어별 전역 DTO를 사용합니다.
*   **Immutability:** 모든 DTO는 `final` 필드 또는 `Lombok`의 `@Value`, `@Builder`를 사용하여 불변성을 유지합니다.
*   **Explicit Transaction:** 서비스 레이어에서는 `@Transactional(readOnly = true)`를 기본으로 사용하고, 쓰기 작업에만 별도로 `@Transactional`을 선언하여 성능을 최적화합니다.
*   **Toss Payments Style API:** 성공 시 리소스 자체를 반환하고, 실패 시 `code`와 `message`를 포함한 표준 에러 객체를 반환하여 클라이언트 가독성을 높입니다.

## 📡 3. Global Infrastructure (`com.vibe.core`)
모든 도메인에서 공통으로 사용하는 '엔진' 성격의 코드는 `core` 패키지에서 관리합니다.
*   `auth`: JWT 발급 및 시큐리티 필터.
*   `config`: 글로벌 설정 (Security, QueryDSL, Swagger).
*   `storage`: 파일 저장소 추상화 레이어.
*   `exception`: 전역 예외 처리기 및 표준 에러 肄붾뱶.
