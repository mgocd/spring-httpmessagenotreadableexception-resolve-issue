This repository reproduces an issue in `DefaultHandlerExceptionResolver#handleHttpMessageNotReadable` method occurring in Spring Framework `6.1.3` when using Tomcat >= `10.1.16` (e.g. Spring Boot >= `3.1.6`).

It serves a single POST endpoint `/hello` (in `WebController`), and uses the load-time AspectJ weaving to throw an `IOException` from `org.apache.coyote.Request.doRead` method.

Steps to reproduce:

1. Observe the Tomcat version override set to `10.1.15` in the `dependencyManagement` section of `build.gradle`, and run `./gradlew test`.
2. Observe the test success and no exception, and a (expected) warning:
```
DefaultHandlerExceptionResolver : Resolved [org.springframework.http.converter.HttpMessageNotReadableException: I/O error while reading input message]
```
3. Comment out the `dependencyManagement` section to bring Tomcat `10.1.18`, and run `./gradlew test` (it's also reproducible for Tomcat >= `10.1.16`).
4. Observe the test failure and the exception: 

```
DefaultHandlerExceptionResolver : Failure while trying to resolve exception [org.springframework.http.converter.HttpMessageNotReadableException]

    java.lang.IllegalStateException: Cannot call sendError() after the response has been committed
```

