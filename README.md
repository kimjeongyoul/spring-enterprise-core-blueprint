# Spring Enterprise Core Blueprint

> **즉시 상용 투입이 가능한 인터페이스 기반의 공통 인프라 표준 엔진**

본 프로젝트는 대규모 시스템 구축 시 매번 반복되는 **로그, 파일 저장, 페이징, 통신** 등의 공통 작업을 인터페이스 기반으로 표준화한 **'백엔드 코어 설계도(Blueprint)'**입니다.

## 🏛 인프라 공통화 기술 (Common Abstractions)

### 1. Storage Abstraction Layer
*   `StorageProvider` 인터페이스를 통해 S3, FTP, Local Storage 등 물리적 저장소에 관계없이 일관된 파일 업로드/다운로드 로직을 제공합니다. (Multipart 대응 완료)

### 2. Generic QueryDSL Support
*   `QuerydslCustomRepositorySupport`를 통해 복잡한 페이징과 카운트 쿼리 최적화 로직을 캡슐화했습니다. 개발자는 오직 `where` 조건절에만 집중할 수 있습니다.

### 3. Traceable Enterprise Logging
*   MDC(Mapped Diagnostic Context) 기반의 **Trace ID 필터**를 통해 분산 환경에서의 로그 추적성을 확보했습니다.
*   AOP 기반의 성능 모니터링 레이어를 통해 모든 API의 실행 속도를 실시간 감시합니다.

### 4. Toss Payments API Standard
*   예측 가능한 에러 모델(`code`, `message`)과 군더더기 없는 리소스 중심의 응답 설계를 채택하여 최상의 DX(Developer Experience)를 제공합니다.
*   **API Documentation (Swagger):** `http://localhost:8080/swagger-ui/index.html`

### 6. Authentication & Authorization Template
개발 효율성을 극대화하기 위해 경로 기반의 보안 분리 정책과 어노테이션 기반의 사용자 주입 템플릿을 제공합니다.

*   **Path-based Security:** `/api/v1/public/**` (비로그인 허용)와 `/api/v1/private/**` (로그인 필수) 경로 규칙을 통해 보안 설정을 직관적으로 관리합니다.
*   **Declarative User Injection:** `@LoginUser` 어노테이션을 제공하여, 컨트롤러에서 토큰 파싱 로직 없이 현재 로그인한 사용자 정보를 즉시 주입받을 수 있습니다.
