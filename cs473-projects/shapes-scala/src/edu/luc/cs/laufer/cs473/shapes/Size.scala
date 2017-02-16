package edu.luc.cs.laufer.cs473.shapes

object Size {
  def size(s: Shape): Int = s match {
    case Ellipse(_, _) =>
      return 1
    case Rectangle(_, _) =>
      return 1
    case Location(_, _, shape) => {
      return size(shape)
    }
    case Group(shape @_*) => {
      //Get a list of individual num of shapes
      val numShapes = shape.map(s => size(s))
      //Sum to get total
      val total = numShapes.reduceRight(_ + _)
      return total
    }
    
  }
}