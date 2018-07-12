spark-shell --packages com.databricks:spark-csv_2.10:1.1.0 --queue root.etl - --conf spark.ui.port=4052 

import org.apache.spark.sql.SQLContext
import org.apache.spark.sql.expressions._
import org.apache.spark.sql.functions._

import org.apache.spark.sql.expressions.Window

import sqlContext.implicits._

val df = sqlContext.read.format("com.databricks.spark.csv").option("header", "true").option("delimiter", "\t").load("/user/rede7001/test.csv")

df.printSchema()

val dfOriginal = sqlContext.read.format("com.databricks.spark.csv").option("header", "true").option("delimiter", "\t").load("/user/rede7001/originalODI.csv")
dfOriginal.printSchema



val totalMatchIndia = dfOriginal.where(lower($"Team 1") === "india" || lower($"Team 2") === "india")

val iccWithHost = totalMatchIndia.as("t1").join(df.select($"Ground", $"Host_Country" ).distinct.as("t2"), $"t1.Ground" === $"t2.Ground","left").join(df.select($))select($"t1.*",$"t2.Host_Country")

1. What is India’s total Win/Loss/Tie percentage?

val totalMatchIndiaCount = iccWithHost.count
totalMatchIndiaCount: Long = 930

val totalWinIndia = iccWithHost.where(lower($"Winner") === "india")
val totalWinIndiaCount = totalWinIndia.count
totalWinIndia: Int = 476


val totalLoss = iccWithHost.where(not((lower($"winner") === "india" || lower($"winner") === "no result" )))
val totalLossCount = totalLoss.count.toInt
totalLoss: Int = 414

val totalTie = iccWithHost.where(lower($"Winner") === "no result")
val totalTieCount = totalTie.count
totalTie: Long = 40


  println(s"Win Percentage of India = ${totalWinIndiaCount * 100f/totalMatchIndiaCount} %\nLoss Percentage of India = ${totalLossCount * 100f/totalMatchIndiaCount} %\nTie Percentage of India = ${totalTieCount *100f/totalMatchIndiaCount} %")

Win Percentage of India = 51.182796 %
Loss Percentage of India = 44.51613 %
Tie Percentage of India = 4.3010755 %



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


