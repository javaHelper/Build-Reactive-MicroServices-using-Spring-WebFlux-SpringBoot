# Build-Reactive-MicroServices-using-Spring-WebFlux-SpringBoot

<img width="607" alt="Screenshot 2022-07-22 at 7 49 43 PM" src="https://user-images.githubusercontent.com/54174687/180459064-3fb065ed-e9b5-422e-ba1a-d2df439f86b3.png">

- Create data by saving below:

```json
db.getCollection('movieInfo').find({})
{
    "_id" : ObjectId("62da836342b68c5790658f45"),
    "name" : "Singham",
    "year" : 2011,
    "cast" : [ 
        "Ajay Devgn", 
        "Kajal Aggarwal", 
        "Prakash Raj"
    ],
    "releaseDate" : ISODate("2011-07-21T18:30:00.000Z"),
    "_class" : "com.example.demo.domain.MovieInfo"
}
{
    "_id" : ObjectId("62da836342b68c5790658f46"),
    "name" : "Once Upon Time In India",
    "year" : 2010,
    "cast" : [ 
        "Ajay Devgn", 
        "Emraan Hashmi", 
        "Kangana Ranaut"
    ],
    "releaseDate" : ISODate("2010-07-29T18:30:00.000Z"),
    "_class" : "com.example.demo.domain.MovieInfo"
}
{
    "_id" : ObjectId("62da836342b68c5790658f47"),
    "name" : "Golmaal 3",
    "year" : 2011,
    "cast" : [ 
        "Ajay Devgn", 
        "Kareena Kapoor", 
        "Arshad Warsit"
    ],
    "releaseDate" : ISODate("2010-10-04T18:30:00.000Z"),
    "_class" : "com.example.demo.domain.MovieInfo"
}
{
    "_id" : ObjectId("62da836342b68c5790658f48"),
    "name" : "Tanhaji",
    "year" : 2021,
    "cast" : [ 
        "Ajay Devgn", 
        "Saif Ali Khan", 
        "Kajol"
    ],
    "releaseDate" : ISODate("2021-01-09T18:30:00.000Z"),
    "_class" : "com.example.demo.domain.MovieInfo"
}
{
    "_id" : ObjectId("62da836342b68c5790658f49"),
    "name" : "Golmaal Again",
    "year" : 2017,
    "cast" : [ 
        "Ajay Devgn", 
        "Tushar Kapoor", 
        "Tabu"
    ],
    "releaseDate" : ISODate("2017-09-19T18:30:00.000Z"),
    "_class" : "com.example.demo.domain.MovieInfo"
}
```

Another collection

```json
db.getCollection('review').find({})
{
    "_id" : ObjectId("62da8dc94c0b11742cde96ae"),
    "movieInfoId" : "62da836342b68c5790658f45",
    "comment" : "Good Movie",
    "rating" : 8.0,
    "_class" : "com.example.demo.model.Review"
}
{
    "_id" : ObjectId("62da8dc94c0b11742cde96af"),
    "movieInfoId" : "62da836342b68c5790658f46",
    "comment" : "Good Movie",
    "rating" : 8.8,
    "_class" : "com.example.demo.model.Review"
}
{
    "_id" : ObjectId("62da8dc94c0b11742cde96b0"),
    "movieInfoId" : "62da836342b68c5790658f47",
    "comment" : "Good Movie",
    "rating" : 7.2,
    "_class" : "com.example.demo.model.Review"
}
{
    "_id" : ObjectId("62da8dc94c0b11742cde96b1"),
    "movieInfoId" : "62da836342b68c5790658f48",
    "comment" : "Good Movie",
    "rating" : 9.0,
    "_class" : "com.example.demo.model.Review"
}
{
    "_id" : ObjectId("62da8dc94c0b11742cde96b2"),
    "movieInfoId" : "62da836342b68c5790658f49",
    "comment" : "Good Movie",
    "rating" : 6.7,
    "_class" : "com.example.demo.model.Review"
}
```

