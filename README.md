# MuzeServer
Simple REST Server API for Art gallery powered by [Ktor](https://ktor.io) framework.

# Build
To generate a JAR executable run the following from the command line:
```
./gradlew shadowJar
```
Generated JAR will be placed to */build/libs* folder of your project's working directory.

# Setup and run
In order to run the server you need to declare these environment variables:
| Variable  | Description | Note |
| ------------- | ------------- | ------------- |
| BASE_URL | Your server's network URL. | -
| DATABASE_URL | Your database URL. | - |
| JDBC_DRIVER | Your JDBC driver. | - |
| ARTWORKS_DIR | Path to a directory to store and retrieve artworks. | Optional; Default path: [workingDir]/data/artworks_dir/ |
| ARTISTS_PORTRAITS_DIR | Path to a directory to store and retrieve artists portraits. | Optional; Default path: [workingDir]/data/artists_portraits/ |

> **Warning** <br/>
> Code contains extension function for PostgreSQL specific ***ILIKE*** operator for case-insensitive search.<br/>
> This likely won't be compatible with other databases but it is planned to be fixed in future versions!

> **Note**  
> Currently was tested in pair with PostgreSQL Database and drivers.

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

## Add an artwork
```
POST /artworks/add
Content-Type:multipart/form-data
```

**Form data pairs**
| Name | Description |
| ------------- | ------------- |
| image | Image file. |
| name  | Artwork name. |
| year | Artwork creation year. |
| location | Artwork location in a muzeum. |
| description | Artwork description. |
| artist_id | An id of the existed artist in database. |

## Delete an artwork
```
DELETE /artworks/:id
```
**Parameters**
| Parameter  | Description |
| ------------- | ------------- |
| id  | The artwork’s ID. Required. |

## Delete all artworks
```
DELETE /artworks
```

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

## Search artists
```
GET /artists/search
```
**Parameters**
| Parameter  | Description |
| ------------- | ------------- |
| query | Search terms. |
| page  | Page number to retrieve. (Optional; default: 1)  |
| per_page  | Number of items per page. (Optional; default: 10)  |

## Add an artist
```
POST /artists/add
Content-Type:multipart/form-data
```
**Form data pairs**
| Name | Description |
| ------------- | ------------- |
| image | Artist's portrait image file. |
| name  | Artist's name. |
| bio | Artist's biography. |
| born_date_string | Artist's born date in "dd/MM/yyyy" format. |
| died_date_string | Artist's death date in "dd/MM/yyyy" format. |

## Delete an artist
```
DELETE /artists/:id
```
**Parameters**
| Parameter  | Description |
| ------------- | ------------- |
| id  | The artist’s ID. Required. |

## Delete all artists
```
DELETE /artists
```

# License
```
MIT License

Copyright (c) 2023 Andrii Andrushchenko

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
```
