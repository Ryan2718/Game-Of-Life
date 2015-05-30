package gameOfLife

import java.awt.Color
import java.awt.event.{MouseEvent, MouseListener}
import javax.swing.{BorderFactory, JPanel}
import javax.swing.border.Border

class Cell extends JPanel {
  private val border : Border = BorderFactory.createLineBorder(Color.BLACK, 1)
  setBorder(border)
  setBackground(Cell.dead)
  addMouseListener(new CellListener())
  
  def this(original : Cell) {
    this()
    setBackground(original.getBackground())
  }
  
  def isAlive() : Boolean = {
    getBackground() == Cell.alive
  }
  
  private class CellListener extends MouseListener {
    def mouseClicked(e : MouseEvent) : Unit = {
      if (getBackground() == Cell.alive) {
        setBackground(Cell.dead)
      } else {
        setBackground(Cell.alive)
      }
    }
    def mousePressed(e : MouseEvent) : Unit = {}
    def mouseReleased(e : MouseEvent) : Unit = {}
    def mouseEntered(e : MouseEvent) : Unit = {}
    def mouseExited(e : MouseEvent) : Unit = {}
  }
  
}

object Cell extends JPanel { // Companion Object
  val alive : Color = Color.BLACK
  val dead : Color = Color.WHITE
}
