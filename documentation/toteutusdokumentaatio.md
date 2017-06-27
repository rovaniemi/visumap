# Toteutusdokumentti

### Yleisrakenne

Ohjelma on kolmessa osassa. [Osm-graph-parser](https://github.com/rovaniemi/osm-graph-parser/) parseroi open street mapin datasta verkon vieruslistaesityksen. [Backend](https://github.com/rovaniemi/visumap/) vastaa käyttäjän kyselyihin vastaamisesta, algoritmeistä ja sekä datan käsittelystä. Datan visualisointi kartalle tapahtuu [frontendissä](https://github.com/rovaniemi/visumap-front). Algoritmit ja tietorakenteet toimii back-endissä hyvin. Käyttäjän kyselyyn vastaaminen voisi olla paremmin tehty. Tavoitteenani on cachettaa vastauksia tietokantaan, josta voin napata käyttäjälle vastauksia. Näin käyttäjän ei tarvitsisi odottaa algoritmin vastausta, vaan toiminta olisi sulavaa ja nopeaa. Valitettavasti ohjelma ei tullut täysin valmiiksi tiralabran aikana, mutta tavoitteenani on tehdä siitä valmis kesän loppuun mennessä. 

Backendin tämän hetkisenä runkona toimii PathfindController -luokka, joka vastaanottaa käyttäjältä tulevan kyselyn. Kyselyyn vastataan oikean tyyppisellä datalla käyttäen apunaan Router -luokkaa. Router -luokka rakentaa GraphBuilder luokan avulla karttadatasta Weightejä käyttävän verkon. Tämän jälkeen luokka antaa verkon algoritmien käyttöön. Algoritmit käyttävät hyväkseen Structure -paketin MinHeap -luokkaa ja Tools -paketin CoordinateDistance -luokkaa. Statistiikasta huolehtii Statistic -paketin alla olevat luokat.

## Saavutetut aika- ja tilavaativuudet

Algoritmien aikavaativuuksissa |E| tarkoittaa kaarien lukumäärää ja |V| tarkoittaa solmujen lukumäärää.

Algoritmi | Aikavaativuus              | Tilavaativuus
----------|----------------------------|--------------
Dijkstra  | O((\|E\| + \|V\|) log \|V\|) | O(\|V\|) 
A*        | O((\|E\| + \|V\|) log \|V\|) | O(\|V\|)

Saavutin algoritmeille tavoite aika- sekä tilavaativuudet.

## Algoritmien toimintaperiaate

* lisää alkusolmu prioriteettijonoon **O(1)**
* niin kauan, kun maalisolmussa ei ole käyty **O(n)**
  * ota jonon ensimmäinen solmu **O(1)**
  * kaikille solmun naapurisolmuille **O(1)**
    * aseta lyhyin reitti kulkemaan kyseisen solmun kautta **O(1)**
    * lisää solmu prioriteettijonoon **O(log n)**


Algoritmit eroavat toisistaan jonon ensimmäisen solmun ottamisessa.
Dijkstra ottaa vierussolmun jolla on pienin paino. Astar algoritmi ei tutki kaikkia tietyn pituisia reittejä alkusolmusta, vaan käyttää heuristiikkafunktiota arviodakseen tietyn solmun mahdollisuutta johtaa lyhintä reittiä haluttuun solmuun. 

## Dijkstran aikavaativuus

* Dijkstran alustaminen vie aikaa O(\|V\|) 
* Keko-operaatioiden aikavaavuus on O(\|log n\|)
* maksimitapauksessa edgejä lisätään kekoon |E| kappaletta, aikaa siis kuluu O(|E| log |E|).
* Näin ollen toistolauseessa kuluu aikaa maksimissaan O((\|E\| + \|V\|) log \|V\|)
* tilavaativuus on siis selvästi O(|V|)

## Astarin aikavaativuus

* Astarin alustaminen vie aikaa O(\|V\|) 
* Keko-operaatioiden aikavaavuus on O(\|log n\|)
* maksimitapauksessa edgejä lisätään kekoon |E| kappaletta, aikaa siis kuluu O(|E| log |E|).
* Näin ollen toistolauseessa kuluu aikaa maksimissaan O((\|E\| + \|V\|) log \|V\|)
* tilavaativuus on siis selvästi O(|V|)
  
## Mahdolliset puutteet ja parrannusehdotukset

Käyttäjälle voisi antaa mahdollisuuden muuttaa lennosta Astar algoritmin heurestiikkafunktiota. Lisäksi olen miettinyt eri algoritmien lisäystä projektiin. Kunhan olen saanut backendin ja frontin tarvittavalle tasolle niin lisään fibonaccin keon dijkstran ja astarin käyttöön. Tällöin molempien aikavaativuus olisi `O(E + V log V)`. Olisi myös mukava nähdä miten eritavalla algoritmit käyttäytyy kartalla tämän jälkeen. Myöskin projektin luokkarakenne on tällähetkellä sekava, ja sitä pitäisi parannella. 