This project is a document translation application that allows users to upload a document, choose a language for translation, and download the translated document. The application uses the Google Translate API for translation services.

1. User Authentication: 
Login page with user ID and password verification.
User credentials are matched from the database.

2. Document Management:
After login, users are redirected to a page with a table listing uploaded documents.
The table contains columns for:
Sr no
File name
Upload date
Download link for the translated document.

3. File Upload and Translation:

An upload button on the document list page allows users to upload new documents.
Upon clicking the upload button, users are taken to a page where they can browse for a document and select a language for translation.
Supported languages include:
Hindi (hi-IN)
Bengali (Bangla) (bn).

4. Translation and Download:

Uploaded documents are sent to the Google Translate API for translation.
Translated documents are saved locally and made available for download.

Backend:
Spring Boot 3
MySQL
Hibernate

Frontend:
HTML/JSP pages


Setup Instructions
Prerequisites
JDK 11 or higher
Maven
MySQL

**Still I'am working on downloading and uploading document.
