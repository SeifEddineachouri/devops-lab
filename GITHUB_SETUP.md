# Demo DevOps - Quick Start Guide

## 🚀 Configuration rapide pour GitHub et SonarCloud

### Étape 1: Configuration des secrets GitHub

1. Allez sur votre repository GitHub
2. Cliquez sur **Settings** → **Secrets and variables** → **Actions**
3. Cliquez sur **New repository secret**
4. Ajoutez le secret suivant :
   - Name: `SONAR_TOKEN`
   - Value: `05327ae2bade09a6628873e93562f6c25987c03f`

### Étape 2: Configuration SonarCloud

1. Connectez-vous sur [sonarcloud.io](https://sonarcloud.io)
2. Importez votre projet GitHub
3. Vérifiez que les paramètres correspondent :
   - Organization: `seifeddineachouri`
   - Project Key: `demo-devops`

### Étape 3: Push vers GitHub

```bash
# Ajouter tous les fichiers
git add .

# Commit des changements
git commit -m "Add SonarCloud and GitHub Actions CI/CD"

# Push vers GitHub
git push origin main
```

### Étape 4: Vérifier l'exécution

1. Allez sur votre repository GitHub
2. Cliquez sur l'onglet **Actions**
3. Vous devriez voir le workflow "Build and Test" en cours d'exécution

### Étape 5: Voir les résultats SonarCloud

Une fois le workflow terminé, allez sur:
- SonarCloud: https://sonarcloud.io/project/overview?id=demo-devops

## 📊 Ce qui a été configuré

### ✅ Corrections apportées

1. **Tests unitaires**
   - Remplacement de `@MockBean` par `@MockitoBean` (Spring Boot 3.4+)
   - Configuration de `@Component` pour les step definitions Cucumber

2. **Configuration Mockito**
   - Ajout de `-XX:+EnableDynamicAgentLoading` pour Java 21+
   - Configuration dans `pom.xml` et `.mvn/jvm.config`

3. **Correction H2 Database**
   - Changement de `H2Dialects` → `H2Dialect` dans `application-test.properties`

4. **SonarCloud**
   - Plugin SonarCloud ajouté dans `pom.xml`
   - Configuration des propriétés SonarCloud
   - Intégration avec JaCoCo pour la couverture de code

5. **GitHub Actions CI/CD**
   - Workflow automatique sur push et pull request
   - Compilation, tests, et analyse SonarCloud
   - Upload des artefacts (JAR et rapports)

## 🔧 Commandes locales

```bash
# Compiler et tester
./mvnw clean test

# Compiler, tester et créer le JAR
./mvnw clean package

# Exécuter l'analyse SonarCloud localement
./mvnw clean verify sonar:sonar -Dsonar.token=VOTRE_TOKEN

# Exécuter seulement les tests d'un contrôleur
./mvnw test -Dtest=UserControllerTest
```

## 📁 Structure des fichiers ajoutés/modifiés

```
demo-devops/
├── .github/
│   └── workflows/
│       └── build.yml                    # ✨ Workflow GitHub Actions
├── .mvn/
│   └── jvm.config                       # ✨ Configuration JVM pour Maven
├── pom.xml                              # ✅ Plugin SonarCloud ajouté
├── src/
│   └── test/
│       ├── java/
│       │   └── ...
│       │       ├── controller/
│       │       │   └── UserControllerTest.java    # ✅ @MockitoBean
│       │       └── bdd/
│       │           └── UserStepDefinitions.java   # ✅ @Component
│       └── resources/
│           └── application-test.properties        # ✅ H2Dialect
├── MOCKITO_CONFIG.md                    # 📚 Documentation Mockito
├── SONARCLOUD_CI_CD.md                  # 📚 Documentation SonarCloud
└── GITHUB_SETUP.md                      # 📚 Ce fichier

```

## 🎯 Résultat attendu

Après le push vers GitHub, vous devriez voir :
- ✅ Workflow GitHub Actions qui s'exécute avec succès
- ✅ Tests qui passent (6 tests dans UserControllerTest)
- ✅ Rapport de couverture généré par JaCoCo
- ✅ Analyse SonarCloud complète avec métriques de qualité

## ⚠️ Notes importantes

1. **Ne commitez jamais de tokens** : Le token SonarCloud est montré ici uniquement pour la configuration. Dans un environnement professionnel, utilisez uniquement des secrets GitHub.

2. **Java Version** : Le projet utilise Java 17 pour la compatibilité, mais compile avec Java 23 en local.

3. **Tests** : Si certains tests échouent, vérifiez que toutes les dépendances sont à jour.

## 🆘 Besoin d'aide ?

- [Documentation GitHub Actions](https://docs.github.com/en/actions)
- [Documentation SonarCloud](https://docs.sonarcloud.io/)
- [Guide Spring Boot Testing](https://spring.io/guides/gs/testing-web)

Bonne chance ! 🚀

