package edu.luc.cs.laufer.cs473.shapealgebra

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite

import TestFixturesExtended._

@RunWith(classOf[JUnitRunner])
class TestExtendedShapeSize extends FunSuite {

  def test(description: String, s: Shape, d: Int): Unit = {
    test(description) {
	  assert(d === ExtendedShapeSize(s))
    }
  }

  test("simple stroke", simpleStroke, 1)
  test("simple fill", simpleFill, 1)
  test("simple outline", simpleOutline, 1)
  test("simple polygon", simplePolygon, 1)
  test("complex polygon", complexPolygon, 1)
  test("simple extended 1", simpleExtended1, 1)
  test("simple extended 2", simpleExtended2, 1)
  test("simple extended 3", simpleExtended3, 1)
  test("extended group", extendedGroup, 5)
  test("extended group rotate", extendedGroupRotate, 6)
}
