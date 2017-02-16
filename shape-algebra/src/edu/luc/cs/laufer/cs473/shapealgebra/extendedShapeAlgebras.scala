package edu.luc.cs.laufer.cs473.shapealgebra


object ExtendedShapeSize extends ExtendedShapeAlgebra[Int] {
  override def visitEllipse(e: Ellipse) = ShapeSize.visitEllipse(e)
  override def visitRectangle(r: Rectangle) = ShapeSize.visitRectangle(r)
  override def visitLocation(r: Int, l: Location) = ShapeSize.visitLocation(r,l)
  override def visitGroup(rs: Seq[Int], g: Group) = ShapeSize.visitGroup(rs, g)
  override def visitStroke(r: Int, s: Stroke) = r
  override def visitFill(r: Int, f: Fill) = r
  override def visitOutline(r: Int, o: Outline) = r
  override def visitRotate(a: Int, r: Rotate ) = a
  override def visitCircle(c: Circle) = 1
  override def visitPolygon(rs: Seq[Int], p: Polygon) = 1
  override def visitPoint(pt: Point) = 0
 }


object ExtendedShapeDepth extends ExtendedShapeAlgebra[Int] {
  override def visitEllipse(e: Ellipse) = ShapeSize.visitEllipse(e)
  override def visitRectangle(r: Rectangle) = ShapeSize.visitRectangle(r)
  override def visitCircle(c: Circle) = ExtendedShapeSize.visitCircle(c)
  override def visitPolygon(rs: Seq[Int], p: Polygon) = ExtendedShapeSize.visitPolygon(rs, p)
  override def visitPoint(pt: Point) = ExtendedShapeSize.visitPoint(pt)
  override def visitStroke(r: Int, s: Stroke) = 1 + ExtendedShapeSize.visitStroke(r, s)
  override def visitFill(r: Int, f: Fill) = 1 + ExtendedShapeSize.visitFill(r, f)
  override def visitOutline(r: Int, o: Outline) = 1 + ExtendedShapeSize.visitOutline(r, o)
  override def visitRotate(a: Int, r: Rotate ) = 1 + ExtendedShapeSize.visitRotate(a,r)
  override def visitLocation(r: Int, l: Location) = 1 + ShapeSize.visitLocation(r,l)
  override def visitGroup(rs: Seq[Int], g: Group) = 1 + g.shapes.map(s => ExtendedShapeDepth(s)).max
}




class ExtendedBoundingBox extends BoundingBox with ExtendedShapeAlgebra[Location] {
  override def visitStroke(r: Location, s: Stroke) = r
  override def visitPoint(pt: Point) = Location(0,0,pt)
  override def visitEllipse(e: Ellipse) = BoundingBox.visitEllipse(e)
  override def visitRectangle(r: Rectangle) = BoundingBox.visitRectangle(r)
  override def visitCircle(c: Circle) = BoundingBox.visitEllipse(Ellipse(c.radius, c.radius))
  override def visitFill(r: Location, f: Fill) = r
  override def visitOutline(r: Location, o: Outline) = r
  override def visitLocation(r: Location, l: Location) = BoundingBox.visitLocation(r,l)
  override def visitGroup(rs: Seq[Location], g: Group) = BoundingBox.visitGroup(rs,g)
  override def visitPolygon(rs: Seq[Location], p: Polygon) = {
    val points = p.points
    val minX = points.reduceLeft((x1,x2) => if (x1.x < x2.x) x1 else x2).x
    val maxX = points.reduceLeft((x1,x2) => if (x1.x > x2.x) x1 else x2).x
    val minY = points.reduceLeft((y1,y2) => if (y1.y < y2.y) y1 else y2).y
    val maxY = points.reduceLeft((y1,y2) => if (y1.y > y2.y) y1 else y2).y
    val width = maxX - minX
    val height = maxY - minY
    Location(minX, minY, Rectangle(width, height))
  }
  override def visitRotate(a: Location, r: Rotate ) = {
    val theta = r.angle
    val bb = ExtendedBoundingBox(r.shape)
    
    val lowerLeft = math.hypot(bb.x, bb.y)
    val upperLeft = math.hypot(bb.x, bb.y+(bb.shape.asInstanceOf[Rectangle].height))
    val lowerRight = math.hypot(bb.x+(bb.shape.asInstanceOf[Rectangle].width), bb.y)
    val upperRight = math.hypot(bb.x+(bb.shape.asInstanceOf[Rectangle].width), bb.y+(bb.shape.asInstanceOf[Rectangle].height))
    
    val lowerLeftTh = math.atan2(bb.y,bb.x) + math.toRadians(theta)
    val upperLeftTh = math.atan2(bb.y+(bb.shape.asInstanceOf[Rectangle].height), bb.x) + math.toRadians(theta)
    val lowerRightTh = math.atan2(bb.y, bb.x+(bb.shape.asInstanceOf[Rectangle].width)) + math.toRadians(theta)
    val upperRightTh = math.atan2(bb.y+(bb.shape.asInstanceOf[Rectangle].height), bb.x+(bb.shape.asInstanceOf[Rectangle].width)) + math.toRadians(theta)
    
    val xLowerLeft = lowerLeft*math.cos(lowerLeftTh)
    val yLowerLeft = lowerLeft*math.sin(lowerLeftTh)
    
    val xUpperLeft = upperLeft*math.cos(upperLeftTh)
    val yUpperLeft = upperLeft*math.sin(upperLeftTh)
    
    val xLowerRight = lowerRight*math.cos(lowerRightTh)
    val yLowerRight = lowerRight*math.sin(lowerRightTh)
    
    val xUpperRight = upperRight*math.cos(upperRightTh)
    val yUpperRight = upperRight*math.sin(upperRightTh)
   
    val xMin = List(xLowerLeft, xUpperLeft, xLowerRight, xUpperRight).min
    val yMin = List(yLowerLeft, yUpperLeft, yLowerRight, yUpperRight).min
    
    val xMax = List(xLowerLeft, xUpperLeft, xLowerRight, xUpperRight).max
    val yMax = List(yLowerLeft, yUpperLeft, yLowerRight, yUpperRight).max
    
    val bWidth = (xMax-xMin).abs
    val bHeight = (yMax-yMin).abs
    
    Location(xMin.toInt, yMin.toInt, Rectangle(bWidth.toInt, bHeight.toInt))
  }
}

object ExtendedBoundingBox extends ExtendedBoundingBox
