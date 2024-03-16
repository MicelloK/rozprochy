# Systemu rozproszone - zad 2

- Implementacja serwera - obsługa zapytań do zewnętrznego serwisu: [0-2] pkt.
- Implementacja serwera - odbiór żądań klienta zgodna z REST, generowanie i wysłanie
  odpowiedzi: [0-2] pkt.
- Implementacja serwera - obsługa asynchroniczności zapytań i błędów [0-3]
- Implementacja klienta - statyczny formularz zapytań / strona odpowiedzi: [0-2] pkt.
- Testowanie żądań REST z pomocą Postman-a/Swager UI (do serwera i do serwisu
  zewnętrznego): [0-1] pkt

## zależności

- python-multipart
- fastapi
- geopy

## uruchomienie

```
python3 -m uvicorn main:app --reload
```

## działanie programu

Serwer wykorzystuje jedną metodę `get` i dwie `post`.

Zaimplementowany został trywialny system autoryzacji. W tym celu podaje się klucz dostępu.

Klient do serwera wysyła dane:

- `city_name` - nazwa miasta w którym będą wyszukiwane browary. Informacje o [bazie danych](https://openbrewerydb.org/breweries),
- `address` - aktualna lokalizacja.

W odpowiedzi serwer wysyła zapytania do serwisów zewnętrznych:

- https://openbrewerydb.org/ - api udostępniające informacje o browarach,
- https://apidocs.geoapify.com/docs/geocoding/forward-geocoding/ - api udsotępniające usługę geocoding,
- https://open-elevation.com/ - api udostępniające usługę obliczania wysokości nad poziomem morza.

a następnie konstruuje odpowiedź.

Klient uzykuje dane takie jak:

- informacje o najbliższym browarze,
- listę wszystkich browarów w wybranym mieście razem z odległościami od nich,
- uśredniony kod pocztowy wszystkich browarów,
- najwyżej i najniżej położony lokal,
- cytat motywacyjny.

## przykład użycia

Należy uruchomić serwer i wejść na podany adres. Następnie zalogować się do serwisu (domyślnie hasło - admin1234). Proponuję wyszukać miasto Wrocław (w tej bazie danych występuje tam duża ilość browarów) oraz dowolną lokalizację (we Wrocławiu np. Krakowska 10 Wrocław).
