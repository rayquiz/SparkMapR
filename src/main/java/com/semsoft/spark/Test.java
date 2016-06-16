package com.semsoft.spark;

import org.apache.spark.SparkConf;
import org.apache.spark.SparkContext;
import org.apache.spark.api.java.JavaSparkContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.List;

/**
 * Test pour SPARK sur Yarn
 * <p>
 * <p>
 * Idées pour débloquer :
 * - Utiliser SparkSubmit : https://github.com/cloudera/oozie/blob/cdh5.5.0-release/sharelib/spark/src/main/java/org.apache.oozie.action.hadoop/SparkMain.java
 * <p>
 * Created by breynard on 11/12/15.
 */
public class Test {
    private static final Logger LOGGER = LoggerFactory.getLogger(Test.class);


    public static void main(String[] args) {
        LOGGER.info("Démarrage");
        try (JavaSparkContext javaSparkContext = modeMapR()) {
            List<String> data = Arrays.asList("a", "b", "c", "d");
            long collect = javaSparkContext.parallelize(data).count();
            LOGGER.info("----------------\n\n\n\n\n\n\n\n\n\n\n\n\nResult {}.", collect);

        } catch (Throwable t) {
            LOGGER.error("Error ", t);
        }
        System.exit(0);
    }

    private static JavaSparkContext modeMapR() {
        SparkConf sparkConf = new SparkConf();
        sparkConf.setAppName("Aggrego Test")
                .setMaster("yarn-client")

                .set("spark.executor.memory", "512M")
                .set("spark.yarn.maxAppAttempts", "10")


                .set("spark.driver.log.level", "INFO")
                .set("spark.eventLog.dir", "hdfs:///user/spark/")
                .set("spark.eventLog.enabled", "true");


        SparkContext sparkContext = new SparkContext(sparkConf);
        return new JavaSparkContext(sparkContext);
    }
}
