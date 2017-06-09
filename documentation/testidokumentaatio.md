# Testausdokumentaatio

Ohjelman testauksessa on käytetty JUnit testejä tietorakenteiden, algoritmien sekä toiminnallisuuden testaamiseen. JUnit testeistä on tehty dokumentaatio käyttäen apuna codecovia, jacocoa ja travista. Dokumentaatio löytyy [täältä](https://codecov.io/gh/rovaniemi/visumap). Kaikki JUnit testit menevät läpi yksittäin ja yhdessä.

Suorituskykytestausta en ole vielä ehtinyt aloittaa, koska Statistic -paketin alla olevat luokat eivät vielä toimi oikein. Tämän seurauksena suorituskyvyn testaaminen on toistaiseksi toissijaista. Tulen ensiviikolla vertaamaan omaa minHeap rakennetta suoraan javan PriorityQueue rakenteeseen. Lisäksi teen testejä jotka vertailevat eri algoritmien nopeuksia, sekä testejä jossa algoritmejä ajetaan random pisteillä eikä algoritmin käyttämä aika saa nousta liian suureksi. Tulen tekemään näistä testeistä kattavat raportit.
