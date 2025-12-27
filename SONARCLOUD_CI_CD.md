# Configuration SonarCloud et CI/CD

## 📊 SonarCloud

### Configuration
Le projet est configuré pour utiliser SonarCloud pour l'analyse de qualité du code.

**Paramètres SonarCloud (pom.xml):**
- `sonar.organization`: seifeddineachouri
- `sonar.host.url`: https://sonarcloud.io
- `sonar.projectKey`: demo-devops

### Exécution locale
Pour exécuter l'analyse SonarCloud localement :

```bash
mvn clean verify sonar:sonar -Dsonar.token=VOTRE_TOKEN
```

⚠️ **Important**: Ne committez jamais votre token SonarCloud dans le code source !

### Configuration GitHub
Pour que l'analyse fonctionne sur GitHub Actions, vous devez ajouter votre token SonarCloud comme secret :

1. Allez dans votre repository GitHub
2. Settings → Secrets and variables → Actions
3. Créez un nouveau secret nommé `SONAR_TOKEN`
4. Collez votre token SonarCloud (celui que vous avez généré sur sonarcloud.io)

## 🚀 GitHub Actions CI/CD

Le workflow GitHub Actions (`.github/workflows/build.yml`) s'exécute automatiquement sur:
- Push vers `main`, `master`, ou `develop`
- Pull requests vers ces branches

### Étapes du workflow:
1. ✅ **Checkout**: Récupération du code source
2. ☕ **Setup JDK 17**: Installation de Java 17
3. 🔨 **Build and Test**: Compilation et exécution des tests
4. 📊 **SonarCloud Analysis**: Analyse de la qualité du code
5. 📤 **Upload Artifacts**: Sauvegarde des rapports et du JAR

### Badges
Ajoutez ces badges dans votre README principal pour afficher le statut :

```markdown
[![Build Status](https://github.com/VOTRE_USERNAME/VOTRE_REPO/workflows/Build%20and%20Test/badge.svg)](https://github.com/VOTRE_USERNAME/VOTRE_REPO/actions)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=demo-devops&metric=alert_status)](https://sonarcloud.io/summary/new_code?id=demo-devops)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=demo-devops&metric=coverage)](https://sonarcloud.io/summary/new_code?id=demo-devops)
```

## 📈 Couverture de code avec JaCoCo

Le projet utilise JaCoCo pour mesurer la couverture de code. Les rapports sont générés automatiquement lors de `mvn test` ou `mvn verify`.

**Emplacement des rapports:**
- HTML: `target/site/jacoco/index.html`
- XML: `target/site/jacoco/jacoco.xml` (utilisé par SonarCloud)

## 🔧 Configuration des secrets GitHub

Pour que le workflow fonctionne correctement, configurez ces secrets dans GitHub:

| Secret | Description | Où le trouver |
|--------|-------------|---------------|
| `SONAR_TOKEN` | Token d'authentification SonarCloud | sonarcloud.io → My Account → Security |
| `GITHUB_TOKEN` | Token GitHub (automatique) | Fourni automatiquement par GitHub Actions |

## 📝 Commandes utiles

```bash
# Tests uniquement
mvn test

# Tests avec couverture
mvn verify

# Analyse SonarCloud locale
mvn clean verify sonar:sonar -Dsonar.token=VOTRE_TOKEN

# Voir la couverture localement
# Ouvrir: target/site/jacoco/index.html dans un navigateur
```

## 🐛 Dépannage

### Erreur: "No plugin found for prefix 'sonar'"
✅ **Solution**: Le plugin est maintenant configuré dans le `pom.xml`

### Erreur d'authentification SonarCloud
✅ **Solution**: Vérifiez que le token est correct et que le secret `SONAR_TOKEN` est bien configuré dans GitHub

### Tests qui échouent
✅ **Solution**: Exécutez `mvn clean test` localement pour identifier les problèmes

## 📚 Ressources

- [SonarCloud Documentation](https://docs.sonarcloud.io/)
- [GitHub Actions Documentation](https://docs.github.com/en/actions)
- [JaCoCo Documentation](https://www.jacoco.org/jacoco/trunk/doc/)

