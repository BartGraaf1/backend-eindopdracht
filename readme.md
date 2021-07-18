# Car garage application

Deze applicatie is gecreëerd om de administratie van een autogarage te digitaliseren.
## Installatie

Voor het installeren van het project zijn de volgende dingen nodig:

- [Git](https://git-scm.com/downloads)
- [Java 11 JDK](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
- [PostgreSQL](https://www.postgresql.org/download/)
- [InteliJ IDEA](https://www.jetbrains.com/idea/download/#section=windows)


## Stap 1 - download het project

Zodra de bovenste programma’s gedownload zijn kunnen beginnen met het project te installeren. Open de Git bash op de locatie waar je het project wilt opslaan.en plak het volgende command hier in:

```bash
git clone https://github.com/BartGraaf1/backend-eindopdracht
```
Als dit succesvol is gebeurd staat het project nu op de computer.

## Stap 2 - Stel database in

Open PostgreSQL en creeer een database. onthoud deze gegevens aangezien we deze gegevens later nodig hebben.

## Stap 3 - Open het project

Open nu het project in een Java IDEA (bij voorkeur InteliJ IDEA).

## Stap 4 - Zet de configuratie goed

Op het volgende bestand:
src > resources > application.properties en stel de volgende parameters in naar de eerder gemaakt database gegevens. 
    
```bash
spring.datasource.url=jdbc:postgresql://localhost:5432/springboot
spring.datasource.username=postgres
spring.datasource.password=ADMIN
```

## Stap 5 - Start het project

Je kan nu het project starten door de class src > Main > Java > com.backendeindopdracht.bartdegraaf > SecurityMain te starten.

## Klaar

Als alles goed gegaan is zou het project nu gestart moeten zijn en is de database verbonden en gevuld met dummy data.

## JWT token in header:
Er zijn 2 accounts gemaakt, je kan inloggen met de volgende gegevens: 

```bash
{
"username": "user_administrative_employee",
"password": "password"
}
```

```bash
{
"username": "user_mechanic",
"password": "password"
}
```

Voeg de JWT token toe op de volgende manier: 

Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbiIsImV4cCI6MTYwMjIxNTUxMSwiaWF0IjoxNjAyMTc5NTExfQ.tCkkxd2twkUSW-ETLoM9xW0HaI-YfOGpM-Km9UIwv0U
