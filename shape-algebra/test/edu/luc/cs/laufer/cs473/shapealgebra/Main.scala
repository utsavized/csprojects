package edu.luc.cs.laufer.cs473.shapealgebra

import TestFixtures.complexGroup

object Main {
  def main(args : Array[String]) : Unit = {
    val s = complexGroup
    println(s)
    println("size = " + ShapeSize(s))
    println("bounding box = " + BoundingBox(s))
  }
}
