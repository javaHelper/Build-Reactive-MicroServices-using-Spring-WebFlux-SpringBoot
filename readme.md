# Build-Reactive-MicroServices-using-Spring-WebFlux-SpringBoot

<img width="607" alt="Screenshot 2022-07-22 at 7 49 43 PM" src="https://user-images.githubusercontent.com/54174687/180459064-3fb065ed-e9b5-422e-ba1a-d2df439f86b3.png">

- Create data - see attached json files

# movie-info-service

GET -> `http://localhost:8080/v1/movieinfos`

GET -> `http://localhost:8080/v1/movieinfos?year=2017`

GET -> `http://localhost:8080/v1/movieinfos?name=Golmaal Again`

GET -> `http://localhost:8080/v1/movieinfos/62da836342b68c5790658f49`

I've not shown POST, PUT and DELETE, you can try that out!

------------

# movie-reviews-servive

GET -> `http://localhost:8091/v1/reviews/`

GET -> `http://localhost:8091/v1/reviews?movieInfoId=62da836342b68c5790658f45`

---------

# movie-service

GET -> `http://localhost:8092/v1/movies/62da836342b68c5790658f47`

Response:

```sh
{
    "movieInfo": {
        "movieInfoId": "62da836342b68c5790658f47",
        "name": "Golmaal 3",
        "year": 2011,
        "cast": [
            "Ajay Devgn",
            "Kareena Kapoor",
            "Arshad Warsit"
        ],
        "release_date": null
    },
    "reviewList": [
        {
            "reviewId": "62da8dc94c0b11742cde96b0",
            "movieInfoId": "62da836342b68c5790658f47",
            "comment": "Good Movie",
            "rating": 7.2
        }
    ]
}
```





