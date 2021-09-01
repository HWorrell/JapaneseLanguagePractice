# JapaneseLanguagePractice

Program Readme:

This program allows for Japanses language practice with associated files.  This file is divided into three sections:  Simple instructions, Technical Details, and File Structure.  Please read whichever section helps you the most.

Simple instructions:

The program loads the text files included in the "Active" folder within the "Questions" folder.  Removing a file from this folder will remove the questions from the question pool.  The program pulls font size information from the Config.ini file located in the same directory as the program.  This allows you to set the font size to a larger or smaller size for comfort.  To change it, just edit the file and change the number within the "[]".

Technical Details:

The program reads from tab-separated text in the UTF-16 data format.  The japanese text should appear first, followed by the english text.

File Structure:

This program depends on the following structure and file placement:

root
  Questions
    Active
      #files go here to be used
    Inactive
      #files go here when not in use
  Config.ini
