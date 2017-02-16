package edu.luc.cs.laufer.cs473.shapealgebra

import java.awt.Graphics2D

class Draw {
  def draw(g: Graphics2D)(s: Shape): Unit = s match {
    case Ellipse(hw, hh) => g.drawArc(-hw, -hh, 2 * hw, 2 * hh, 0, 360)
    case Rectangle(w, h) => g.drawRect(0, 0, w, h)
    case Location(x, y, shape) => {
    	//Translate into new location
    	g.translate(x,y)
    	//Draw shape
    	draw(g)(shape)
    	//Reset location
    	g.translate(-x, -y)
    }
    case Group(shapes @_*) => {
      //Map shapes into each case
      shapes.map(z => draw(g)(z))
    }
  }
}

object Draw extends Draw {
  def apply(g: Graphics2D) = draw(g)(_)
}