package edu.luc.cs.laufer.cs473.shapealgebra

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite

@RunWith(classOf[JUnitRunner])
class TestShapesValidity extends FunSuite {

  test("location with null child") {
    intercept[IllegalArgumentException] {
	  Location(10, 20, null)
	  fail("should have thrown IllegalArgumentException")
	}
  }

  test("group with null child") {
	intercept[IllegalArgumentException] {
	  Group(Ellipse(50, 50), null, Rectangle(30, 40))
	  fail("should have thrown IllegalArgumentException")
	}
  }

  test("group with no children") {
	intercept[IllegalArgumentException] {
	  Group()
	  fail("should have thrown IllegalArgumentException")
	}
  }
}
