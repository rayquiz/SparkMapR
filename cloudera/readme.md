#Spark on Cloudera


### Installation
* Installation de VirtualBox
* Téléchargement de CDH sandbox [http://www.cloudera.com/downloads/quickstart_vms/5-7.html]()
* Importation de la VM dans VirtualBox (en remettant à zéro les adresses Mac - Mettre au moins 8Go de RAM et 2 CPUs)
* Lancement de la VM, puis dans la VM : "Launch Cloudera Express"
* Configurer le /etc/hosts de la machine parente pour relier quickstart.cloudera à 127.0.0.1 (puis sudo service networking restart)

Actuellement je suis :
CDH 5.7.0 et spark est en version 1.6.0+cdh5.7.0+180



### Usage

* Aller sur la page [http://quickstart.cloudera:7180]() et se connecter (cloudera/cloudera)
* Aller dans Clusters / YARN (MR2 Included) puis dans Actions / Télécharger la configuration cliente
* Extraire sont contenu dans le répertoire src/main/resources
* Compiler le projet avec Maven ```mvn clean install```
* Copie du worker précédemment compilé surle cluster Hadoop et dans HDFS.
  * Comme je n'arrive pas à me connecter en SSH sur la sandbox, je le fais via hue [http://quickstart.cloudera:8888]() (Rq, ne pas oublier de donner les droits à tout le monde).
  * Le spark assembly est dans le répertoire (dans la sandbox): ```/usr/lib/spark/lib/spark-assembly.jar```
```
sudo -u spark hdfs dfs -mkdir -p /user/spark
sudo -u spark hdfs dfs -put /opt/cloudera/parcels/CDH/lib/spark/lib/spark-assembly.jar /user/spark/spark-assembly.jar
sudo -u spark hdfs dfs -put /tmp/sparkYarn-1.0-SNAPSHOT-worker.jar /user/spark/sparkYarn-1.0-SNAPSHOT-worker.jar
sudo -u spark hdfs dfs -chmod -R 777 /user/spark
sudo -u spark hdfs dfs -ls /user/spark/
```


