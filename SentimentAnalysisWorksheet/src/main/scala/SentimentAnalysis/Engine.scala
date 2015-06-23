package SentimentAnalysis

import io.prediction.controller.Engine
import io.prediction.controller.EngineFactory

// Queries will be coming in as text instances.
case class Query(
  text : String
)



// Our goal is to return one of the following sentiment classes:
// 0.0 - Negative
// 1.0 - Somewhat Negative
// 2.0 - Neutral
// 3.0 - Somewhat Positive
// 4.0 - Positive
case class PredictedResult(
  sentiment : String
)

case class ActualResult(
sentiment : String
)


object SentimentEngine extends EngineFactory {

  override
  def apply () = {
    new Engine(
      classOf[DataSource],
      classOf[Preparator],
      Map("algorithm" -> classOf[Algorithm]),
      classOf[Serving]
    )
  }

}

