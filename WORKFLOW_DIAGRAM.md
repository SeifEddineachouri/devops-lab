# 🔄 Workflow DevOps Complet

```
┌─────────────────────────────────────────────────────────────────────────┐
│                        DÉVELOPPEUR LOCAL                                │
└─────────────────────────────────────────────────────────────────────────┘
                                    │
                                    │ Code + Tests
                                    ▼
                      ┌──────────────────────────┐
                      │   git add .              │
                      │   git commit -m "..."    │
                      │   git push origin main   │
                      └──────────────────────────┘
                                    │
                                    ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                            GITHUB                                       │
│                                                                         │
│  ┌─────────────────────────────────────────────────────────────────┐  │
│  │                    GitHub Actions Workflow                      │  │
│  │                    (.github/workflows/build.yml)                │  │
│  └─────────────────────────────────────────────────────────────────┘  │
│                              │                                          │
│                              ▼                                          │
│          ┌─────────────────────────────────────────┐                   │
│          │  1. Checkout code (actions/checkout@v4)│                   │
│          └─────────────────────────────────────────┘                   │
│                              │                                          │
│                              ▼                                          │
│          ┌─────────────────────────────────────────┐                   │
│          │  2. Setup JDK 17 (actions/setup-java@v4)│                  │
│          └─────────────────────────────────────────┘                   │
│                              │                                          │
│                              ▼                                          │
│          ┌─────────────────────────────────────────┐                   │
│          │  3. Build & Test (mvn clean verify)    │                   │
│          │     ├── Compile code                    │                   │
│          │     ├── Run unit tests                  │                   │
│          │     ├── Run integration tests           │                   │
│          │     └── Generate JaCoCo report          │                   │
│          └─────────────────────────────────────────┘                   │
│                              │                                          │
│                   ┌──────────┴──────────┐                              │
│                   │                     │                              │
│                   ▼                     ▼                              │
│    ┌──────────────────────┐ ┌──────────────────────┐                  │
│    │  4a. Upload to       │ │  4b. SonarCloud      │                  │
│    │      SonarCloud      │ │      Analysis        │                  │
│    │      (mvn sonar:     │ │                      │                  │
│    │       sonar)         │ │  Uses SONAR_TOKEN    │                  │
│    └──────────────────────┘ └──────────────────────┘                  │
│                   │                     │                              │
│                   └──────────┬──────────┘                              │
│                              ▼                                          │
│          ┌─────────────────────────────────────────┐                   │
│          │  5. Upload Artifacts                    │                   │
│          │     ├── test-results                    │                   │
│          │     ├── jacoco reports                  │                   │
│          │     └── application JAR                 │                   │
│          └─────────────────────────────────────────┘                   │
└─────────────────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                         SONARCLOUD                                      │
│                                                                         │
│  ┌───────────────────────────────────────────────────────────────┐    │
│  │  Analysis Results:                                            │    │
│  │  ✅ Quality Gate                                              │    │
│  │  📊 Code Coverage: XX%                                        │    │
│  │  🐛 Bugs: X                                                   │    │
│  │  💩 Code Smells: X                                            │    │
│  │  🔒 Security Hotspots: X                                      │    │
│  │  📈 Technical Debt: Xh Xmin                                   │    │
│  └───────────────────────────────────────────────────────────────┘    │
│                                                                         │
│  URL: https://sonarcloud.io/project/overview?id=demo-devops           │
└─────────────────────────────────────────────────────────────────────────┘
                              │
                              ▼
┌─────────────────────────────────────────────────────────────────────────┐
│                      FEEDBACK AU DÉVELOPPEUR                            │
│                                                                         │
│  ✅ Build Status Badge sur README                                      │
│  ✅ Quality Gate Badge sur README                                      │
│  ✅ Coverage Badge sur README                                          │
│  ✅ Notifications par email (si configuré)                             │
│  ✅ Commentaires automatiques sur PR                                   │
└─────────────────────────────────────────────────────────────────────────┘
```

## 📋 Détails du workflow

