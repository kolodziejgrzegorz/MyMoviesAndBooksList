# MyMovieAndBooksList
>JavaFx app with OrmLite and H2 database

## Table of contents
* [General info](#general-info)
* [Technologies](#technologies)
* [Setup](#setup)
* [Features](#features)
* [Inspiration](#inspiration)

## General info
Simple JavaFx app to store list of movies and books in H2 database. It uses an in-memory database (H2) which gets populated at startup with sample data.

## Technologies
* Java 1.8
* OrmlLite 5.0
* H2 database 1.4

## Setup:

1. Clone github repository
2. Download maven dependencies
3. Hit run button

To change language go to Main class and uncomment
 > Locale.setDefault(new Locale("pl")); -> for polish
 > Locale.setDefault(new Locale("en")); -> for english

## Features:

-show table of books stored in database with information like author, category, title, description, etc.
-show table of authors with name and surname
-show table of categories with name and list of books
-add/delete/edit books, authors and categories
-store date in H2 database
-change language english <-> polish
-two styles Caspian and Modena
-option to show app always on top
-show table of movies stored in database with information like director, genre, title, storyline, etc.
-show table of directors with name and surname
-show table of genres with name and list of movies
-add/delete/edit movies, directors and genres

## Inspiration
This app is basen on JavaFx course from
http://zacznijprogramowac.net/category/kursy-programowania/you-tube/kurs-javafx/