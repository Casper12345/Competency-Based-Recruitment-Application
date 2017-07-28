package model

/**
  * Methods for Euclidean similarity algorithm.
  */
object similarityAlgorithm {

  /**
    * Method for calculating the max distance.
    *
    * @param requiredVector
    * @return Max distance as double
    */
  def maxDistCal(requiredVector: List[Int]): Double = {

    val allZeroVector = List.fill(requiredVector.size)(0)

    cappedEuclideanDistance(requiredVector, allZeroVector)

  }


  /**
    * Method for calculating euclidean distance between vectors
    *
    * @param vector1
    * @param vector2
    * @return distance as double
    */
  def euclideanDistance(vector1: List[Int], vector2: List[Int]): Double = {

    var subCal = 0.0

    for (i <- vector1.indices) {
      subCal = subCal + math.pow(vector1(i) - vector2(i), 2)

    }

    math.sqrt(subCal)

  }

  /**
    * Method for calculating the capped euclidean distance
    * Method caps vectors from becoming negative
    *
    * vector1 has to be the required vector and vector2 the candidate vector
    *
    * @param vector1
    * @param vector2
    * @return capped euclidian distance as double
    */
  def cappedEuclideanDistance(vector1: List[Int], vector2: List[Int]): Double = {

    var subCal = 0.0

    for (i <- vector1.indices) {
      subCal = subCal + math.pow(math.max(0, vector1(i) - vector2(i)), 2)

    }

    math.sqrt(subCal)

  }


  /**
    * Method that calculates the similarity as a percentage of the max distance
    * This method uses the capped euclidean distance to avoid negative values
    *
    * @param vector1
    * @param vector2
    * @return
    */
  def distSim(vector1: List[Int], vector2: List[Int]): Double = {

    100 - ((cappedEuclideanDistance(vector1, vector2) / maxDistCal(vector1)) * 100)

  }

}

