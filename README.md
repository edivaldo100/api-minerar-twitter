# api-minera-tweets

Para executar localmente basta add. as variaveis de ambiente como arqgumentos
-DDATABASE_HOST=localhost 
-DDATABASE_PORT=3306 
-DDATABASE_USER=twitter 
-DDATABASE_PASSWORD=twitter 
-DDATABASE_NAME=twitter

# Criando a image do docker
mvn clean install package docker:build -DskipTests

