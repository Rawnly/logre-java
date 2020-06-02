# LogreIO Java Client (Docs)
> Java driver to send logs to [Logre.IO](https://logre.io)

## Installation
```xml
<dependency>
    <groupId>com.github.rawnly</groupId>
    <artifactId>logre-client-java</artifactId>
    <version>{latest}</version>
</dependency>
```

## Quick Start
```java
class HelloWorld {
	// Initialize the class
	private static final LogreClient log = new LogreClient("<boxId>", "<auth-token>", MyClass.self);

	public static void main(String[] args) {
		// Start Logging!
		log.info("Hello World!");


		System.out.println("Hello World!");
	}
}
```

### Available Constructors
- `LogreClient(String boxId, String authToken, Class<?> aClass)`
- `LogreClient(String boxId, Class<?> aClass)`

### Proxy requests
LogreClient uses Unirest under the hood, so you can easily setup a proxy via the `setProxy` method.
```java
	private final LogreClient log = new LogreClient(...) 
	
	
	// Later somewhere in your code
	log.setProxy(String host, int port) 
	// or
	log.setProxy(String host, int port, String username, String password) 
```
