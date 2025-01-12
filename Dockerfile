FROM eclipse-temurin:21-jdk

RUN mkdir -p /logs

ENV PROFILE=default
ENV TZ=Asia/Seoul
EXPOSE 8080

ARG JAR_FILE="build/libs/api.jar"

COPY ${JAR_FILE} app.jar

ENTRYPOINT ["sh", "-c", "java -XX:MaxGCPauseMillis=100 -XX:InitialRAMPercentage=50.0 -XX:MaxRAMPercentage=80.0 -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=*:5005 $JAVA_OPTS -Dspring.profiles.active=${SPRING_PROFILES_ACTIVE} -jar app.jar"]
