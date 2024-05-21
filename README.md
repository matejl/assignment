# assignment

This is a solution for assignment in Quarkus.

## Running the application
Currently, the application was only run in a dev mode. It sets up the docker container with Postgres database, executes
the migrations and runs the application in live mode.

You can run your application in dev mode that enables live coding using:
```shell script
./gradlew quarkusDev
```
The application starts at port `8080`.

## Endpoints
Application provides 3 endpoints.

IntelliJ Http Client example is in `./docs/workflow.http`.

There are also tests that describe the workflow.

### Description of the workflow
Although the examples are provided, the workflow is described here in more detailed form.

First, a new trader needs to be created which supports taxation type either `PER_RATE` or `PER_AMOUNT`.
```
POST /traders
{
    "taxationType": "<PER_RATE|PER_AMOUNT>",
    "taxRate": 0.1,   // if taxationType=PER_RATE
    "taxAmount": 10   // if taxationType=PER_AMOUNT
}
```
It returns like the following:
```
{
  "id": 1,
  "taxationType": "PER_RATE",
  "taxRate": 10,
  "taxAmount": null
}
```

Then, to calculate the taxation, the following endpoints are provided for general taxation and winnings:
```
GET /taxations/general?traderId=<traderId>&playedAmount=<amount>&odd=<odd>
```
```
GET /taxations/winnings?traderId=<traderId>&playedAmount=<amount>&odd=<odd>
```

## Additional remarks
This project is only intended as a demo from the coding perspective.

For any live environments, at least the application settings would need to be configured accordingly.

Beware that there is also no authentication at this point so it is not secure.

Of course, we are only testing happy paths in the tests. We should be adding more edge cases and unit tests.