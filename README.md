# Cases Service

Create .env file 
```shell
[ -f .env ] || cp env-sample .env
```

```shell
# Build the project
./gradlew build

# Run the application
./gradlew bootRun
```

To run the app attaching the javaajent
```shell
./gradlew build; java -javaagent:/path/to/dd-java-agent.jar \
  -Ddd.logs.injection=true \
  -Ddd.service=cases-service \
  -Ddd.env=local \
	  -jar build/libs/cases-service-0.0.1-SNAPSHOT.jar
```
