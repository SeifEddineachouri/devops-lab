# Configuration Mockito pour Java 21+

## Problème
Avec les versions récentes de Java (21+), Mockito affiche des avertissements concernant le chargement dynamique d'agents :
```
WARNING: A Java agent has been loaded dynamically
WARNING: Dynamic loading of agents will be disallowed by default in a future release
```

## Solution implémentée

### 1. Configuration Maven (pom.xml)
- Ajout de la propriété `argLine` dans les properties :
  ```xml
  <argLine>-XX:+EnableDynamicAgentLoading</argLine>
  ```

- Configuration du plugin maven-surefire-plugin :
  ```xml
  <plugin>
      <groupId>org.apache.maven.plugins</groupId>
      <artifactId>maven-surefire-plugin</artifactId>
      <configuration>
          <argLine>@{argLine} -XX:+EnableDynamicAgentLoading</argLine>
      </configuration>
  </plugin>
  ```

### 2. Configuration Maven Wrapper (.mvn/jvm.config)
Le fichier `.mvn/jvm.config` contient :
```
-XX:+EnableDynamicAgentLoading
```

Cette configuration permet à Maven d'utiliser automatiquement cet argument JVM.

## Exécution des tests

### Via Maven
```bash
./mvnw test
```

### Via IntelliJ IDEA
Les tests devraient maintenant s'exécuter sans avertissements grâce aux configurations ci-dessus.

Si vous voyez encore des avertissements dans IntelliJ, ajoutez manuellement l'argument JVM :
1. Run → Edit Configurations
2. Sélectionnez votre configuration de test
3. Dans "VM options", ajoutez : `-XX:+EnableDynamicAgentLoading`

## Références
- [Documentation Mockito sur les agents Java](https://javadoc.io/doc/org.mockito/mockito-core/latest/org.mockito/org/mockito/Mockito.html)
- [JEP 451: Prepare to Disallow the Dynamic Loading of Agents](https://openjdk.org/jeps/451)

