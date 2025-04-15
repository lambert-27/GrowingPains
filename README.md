
**Object-Oriented Software Development - Project**
*By Mark Lambert - C00192497*

---

## Description
**Growing  Pains** is a Java-based plant retail system designed to browse a diverse range of products, manage orders and create/edit account details. The system adheres to **MVC architecture** and implements core OOSD principles such as keeping class information encapsulated, inheritence, polymorphism and aggregational relationships. The system is supported by a secure backend databse ran through MySQL via CLI tools. Java's Swing library was utilised for implementation of a responsive GUI.

## Features

GrowingPains is a customer-focused system in its current iteration, so CRUD operations are limited to customer-side interactions with the system.
- Account creation, profile management 
- Secure login system with use of SHA-256 password hashing
- Browse a dynamic catalogue of products with filtering options by category
- Cart management (add/remove products, update values)
- Secure checkout system with payment handling
- Order history and cancellation implementation

## Technical Features
- **Inheritance** : Use of abstract class for the "Item" class- extended by "Plant" and "Accessory"
- **Compositional Aggregation** : A "Customer" object *owns* an "Account" object, meaning the Account's life-cycle is attached to that of the Customer
- **Basic Aggregation** : A "Customer" object *has* an "Address"
- **Polymorphism** : Subclasses of "Item" are treated appropriately according to type
- **Software Architecture** : Use of a **M**odel, **V**iew, **C**ontroller software architecture to separate logic between GUI and CRUD/logic based operations
- **Security** : Use of SHA-256 password hashing for security
- **Error Handling and Validation** : Input fields are validated appropriately with error writing implementation for further error insepction

## Technologies Used
- **Backend** : Java, MySQL
- **Frontend** : Java Swing
- **Tools** : Eclipse IDE, Git

---

## Additional Notes
- Use of technical documentation from previous [📁 Systems Analysis, Design and Testing](./Documentation/GrowingPains%20Design%20Doc) project output for the implementation of this system, can be found 
- Custom exception handling for validation and user interaction with the system
- [📁 Technical document](./Documentation/GrowingPains%20Implementation%20Doc) output from implementation, detaling interesting code snippets, requirements finding documentation etc
- ## How to Run 
 1. **Prerequisites**
    - MySQL with tables ready 
    - Java installed
2. **Steps**
    - Clone the repo -    ``` git clone https://github.com/lambert-27/GrowingPains.git ```
    - Import the [📁 Dump File](./Misc/GrowingDump.sql) to your SQL server 
    - Run the [📁 Jar File](./Misc/FinalJar.jar) 

## Future Implementation
- Reminder System: Ability for users to set water scheduling notifications
- Admin Panel: Manage inventory, customers and customer orders
- Comprehensive Order/Product relationship integration in the backend database (simplified relationships)

## Author

**Mark Lambert C00192497**
- Email: [marklambert123@gmail.com](mailto:marklambnert123@gmail.com)
- College Email: [c00192497@setu.ie](mailto:c00192497@setu.ie)