### Étape 1: Checkout (✅ Automatique)
```yaml
- uses: actions/checkout@v4
  with:
    fetch-depth: 0  # Important pour SonarCloud
```

### Étape 2: Setup JDK (✅ Automatique)
```yaml
- uses: actions/setup-java@v4
  with:
    java-version: '17'
    distribution: 'temurin'
    cache: 'maven'
```

### Étape 3: Build & Test (✅ Automatique)
```bash
mvn clean verify
```
Exécute :
- Compilation
- Tests unitaires
- Tests d'intégration
- Génération rapport JaCoCo

### Étape 4: SonarCloud (✅ Automatique)
```bash
mvn sonar:sonar
```
Utilise le secret `SONAR_TOKEN` pour s'authentifier

### Étape 5: Upload Artifacts (✅ Automatique)
```yaml
- uses: actions/upload-artifact@v4
```
Sauvegarde les rapports et le JAR

## 🔑 Variables d'environnement

| Variable | Source | Utilisation |
|----------|--------|-------------|
| `SONAR_TOKEN` | GitHub Secrets | Authentification SonarCloud |
| `GITHUB_TOKEN` | GitHub (auto) | Accès API GitHub |

## 📊 Métriques collectées

### JaCoCo (Couverture de code)
- **Line Coverage**: % de lignes couvertes
- **Branch Coverage**: % de branches couvertes
- **Complexity**: Complexité cyclomatique

### SonarCloud (Qualité du code)
- **Bugs**: Erreurs potentielles
- **Vulnerabilities**: Failles de sécurité
- **Code Smells**: Mauvaises pratiques
- **Coverage**: Couverture de code
- **Duplications**: Code dupliqué
- **Technical Debt**: Dette technique estimée

## 🎯 Quality Gates SonarCloud

### Conditions par défaut:
- ✅ Coverage sur nouveau code > 80%
- ✅ Bugs = 0
- ✅ Vulnerabilities = 0
- ✅ Code Smells Rating ≤ A
- ✅ Security Hotspots reviewed = 100%
- ✅ Duplications sur nouveau code < 3%

## 🚀 Déclencheurs du workflow

```yaml
on:
  push:
    branches: [main, master, develop]
  pull_request:
    branches: [main, master, develop]
```

**Quand le workflow s'exécute:**
- ✅ À chaque push sur main/master/develop
- ✅ À chaque Pull Request vers ces branches
- ✅ Manuellement depuis l'interface GitHub Actions

## 📈 Temps d'exécution typique

```
┌─────────────────────┬──────────────┐
│ Étape               │ Durée        │
├─────────────────────┼──────────────┤
│ Checkout            │ ~10s         │
│ Setup JDK           │ ~20s         │
│ Build & Test        │ ~2-3 min     │
│ SonarCloud Analysis │ ~30-60s      │
│ Upload Artifacts    │ ~10-20s      │
├─────────────────────┼──────────────┤
│ TOTAL               │ ~4-5 min     │
└─────────────────────┴──────────────┘
```

## 🔄 Cycle de vie complet

```
Développeur écrit code
        ↓
Développeur commit + push
        ↓
GitHub détecte le push
        ↓
GitHub Actions démarre
        ↓
Compile + Teste
        ↓
Envoie à SonarCloud
        ↓
SonarCloud analyse
        ↓
Résultats disponibles
        ↓
Badges mis à jour
        ↓
Développeur voit les résultats
```

## ✨ Avantages de ce workflow

1. **Automatisation complète** : Aucune intervention manuelle
2. **Feedback rapide** : ~5 minutes après le push
3. **Qualité garantie** : Tests + analyse à chaque commit
4. **Historique** : Tous les résultats archivés
5. **Visibilité** : Badges sur le README
6. **Collaboration** : Commentaires automatiques sur PR

## 🎓 Bonnes pratiques appliquées

- ✅ Tests automatisés
- ✅ Analyse de code statique
- ✅ Mesure de couverture
- ✅ Quality gates
- ✅ Intégration continue
- ✅ Artifacts versionnés
- ✅ Documentation à jour

---

**Ce workflow représente les standards DevOps modernes !** 🚀

