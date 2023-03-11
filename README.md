# Proiect POO
#### Angheloiu George-Bogdan 324CB

Am implementat 4 design patterns:

1. Singleton - clasa ProiectPOO
    Nu era necesară decât o singură înstanță a acestei clase, așa că Singleton era soluția perfectă. Am folosit un câmp static care dacă era null, se instanția, iar
    dacă era diferit de null, se păstra instanța deja creată, asigurând că întotdeauna există numai una. Am folosit apelul Instance() și pentru a reseta niște
    date interne.

2. Factory - clasa InputFactory
    Am folosit un Factory atât pentru execuția tuturor comenzilor primite în program, cât și pentru instanțierea User-ilor, Stream-urilor și Streamer-ilor fără a
    specifica neapărat clasa. Toate cele trei moștenesc clasa abstractă InputBase care conține doar câmpurile id și name, comune tuturor. Toate instanțele acestor clase
    se află într-un Map în ProiectPOO, cu cheia egală cu id-ul.

3. Strategy - interfața ListStrategy, clasele User și Streamer
    Folosit strict pentru implementarea metodei list(), am folosit Strategy deoarece comanda era abstractă (se primea decât un id, dar nu se specifica tipul obiectului),
    și astfel am putut folosi același apel pentru a implementa funcționalități diferite pentru list() în funcție de clasa obiectului.

4. Iterator - interfața IteratorInterface, clasele RecommendIterator și SurpriseIterator
    Din moment ce am folosit Map pentru a stoca efectiv tot, nu era tocmai comod să scot valori sortate pentru recomandări și surprize. Am folosit Iterator pentru a
    trece ușor prin acestea fără a complica codul înafara acestor clase. Din moment ce am avut nevoie doar de Stream-uri sortate, am folosit iteratorul doar
    pentru acestea.

Pe parcursul implementării am prioritizat cât mai mult simplitatea scrierii codului: am folosit cât mai multe funcții lambda, predicate etc. pentru a scrie cât mai
puțin cod și pentru a î-l face mai ușor de înțeles. De asemenea, datorită abstractizării și design pattern-urilor folosite, aplicația poate fi ușor extinsă pentru a
acomoda mai multe cerințe sau pentru a îi spori funcționalitatea sau complexitatea.

