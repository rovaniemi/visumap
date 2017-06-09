# Toteutusdokumentti

### Yleisrakenne

Ohjelman runkona toimii PathfindController -luokka, joka vastaanottaa käyttäjältä tulevan kyselyn. Luokka vastaa kyselyyn oikean tyyppisellä datalla käyttäen Router -luokkaa. Router -luokka rakentaa GraphBuilder luokan avulla karttadatasta Weightejä käyttävän verkon. Tämän jälkeen luokka antaa verkon algoritmien käyttöön. Algoritmit käyttävät hyväkseen Structure -paketin MinHeap -luokkaa ja Tools -paketin CoordinateDistance -luokkaa. Statistiikasta huolehtii Statistic -paketin alla olevat luokat.

## Saavutetut aika- ja tilavaativuudet

Algoritmien aikavaativuuksissa |E| tarkoittaa kaarien lukumäärää ja |V| tarkoittaa solmujen lukumäärää.

Algoritmi | Aikavaativuus              | Tilavaativuus
----------|----------------------------|--------------
Dijkstra  | O(\|E\| + \|V\| log \|V\|) | O(\|V\|) 
A*        | O(\|E\|)                   | O(\|V\|^2)

Saavutin algoritmeille melkein asettamani tavoite aika- sekä tilavaativuudet.

## Kartan ratkaiseminen breadth-first search algoritmilla

Algoritmin toimintaperiaate:  

* lisää alkusolmu jonoon **O(1)**
* niin kauan, kun maalisolmussa ei ole käyty **O(n)**
  * ota jonon ensimmäinen solmu **O(1)**
  * kaikille solmun naapurisolmuille **O(1)**
    * aseta lyhyin reitti kulkemaan kyseisen solmun kautta **O(1)**
    * lisää solmu jonoon **O(1)**

BFS aloittaa lähtösolmusta ja käy aina seuraavaksi lyhyimmät reitit läpi, joihin pääsee jo algoritmin läpikäymistä solmuista. 

En ole ehtinyt tehdä vielä algoritmiä loppuun niin jätän lopun analyysin myöhemmälle. 

## Lyhimmän reitin ratkaiseminen A* algoritmilla

Algoritmin toimintaperiaate:  
* lisää alkusolmu prioriteettijonoon **O(1)**
* niin kauan, kun maalisolmussa ei ole käyty **O(n)**
  * ota jonon ensimmäinen solmu **O(1)**
  * kaikille solmun naapurisolmuille **O(1)**
    * aseta lyhyin reitti kulkemaan kyseisen solmun kautta **O(1)**
    * lisää solmu prioriteettijonoon **O(log n)**
  
Algoritmi ei tutki kaikkia tietyn pituisia reittejä alkusolmusta, vaan käyttää heuristiikkafunktiota arviodakseen tietyn solmun mahdollisuutta johtaa lyhintä reittiä haluttuun solmuun. Pahimmassa tapauksessa aikavaativuudeltaan O(n log n).

## Mahdolliset puutteet ja parrannusehdotukset

Käyttäjälle voisi antaa mahdollisuuden muuttaa lennosta Astar algoritmin heurestiikkafunktiota. Projektin luokkarakenne on vähän sekava. Olisi myös mahdollista lukea dataa suoraan OSM -apista, ja tehdä laskentaa verkoille lennosta. Tällöin algoritmit voisi laittaa kattamaan koko Suomen tieverkoston.