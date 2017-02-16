package edu.luc.cs.laufer.cs473.shapealgebra

import java.awt.Color
/*
 * Some additional shape classes added later.
 */

/**
 * An initial algebra of shapes.
 */


case class Point(x:Int, y: Int) extends Shape
case class Circle(radius: Int) extends Shape
case class Polygon(points: Point*) extends Composite(points: _*)
case class Stroke(color: Color, shape: Shape) extends Decorator(shape)
case class Fill(shape: Shape) extends Decorator(shape)
case class Outline(shape: Shape) extends Decorator(shape)
case class Rotate(angle: Int, shape: Shape) extends Decorator(shape)
