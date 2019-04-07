package io.codelirium.examples.spark.yarn.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;


@Data
@Configuration
public class SparkConfiguration {

	public static final String SPARK_MASTER_VALUE = "yarn";

	public static final String SPARK_DEPLOY_MODE_VALUE = "client";


	@Value("${spark.driver.memory}")
	private String driverMemory;

	@Value("${spark.yarn.queue.name}")
	private String yarnQueueName;

	@Value("${spark.home.directory}")
	private String homeDirectory;

	@Value("${spark.app.resource}")
	private String appResource;

}
