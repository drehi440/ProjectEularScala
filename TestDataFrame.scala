spark-shell --packages com.databricks:spark-csv_2.10:1.1.0 --queue root.etl --conf spark.ui.port=4052 

import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.expressions._
import org.apache.spark.sql.functions._

import org.apache.spark.sql.expressions.Window

import sqlContext.implicits._

val df = sqlContext.read.format("com.databricks.spark.csv").option("header", "true").option("delimiter", "\t").load("/user/rede7001/test.csv")

df.printSchema()

val dfOriginal = sqlContext.read.format("com.databricks.spark.csv").option("header", "true").option("delimiter", "\t").load("/user/rede7001/originalODI.csv")
dfOriginal.printSchema

1. What is India’s total Win/Loss/Tie percentage?


val df2 = dfOriginal.select($"Team 1",$"Team 2", $"Winner")

val totalIndia=df2.select(
when((lower($"Team 1")==="india" || lower($"Team 2")==="india") && lower($"Winner")==="india","Winner").
when((lower($"Team 1")==="india" || lower($"Team 2")==="india") && not(lower($"Winner") isin ("india","no result")),"Lose").
when((lower($"Team 1")==="india" || lower($"Team 2")==="india") && lower($"Winner")==="no result","Tie")
.alias("Win-Loss-Tie")
).filter($"Win-Loss-Tie".isNotNull)


val india = totalIndia.
withColumn("totalCount", lit(totalIndia.count())).
withColumn("totalWinCount",lit(totalIndia.filter(lower($"Win-Loss-Tie")==="winner").count())).
withColumn("totalLossCount",lit(totalIndia.filter(lower($"Win-Loss-Tie")==="lose").count())).
withColumn("totalTieCount",lit(totalIndia.filter(lower($"Win-Loss-Tie")==="tie").count())).
show()

val indiapercentage=totalIndia.
groupBy($"Win-Loss-Tie").
agg(count("*").alias("individual")).withColumn("percentage", $"individual".multiply(100).cast("integer")/  lit(totalIndia.count())).show()



What is India’s Win/Loss/Tie percentage in away and home matches?

Home Win/Loss/Tie

val IndiaHomeWin = totalWinIndia.where($"Host_Country" === "India")
val IndiaHomeWinCount = IndiaHomeWin.count
IndiaHomeWin: Long = 171

val IndiaHomeLoss = totalLoss.where($"Host_Country" === "India")
val IndiaHomeLossCount = IndiaHomeLoss.count
IndiaHomeLossCount: Long = 114

val IndiaHomeTie = totalTie.where($"Host_Country" === "India")
val IndiaHomeTieCount = IndiaHomeTie.count
IndiaHomeTieCount: Long = 8


println(s"Win Percentage of India in Home Matches = ${IndiaHomeWinCount * 100f/totalMatchIndiaCount} %\nLoss Percentage of India in Home Matches = ${IndiaHomeLossCount * 100f/totalMatchIndiaCount} %\nTie Percentage of India in Home Matches = ${IndiaHomeTieCount *100f/totalMatchIndiaCount} %")

Win Percentage of India in Home Matches = 18.387096 %
Loss Percentage of India in Home Matches = 12.258064 %
Tie Percentage of India in Home Matches = 0.86021507 %

Away Win/Loss/Tie:

val IndiaAwayWin = totalWinIndia.where(not($"Host_Country" === "India"))
val IndiaAwayWinCount = IndiaAwayWin.count
IndiaAwayWinCount: Long = 261

val IndiaAwayLoss = totalLoss.where(not($"Host_Country" === "India"))
val IndiaAwayLossCount = IndiaAwayLoss.count
IndiaAwayLossCount: Long = 270

val IndiaAwayTie = totalTie.where(not($"Host_Country" === "India"))
val IndiaAwayTieCount = IndiaAwayTie.count
IndiaAwayTieCount: Long = 19


println(s"Win Percentage of India in Away Matches = ${IndiaAwayWinCount * 100f/totalMatchIndiaCount} %\nLoss Percentage of India in Away Matches = ${IndiaAwayLossCount * 100f/totalMatchIndiaCount} %\nTie Percentage of India in Away Matches = ${IndiaAwayTieCount *100f/totalMatchIndiaCount} %")


