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
import java.util.HashMap
import java.io.Serializable
import scala.util.Random
import scala.annotation.tailrec



object reMaster {
  def main(args: Array[String]): Unit = {

    val readFile = Source.fromFile("C:\\Users\\drehi\\workspace\\SparkApp\\partyStge.csv")

    val file = new FileWriter("C:\\Users\\drehi\\workspace\\SparkApp\\testTable1.txt")

    var dataFile = new ListBuffer[String]

    val readFileLines = readFile.getLines.map(x => x.split(","))

    var vuff = new BufferedWriter(file)

    var LineAsString = ""
    var updateLineWithId = ""
    var finalListRow = ""
    
    

    var finalfirstName = ""
    var finalTaxId = ""
    var finalLicenceNum = ""
    var mainCondition1 = ""
    var mainCondition2 = ""
    
    val r = new scala.util.Random(31)
    
    
      def makeCondition2(x:String,y:String):String={
  //      println("THIS IS THE Y VALUE PRINT"+y.trim().length())
        if(y.trim().length() < 1){
          
          var rand=x.trim()+"|"+Random.alphanumeric.take(11).mkString
          rand
        }
        else
        x.trim()+"|"+y.trim()
      }
    
    
    //advance
    
    
    
    println(makeCondition2("Alex","CCM"))
    
    println(makeCondition2("Alex",""))
    
    var map1 = new HashMap[String,String]//use it for storing and iterating over every thing
    
    var i =0
    
    for (e <- readFileLines) {
    breakable {    
              if(i == 0){
              i = i+1
                break
              }
      
        LineAsString = e mkString ","
        updateLineWithId = e(0).toString()
        finalfirstName = e(2).toString()
        finalLicenceNum = e(5).toString()
        finalTaxId = e(6).toString()
        
        mainCondition1 = makeCondition2(finalfirstName, finalTaxId)//condition1
        mainCondition2 = makeCondition2(finalfirstName, finalLicenceNum)//condition 2
        
        if(map1.containsKey(mainCondition1) || map1.containsKey(mainCondition2 )){//if 1st condition is true: Run// false: Check the other condition
          map1.get(updateLineWithId)
                    if(map1.get(mainCondition1)!=null ){
                          finalListRow = LineAsString + "," + map1.get(mainCondition1)
                    }
                    else {
                          finalListRow = LineAsString + "," + map1.get(mainCondition2)
            
                    }
          
          
          println("-----------------)))))))))))))----------------------------------")
          println(updateLineWithId+"----------------"+finalListRow)
          dataFile = dataFile ++ List(finalListRow)
        
        }
        
    
        
        else{

          map1.put(mainCondition1, updateLineWithId)
          map1.put(mainCondition2, updateLineWithId)
          if(map1.get(mainCondition1)!=null){
          finalListRow = LineAsString + "," + map1.get(mainCondition1)  
             
          }
          else{
          finalListRow = LineAsString + "," + map1.get(mainCondition2)  
          }
          println("-----------------)))))))))))))----------------------------------")
          println(updateLineWithId+"----------------"+finalListRow)
          
          dataFile = dataFile ++ List(finalListRow)
        }
        
    }
    }   
        

    
    println("(((((((((((___________________________________))))))))))))))))))))))))))))")
    dataFile.map { x => println(x) }
        
        
        
        
        
        /*        counter = counter+1

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
                          localfirstName = y(2).toString()//may be null
                          localTaxId = y(7).toString()//may be null
                          tempCondition = localfirstName+localTaxId
                               if(localfirstName.trim().length() > 0 && localTaxId.trim().length() > 0 && finalCondition == tempCondition){
                                  dataFile = dataFile ++ List(localLineAsString +","+ updateLineWithId)
                              }
                              else if(checkCounter <14){
                                  
                                  println("Hello!"+checkCounter)
                                  checkCounter= checkCounter+1
                  }
                              else {
                                dataFile = dataFile ++ List( localLineAsString+","+localWithId )
                              }
                    
            }
            
            }//
          }//B
  }

      sum = sum + 1
    }

    dataFile.map { x => println(x) }
*/
  }
}
