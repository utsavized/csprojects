package edu.luc.cs.laufer.cs473.shapealgebra

import java.awt.{Color, Graphics2D}

object TestFixturesExtended {

  val simpleStroke = Stroke(Color.ORANGE, Circle(50))

  val simpleFill = Fill(Rectangle(80, 120))

  val simpleOutline = Outline(Rectangle(80, 120))

  val simplePolygon = Fill(Polygon(
    Point(20, 20),
    Point(10, 80),
    Point(50, 110),
    Point(90, 70),
    Point(80, 10)
  ))

  val complexPolygon = Location(30, 40,
    Stroke(Color.ORANGE,
      Fill(
        Polygon(
	      Point(20, 20),
	      Point(10, 80),
	      Point(50, 110),
	      Point(90, 70),
	      Point(80, 10)
        )
      )
    )
  )
  
  val simpleExtended1 = Rotate(45, Rectangle(100, 100))

  val simpleExtended2 = Location(100, 200, Rotate(45, Rectangle(100, 100)))

  val simpleExtended3 = Location(100, 200, Rotate(60, Ellipse(200, 100)))

  val extendedGroup = 
    Location(50, 100, 
      Group( 
        Circle(20),
        Location(150, 50,
	      Stroke(Color.RED,
	        Group(
		      Fill(Rectangle(50, 30)),
		      Rectangle(300, 60),
		      Location(100, 200,
		        Stroke(Color.ORANGE,
			      Fill(Circle(50))
		        )
		      )
	        )
	      )
        ),
        Rectangle(100, 200)
      )
    )

  val extendedGroupRotate = 
    Location(50, 100, 
      Group( 
        Rotate(60, 
          Group(
            Circle(20),
	        Location(150, 50,
		      Stroke(Color.RED,
		        Group(
			      Fill(Rectangle(50, 30)),
			      Rectangle(300, 60),
			      Location(100, 200,
			        Stroke(Color.ORANGE,
				      Fill(Circle(50))
			        )
			      )
		        )
		      )
	        ),
	        Rectangle(100, 200)
	      )
	    ),
	    Location(-93, 341, Ellipse(100, 50))
	  )
    )

  def paintExtendedGroup(g: Graphics2D) = {
	g.translate(50, 100)
	g.drawArc(-20, -20, 40, 40, 0, 360)
	g.drawRect(0, 0, 100, 200)
	g.setColor(Color.RED)
	g.fillRect(150, 50, 50, 30)
	g.drawRect(150, 50, 300, 60)
	g.translate(250, 250)
	g.setColor(Color.ORANGE)
	g.fillArc(-50, -50, 100, 100, 0, 360)
	g.setColor(Color.BLACK)
	g.translate(-250, -250)
  }

  def paintExtendedGroupRotate(g: Graphics2D) = {
	g.translate(50, 100)
	g.rotate(scala.math.Pi / 3)
	g.drawArc(-20, -20, 40, 40, 0, 360)
	g.drawRect(0, 0, 100, 200)
	g.setColor(Color.RED)
	g.fillRect(150, 50, 50, 30)
	g.drawRect(150, 50, 300, 60)
	g.translate(250, 250)
	g.setColor(Color.ORANGE)
	g.fillArc(-50, -50, 100, 100, 0, 360)
	g.setColor(Color.BLACK)
	g.translate(-250, -250)
	g.rotate(- scala.math.Pi / 3)
	g.translate(-93, 341)
	g.drawArc(-100, -50, 200, 100, 0, 360)
  }
}
