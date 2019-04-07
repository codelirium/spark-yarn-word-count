package io.codelirium.examples.spark.yarn.driver;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import scala.Tuple2;

import static java.lang.System.*;
import static java.util.Arrays.asList;
import static java.util.regex.Pattern.compile;


public class JavaWordCount {

	private void simpleWordCount(final JavaSparkContext sparkContext, final String filename) {

		final JavaRDD<String> lines = sparkContext.textFile(filename, 1);

		final JavaRDD<String> words = lines.flatMap((FlatMapFunction<String, String>) s -> asList(compile(" ").split(s)).iterator());

		final JavaPairRDD<String, Integer> ones = words.mapToPair((PairFunction<String, String, Integer>) s -> new Tuple2<>(s, 1));

		final JavaPairRDD<String, Integer> counts = ones.reduceByKey((Function2<Integer, Integer, Integer>) Integer::sum);


		counts.collect().forEach(tuple -> out.printf("%s: %d%n", tuple._1(), tuple._2()));


		sparkContext.stop();
	}


	public static void main(final String ... args) {

		if (args.length != 1) {

			err.printf("Syntax: %s <file>%n", JavaWordCount.class.getName());

			exit(1);
		}


		final SparkConf sparkConf = new SparkConf();

		sparkConf.setAppName(JavaWordCount.class.getSimpleName());


		new JavaWordCount().simpleWordCount(new JavaSparkContext(sparkConf), args[0]);
	}
}
