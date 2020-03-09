# prime-number-generator

This web service generates prime numbers up till a given integer n (where n < 10000000).

This service implements 3 well known algorithms to generate primes -
1) Sieve of Atkins
2) Sieve of Sundaram
3) Sieve of Eratoshenes

User can specify the algorithm as an URL parameter. By default this service uses Atkins for its computation speed & efficiency.

The service can respond back in 3 different media types -
1) application/json
2) application/xml
3) text/plain

User can specify the response media type with Accept header. By default, this service responds in application/json.

To improve efficiency of the service, we cache the results of each algorithm at the initial request & serve the responses from the cache unless a larger integer n is now given as input. If we get a larger integer n than we have in our cache, we will recompute the primes using the algorithm & cache those results again.

Please refer to the swagger.json file to get detailed information about the endpoints.

To run the service locally -

gradle clean build bootRun

Pre-requisite : Please ensure you have Java 8 & Gradle 4.10 or above version installed.

This service has also been deployed to Google Cloud Platform. You can access the service at ->
https://prime-number-generator.appspot.com/primes/{n}

Kindly note below -

1) If you want to test with larger values, please use Atkins algorithm. The largest value the service can take is 10 million.
2) The Google Cloud Account is personal account, hence the instances are scaled down unless first traffic hits the service. First few calls can be slow. If the URL is down or not working, please email nikhil.palavalasa@gmail.com
3) If you are using Postman tool to test the service, kindly note that the tool takes time to render the response of large inputs. The server responds fast enough (especially cached responses), however Postman takes sometime to render the output.

Some sample test URLs ->

1. https://prime-number-generator.appspot.com/primes/20?algorithm=sundaram
2. https://prime-number-generator.appspot.com/primes/200?algorithm=atkins
3. https://prime-number-generator.appspot.com/primes/20?algorithm=eratoshenes
4. https://prime-number-generator.appspot.com/primes/2000
