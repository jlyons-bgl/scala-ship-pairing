package pairing.vessel

import pairing.Fleet


class CommandShip(
  private val fleet: Fleet,
  x: Int,
  y: Int
) extends Vessel(x, y)
