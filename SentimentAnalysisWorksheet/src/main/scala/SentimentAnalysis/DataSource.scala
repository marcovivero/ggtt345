///////////////////////////////////////////////////////////////////////////////////////////////////

// Script Header Information

package SentimentAnalysis

import io.prediction.controller.EmptyActualResult
import io.prediction.controller.EmptyEvaluationInfo
import io.prediction.controller.Params
import io.prediction.controller.PDataSource
import io.prediction.data.store.PEventStore
import org.apache.spark.SparkContext
import org.apache.spark.rdd.RDD
import scala.collection.immutable.HashSet

///////////////////////////////////////////////////////////////////////////////////////////////////

// Parameter Class

/// This is what we will call an engine component parameter class.
/// These parameter classes can be associated to each of your DASE
/// components, and server as wrappers for storing parameters needed
/// for any given functionality in the component.

case class DataSourceParams (
  appName : String, // Needed to pull data from correct application.
  evalK : Option[Int]
) extends Params


///////////////////////////////////////////////////////////////////////////////////////////////////

// Auxiliary Classes

/// Additional classes needed for functionality.

/// Wrapper to help define data observations explicitly.
case class Observation (
  label : Double,
  text : String
) extends Serializable

/// This will serve as our training data, hence this must be a collection
/// of observations, or Observation instances.
case class TrainingData (
  data : RDD[Observation],
  specialChars : HashSet[String]
) extends Serializable


///////////////////////////////////////////////////////////////////////////////////////////////////

// Data Source Class

class DataSource (
  dsp : DataSourceParams
) extends PDataSource[TrainingData, EmptyEvaluationInfo, Query, ActualResult] {



  def readEventData (sc : SparkContext) : TrainingData = {

    // Get stop words from event server.
    val specialChars : HashSet[String]= HashSet(
      PEventStore.find(
        appName = dsp.appName, // Specify application name.
        entityType = Some("stopword") // Specify entity type.
      )(sc).map(
        event => event.properties.get[String]("word") // Get stopword.
      ).collect : _* // Multiple argument constructor.
    )





    // ----- EXERCISE 1 Start -------

    // Get your training observations from event server.
    // Have provided the definition of specialChars as an example.
    val trainingObservations : RDD[Observation] = {

      // Replace the following line with your actual code.
      // You will want to cache the final RDD as you will re-use it.
      sc.parallelize(Seq(Observation(1.0, "")))

    }


    TrainingData(trainingObservations, specialChars)
  }

  // ----- EXERCISE 1 End -------






  // This method MUST be implemented as part of the Data Source component.
  // Its functionality is factored out via readEventData.
  override
  def readTraining (sc : SparkContext) : TrainingData = {
    readEventData(sc)
  }


  // ----- EXERCISE 10 Start -------

  // Implement the readEval function which returns evalK
  // number of TrainingData objects, evaluation logs, and
  // query/actual result pairs.

  def readEval(sc: SparkContext):
  Seq[(TrainingData, EmptyEvaluationInfo, RDD[(Query, ActualResult)])] = {

  }

  // ----- EXERCISE 10 End -------



}










