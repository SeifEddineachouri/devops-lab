# 🚀 Demo DevOps - Spring Boot Application

[![Build Status](https://github.com/seifeddineachouri/demo-devops/workflows/Build%20and%20Test/badge.svg)](https://github.com/seifeddineachouri/demo-devops/actions)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=demo-devops&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=demo-devops)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=demo-devops&metric=coverage)](https://sonarcloud.io/summary/new_code?id=demo-devops)
[![Bugs](https://sonarcloud.io/api/project_badges/measure?project=demo-devops&metric=bugs)](https://sonarcloud.io/summary/new_code?id=demo-devops)
[![Code Smells](https://sonarcloud.io/api/project_badges/measure?project=demo-devops&metric=code_smells)](https://sonarcloud.io/summary/new_code?id=demo-devops)

## 📖 Description

Projet de démonstration DevOps avec Spring Boot, intégrant les meilleures pratiques de développement moderne :
- Tests unitaires et d'intégration
- Tests BDD avec Cucumber
- Couverture de code avec JaCoCo
- Analyse qualité avec SonarCloud
- CI/CD avec GitHub Actions

## 🛠️ Technologies

- **Framework**: Spring Boot 3.5.9
- **Java**: 17 (Compilé avec 23)
- **Build Tool**: Maven
- **Database**: 
  - Production: MySQL
  - Tests: H2 (in-memory)
- **Testing**: 
  - JUnit 5
  - Mockito
  - Cucumber (BDD)
- **Code Quality**: 
  - SonarCloud
  - JaCoCo
- **CI/CD**: GitHub Actions
- **Documentation API**: OpenAPI/Swagger

## 🚀 Démarrage rapide

### Prérequis
- Java 17 ou supérieur
- Maven 3.6+ (ou utiliser le wrapper Maven inclus)
- MySQL (pour la production) ou H2 (pour les tests)

### Installation

```bash
# Cloner le repository
git clone https://github.com/seifeddineachouri/demo-devops.git
cd demo-devops

# Compiler et tester
./mvnw clean test

# Compiler et créer le JAR
./mvnw clean package

# Lancer l'application
./mvnw spring-boot:run
```

### Accès à l'application

- **Application**: http://localhost:8080
- **Swagger UI**: http://localhost:8080/swagger-ui.html
- **API Docs**: http://localhost:8080/v3/api-docs

## 🧪 Tests

### Exécuter tous les tests
```bash
./mvnw test
```

### Tests avec couverture
```bash
./mvnw verify
```

### Voir le rapport de couverture
Ouvrir dans un navigateur : `target/site/jacoco/index.html`

### Tests unitaires spécifiques
```bash
./mvnw test -Dtest=UserControllerTest
```

## 📊 Analyse de qualité

### Analyse locale avec SonarCloud
```bash
./mvnw clean verify sonar:sonar -Dsonar.token=VOTRE_TOKEN
```

### Voir les résultats
- Dashboard: https://sonarcloud.io/project/overview?id=demo-devops

## 🔄 CI/CD

Le projet utilise GitHub Actions pour l'intégration continue. À chaque push ou pull request :
1. ✅ Compilation du code
2. ✅ Exécution des tests
3. ✅ Génération du rapport de couverture
4. ✅ Analyse SonarCloud
5. ✅ Upload des artefacts

Voir le workflow: `.github/workflows/build.yml`

## 📁 Structure du projet

```
demo-devops/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/example/demo_devops/
│   │   │       ├── controller/      # API REST Controllers
│   │   │       ├── entity/          # Entités JPA
│   │   │       ├── repository/      # Repositories
│   │   │       └── service/         # Services métier
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       ├── java/
│       │   └── com/example/demo_devops/
│       │       ├── controller/      # Tests unitaires
│       │       ├── integration/     # Tests d'intégration
│       │       └── bdd/            # Tests BDD (Cucumber)
│       └── resources/
│           └── application-test.properties
├── .github/
│   └── workflows/
│       └── build.yml               # CI/CD Pipeline
├── pom.xml                         # Configuration Maven
└── README.md                       # Ce fichier
```

## 🎯 Endpoints principaux

### User Management

| Méthode | Endpoint | Description |
|---------|----------|-------------|
| GET | `/api/users` | Liste tous les utilisateurs |
| GET | `/api/users/{id}` | Récupère un utilisateur par ID |
| POST | `/api/users` | Crée un nouvel utilisateur |
| PUT | `/api/users/{id}` | Met à jour un utilisateur |
| DELETE | `/api/users/{id}` | Supprime un utilisateur |

## 🔧 Configuration

### Base de données (application.properties)
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/demo_devops
spring.datasource.username=root
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
```

### Tests (application-test.properties)
```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.jpa.hibernate.ddl-auto=create-drop
```

## 📚 Documentation

- [Configuration Mockito](MOCKITO_CONFIG.md)
- [Guide SonarCloud & CI/CD](SONARCLOUD_CI_CD.md)
- [Configuration GitHub](GITHUB_SETUP.md)
- [Résumé des modifications](SUMMARY.md)

## 🤝 Contribuer

1. Fork le projet
2. Créez une branche pour votre fonctionnalité (`git checkout -b feature/AmazingFeature`)
3. Committez vos changements (`git commit -m 'Add some AmazingFeature'`)
4. Poussez vers la branche (`git push origin feature/AmazingFeature`)
5. Ouvrez une Pull Request

## 📝 Changelog

### Version actuelle
- ✅ Configuration SonarCloud
- ✅ GitHub Actions CI/CD
- ✅ Correction tests Spring Boot 3.4+
- ✅ Configuration Mockito pour Java 21+
- ✅ Couverture de code JaCoCo
- ✅ Tests BDD avec Cucumber

## 📄 License

Ce projet est sous licence MIT.

## 👨‍💻 Auteur

**Seifeddine Achouri**
- GitHub: [@seifeddineachouri](https://github.com/seifeddineachouri)
- SonarCloud: [@seifeddineachouri](https://sonarcloud.io/organizations/seifeddineachouri)

## 🙏 Remerciements

- Spring Boot Team
- SonarCloud
- GitHub Actions
- La communauté Open Source

---

⭐ Si ce projet vous a été utile, n'hésitez pas à lui donner une étoile !

