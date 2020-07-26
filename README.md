# MongoDB-Java-Project

- Connection to the database
- Creating documents
- Retrieving documents
- Updating documents
- Deleting Documents

# Mongo DB

MongoDB document-oriented database. Classified as a NoSQL database, MongoDB avoids the traditional table-based relational database structure in favor of JSON-like documents with dynamic schemas, making the integration of data in certain types of applications easier and faster.


# Mongo Queries

Document Structure

```
{
    "_id" : ObjectId("5f1d164db553b2e22b421a73"),
    "address" : {
        "street" : "Stillwell Avenue",
        "zipcode" : "11224",
        "building" : "2780",
        "coord" : [ 
            -73.98242, 
            40.579505
        ]
    },
    "borough" : "Brooklyn",
    "cuisine" : "American ",
    "grades" : [ 
        {
            "date" : ISODate("2014-06-10T00:00:00.000Z"),
            "grade" : "A",
            "score" : 5.0
        }, 
        {
            "grade" : "A",
            "score" : 7.0,
            "date" : ISODate("2013-06-05T00:00:00.000Z")
        }, 
        {
            "score" : 12.0,
            "date" : ISODate("2012-04-13T00:00:00.000Z"),
            "grade" : "A"
        }, 
        {
            "date" : ISODate("2011-10-12T00:00:00.000Z"),
            "grade" : "A",
            "score" : 12.0
        }
    ],
    "name" : "Riviera Caterer",
    "restaurant_id" : "40356018"
}
```

Import attached json file in mongo ( basically , it is set of documents for restaurants collection ) - 3778 records
```
mongoimport --db myDb --collection restaurants --file C:\Users\xoxox\xoxox\restaurants.json
```

**display the fields restaurant_id, name, borough and cuisine for all the documents, but exclude the field _id**
```
db.restaurants.find({},{"restaurant_id" : 1,"name":1,"borough":1,"cuisine" :1,"_id":0});
```

**display first 5 restaurants which is in the borough Bronx**
```
db.restaurants.find({"borough": "Bronx"}).limit(5);
```

**display the next 5 restaurants after skipping first 5**
```
db.restaurants.find({"borough": "Bronx"}).skip(5).limit(5);
```

**restaurants who achieved a score more than 90**
```
db.restaurants.find({grades : { $elemMatch:{"score":{$gt : 90}}}});
```

**AND / OR operator**
```
db.mycol.find({ $and: [ {<key1>:<value1>}, { <key2>:<value2>} ] })
db.mycol.find({ $or: [ {<key1>:<value1>}, { <key2>:<value2>} ] })

db.restaurants.find(
{
    $and:
    [
        {"borough": "Bronx"},{"cuisine":"Hamburgers"}
    ]
})

```

**restaurants that achieved a score is more than 80 but less than 100**

```
db.restaurants.find(
{
    grades : 
    { 
        $elemMatch:
        {
            "score":
            {
                $gt : 80 , $lt :100
            }
        }
    }
});
```

**IN Operator***
```
{ field: { $in: [<value1>, <value2>, ... <valueN> ] } }

db.restaurants.find(
{
    "borough":
    {
        $in:
        [
            "Bronx","Staten Island"
        ]
    }
}
);
```

**Update document**
```
db.restaurants.update(
{
    "name" : 
        {
            $in:
            [
                "Wendy'S","Riviera Caterer"
            ]
        }
},
{
    $set:
    {
        "cuisine":"indian",
        "borough":"ahmedabad"
    }
},
{
    multi:true
}
);
```




