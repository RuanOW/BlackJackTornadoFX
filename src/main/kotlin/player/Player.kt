package player

import card.Card
import javafx.beans.property.SimpleBooleanProperty
import javafx.beans.property.SimpleFloatProperty
import javafx.beans.property.SimpleObjectProperty
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.paint.Color
import javafx.scene.text.TextAlignment
import tornadofx.*

class Player(name: String){
    var hand = SimpleObjectProperty(HandViewModel(Hand()))
    val nameProperty = SimpleStringProperty(name)
    var isSecondHandProperty = SimpleBooleanProperty(false)
    var cashProperty = SimpleFloatProperty(1000000f)
    var betPlayed = SimpleFloatProperty(0f)
    var active = SimpleBooleanProperty(true)
    val dealer = SimpleBooleanProperty(name == "DEALER")
}

class PlayerViewModel(val player: Player): ViewModel(){

    val name = bind { player.nameProperty }
    val isSecondHand = bind { player.isSecondHandProperty }
    val cashProperty = bind { player.cashProperty }
    val betPlayed = bind { player.betPlayed }
    val active = bind { player.active }
    val isDealer = bind { player.dealer }
    val hand = bind { player.hand }

    fun addCardToHand(card: Card){
        player.hand.value.addCardToHand(card)
    }
    fun dealCardToHand(card: Card){
        player.hand.value.addCardToHand(card)
    }
}

class PlayerView(val model: PlayerViewModel): View(){

    override val root = vbox {
        spacing = 10.0
        prefWidth = 400.0
        style {
            backgroundColor += Color.GREEN
            textAlignment = TextAlignment.CENTER
        }
        label(model.name.value) {
            textFill = Color.BLACK

            vboxConstraints {
                alignment = Pos.CENTER
            }
            style{
//                backgroundColor += Color.DARKGRAY
                fontSize = 20.px
            }
        }

        add(HandView(model.hand.value).root)

    }
}
