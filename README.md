# Git-Out-There – Project Retrospective and Overview

[Video Walkthrough](#)  
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

### Tom Kerr

- [Pull Requests](https://github.com/dayshaunlyy/Git-Out-There/pulls?q=is%3Apr+assignee%3Atkerr-csumb+is%3Aclosed)
- [Issues Created](https://github.com/dayshaunlyy/Git-Out-There/issues?q=is%3Aissue%20author%3Atkerr-csumb) 

#### Role / Stories Worked On

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

### Dayshaun Ly

- [Pull Requests](https://github.com/dayshaunlyy/Git-Out-There/pulls?q=is%3Apr+author%3Adayshaunlyy+is%3Aclosed)
- [Issues Created](https://github.com/dayshaunlyy/Git-Out-There/issues?q=is%3Aissue%20state%3Aclosed%20author%3Adayshaunlyy)

#### Role / Stories Worked On

- UI level coding
- Favorites Functionality: UI, viewModel, factory
- Guest User Feed

#### Biggest Challenge

- Researching and implementing star icons

##### Why was it a challenge? 

- Learning a new language while trying to get results within our time limit.

##### How was the challenge addressed?

- Had to take a lot of time out of the day to find certain work arounds.

