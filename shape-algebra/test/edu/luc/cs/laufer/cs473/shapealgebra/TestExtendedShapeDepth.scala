package edu.luc.cs.laufer.cs473.shapealgebra

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite

import TestFixtures._
import TestFixturesExtended._

@RunWith(classOf[JUnitRunner])
class TestExtendedShapeDepth extends FunSuite {

  def test(description: String, s: Shape, d: Int): Unit = {
    test(description) {
	  assert(d === ExtendedShapeDepth(s))
    }
  }

  test("simple circle", simpleEllipse, 1)
  test("simple rectangle", simpleRectangle, 1)
  test("simple location", simpleLocation, 2)
  test("simple group", simpleGroup, 3)
  test("complex group", complexGroup, 6)
  test("simple stroke", simpleStroke, 2)
  test("simple fill", simpleFill, 2)
  test("simple outline", simpleOutline, 2)
  test("simple polygon", simplePolygon, 2)
  test("complex polygon", complexPolygon, 4)
  test("simple extended 1", simpleExtended1, 2)
  test("simple extended 2", simpleExtended2, 3)
  test("simple extended 3", simpleExtended3, 3)
  test("extended group", extendedGroup, 9)
  test("extended group rotate", extendedGroupRotate, 11)
}
