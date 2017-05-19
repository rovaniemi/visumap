## Määrittelydokumentti

#### Mitä ongelmaa ratkaiset ja miksi valitsit kyseisen ongelman?

Haen ohjelmallani oppimista helpottavaa visuaalista ratkaisua SSSP (singe source sortest path) ongelmaan. Tutkin siis matemaattisen verkon lyhimmän polun etsimiseen käytettäviä algoritmejä ja visualisoin niiden käyttämiä etsintätapoja. Käytän Suomen kaupunkien (Helsinki, Turku, Rovaniemi, Ivalo, Tornio) maantiekarttoja verkkoina. Kirjaan myös eri algoritmien läpikäymien solmujen määrää, sekä algoritmien vaativuutta eri aineistoilla (kaupungit) ja aloitus- sekä lopetuspisteillä. Valitsin kyseisen ongelman huomatessani, ettei mielenkiintoista visualisaattoria kyseiseiselle ongelmalle ole olemassa. Ongelmaa sovelletaan monissa eri ohjelmissa, joten sen osaaminen on oleellista. Monet innokkaat opiskelijat painivat kyseisen ongelman kanssa vuosittain. Toivoisin, että karttavisualisaattori auttaisi heitä opiskelussa ja mahdollisesti jopa helpottaisi ongelman hahmottamisessa.

#### Mitä algoritmeja ja tietorakenteita toteutat työssäsi?

Aloitan työn kahdella perinteisellä lyhimmän polun etsimisen algoritmillä, eli dijkstralla ja A\*:lla. Tämän jälkeen tulen lisäämään ominaisuuden BFS algoritmin käyttämiseen pienemmillä syötteillä. Lisään  mahdollisesti myös [Thorup algoritmin](http://delivery.acm.org/10.1145/320000/316548/p362-thorup.pdf?ip=128.214.138.187&id=316548&acc=ACTIVE%20SERVICE&key=74A0E95D84AAE420%2ECFCAB708F0B7DA8F%2E4D4702B0C3E38B35%2E4D4702B0C3E38B35&CFID=764695677&CFTOKEN=67972069&__acm__=1495217584_63f5ccfdf56c50c1e536cfd765acdac6).

#### Tavoitteena olevat aika- ja tilavaativuudet (m.m. O-analyysit)

Algoritmien aikavaativuuksissa |E| tarkoittaa kaarien lukumäärää ja |V| tarkoittaa solmujen lukumäärää.

Algoritmi | Aikavaativuus              | Tilavaativuus
----------|----------------------------|--------------
Dijkstra  | O(\|E\| + \|V\| log \|V\|) | O(\|V\|) 
A*        | O(\|E\|)                   | O(\|V\|)
BFS       | O(\|V\| + \|E\|)           | O(\|V\|)
Thorup    | O(\|E\|)                   | O(\|V\|)

Kyseiset aika- ja tilavaativuudet on mitoitettu suuntaamattomille verkoille. Lisään verkkoon myöhemmin mahdollisesti myös yksisuuntaiset tiet, jolloin aika- sekä tilavaativuudet muuttuvat.

#### Mitä syötteitä ohjelma saa ja miten näitä käytetään

Ohjelma saa syötteenään matemaattisen verkon [open street mapin datasta](https://www.openstreetmap.org/). Käytän datan parsintaan vartavasten tätä projektia varten tehtyä [parsinta sovellusta](https://github.com/rovaniemi/osm-graph-parser). 

Esimerkkidata: 

```json
[
  {
    "id": 443030113,
    "lat": 66.4740787,
    "lon": 25.7742872,
    "edges": [
      {
        "id": 736017556,
        "weight": 899
      },
      {
        "id": 443030115,
        "weight": 3756
      }
    ]
  },
  {
    "id": 443030115,
    "lat": 66.4741164,
    "lon": 25.7751283,
    "edges": [
      {
        "id": 443030113,
        "weight": 3756
      },
      {
        "id": 443030119,
        "weight": 4014
      }
    ]
  }
```

Jokaisella solmulla (piste kartalla) on latitude ja longitude. Ne sisältävät tiedon siitä mihin solmuun menee kaari (realimaailmassa tie) ja mikä on tämän kaaren paino (pituus). Datan kokoluokka tulee olemaan suurinpiirtein seuraavanlainen (lopullista dataa en ole vielä parsinut):

Kaupunki  | Solmujenmäärä              | Kaarienmäärä
----------|----------------------------|--------------
Helsinki  | Miljoonissa                | Sadoissa tuhansissa
Rovaniemi | Sadoissa tuhansissa         | Kymmenissä tuhansissa
Tornio    | Sadoissa tuhansissa         | Kymmenissä tuhansissa
Ivalo     | Kymmenissä tuhansissa       | Tuhansissa

## Lähteet

* HY tietorakenteet ja algoritmit kurssin [kurssimateriaali](https://moodle.helsinki.fi/pluginfile.php/1537867/mod_resource/content/12/tira.pdf)
* [Mikkel Thorup](http://delivery.acm.org/10.1145/320000/316548/p362-thorup.pdf?ip=128.214.138.187&id=316548&acc=ACTIVE%20SERVICE&key=74A0E95D84AAE420%2ECFCAB708F0B7DA8F%2E4D4702B0C3E38B35%2E4D4702B0C3E38B35&CFID=764695677&CFTOKEN=67972069&__acm__=1495217584_63f5ccfdf56c50c1e536cfd765acdac6)
* [OSM XML file format notes](https://wiki.openstreetmap.org/wiki/OSM_XML#OSM_XML_file_format_notes)
