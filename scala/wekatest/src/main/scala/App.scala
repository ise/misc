package com.example.WekaTest

import weka.core._
import weka.classifiers._
import weka.classifiers.trees._
import java.io._

object Main extends App{
  override def main(args: Array[String]){
    val train = new Instances(new FileReader("data/weather.arff"))
    val test = new Instances(new StringReader("""@relation weather

@attribute outlook {sunny, overcast, rainy}
@attribute temperature real
@attribute humidity real
@attribute windy {TRUE, FALSE}
@attribute play {yes, no}

@data
sunny,85,85,FALSE,no
sunny,80,90,TRUE,no
overcast,83,86,FALSE,yes"""))
    train.setClassIndex(train.numAttributes() - 1)
    test.setClassIndex(test.numAttributes() - 1)
    
    //c4.5アルゴリズムによる決定木の構築
    val j48 = new J48()
    j48.buildClassifier(train)

    //決定木の表示
    println(j48)
    
    //テストデータを分類 
    val eval = new Evaluation(train)
    eval.evaluateModel(j48, test)
    println(eval.toClassDetailsString())
    println(eval.toMatrixString())
    println(eval.toSummaryString())
  }
}
