package clicker2.networking

import akka.actor.Actor

case object Update

case object ClickGold

case object Save

case object Setup

case class BuyEquipment(equipmentID: String)

class GameActor(username: String) extends Actor {
  var the_game =new clicker2.Game(username)
  override def receive: Receive = {

    case Setup =>
      if(Database.playerExists(username)){
        Database.loadGame(username,the_game)
      }
      else{
        Database.createPlayer(username)
      }
    case Update =>
      the_game.update(System.nanoTime())
      sender()!the_game.toJSON()
    case Save =>
       Database.saveGame(username,the_game.gold,the_game.equipment("shovel").numberOwned,the_game.equipment("excavator").numberOwned,the_game.equipment("mine").numberOwned,the_game.lastUpdateTime)
    case ClickGold =>
      the_game.clickGold()
    case buy: BuyEquipment =>
      the_game.buyEquipment(buy.equipmentID)
  }
}
