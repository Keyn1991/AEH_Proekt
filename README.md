Student Management System

Opis Projektu

Student Management System to aplikacja napisana w języku Java, która umożliwia zarządzanie danymi studentów. Projekt wykorzystuje bibliotekę Swing do stworzenia graficznego interfejsu użytkownika oraz bazę danych SQLite do przechowywania informacji o studentach.

Funkcjonalności aplikacji:

Dodawanie nowych studentów.

Usuwanie studentów na podstawie ich ID.

Aktualizowanie danych studentów.

Wyświetlanie listy wszystkich studentów.

Obliczanie średniej ocen wszystkich studentów.

Wymagania

Aby uruchomić projekt, wymagane jest:

Zainstalowane środowisko JDK (Java Development Kit) w wersji 8 lub wyższej.

Biblioteka SQLite JDBC (wraz z konfiguracją).

Instrukcja Kompilacji i Uruchamiania

Krok 1: Pobranie kodu źródłowego

Skopiuj cały projekt do swojego środowiska lokalnego. Upewnij się, że plik bazy danych students.db znajduje się w katalogu src/Database/.

Krok 2: Kompilacja projektu

W terminalu przejdź do katalogu głównego projektu i skompiluj kod przy użyciu polecenia:

javac -d bin src/**/*.java

Krok 3: Uruchomienie aplikacji

Uruchom aplikację przy użyciu polecenia:

java -cp bin Main

Krok 4: Testowanie projektu (opcjonalne)

Projekt zawiera testy jednostkowe, które można uruchomić za pomocą JUnit 5. Aby uruchomić testy, wykonaj:

java -cp bin:lib/junit-platform-console-standalone-1.9.2.jar org.junit.platform.console.ConsoleLauncher --scan-classpath

Struktura Projektu

src/ - Główne źródła kodu projektu.

Database/ - Katalog zawierający bazę danych SQLite (students.db).

Main.java - Klasa główna projektu.

Student.java - Klasa reprezentująca studenta.

StudentManager.java - Interfejs definiujący operacje na danych studentów.

StudentManagerImpl.java - Implementacja interfejsu z wykorzystaniem bazy danych SQLite.

StudentManagementGUI.java - Graficzny interfejs użytkownika.

bin/ - Skatalogowane pliki wynikowe po kompilacji.

Autorzy

Dmytro Potapchuk


