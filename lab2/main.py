# Systemy rozproszone - zadanie 2
# @ Michał Kobiera

import requests
import os
from fastapi import FastAPI, Form
from fastapi.responses import FileResponse, HTMLResponse
from geopy.distance import geodesic

app = FastAPI()

@app.get('/')
async def root():
     if not os.path.exists('templates/index.html'):
          return HTMLResponse("<h1>Something is no yes</h1>", 500)
     return FileResponse('templates/index.html', 200)

@app.post('/logged')
async def login(password: str = Form(...)):
     if password != 'admin1234':
          return HTMLResponse('<h1>401 ZŁE HASLO WARIACIE!</h1> <img src="https://http.dog/static/codes/dogs/small/401.jpg">', 401)
     if not os.path.exists('templates/logged.html'):
          return HTMLResponse("<h1>Something is no yes</h1>", 500)
     return FileResponse('templates/logged.html', 200)

@app.post('/logged/search', response_class=HTMLResponse)
async def beer_data(city_name: str = Form(...), address: str = Form(...)):
     # getting brewery list by city
     url = f"https://api.openbrewerydb.org/v1/breweries?by_city={city_name}"
     resp = requests.get(url)
     brewery_list = resp.json()
     
     if not brewery_list:
          return FileResponse('templates/brak_piwa.html', resp.status_code)
     if not resp.ok:
          return HTMLResponse("<h1>Something is no yes</h1>", resp.status_code)
     
     # getting longitude and latitude by address
     api_key = "177cb2528f0c4a8392cc36ecb9f1925b"
     url = f"https://api.geoapify.com/v1/geocode/search?text={address}&apiKey={api_key}"
     resp = requests.get(url)
     geocoding = resp.json()
     
     if not geocoding['features']:
          return FileResponse('templates/wrong_address.html', resp.status_code)
     if not resp.ok:
          return HTMLResponse('<h1>Something is no yes</h1><img src="https://i.pinimg.com/736x/e0/2f/32/e02f32da930fa4d6996a3808935a3d3c.jpg">', resp.status_code)
     
     current_lon = geocoding['features'][0]['properties']['lon']
     current_lat = geocoding['features'][0]['properties']['lat']
     
     # getting brewery dists
     brewery_dists = []
     brewery_elevations = []
     postal_code_mean1 = 0 # ;)
     postal_code_mean2 = 0
     for brewery in brewery_list:
          lon = brewery['longitude']
          lat = brewery['latitude']
          
          dist = geodesic((current_lat, current_lon), (lat, lon)).kilometers
          brewery_dists.append((dist, brewery))
          
          # getting brewery elevation
          url = f'https://api.open-elevation.com/api/v1/lookup?locations={lat},{lon}'
          resp = requests.get(url)
          elevation = resp.json()
          elevation = elevation['results'][0]['elevation']
          brewery_elevations.append((elevation, brewery))
          
          if not resp.ok:
               return HTMLResponse("<h1>Something is no yes</h1>", resp.status_code)
          
          postal_code_mean1 += int(brewery['postal_code'][:2])
          postal_code_mean2 += int(brewery['postal_code'][3:])
          
     postal_code_mean1 /= len(brewery_list)
     postal_code_mean2 /= len(brewery_list)
     postal_code_mean = f"{round(postal_code_mean1)}-{round(postal_code_mean2)}  ({postal_code_mean1}-{postal_code_mean2})"
               
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


