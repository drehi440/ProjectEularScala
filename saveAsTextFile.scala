package com.rehi.master


import org.apache.spark.SparkConf
import org.apache.spark.SparkContext
import org.apache.spark.SparkConf
import java.util.Calendar

object loadHdfs {
  def main(args: Array[String]): Unit = {
  println("THIS IS SPARK")
  System.setProperty("hadoop.home.dir", "D:\\Softwares\\hadoop-common-2.2.0-bin-master\\")
  val conf = new SparkConf().setAppName("SAMPLE_SPARK").setMaster("local[*]")
  val sc = new SparkContext(conf)
  
  val now = Calendar.getInstance()
  val currentMinuteStarted = now.get(Calendar.HOUR)+" : "+now.get(Calendar.MINUTE)+" : "+now.get(Calendar.SECOND)
  println("STARTED TIME==============================================>"+currentMinuteStarted)
  
  val readFile = sc.textFile("src/main/resources/", 2)
  
  val saveFile = readFile.coalesce(1).saveAsTextFile("C:/Users/drehi/workspace/SparkApp/src/main/resources/wordCount")
  
  
  val currentMinuteEnded = now.get(Calendar.HOUR)+" : "+now.get(Calendar.MINUTE)+" : "+now.get(Calendar.SECOND)
  println("END TIME==============================================>"+currentMinuteEnded)
    
  }
}
