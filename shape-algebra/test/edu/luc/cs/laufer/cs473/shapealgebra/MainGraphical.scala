package edu.luc.cs.laufer.cs473.shapealgebra

import scala.swing._
import java.awt.{Point => AWTPoint,Dimension,Graphics2D}

import TestFixtures.{complexGroup,paintComplexGroup}

object MainGraphical {
  def main(args : Array[String]) { }

  val s = complexGroup
  val b @ Location(x, y, Rectangle(w, h)) = BoundingBox(s)
  println("shape = " + s)
  println("bounding box = " + b)
  val padding = 20

  val top1 = new MainFrame {
    override def closeOperation() { System.exit(0) }
    title = "drawn by Draw function"
    location = new AWTPoint(0, 0)
    contents = new Panel {
      preferredSize = new Dimension(w + 2 * padding, h + 2 * padding)
	  override def paint(g: Graphics2D) = {
		g.translate(-x + padding, -y + padding)
		Draw(g)(s)
		Draw(g)(b)
	  }
    }
	pack()
	visible = true
  }

  // now draw the same complex group of shapes by hand
  // (without the bounding box)
  val top2 = new MainFrame {
    override def closeOperation() { System.exit(0) }
    title = "drawn directly"
    location = new AWTPoint(w + 2 * padding, 0)
    contents = new Panel {
      preferredSize = new Dimension(470 + 2 * padding, 320 + 2 * padding)
	  override def paint(g: Graphics2D) = {
    	g.translate(-10, -60)
    	paintComplexGroup(g)
	  }
    }
	pack()
	visible = true
  }
}
