package gameOfLife

import javax.swing.JFrame

object GameOfLife {
  def main(args : Array[String]) {
    val frame : JFrame = new JFrame("Conway's Game of Life")
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE)
    val g = new Grid(10, 20)
    frame.getContentPane().add(g)
    frame.pack()
    frame.setVisible(true)
    Thread.sleep(5000) // Have some time to add starting patterns
    g.play()
  }
}
