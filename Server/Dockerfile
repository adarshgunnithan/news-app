FROM java:8-jre
WORKDIR usr/src
ENV MYSQL_DATABASE=newsDb
ENV MYSQL_USER=app_root
ENV MYSQL_PASSWORD=root123
ENV MYSQL_CI_URL=jdbc:mysql://localhost:3306/newsDb
ADD ./target/news-app-0.0.1-SNAPSHOT.jar usr/src/
RUN ["chmod","+x","usr/src/news-app-0.0.1-SNAPSHOT.jar"]
ENTRYPOINT ["java","-jar","usr/src/news-app-0.0.1-SNAPSHOT.jar"]
CMD ["-start"]