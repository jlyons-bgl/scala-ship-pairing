package pairing.vessel

class Vessel(protected var x: Int, protected var y: Int)
{

  checkOutOfBounds(x)
  checkOutOfBounds(y)

  def checkOutOfBounds(x: Int): Unit = {
    if (0 > x || x > 100) throw new Exception("ship out of bounds")
  }

  def getX: Int = x
  def getY: Int = y

  def move(x: Int, y: Int): Unit = {
    checkOutOfBounds(x + this.x)
    checkOutOfBounds(y + this.y)
    this.x += x
    this.y += y
  }
}
