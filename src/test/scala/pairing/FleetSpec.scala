package pairing

import pairing.vessel.offense.{Carrier, Destroyer, OffensiveVessel}
import scala.util.Random
import org.scalatest._
import pairing.vessel.Vessel
import pairing.vessel.support.{RefuelingVessel, RepairVessel, SupportVessel}

class FleetSpec extends FlatSpec with Matchers
{

  "A Fleet" should "pair attack and defence ships" in {
    val fleet = this.generateFleet()

    fleet.getType((ship: Vessel) => ship.isInstanceOf[SupportVessel]).length should be 25
    fleet.getType((ship: Vessel) => ship.isInstanceOf[OffensiveVessel]).length should be 25

    val pairedShips = fleet.pairOff()

    pairedShips.length should be 25

    pairedShips.count(pair => this.isAdjacent(pair)) should be 25
  }

  private def generateFleet(): Fleet =
  {
    val offenceiveGenerator = Array[(Int, Int) => OffensiveVessel](
      (x: Int,y: Int) => new Carrier(x,y),
      (x: Int,y: Int) => new Destroyer(x,y),
      (x: Int,y: Int) => new Destroyer(x,y)
    )

    val supportGenerator= Array[(Int, Int) => SupportVessel](
      (x: Int,y: Int) => new RefuelingVessel(x,y),
      (x: Int,y: Int) => new RepairVessel(x,y),
      (x: Int,y: Int) => new SupportVessel(x,y)
    )

    val shipRange = Array.range(1,26)

    val offence = shipRange.map(
      (rangeIndex: Int) => {
        val index = this.randFromRange(0, offenceiveGenerator.length - 1)
        val x = this.randFromRange(1, 100)
        val y = this.randFromRange(1, 100)
        offenceiveGenerator(index)(x, y)
      }
    )

    val support = shipRange.map(
      (rangeIndex: Int) => {
        val index = this.randFromRange(0, supportGenerator.length - 1)
        val x = this.randFromRange(1, 100)
        val y = this.randFromRange(1, 100)
        supportGenerator(index)(x, y)
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
    return false
  }

  private def isAdjacent(pair: Pair): Boolean =
  {
    if (this.outOfRange(pair.supportVessel.getX, pair.offensiveVessel.getX)) return false
    if (this.outOfRange(pair.supportVessel.getY, pair.offensiveVessel.getY)) return false
    return true
  }

}
