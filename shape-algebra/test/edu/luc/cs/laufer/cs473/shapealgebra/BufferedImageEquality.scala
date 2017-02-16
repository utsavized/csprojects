package edu.luc.cs.laufer.cs473.shapealgebra

import org.scalatest.FunSuite

import java.awt.image.BufferedImage

trait BufferedImageEquality extends FunSuite {
  def assertEquals(i1: BufferedImage, i2: BufferedImage) = {
	val u = i1.getData()
	val v = i2.getData()
	for (l <- 0 to 499) {
		for (k <- 0 to 499) {
			val pu = u.getPixel(k, l, null: Array[Int])
			val pv = v.getPixel(k, l, null: Array[Int])
			assert(pu.length === pv.length)
			for (m <- 0 to pu.length - 1) {
				assert(pu(m) === pv(m))
			}
		}
	}
  }
}
