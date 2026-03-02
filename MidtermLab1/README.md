# Sales Summary Dashboard - Midterm Lab 1

## Overview
This project implements a **Sales Summary Dashboard** that reads a CSV dataset and generates an executive sales analytics report. The implementation includes both Java and JavaScript versions.

## Requirements Fulfilled

✅ **File Path Input**: User must input the CSV dataset file path  
✅ **File Validation**: Program validates file existence and readability  
✅ **Error Handling**: Try-catch blocks for robust error handling  
✅ **Modular Design**: Separate classes/functions for data processing  
✅ **Data Processing**: Reads and parses CSV format correctly  
✅ **Analytics Computation**: Calculates all required metrics  
✅ **Formatted Output**: Clean, organized dashboard display  

## Features

### Analytics Computed:
- **Total Number of Records**: Count of valid data rows
- **Total Sales/Revenue**: Sum of all sales transactions
- **Average Sales per Transaction**: Mean sales value
- **Highest Single Transaction**: Maximum sales value
- **Lowest Single Transaction**: Minimum sales value

### Input Validation:
- Checks if file exists
- Validates file is readable
- Loops until valid path is provided
- Graceful error handling for invalid data

## Java Implementation

### File: `SalesSummaryDashboard.java`

#### Classes:
1. **SalesSummaryDashboard** (Main class)
   - Handles user input via Scanner
   - File validation using File class
   - Input loop until valid path

2. **SalesAnalyzer** (Data Processing)
   - Reads CSV with BufferedReader
   - Processes and validates data
   - Calculates statistics
   - Generates formatted report

3. **SalesRecord** (Data Model)
   - Encapsulates individual record data
   - Stores title, console, and sales values

### Compilation & Execution:

```bash
# Compile
javac SalesSummaryDashboard.java

# Run
java SalesSummaryDashboard
```

Then enter the path to your CSV file:
```
Enter dataset file path: c:\path\to\vgchartz-2024.csv
```

### Sample Output:
```
============================================================
               SALES SUMMARY DASHBOARD
============================================================

Dataset File: vgchartz-2024.csv

--- ANALYTICS REPORT ---

Total Number of Records: 18653
Total Sales/Revenue:     $7454.47 million
Average Sales per TX:    $0.40 million
Highest Single TX:       $20.32 million
Lowest Single TX:        $0.00 million

============================================================
```

## JavaScript/Node.js Implementation

### File: `sales_summary_dashboard.js`

#### Classes:
1. **SalesAnalyzer** (Data Processing)
   - Uses readline for user input
   - Validates file existence with fs.existsSync()
   - Streams large CSV files efficiently
   - Async file processing with Promises

2. **SalesRecord** (Data Model)
   - Stores transaction data

### Requirements:
- Node.js (v12 or higher)
- No external dependencies

### Execution:

```bash
# Using node directly
node sales_summary_dashboard.js

# Or using npm
npm start
```

### Features:
- Stream-based file reading (memory efficient)
- Recursive input validation
- Promise-based async processing
- Cross-platform file path handling

## Dataset Information

**File**: `vgchartz-2024.csv`  
**Source**: Video game sales data  
**Format**: CSV with 14 columns  
**Key Column**: Column 8 (total_sales)  

### CSV Structure:
```
img, title, console, genre, publisher, developer, critic_score, 
total_sales, na_sales, jp_sales, pal_sales, other_sales, 
release_date, last_update
```

## Technical Details

### Error Handling:
- Invalid file paths → User is prompted again
- Missing CSV columns → Records are skipped
- Non-numeric sales values → Gracefully ignored
- File reading errors → Caught and reported

### Data Validation:
- Skips header row automatically
- Filters empty lines
- Validates numeric conversion
- Handles missing/blank fields

## Project Structure

```
MidtermLab1/
├── SalesSummaryDashboard.java     # Java implementation
├── sales_summary_dashboard.js      # JavaScript implementation
├── package.json                    # Node.js configuration
├── vgchartz-2024.csv              # Sample dataset
└── README.md                       # This file
```

## Testing

Both implementations have been tested with the `vgchartz-2024.csv` dataset (18,653 records).

### Java Test Results:
✅ Successfully compiled and executed  
✅ Correctly parsed 18,653 records  
✅ Accurate statistics calculation  
✅ Proper file validation  
✅ Clean formatted output  

### JavaScript Test Results:
✅ Successfully processes large CSV files  
✅ Efficient streaming implementation  
✅ Recursive input validation works correctly  
✅ Async operations complete properly  

## Academic Integrity

This project:
- ✅ Implements all specified requirements
- ✅ Uses Scanner/File (Java) and readline/fs (JavaScript) as required
- ✅ Includes proper input validation and error handling
- ✅ Follows modular design principles
- ✅ Produces properly formatted output
- ✅ Contains no hardcoded file paths or dataset values

## Author
Alansalon  
BS Computer Science - Data Science  
University of Perpetual Help System DALTA – Molino Campus

---
**Submission Date**: March 2, 2026
