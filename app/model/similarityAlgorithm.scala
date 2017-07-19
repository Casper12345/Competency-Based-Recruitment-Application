package model

/**
  * Created by Casper on 13/07/2017.
  */
object similarityAlgorithm {

   def maxDistCal(v: List[Int]): Double = math.sqrt(math.pow(5, 2) * v.size)

   def euclideanDistance(vector1: List[Int], vector2: List[Int]): Double = {

    var subCal = 0.0

    for (i <- vector1.indices) {
      subCal = subCal + math.pow(vector1(i) - vector2(i), 2)

    }

    math.sqrt(subCal)

  }

  def distSim(vector1: List[Int], vector2: List[Int]): Double = {

    100 - ((euclideanDistance(vector1, vector2) / maxDistCal(vector1)) * 100)

  }


}

