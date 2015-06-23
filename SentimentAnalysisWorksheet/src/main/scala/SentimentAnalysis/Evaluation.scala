///////////////////////////////////////////////////////////////////////////////////////////////////

// Script Header Information

package SentimentAnalysis

import io.prediction.controller._

///////////////////////////////////////////////////////////////////////////////////////////////////

// Define Metric



// ----- EXERCISE 9 Start -------

// Define the accuracy metric.

case class Accuracy ()
  extends AverageMetric[EmptyEvaluationInfo, Query, PredictedResult, ActualResult] {
    def calculate (query : Query, pred : PredictedResult, actual : ActualResult): Double = {
      // Replace with actual code.
      1.0
    }
  }
// ----- EXERCISE 9 End -------



///////////////////////////////////////////////////////////////////////////////////////////////////

// Evaluator.

object AccuracyEvaluation extends Evaluation {
  engineMetric = (SentimentEngine(), Accuracy())
}

// List of parameters to test:

object EngineParamsList extends EngineParamsGenerator {
  // Set data source and preparator parameters.
  private[this] val baseEP = EngineParams(
    dataSourceParams = DataSourceParams(appName = "MyTextApp", evalK = Some(4)),
    preparatorParams = PreparatorParams(nGram = 2)
  )

  // Set the algorithm params for which we will assess an accuracy score.
  engineParamsList = Seq(
    baseEP.copy(algorithmParamsList = Seq(("algorithm", AlgorithmParams(0.25)))),
    baseEP.copy(algorithmParamsList = Seq(("algorithm", AlgorithmParams(0.5)))),
    baseEP.copy(algorithmParamsList = Seq(("algorithm", AlgorithmParams(1.0))))
  )
}