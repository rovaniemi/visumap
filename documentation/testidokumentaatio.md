# Testausdokumentaatio

Ohjelman testauksessa on käytetty JUnit testejä tietorakenteiden, algoritmien sekä toiminnallisuuden testaamiseen. JUnit testeistä on tehty dokumentaatio käyttäen apuna codecovia, jacocoa ja travista. Dokumentaatio löytyy [täältä](https://codecov.io/gh/rovaniemi/visumap). Kaikki JUnit testit menevät läpi yksittäin ja yhdessä.

## Suorituskykytestausta

### MinHeap - tietorakenne

Määrä | operaatio | MinHeap | PriorityQuoeue
------|------------|---------|----------------
10000    | Weightin lisäys | 3 ms | 7 ms
100000   | Weightin lisäys | 121 ms | 186 ms
1000000  | Weightin lisäys | 778 ms | 789 ms
10000 | Weightin lisäys ja poisto|  6 ms | 5 ms
100000 | Weightin lisäys ja poisto|  62 ms | 59 ms
1000000 | Weightin lisäys ja poisto|  911 ms | 1010 ms

Huomasin testeistä että javan oma priorityQueue toimii oikeastaan täsmälleen yhtänopeaa kuin tekemäni MinHeap

### Astar ja Dijkstra - algoritmit

Toistojen määrä | operaatio | Astar | Dijkstra
------|------------|---------|----------------
10000    | Randomilla valittujen nodejen välinen etäisyys | 15 ms | 12 ms

Lisään dijkstran, astarin ja bfssän välisiä suorituskykymittauksia kun saan importattua projektiin muutkin kaupungit kuin pelkästään Tornion. Otan myös yksisuuntaiset tiet huomioon.