# Open Data for Java (WIP)

An unofficial Java wrapper for the University of Waterloo's [Open Data API](https://uwaterloo.ca/api/).

### Basic Usage

Initialize a UWaterlooClient object by passing in your API key (get one [here](https://uwaterloo.ca/api/register)).

```java
UWaterlooClient client = new UWaterlooClient("api_key");
```

Now you're ready to make requests! Here's an example to get you started:

```java
Course course = client.getCourse("ECE", "105");
if(course.getOfferings().isOnline()) {
	System.out.println("I can take this course during a co-op term!");
}
```