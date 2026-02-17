# Git-Out-There – Project Retrospective and Overview

[Video Walkthrough](https://www.youtube.com/watch?v=RhmnjzB2HEU)  
[GitHub Repository](https://github.com/dayshaunlyy/Git-Out-There)

---

## Overview

**Git-Out-There** is an Android application built with Kotlin that helps developers discover open-source repositories looking for contributors.

The app uses the GitHub REST API to:

- Browse repositories
- View repository details
- Explore open issues
- Identify contribution opportunities
- Save favorite repositories (authenticated users only)

Guest users can freely browse repositories, while authenticated users can create an account, sign in, and save repositories to a personalized favorites list stored locally using Room.

---

### Features

#### Guest Users
- Browse repositories
- View repository details
- View open issues

#### Authenticated Users
- Create account
- Sign in
- Save repositories to favorites
- View and manage saved repositories

---

## Introduction

### Communication

Team communication was managed through:
- GitHub Issues for task tracking
- GitHub Pull Requests for code review
- In-person collaboration and discussion

We relied on feature branches and PR reviews to minimize conflicts.

### Stories / Issues

- Initial stories planned: 12–15
- Core stories completed:
  - Authentication
  - Repository browsing
  - Favorites persistence
  - API integration

Some stretch features (advanced filtering, sorting, etc.) were considered but not fully implemented due to time constraints.

---

## Team Retrospective

### Nate Towsley

- [Pull Requests](https://github.com/dayshaunlyy/Git-Out-There/pulls?q=is%3Apr+is%3Aclosed+assignee%3Anatetowsley)
- [Issues Created](https://github.com/dayshaunlyy/Git-Out-There/issues?q=is%3Aissue%20author%3Anatetowsley) 

#### Role / Stories Worked On

Nate focused on implementing markdown rendering for README files, account creation logic, and ensuring app reliability through Compose UI testing.

- Markdown rendering for README display
- Account creation logic
- Room database integration for Users
- Compose UI testing

#### Biggest Challenge

Using regex to remove tags necessary so that repository README.md files could be displayed natively through app.

##### Why was it a challenge?

- Umfamiliarity with regex syntax
- Certain icons (badges, etc) unable to be displayed

##### How was the challenge addressed?

- Research on regex tags and how to best edit raw md to be friendlier with interface

##### Favorite / most interesting part of this project

The most interesting part of this project was working with Room database again, specifically with Dao's and Repositories.

##### If you could do it over, what would you change?
- Design reusable Compose components earlier to simplify UI management

- Improve state handling and loading indicators when switching between repository detail views.


##### What is the most valuable thing you learned?

The most valuable lesson I learned from this project is that time constraints mean planning and cutting features is necessary.

### Tom Kerr

- [Pull Requests](https://github.com/dayshaunlyy/Git-Out-There/pulls?q=is%3Apr+assignee%3Atkerr-csumb+is%3Aclosed)
- [Issues Created](https://github.com/dayshaunlyy/Git-Out-There/issues?q=is%3Aissue%20author%3Atkerr-csumb) 

#### Role / Stories Worked On

Tom worked on essential user account features and project setup, including configuring the .gitignore file, implementing logout functionality, and building the logic to save and manage favorites within user profiles.

- Created .gitignore file
- Logout button and function
- Saving Favorites to profile

#### Biggest Challenge

Remembering how to use Android Studio/learning Kotlin in tandem with four other people

##### Why was it a challenge?

- Not knowing if the code I was making was implemented correctly
- Time management and trying to get my work done so that my teammates could build off of it

##### How was the challenge addressed?

- Code review via pull requests and if something slipped through the cracks, debugging

##### Favorite / most interesting part of this project

- The most interesting part of this project was working with the Android architecture

##### If you could do it over, what would you change?
- Devote more time to learning Kotlin commands & Android Studio

- Implement the personal profile and extra features related to it 

##### What is the most valuable thing you learned?

The most valuable lesson I learned through this project was how crucial it is to make sure code gets reviewed before being merged.

### Dayshaun Ly

- [Pull Requests](https://github.com/dayshaunlyy/Git-Out-There/pulls?q=is%3Apr+author%3Adayshaunlyy+is%3Aclosed)
- [Issues Created](https://github.com/dayshaunlyy/Git-Out-There/issues?q=is%3Aissue%20state%3Aclosed%20author%3Adayshaunlyy)

#### Role / Stories Worked On

Dayshaun concentrated on front-end development, building key UI components, implementing the Favorites feature across the UI and ViewModel layers, and developing the guest user feed experience.

- UI level coding
- Favorites Functionality: UI, viewModel, factory
- Guest User Feed

#### Biggest Challenge

- Researching and implementing star icons

##### Why was it a challenge? 

- Learning a new language while trying to get results within our time limit.

##### How was the challenge addressed?

- Had to take a lot of time out of the day to find certain work arounds.

##### Favorite / most interesting part of this project

My favorite part of this project was working with the composable UI.

##### If you could do it over, what would you change?
- Focus and Implement more user activities

- Have a better timeline set up for each activities with hard deadlines

##### What is the most valuable thing you learned?

The most valuable lesson I learned through this project was how breaking down an app into user stories helps us see what is necessary versus what can wait.

### Brandon Hernandez-Cano

- [Pull Requests](https://github.com/dayshaunlyy/Git-Out-There/issues?q=state%3Aclosed%20is%3Apr%20author%3AS0nbr4ndonz+is%3Aclosed)
- [Issues Created](https://github.com/dayshaunlyy/Git-Out-There/issues?q=is%3Aissue%20state%3Aclosed%20author%3AS0nbr4ndonz)

#### Role / Stories Worked On

Brandon handled authentication and data integration, implementing login logic and error handling, as well as fetching, structuring, and formatting data from the REST API for use within the app.

- Log in logic
- Log in error
- Pulling data from REST API
- Formatting data pulled from REST API

#### Biggest Challenge

- The biggest challenge was definitely learning to fetch and structure data from a REST API using kotlin. Particularly, properly modeling the data fetched in JSON format to work with Kotlin models.

##### Why was it a challenge? 

- It was required to simulatenously learn Kotlin, re-learn and apply Android development patterns, understand how interfaces, data classes, and repositories interact with one another, and mapping JSON responses into Kotlin models.

##### How was the challenge addressed?

- By dedicating more time outside of coding to study kotlin fundamentals, android architecture, and the Retrofit library, I was able to develop a better understanding of how data modeling and networking collaborate in an android application.

##### Favorite / most interesting part of this project

- My favorite part of this project was re-learning how the android architecture functions and how different classes and views interact and depend on one another.

##### If you could do it over, what would you change?
- Allocate more time into exploring GitHub REST API

- Better understand response structures and query parameters

- Develop better strategy in implementing filtering/personalization

##### What is the most valuable thing you learned?

The most valuable lesson I learned through this project was how important it is to identify what is absolutely necessary in a product and focusing on that first instead of trying to get all the wants done.
