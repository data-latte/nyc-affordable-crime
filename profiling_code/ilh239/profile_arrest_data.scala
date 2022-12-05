val df1  = spark.read.option("header",true).csv("/user/ilh239/clean_data.csv")
df1.summary().show()
