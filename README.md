# Scalajs Workshop

This repo hosts a workshop on [ScalaJS](https://www.scala-js.org/), using [scalajs-react](https://github.com/japgolly/scalajs-react) by David Barry (Japgolly)

The project is organised in 3 modules:

- `shared` contains common classes, used on the frontend and on the backend
- `jvm` is the server side
- `js` is the front side

## Prerequisites

In order to have everything working, you will need:

- Your favorite IDE supporting scala
- `sbt`, consider using [coursier](https://github.com/coursier/coursier) for better dependency management
- `npm`, consider using [nvm](https://github.com/creationix/nvm) to manage node versions in a better way
- [yarn](https://yarnpkg.com/en/docs/install)

## Starting the backend application

The most simple way to do it is with the following sbt command:

```
sbt jvm/run
```

## Starting the front application in dev mode

Again use sbt:


```
sbt fastOptJS::startWebpackDevServer ~fastOptJS
```

This command will will:
- Start the webpack dev server
- rebuild the scala part incrementally and use `hot` to reload the application

If you change the javascript dependencies, you will have to do a full rebuild of the js module.
