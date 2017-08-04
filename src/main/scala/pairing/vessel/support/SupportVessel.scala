package pairing.vessel.support

import pairing.vessel.Vessel

class SupportVessel(x: Int, y: Int) extends Vessel(x, y)

class RefuelingVessel(x: Int, y: Int ) extends SupportVessel(x,y)

class SupplyVessel(x: Int, y: Int) extends SupportVessel(x,y)

class RepairVessel(x: Int, y: Int) extends SupportVessel(x,y)