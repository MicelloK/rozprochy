# System rozproszony do przechowywania artefaktów w bazie

Zaimplementuj rozproszony system do przechowywanie artefaktów na bazie
rozproszonego systemu plików

- Artefakt składa się z nazwy i długiego napisu, który jest rozbijany na węzły
- Jest jeden name-node i kilka storage-node - wszystkie węzły to aktorzy
- Całość implementacji może być przedstawiona w Jupyter Notebook’u
- Aktor name-node dystrybuuje artefakty do węzłów storage-node w
  chunkach/blokach i monitoruje je
- Istnieje kilka kopii takich bloków w systemie (aby poprawić wysoką dostępność i
  transfer danych - podobnie jak w HDFS)
- Węzły storage-node mogą ulec awarii (zmiana statusu); węzeł name-node śledzi
  wszystkie fragmenty i zarządza kopiami (wszystkie dane, operacje i stan systemu
  powinny być spójne)
- Wszystkie węzły powinny mieć metody dostarczające możliwość wylistowanie
  najważniejszych danych (np. gdzie przechowywane są chunki/bloki)
- Użytkownik ma możliwość: stworzenia, aktualizacji, skasowania, pobrania artefaktu. Operacje powinny być weryfikowane przez wylistowanie stanu węzłów.
