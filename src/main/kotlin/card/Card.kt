package card

import javafx.beans.property.SimpleIntegerProperty
import javafx.beans.property.SimpleStringProperty
import javafx.geometry.Pos
import javafx.scene.Parent
import javafx.scene.effect.DropShadow
import javafx.scene.paint.Color
import javafx.scene.text.FontWeight
import tornadofx.*

// The Card Model
class Card(rank: Int, suit: Int) {
    val rankProperty = SimpleIntegerProperty(this,"rank", rank)
    val suitProperty = SimpleIntegerProperty(this,"suit", suit)
    val rankName = when(rank){
        1 -> "Ace"
        11 -> "Jack"
        12 -> "Queen"
        13 -> "King"
        else -> rank.toString()
    }
    val rankNameProperty = SimpleStringProperty(rankName)

    val suitName = when(suit){
        1 -> "Hearts"
        2 -> "Diamonds"
        3 -> "Clubs"
        4 -> "Spades"
        else -> "Invalid Card Suit"
    }
    val suitNameProperty = SimpleStringProperty(suitName)

    val blackJackValue = when(rank){
        1 -> 11
        11 -> 10
        12 -> 10
        13 -> 10
        else -> rank
    }
    val BlackJackValueProperty = SimpleIntegerProperty(blackJackValue)
}

// The CardViewModel
class CardViewModel(val card: Card): ViewModel(){
    val rank = bind { card.rankProperty }
    val suit = bind { card.suitProperty }
    val rankName = bind { card.rankProperty }
    val suitName = bind { card.suitProperty }
}

// The CardView
class CardView(val model: CardViewModel): View(){

    fun cardColour(): Color {
        return when(model.suit.value){
            1, 2 -> Color.RED
            3, 4 -> Color.BLACK
            else -> Color.GREEN
        }
    }

    fun rankIcon(): String {
        return when(model.rank.value){
            1 -> "A"
            11 -> "J"
            12 -> "Q"
            13 -> "K"
            else -> model.rank.value.toString()
        }
    }

    override val root = stackpane {
        setMaxSize(120.0, 170.0)
//        setMinSize(12.0, 17.0)

//        style {
//            backgroundColor += Color.LIGHTBLUE
//        }

        effect = DropShadow(5.0, Color.BLACK)
        rectangle {
            arcHeight = 15.0
            arcWidth = 15.0
            width = 120.0
            height = 170.0
            style {
                fill = Color.WHITE
            }
        }

        rectangle {
            arcHeight = 10.0
            arcWidth = 10.0
            width = 100.0
            height = 150.0
            stroke = cardColour()
            strokeWidth = 2.0
            fill = Color.TRANSPARENT
        }
        label(rankIcon()) {
            stackpaneConstraints {
                alignment = Pos.TOP_LEFT
                marginTop = 15.0
                marginLeft = 15.0
            }
            style {
                textFill = cardColour()
                fontWeight = FontWeight.EXTRA_BOLD
            }
        }

        label(rankIcon()) {
            stackpaneConstraints {
                alignment = Pos.BOTTOM_RIGHT
                marginBottom = 15.0
                marginRight = 15.0
            }
            style {
                textFill = cardColour()
                fontWeight = FontWeight.EXTRA_BOLD
            }
        }
    }

}
