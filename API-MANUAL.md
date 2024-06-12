# API modification manual

### Table of contents
- [⛓️ Source structure](#-source-structure)
  - [Package](#package)
  - [Controller](#controller)
  - [Service](#service)
  - [Dao](#dao)
  - [Dto](#dto)
  - [Repository](#repository)
  - [Exception](#exception)
  - [FlaskServerManager](#flaskservermanager)
- [How to modify apis](#how-to-modify-apis)
  - [API 추가](#api-추가)
  - [API 수정](#api-수정)
  - [API 삭제](#api-삭제)
- [배포 환경 설정](#배포-환경-설정)
  - [input port 설정](#input-port-설정)
  - [MongoDB 설치](#mongodb-설치)

## ⛓️ Source structure
> **해당 시스템은 Spring Boot 기반으로 구성되어 있습니다. 따라서 MVC 패턴을 따르고 있으며, 각 패키지별로 역할을 나누어 구성되어 있습니다.
 자세한 API specification은 [여기](https://github.com/rlaekdud/lingo-be/blob/dev/README.md#functions---based-on-api)를 참고하세요.**

```plaintext
main
├─ java
│  └─ kr
│     └─ co
│        └─ ipalab
│           └─ lingobe
│              ├─ LingoBeApplication.java
│              ├─ api
│              │  ├─ log
│              │  │  ├─ controller
│              │  │  │  └─ LogController.java
│              │  │  ├─ dao
│              │  │  │  └─ LogDao.java
│              │  │  ├─ domain
│              │  │  │  └─ LogEntity.java
│              │  │  ├─ dto
│              │  │  │  └─ request
│              │  │  │     ├─ EvaluationDto.java
│              │  │  │     ├─ LogRequestDto.java
│              │  │  │     ├─ SuggestionDto.java
│              │  │  │     └─ TranslationDto.java
│              │  │  ├─ repository
│              │  │  │  └─ LogRepository.java
│              │  │  └─ service
│              │  │     └─ LogService.java
│              │  ├─ modelList
│              │  │  ├─ controller
│              │  │  │  └─ ModelListController.java
│              │  │  ├─ dto
│              │  │  │  └─ response
│              │  │  │     ├─ Model.java
│              │  │  │     └─ ModelListResponseDto.java
│              │  │  └─ service
│              │  │     └─ ModelListService.java
│              │  ├─ suggestion
│              │  │  ├─ controller
│              │  │  │  └─ SuggestionController.java
│              │  │  ├─ dto
│              │  │  │  ├─ request
│              │  │  │  │  └─ SuggestionRequestDto.java
│              │  │  │  └─ response
│              │  │  │     └─ SuggestionResponseDto.java
│              │  │  └─ service
│              │  │     └─ SuggestionService.java
│              │  └─ translation
│              │     ├─ controller
│              │     │  └─ TranslationController.java
│              │     ├─ dto
│              │     │  ├─ request
│              │     │  │  └─ TranslationRequestDto.java
│              │     │  └─ response
│              │     │     └─ TranslationResponseDto.java
│              │     └─ service
│              │        └─ TranslationService.java
│              ├─ flask
│              │  ├─ suggestion
│              │  │  └─ dto
│              │  │     ├─ request
│              │  │     │  └─ FlaskSuggestionRequestDto.java
│              │  │     └─ response
│              │  │        └─ FlaskSuggestionResponseDto.java
│              │  └─ translation
│              │     └─ dto
│              │        ├─ request
│              │        │  └─ FlaskTranslationRequestDto.java
│              │        └─ response
│              │           └─ FlaskTranslationResponseDto.java
│              └─ global
│                 ├─ advice
│                 │  ├─ ErrorCode.java
│                 │  ├─ ErrorMessage.java
│                 │  ├─ ErrorResponse.java
│                 │  └─ GlobalExceptionAdvice.java
│                 ├─ config
│                 │  └─ SwaggerConfig.java
│                 ├─ exception
│                 │  ├─ DatabaseAccessException.java
│                 │  ├─ FlaskResponseTimeoutError.java
│                 │  └─ GlobalException.java
│                 └─ utils
│                    ├─ FlaskServerManager.java
│                    └─ Response.java
└─ resources
   ├─ application-local.yml
   ├─ application.yml
   ├─ static
   └─ templates
```

### Package
| package   | description                                                  |
|-----------|--------------------------------------------------------------|
| `/api`    | • api를 관리하는 package<br>• 모든 api에 대한 controller, service 등이 담겨 있음 |
| `/flask`  | • flask 서버에서 api 응답을 받을 때 사용하는 클래스들이 담긴 패키지<br>• `/flask/{api}/dto`에 요청/응답 시 dto가 들어있음 |
| `/global` | • global하게 사용해야 하는 것들을 담은 package<br>• error, exception, response, util 등 범용적으로 사용되는 것이 포함 |

### Controller
- 각 controller는 api를 호출할 때 사용되는 endpoint를 정의하고, 해당 endpoint에 대한 요청을 처리하는 역할을 합니다.
- `@RestController` 어노테이션을 사용하여 정의되어 있습니다.
- `@RequestMapping` 어노테이션을 사용하여 endpoint를 정의하고, 해당 endpoint에 대한 요청을 처리하는 메소드를 정의합니다.
- `@RequestBody` 어노테이션을 사용하여 요청받은 body를 객체로 변환합니다.
- **controller는 service를 호출하여 비즈니스 로직을 처리하고, 결과를 반환합니다.**
- `swagger`를 활용해 api 문서를 자동으로 생성합니다. 이를 위해 `@Tag`, `@Operation`, `@ApiParam` 등의 어노테이션을 사용합니다.

### Service
- 각 service는 비즈니스 로직을 처리하는 역할을 합니다.
- `@Service` 어노테이션을 사용하여 정의되어 있습니다.
- **service는 dao를 호출하여 데이터를 조회하거나 저장하고, 결과를 반환합니다.**

### Dao
- 각 dao는 데이터베이스에 접근하여 데이터를 조회하거나 저장하는 역할을 합니다.
- `@Repository` 어노테이션을 사용하여 정의되어 있습니다.

### Dto
- 각 dto는 api 요청/응답 시 사용되는 객체를 정의하는 역할을 합니다.
- `@Getter`, `@Setter` 어노테이션을 사용하여 getter, setter 메소드를 자동으로 생성합니다.
- `@Builder` 어노테이션을 사용하여 builder 패턴을 사용할 수 있도록 합니다.
- `@NoArgsConstructor`, `@AllArgsConstructor` 어노테이션을 사용하여 기본 생성자, 모든 필드를 인자로 받는 생성자를 자동으로 생성합니다.

### Repository
- 각 repository는 데이터베이스에 접근하여 데이터를 조회하거나 저장하는 역할을 합니다. Repository를 Dao에서 사용합니다.
- `@Repository` 어노테이션을 사용하여 정의되어 있습니다.
- `MongoRepository`를 상속받아 사용합니다.

### Exception
- 각 exception은 예외 발생 시 처리하는 역할을 합니다.
- customizing한 exception을 사용하여 예외 발생 시 처리합니다. 모든 예외는 `GlobalException`을 상속받아 사용합니다.
- `GlobalExceptionAdvice`를 사용하여 예외를 핸들링합니다.

### FlaskServerManager
- flask 서버와 통신하기 위한 클래스입니다.
- `RestTemplate`을 사용하여 flask 서버와 통신합니다.
- `FlaskServerManager`를 사용하여 flask 서버와 통신하는 메소드를 정의합니다.
- `global/utils` 패키지에 위치합니다.

## How to modify apis
> Spring Boot 및 MVC 패턴에 대한 이해가 필요합니다. 이에 대한 이해도가 없는 경우 아래의 체크리스트를 확인하며 작업을 진행하기 바랍니다.

### API 추가
- [ ] `api` 패키지 내에 새로운 api 패키지를 생성합니다.
- [ ] `controller`, `service`, `dao`, `dto` 패키지를 생성합니다.
- [ ] `controller`에는 api endpoint를 정의하고, 요청을 처리하는 메소드를 정의합니다.
- [ ] `service`에는 비즈니스 로직을 처리하는 메소드를 정의합니다.
- [ ] `dao`에는 데이터베이스에 접근하여 데이터를 조회하거나 저장하는 메소드를 정의합니다.
- [ ] `dto`에는 api 요청/응답 시 사용되는 객체를 정의합니다.
- [ ] `repository`에는 데이터베이스에 접근하여 데이터를 조회하거나 저장하는 메소드를 정의합니다.
- [ ] `exception`에는 예외 발생 시 처리하는 메소드를 정의합니다.
- [ ] `global` 패키지 내에는 범용적으로 사용되는 것들을 정의합니다.

### API 수정
- [ ] `controller`에 있는 endpoint를 수정합니다.
- [ ] `service`에 있는 비즈니스 로직을 수정합니다.
- [ ] `dao`에 있는 데이터베이스 접근 메소드를 수정합니다.
- [ ] `dto`에 있는 api 요청/응답 시 사용되는 객체를 수정합니다.
- [ ] `repository`에 있는 데이터베이스 접근 메소드를 수정합니다.
- [ ] `exception`에 있는 예외 처리 메소드를 수정합니다.

### API 삭제
> API 삭제 시 repository, entity가 **다른 api에 의존성을 가질 수 있으므로 주의**하여 삭제합니다.

- [ ] `api` 패키지 내에 삭제할 api 패키지를 삭제합니다.
- [ ] `controller`, `service`, `dao`, `dto` 패키지를 삭제합니다.
- [ ] `repository`에 있는 데이터베이스 접근 메소드를 삭제합니다.
- [ ] `exception`에 있는 예외 처리 메소드를 삭제합니다.
- [ ] `global` 패키지 내에 있는 범용적으로 사용되는 것들을 삭제합니다.

## 배포 환경 설정
### input port 설정
- 배포 서버의 인바운드 설정이 필요합니다.
- `sudo iptables -I INPUT 1 -p tcp —dport 8080 -j ACCEPT` 명령어를 사용하여 8080 포트를 열어줍니다.
- `sudo iptables -P OUTPUT ACCEPT` 명령어를 사용하여 OUTPUT 설정을 ACCEPT로 변경합니다.

### MongoDB 설치
- 배포 서버가 하나로 통일되어 있기 때문에 한 서버에 DB를 설치하고 BE를 배포해 사용합니다.
- Docker를 활용해 MongoDB를 설치하고 실행합니다.
  - MongoDB 설치 방법은 [여기](https://poiemaweb.com/docker-mongodb)를 참고하세요.
  - 단, **컨테이너 실행 시 `--network=host` option을 사용하여 호스트 네트워크와 연결합니다.**
- Docker를 사용하지 않을 경우 [여기](https://coterie.tistory.com/20)를 참고하여 MongoDB를 설치하세요.

- - -
**Developed by ipalab(KNU)**, API 문서 관련 연락은 [여기](https://github.com/rlaekdud)로 부탁드립니다.