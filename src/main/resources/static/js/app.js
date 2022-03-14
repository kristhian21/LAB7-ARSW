var Pintado = false;
var point = [];
var bpname = "";

var app = (function(){

    // Input of the author name
    var newAuthorName;
    var bandera = false;

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

    function initMouse () {
        		console.info('initialized');
        		var canvas = document.getElementById("canvas"),
        			context = canvas.getContext("2d");

                var draw = Draw();

        		if (window.PointerEvent) {
        			canvas.addEventListener("pointerdown", Draw, false);
        		}
    }

    function Draw () {
        if(Pintado){
                console.info("Entrar a pintado")
        		var canvas = document.getElementById("canvas"),
        			context = canvas.getContext("2d");
        		var offsetleft = parseInt(getOffset(canvas).left, 10);
        		var offsettop = parseInt(getOffset(canvas).top, 10);
        		var x = event.pageX - offsetleft;
        		var y = event.pageY - offsettop;
        		var cordenadas = { "x": x, "y": y };
        		point.push(cordenadas)
        		module.getBlueprintsByNameAndAuthor(newAuthorName, bpname);
        	}
    }

    function getOffset (obj) {
    		var offsetLeft = 0;
    		var offsetTop = 0;
    		do {
    			if (!isNaN(obj.offsetLeft)) {
    				offsetLeft += obj.offsetLeft;
    			}
    			if (!isNaN(obj.offsetTop)) {
    				offsetTop += obj.offsetTop;
    			}
    		} while (obj = obj.offsetParent);
    		return { left: offsetLeft, top: offsetTop };
    }

    // Draw the current blueprint in the canvas
    function drawBlueprint(bluePrintName){
        currentBluePrint.innerHTML = "Current Blueprint: " + bluePrintName;
        bpname = bluePrintName;
        point = [];
        var context = canvas.getContext('2d');
        context.clearRect(0, 0, canvas.width, canvas.height);
        module.getBlueprintsByNameAndAuthor(newAuthorName, bluePrintName);
    }


    function updateBluePrints(){
        module.putBluePrint(newAuthorName, bpname);  
    }

    function refresh(){
        clearData();
        updateName(newAuthorName);
        module.getBluePrintsByAuthor(newAuthorName); 
    }

    function createNewBp(){
        result = window.prompt("Ingrese el nombre del nuevo plano", "Default name");
        bpname = result;
        module.postBluePrint(newAuthorName, result);
    }

    return{
        draw:drawBlueprint,
        initMouse: initMouse,
        update: updateBluePrints,
        reload: refresh,
        createBp: createNewBp
    };

})();
