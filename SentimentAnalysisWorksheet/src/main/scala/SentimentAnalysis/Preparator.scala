///////////////////////////////////////////////////////////////////////////////////////////////////

// Script Header Information

package SentimentAnalysis

import io.prediction.controller.Params
import io.prediction.controller.PPreparator
import org.apache.spark.SparkContext
import org.apache.spark.mllib.feature.HashingTF
import org.apache.spark.mllib.feature.IDF
import org.apache.spark.mllib.feature.IDFModel
import org.apache.spark.mllib.linalg.Vector
import org.apache.spark.mllib.linalg.Vectors
import org.apache.spark.mllib.regression.LabeledPoint
import org.apache.spark.rdd.RDD
import scala.collection.immutable.HashSet

///////////////////////////////////////////////////////////////////////////////////////////////////

// Parameter Class

case class PreparatorParams (
  nGram : Int
) extends Params

///////////////////////////////////////////////////////////////////////////////////////////////////

// Auxiliary Classes

// LabeledNGrams essentially serves as an organizational wrapper for
// storing each observation as a label associated to a list of ngrams.

case class LabeledNGrams (
  label : Double,
  nGrams : Array[String]
)


// PreparedData class represents our set of training observations in a
// processed format. This auxiliary class will be in charge of all our
// data processing per the DASE framework reqs.

case class PreparedData (
  nGram : Int,
  trainingData : TrainingData
) extends Serializable {






  // ----- EXERCISE 2 Start -------

  // Implement a function that will remove the special characters that we
  // imported. Note that these are stored as string in
  // traininData.specialChars


  private def rmChars (text : String) : String = {

    // Replace with your actual code.

    ""
  }

  // ----- EXERCISE 2 End -------





  // This function we have implemented for you. This will
  // serve as our tokenizer, with the added functionality
  // of returning n-grams versus just words.


  private def tokenize (text : String) : Array[String] = {
    val newText = rmChars(text) // Use your function to remove unwanted characters.
    (1 until (nGram + 1)).flatMap(
        k => newText.split(" ").sliding(k).map(_.mkString)
    ).toArray
  }






  // ----- EXERCISE 3 Start -------

  // You are advised to take a look at the Spark API:
  // https://spark.apache.org/docs/1.3.1/api/scala/

  // Serve as hashing function for n-grams vectorization.
  private val hasher : HashingTF = new HashingTF()

  // Transform your trainingData.data object using the hasher
  // into an RDD[Vector] (mllib.linalg.Vector), and use it to obtain
  // an IDFModel instance.

  // HINT: You will not be using an IDFModel constructor explicitly.

  private val idf : IDFModel = {

    // Replace with your code.
    new IDFModel()

  }
  // ----- EXERCISE 3 End -------






  // ----- EXERCISE 4 Start -------

  // Define a transform function that takes as input a label and
  // text string
  // and does the following:
  // (1) tokenizes the string
  // (2) Converts the token list into a tf-idf feature vector;
  // (3) Returns an MLLib Vector object.


  def transform (text : String) : Vector = {

    // Replace with your code here.
    Vectors.dense(Array(1.0, 1.0, 1.0))
  }


  // ----- EXERCISE 4 End -------






  // ----- EXERCISE 5 Start -------

  // Use the transform function you just defined, and use it to define a map
  // from an Observation instance to a LabeledPoint instance, and apply
  // it on your trainingData.data object to obtain an RDD[LabeledPoint] instance.

  val data : RDD[LabeledPoint] = {
    // Replace with your code.
    trainingData.data.map(e => LabeledPoint(1.0, Vectors.dense(Array(1.5))))
  }

  // ----- EXERCISE 5 End -------


}


///////////////////////////////////////////////////////////////////////////////////////////////////

// Main Class


// ----- EXERCISE 6 Start -------

// Finish implementing the Preparator class.
// Your nGram constructor value should be coming from the Preparator Parameters.

// YOUR CODE HERE

class Preparator (pp : PreparatorParams) extends PPreparator[TrainingData, PreparedData] {

  def prepare(sc : SparkContext, trainingData: TrainingData) : PreparedData = {

    // Replace with your code.
    PreparedData(4, TrainingData(sc.parallelize(Seq(Observation(1, ""))), HashSet("")))

  }

}


// ----- EXERCISE 6 End -------


