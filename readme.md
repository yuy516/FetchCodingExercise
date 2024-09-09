# Fetch Rewards Coding Exercise - Software Engineering - Mobile

### Task

<p>Please write a native Android app in Kotlin or Java that retrieves the data from https://fetch-hiring.s3.amazonaws.com/hiring.json.

Display this list of items to the user based on the following requirements:

- Display all the items grouped by "listId"
- Sort the results first by "listId" then by "name" when displaying.
- Filter out any items where "name" is blank or null.

The final result should be displayed to the user in an easy-to-read list.

Please make the project buildable on the latest (non-pre release) tools and supporting the current release mobile OS.</p>

### Environment

<p>Android Studio (flamingo)</p>

### Programming Language

<p>Java</p>

### Java files

<p><strong>RetrofitClient.java</strong>:  create a `RetrofitClient` class to configure the Retrofit instance, which will be used to send network requests</p>

<p><strong>Api.java</strong>: declare the endpoints that Retrofit will interact with.</p>

<p><strong>Item.java</strong>: Create a class to represent the structure of each item in the JSON response </p>

<p><strong>MainActivity.java</strong>: use the Retrofit client to fetch data from the API, sort the data and display the data. </p>