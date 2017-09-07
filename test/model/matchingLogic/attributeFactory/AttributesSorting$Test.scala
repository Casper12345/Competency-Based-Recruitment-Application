package model.matchingLogic.attributeFactory

import org.scalatest.FunSuite

/**
  * Created by Casper on 09/08/2017.
  */
class AttributesSorting$Test extends FunSuite {

  test("sortAttributes"){

    var l: List[Attribute] = List(Attribute(1, "d", 2, "skill"), Attribute(1,"a", 4,"competency"), Attribute(2,"b", 3,"competency"), Attribute(1,"a", 4,"copetency"))

    val sort = AttributesSorting.sortAttributes(l)

    println(sort)

  }

}
