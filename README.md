# Bank - A Banking Application

## Introduction

The **Bank** application simulates a basic banking system with multiple user accounts. Users can interact with the system by specifying their account number, name, and the type of transaction they wish to perform (deposit or withdrawal). The application ensures data consistency by allowing only valid transactions.

## Project Components

The project consists of the following components:

1. **Server**: The server component listens for client connections and handles banking transactions.

2. **Client**: The client component allows users to interact with the banking system by entering their account information and transaction details.

3. **Compte**: The `Compte` class represents a bank account and includes methods for depositing and withdrawing funds.

4. **Transaction**: The `Transaction` class encapsulates transaction details such as the user's name, account number, transaction type, and amount.

5. **ThreadDeposer**: A thread class responsible for depositing funds into an account.

6. **ThreadRetirer**: A thread class responsible for withdrawing funds from an account.

## Socket Communication

The **Bank** project utilizes socket communication for enabling client-server interactions. Sockets provide a reliable means of data exchange between the client and server components.

## Multithreading

Multithreading is employed to handle multiple client connections concurrently. This allows the server to serve multiple clients simultaneously without blocking operations, providing a responsive and efficient banking experience.

## Getting Started

To get started with the **Bank** application, follow these steps:

1. **Clone the Repository**:
   ```shell
   git clone https://github.com/yourusername/compte-banking.git
   cd compte-banking