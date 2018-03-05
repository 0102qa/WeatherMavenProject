# WeatherMavenProject
End-to-end test framework for Demo Site


UI – `http://35.227.188.130/`

api-proxy - `http://35.203.167.23/`

`/latlong/{string}` – This grabs the lat long of a string

`/weather/{lat}/{long}/{epochTime}` – This grabs 8 days of weather data from
the epochTime.


## Overview
GEMS UI tests are written in Java and used such extensions:
 - [Test NG](http://testng.org/doc/index.html) - is a testing framework;
 - [Maven](https://maven.apache.org/index.html) - is a software project management and comprehension tool;
 - [REST Assured](http://rest-assured.io/) - is a Java library that provides a domain-specific language (DSL) for writing powerful, maintainable tests for RESTful APIs;
 - [Selenium WebDriver](https://www.seleniumhq.org/) - is a collection of open source APIs which are used to automate the testing of a web application.
 
 ## Run tests
To run test features, go to the project dir in console and perform:

```
mvn test
```
### Configuration
The project related configuration is stored in [data.properties](https://github.com/0102qa/WeatherMavenProject/blob/master/src/main/java/resources/data.properties).

#### Environment
Tests could be run against any enveroumenf. To specify concrete env, set url as a value for `aplicationUrl` key in [data.properties](https://github.com/0102qa/WeatherMavenProject/blob/master/src/main/java/resources/data.properties).


#### Browsers
Two browsers are allowed to be used for web interactions in the framework: _chrome_ and _firefox_. To specify browser that would be used in tests, set `chrome` or `firefox` as a value for `browser` key in [data.properties](https://github.com/0102qa/WeatherMavenProject/blob/master/src/main/java/resources/data.properties).

#### Search parameter
Test could be run with any search parameter. To specify concrete parameter, set city name as a value for `city` key in [data.properties](https://github.com/0102qa/WeatherMavenProject/blob/master/src/main/java/resources/data.properties).

### Covered cases:
* Api-proxy return correct status code
* Api-proxy return correct content Type
* Api call take not longer than 5 sec.
* Api-proxy return not empty response
* UI search request match with search request in API
* First search result date on UI match with current user date
* Correct number of results displayed on the page
* UI display correct information that API sent:
  * city name
  * date
  * temperature
  * details
