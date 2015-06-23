///////////////////////////////////////////////////////////////////////////////////////////////////

// Script Header Information

package SentimentAnalysis

import io.prediction.controller.EmptyActualResult
import io.prediction.controller.EmptyEvaluationInfo
import io.prediction.controller.Params
import io.prediction.controller.PDataSource
import io.prediction.controller.SanityCheck
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
  appName : String // Needed to pull data from correct application.
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
) extends Serializable with SanityCheck {


  def sanityCheck: Unit = {
    specialChars.foreach(println)
  }
}


///////////////////////////////////////////////////////////////////////////////////////////////////

// Data Source Class

class DataSource (
  dsp : DataSourceParams
) extends PDataSource[TrainingData, EmptyEvaluationInfo, Query, EmptyActualResult] {



  def readEventData (sc : SparkContext) : TrainingData = {

    // Get stop words from event server.
    val specialChars : HashSet[String]= HashSet(
      PEventStore.find(
        appName = dsp.appName, // Specify application name.
        entityType = Some("special") // Specify entity type.
      )(sc).map(
        event => event.properties.get[String]("char") // Get stopword.
      ).collect : _* // Multiple argument constructor.
    )





    // ----- EXERCISE 1 Start -------

    // Get your training observations from event server.
    // Have provided the definition of specialChars as an example.
    val trainingObservations : RDD[Observation] = {

      PEventStore.find(
        appName = dsp.appName,
        entityType = Some("training")
      )(sc).map(
        event => Observation(
          event.properties.get[Double]("label"),
          event.properties.get[String]("text")
        )
      )

    }


    TrainingData(trainingObservations, specialChars)
  }

  // ----- EXERCISE 1 End -------






  // This method MUST be implemented as part of the Data Source component.
  // Its functionality is factored out via readEventData.
  def readTraining (sc : SparkContext) : TrainingData = {
    readEventData(sc)
  }



}