Win Percentage of India in Away Matches = 28.064516 %
Loss Percentage of India in Away Matches = 29.032259 %
Tie Percentage of India in Away Matches = 2.0430107 %


How many matches has India played against different ICC teams?


val totalMatchAgainsts = iccWithHost.where($"Team 1" === "India").where(not($"Team 2" === "India")).distinct.count




How many matches India has won or lost against different teams?















Questions: 

1. What is the 2nd most delayed route of the flights operated by ‘Pacific Airways’
2. What carrier has flown the 3rd most number of flights? How many? 
3. What airport has the 10th most delays? 
4. What is the second most popular day of the week to travel? Why? 
5. What is the 10th most flown route?
6. What other actionable insights can we gain by leveraging the TranStats dataset?   

				Pyspark

from pyspark.sql import functions as F
from pyspark.sql.window import Window as W

monthlydata=sqlContext.table("monthlydata")
lookupdata=sqlContext.table("lookupdata")

1st=>

monthlydata1=monthlydata.join(lookupdata,F.col("carrier")==F.col("code")).filter(F.lower(F.col("description"))=="pacific airways")

monthlydata2=monthlydata1.groupBy("origin","dest").agg(F.sum("ARR_DELAY").cast("int").alias("totaldelay"))

window=W.partitionBy(F.col("origin"),F.col("dest")).orderBy(F.col("totaldelay").desc())

monthlydata3=monthlydata2.select(F.col("origin"),F.col("dest"),F.row_number().over(window).alias("rowid"),"totaldelay")

monthlydata3.filter(F.col("rowid")==2)

2nd=>

monthlydata2=monthlydata.groupBy(F.col("carrier")).agg(F.count(F.lit(1)).alias("totalflights"))

window=W.partitionBy(F.col("carrier")).orderBy(F.col("totalflights").desc())

monthlydata3=monthlydata2.select(F.col("carrier"),F.row_number().over(window).alias("rowid"),"totalflights")

monthlydata3.filter(F.col("rowid")==3).show()

3rd =>

monthlydata1=monthlydata.select("ORIGIN_AIRPORT_ID","ORIGIN",F.col("DEP_DELAY_NEW").cast("long")).filter(F.col("DEP_DELAY_NEW")>0)
monthlydata2=monthlydata1.groupBy(F.col("ORIGIN_AIRPORT_ID"),F.col("ORIGIN")).agg(F.count(F.col("DEP_DELAY_NEW")).alias("Total_DEP_DELAY"))

window=W.partitionBy(F.col("ORIGIN_AIRPORT_ID"),F.col("ORIGIN")).orderBy(F.col("Total_DEP_DELAY").desc())

monthlydata3=monthlydata2.select(F.col("ORIGIN_AIRPORT_ID"),F.col("ORIGIN"),F.row_number().over(window).alias("rowid"))

monthlydata3.filter(F.col("rowid")==10).show()



4th =>


monthlydata1=monthlydata.select(F.date_format(F.from_unixtime(F.unix_timestamp('FL_DATE', 'yyyymmdd')),'E').alias('dayofweek'))
monthlydata2=monthlydata1.groupBy(F.col("dayofweek")).agg(F.count(F.lit(1)).alias("totalflights"))

window=W.partitionBy(F.col("dayofweek")).orderBy(F.col("totalflights").desc())

monthlydata3=monthlydata2.select(F.col("dayofweek"),F.row_number().over(window).alias("rowid"),"totalflights")

monthlydata3.filter(F.col("rowid")==2).show()



5th=>
monthlydata2=monthlydata.groupBy("origin","dest").agg(F.count(F.lit(1)).alias("totalflights"))

window=W.partitionBy(F.col("origin"),F.col("dest")).orderBy(F.col("totalflights").desc())

monthlydata3=monthlydata2.select(F.col("origin"),F.col("dest"),F.row_number().over(window).alias("rowid"),"totalflights")

monthlydata3.filter(F.col("rowid")==10)



6th =>

We can get the insights of the airline with most no of cancelled,emergency landing,delayed departure flights.
