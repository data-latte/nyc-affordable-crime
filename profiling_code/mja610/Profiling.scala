// import csv
val housing_df = spark.read.format("csv").option("header", "true").load("final_scala/final_sum.csv");

// summary of dataset
housing_df.summary().show(false);

// look into affordable units
housing_df.describe("AFFORDABLE_UNITS").show(false);