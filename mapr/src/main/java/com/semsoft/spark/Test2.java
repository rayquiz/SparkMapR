package com.semsoft.spark;

import java.util.Arrays;
import java.util.List;

import org.apache.spark.api.java.JavaSparkContext;

/**
 * Test pour SPARK sur Yarn
 */
public class Test2 {
	public static void main(String[] args) {

		// voir
		// https://github.com/mahmoudparsian/data-algorithms-book/blob/master/src/main/java/org/dataalgorithms/bonus/friendrecommendation/spark/SparkFriendRecommendation.java
		// l78 pour l'explication du 'yarn-cluster'
		try (JavaSparkContext context = new JavaSparkContext("yarn-cluster", "Aggrego Test")) {
			List<String> data = Arrays.asList("a", "b", "c", "d");
			long collect = context.parallelize(data).count();
			System.out.println("Nombre de lignes: " + collect);
		}

	}

}
