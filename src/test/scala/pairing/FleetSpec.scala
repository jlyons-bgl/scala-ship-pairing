package pairing

import pairing.vessel.offense.{Carrier, Destroyer, OffensiveVessel}
import scala.util.Random
import org.scalatest._
import pairing.vessel.Vessel
import pairing.vessel.support.{RefuelingVessel, RepairVessel, SupportVessel}

class FleetSpec extends FlatSpec with Matchers
{
  val offensiveGenerator: Array[(Int, Int) => OffensiveVessel] = Array(
    (x: Int,y: Int) => new Carrier(x,y),
    (x: Int,y: Int) => new Destroyer(x,y),
    (x: Int,y: Int) => new Destroyer(x,y)
  )

  val supportGenerator:  Array[(Int, Int) => SupportVessel] = Array(
    (x: Int,y: Int) => new RefuelingVessel(x,y),
    (x: Int,y: Int) => new RepairVessel(x,y),
    (x: Int,y: Int) => new SupportVessel(x,y)
  )

  "A Fleet" should "pair attack and support ships" in {

    val fleet = this.generateFleet()
    val supportCount = fleet.getType((ship: Vessel) => ship.isInstanceOf[SupportVessel]).length
    val offenseCount = fleet.getType((ship: Vessel) => ship.isInstanceOf[OffensiveVessel]).length
    val pairedShips = fleet.pairOff()
    val adjacentPairs = pairedShips.count(pair => this.isAdjacent(pair))

    supportCount should be (25)
    offenseCount should be (25)
    pairedShips.length should be (25)
    adjacentPairs should be (25)
  }

  private def generateFleet(): Fleet = {
    val shipRange = Array.range(1,26)

    val offence = shipRange.map(_ => getShipRange(offensiveGenerator))

    val support = shipRange.map(_ => getShipRange(supportGenerator))

    new Fleet(support ++ offence)
  }

  private def getShipRange[T](gen: Array[(Int, Int) => T]) = {
    val index = randFromRange(0, gen.length - 1)
    val x = randFromRange(1, 100)
    val y = randFromRange(1, 100)
    gen(index)(x, y)
  }

  private def randFromRange(start: Int, end: Int): Int = {
    start + Random.nextInt(end)
  }

  private def outOfRange(a: Int, b: Int): Boolean = {
    if (a > b+1 || a < b-1) return true
    if (a == b) return true
    false
  }

  private def isAdjacent(pair: Pair): Boolean = {
    if (outOfRange(pair.supportVessel.getX, pair.offensiveVessel.getX)) return false
    if (outOfRange(pair.supportVessel.getY, pair.offensiveVessel.getY)) return false
    true
  }

}
