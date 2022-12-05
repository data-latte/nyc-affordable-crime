# Affordable Housing effects on NYC Residents

## `/ana_code`
**File Listing**
```
└── ana_code/
    └──ana_code.zip
        ├──analytic.py
        ├──analytic_output
```
**To run the analytic located at ana_code/analytic.py**
  - Make sure "clean_data.csv" and "final_sum.csv" and "analytic.py" are in your home directory
  - Run the commands `hdfs dfs -put clean_data.csv` and `hdfs dfs -put final_sum.csv` to to upload to hdfs. 
  - Then do the command spark-submit analytic.py
  - If this does not work use `pyspark --deploy-mode client` and then type in commands that are in analytic_shell_commands.txt
  - This will print out our analytic.

## `/data_ingest`
**File Listing**
```
└── data_ingest/
    ├── commands.txt
    ├── NYPD_Arrest_Data_Historical.csv
    └── Original_Housint_data.csv
```

## `/etl_code/mja610`
**File Listing**
```
└── mja610/
    ├── part_1 (mapreduce)/
    │   ├── Clean.java
    │   ├── CleanMapper.java
    │   ├── CleanReducer.java
    │   ├── Dated_Homes.csv
    │   └── Original_Data.csv
    ├── part_2 (scala)/
    │   ├── Add_ZipCode.scala
    │   └── Dated_Homes_Precinct.csv
    └── part_3 (mapreduce dupes)/
        ├── Clean_Final.java
        ├── CleanMapper_Final.java
        ├── CLeanReducer_Final.java
        └── final.csv
```
**Part_1 Instructions**

    javac -classpath `yarn classpath` -d . CleanMapper.java
    javac -classpath `yarn classpath` -d . CleanReducer.java
    javac -classpath `yarn classpath`:. -d . Clean.java
    jar -cvf Clean.jar *.class
    hadoop jar Clean.jar Clean /user/mja610/final/input/Original_Data.csv /user/mja610/final/output
    hdfs dfs -copyToLocal final/output

**Part_2 Instructions**
  - Upload `Add_ZipCode.scala`, `Dated_Homes.csv`, and `zipcode_precinct.csv`and to your home directory 
  - To run a profile on the dataset
  - Launch spark using the command `spark-shell --deploy-mode client`
  - Run `:load Add_ZipCode.scala`

**Part_3 Instructions**

    javac -classpath `yarn classpath` -d . CleanMapper_Final.java
    javac -classpath `yarn classpath` -d . CleanReducer_Final.java
    javac -classpath `yarn classpath`:. -d . Clean_Final.java
    jar -cvf Clean_Final.jar *.class
    hadoop jar Clean_Final.jar Clean_Final /user/mja610/final_new/input/Dated_Homes_Precinct.csv /user/mja610/final_new/output
    hdfs dfs -copyToLocal final_new/output
    
**Part_4 Instructions**

  - Upload `final_sum.scala`, `final.csv`, and ` to your home directory 
  - To run a profile on the dataset
  - Launch spark using the command `spark-shell --deploy-mode client`
  - Run `:load final_sum.scala`

## `/etl_code/ilh239`
**File Listing**
```
└── ilh239/
    ├── clean_arrest_data.csv
    ├── clean_arrest_data.scala
    ├── Copy of NYPD_Arrests_Data_Historic.csv
    └── NYPD_Arrests_Data_Historic.csv
```

**To clean the input data which is located at `"/user/ilh239/FINAL_PROJECT/NYPD_Arrests_Data_Historic.csv"`**
  - Upload `clean_arrest_data.scala` to your home directory.
  - Open spark with  `spark-shell --deploy-mode client`
  - Run `:load clean_arrest_data.scala`
  - Download the cleaned dataset to your home directory with `hadoop fs -get clean_data.csv`

## `/profiling_code/mja610`
**File Listing**
```
└── mja610/
    ├── Profiling.scala
    └── final.csv
```
**To run profiling located at `"/user/mja610/project_scala"`**
  - Upload `Profiling.scala` and `final.csv` to your home directory 
  - To run a profile on the dataset.
  - Launch spark using the command `spark-shell --deploy-mode client`.
  - Run `:load Profiling.scala`.
  - This will print out different insights.

## `/profiling_code/ilh239`
**File Listing**
```
└── ilh239/
    ├── profile_arrest_data.scala
    └── clean_arrest_data.csv
```
**To run profiling**
  - Upload `profile_arrest_data.scala` and `clean_data.csv` to your home directory
  - Upload `clean_data.csv` to HDFS with `hdfs dfs -put clean_data.csv`
  - To run a profile on the dataset.
  - Launch spark using the command `spark-shell --deploy-mode client`.
  - Run `:load profile_arrest_data.scala`.
  - This will print out different insights about the dataset such as the mean, max, and stddev. 
    
## `/screenshots`
**File Listing**
```
└── screenshots/
    ├──Analytic_Screenshot.png
```