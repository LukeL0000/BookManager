# Personal Project - Book Collection Manager

## Instructions for Grader

- You can generate the first required event by selecting the "Add Shelf" button
on the menu, and entering a string for a shelf name that does not already exist.
- You can generate the second required event by selecting the "Add Book" button
on the menu, and filling the boxes using the instructions from the pop-up. Please
make sure that the title is not already taken by another book.
- Additional components include "Move Shelf" and "Move Book". These buttons move
elements in the collection, and usage instructions are in the pop-up. 
- The visual component is already displayed on-screen, with the shelves and books.
The "Find Book" button can also display a section of text in a new window containing 
information about the given book.
- You can save the state of my application by clicking the "Save" button.
- You can reload the state of my application by clicking the "Load" button.

## Overview

This project will be based upon the needs of anyone with a book collection, 
helping to both organize and add to the current collection and figure 
out which book(s) to read next. Given the similarity of a book collection to an
anime or movie list, this software could also fit the need of those demographics.

The software's overview by the end of Phase 3 is to include the current features:
- Adding a book into the collection
- Adding a shelf into the collection
- Finding a certain book in the collection
- Displaying information about any book in the collection
- Displaying a basic overview of the entire collection
- Moving books between shelves in the collection
- Saving the state of the book collection to load later
- Loading any previously saved collection
- GUI for the application

Features to add later:
- Updating the information about any book or shelf in the collection
- Tracking and updating progress on books in the collection

In essence, it will be an almost comprehensive solution to managing a collection
digitally for any dedicated bookworm. And, they can potentially manage themselves 
too using the pacing and progress tracking tools to be implemented later.

Personally, I do enjoy books but often have issues involving pacing and deciding
exactly what to buy or read next. Therefore, this project is of personal interest,
for it can help fix many of the difficulties of managing my collection right now.


## User Journal:

Phase 1:
- I want to see list of all my current books
- I want to be able to find a book in the collection
- I want to see all the information about any book in the collection
- I want to be able to add any amount of books into the collection
- I want to be able to add any amount of shelves into the collection
- I want to be able to move any book between shelves in the collection
- I want to be able to move any shelf in the collection

After Phase 1:

- I want to be able to save the current collection to reopen later
- I want to be able to load any previously saved collection
- I want a prompt that reminds me to save when exiting the application
- I want a prompt that reminds me to load when the current collection is empty
- I want to track my progress on books in the collection
- I want to update the information about any existing book in the collection
- I want to update the information about any existing shelf in the collection
- I want to have some method (aside from excel) to organize my collection
- I want said method to organize quickly without the need for input from my end
- I want something to organize my collection in ways that I am too lazy to do
- I want something to figure out what book(s) to buy next
- I want something to figure out what book(s) to read next

## Phase 4 - Task 2

### Expected log if no loading occurs, exited VIA application button:

Wed Aug 10 17:31:33 PDT 2022

Added a shelf named Shelf 1 to the collection.

Wed Aug 10 17:31:38 PDT 2022

Added a shelf named Shelf 2 to the collection.

Wed Aug 10 17:31:44 PDT 2022

Added a shelf named Shelf 3 to the collection.
 
Wed Aug 10 17:31:48 PDT 2022

Added a shelf named Shelf 4 to the collection.

Wed Aug 10 17:32:11 PDT 2022

Book with title Title added to collection.

Wed Aug 10 17:32:26 PDT 2022

Moved shelf named Shelf 1 to Shelf 4.

Wed Aug 10 17:32:44 PDT 2022

Book with title Title moved to Shelf 2.

Wed Aug 10 17:32:52 PDT 2022

Found book with title Title in Shelf 2

Wed Aug 10 17:33:00 PDT 2022

Saved book collection.

### Expected log output from loading procedure alone:

Wed Aug 10 17:36:20 PDT 2022

Loaded JSON data for parsing.

Wed Aug 10 17:36:20 PDT 2022

Added a shelf named Shelf 2 to the collection.

Wed Aug 10 17:36:20 PDT 2022

Book with title Title added to collection.

Wed Aug 10 17:36:20 PDT 2022

Added a shelf named Shelf 3 to the collection.

Wed Aug 10 17:36:20 PDT 2022

Added a shelf named Shelf 1 to the collection.

Wed Aug 10 17:36:20 PDT 2022

Added a shelf named Shelf 4 to the collection.

### No log output expected when exiting via window, as it initiates System.exit(0),
### which means program shuts down before outputting event log.

## Phase 4 - task 3

From the design of the code, I believe that some refactoring is required to move some
classes into the correct packages, as well as moving some functions into new
classes to improve cohesion. The list of refactors are as follows:
- Refactor the EventLog and Event classes into their own observer package.
- Refactor bookshelf/shelf/book to follow composite pattern. 
- Refactor the addBook and findBook methods into an abstract class extended by Bookshelf
and Shelf, or make an interface that handles processes that require both classes. This
also includes the helper functions such as findShelfWithBook.
- Turn shelf into an iterable over book to facilitate find/move book commands.
- Refactor ProgressTracker (not used in project) to be included inside book so it's
easier to call.
- Initialize the ToolBar subclasses as ToolBar to simplify calls.
- Refactor pop-up and button creation for the ToolBar buttons into a new class and
call that instead, or just add button creation into Interface.
- Refactor the Add, Move, Load/Save/Exit related buttons into 3 different classes to
reduce complexity of toolbar package.
- Refactor project to include more try-catch and exceptions for increased robustness.
- Change the way books are stored to a map for easier finding/moving. This can
also apply to how shelves work - for example, we can have key = shelf, value = list of books.
#� �B�o�o�k�C�o�l�l�e�c�t�i�o�n�M�a�n�a�g�e�r�
�
�
