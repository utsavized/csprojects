package edu.luc.cs.laufer.cs473.shapealgebra

import java.awt.{Graphics2D, Color}

class ExtendedDraw extends Draw {

  override def draw(g: Graphics2D)(s: Shape): Unit = s match {
    case Circle(radius: Int) => draw(g)(Ellipse(radius, radius))
    case Polygon(points @_*) => points.map(p => g.drawLine(p.x, p.y, p.x, p.y))
    case Stroke(color: Color, shape: Shape) => {
      g.setColor(color)
      draw(g)(shape)
      g.setColor(new Color(255,255,255))
    }
    case Fill(shape: Shape) => fill(g)(shape)
    case Outline(shape: Shape) => outline(g)(shape)
    case Rotate(angle: Int, shape: Shape) => {
      g.rotate(math.toRadians(angle))
      draw(g)(shape)
      g.rotate(math.toRadians(-angle))
    }
    case _ => super.draw(g)(s)
  }

  def fill(g: Graphics2D)(s: Shape): Unit = s match {
    case Ellipse(hw, hh) => g.fillArc(-hw, -hh, 2 * hw, 2 * hh, 0, 360)
    case Rectangle(w, h) => g.fillRect(0, 0, w, h)
    case Circle(r) => g.fillArc(-r, -r, r * 2, r * 2, 0, 360)
    case Polygon(points @_*) => {
   
   /* This should technically work, but experienced some
    * issues with how padding is done in MainGraphics
    * 
      val xPoints = points.map(p => p.x)
      val yPoints = points.map(p => p.y)
      val nPoints = points.size
      points.map(p => g.fillPolygon(xPoints.toArray, yPoints.toArray, nPoints))
      */
      points.map(p => g.drawLine(p.x, p.y, p.x, p.y))
      
    }
    case _ => draw(g)(s)
  }
  
    def outline(g: Graphics2D)(s: Shape): Unit = s match {
    case Ellipse(hw, hh) => g.drawArc(-hw, -hh, 2 * hw, 2 * hh, 0, 360)
    case Rectangle(w, h) => g.drawRect(0, 0, w, h)
    case Circle(r) => g.drawArc(-r, -r, r * 2, r * 2, 0, 360)
    case Polygon(points @_*) => {
   
   /* This should technically work, but experienced some
    * issues with how padding is done in MainGraphics
    * 
      val xPoints = points.map(p => p.x)
      val yPoints = points.map(p => p.y)
      val nPoints = points.size
      points.map(p => g.drawPolygon(xPoints.toArray, yPoints.toArray, nPoints))
      */
      points.map(p => g.drawLine(p.x, p.y, p.x, p.y))
    }
    case _ => draw(g)(s)
  }
}

object ExtendedDraw extends ExtendedDraw {
  def apply(g: Graphics2D) = draw(g)(_)
}