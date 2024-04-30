# Systemy rozproszone - zadanie 2
# @ Michał Kobiera

import requests
import os
from fastapi import FastAPI
from fastapi.responses import FileResponse, HTMLResponse
from geopy.distance import geodesic

app = FastAPI()

API_KEY = '7nG0kRZXY8Vc3DfS26AbzLmHxW1IjPQ9'

@app.get('/')
async def root():
     if not os.path.exists('index.html'):
          return HTMLResponse("<h1>Something is no yes</h1>", 501)
     return FileResponse('index.html', 200)

def error_page(status_code: int):
     return HTMLResponse(f"""
                         <div style="text-align: center;">
                              <h1>Brak PIWA mój Panie! :(</h1><img src="https://http.cat/{status_code}">
                         </div>""", status_code)

@app.get('/search')
async def search(city_name: str, address: str, api_key: str):
     if api_key != API_KEY:
          return error_page(401)
     
     # getting brewery list by city
     url = f"https://api.openbrewerydb.org/v1/breweries?by_city={city_name}"
     resp = requests.get(url)
     
     if not resp.ok:
          return error_page(resp.status_code)
     
     brewery_list = resp.json()
     
     if not brewery_list:
          return error_page(400)
     
     
     # getting longitude and latitude by address
     address_api_key = "177cb2528f0c4a8392cc36ecb9f1925b"
     url = f"https://api.geoapify.com/v1/geocode/search?text={address}&apiKey={address_api_key}"
     resp = requests.get(url)
     
     if not resp.ok:
          return error_page(resp.status_code)
     
     geocoding = resp.json()
     
     if not geocoding['features']:
          return error_page(400)
     
     
     current_lon = geocoding['features'][0]['properties']['lon']
     current_lat = geocoding['features'][0]['properties']['lat']
     
     # getting brewery dists
     brewery_dists = []
     brewery_elevations = []
     postal_code_mean = [0, 0]
     for brewery in brewery_list:
          lon = brewery['longitude']
          lat = brewery['latitude']
          
          dist = geodesic((current_lat, current_lon), (lat, lon)).kilometers
          brewery_dists.append((dist, brewery))
          
          # getting brewery elevation
          url = f'https://api.open-elevation.com/api/v1/lookup?locations={lat},{lon}'
          resp = requests.get(url)
          
          if not resp.ok:
               return error_page(resp.status_code)
          
          elevation = resp.json()
          elevation = elevation['results'][0]['elevation']
          brewery_elevations.append((elevation, brewery))
          
          postal_code_mean[0] += int(brewery['postal_code'][:2])
          postal_code_mean[1] += int(brewery['postal_code'][3:])
          
     postal_code_mean[0] /= len(brewery_list)
     postal_code_mean[1] /= len(brewery_list)
     postal_code_mean = f"{round(postal_code_mean[0])}-{round(postal_code_mean[1])}  ({postal_code_mean[0]}-{postal_code_mean[1]})"
               
     nearest_brewery = min(brewery_dists)
     highest_brewery = max(brewery_elevations)
     lowest_brewery = min(brewery_elevations)
     
     found_brewery_info = []
     brewery_dists.sort()
     for brewery in brewery_dists:
          found_brewery_info.append(f"{brewery[1]['name']} - adres: {brewery[1]['street']} - odległość: {format(brewery[0], '.2f')}km")
     found_brewery_info = "<br>".join(found_brewery_info)

     return HTMLResponse(f"""
     <!DOCTYPE html>
     <html lang="en">
     <head>
          <meta charset="UTF-8">
          <meta name="viewport" content="width=device-width, initial-scale=1.0">
          <title>DAWAĆ MI PIWO</title>
     </head>
     <body>
          <div class="content">
               <h1>Najbliższe PIWO w [{city_name}]: <strong>{nearest_brewery[1]['name']}</strong></h1>
               <p>Dystans na najkrótszej trasie: {format(nearest_brewery[0], '.2f')}km</p>
               <p>Adres: {nearest_brewery[1]['street']}</p>
               <h2>Lista wszystkich PIW</h2>
               {found_brewery_info}
               <h2>Uśredniony kod pocztowy</h2>
               {postal_code_mean}
               <h2>Chcesz się napić piwa jak najwyżej? Nie ma problemu!</h2>
               <p>Nasz najwyższy lokal: {highest_brewery[1]['name']} - wysokość: {highest_brewery[0]}m n.p.m</p>
               <p>Nasz najniższy lokal: {lowest_brewery[1]['name']} - wysokość: {lowest_brewery[0]}m n.p.m</p>
               <h3>Spiesz się po PIWO, tak szybko odchodzi</h3>
               ~Arystoteles
          </div>
          
     </body>
     </html>
     """, 200)
