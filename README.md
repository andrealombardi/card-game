
## Assumptions:

- It was not clear from the specs what top() and bottom() should do. Since it was clearly specified that draw() would
remove the card from the deck, I assumed that top() and bottom() would simply return the card, without removing it.

## General design considerations

- The deck is injected with a CardFactory. This keeps the concern of creating the cards separate from
the logic of handling the deck. This separation improves the testability and the re-usability of the deck.

- The deck module has no dependencies on the card module.

- I decided to throw an IllegalStateException when a method is invoked on an empty Deck. Alternative strategies could 
have been returning  Optional values or blocking till a shuffle() had been invoked.

- The default implementation of the Card is immutable. As a result the same instance of the card can be shared across
multiple decks.

- The PokerCardFactory has a single, immutable, static copy of the set of cards. This keeps the memory footprint low as, 
no matter how many decks are created, there will only be 52 card objects in memory.

- The CardDeck is not thread safe.

## Performance:

- The main data structure is of the CardDeck is an ArrayList.

The ArrayList was chosen because it would give better performance on most operations, but paying the price in drawing 
the card. The time complexity of other data structures was considered

| Method        | LinkedList    | ArrayList  |  NavigableSet |
| ------------- | ------------- | -----------| --------------|
| top()         |  O(1)         |     O(1)   |   O(log n)    |
| bottom()      |  O(n)*        |     O(1)   |   O(log n)    |
| draw()        |               |            |               |
|   - search    |  O(n)         |     O(1)   |   O(log n)    |
|   - remove    |  O(1)         |     O(n)   |   O(log n)    |
| shuffle()     |  O(n)         |     O(n)   |   O(n log n)  |

*could be O(1) with a doubly linked list.

A NavigableSet could have been adopted by adding a randomly assigned comparable value to the card. If draw() was the
predominant operation this option should be considered more carefully.

