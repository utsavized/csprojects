package edu.luc.cs.laufer.cs473.shapealgebra

import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import org.scalatest.FunSuite

import java.awt.{Color,Graphics}
import java.awt.image.BufferedImage

import TestFixturesExtended.{complexPolygon, extendedGroup,paintExtendedGroup}



@RunWith(classOf[JUnitRunner])
class TestExtendedDraw extends FunSuite with BufferedImageEquality {
  test("complex") {
	val s = extendedGroup
	val i = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB)
	ExtendedDraw(i.createGraphics())(s)
	val j = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB)
	paintExtendedGroup(j.createGraphics())
	assertEquals(i, j)
  }
}


/*
@RunWith(classOf[JUnitRunner])
class TestExtendedDraw extends FunSuite with BufferedImageEquality {
  test("complex") {
	val s = complexPolygon
	val i = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB)
	ExtendedDraw(i.createGraphics())(s)
	val j = new BufferedImage(500, 500, BufferedImage.TYPE_INT_RGB)
	paintExtendedGroup(j.createGraphics())
	assertEquals(i, j)
  }
}
*/
