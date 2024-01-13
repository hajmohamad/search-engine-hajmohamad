from elasticsearch import Elasticsearch

# Connect to Elasticsearch
es = Elasticsearch()
word=input()

# Define the index and type
index = "search-engine"
type = "_doc"

# Define the query
query = {
    "query": {
        "match": {
            "txt": {
                "query": word,
                "fuzziness": 1
            }
        }
    }
}

# Execute the search
response = es.search(index=index, doc_type=type, body=query)

# Print the results
for filename  in response["hits"]["filename"]:
    print(filename)