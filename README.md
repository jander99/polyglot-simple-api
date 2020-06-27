# A Polyglot Simple API #

This respository contains an implementation of the following API specification using several different langauges and frameworks for practice. 

## The API ##

The API will be based on the data from jsonplaceholder.com. It will aggregate the data into the following JSON document. 


/users
```json
[{
	"userId": 1,
	"username": "Bret",
	"email": "Sincere@april.biz",
	"albums": [{
		"albumId": 1,
		"title": "quidem molestiae enim",
		"photos": [{
			"photoId": 1,
			"title": "accusamus beatae ad facilis cum similique qui sunt",
			"url": "https://via.placeholder.com/600/92c952"
		}]
	}]
}]
```

/users/{userId}
```json
{
	"userId": 1,
	"username": "Bret",
	"email": "Sincere@april.biz",
	"albums": [{
		"albumId": 1,
		"title": "quidem molestiae enim",
		"photos": [{
			"photoId": 1,
			"title": "accusamus beatae ad facilis cum similique qui sunt",
			"url": "https://via.placeholder.com/600/92c952"
		}]
	}]
}
```

/users/{userId}/albums
```json
[{
    "albumId": 1,
    "title": "quidem molestiae enim",
    "photos": [{
        "photoId": 1,
        "title": "accusamus beatae ad facilis cum similique qui sunt",
        "url": "https://via.placeholder.com/600/92c952"
    }]
}]
```

/users/{userId}/albums/{albumId}
```json
{
    "albumId": 1,
    "title": "quidem molestiae enim",
    "photos": [{
        "photoId": 1,
        "title": "accusamus beatae ad facilis cum similique qui sunt",
        "url": "https://via.placeholder.com/600/92c952"
    }]
}
```

/users/{userId}/albums/{albumId}/photos
```json
[{
    "photoId": 1,
    "title": "accusamus beatae ad facilis cum similique qui sunt",
    "url": "https://via.placeholder.com/600/92c952"
}]
```

/users/{userId}/albums/{albumId}/photos/{photoId}
```json
{
    "photoId": 1,
    "title": "accusamus beatae ad facilis cum similique qui sunt",
    "url": "https://via.placeholder.com/600/92c952"
}
```