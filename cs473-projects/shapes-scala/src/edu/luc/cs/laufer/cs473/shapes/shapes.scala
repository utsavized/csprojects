package edu.luc.cs.laufer.cs473.shapes

abstract class Shape

case class Rectangle(width: Int, height: Int) extends Shape

case class Ellipse(width: Int, height: Int) extends Shape

case class Location(x: Int, y: Int, shape: Shape) extends Shape {
  if (shape == null) {
    throw new IllegalArgumentException("null shape in location")
  }
}

case class Group(shape: Shape*) extends Shape {
  if (shape == null) {
    throw new IllegalArgumentException("null shape in group")
  }
}