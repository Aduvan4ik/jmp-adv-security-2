FROM maven:3.8.3-openjdk-17 AS build
COPY src /home/jmp-adv-security-2/src
COPY pom.xml /home/jmp-adv-security-2
RUN mvn -f /home/jmp-adv-security-2/pom.xml clean package

FROM amazoncorretto:17
COPY --from=build /home/jmp-adv-security-2/target/jmp-adv-security-2.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]