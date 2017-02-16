package edu.luc.cs.laufer.cs473.shapealgebra

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite

import TestFixtures._

@RunWith(classOf[JUnitRunner])
class TestBoundingBox extends FunSuite {

  def test(description: String, s: Shape, x: Int, y: Int, width: Int, height: Int): Unit = {
    test(description) {
	  val b = BoundingBox(s)
	  val r = b.shape.asInstanceOf[Rectangle]
	  assert(x === b.x)
	  assert(y === b.y)
	  assert(width === r.width)
	  assert(height === r.height)
    }
  }

  test("simple circle", simpleEllipse, -50, -50, 100, 100)
  test("simple rectangle", simpleRectangle, 0, 0, 80, 120)
  test("simple location", simpleLocation, 70, 30, 80, 120)
  test("simple group", simpleGroup, 150, 50, 350, 300)
  test("complex group", complexGroup, 30, 80, 470, 320)
}
