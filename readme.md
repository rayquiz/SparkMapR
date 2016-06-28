#Connection à Hadoop via Spark via yarn client

Pré requis:
- une VM nommée `mapr-dev` avec un cluster hadoop / spark installé (utilisation de la VM Vagrant `hellofresh/mapr`)`
  - Note: Cette VM n'est pas en connection **NAT** mais **Private Host Network**, ceci peut avoir un impact sur la communication inter-vm 


Etapes:
- builder les projet `sparkYarnMapRLauncher` et `sparkYarnMapR`
- déposer le jar `sparkYarnMapR-1.0-SNAPSHOT.jar` dans le cluster hadoop dans le dossier `/mapr/<cluster-name>/input`
- déposer le jar `sparkYarnMapRLauncher-1.0-SNAPSHOT-all.jar` dans un dossier local de la VM contenant le cluster 
- Vérifier que le fichier suivant est bien présent sur la machine: `/home/mapr/spark-assembly-1.5.2-mapr-1512-hadoop2.7.0-mapr-1509.jar`
- lancer la commande `java -cp sparkYarnMapRLauncher-1.0-SNAPSHOT-all.jar com.semsoft.spark.Launcher`

Notes techniques:
Le fichier `/etc/hosts` doit contenir l'hôte courant:

```bash
127.0.0.1 localhost
10.10.10.30 mapr-dev
```