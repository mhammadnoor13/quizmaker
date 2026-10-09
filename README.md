# QuizMaker

QuizMaker is a Java project for modeling multiple-choice questions and their answer choices. It combines a small domain module, unit tests for its validation rules, and a documentation site with a UML class diagram.

The project is at an early stage: this repository currently contains the domain model and design documentation. There is no runnable web application, REST API, or database integration yet.

## What is implemented

- Questions and answer choices with automatically generated UUIDs.
- Text validation: question and choice text cannot be null, empty, or whitespace-only; accepted text is trimmed.
- A correctness flag on each answer choice.
- Question validation based on having at least two choices.
- JUnit tests for construction and validation behavior.
- Design documentation written in AsciiDoc, with PlantUML diagrams and an Antora site generator.

## Technology stack

| Area | Technology |
| --- | --- |
| Domain model | Java 17 |
| Build | Maven, with a parent project and a domain module |
| Unit tests | JUnit Jupiter 5.11.0 |
| Documentation site | Antora 3.1.14 |
| Documentation sources | AsciiDoc and PlantUML |
| Diagram integration | `asciidoctor-plantuml` |

Node.js dependencies are used to generate the documentation site; the Java module has no runtime dependencies declared in its POM.

## Repository structure

| Path | Purpose |
| --- | --- |
| [`quizmaker-backend/pom.xml`](quizmaker-backend/pom.xml) | Parent Maven project declaring the domain module |
| [`quizmaker-backend/quizmaker-domain/`](quizmaker-backend/quizmaker-domain/) | Java domain classes, unit tests, and module build configuration |
| [`quizmaker-doc/`](quizmaker-doc/) | Antora component, navigation, AsciiDoc pages, and PlantUML sources |
| [`antora-playbook.yml`](antora-playbook.yml) | Documentation inputs, UI bundle, diagram extension, and output configuration |
| [`package.json`](package.json) | Documentation tooling dependencies |

The Java package is `fr.alma.quizmaker.domain`.

## Getting started

### Prerequisites

- Git.
- JDK 17 or later; the Maven compiler targets Java 17.
- Maven 3.x, installed separately; a Maven wrapper is not included.
- Node.js and npm, only if you want to build the documentation. The locked Antora packages require Node.js 16 or later.

### Clone the repository

```bash
git clone https://github.com/mhammadnoor13/quizmaker.git
cd quizmaker
```

### Build and test

Run these commands from the repository root.

To run the unit tests:

```bash
mvn -f quizmaker-backend/pom.xml test
```

To clean, run tests, and package the domain module:

```bash
mvn -f quizmaker-backend/pom.xml clean verify
```

The resulting JAR is generated at:

```text
quizmaker-backend/quizmaker-domain/target/quizmaker-domain-1.0-SNAPSHOT.jar
```

This JAR contains the domain classes and has no application entry point. Maven downloads the required build plugins and test dependencies on the first build.

## Domain model

| Class | Data | Behavior |
| --- | --- | --- |
| [`Question`](quizmaker-backend/quizmaker-domain/src/main/java/fr/alma/quizmaker/domain/Question.java) | UUID, text, and a list of choices | Validates and trims text, adds choices, and checks whether at least two choices exist |
| [`Choice`](quizmaker-backend/quizmaker-domain/src/main/java/fr/alma/quizmaker/domain/Choice.java) | UUID, text, and a correctness flag | Validates and trims text, and exposes whether the choice is correct |

### Current behavior

- Both constructors throw `IllegalArgumentException` for null or blank text.
- `Question.isValid()` checks only the number of choices. It does not require a correct answer or enforce exactly one correct answer.
- A question can be constructed with fewer than two choices; `isValid()` then returns `false`.
- `Question` retains the supplied list, and `getChoices()` returns that same list. Supply a non-null, mutable list if you intend to call `addChoice()`.
- The `Choice` constructor has package-private visibility, so direct construction is limited to `fr.alma.quizmaker.domain`.

### Tests

The repository includes seven test methods across [`ChoiceTest`](quizmaker-backend/quizmaker-domain/src/test/java/fr/alma/quizmaker/domain/ChoiceTest.java) and [`QuestionTest`](quizmaker-backend/quizmaker-domain/src/test/java/fr/alma/quizmaker/domain/QuestionTest.java). They cover rejection of null and empty text, successful construction, generated identifiers, and question validity with one or two choices.

## Build the documentation

From the repository root:

```bash
npm ci
npx antora antora-playbook.yml
```

The generated site is written to `build/site/`. Open `build/site/index.html` to browse it locally.

The playbook reads the `quizmaker-doc` component from the local Git repository at `HEAD`. Commit documentation changes before rebuilding if you want those changes included in the generated site.

The build also uses an external Antora UI bundle hosted on GitLab and the configured PlantUML server to render diagrams, so network access is required.

Documentation sources:

- [Design page](quizmaker-doc/modules/ROOT/pages/conception.adoc)
- [Question and choice class diagram](quizmaker-doc/modules/ROOT/examples/question-choice.puml)
- [Navigation](quizmaker-doc/modules/ROOT/nav.adoc)

The UML diagram describes the intended relationship between questions and choices. Refer to the Java source for the current constructor signatures and validation behavior.
