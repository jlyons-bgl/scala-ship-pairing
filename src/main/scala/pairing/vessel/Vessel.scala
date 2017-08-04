package pairing.vessel

class Vessel(protected var x: Int, protected var y: Int)
{
  def getX: Int = x
  def getY: Int = y

  def move(x: Int, y: Int): Unit =
  {
    if (0 > (x + this.x) || (x + this.x) > 100) throw new Exception("ship out of bounds")
    this.x += x
    this.y += y
  }
}
