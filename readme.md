# Spark in mode yarn-client with MapR



###MapR sandbox
Download, Install and run the MapR sandbox MapR 5.1 : http://doc.mapr.com/display/MapR/MapR+Sandbox+for+Hadoop

* The server name is ``maprdemo``
* The web manager on [http://maprdemo:8443](http://maprdemo:8443) mapr/mapr

###Usage
I will try the yarn client mode to connect Spark to the MapR cluster
* Configure the **yarn-site.xml** in the resources directory (to connect correctly to Yarn with Spark).
We need the following properties :
  * yarn.resourcemanager.scheduler.address
  * yarn.resourcemanager.principal
  * yarn.resourcemanager.resource-tracker.address
  * yarn.resourcemanager.address
  * yarn.resourcemanager.hostname

* Configure the **core-site.xml** in the resources directory (to connect correctly to HDFS with Spark). We nedd the folowwing properties :
  * fs.defaultFS

**_On a surement un bug ici, car mapr n'utilise pas hdfs comme le fait une distribution CDH, mais maprfs_**

* Compilation with ```mvn clean install```
* Copy the worker on the hadoop cluster and put it on HDFS
```
# From the current directory
#cp target/sparkYarn-1.0-SNAPSHOT-worker.jar /tmp/
scp -P 2222 target/sparkYarn-1.0-SNAPSHOT-worker.jar mapr@maprdemo:///tmp/
#scp -P 2222 mapr@maprdemo:///opt/mapr/spark/spark-1.5.2/lib/spark-assembly-1.5.2-mapr-1602-hadoop2.7.0-mapr-1602.jar /tmp/

ssh mapr@maprdemo -p 2222
# On the host maprdemo do
#cp /opt/mapr/spark/spark-1.5.2/lib/spark-assembly-1.5.2-mapr-1602-hadoop2.7.0-mapr-1602.jar /tmp/

hadoop dfs -mkdir -p /user/spark
hadoop dfs -put /opt/mapr/spark/spark-1.5.2/lib/spark-assembly-1.5.2-mapr-1602-hadoop2.7.0-mapr-1602.jar /user/spark/spark-assembly-1.5.2-mapr-1602-hadoop2.7.0-mapr-1602.jar
hadoop dfs -put /tmp/sparkYarn-1.0-SNAPSHOT-worker.jar /user/spark/sparkYarn-1.0-SNAPSHOT-worker.jar
hadoop dfs -chmod -R 777 /user/spark
hadoop dfs -ls /user/spark/
```

For CDH we run something like :
```
sudo -u spark hdfs dfs -mkdir -p /user/spark
sudo -u spark hdfs dfs -put /opt/mapr/spark/spark-1.5.2/lib/spark-assembly-1.5.2-mapr-1602-hadoop2.7.0-mapr-1602.jar /user/spark/spark-assembly-1.5.2-mapr-1602-hadoop2.7.0-mapr-1602.jar
sudo -u spark hdfs dfs -put /tmp/sparkYarn-1.0-SNAPSHOT-worker.jar /user/spark/sparkYarn-1.0-SNAPSHOT-worker.jar
sudo -u spark hdfs dfs -chmod -R 777 /user/spark
sudo -u spark hdfs dfs -ls /user/spark/
```

* Check the configuration in the Test.java, specially the following parameters for the sparkConfiguration
  * spark.yarn.dist.files
  * spark.yarn.jar
  * spark.yarn.am.extraLibraryPath

* Launch the Test Java application (I don't want to use the spark-submit process)




