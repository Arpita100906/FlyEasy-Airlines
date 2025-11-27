# FlyEasy-Airlines

Finalized project package prepared for submission.

**Important:** replace the GitHub placeholder link below with your actual repository URL before final upload.

## What is included
- Maven project (pom.xml) with MySQL JDBC dependency
- `src/main/java/com/flyeasy/` - Java source (models, DAOs, BookingService, Main)
- `sql/flyeasy_schema.sql` - schema + sample data (MySQL)
- `presentation/FlyEasy_Presentation_FINAL.pptx` - presentation with screenshots
- `.gitignore`

## Quick setup (command-line)
1. Install Java JDK 11+ and MySQL server.
2. Import SQL schema:
   ```bash
   mysql -u root -p < sql/flyeasy_schema.sql
   ```
3. Edit DB credentials:
   - Open `src/main/java/com/flyeasy/DBConnection.java`
   - Replace `USER` and `PASS` with your MySQL username and password.
4. Build with Maven:
   ```bash
   mvn clean package
   ```
5. Run:
   ```bash
   java -cp target/FlyEasy-Airlines-1.0-SNAPSHOT.jar com.flyeasy.Main
   ```
   or use `mvn exec:java` (if configured).

## GitHub
- Suggested repo name: `FlyEasy-Airlines`
- Placeholder repo URL: https://github.com/<your-github-username>/FlyEasy-Airlines
  - Replace `<your-github-username>` with your GitHub username and add the link to the presentation slides.

## What I improved (finalized items)
- Javadoc and inline comments added
- Transactional booking flow implemented (BookingService)
- Better input validation and error messages
- Maven project for dependency management
- Presentation includes demo screenshots (terminal output) and final checklist

## Notes before submission
- Make sure the DB user has privileges to create/insert tables.
- If you prefer not to include the JDBC jar in the repo, rely on Maven dependency (recommended).
