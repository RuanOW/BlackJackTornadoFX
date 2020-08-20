package game

import card.Card
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleObjectProperty
import player.Player
import player.PlayerViewModel
import tornadofx.Controller
import tornadofx.ViewModel
import tornadofx.shuffle

class Game {
    val deckProperty = SimpleListProperty<SimpleObjectProperty<Card>>()
    val playerModel = PlayerViewModel(Player("DEALER"))
    var gameOverProperty = SimpleBooleanProperty(true)
    val dealerProperty = SimpleObjectProperty(playerModel)
    var playersProperty = SimpleListProperty<SimpleObjectProperty<Player>>()
}

class GameViewModel(val game: Game): ViewModel(){
    var gameOver = bind { game.gameOverProperty }
    var dealer = bind { game.dealerProperty }
    var players = bind { game.playersProperty }
    var deck = bind { game.deckProperty }

    init {
        val cardList = mutableListOf<SimpleObjectProperty<Card>>()
        for(rank in 1..13){
            for (suit in 1..4){
//                model.deck.value.add(SimpleObjectProperty(Card(rank, suit)))
                cardList.add(SimpleObjectProperty(Card(rank, suit)))
            }
        }
        cardList.shuffle()
        cardList.forEach {
            deck.value.add(it)
        }
    }

    fun updateGameState(){
        gameOver.value = !gameOver.value
    }

    fun addPlayer(player: Player){
        players.value.add(SimpleObjectProperty(player))
    }

    fun shuffleCards(){
        deck.value.shuffle()
    }

    fun draw() = deck.value.removeAt(deck.value.size-1)

}
