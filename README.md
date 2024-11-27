# My Personal Project

## Introduction

I am developing an application that will allow users to track their daily water and beverage intake. The app will compare the amount of water consumed with other drinks, offering **positive feedback** when users meet the recommended daily water intake. However, if the user consumes too many beverages instead of water, the app will provide a **warning**, which encourage poeple to get healthier hydration habits. This project is aimed at people who want to monitor and improve their hydration, particularly those who tend to rely on sugary or flavored drinks. I’ve personally experienced the negative effects of consuming too many drinks, which led to physical discomfort. This has inspired me to build this app to promote healthier hydration and provide users with valuable insights into their drinking habits, helping them make better choices to stay healthy.


## User Stories:

- As a user, I want to be able to add a new drinking record to my drinking records
- As a user, I want to be able to view the list of drinking record in my drinking records
- As a user, I want to be able to select water/non-water drinks and view the drinking amount in detail
- As a user, I want to be able to remove a drinking record from my drinking records
- As a user, I want to be able to get *positive/negative* feedback for my drinking records
- As a user, I want to be able to save my drink records to file (if I so choose)
- As a user, I want to be able to be able to load my drink records from file (if I so choose)

## Phase 4: Task 2
- user add a new drink record to the application
- user remove a drink record in the application
- user get the feedback for drink records in the application

## Phase 4: Task 3
- First, I think both DrinkRecordSummary and GUI class both contain the responsible for more than one thing. To improve Cohension, I can separate the methods in each class into different small classes. For example, for GUI class, I can extract a class calld GUIComponents to handles the layout and initialization of GUI components and a class called DrinkRecordManager to manages drink records, including adding, removing, saving, and loading.

- Secondly, I think there are some duplication in the DrinkRecordSummary and GUI class. I can make a new abstract class to implement these similar methods, and let DrinkRecordSummary and GUI class to extends this new class and delete original methods in each class. This helps the code to reduce duplication.