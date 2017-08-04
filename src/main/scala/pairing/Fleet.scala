package pairing

import pairing.vessel.offense.OffensiveVessel
import pairing.vessel.support.SupportVessel
import vessel.Vessel

class Fleet(private val ships: Array[Vessel]) {

  def getType(callback: (Vessel) => Boolean): Array[Vessel] = ships.filter(callback)

  def moveAdjacent(support: SupportVessel, offense: OffensiveVessel): Pair = {

  }


  def pairOff(): Array[Pair] = {
    val support = this.ships.filter((ship: Vessel) => ship.isInstanceOf[SupportVessel])
    val offense = this.ships.filter((ship: Vessel) => ship.isInstanceOf[OffensiveVessel])

    var pairs = Array[Pair]()

    for (i <- 0 to support.length) {
      pairs(this.moveAdjacent(support(i), offense(i)))
    }
  }

}

class Pair(val supportVessel: SupportVessel, val offensiveVessel: OffensiveVessel)