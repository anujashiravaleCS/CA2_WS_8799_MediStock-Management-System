# MediStock Management System

## Project Description

MediStock Management System is a RESTful Web Service-based application developed to manage medicine and stock information efficiently.

The system allows users to add, view, update, partially update, and delete medicine records through a web-based interface.

## Features

- Add new medicine records
- View all medicine records
- Update complete medicine details
- Update medicine quantity and stock status
- Delete medicine records
- Manage medicine expiry dates and stock status

## Technologies Used

- Java
- RESTful Web Services
- HTML
- CSS
- JavaScript
- JDBC
- Apache Derby Database
- JSON

## HTTP Methods Used

| Method | Purpose |
|--------|---------|
| GET | Retrieve and display medicine records |
| POST | Add a new medicine |
| PUT | Update complete medicine details |
| PATCH | Update specific medicine information such as quantity and status |
| DELETE | Delete a medicine record |

## Medicine Details

The system manages the following information:

- Medicine ID
- Medicine Name
- Quantity
- Expiry Date
- Status

## How the System Works

The user enters medicine information through the web interface. JavaScript sends requests to the backend using RESTful HTTP methods. The Java backend processes the request and performs the required database operation using JDBC. The result is then returned to the frontend in JSON format.

## CRUD Operations

- Create - Add a new medicine using POST
- Read - Retrieve medicine records using GET
- Update - Update complete medicine details using PUT
- Partial Update - Update quantity and status using PATCH
- Delete - Remove a medicine using DELETE

## Developer

**Anuja Shiravale**  
Roll No: 8799  
B.Sc. Computer Science
TYCS - B 
## Project Guide

**Miss Princi Yadav**
