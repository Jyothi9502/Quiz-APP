
# Quiz App

### Overview:
This is a simple quiz application built using Spring Boot. It uses an in-memory H2 database and provides REST APIs to manage quiz sessions, fetch questions, submit answers, and retrieve summaries.

### Assumptions:
1. **Single User Scenario**: The application is built for a single user; no authentication or user management has been implemented.
2. **Session Management**: Each quiz session is associated with a `sessionId` that must be passed in API calls.
3. **Question Pool**: Questions are pre-seeded into the database using a `data.sql` file.
4. **Answer Submission**: Submissions must include a `sessionId`, `questionId`, and the selected option (e.g., A, B, C, D).
5. **No Duplicate Questions**: The same question might appear multiple times since randomness is used.
6. **Correct Option**: The options for questions are labeled as `A`, `B`, `C`, and `D`, and the correct answer is stored as one of these labels.
7. **Database Reset**: Since H2 is in-memory, the data resets whenever the application restarts.

### Steps to Run:
1. Clone the repository.
2. Ensure you have Maven and Java 17+ installed.
3. Build and run the application:
   ```bash
   mvn spring-boot:run
   ```
4. Access the H2 Database Console (Optional):
   - URL: `http://localhost:8080/h2-console`
   - JDBC URL: `jdbc:h2:mem:quizdb`
   - Username: `sa`
   - Password: (leave blank)

### REST API Endpoints:
1. **Start New Quiz Session**
   - `POST /api/quiz/start?userId={userId}`
   - Starts a new quiz session.

2. **Get a Random Question**
   - `GET /api/quiz/question`
   - Fetches a random question.

3. **Submit an Answer**
   - `POST /api/quiz/submit`
   - Request Body:
     ```json
     {
       "sessionId": 1,
       "questionId": 1,
       "selectedOption": "A"
     }
     ```

4. **Get Quiz Summary**
   - `GET /api/quiz/summary?sessionId={sessionId}`
   - Retrieves total questions answered and the count of correct answers.

### Dependencies:
- Spring Boot Starter Web
- Spring Boot Starter Data JPA
- H2 Database

### Notes:
- Use tools like Postman or cURL to test the APIs.
- Example Question Table:
   ```
   | ID | Question Text                  | Option A | Option B | Option C | Option D | Correct Answer |
   |----|--------------------------------|----------|----------|----------|----------|----------------|
   | 1  | What is the capital of France? | Paris    | London   | Berlin   | Madrid   | A              |
   ```

---

Happy Quizzing! 🎉
