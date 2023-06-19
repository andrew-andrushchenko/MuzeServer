# MuzeServer
Simple REST Server API for Art gallery.

# How to setup
In order to run the server you need to declare these environment variables:
| Variable  | Description |
| ------------- | ------------- |
| BASE_URL | Url for your server. Needed to properly store and retrieve images. |
| DATABASE_URL | Url to your database. |
| JDBC_DRIVER | Your JDBC driver. |
| ARTWORKS_DIR | Path to a directory to store and retrieve artworks. |
| ARTISTS_PORTRAITS_DIR | Path to a directory to store and retrieve artists portraits. |

> **Note**  
> Currently was tested in pair with PostgreSQL Database and drivers

> **Warning** <br/>
> Code contains extension function for PostgreSQL specific ***ILIKE*** operator for case-insensitive search.<br/>
> This likely won't be compatible with other databases but it is planned to be fixed in future versions!

# Routes
## Get artworks
```
GET /artworks
```
**Parameters**
| Parameter  | Description |
| ------------- | ------------- |
| page  | Page number to retrieve. (Optional; default: 1)  |
| per_page  | Number of items per page. (Optional; default: 10)  |

## Get an artwork
```
GET /artworks/:id
```
**Parameters**
| Parameter  | Description |
| ------------- | ------------- |
| id  | The artwork’s ID. Required. |

## Search artworks
```
GET /artworks/search
```
**Parameters**
| Parameter  | Description |
| ------------- | ------------- |
| query | Search terms. |
| page  | Page number to retrieve. (Optional; default: 1)  |
| per_page  | Number of items per page. (Optional; default: 10)  |

## Get artists
```
GET /artists
```
**Parameters**
| Parameter  | Description |
| ------------- | ------------- |
| page  | Page number to retrieve. (Optional; default: 1)  |
| per_page  | Number of items per page. (Optional; default: 10)  |

## Get an artist
```
GET /artists/:id
```
**Parameters**
| Parameter  | Description |
| ------------- | ------------- |
| id  | The artist’s ID. Required. |

## Search artworks
```
GET /artists/search
```
**Parameters**
| Parameter  | Description |
| ------------- | ------------- |
| query | Search terms. |
| page  | Page number to retrieve. (Optional; default: 1)  |
| per_page  | Number of items per page. (Optional; default: 10)  |
