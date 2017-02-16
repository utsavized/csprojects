package edu.luc.cs.laufer.cs473.shapealgebra

object ShapeSize extends ShapeAlgebra[Int] {
  override def visitEllipse(e: Ellipse) = 1
  override def visitRectangle(r: Rectangle) = 1
  override def visitLocation(r: Int, l: Location) = r
  override def visitGroup(rs: Seq[Int], g: Group) = rs.sum
}

class BoundingBox extends ShapeAlgebra[Location] {
  override def visitEllipse(e: Ellipse) =
    Location(-e.halfWidth, -e.halfHeight, Rectangle(2 * e.halfWidth, 2 * e.halfHeight))
  override def visitRectangle(r: Rectangle) =
    Location(0, 0, r)
  override def visitLocation(b: Location, l: Location) = {
    Location(l.x + b.x, l.y + b.y, b.shape)
  }
  override def visitGroup(rs: Seq[Location], g: Group) = {
      val boundingBoxes = rs.map(s => BoundingBox(s))
      val xMin = boundingBoxes.reduceLeft((x1,x2) => if (x1.x < x2.x) x1 else x2).x
      val yMin = boundingBoxes.reduceLeft((y1,y2) => if (y1.y < y2.y) y1 else y2).y
      val xMax = boundingBoxes.reduceLeft((x1,x2) => if (x1.x + x1.shape.asInstanceOf[Rectangle].width > x2.x + x2.shape.asInstanceOf[Rectangle].width) x1 else x2)
      val yMax = boundingBoxes.reduceLeft((y1,y2) => if (y1.y + y1.shape.asInstanceOf[Rectangle].height > y2.y + y2.shape.asInstanceOf[Rectangle].height) y1 else y2)
      val bWidth = (xMax.shape.asInstanceOf[Rectangle].width + xMax.x) - xMin
      val bHeight = (yMax.shape.asInstanceOf[Rectangle].height + yMax.y) - yMin
      Location(xMin, yMin, Rectangle(bWidth, bHeight))
   }
}

object BoundingBox extends BoundingBox