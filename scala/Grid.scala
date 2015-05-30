package gameOfLife

import java.awt.{Dimension, GridLayout}
import java.awt.event.{ActionEvent, ActionListener}
import javax.swing.{JPanel, Timer}
import Array.ofDim

class Grid(val numRows : Int, val numCols : Int)
  extends JPanel(new GridLayout(numRows, numCols)){
  
  private var cells = ofDim[Cell](numRows, numCols)
  private val timer = new Timer(1000, new NextGenerationListener())
  for (row <- 0 to (numRows - 1)) {
    for (col <- 0 to (numCols - 1)) {
      cells(row)(col) = new Cell()
      add(cells(row)(col))
    }
  }
  setPreferredSize(new Dimension(Grid.panelWidth, Grid.panelHeight))
  
  def this(original : Grid) {
    this(original.numRows, original.numCols)
    for (row <- 0 to (numRows - 1)) {
      for (col <- 0 to (numCols - 1)) {
        cells(row)(col) = new Cell(original.cells(row)(col))
      }
    }
  }
  
  def updateDelay(delay : Int) : Unit = {
    timer.setDelay(delay)
  }
  
  def play() : Unit = {
    timer.start()
    System.out.println("Timer started")
  }
  
  def stop() : Unit = {
    if (timer.isRunning()) {
      timer.stop()
    }
  }
  
  def clear() : Unit = {
    for (row <- 0 to (numRows - 1)) {
      for (col <- 0 to (numCols - 1)) {
        cells(row)(col).setBackground(Cell.dead)
        cells(row)(col).repaint()
      }
    }
  }
  
  private def withinBounds(row : Int, col : Int) : Boolean = {
    (0 <= row && row < numRows) && (0 <= col && col < numCols)
  }
  
  private def neighbor(row : Int, col: Int, deltaRow : Int, deltaCol : Int) : 
    Option[Cell] = {
    val newRow = row + deltaRow
    val newCol = col + deltaCol
    if (withinBounds(newRow, newCol)) {
      Some(cells(newRow)(newCol))
    } else {
      None
    }
  }
  
  private def upperLeft(row : Int, col : Int) : Option[Cell] = {
    neighbor(row, col, Grid.upper, Grid.left)
  }
 
  private def upper(row : Int, col : Int) : Option[Cell] = {
    neighbor(row, col, Grid.upper, 0)
  }
  
  private def upperRight(row : Int, col : Int) : Option[Cell] = {
    neighbor(row, col, Grid.upper, Grid.right)
  }
  
  private def left(row : Int, col : Int) : Option[Cell] = {
    neighbor(row, col, 0, Grid.left)
  }
  
  private def right(row : Int, col : Int) : Option[Cell] = {
    neighbor(row, col, 0, Grid.right)
  }
  
  private def lowerLeft(row : Int, col : Int) : Option[Cell] = {
    neighbor(row, col, Grid.lower, Grid.left)
  }
 
  private def lower(row : Int, col : Int) : Option[Cell] = {
    neighbor(row, col, Grid.lower, 0)
  }
  
  private def lowerRight(row : Int, col : Int) : Option[Cell] = {
    neighbor(row, col, Grid.lower, Grid.right)
  }
  
  private def isAlive(optionCell : Option[Cell]) : Boolean =
    optionCell match {
    case Some(cell) => cell.isAlive()
    case None => false
  }
  
  private def neighbors(row : Int, col : Int) : List[Option[Cell]] = {
    List(upperLeft(_,_), upper(_,_), upperRight(_,_),
     left(_,_), right(_,_),
     lowerLeft(_,_), lower(_,_), lowerRight(_,_)).map(
         (f : (Int, Int) => Option[Cell]) => f(row, col))
  }
  
  private def numLivingNeighbours(row : Int, col : Int) : Int = {
      (neighbors(row, col)).filter(isAlive).length
  }
  
  private def underPopulation(row : Int, col : Int) : Boolean = {
    if (cells(row)(col).isAlive) {
      numLivingNeighbours(row, col) < 2
    } else {
      false
    }
  }
  
  private def overCrowding(row : Int, col : Int) : Boolean = {
    if (cells(row)(col).isAlive) {
      numLivingNeighbours(row, col) > 3
    } else {
      false
    }
  }
  
  private def reproduction(row : Int, col : Int) : Boolean = {
    if (!cells(row)(col).isAlive) {
      numLivingNeighbours(row, col) == 3
    } else {
      false
    }
  }
  
  private def updateCell(g : Grid, row : Int, col : Int) : Unit =  {
    if (g.underPopulation(row, col) || g.overCrowding(row, col)) {
      cells(row)(col).setBackground(Cell.dead)
    } else if (g.reproduction(row, col)) {
      cells(row)(col).setBackground(Cell.alive)
    } // else => retain original coloring
  }
    
  
  private def update() : Unit = {
    val g = new Grid(this)
    val threads = List.empty[Thread]
    
    for (row <- 0 to (numRows - 1)) {
      for (col <- 0 to (numCols - 1)) {
        val t = new Thread(new Runnable {
          def run() {
            updateCell(g, row, col) // Look at the copy, not the original!
                                   // Otherwise, some cells may try to update
                                   // based on already-updated cells
          }
        })
        t::threads
        t.start
      }
    }
    
    for (t <- threads) {
      t.join
    }
    
  }
  
  
  private class NextGenerationListener extends ActionListener {
    def actionPerformed(event : ActionEvent): Unit = {
      update()
      for (row <- 0 to (numRows - 1)) {
        for (col <- 0 to (numCols - 1)) {
          cells(row)(col).repaint()
        }
      }
      revalidate()
      repaint()
    }
  }
  
}

object Grid extends JPanel { // Companion Object
  val panelWidth = 900
  val panelHeight = 450
  private val left = -1
  private val right = 1
  private val upper = -1
  private val lower = 1
}
