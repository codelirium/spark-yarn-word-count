#!/usr/bin/env bash


IP="10.250.250.90"
SPARK_HOME=/opt/mapr/spark/spark-2.1.0-mapr
JAR=../../../../spark-yarn-word-count-drivers/target/spark-yarn-word-count-drivers.jar

$SPARK_HOME/bin/spark-submit \
						--master yarn \
						--deploy-mode client \
						--queue spark \
						--num-executors  1 \
						--executor-cores 1 \
						--conf "spark.driver.host=$IP" \
						--conf "spark.driver.memory=2g" \
						--conf "spark.executor.memory=2g" \
						--class io.codelirium.examples.spark.yarn.driver.JavaWordCount \
						--jars $JAR $JAR \
						/user/codelirium/words.txt
