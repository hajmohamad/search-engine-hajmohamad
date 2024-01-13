from elasticsearch import Elasticsearch

# Connect to Elasticsearch
es = Elasticsearch()
string=input()
split_string = string.split(" ")  # Splitting the string at the comma delimiter
filenames =split_string[0]

# Index the document

index_name = "search-engine"
for item in myArray:
    doc = {
    "filename": "Ali",
    "text": item
}
es.index(index=index_name, body=doc)