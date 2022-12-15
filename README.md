Name: Sindrilaru Catalina Maria

Group: 322CA 2022

# OOP-TV - Project first stage

***

## Presentation of the platform

The goal of this project is to create a simple backend for
an application like Netflix, HBO. So, this first stage allow us 
to have a database with users and their credentials and movies available
in the platform. A user can make 2 types of actions, to change the page
he is on and also to do some actions like login, register, filter the
movies, logout etc. It is important to notify that some actions can be made
just from some types of pages. This blocks the user's access to
some resources

## Organization and Implementation

Firstly, I created classes for all the objects from the json format,
like `Input`, `UserInput`, `ActionInput`, `MovieInput` and so on. 

In these classes I named the principal fields like the names in the json files,
to ease the process of parsing the data from the files in the main class.

After this, I created a class `StartProgram` that contains the principal
method that starts all the actions. For this class I used `Singleton Design Pattern`,
taking in consideration that I need just an instance of this class in the
program. The method created in this class verifies what type of action the user did
and call a method (resolveCommand) that resolve each command. There I used `Factory Design Pattern`
for hide the type of the object created for this two different types (change page 
and on page).

For the `Change page action`, firstly I verified if it is possible to move
to the page I want from the page I am on. (I chose to use the `Factory Design Pattern`
to hide the implementation and classes behind these errors, considering that I chose
3 main cases of errors - for Authenticated, Unauthenticated and movies).
Then, I treated each case of moving to another page and made the necessary changes,
checking other cases of error.

For the `On page action`, I verified what type of action the user want to do
and I used `Factory Design Pattern` for create instance for the appropriate
class (classes for the events are named exactly as the action itself). After
creating the object (all the events classes implements the Event interface),
I called the makeEvent method that is common for each type of event.

Events:

* For `login` event, I verified if the user exists in the database and if yes,
I named it the current user.
* For the `register` event, I added the new credentials at the list of users
and I named it the current user.
* For the `Buy` events, I change the balance and the tokens for each user and 
also the type of the account for the user.
* For the `Purchased, Watch, Like, Rate`, I decreased the tokens in some cases and
added the specific movie at the users list of movies, after case.
* For the `Filter` event, I have deleted the movies that do not contain the 
actors and genres from the given lists (using `Factory Design Pattern`) and
then I sorted the movies list by duration and rating.

For the display part, I created classes and methods for forming ObjectNodes and 
ArrayNodes that contains a movie, a user or a list of movies. I called the method
from DisplayCommand when an action was succeed or from the ErrorDisplay when
an error was occurred. 


