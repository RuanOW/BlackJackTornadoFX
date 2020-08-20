
import card.Card
import card.CardView
import card.CardViewModel
import game.Game
import game.GameViewModel
import javafx.beans.property.SimpleBooleanProperty
import javafx.scene.Parent
import javafx.scene.paint.Color
import javafx.util.StringConverter
import player.Player
import player.PlayerView
import player.PlayerViewModel
import tornadofx.*

class GameView: View("BlackJack") {

    val gameViewModel = GameViewModel(Game())

    override val root = vbox{

        style {
            backgroundColor += Color.GREEN
        }
        setPrefSize(1440.0, 960.0)

        val dealer = gameViewModel.dealer.value.player
        val dealerViewModel = PlayerViewModel(dealer)

        add(PlayerView(dealerViewModel).root)

        button("Deal Cards") {
            action {
                gameViewModel.addPlayer(Player("Player 1"))
                gameViewModel.addPlayer(Player("Player 2"))
                gameViewModel.addPlayer(Player("Player 3"))
                gameViewModel.dealer.value.addCardToHand(gameViewModel.draw().value)
                gameViewModel.dealer.value.addCardToHand(gameViewModel.draw().value)
                gameViewModel.players.value.forEach {
                    PlayerViewModel(it.value).addCardToHand(gameViewModel.draw().value)
                    PlayerViewModel(it.value).addCardToHand(gameViewModel.draw().value)
                }
            }
        }

        flowpane {
            children.bind(gameViewModel.players.value){
                val playerViewModel = PlayerViewModel(it.value)
                val player = PlayerView(playerViewModel)

                player.root.children.add(
                    button("HIT") {
                        action {
                            playerViewModel.addCardToHand(gameViewModel.draw().value)
                        }
                    }
                )

                player.root.children.add(
                        button("STAND") {
                            action {
//                                playerViewModel.addCardToHand(gameViewModel.draw().value)
                            }
                        }
                )

                player.root
            }
        }

    }


//    var toggle = SimpleBooleanProperty()
//    val boolToggle by toggle
//
//    override val root = hbox {
//
//        paddingAll = 10.0
//        spacing = 10.0
//        add(CardView(CardViewModel(Card(1, 4))))
//        add(CardView(CardViewModel(Card(2, 1))))
//        add(CardView(CardViewModel(Card(4, 2))))
//        add(CardView(CardViewModel(Card(11, 3))))
//        add(CardView(CardViewModel(Card(12, 4))))
//
//        label(toggle.value.toString()) {
//            toggle.onChange {
//                text = it.toString()
////                isVisible = it
//            }
//        }
//
//        button("Toggle") {
//            action {
//                toggle.value = !toggle.value
//                println(toggle.value)
//            }
//        }
//
//
//    }
}
