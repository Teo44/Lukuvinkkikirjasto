# Lukuvinkkikirjasto

Lukuvinkkikirjasto is a simple Java program for saving and organising links, 
books, videos and such to later come back to. The goal of Lukuvinkkikirjasto is 
to replace messy browser bookmarks with a system that offers more functionality 
and ease of use. 

Currently users can save, browser, edit, filter and delete vinks (reading tips) locally 
through a text-based interface. Each vink can have a headline, type, comment, a link and a list of tags. Vinks can also be searched based on these attributes.


[![CircleCI](https://circleci.com/gh/Teo44/Lukuvinkkikirjasto.svg?style=svg)](https://circleci.com/gh/Teo44/Lukuvinkkikirjasto)  
[![codecov](https://codecov.io/gh/Teo44/Lukuvinkkikirjasto/branch/master/graph/badge.svg)](https://codecov.io/gh/Teo44/Lukuvinkkikirjasto)

### Documentation
#### Definition of done
  * Task code implemented
  * Task test coverage at least 70%
  * All tests pass
  * Code is integrated
  * Code is documented where necessary
#### Links
[Timesheet](https://docs.google.com/document/d/1zp6uDgYHKWCMQ79mLk7mYPMAjm6WrY5GgZGcwMbQPqI/edit)  
[Product backlog](https://docs.google.com/document/d/1FR4BrOckpbEB3I1rKpynxpawOWVKYhy5OIBTQ3M9wdM/edit)  
[Sprint backlog](https://docs.google.com/spreadsheets/d/1AWoK2_GHIpFiuzt8_Ukvyc1NTDDrdSyNQ1x6abmLS5Q/edit#gid=1495239726)  
[TODO](https://docs.google.com/document/d/1GY2VHXmMcwrK4B9ckDM1jvYDnR3jMSKriCtN3BO0_do/edit)  
[Burndown](https://docs.google.com/spreadsheets/d/1NiYYkdUoy73aPJDHR0R6Ju9xdiTgUT62SOvrWwu4DPk/edit#gid=0)  
[Project documents](https://drive.google.com/drive/folders/1vjlllWe4OPGp9iqdESAkCbsBWRAaRfYo?usp=sharing)  
[CircleCI](https://circleci.com/gh/Teo44/Lukuvinkkikirjasto)  
[Final report](https://docs.google.com/document/d/1SJNS45DyR0Ty3FHat3JKM67OUa8g89Nt_m_M3S62Gaw/edit?folder=1vjlllWe4OPGp9iqdESAkCbsBWRAaRfYo#)

### Releases
[v0.2](https://github.com/Teo44/Lukuvinkkikirjasto/releases/tag/v0.2)  

[v0.1](https://github.com/Teo44/Lukuvinkkikirjasto/releases/tag/v0.1)  

### Usage

Available commands:

* **new**    to create a new vink to the database
* **list**   to list all the vinks in the database
* **delete** to delete a vink by title
* **modify** to modify vink's parameters
* **filter** to search vinks using a keyword
* **read**   to list all the vinks with a particular reading progress
* **mark**   to mark a vink as read or unread
* **open**   to open a document according to a vink's file path


### Gradle

#### Installation

After cloning this repository you can run the project with running  
```
./gradlew -q --console plain run
```
Tests can be run with  
```
./gradlew test  
```
Jacoco testreport can be generated with
```
./gradlew jacocoTestReport
```

Checkstyle report can be generated with
```
./gradlew checkstyleMain
```


### License
[MIT-License](https://github.com/Teo44/Lukuvinkkikirjasto/blob/master/LICENSE)
* 


