# Search and Autosuggest

## Demo
Check out the demo [here](https://drive.google.com/file/d/1AgxWbhBhWc9WfvNlbqg5oXCeWcqM9_lF/view?usp=sharing)

## Features
* Show autosuggest result once the prefix search key is 3 or greater.
* Show search results (without any condition on prefix).
* Show no results found in case we don't have an index for search key.
* Enter(return) key to work as submit button.

## Behind the scenes

### Frontend
* BootStrap
* CSS3
* HTML5
* JS

### Backend
* Spring(Boot) Framework (Service(controller), Manager, Dao layer)
* Trie to hold the data
* Api for search, suggest and index a key
* Test cases and configurable suggest size


## How to run
* Import the project in Eclipse/IntelliJ
* In src/main/resources provide path of csv(domains) file
* Run Application.java file
* This will start the backend server and will expose the following api

### Serach  GET : http://localhost:8088/domain/search/face
### Suggest GET : http://localhost:8088/domain/suggest/bhas
### Index  POST : curl -X POST \
  http://localhost:8088/domain/add \
  -H 'cache-control: no-cache' \
  -H 'content-type: application/json' \
  -d '{
 "name":"bhaskarskar",
 "rating":1111
}'

Now in the Frontend folder run App.html
