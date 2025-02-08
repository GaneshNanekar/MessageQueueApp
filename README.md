Message Queue Application
A simple multithreaded Producer-Consumer system in Java using a synchronized message queue.

Features
✅ Thread-safe message queue
✅ Graceful consumer shutdown
✅ JUnit test cases

**Build & Run**
mvn clean package
mvn exec:java -Dexec.mainClass="com.example.messagequeue.Application"

Enter the number of messages to produce when prompted.

**Run Tests**
mvn test

Future Enhancements
🔹 Support multiple consumers
🔹 Replace synchronized with BlockingQueue
🔹 Add logging
