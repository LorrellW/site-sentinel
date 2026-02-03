# 🛡️ SiteSentinel - Synthetic Monitoring Platform

A full-stack automated monitoring SaaS that tracks website uptime and visual integrity.
Built with **Java Spring Boot** for the backend architecture and **Playwright** for headless browser automation.

![Project Status](https://img.shields.io/badge/Status-Prototype-emerald)

---

## 🛠️ Tech Stack

**Backend** ![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/spring-%236DB33F.svg?style=for-the-badge&logo=spring&logoColor=white)
![Playwright](https://img.shields.io/badge/playwright-%232EAD33.svg?style=for-the-badge&logo=playwright&logoColor=white)
![H2 Database](https://img.shields.io/badge/H2_Database-003B57?style=for-the-badge&logo=h2&logoColor=white)

**Frontend** ![Next JS](https://img.shields.io/badge/Next-black?style=for-the-badge&logo=next.js&logoColor=white)
![TailwindCSS](https://img.shields.io/badge/tailwindcss-%2338B2AC.svg?style=for-the-badge&logo=tailwind-css&logoColor=white)
![TypeScript](https://img.shields.io/badge/typescript-%23007ACC.svg?style=for-the-badge&logo=typescript&logoColor=white)

---

## 🚀 Key Features

* **Headless Browser Engine:** Uses a Java-based Playwright instance to spin up a real Chromium browser for every check.
* **Visual Verification:** Captures and stores DOM screenshots to verify site rendering, not just HTTP status codes.
* **On-Demand Scheduling:** API-driven manual triggers allowing for immediate "health checks" from the dashboard.
* **Response Time Analytics:** Tracks latency in milliseconds for every request.

## 🧠 How It Works

1.  **The Trigger:** User clicks "Run Check" on the Next.js dashboard.
2.  **The Service:** Spring Boot receives the request and initializes a `Playwright` context.
3.  **The Browser:** A headless Chromium instance navigates to the target URL, waits for network idle, and captures a screenshot.
4.  **The Persistence:** Metadata (Status 200, Latency 400ms) and the screenshot filename are stored in the database.
5.  **The Delivery:** The dashboard polls the API and updates the UI instantly with the new screenshot.

## 📦 Getting Started

Follow these instructions to run the full stack locally.

### 1. Start the Backend (Java)
Open a terminal in the root directory:
```bash
cd backend
./mvnw spring-boot:run
```
* The Backend API will start on http://localhost:8080

### 2. Start the Frontend (Next.js)
Open a new terminal window:
```bash
cd frontend
npm install
npm run dev
```
- The Dashboard will open on http://localhost:3000
