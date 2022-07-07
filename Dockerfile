FROM azul/zulu-openjdk-alpine:11
ADD /target/Aeb-1.jar backend.jar
ENTRYPOINT ["java", "-jar", "backend.jar"]