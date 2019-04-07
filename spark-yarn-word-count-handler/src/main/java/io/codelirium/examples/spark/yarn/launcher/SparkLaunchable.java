package io.codelirium.examples.spark.yarn.launcher;

import org.apache.spark.launcher.SparkAppHandle;
import java.io.IOException;


public interface SparkLaunchable {

	String SPARK_YARN_QUEUE = "spark.yarn.queue";


	SparkAppHandle launch(final String logger) throws IOException;

}
