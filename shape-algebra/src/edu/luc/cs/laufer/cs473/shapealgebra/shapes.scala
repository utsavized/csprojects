package edu.luc.cs.laufer.cs473.shapealgebra

/**
 * An initial algebra of shapes.
 */
abstract class Shape

class Decorator(shape: Shape) extends Shape {
  if (shape == null) {
    throw new IllegalArgumentException("null child in decorator")
  }
}

class Composite(shapes: Shape*) extends Shape {
  if (shapes isEmpty) {
    throw new IllegalArgumentException("empty composite")
  }
  if (shapes.contains(null)) {
    throw new IllegalArgumentException("null child in composite")
  }
}

case class Ellipse(halfWidth: Int, halfHeight: Int) extends Shape
case class Rectangle(width: Int, height: Int) extends Shape
case class Location(x: Int, y: Int, shape: Shape) extends Decorator(shape)
case class Group(shapes: Shape*) extends Composite(shapes: _*)
