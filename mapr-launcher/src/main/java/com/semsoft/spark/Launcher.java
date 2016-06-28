package com.semsoft.spark;

import org.apache.hadoop.conf.Configuration;
import org.apache.spark.SparkConf;
import org.apache.spark.deploy.yarn.Client;
import org.apache.spark.deploy.yarn.ClientArguments;

public class Launcher {

	public static void main(String[] arguments) throws Exception {

		// prepare arguments to be passed to
		// org.apache.spark.deploy.yarn.Client object
		String[] args = new String[] {
				// the name of your application
				"--name", "Aggrego Test",

				// memory for driver (optional)
				// "--driver-memory", "1000M",

				// name of your application's main class (required)
				"--class", "com.semsoft.spark.Test2",

				// comma separated list of local jars that want
				// SparkContext.addJar to work with
				"--addJars", "local:/home/mapr/spark-assembly-1.5.2-mapr-1512-hadoop2.7.0-mapr-1509.jar",

				// argument 1 to your Spark program (SparkFriendRecommendation)
				// "--arg", "3",

				// argument 2 to your Spark program (SparkFriendRecommendation)
				// "--arg", "/friends/input",

				// argument 3 to your Spark program (SparkFriendRecommendation)
				// "--arg", "/friends/output",

				// argument 4 to your Spark program (SparkFriendRecommendation)
				// this is a helper argument to create a proper JavaSparkContext
				// object
				// make sure that you create the following in
				// SparkFriendRecommendation program
				// ctx = new JavaSparkContext("yarn-cluster",
				// "SparkFriendRecommendation");
				// "--arg", "yarn-cluster"

				// path to your application's JAR file
				// required in yarn-cluster mode
				"--jar", // "local:/hellofresh/Platine/SparkMapRBoris/mapr/target/sparkYarnMapR-1.0-SNAPSHOT-all.jar"
				"hdfs://mapr-dev/input/sparkYarnMapR-1.0-SNAPSHOT.jar" };

		// create a Hadoop Configuration object
		Configuration config = new Configuration();

		// identify that you will be using Spark as YARN mode
		System.setProperty("SPARK_YARN_MODE", "true");
		// System.setProperty("HADOOP_CONF_DIR",
		// "C:\\shared\\projects\\Astria\\Platine\\SparkMapRBoris\\mapr\\src\\main\\resources");

		// create an instance of SparkConf object
		SparkConf sparkConf = new SparkConf();

		// create ClientArguments, which will be passed to Client
		ClientArguments cArgs = new ClientArguments(args, sparkConf);

		// create an instance of yarn Client client
		Client client = new Client(cArgs, config, sparkConf);

		// submit Spark job to YARN
		try {
			client.run();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
