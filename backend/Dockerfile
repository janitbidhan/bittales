FROM openjdk

CMD ["mvn clean install"]

COPY target/i18nApplication-1.jar .

EXPOSE 8080

CMD ["java", "-jar", "i18nApplication-1.jar"]

# docker build -t i18nappln .
# docker run -p 8081:8080 --rm  --name i18ncontainer i18nappln