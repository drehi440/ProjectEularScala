package com.rehi.master

import org.apache.spark.SparkConf
import org.apache.spark.sql.SQLContext
import org.apache.spark.SparkContext
import org.apache.spark.sql.UDFRegistration
import com.databricks.spark.csv
import org.apache.spark.sql.Row
import org.apache.spark.sql.functions._
import org.apache.spark.sql.hive.HiveContext
import scala.collection.mutable.ListBuffer
import java.io.FileWriter
import java.io.BufferedWriter
import java.io.FileReader
import java.io.BufferedReader
import scala.io.Source
import util.control.Breaks._



object reMaster {
  def main(args: Array[String]): Unit = {

    val readFile = Source.fromFile("C:\\Users\\drehi\\workspace\\SparkApp\\partyStge.csv")

    val file = new FileWriter("C:\\Users\\drehi\\workspace\\SparkApp\\testTable1.txt")

    var dataFile = new ListBuffer[String]

    val readFileLines = readFile.getLines.map(x => x.split(","))

    var vuff = new BufferedWriter(file)

    var LineAsString = ""
    var localLineAsString = ""
    var updateLineWithId = ""
    var finalListRow = ""
    
    var localfirstName = ""
    var localTaxId = ""
    var tempCondition = ""
    var localWithId = ""
    
    var sum = 0
    var counter = 0
    var checkCounter = 0

    for (e <- readFileLines) {
      
      
      if (sum == 0) {

        LineAsString = e mkString ","
        updateLineWithId = e(0).toString()

        finalListRow = LineAsString + "," + updateLineWithId
        dataFile = dataFile ++ List(finalListRow)
        counter = counter+1

      } else{//for sum =1 and on
        
          var currentfirstName = e(2).toString()
          var currentTaxId = e(6).toString()
          var finalCondition = currentfirstName+currentTaxId

          
          breakable{//B
              for (y <- readFileLines) {//
                  localLineAsString = y.mkString(",")
                    if (checkCounter > sum) {//FOR SKIPING N LINE
                     println("INNER HI")
                    checkCounter = checkCounter+1
                    } 
                     else {
                          localfirstName = y(2).toString()
                          localTaxId = y(6).toString()
                          tempCondition = localfirstName+localTaxId
                              if(finalCondition == tempCondition){
                                  dataFile = dataFile ++ List(localLineAsString +","+ updateLineWithId)
                              }
                              else if(checkCounter <14){
                                  
                                  println("Hello!"+checkCounter)
                                  checkCounter= checkCounter+1
                  }
                              else {
                                dataFile = dataFile ++ List(localLineAsString+","+localWithId  )
                              }
                    
            }
            
            }//
          }//B
  }

      sum = sum + 1
    }

    dataFile.map { x => println(x) }

  }
}
