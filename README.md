# Personal Project - Book Collection Manager

## Instructions

- The application requires an existing book collection to perform any function
aside from adding a shelf. As such, begin by either loading an existing collection
or adding a shelf. 
- When using any function, the instructions are indicated in the pop-up. Do note 
that the entered info is exactly as it appears in the pop-up.
- For Move Shelf and Move Book, multiple shelves are required. Therefore, ensure
that multiple shelves exist before using them.
- A basic visual is displayed on-screen, with the shelves and books. For more detail,
the "Find Book" button can also display a section of text in a new window containing 
information about the given book.
- You can save the state of my application by clicking the "Save" button.
- You can reload the state of my application by clicking the "Load" button.

## Overview

This project will be based upon the needs of anyone with a book collection, 
helping to both organize and add to the current collection and figure 
out which book(s) to read next. Given the similarity of a book collection to an
anime or movie list, this software could also fit the need of those demographics.
Do note, the current version is a functional version, but more features will come.

The software's should have the following features after completion:
- Adding a book into the collection
- Adding a shelf into the collection
- Finding a certain book in the collection
- Displaying information about any book in the collection
- Displaying a basic overview of the entire collection
- Moving books between shelves in the collection
- Saving the state of the book collection to load later
- Loading any previously saved collection
- GUI for the application
- Updating the information about any book or shelf in the collection
- Tracking and updating progress on books in the collection

In essence, it will be an almost comprehensive solution to managing a collection
digitally for any dedicated bookworm. And, they can potentially manage themselves 
too using the pacing and progress tracking tools to be implemented later.

Personally, I do enjoy books but often have issues involving pacing and deciding
exactly what to buy or read next. Therefore, this project is of personal interest,
for it can help fix many of the difficulties of managing my collection right now.


## User Journal:

Initial version:
- I want to see list of all my current books
- I want to be able to find a book in the collection
- I want to see all the information about any book in the collection
- I want to be able to add any amount of books into the collection
- I want to be able to add any amount of shelves into the collection
- I want to be able to move any book between shelves in the collection
- I want to be able to move any shelf in the collection

Current and Future version(s):

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


## Reflections:

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