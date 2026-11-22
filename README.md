# ShopEase – E-Commerce REST API

Spring Boot backend for an e-commerce application with JWT authentication and role-based access (USER / ADMIN).

## Tech Stack
- Java 17, Spring Boot
- Spring Data JPA, Hibernate
- Spring Security + JWT
- MySQL
- Maven
- Postman (API testing)

## Modules
- **Auth** – Register, Login (JWT token)
- **Product** – CRUD with validation
- **Cart** – Add, view, remove items (stock check)
- **Order** – Place order, order history
- **Payment** – UPI / CARD / COD
- **Admin** – Manage orders, view users, dashboard stats

## Key Features
- JWT authentication with BCrypt password encryption
- Role-based access (`/api/admin/**` is ADMIN only)
- Global exception handling with JSON error responses
- Request validation with proper 400 responses

## API Endpoints
| Module | Endpoint |
|---|---|
| Auth | POST /api/auth/register, POST /api/auth/login |
| Products | /api/products |
| Cart | /api/cart/add, /api/cart/{userId} |
| Orders | /api/orders/place/{userId}, /api/orders/user/{userId} |
| Payments | /api/payments/pay, /api/payments/order/{orderId} |
| Admin | /api/admin/orders, /api/admin/users, /api/admin/dashboard |

## How to Run
1. Clone the repo
2. Create a MySQL database named `ecommerce_db`
3. Copy `application.properties.example` to `src/main/resources/application.properties`
4. Add your own DB username and password
5. Run `AppApplication.java` (or `mvnw spring-boot:run`)
6. Test the APIs in Postman

## Author
Archana S – [GitHub](https://github.com/CodeByArchanaa)
