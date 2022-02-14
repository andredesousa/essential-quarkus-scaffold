# Essential Quarkus Scaffold

This project uses [Quarkus](https://quarkus.io/), the Supersonic Subatomic Java Framework.
It provides a complete **RESTful API** configured, including build, test, and deploy scripts as examples.
It is recommended to have, at least **Java 11**, [GraalVM](https://www.graalvm.org/), [Node.js](https://nodejs.org/en/), [Docker](https://www.docker.com/) and [Ansible](https://www.ansible.com/) installed.

## Table of Contents

- [Project structure](#project-structure)
- [Available gradle tasks](#available-gradle-tasks)
- [Running in development mode](#running-in-development-mode)
- [Linting and formatting code](#linting-and-formatting-code)
- [Running unit tests](#running-unit-tests)
- [Running functional tests](#running-functional-tests)
- [Debugging](#debugging)
- [Commit messages convention](#commit-messages-convention)
- [Building and deploying](#building-and-deploying)
- [Reference documentation](#reference-documentation)

## Project structure

When working in a large team with many developers that are responsible for the same codebase, having a common understanding of how the application should be structured is vital.
Based on best practices from the community, Quarkus, other github projects and developer experience, your project should look like this:

```html
├── ci
|  ├── build
|  └── deploy
├── gradle
├── src
|  ├── functionalTest
|  ├── main
|  |  ├── docker
|  |  ├── java
|  |  |  └── app
|  |  |    ├── AppController.java
|  |  |    └── AppService.java
|  |  └── resources
|  |    ├── application-prod.properties
|  |    └── application.properties
|  └── test
├── .editorconfig
├── .gitignore
├── .prettierrc
├── build.gradle
├── CHANGELOG.md
├── changelog.mustache
├── checkstyle.xml
├── gradlew
├── gradlew.bat
├── LICENSE
├── README.md
└── settings.gradle
```

All of the app's code goes in a folder named `src/main`.
The unit tests and functional tests are in the `src/test` and `src/functionalTest` folders.
Static files are placed in `src/main/resources` folder.

## Available gradle tasks

The tasks in [build.gradle](build.gradle) file were built with simplicity in mind to automate as much repetitive tasks as possible and help developers focus on what really matters.

The next tasks should be executed in a console inside the root directory:

- `./gradlew tasks` - Displays the tasks runnable from root project 'quarkus-app'.
- `./gradlew quarkusDev` - Runs this project with background compilation.
- `./gradlew check` - Runs all checks.
- `./gradlew test` - Runs the unit tests.
- `./gradlew functionalTest` - Run the functional tests.
- `./gradlew lint` - Runs several static code analysis.
- `./gradlew format` - Applies code formatting steps to source code in-place.
- `./gradlew clean` - Deletes the build directory.
- `./gradlew javadoc` - Generates Javadoc API documentation for the main source code.
- `./gradlew generateChangelog` - Generates a changelog from GIT repository.
- `./gradlew dependencyUpdates` - Displays the dependency updates for the project.
- `./gradlew build` - Assembles and tests this project.
- `./gradlew buildImage` - Builds a Docker image of the application.
- `./gradlew release` - Performs release, creates tag and pushes it to remote.
- `./gradlew deploy` - Deploys the application to Docker Swarm.
- `./gradlew help` - Displays a help message.

For more details, read the [Command-Line Interface](https://docs.gradle.org/current/userguide/command_line_interface.html) documentation in the [Gradle User Manual](https://docs.gradle.org/current/userguide/userguide.html).

## Running in development mode

You can run your application in dev mode that enables live coding using `./gradlew bootRun` command.
Quarkus ships with a Dev UI, which is available in dev mode only at <http://localhost:8080/q/dev/>.

This app includes [Swagger](https://swagger.io/). It is available at <http://localhost:8080/q/swagger-ui/>.
The OpenAPI Specification is automatically generated on build. See the file [openapi.json](build/openapi/openapi.json) or [openapi.yaml](build/openapi/openapi.yaml).

## Linting and formatting code

A linter is a static code analysis tool used to flag programming errors, bugs, stylistic errors and suspicious constructs.

It includes [Prettier](https://prettier.io/), [Checkstyle](https://checkstyle.sourceforge.io/), [PMD](https://pmd.github.io/) and [SpotBugs](https://spotbugs.github.io/):

- **Prettier** enforces a consistent style by parsing your code and re-printing it with its own rules, wrapping code when necessary.
- **Checkstyle** finds class design problems, method design problems, and others. It also has the ability to check code layout and formatting issues.
- **PMD** finds common programming flaws like unused variables, empty catch blocks, unnecessary object creation, and so forth.
- **SpotBugs** is used to perform static analysis on Java code. It looks for instances of "bug patterns".

Use `./gradlew lint` to analyze your code. Many problems can be automatically fixed with `./gradlew format` command.
Depending on our editor, you may want to add an editor extension to lint and format your code while you type or on-save.

## Running unit tests

Unit tests are responsible for testing of individual methods or classes by supplying input and making sure the output is as expected.

Use `./gradlew test` to execute the unit tests via [JUnit 5](https://junit.org/junit5/) and [Mockito](https://site.mockito.org/).
Use `./gradlew test -t` to keep executing unit tests in real time while watching for file changes in the background.
You can see the HTML report opening the [index.html](build/reports/tests/test/index.html) file in your web browser.

It's a common requirement to run subsets of a test suite, such as when you're fixing a bug or developing a new test case.
Gradle provides different mechanisms.
For example, the following command lines run either all or exactly one of the tests in the `SomeTestClass` test case:

```bash
./gradlew test --tests SomeTestClass
```

For more details, you can see the [Test filtering](https://docs.gradle.org/current/userguide/java_testing.html#test_filtering) section of the Gradle documentation.

This project uses [JaCoCo](https://www.eclemma.org/jacoco/) which provides code coverage metrics for Java.
The minimum code coverage is set to 80%.
You can see the HTML coverage report opening the [index.html](build/reports/jacoco/test/html/index.html) file in your web browser.

## Running functional tests

Functional tests validate the software system against the functional requirements/specifications.

Use `./gradlew functionalTest` to execute the functional tests via [JUnit 5](https://junit.org/junit5/) and [REST Assured](https://rest-assured.io/).
Use `./gradlew functionalTest -t` to keep executing your tests while watching for file changes in the background.
You can see the HTML report opening the [index.html](build/reports/tests/functionalTest/index.html) file in your web browser.

Like unit tests, you can also run subsets of a test suite.
See the [Test filtering](https://docs.gradle.org/current/userguide/java_testing.html#test_filtering) section of the Gradle documentation

## Debugging

You can debug the source code, add breakpoints, inspect variables and view the application's call stack.
Also, you can use the IDE for debugging the source code, unit and functional tests.
You can customize the [log verbosity](https://docs.gradle.org/current/userguide/logging.html#logging) of gradle tasks using the `-i` or `--info` flag.

This project includes [Swagger](https://swagger.io/). To get a visual representation of the interface and send requests for testing purposes go to <http://localhost:8080/q/swagger-ui/>.

## Commit messages convention

In order to have a consistent git history every commit must follow a specific template. Here's the template:

```bash
<type>(<ITEM ID>?): <subject>
```

### Type

Must be one of the following:

- **build**: Changes that affect the build system or external dependencies (example scopes: Gradle, Maven)
- **ci**: Changes to our CI configuration files and scripts (example scopes: Jenkins, Travis, Circle, SauceLabs)
- **chore**: Changes to the build process or auxiliary tools and libraries such as documentation generation
- **docs**: Documentation only changes
- **feat**: A new feature
- **fix**: A bug fix
- **perf**: A code change that improves performance
- **refactor**: A code change that neither fixes a bug nor adds a feature
- **revert**: A commit that reverts a previous one
- **style**: Changes that do not affect the meaning of the code (white-space, formatting, missing semi-colons, etc.)
- **test**: Adding missing tests or correcting existing tests

### ITEM ID

The related **issue** or **user story** or even **defect**.

- For **user stories**, you shoud use `US-` as prefix. Example: `feat(US-4321): ...`
- For **no related issues** or **defects** you should leave it blank. Example: `feat: ...`

### Subject

The subject contains a succinct description of the change.

## Building and deploying

In `ci` folder you can find scripts for your [Jenkins](https://www.jenkins.io/) CI pipeline and an example for deploying your application with Ansible to [Docker Swarm](https://docs.docker.com/engine/swarm/).

This project follows [Semantic Versioning](https://semver.org/) and uses git tags to define the current version of the project.
Use `./gradlew currentVersion` to print the current version extracted from SCM and `./gradlew release` to release the current version.

You can create a native executable via GraalVM using:

```bash
./gradlew build -Dquarkus.package.type=native
```

You can then execute your native executable with: `./build/quarkus-app-1.0.0-SNAPSHOT-runner`
If you want to learn more about building native executables, please consult <https://quarkus.io/guides/gradle-tooling>.

This project contains a Dockerfile that you can use to build your Docker image. Use `./gradlew buildImage` after `build` command.
Native images provide key advantages, such as instant startup, instant peak performance, and reduced memory consumption.

Also, you can deploy this project to Docker Swarm using `./gradlew deploy` command.

## Reference documentation

For further reference, please consider the following sections:

- [Official Gradle documentation](https://docs.gradle.org)
- [Quarkus QuickStarts](https://github.com/quarkusio/quarkus-quickstarts)
- [Quarkus - Creating your first application](https://quarkus.io/guides/getting-started)
- [Quarkus - building a native executable](https://quarkus.io/guides/building-native-image)
- [Quarkus - Writing JSON REST services](https://quarkus.io/guides/rest-json)
- [Quarkus - Using OpenAPI and Swagger UI](https://quarkus.io/guides/openapi-swaggerui)
- [Quarkus - Testing your application](https://quarkus.io/guides/getting-started-testing)
- [Testing Quarkus Applications](https://www.baeldung.com/java-quarkus-testing)

If you want to learn more about Quarkus, please visit its website: <https://quarkus.io/>.
