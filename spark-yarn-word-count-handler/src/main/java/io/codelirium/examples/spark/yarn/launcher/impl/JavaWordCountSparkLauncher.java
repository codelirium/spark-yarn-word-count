package io.codelirium.examples.spark.yarn.launcher.impl;

import io.codelirium.examples.spark.yarn.SparkYarnApplication;
import io.codelirium.examples.spark.yarn.configuration.SparkConfiguration;
import io.codelirium.examples.spark.yarn.driver.JavaWordCount;
import io.codelirium.examples.spark.yarn.launcher.SparkLaunchable;
import io.codelirium.examples.spark.yarn.listerner.LauncherStateListener;
import org.apache.spark.launcher.SparkAppHandle;
import org.apache.spark.launcher.SparkLauncher;
import org.springframework.stereotype.Component;
import javax.inject.Inject;
import java.io.IOException;

import static io.codelirium.examples.spark.yarn.configuration.SparkConfiguration.*;
import static io.codelirium.examples.spark.yarn.configuration.SparkConfiguration.SPARK_MASTER_VALUE;
import static org.apache.spark.launcher.SparkLauncher.DRIVER_MEMORY;


@Component
public class JavaWordCountSparkLauncher implements SparkLaunchable {

	@Inject
	private SparkConfiguration sparkConfiguration;


	@Override
	public SparkAppHandle launch(final String logger) throws IOException {

		return new SparkLauncher()
						.setAppName(JavaWordCount.class.getSimpleName())
						.setSparkHome(sparkConfiguration.getHomeDirectory())
						.setMaster(SPARK_MASTER_VALUE)
						.setDeployMode(SPARK_DEPLOY_MODE_VALUE)
						.setMainClass(JavaWordCount.class.getName())
						.setAppResource(sparkConfiguration.getAppResource())
						.setConf(DRIVER_MEMORY, sparkConfiguration.getDriverMemory())
						.setConf(SPARK_YARN_QUEUE, sparkConfiguration.getYarnQueueName())
						.addAppArgs("/user/codelirium/words.txt")
						.redirectToLog(SparkYarnApplication.class.getName())
						.startApplication(new LauncherStateListener());

	}
}
