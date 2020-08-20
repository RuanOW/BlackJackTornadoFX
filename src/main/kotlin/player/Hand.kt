package player

import card.Card
import card.CardView
import card.CardViewModel
import javafx.beans.property.SimpleListProperty
import javafx.beans.property.SimpleObjectProperty
import tornadofx.*


class Hand {
    val cardsProperty = SimpleListProperty<SimpleObjectProperty<Card>>()
    private var handValue = 0
    var bust = false // Need to convert to simple prop
    var isBlackJack = false
}

class HandViewModel(val hand: Hand): ViewModel(){
    val cards = bind { hand.cardsProperty }

    fun addCardToHand(card: Card){
//        println("Card to Be Added ${card.suitName}")
        cards.value.add(SimpleObjectProperty(card))
//        println("Cards Size: ${cards.value[0].value.suitName}")
    }
}

class HandView(val viewModel: HandViewModel): View(){

    override val root = hbox {
//        model.cards.value.onChange {
//            println("Cards OnChange: ${it}")
//        }
//        println("Printing from HandView ${model.cards.value.size}")
        flowpane {
            hgap = 10.0
            vgap = 10.0
            children.bind(viewModel.cards.value){
//                println("Card In HandView ${it.value.suitName}")
                CardView(CardViewModel(it.get())).root
            }
        }
    }
}