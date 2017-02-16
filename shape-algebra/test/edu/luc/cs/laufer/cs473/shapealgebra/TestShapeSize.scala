package edu.luc.cs.laufer.cs473.shapealgebra

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite

import TestFixtures._

@RunWith(classOf[JUnitRunner])
class TestShapeSize extends FunSuite {

  def test(description: String, s: Shape, d: Int): Unit = {
    test(description) {
	  assert(d === ShapeSize(s))
    }
  }

  test("simple circle", simpleEllipse, 1)
  test("simple rectangle", simpleRectangle, 1)
  test("simple location", simpleLocation, 1)
  test("simple group", simpleGroup, 2)
  test("complex group", complexGroup, 5)
}
