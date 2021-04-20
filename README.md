# Warlords 

**Warlords** è un gioco di strategia-assedio pvp 2D ispirato a [Warlords 2 – Rise of Demons]( https://www.flashgames.it/warlords2.html).

Il gioco consiste nello **scontro** tra due giocatori, con entrambi dei punteggi. I giocatori hanno a disposizione diverse tipologie di truppe da schierare in combattimento, nelle corsie, contro le truppe dell’avversario con lo scopo di raggiungere la fine della corsia per fare **punto**.


# Indicazioni

## Prerequisiti

Occorre aver installato correttamente il [JDK](https://adoptopenjdk.net/index.html?variant=openjdk11&jvmVariant=hotspot) sulla propria macchina.

## Avvio

Per far partire il gioco basta lanciare da terminale: 
```bash
java -jar oop-warlords.jar
```

## Documentazione
Consultabile nella cartella ...

## Relazione
Contenuta nel file Relazione.pdf


# Credits: 
* Giosuè Giocondo Mainardi
* Filippo Tartagni
* Andrea Zacconi
* Giacomo Magrini

n

n

n

n

n

n

n

n

n

n

n

n

n

n               
# Testo di riferimento:

### Using sets

Observe `it.unibo.oop.lab05.ex1.UseCollection`, and use it as example to complete the exercise in `it.unibo.oop.lab05.ex1.UseSet`.
Before proceeding, please read and make sure you understand the following notes.

##### Important Notes

**Natural Ordering**: Elements in a `TreeSet` are sorted by *natural order*, i.e., the type `X` used within `TreeSet<X>` must implement the interface `Comparable<? super X>`. As such, for instance:

* A `TreeSet<String>` allows new elements to be added by using the `add` method because `String` implements `Comparable<String>`;

* conversely, trying to add elements of type `MyType` to a `TreeSet<MyType>`, in the hypothesis that `MyType` **does not** implement `Comparable<MyType>`, would raise a run-time error of type `ClassCastException` (details on exceptions and error handling will be provided in the next lessons).

**Concurrent modifications**: All the iterators created from instances of the `TreeSet<E>` class (actually, iterators created by any of the collections provided with the JDK standard library) are *fail-fast*.

* try to write a `for`-each cycle iterating over `TreeSet` (which, internally, generates and uses an `Iterator`), and within the cycle try to remove an nelement from the `TreeSet`. What happens?

The correct way to remove elements from a collections while iterating it is to use the `remove()` method of `Iterator`.
This requires a reference to the iterator, and, as such, cannot be used from within a `for`-each cycle.


### Custom comparators

* Follow the comments in `it.unibo.oop.lab05.ex2.UseSetWithOrder`, and create a program that sorts a `TreeSet<String>` using a custom `Comparator<String>` (to be created separately, from scratch).

* Refer to the [java documentation](https://docs.oracle.com/javase/8/docs/api/) to understand how to create a `Comparator` [(interface documentation)](https://docs.oracle.com/javase/8/docs/api/java/util/Comparator.html).
