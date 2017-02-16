package edu.luc.cs.laufer.cs473.shapealgebra

import java.awt.Graphics
import java.awt.Color

object TestFixtures {
  
  val simpleEllipse = Ellipse(50, 50)

  val simpleRectangle = Rectangle(80, 120)

  val simpleLocation = Location(70, 30, Rectangle(80, 120))

  val simpleGroup = Group(
    Location(200, 100, Ellipse(50, 50)),
	Location(400, 300, Rectangle(100, 50))
  )

  val complexGroup = Location(50, 100,
    Group(
	  Ellipse(20, 20),
	  Location(150, 50,
        Group(
	      Rectangle(50, 30),
	      Rectangle(300, 60),
	      Location(100, 200,
	        Ellipse(50, 50)
	      )
	    )
	  ),
      Rectangle(100, 200)
    )
  )

  def paintComplexGroup(g: Graphics) = {
	g.translate(50, 100)
	g.drawArc(-20, -20, 40, 40, 0, 360)
	g.drawRect(0, 0, 100, 200)
	g.drawRect(150, 50, 50, 30)
	g.drawRect(150, 50, 300, 60)
	g.translate(250, 250)
	g.drawArc(-50, -50, 100, 100, 0, 360)
  }
}
