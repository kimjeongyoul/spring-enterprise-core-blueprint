# Java/Spring Development & AI-Native Conventions

> **본 프로젝트의 일관된 코드 품질과 고속 개발을 위한 백엔드 시니어 가이드라인**

## 🧱 1. Architectural Layering
AI와 협업 시 각 파일의 역할을 명확히 분리하여 책임 소재를 고립시킵니다.

1.  **API Layer (`com.vibe.api`):** 외부 노출 인터페이스. HTTP 상태 코드 및 엔드포인트 정의에만 집중합니다.
2.  **Application Layer (`com.vibe.application`):** 비즈니스 흐름 제어. 트랜잭션 관리와 유스케이스 구현을 담당합니다.
3.  **Domain Layer (`com.vibe.domain`):** 핵심 비즈니스 로직 및 객체. 순수 자바 객체를 지향합니다.
4.  **Infrastructure Layer (`com.vibe.infrastructure`):** 기술적 세부 구현. DB 접근, 외부 API 연동 등을 처리합니다.

## 🤖 2. AI-Native (Vibe Coding) Rules
AI 에이전트(Cursor, Gemini 등)와 협업 시 코드 무결성을 위해 다음 원칙을 반드시 준수합니다.

*   **Build-First Verification:** 모든 코드 생성 및 수정 요청 후에는 반드시 `./gradlew compileJava`를 실행하여 컴파일 에러가 없는지 AI에게 확인시켜야 합니다. "된다"는 말보다 "빌드 성공" 로그가 우선입니다.
*   **Dependency Integrity:** 새로운 클래스나 인터페이스를 참조할 때, 해당 파일이 실제로 존재하는지 패키지 경로를 전수 검사하도록 지시하십시오.
*   **Atomic Refactoring:** 코드를 수정할 때 관련 클래스들을 묶어서 한 번에 업데이트하여 의존성 불일치를 방지하십시오.
*   **Small & Focused:** 하나의 클래스는 하나의 책임만 가집니다. AI에게 수정 요청 시 "이 클래스의 핵심 로직만 수정해줘"라고 명확히 지시할 수 있는 크기를 유지합니다.

## 📡 3. API & Data Standards
*   **Toss Payments Style:** 성공 시 직관적인 리소스 반환, 실패 시 `code`와 `message`를 포함한 표준 에러 객체 반환.
*   **Swagger Documentation:** 모든 컨트롤러는 OpenAPI 표준을 따르며, 로컬 구동 시 반드시 다음 주소에서 명세를 확인하십시오.
    *   **URL:** `http://localhost:8080/swagger-ui/index.html`

## 🛡 4. Security & Context
*   **Framework Neutrality:** 비즈니스 로직은 `SecurityContextHolder`를 직접 호출하지 않습니다. 반드시 `DomainUserContext` 추상화 레이어를 통해 사용자 정보를 획득합니다.
*   **MDC-based Traceability:** 모든 요청의 로그에는 고유 Trace ID가 포함되어야 하며, 이는 필터 레이어에서 자동으로 관리됩니다.
