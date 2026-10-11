# katalon-petstore-api
katalon-petstore-api


# API Automation Testing — Katalon Studio

## Project Overview
REST API automation testing project using Katalon Studio and the PetStore API. The project validates CRUD operations, HTTP method handling, positive and negative scenarios, and an end-to-end API workflow.

**API Under Test:** https://petstore.swagger.io/

## How to Run
1. Clone this repository.
2. Open the project in Katalon Studio.
3. Verify the API endpoint and execution profile.
4. Open `Test Suites/TS_API_Final_Project`.
5. Run the test suite and review the execution results.

## Test Suite
- **Test Suite:** `TS_API_Final_Project`
- **Execution:** Katalon Studio
- **Report:** Refer to the API execution report included in this repository.

## Test Coverage

| Test Case | Coverage |
|---|---|
| TC_API_001 | Create Pet — Positive |
| TC_API_002 | Invalid HTTP Method |
| TC_API_003 | Get Pet — Positive |
| TC_API_004 | Get Pet — Negative |
| TC_API_005 | Update Pet — Positive |
| TC_API_006 | Update Pet — Negative |
| TC_API_007 | Delete Pet — Positive |
| TC_API_008 | Delete Pet — Negative |
| TC_API_009 | End-to-End API Scenario |

**Total:** 9 test cases covering CRUD operations, negative scenarios, and end-to-end testing.