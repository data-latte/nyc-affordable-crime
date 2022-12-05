// import csv
var housing_df = spark.read.format("csv").option("header", "true").load("final_scala/Dated_Homes.csv");
var precint_df = spark.read.format("csv").option("header", "true").load("final_scala/zipcode_precinct.csv");

// join based on zip code to add corresponding precinct
housing_df.join(precint_df, Seq("ZIP_CODE")).show
housing_df = housing_df.join(precint_df, Seq("ZIP_CODE"))

// drop zip code as it's no longer needed
housing_df.drop("ZIP_CODE").show
housing_df = housing_df.drop("ZIP_CODE")

// rearrange columns for readability
housing_df.select("POLICE_PRECINCT","DATE","AFFORDABLE_UNITS").show
housing_df = housing_df.select("POLICE_PRECINCT","DATE","AFFORDABLE_UNITS")

// save to csv w/ headers
housing_df.write.option("header",true).csv("/user/mja610/final_scala/Dated_Homes_Precinct")