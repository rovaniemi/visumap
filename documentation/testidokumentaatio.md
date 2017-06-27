# Testausdokumentaatio

## Yksikkötestaus

Ohjelman testauksessa on käytetty JUnit testejä tietorakenteiden, algoritmien sekä toiminnallisuuden testaamiseen. JUnit testeistä on tehty dokumentaatio käyttäen apuna codecovia, jacocoa ja travisia. Dokumentaatio löytyy [täältä](https://codecov.io/gh/rovaniemi/visumap). Kaikki JUnit testit menevät läpi yksittäin ja yhdessä.

## Suorituskykytestaus

### MinHeap - tietorakenne

Määrä | operaatio | MinHeap | PriorityQuoeue
------|------------|---------|----------------
10000    | Weightin lisäys | 3 ms | 7 ms
100000   | Weightin lisäys | 121 ms | 186 ms
1000000  | Weightin lisäys | 778 ms | 789 ms
10000 | Weightin lisäys ja poisto|  6 ms | 5 ms
100000 | Weightin lisäys ja poisto|  62 ms | 59 ms
1000000 | Weightin lisäys ja poisto|  911 ms | 1010 ms

Huomasin testeistä että javan oma priorityQueue toimii melkein yhtänopeaa kuin tekemäni MinHeap. Testasin saman asian molemmilla algoritmeillä, ja testit olivat melkein yksi yhteen priorityQueuen kanssa.

### Astar ja Dijkstra - algoritmit

Astaria ja Dijkstraa on testattu koko suomen maantieverkostolla. Testejä on ajettu puolitoista päivää.

#### Algoritmien aikatestit

![Imgur](http://i.imgur.com/cU6I6Ev.png)

Iteraatioita satunnaisluvuilla on tehty noin pari tuhatta. Epätasaisuudet kuvaajassa johtuu iteraatioden pienestä määrästä. Huomaamme että mitä suurempi solmujen määrä lyhyimmässä reitissä on sitä hitaampi dijkstra on verrattuna astariin. Tämä johtuu siitä että astar osaa heurestiikka funktion avusta ohjata laskennan heti alusta oikeaan suuntaan. Alemmista kuvista vasemmanpuolinen on dijkstra ja oikeanpuolinen on astar. Aloituspiste on Oulun kohdilla ja lopetuspiste Helsingissä.

<img src="http://i.imgur.com/79hTtwS.png" width="200">
<img src="http://i.imgur.com/m4nUdXW.png" width="200">

Kuvista huomaamme miten eritavalla algoritmit käyttäytyvät. Dijkstra ottaa aina vain lyhyimmän reitin. AStar taas painattaa kokoajan oikeaan suuntaan vaikka väärässä suunnassa olisi pienemmällä painolla oleva kaari.

#### Läpikäytyjen solmujen määrä
![Imgur](http://imgur.com/MSwAt4N.png)

Iteraatioita satunnaisluvuilla on tehty tässänkin testissä noin pari tuhatta. Epätasaisuudet kuvaajassa johtuu iteraatioiden pienestä määrästä. Tästä kuvaajasta voimme todeta että dijkstra käy valtavasti enemmän solmuja läpi suurilla verkoilla. Syy on täysin sama kuin ylemässä testissä. 
