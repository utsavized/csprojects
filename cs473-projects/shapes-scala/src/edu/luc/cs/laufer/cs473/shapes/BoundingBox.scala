package edu.luc.cs.laufer.cs473.shapes

object BoundingBox {
  def boundingBox(s: Shape): Location = s match {
    case Ellipse(h, w) =>
      new Location(-h, -w, Rectangle(2*h, 2*w))
    case Rectangle(_, _) =>
      new Location(0, 0, s)
    case Location(x, y, shape) => {
      val b = boundingBox(shape)
      Location(x + b.x, y + b.y, b.shape)
    }
    case Group(shape @_*) => {
      //Map each shape to its bounding box
      val boundingBoxes = shape.map(s => boundingBox(s))
      //Get lower-left coordinates
      val xMin = boundingBoxes.reduceLeft((x1,x2) => if (x1.x < x2.x) x1 else x2).x
      val yMin = boundingBoxes.reduceLeft((y1,y2) => if (y1.y < y2.y) y1 else y2).y
      //Get upper-right coordinates
      val xMax = boundingBoxes.reduceLeft((x1,x2) => if (x1.x + x1.shape.asInstanceOf[Rectangle].width > x2.x + x2.shape.asInstanceOf[Rectangle].width) x1 else x2)
      val yMax = boundingBoxes.reduceLeft((y1,y2) => if (y1.y + y1.shape.asInstanceOf[Rectangle].height > y2.y + y2.shape.asInstanceOf[Rectangle].height) y1 else y2)
      //Get width of bounding box
      val bWidth = (xMax.shape.asInstanceOf[Rectangle].width + xMax.x) - xMin
      //Get height of bounding box
      val bHeight = (yMax.shape.asInstanceOf[Rectangle].height + yMax.y) - yMin
      //Return the bounding box
      new Location(xMin, yMin, Rectangle(bWidth, bHeight))
    }
    
  }
}
