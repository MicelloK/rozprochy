# Systemu rozproszone - zad 2

- Implementacja serwera - obsługa zapytań do zewnętrznego serwisu: [0-2] pkt.
- Implementacja serwera - odbiór żądań klienta zgodna z REST, generowanie i wysłanie
  odpowiedzi: [0-2] pkt.
- Implementacja serwera - obsługa asynchroniczności zapytań i błędów [0-3]
- Implementacja klienta - statyczny formularz zapytań / strona odpowiedzi: [0-2] pkt.
- Testowanie żądań REST z pomocą Postman-a/Swager UI (do serwera i do serwisu
  zewnętrznego): [0-1] pkt
  <br><br>

# PIWO API

Api znajdujące informacje o PIWIE.

## Zależności

- fastapi
- geopy

## Opis programu

Serwery wykorzystuje metody `get`.

W celu autoryzacji należy podać klucz api.  
`API_KEY` = 7nG0kRZXY8Vc3DfS26AbzLmHxW1IjPQ9

Klient do serwera wysyła dane:

- `city_name` - nazwa miasta w którym będą wyszukiwane browary. Informacje o [bazie danych](https://openbrewerydb.org/breweries),
- `address` - aktualna lokalizacja.
- `api_key` - klucz do autoryzacji.

W odpowiedzi serwer wysyła zapytania do serwisów zewnętrznych:

- https://openbrewerydb.org/ - api udostępniające informacje o browarach,
- https://apidocs.geoapify.com/docs/geocoding/forward-geocoding/ - api udsotępniające usługę geocoding,
- https://open-elevation.com/ - api udostępniające usługę obliczania wysokości nad poziomem morza.

a następnie konstruuje odpowiedź.

W przypadku błędu do wygenerowania obsługłującej strony, serwer korzysta z serwisu https://http.cat/.

Klient uzykuje dane takie jak:

- informacje o najbliższym browarze,
- listę wszystkich browarów w wybranym mieście razem z odległościami od nich,
- uśredniony kod pocztowy wszystkich browarów,
- najwyżej i najniżej położony lokal,
- cytat motywacyjny.

## Dokumentacja

### Uruchomienie serwera

```
python3 -m uvicorn main:app --reload
```

### Strona startowa

`url` = localhost:8000

### Link do zasobu

`url` = localhost:8000/search?city_name={_miasto_}&address={_adres_}&api_key={_twój klucz_}

### Przykład użycia

Proponuję wybrać miasto _Wrocław_ (duża ilość browarów w bazie) i jakiś istniejący adres np. _Krakowska 10 Wrocław_.
