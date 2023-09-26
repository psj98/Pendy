FROM adoptopenjdk/openjdk11
EXPOSE 8081
ARG JAR_FILE=build/libs/namani-0.0.1-SNAPSHOT.jar
#ENV PROD_DATASOURCE_URL="jdbc:mysql://stg-yswa-kr-practice-vector_chromadb-master.mariadb.database.azure.com:3306/S09P22A410?serverTimezone=Asia/Seoul&useUnicode=true&characterEncoding=utf8&useSSL=false" \
#    PROD_DATASOURCE_USERNAME=S09P22A410@stg-yswa-kr-practice-vector_chromadb-master \
#    PROD_DATASOURCE_PASSWORD=eMTQCDD8CU
ADD ${JAR_FILE} app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]