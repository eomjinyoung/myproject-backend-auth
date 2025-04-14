# myproject-backend-auth

## 설정

### 스프링부트 설정 파일을 개발과 운영으로 분리

- application.properties 파일 분리
  - application-dev.properties (개발)
  - application-prod.properties (운영)
- 실행 방법
  - 환경변수를 통해 설정
    - 예) $ export SPRING_PROFILES_ACTIVE=dev
    - 예) $ gradle bootRun
    - 예) $ java -jar myapp.jar
  - JVM 아규먼트: `-Dspring.profiles.active=dev`
    - 예) $ java -Dspring.profiles.active=prod -jar myapp.jar
  - 프로그램 아규먼트: `--spring.profiles.active=dev`
    - 예) $ java -jar myapp.jar --spring.profiles.active=prod
    - 예) $ gradle bootRun --args='--spring.profiles.active=dev'
  - IntelliJ : 환경변수를 통해 설정한다.
    - bootRun -> 구성 -> 편집: spring.profiles.active=dev

### 서버 포트 번호 설정

- 실행 방법
  - 환경변수를 통해 설정
    - 예) `$ export SERVER_PORT=9010`
    - 예) `$ gradle bootRun`
    - 예) `$ java -jar myapp.jar`
  - JVM 아규먼트: `-Dserver.port=9010`
    - 예) `$ java -Dserver.port=9010 -jar myapp.jar`
  - 프로그램 아규먼트: `--server.port=9010`
    - 예) `$ java -jar myapp.jar --server.port=9010`
    - 예) `$ gradle bootRun --args='--server.port=9010`

## 빌드 및 실행 테스트

```bash
./gradlew bootJar
java -jar ./app/build/libs/myproject-backend-auth.jar --spring.profiles.active=prod --server.port=9010
```

## Docker Image 파일 생성

```
# 1. Java 17 경량 이미지 사용
FROM eclipse-temurin:17-jdk-alpine

# 2. 작업 디렉토리 생성
WORKDIR /app

# 3. JAR 파일 복사
COPY ./app/build/libs/myproject-backend-auth.jar app.jar

# 4. 환경변수 기본값 설정 (원하면 오버라이드 가능)
ENV SPRING_PROFILES_ACTIVE=prod
ENV SERVER_PORT=9010

# 5. 포트 노출 (optional)
EXPOSE ${SERVER_PORT}

# 6. 실행 명령 (환경변수 사용)
ENTRYPOINT ["sh", "-c", "java -jar app.jar --spring.profiles.active=$SPRING_PROFILES_ACTIVE --server.port=$SERVER_PORT"]

```
