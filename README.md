# Spring Enterprise Core Blueprint

> **엔터프라이즈급 서비스 확장을 위한 도메인 중심(Domain-Driven) 백엔드 표준 엔진**

본 프로젝트는 대규모 시스템 구축 시 발생할 수 있는 코드 복잡도를 제어하고, 도메인 간의 결합도를 낮추기 위해 설계된 **'시니어급 백엔드 아키텍처 템플릿'**입니다.

## 🏛 Key Architecture: Package by Domain
기존의 `controller`, `service`, `repository` 중심의 수평적 구조에서 탈피하여, 비즈니스 가치 중심의 **수직적 도메인 구조**를 채택했습니다.

```text
com.vibe.modules
├── auth (인증/인가)
├── order (주문/흐름)
├── user (사용자/도메인)
└── common (공통 유틸리티)
```

각 도메인은 `api`, `application`, `domain`, `infrastructure` 레이어를 내부에 품고 있어, 특정 도메인 수정이 다른 도메인에 미치는 영향을 최소화합니다.

## 🚀 Core Features

### 1. Robust Security & Context
*   **JWT & Spring Security:** 무상태(Stateless) 아키텍처 기반의 보안 필터 체인.
*   **Declarative User Injection:** 컨트롤러 파라미터에서 `@LoginUser` 만으로 인증된 사용자 정보를 즉시 획득.

### 2. Infrastructure Abstraction
*   **Storage Abstraction:** 인터페이스 기반 설계로 Local, S3, FTP 등 저장소 환경 변화에 유연하게 대응.
*   **Standard Messaging:** `EventPublisher`를 통한 도메인 간 이벤트 기반 비동기 처리 기반 마련.

### 3. API Standards (Toss Style)
*   **Predictable Error Model:** 모든 에러는 `ErrorCode`를 통해 표준화된 응답 포맷으로 전달됩니다.
*   **Swagger Integration:** 모든 API는 `http://localhost:8080/swagger-ui/index.html`에서 즉시 테스트 가능합니다.

## 🚀 Quick Start & Testing Guide

본 프로젝트는 보안을 위해 **이중 인증(Client Key + JWT)** 체계를 채택하고 있습니다. 모든 API 호출 시 다음 순서를 따르십시오.

### 1. 기본 필수 헤더 (Required Headers)
모든 요청에는 허가된 클라이언트임을 증명하는 키가 필요합니다.
*   `X-VIBE-CLIENT-KEY`: `vibe-client-secret-key-2026`

### 2. 테스트 계정 정보
서버 시작 시 `TestDataInit`에 의해 다음 계정이 자동 생성됩니다.
*   **ID:** `admin-vibe`
*   **Password:** `password123`

### 3. 인증 흐름 (Authentication Flow)

#### Step 1: 로그인 및 토큰 발급
```bash
curl -X 'POST' \
  'http://localhost:8080/api/v1/public/auth/login' \
  -H 'X-VIBE-CLIENT-KEY: vibe-client-secret-key-2026' \
  -H 'Content-Type: application/json' \
  -d '{ "userId": "admin-vibe", "password": "password123" }'
```
*   응답의 `accessToken`을 복사하여 이후 요청의 `Authorization` 헤더에 사용합니다.

#### Step 2: 주문 생성 (Multipart/Form-Data)
```bash
curl -X 'POST' \
  'http://localhost:8080/api/v1/private/orders' \
  -H 'X-VIBE-CLIENT-KEY: vibe-client-secret-key-2026' \
  -H 'Authorization: Bearer <YOUR_TOKEN>' \
  -H 'Content-Type: multipart/form-data' \
  -F 'request={"productName": "MacBook Pro", "quantity": 1};type=application/json' \
  -F 'receipt=@your_file.txt'
```
> **💡 Tip:** 본 프로젝트는 `WebConfig` 설정을 통해 Swagger UI 등에서 JSON 파트의 `Content-Type`이 누락되어도 자동으로 인식하도록 지능형 컨버터를 탑재하고 있습니다.

#### Step 3: 내 주문 내역 조회 (Paging)
```bash
curl -X 'GET' \
  'http://localhost:8080/api/v1/private/orders/me?page=0&size=10' \
  -H 'X-VIBE-CLIENT-KEY: vibe-client-secret-key-2026' \
  -H 'Authorization: Bearer <YOUR_TOKEN>'
```

## 🛠 Tech Stack
- **Framework:** Spring Boot 3.4
- **Security:** Spring Security & JWT
- **Persistence:** Spring Data JPA, QueryDSL, H2/MySQL
- **Documentation:** SpringDoc OpenAPI (Swagger)
- **Validation:** Hibernate Validator
- **Logging:** Log4j2 with MDC (Trace ID)
