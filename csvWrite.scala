package com.rehi.master
import java.io._
import org.apache.hadoop.conf.Configuration
import org.apache.hadoop.fs.FileSystem
import org.apache.hadoop.fs.Path
import java.io.PrintWriter

object csvWrite{
  def main(args: Array[String]): Unit = {
    // println("THIS IS csv writing") 
    val fineName = "NEWEMPLOYEE.csv"
    val pw = new PrintWriter(new File(fineName ))
    
//    pw.write("NAME,JOB TITLE,DEPARTMENT,EMPLOYEE ANNUAL SALARY,ESTIMATED ANNUAL SALARY MINUS FURLOUGHS")
//    pw.write("\n")
    // println("File writtern to the==================>"+fineName)

    val autoStart = 10000
    var annualSal = 10000.toLong
    var Factor = 2000.toLong
    for(i<- 1 to 4000375){
        if(i<= 1000000){
              var salFactor = annualSal - Factor
              if(i <= 250000){
                  var createString = autoStart+i+", Name"+i+",Associate Software Engineer, BIGDATA ANALYSTICS ,"+annualSal+","+ salFactor
                  pw.write(createString)
                  pw.write("\n")
                  // println(createString)  
                  }
              else if(i> 250000 && i<=500000){
                  var createString = autoStart+i+", Name"+i+",Associate Software Engineer, UI DEVELOPMENT ,"+annualSal+","+ salFactor
                  pw.write(createString)
                  pw.write("\n")
                  // println(createString)  
                  }
              else if(i> 500000 && i<=750000){
                  var createString = autoStart+i+", Name"+i+",Associate Software Engineer, PRODUCTION SUPPORT ,"+annualSal+","+ salFactor
                  pw.write(createString)
                  pw.write("\n")
                  // println(createString)  
                  }
              else{
                  var createString = autoStart+i+", Name"+i+",Associate Software Engineer, JAVA ,"+annualSal+","+ salFactor
                  pw.write(createString)
                  pw.write("\n")
                  // println(createString)  
                }  
        }
      
//      pw.write(createString)
      
      
      else if(i> 1000000 && i<=2000000){
        val annualSal1 = annualSal+annualSal
        var salFactor1 = annualSal1 - Factor
          if(i <= 1250000){
                  var createString = autoStart+i+", Name"+i+",Software Engineer, BIGDATA ANALYSTICS ,"+annualSal1+","+ salFactor1
                  pw.write(createString)
                  pw.write("\n")
                  // println(createString)  
                  }
              else if(i> 1250000 && i<=1500000){
                  var createString = autoStart+i+", Name"+i+",Software Engineer, UI DEVELOPMENT ,"+annualSal1+","+ salFactor1
                  pw.write(createString)
                  pw.write("\n")
                  // println(createString)  
                  }
              else if(i> 1500000 && i<=1750000){
                  var createString = autoStart+i+", Name"+i+",Software Engineer, PRODUCTION SUPPORT ,"+annualSal1+","+ salFactor1
                  pw.write(createString)
                  pw.write("\n")
                  // println(createString)  
                  }
              else{
                  var createString = autoStart+i+", Name"+i+",Software Engineer, JAVA ,"+annualSal1+","+ salFactor1
                  pw.write(createString)
                  // println(createString)
                  pw.write("\n")  
                }  
//        pw.write(createString)
      }
      
      else if(i> 2000000 && i<=3000000){
        val annualSal1 = annualSal+annualSal+annualSal
        val salFactor1 = annualSal1 - Factor
        if(i <= 2250000){
                  var createString = autoStart+i+", Name"+i+",Senior Engineer, BIGDATA ANALYSTICS ,"+annualSal1+","+ salFactor1
                  pw.write(createString)
                  pw.write("\n")
                  // println(createString)  
                  }
              else if(i> 2250000 && i<=2500000){
                  var createString = autoStart+i+", Name"+i+",Senior Engineer, UI DEVELOPMENT ,"+annualSal1+","+ salFactor1
                  pw.write(createString)
                  pw.write("\n")
                  // println(createString)  
                  }
              else if(i> 2500000 && i<=2750000){
                  var createString = autoStart+i+", Name"+i+",Senior Engineer, PRODUCTION SUPPORT ,"+annualSal1+","+ salFactor1
                  pw.write(createString)
                  pw.write("\n")
                  // println(createString)  
                  }
              else{
                  var createString = autoStart+i+", Name"+i+",Senior Engineer, JAVA ,"+annualSal1+","+ salFactor1
                  pw.write(createString)
                  pw.write("\n")
                  // println(createString)  
                }  
//      pw.write(createString)
      
      }
      
      
      else if(i> 3000000 && i<=4000000){
        val annualSal1 = annualSal+annualSal+annualSal+annualSal
        val salFactor1 = annualSal1 - Factor
        if(i <= 3250000){
                  var createString = autoStart+i+", Name"+i+",Tech Lead, BIGDATA ANALYSTICS ,"+annualSal1+","+ salFactor1
                  pw.write(createString)
                  pw.write("\n")
                  // println(createString)  
                  }
              else if(i> 3250000 && i<=3500000){
                  var createString = autoStart+i+", Name"+i+",Tech Lead, UI DEVELOPMENT ,"+annualSal1+","+ salFactor1
                  pw.write(createString)
                  pw.write("\n")
                  // println(createString)  
                  }
              else if(i> 3500000 && i<=3750000){
                  var createString = autoStart+i+", Name"+i+",Tech Lead, PRODUCTION SUPPORT ,"+annualSal1+","+ salFactor1
                  pw.write(createString)
                  pw.write("\n")
                  // println(createString)  
                  }
              else{
                  var createString = autoStart+i+", Name"+i+",Tech Lead, JAVA ,"+annualSal1+","+ salFactor1
                  pw.write(createString)
                  pw.write("\n")
                  // println(createString)  
                }   
//      pw.write(createString)
      
      }
        
      else{
        val annualSal1 = annualSal+annualSal+annualSal+annualSal+annualSal
        val salFactor1 = annualSal1 - Factor
        if(i %2==0){
                  var createString = autoStart+i+", Name"+i+",MANAGER, OFFSHORE ,"+annualSal1+","+ salFactor1
                  pw.write(createString)
                  pw.write("\n")
                  // println(createString)  
                  }
              else{
                  var createString = autoStart+i+", Name"+i+",MANAGER, ONSHORE ,"+annualSal1+","+ salFactor1
                  pw.write(createString)
                  pw.write("\n")
                  // println(createString)  
                }   
//      pw.write(createString)
      
      }
        
        
    }
    pw.close

    println("DONE! FILE CREATED")

  }
  
  
}
