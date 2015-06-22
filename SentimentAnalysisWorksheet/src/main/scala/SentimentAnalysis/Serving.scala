///////////////////////////////////////////////////////////////////////////////////////////////////

// Script Header Information

package SentimentAnalysis

import io.prediction.controller.LServing
import scala.collection.immutable.HashMap

class Serving extends LServing[Query, PredictedResult] {

  // ----- EXERCISE 8 Start -------

  // Recall that the Serving component is supposed to combine
  // predicted results and return a final prediction. It is also
  // the component that is supposed to implement any prediction
  // formatting unique to the application.

  // Thus far, we have only been working with labels: 0.0, 1.0, ..., 4.0.
  // However, we would like our prediction to say whether a sentiment is:
  // Negative, Somewhat Negative, Neutral, etc.
  // Use the following map to have the method serve return the appropriate
  // predicted result:



  val labelMap : HashMap[String, String] = HashMap(
    "0.0" -> "Negative",
    "1.0" -> "Somewhat Negative",
    "2.0" -> "Neutral",
    "3.0" -> "Somewhat Positive",
    "4.0" -> "Positve"
  )




  def serve (
    query : Query,
    predictions : Seq[PredictedResult]
  ) : PredictedResult = {
    // Replace with your code.
    PredictedResult("")

  }

  // ----- EXERCISE 8 End -------
}