//This loads the data into a dataframe
val df1  = spark.read.option("header",true).csv("/user/ilh239/FINAL_PROJECT/NYPD_Arrests_Data_Historic.csv")
//This selects only the columns with the Arrest Date as well as the arrest precinct.
val df2 = df1.select("ARREST_DATE", "ARREST_PRECINCT").na.drop().withColumn("rowId1", monotonically_increasing_id())
//We now need to convert the dates to be the correct format. MM/yyyy:
//This will make it so we can run mapreduce on the data set. 
//And we get the number of crimes in each precinct per month. 

//We will first create a dataframe which has each arrest date but converted to date format. 
val df3 = df2.select(col("ARREST_DATE"), to_date(col("ARREST_DATE"),"MM/dd/yyyy").as("to_date"))


//We will then create a dataset which converts this to MM/yyyy format from the date format.
val df4 = df3.select(
    col("ARREST_DATE").as("ARREST_DATE"),
    date_format(col("to_date"),"yyyy MM").as("new_arrest_date")
  ).withColumn("rowId2", monotonically_increasing_id())

//We will then merge the two dataframes on their ids
val df5 = df2.as("df1").join(df4.as("df2"), df2("rowId1") === df4("rowId2"), "inner").select("ARREST_PRECINCT", "new_arrest_date")


//Finally we will use the groupby function to get the count of crimes for each police district in each month. 
val df6 = df5.groupBy("ARREST_PRECINCT","new_arrest_date").count()

//Finally we will download the dataset as a csv
//this will download as a csv with all of the data in one file. 
df6.coalesce(1).write.csv("clean_data.csv")


