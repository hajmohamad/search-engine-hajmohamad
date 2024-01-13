
import requests

# Set your access key
access_key = 'y-XBP-MAhOfpfFDj2SK21nkAyaBA5nIq5WLSKXmMr-0'

# Set the base URL for the API
base_url = 'https://api.unsplash.com/'

# Set the endpoint you want to access
endpoint = 'search/photos'
string =input()
split_string = string.split(" ")  # Splitting the string at the comma delimiter


# Set any query parameters you need
query_params = {

   'query':string,
       'client_id': access_key,
       'per_page': 8 # Number of random photos to retrieve
}
# Send a GET request to the API
response = requests.get(base_url + endpoint, params=query_params)
file = open("filename.txt", "+w")

# Write content to the file

# Close the file

# Check if the request was successful
if response.status_code == 200:
    # Extract the JSON data from the response
    data = response.json()

    for photo in data["results"]:
             print(photo["urls"]["small"])




else:
    # Handle the error
    print('Error:', response.status_code)

# Open the file in write mode
file.close()
