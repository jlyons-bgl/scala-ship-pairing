package pairing

import pairing.vessel.offense.{Carrier, Destroyer, OffensiveVessel}
import scala.util.Random
import org.scalatest._
import pairing.vessel.Vessel
import pairing.vessel.support.{RefuelingVessel, RepairVessel, SupportVessel}

class FleetSpec extends FlatSpec with Matchers
{

  "A Fleet" should "pair attack and support ships" in {

    val fleet = this.generateFleet()
    val supportCount = fleet.getType((ship: Vessel) => ship.isInstanceOf[SupportVessel]).length
    val offenseCount = fleet.getType((ship: Vessel) => ship.isInstanceOf[OffensiveVessel]).length
    val pairedShips = fleet.pairOff()

    supportCount should be (25)
    offenseCount should be (25)
    pairedShips.length should be (25)
    pairedShips.count(pair => this.isAdjacent(pair)) should be (25)
  }

  private def generateFleet(): Fleet =
  {
    val shipRange = Array.range(1,26)

    val offence = shipRange.map(
      (rangeIndex: Int) => {
        val index = this.randFromRange(0, ShipGen.offenceiveGenerator.length - 1)
        val x = this.randFromRange(1, 100)
        val y = this.randFromRange(1, 100)
        ShipGen.offenceiveGenerator(index)(x, y)
      }
    )

    val support = shipRange.map(
      (rangeIndex: Int) => {
        val index = this.randFromRange(0, ShipGen.supportGenerator.length - 1)
        val x = this.randFromRange(1, 100)
        val y = this.randFromRange(1, 100)
        ShipGen.supportGenerator(index)(x, y)
      }
    )

    new Fleet(support ++ offence)
  }

  private def randFromRange(start: Int, end: Int): Int =
  {
    start + Random.nextInt(end)
  }

  private def outOfRange(a: Int, b: Int): Boolean =
  {
    if (a > b+1 || a < b-1) return true
    if (a == b) return true
    false
  }

  private def isAdjacent(pair: Pair): Boolean =
  {
    if (this.outOfRange(pair.supportVessel.getX, pair.offensiveVessel.getX)) return false
    if (this.outOfRange(pair.supportVessel.getY, pair.offensiveVessel.getY)) return false
    true
  }

}

object ShipGen {
  val offenceiveGenerator: Array[(Int, Int) => OffensiveVessel] = Array(
    (x: Int,y: Int) => new Carrier(x,y),
    (x: Int,y: Int) => new Destroyer(x,y),
    (x: Int,y: Int) => new Destroyer(x,y)
  )

  val supportGenerator:  Array[(Int, Int) => SupportVessel] = Array(
    (x: Int,y: Int) => new RefuelingVessel(x,y),
    (x: Int,y: Int) => new RepairVessel(x,y),
    (x: Int,y: Int) => new SupportVessel(x,y)
  )
}
