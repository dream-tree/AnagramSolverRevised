# AnagramSolverExtended

EDIT:<br>
AnagramSolverExtended project based on the AnagramSolver project.<br>
Project was migrated to SpringBoot.
Planned revisions: remodelling entities (done), allowing addition new entries to the database (partly done), allowing removal/update user entries (TODO).<br>
This is an early stage of this project.<br>

---

Web based app serving as an english anagrams solver. It takes text input and finds out all possible word combinations (anagrams) using english dictionary.
User can query the database by typing a "real" word or any other sequence of letters.
Query must contain minimum 3 letters and must not contain any digit, space or any other special character (e.g. '?', '*' or '/'). Input is validated by Hibernate Validator. Search is case-insensitive.

Internally, user input is <i>alphabetized</i> what means that all chars of a given word/sequence of letters are being sorted lexicographically. New string created in that way is called an alphabetized word. Application looks for the corresponding anagrams matching the alphabetized word saved in the database. Actually, it is based on key-value pairs (an alphabetized word and mapped anagrams) defined and saved in the database before building the features of the actual web application. Querying the base means looking for a single alphabetized word and its mapped counterpart list of "real" words. If there is no matching result, user gets "Nothing was found" info. 

List of english words used in the database comes from https://numeracy.co/artnez/word-lists/sources/enable page.
Enable is The Enhanced North American Benchmark LExicon (ENABLE) and it is word list and reference available for Scrabble players. 

----

Used technologies:
Spring Boot, Spring MVC, Hibernate ORM, Hibernate Validator, HTML, JSP, JSTL.<br>
Creating this app I didn't mind to pay much attention to the presentation layer (no graphical bells and whistles here). The main purpose was to build a web app doing its job: to enable querying the database and displaying the results on the page. 

---

Project #005.