package clicker2.equipment

class Alchemy extends Equipment {

  this.name="Alchemy"

  override def goldPerClick(): Double = this.numberOwned*5000

  override def goldPerSecond(): Double = this.numberOwned*40

  override def costOfNextPurchase(): Double = {
    10*(this.numberOwned+1)
  }
}
