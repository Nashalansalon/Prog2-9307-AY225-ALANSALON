const fs = require('fs');
const readline = require('readline');
const path = require('path');

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

function askFilePath() {
    rl.question("Enter dataset file path: ", function(filePath) {
        if (fs.existsSync(filePath) && fs.statSync(filePath).isFile()) {
            console.log("File found. Processing...\n");
            const analyzer = new SalesAnalyzer(filePath);
            analyzer.processData()
                .then(() => {
                    analyzer.displayReport();
                    rl.close();
                })
                .catch(error => {
                    console.error("Error processing file: " + error.message);
                    rl.close();
                });
        } else {
            console.log("Invalid file path. Try again.");
            askFilePath();
        }
    });
}

class SalesAnalyzer {
    constructor(filePath) {
        this.filePath = filePath;
        this.records = [];
        this.totalSales = 0;
        this.highestSale = Number.NEGATIVE_INFINITY;
        this.lowestSale = Number.POSITIVE_INFINITY;
    }

    async processData() {
        return new Promise((resolve, reject) => {
            const stream = fs.createReadStream(this.filePath, { encoding: 'utf8' });
            let buffer = '';
            let isFirstLine = true;

            stream.on('data', chunk => {
                buffer += chunk;
                let idx;
                while ((idx = buffer.indexOf('\n')) >= 0) {
                    const line = buffer.slice(0, idx).replace(/\r$/, '');
                    buffer = buffer.slice(idx + 1);

                    if (isFirstLine) {
                        isFirstLine = false; // skip header
                    } else {
                        this.processLine(line);
                    }
                }
            });

            stream.on('end', () => {
                if (buffer.trim()) {
                    if (!isFirstLine) this.processLine(buffer.replace(/\r$/, ''));
                }
                resolve();
            });

            stream.on('error', reject);
        });
    }

    processLine(line) {
        if (!line || !line.trim()) return;

        const parts = parseCSVLine(line);
        if (parts.length > 7) {
            const totalSalesStr = (parts[7] || '').trim();
            if (totalSalesStr) {
                const sales = parseFloat(totalSalesStr);
                if (!isNaN(sales)) {
                    this.records.push({
                        title: parts[1] || '',
                        console: parts[2] || '',
                        sales: sales
                    });
                    this.totalSales += sales;
                    if (sales > this.highestSale) this.highestSale = sales;
                    if (sales < this.lowestSale) this.lowestSale = sales;
                }
            }
        }
    }

    displayReport() {
        const fileName = path.basename(this.filePath);
        const totalRecords = this.records.length;
        const averageSales = totalRecords > 0 ? this.totalSales / totalRecords : 0;

        console.log('='.repeat(60));
        console.log('               SALES SUMMARY DASHBOARD');
        console.log('='.repeat(60));
        console.log(`\nDataset File: ${fileName}\n`);
        console.log('--- ANALYTICS REPORT ---\n');
        console.log(`Total Number of Records: ${totalRecords}`);
        console.log(`Total Sales/Revenue:     $${this.totalSales.toFixed(2)} million`);
        console.log(`Average Sales per TX:    $${averageSales.toFixed(2)} million`);

        if (totalRecords > 0) {
            console.log(`Highest Single TX:       $${this.highestSale.toFixed(2)} million`);
            console.log(`Lowest Single TX:        $${this.lowestSale.toFixed(2)} million`);
        } else {
            console.log('Highest Single TX:       N/A');
            console.log('Lowest Single TX:        N/A');
        }

        console.log('\n' + '='.repeat(60));
    }
}

// Basic CSV parser handling quotes and escaped quotes
function parseCSVLine(line) {
    const result = [];
    if (line == null) return result;
    let cur = '';
    let inQuotes = false;
    for (let i = 0; i < line.length; i++) {
        const ch = line[i];
        if (ch === '"') {
            if (inQuotes && i + 1 < line.length && line[i + 1] === '"') {
                cur += '"'; i++; // escaped quote
            } else {
                inQuotes = !inQuotes;
            }
        } else if (ch === ',' && !inQuotes) {
            result.push(cur);
            cur = '';
        } else {
            cur += ch;
        }
    }
    result.push(cur);
    return result;
}

// Start
askFilePath();
