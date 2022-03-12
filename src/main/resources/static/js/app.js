var app = (function(){

    // Input of the author name
    var newAuthorName;

    // Get the apiclient or the apimock module
    var module = apiclient;

    // Get elements
    var table = document.getElementById("tableBluePrints");
    var button = document.getElementById("authorNameButton");
    var currentBluePrint = document.getElementById("currentBluePrintName");
    var authorNameH = document.getElementById("authorName");
    var canvas = document.getElementById("canvas");

    // Click button listener
    button.addEventListener("click", function(){
        newAuthorName = document.getElementById("inputName").value;
        // Clear the table
        clearData();
        // Update de author information
        updateName(newAuthorName);
        module.getBluePrintsByAuthor(newAuthorName);
    });

    // Updates the author name
    function updateName(newNameValue){
        if (newNameValue == "") {
            newNameValue = "All Blue prints";
        }
        authorNameH.innerHTML = newNameValue;
    }

    // Reset the author information
    function clearData(){
        var size = table.rows.length;
        for(var x=size-1; x > 0; x--){
            table.deleteRow(x);
        }
    }

    // Draw the current blueprint in the canvas
    function drawBlueprint(bluePrintName){
        currentBluePrint.innerHTML = "Current Blueprint: " + bluePrintName;
        module.getBlueprintsByNameAndAuthor(newAuthorName, bluePrintName);
    }

    return{
        draw:drawBlueprint
    }

})();
