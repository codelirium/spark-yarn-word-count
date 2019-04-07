package io.codelirium.examples.spark.yarn.listerner;

import org.apache.spark.launcher.SparkAppHandle;
import org.apache.spark.launcher.SparkAppHandle.Listener;
import org.apache.spark.launcher.SparkAppHandle.State;
import org.slf4j.Logger;

import static java.lang.String.format;
import static java.util.Objects.nonNull;
import static org.slf4j.LoggerFactory.getLogger;


public class LauncherStateListener implements Listener {

	private static final Logger LOGGER = getLogger(LauncherStateListener.class);


	@Override
	public void stateChanged(final SparkAppHandle handle) {

		final String sparkAppId = handle.getAppId();

		final State appState = handle.getState();


		if (nonNull(sparkAppId)) {

			LOGGER.debug(format("Spark job with app id: %s - state changed to: %s", sparkAppId, appState));

		} else {

			LOGGER.debug(format("Spark job's state changed to: %s", appState));

		}
	}


	@Override
	public void infoChanged(final SparkAppHandle handle) { }
}
