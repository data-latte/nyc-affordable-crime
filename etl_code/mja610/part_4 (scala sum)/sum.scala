// import
var housing_df = spark.read.format("csv").option("header", "true").load("final_scala/final.csv")

// convert column to int
housing_df = housing_df.withColumn("AFFORDABLE_UNITS",col("AFFORDABLE_UNITS").cast("int"))

// sum rows by precinct preserving dates
import org.apache.spark.sql.expressions.Window
import org.apache.spark.sql.functions._
val w = Window.partitionBy($"POLICE_PRECINCT").orderBy($"POLICE_PRECINCT", $"DATE").rowsBetween(Window.unboundedPreceding, Window.currentRow)
val new_df = housing_df.withColumn("AFFORDABLE_UNITS_SUM", sum($"AFFORDABLE_UNITS").over(w))

// order by precinct, date
new_df.orderBy("POLICE_PRECINCT", "DATE").show
val housing_df = new_df.orderBy("POLICE_PRECINCT", "DATE")

// drop old column
housing_df = housing_df.drop("AFFORDABLE_UNITS")

// save to csv w/ headers
housing_df.write.option("header",true).csv("/user/mja610/final_scala/final_sum")
