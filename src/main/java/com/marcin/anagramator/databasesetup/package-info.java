/**
 * Package grouping classes used for reading words from a source file (kind of a dictionary),
 * alphabetizing every single word,
 * mapping all anagrams to an appropriate alphabetized word
 * and dumping the results to the database.
 * 
 * Generally speaking, all classes from this package are used only once - while creating the database.
 * They are not used in the custom application use.
 * 
 * EDIT: this package uses some resolutions that was changed while developing the main app, 
 * for example it uses the SessionFactory interface or the xml config. 
 * But the main app does not need these package classes to function, 
 * so no changes were made here when the main app was respectively refactored. 
 * 
 * @author dream-tree
 * @version 4.00, June-September 2018
 */
package com.marcin.anagramator.databasesetup;