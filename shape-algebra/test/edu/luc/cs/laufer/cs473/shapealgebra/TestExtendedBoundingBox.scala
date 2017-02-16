package edu.luc.cs.laufer.cs473.shapealgebra

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite

import TestFixturesExtended._
import TestFixtures._

@RunWith(classOf[JUnitRunner])
class TestExtendedBoundingBox extends FunSuite {

  def test(description: String, s: Shape, x: Int, y: Int, width: Int, height: Int): Unit = {
    test(description) {
val b = ExtendedBoundingBox(s)
val r = b.shape.asInstanceOf[Rectangle]
assert(x === b.x)
assert(y === b.y)
assert(width === r.width)
assert(height === r.height)
    }
  }
  
  test("simple ellipse", simpleEllipse, -50, -50, 100, 100)
  test("simple stroke", simpleStroke, -50, -50, 100, 100)
  test("simple fill", simpleFill, 0, 0, 80, 120)
  test("simple outline", simpleOutline, 0, 0, 80, 120)
  test("simple polygon", simplePolygon, 10, 10, 80, 100)
  test("complex polygon", complexPolygon, 40, 50, 80, 100)
  test("simple extended 1", simpleExtended1, -70, 0, 141, 141)
  test("simple extended 2", simpleExtended2, 30, 200, 141, 141)
  test("simple extended 3", simpleExtended3, -86, -23, 373, 446)
  test("extended group", extendedGroup, 30, 80, 470, 320)
  test("extended group rotate", extendedGroupRotate, -219, 73, 512, 567)
}