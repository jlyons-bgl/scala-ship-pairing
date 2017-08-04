package pairing.vessel.offense

import pairing.vessel.Vessel

class OffensiveVessel(x: Int, y: Int) extends Vessel(x,y)

class Carrier(x: Int, y: Int) extends OffensiveVessel(x,y)

class Destroyer(x: Int, y: Int) extends OffensiveVessel(x,y)

class WarShip(x: Int, y: Int) extends OffensiveVessel(x,y)