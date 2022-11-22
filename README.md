# OnlineShop

*Configurarea bazei de date*

Pentru configurarea bazei de date am folosit MySql Workbench

1. In cadrul unei conexiuni se va crea o schema cu numele managementtest1: ```create schema managementtest1;```


*Configurarea proiectului*

Daca este pentru prima data cand luati acest proiect trebuie sa rulati comanda ```git clone ```

Pentru a putea sa va legati proiectul cu baza de date va trebui sa faceti urmatoarele, dupa deschiderea proiectului in IntelliJ:
1. Click pe `Run` din partea de sus a ecranului
2. Click pe `Edit Configurations` 
3. Click pe `Modify options`
4. Select `Environment variables`
5. Completati caseta de input cu `DB_USER=nume_db;DB_PASS=parola_db` unde nume_db este numele bazei de date (in general root) si parola_db este parola bazei de date
6. Click `OK`


*Testare proiect*
1. Accesati urmatorul link: https://app.getpostman.com/join-team?invite_code=147bf32f4c966ecfdeca41b0075361a0&target_code=d3343bee3449e454ec7a3b04c39eb230
