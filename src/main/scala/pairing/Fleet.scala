package pairing

import pairing.vessel.offense.OffensiveVessel
import pairing.vessel.support.SupportVessel
import vessel.Vessel

class Fleet(private val ships: Array[Vessel]) {

  def moveAdjacent(support: SupportVessel, offense: OffensiveVessel): Pair = {
    val xdiff = support.getX - offense.getX + (if (support.getX == 100) -1 else 1)
    val ydiff = support.getY - offense.getY + (if (support.getY == 100) -1 else 1)


    offense.move(xdiff, ydiff)
    new Pair(support, offense)
  }


  def getType(callback: (Vessel) => Boolean): Array[Vessel] = ships.filter(callback)



  def pairOff(): IndexedSeq[Pair] = {
    val support = this.ships.collect { case ship: SupportVessel => ship }

    val offense = this.ships.collect { case ship: OffensiveVessel => ship }

    support.indices.map((i: Int) => this.moveAdjacent(support(i), offense(i)))
  }

}

