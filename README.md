# LogreIO Java Client (Docs)
> Java driver to send logs to [Logre.IO]

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
