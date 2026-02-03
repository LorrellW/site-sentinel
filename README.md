\# 🛡️ SiteSentinel - Synthetic Monitoring Platform



A full-stack automated monitoring SaaS that tracks website uptime and visual integrity. 

Built with \*\*Java Spring Boot\*\* for the backend architecture and \*\*Playwright\*\* for headless browser automation.



!\[Project Status](https://img.shields.io/badge/Status-Prototype-emerald)



\## 🚀 Key Features



\* \*\*Headless Browser Engine:\*\* Uses a Java-based Playwright instance to spin up a real Chromium browser for every check.

\* \*\*Visual Verification:\*\* Captures and stores DOM screenshots to verify site rendering, not just HTTP status codes.

\* \*\*On-Demand Scheduling:\*\* API-driven manual triggers allowing for immediate "health checks" from the dashboard.

\* \*\*Response Time Analytics:\*\* Tracks latency in milliseconds for every request.



\## 🛠️ Tech Stack



\### Backend (The Core)

\* \*\*Language:\*\* Java 21+

\* \*\*Framework:\*\* Spring Boot 3.4

\* \*\*Automation:\*\* Playwright for Java (Chromium Engine)

\* \*\*Database:\*\* H2 In-Memory (Dev) / PostgreSQL (Prod)

\* \*\*API:\*\* RESTful endpoints



\### Frontend (The Dashboard)

\* \*\*Framework:\*\* Next.js 14 (App Router)

\* \*\*Styling:\*\* Tailwind CSS + Lucide Icons

\* \*\*State:\*\* SWR for real-time polling



\## 🧠 How It Works



1\.  \*\*The Trigger:\*\* User clicks "Run Check" on the Next.js dashboard.

2\.  \*\*The Service:\*\* Spring Boot receives the request and initializes a `Playwright` context.

3\.  \*\*The Browser:\*\* A headless Chromium instance navigates to the target URL, waits for network idle, and captures a screenshot.

4\.  \*\*The Persistence:\*\* Metadata (Status 200, Latency 400ms) and the screenshot filename are stored in the database.

5\.  \*\*The Delivery:\*\* The dashboard polls the API and updates the UI instantly with the new screenshot.



\## 📦 Getting Started



\### 1. Start the Backend

```bash

cd backend

./mvnw spring-boot:run

