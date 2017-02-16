package edu.luc.cs.laufer.cs473.shapealgebra
import java.awt.Color
/**
 * The category of shape algebras extended to support additional shapes.
 */
trait ExtendedShapeAlgebra[R] extends ShapeAlgebra[R] {

  def visitPoint(pt: Point): R
  def visitCircle(c: Circle): R
  def visitPolygon(rs:Seq[R], p: Polygon): R
  def visitStroke(r: R, s: Stroke): R
  def visitFill(r: R, f: Fill): R
  def visitOutline(r: R, o: Outline): R
  def visitRotate(a: R, r: Rotate): R
  
  /**
   * The extended catamorphism for shapes.
   */
  override def fold(s: Shape): R = s match {
    case pt: Point => visitPoint(pt)
    case cr: Circle => visitCircle(cr)
  	case pl: Polygon => visitPolygon(pl.points.map(fold(_)), pl)
  	case st: Stroke => visitStroke(fold(st.shape), st)
  	case fl: Fill => visitFill(fold(fl.shape), fl)
  	case ot: Outline => visitOutline(fold(ot.shape), ot)
  	case ro: Rotate => visitRotate(fold(ro.shape), ro)
    case _ => super.fold(s)
  }
}
