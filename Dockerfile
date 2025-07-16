# Étape 1 : builder l'application avec Maven
FROM maven:3.8.7-eclipse-temurin-17 AS build

WORKDIR /app

# Copier pom.xml et télécharger les dépendances (cache)
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copier tout le code source
COPY src ./src

# Compiler et packager le jar (sans tests pour gagner du temps)
RUN mvn clean package -DskipTests

# Étape 2 : créer une image runtime plus légère avec JRE
FROM eclipse-temurin:17-jre-alpine

WORKDIR /app

# Copier le jar construit depuis l'étape de build
COPY --from=build /app/target/*.jar app.jar

# Exposer le port sur lequel ton application Spring Boot écoute (par défaut 8080)
EXPOSE 8080

# Lancer l'application
ENTRYPOINT ["java", "-jar", "app.jar"]
