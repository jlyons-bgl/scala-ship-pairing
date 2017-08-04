package pairing

import pairing.vessel.offense.{Carrier, Destroyer, OffensiveVessel}
import scala.util.Random
import collection.mutable.Stack
import org.scalatest._
import pairing.vessel.Vessel
import pairing.vessel.support.{RefuelingVessel, RepairVessel, SupportVessel}

class FleetSpec extends FlatSpec with Matchers {
  private def generateFleet() = {

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

    val offence = shipRange.map((): Vessel => {
      val index = this.randFromRange(0, 3)
      offenciveGenerator(index)()
    })

  }

  def randFromRange(start: Int, end: Int): Int = {
    start + Random.nextInt(end)
  }

  "A Fleet" should "pair attack and defence ships" in {
    val fleet = this.generateFleet()
  }

}
