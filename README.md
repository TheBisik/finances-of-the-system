# 📊 Finances of the System

**Finances of the System** to system finansowy budowany w Spring Boot, wspierający logowanie przez Google OAuth2 i bazę danych PostgreSQL.  
Projekt powstaje hobbystycznie jako nauka i rozwój w kierunku **Fullstack Java Developer**.

---

## 🚀 Funkcjonalności (obecne i planowane)

- ✅ Logowanie użytkowników przez **Google OAuth2**  
- ✅ Integracja z bazą danych **PostgreSQL**  
- ✅ Weryfikacja dostępu użytkownika na podstawie **Google Sub ID**  
- ⚙️ Admin ręcznie dodaje użytkownika do bazy (Sub ID), aby umożliwić mu dostęp  
- 🏗️ Trwające prace:
  - Dodawanie / usuwanie / edytowanie rekordów finansowych
  - Wystawianie faktur z listy klientów w bazie
  - Automatyczne oznaczanie rekordów jako przychód / wydatek
  - Raporty miesięczne z możliwością edycji
  - Automatyczne obliczanie podatku VAT
  - Dashboard z listą transakcji i statystykami

---

## 🛠️ Wykorzystane technologie

- **Java 17**  
- **Spring Boot 3** (Spring Security, OAuth2, Spring Data JPA)  
- **PostgreSQL** (Docker)  
- **Thymeleaf** – renderowanie widoków  
- **Maven** – budowanie projektu  
- **Docker Compose** – uruchamianie bazy danych  

---

## ⚙️ Konfiguracja

Konfiguracja odbywa się w pliku `application.yml`.  
Wymagane są:  
- dane do logowania Google OAuth2 (`client-id`, `client-secret`),  
- konfiguracja bazy PostgreSQL (`url`, `username`, `password`).  

Przykład (bez sekretów, używaj zmiennych środowiskowych!):

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/system_finansowy_db
    username: ${DB_USER}
    password: ${DB_PASSWORD}
  security:
    oauth2:
      client:
        registration:
          google:
            client-id: ${GOOGLE_CLIENT_ID}
            client-secret: ${GOOGLE_CLIENT_SECRET}
            scope:
              - email
              - profile
```

---

## ▶️ Uruchamianie projektu

1. **Uruchom bazę danych PostgreSQL** (np. przez Docker):
   ```bash
   docker-compose up -d
   ```
   *(plik `docker-compose.yml` znajdziesz w repo)*

2. **Ustaw zmienne środowiskowe**:
   ```bash
   export DB_USER=postgres
   export DB_PASSWORD=twoje_haslo
   export GOOGLE_CLIENT_ID=xxxx.apps.googleusercontent.com
   export GOOGLE_CLIENT_SECRET=xxxx
   ```

3. **Uruchom aplikację**:
   ```bash
   ./mvnw spring-boot:run
   ```
   Aplikacja wystartuje pod: [http://localhost:8080](http://localhost:8080)

---

## 👤 Dodawanie użytkowników

1. Użytkownik loguje się przez Google → aplikacja pokaże jego **Sub ID**.  
2. Administrator systemu zapisuje Sub ID w bazie danych (tabela `app_user`).  
3. Przy następnym logowaniu użytkownik ma pełny dostęp do systemu.  

---

## 📝 Status projektu

Projekt **w budowie** – aktualnie działa logowanie i weryfikacja użytkownika.  
Planowane są kolejne funkcjonalności:  
- zarządzanie rekordami finansowymi,  
- baza klientów i faktury,  
- raporty i automatyczne obliczanie podatków.  

---

## 📸 Screenshoty



![Ekran logowania](docs/screenshots/login.png)  
![Brak Dostepu](docs/screenshots/no-access.png.png)  
![Lista rekordów finansowych](docs/screenshots/records.png)  


---

## 🤝 Autor

Projekt tworzony hobbystycznie przez **[Fabiana Barańskiego](https://github.com/TheBisik)**.  
Celem projektu jest nauka oraz budowanie portfolio jako programista Java.  
