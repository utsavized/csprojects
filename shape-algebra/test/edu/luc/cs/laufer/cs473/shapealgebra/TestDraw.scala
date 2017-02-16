package edu.luc.cs.laufer.cs473.shapealgebra

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite

import java.awt.image.BufferedImage

import TestFixtures._

@RunWith(classOf[JUnitRunner])
class TestDraw extends FunSuite with BufferedImageEquality {
  test("simple") {
	val s = simpleLocation
	val i = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB)
	Draw(i.createGraphics())(s)
	val j = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB)
	val g = j.createGraphics()
	g.translate(70, 30)
	g.drawRect(0, 0, 80, 120)
	assertEquals(i, j)
  }

  
  test("complex") {
	val s = complexGroup
	val i = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB)
	Draw(i.createGraphics())(s)
	val j = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB)
	val g = j.createGraphics()
	paintComplexGroup(g)
	assertEquals(i, j)
  }
}
