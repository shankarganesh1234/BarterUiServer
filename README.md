# BarterUi Server Side Application

This application exposes REST services which perform a number of operations related to a barter platform.
<br>
The list of services are :-
<br>
1.  User login, authentication and authorization using facebook.
2.  List of available items
3.  Search available items based on most recently added, item location and distance, full and partial keywords.
3.  View single item.
4.  Add/Update single item.
5.  Delete single item.
6.  Upload images for single item.
7.  Make offers on items (Barter)
8.  Get offers for a user
9.  Get/Update/Delete offers made.
10. Member to member messaging between interested users.
11. Flag an item for abuse or inappropriate
12. Set an item as bartered.

The application connects to the following external sources :-
<br>
1. Facebook : User login, authentication and authorization.
2. Sendbird : Member to member communication.
3. Cloudinary : Item images storage and retrieval

The application is designed/developed using: <br>
1. Spring : Bean management, DI and transaction management
2. Spring Social : Interfacing with FB (and other providers in the future) for user login, authentication and authorization.
3. Hibernate : ORM
4. Postgres SQL : DB
5. Elasticsearch : Powers all searches and item list retrievals
6. Jersey : JAX-RS implementation for REST services
7. Websockets for notifications


