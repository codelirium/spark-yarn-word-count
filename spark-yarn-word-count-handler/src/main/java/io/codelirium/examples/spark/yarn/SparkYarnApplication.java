package io.codelirium.examples.spark.yarn;

import io.codelirium.examples.spark.yarn.launcher.impl.JavaWordCountSparkLauncher;
import org.apache.spark.launcher.SparkAppHandle;
import org.slf4j.Logger;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import javax.inject.Inject;

import static java.lang.Boolean.FALSE;
import static org.slf4j.LoggerFactory.getLogger;
import static org.springframework.boot.Banner.Mode.OFF;


@SpringBootApplication
public class SparkYarnApplication implements CommandLineRunner {

	private static final Logger LOGGER = getLogger(SparkYarnApplication.class);


	@Inject
	private JavaWordCountSparkLauncher wordCountSparkLauncher;


	public static void main(final String ... args) {

		new SpringApplicationBuilder(SparkYarnApplication.class)
				.logStartupInfo(FALSE)
				.bannerMode(OFF)
				.run(args);

	}


	@Override
	public void run(final String ... args) throws Exception {

		LOGGER.debug("Invoking launcher ...");

		final SparkAppHandle handle = wordCountSparkLauncher.launch(SparkYarnApplication.class.getName());

		while(!handle.getState().isFinal());
	}
}
