# 🚀 Commandes Git pour déployer vers GitHub

## Étape 1: Vérifier l'état des fichiers

```bash
git status
```

## Étape 2: Ajouter tous les fichiers modifiés

```bash
git add .
```

## Étape 3: Commiter les changements

```bash
git commit -m "feat: Configure SonarCloud and GitHub Actions CI/CD

- Add @MockitoBean to replace deprecated @MockBean
- Add @Component to UserStepDefinitions for Spring context
- Configure Mockito agent for Java 21+ compatibility
- Fix H2Dialect configuration in test properties
- Add SonarCloud integration with JaCoCo coverage
- Create GitHub Actions workflow for automated CI/CD
- Add comprehensive documentation (README, guides)
- Configure maven-surefire-plugin with proper argLine

Fixes: Tests compatibility with Spring Boot 3.4+
Closes: Configuration for modern Java versions
"
```

## Étape 4: Pousser vers GitHub

```bash
git push origin main
```

Ou si votre branche principale s'appelle `master` :

```bash
git push origin master
```

## Étape 5: Vérifier le déploiement

1. Allez sur votre repository GitHub
2. Cliquez sur l'onglet **Actions**
3. Vérifiez que le workflow "Build and Test" s'exécute

## ⚠️ IMPORTANT: Configurer les secrets GitHub

Avant de pusher, ou juste après, configurez le secret SonarCloud :

1. Allez sur GitHub.com → Votre repository
2. **Settings** → **Secrets and variables** → **Actions**
3. Cliquez sur **New repository secret**
4. Nom: `SONAR_TOKEN`
5. Valeur: `05327ae2bade09a6628873e93562f6c25987c03f`
6. Cliquez sur **Add secret**

## 📋 Checklist finale

Avant de pusher, vérifiez que :

- [ ] Tous les tests passent localement : `./mvnw test`
- [ ] Le projet compile : `./mvnw clean package`
- [ ] Le fichier `.gitignore` est présent
- [ ] Les fichiers sensibles ne sont PAS committés
- [ ] Le README.md est à jour
- [ ] Les workflows GitHub Actions sont dans `.github/workflows/`

## 🎯 Après le push

Une fois le code poussé sur GitHub :

1. **Vérifier le workflow**:
   - GitHub → Actions → "Build and Test"
   - Vérifier que toutes les étapes sont vertes ✅

2. **Vérifier SonarCloud**:
   - Allez sur https://sonarcloud.io
   - Cherchez votre projet "demo-devops"
   - Vérifiez le Quality Gate

3. **Voir la couverture**:
   - SonarCloud affichera automatiquement la couverture de code
   - Les rapports JaCoCo sont uploadés automatiquement

## 🔧 En cas de problème

### Workflow échoue sur GitHub
```bash
# Vérifier localement
./mvnw clean verify

# Si ça marche, le problème vient peut-être des secrets
# Vérifiez que SONAR_TOKEN est bien configuré
```

### Tests échouent
```bash
# Exécuter les tests localement avec détails
./mvnw test -X

# Vérifier les logs
cat target/surefire-reports/*.txt
```

### SonarCloud ne reçoit pas les données
```bash
# Tester l'analyse localement
./mvnw clean verify sonar:sonar -Dsonar.token=05327ae2bade09a6628873e93562f6c25987c03f
```

## 📊 Commandes utiles après le déploiement

```bash
# Voir l'historique des commits
git log --oneline -10

# Voir les branches
git branch -a

# Créer une nouvelle branche pour une feature
git checkout -b feature/nouvelle-fonctionnalite

# Mettre à jour depuis GitHub
git pull origin main
```

## 🎉 C'est fait !

Une fois que vous avez exécuté ces commandes, votre projet est :
- ✅ Versionné sur GitHub
- ✅ Analysé par SonarCloud
- ✅ Testé automatiquement par GitHub Actions
- ✅ Prêt pour le développement collaboratif

---

**Besoin d'aide ?** Consultez les fichiers de documentation :
- `README.md` - Vue d'ensemble du projet
- `GITHUB_SETUP.md` - Guide de démarrage rapide
- `SONARCLOUD_CI_CD.md` - Documentation SonarCloud et CI/CD
- `SUMMARY.md` - Résumé de toutes les modifications

