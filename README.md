# Problem
Groceries are getting more expensive and people want to save money. 
One way to save money is to buy groceries at the cheapest store. 
However, this is not always possible because some stores are too far away or do not have the products you want. 
Another way to save money is to buy products that are on sale. 
However, this is not always possible because you do not know which products are on sale. 
This application will help you save money by finding the cheapest way to buy your groceries.

The goal:
- Search items
- Place items that can substitute eachother in a product box
- Place product boxes in a shopping cart
- Find the cheapest way to buy the items in the shopping cart with some filters (e.g. only online, only in store, combination)


# Backlog
## Definitions
- **Product**: A product is a single item that can be bought in a store. 
- **Category**: A category is a group of products that are similar.
- **Store**: A store is a place where products can be bought.
- **Shopping Cart**: A shopping cart is a list of product boxes that need to be bought.
- **product box**: A product box is a list of products that can replace eachother, for example the same product but from different stores.
- **BaseUnit**: A base unit is the unit of the quantity of a product. e.g. GRAM, LITER, PIECE
- **Quantity**: A quantity is the amount of a product in a base unit. e.g. 1.5 LITER, 500 GRAM, 2 PIECE
- **Price**: A price is the amount of money that needs to be paid for a quantity of a product.

## User Stories
- As a user I want to be able to search products by name and description
- As a user I want to be able to search products by category
- As a user I want to be able to sort the search results by price and name
- As a user I want to be able to choose which stores I want to shop at
- As a user I want to be able to add products to product boxes
- As a user I want to be able to name product boxes
- As a user I want to be able to add product boxes to a shopping cart
- As a user I want to be able to name shopping carts
- As a user I want to be able to see the total price of a shopping cart
- As a user I want to be able to save a shopping cart
- As a user I want to be able to load a shopping cart
- As a user I want to be able to copy a shopping cart
- As a user I want to be able to delete a shopping cart
- As a user I want to be able to see the cheapest configuration of a shopping cart. (i.e. what items to buy from each product box)
- As a user I want to be able to choose the ways of buying (e.g. only online, only in store, combination)
- As a user I want to be able to log in using email and password

## Technical Requirements
### Frontend
- Written in HTML, CSS and JavaScript
- Works in combination with the backend (i.e. Thymeleaf)

### Backend
- The backend should be written in Java
- The backend should use Spring Boot
- The backend should use Spring Security
  - For login/logout functionality
  - This way a user can only see their own shopping carts and personalize them
  - 
- The backend should use Spring Data JPA
- The backend should use an PostgreSQL database as the amount of data will be large (so in-memory like H2 is not a good option)
- The backend should use a REST API
- The backend should use Thymeleaf to render the frontend

### Classes
#### Product
- id?
- name
- sku
- description
- category
- nutrition
- promotion
- store
- base unit
- quantity
- price
- image url
- product url

### Persistence
- The database should have a table for products
- The database should have a table for product boxes
- The database should have a table for shopping carts
- The database should have a table for users
- The database should have a table for categories
- The database should have a table for promotions
- The database should have a table for promotiontypes
- The database should have a table for base units
- The database should have a table for nutrition
- The database should have a table for stores
- The database should have indexes on the id columns of the tables
- The database should have indexes on the name columns of the tables 

## Data Model
TODO: Add data model
### Product
- Has a 1 to many relationship with a category
- Has a 1 to 1 relationship with a nutrition
- Has a 1 to 1 relationship with a promotion
- Has a many to 1 relationship with a store
- Has a many to 1 relationship with a cateqory
- Has a many to one relationship with a product box
- Has a many to one relationship with a base unit

- ID needs to be generated with store and store product id

### Promotion
- Has a many to 1 relationship with a promotion type

- ID needs to be created for database, but name is also unique

### Shopping Cart
- Has a many to 1 relationship with a user
- Has a 1 to many relationship with a product box


## REST API
BASE URL: /api

### Web pages
Base URL: /

- Login page
- Register page
- Home page
- Search page
- Product page
- Shopping cart page
- Account page

| Method | Endpoint           | Description        |
|--------|--------------------|:-------------------|
| GET    | /                  | Home page          |
| GET    | /login             | Login page         |
| GET    | /register          | Register page      |
| GET    | /search            | Search page        |
| GET    | /product/{id}      | Product page       |
| GET    | /shoppingcart/{id} | Shopping cart page |
| GET    | /account           | Account page       |

### Product
Base URL: /api/products

- Get all products
- Get a product by id
- Search for products with a search term
- Filter for products with a specific category
- Filter for products with a specific store
- Filter to see only products that have a promotion, with a specific promotion type

| Method | Endpoint             | Description                 |
|--------|----------------------|:----------------------------|
| GET    | /api/products        | Get all products            |
| GET    | /api/products/{id}   | Get a product by id         |
| GET    | /api/products/search | Search for products by name |

## Authorization - Authentication - Security
Spring security can do:
- User name and password authentication
- SSO (Single Sign On) / OKTA / LDAP
- App level Authorization
- Intra App Authorization like OAuth
- JWT (JSON Web Token) / OAuth2, but this is not used in this project. We do not need to limit the access to the API.

### How to add spring security
https://www.youtube.com/playlist?list=PLqq-6Pq4lTTYTEooakHchTGglSvkZAjnE

## Scraping
Use insomnia API tool to generate code for scraping. Then use the code in the application. 
Insomnia will find the correct headers for the requests, and will identify any variables.

Using mitmproxy you can intercept requests to a private API.
1. Download mitmproxy on PC
2. Start mitmproxy
3. Set proxy on phone to PC IPv4:8080 address in wifi settings
4. Open mitm.it in browser
5. Install certificate on phone
6. Open app on phone

Some apps do proxy checking.
https://github.com/shroudedcode/apk-mitm This link has a program which removes that.
Friga or other patchers could maybe also be used to change the apk.

### Hoogvliet
The Promotion objects also contain ExpireDate and StartDate. These are not used in the application, Possible use in the future?

The startdate should be checked if the promotion is still valid. If the startdate is in the future, the promotion is not valid yet.

Some ideas:
- Keep track of promotion products, and their expiration. Check only expired products. Also check promotions start dates if the promotion actually applies.
- Hoogvliet has a promotion page. After importing all products, do daily checks on that page. If a product is not on the page anymore, remove the promotion from the product. If a new product is on the page, add the promotion to the product.
- Do daily checks on the normal way of finding SKUs, to check for any new products, or removed products.
- 





