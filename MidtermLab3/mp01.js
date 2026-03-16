const fs = require("fs");
const readline = require("readline");

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout
});

rl.question("Enter dataset file path: ", function(path){

    fs.readFile(path, "utf8", (err, data) => {

        if(err){
            console.log("Error reading file.");
            rl.close();
            return;
        }

        const rows = data.trim().split("\n");

        const total = rows.length - 1;

        console.log("Total Records:", total);

        rl.close();
    });

});