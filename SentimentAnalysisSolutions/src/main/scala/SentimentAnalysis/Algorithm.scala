///////////////////////////////////////////////////////////////////////////////////////////////////

// Script Header Information

package SentimentAnalysis

import io.prediction.controller.Params
import io.prediction.controller.PAlgorithm
import org.apache.spark.SparkContext
import org.apache.spark.mllib.classification.NaiveBayes
import org.apache.spark.mllib.classification.NaiveBayesModel

///////////////////////////////////////////////////////////////////////////////////////////////////

// Parameter Class

case class AlgorithmParams (
  lambda : Int
) extends Params

///////////////////////////////////////////////////////////////////////////////////////////////////

// Auxiliary Class

case class Model(
  pd : PreparedData,
  lambda : Double
) extends Serializable {






  // ----- EXERCISE 7 Start -------

  // Implement this model's predict function which takes in as
  // input a Query instance. Use the following Naive Bayes Model
  // for prediction:

  val nb : NaiveBayesModel = NaiveBayes.train(pd.data, lambda)

  // Remember that your queries are coming in as text strings,
  // and that your PredictedResult class also expects a string.
  // Look at the serving component to see how you should format
  // the prediction obtained from the Naive Bayes Model.

  def predict (query : Query) : PredictedResult = {
    PredictedResult(nb.predict(pd.transform(query.text)).toString)
  }


  // ----- EXERCISE 7 End -------






}

///////////////////////////////////////////////////////////////////////////////////////////////////

// Main Class

class Algorithm (
  ap :  AlgorithmParams
) extends PAlgorithm[PreparedData, Model, Query, PredictedResult] {


  def train (sc : SparkContext, pd : PreparedData) : Model = {
    Model(pd, ap.lambda)
  }

  def predict(model : Model, query : Query) : PredictedResult = {
    model.predict(query)
  }


}


