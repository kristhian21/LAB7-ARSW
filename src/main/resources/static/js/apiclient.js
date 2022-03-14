var apiclient = (function() {

    return{
        getBluePrintsByAuthor: function (authorName){
            $.ajax({
                url: "http://localhost:8080/blueprints/"+authorName
            }).then(function(data) {
                var cont = 0;
                data.forEach(element => {
                    var table = document.getElementById("tableBluePrints");
                    let row =  table.insertRow();
                    let bluePrintName = row.insertCell();
                    let numberOfPoints = row.insertCell();
                    let button = row.insertCell();
                    bluePrintName.innerHTML = element.name;
                    numberOfPoints.innerHTML = element.points.length;
                    cont += element.points.length;
                    button.innerHTML = "<button class=\"btn btn-success\" onclick=\"app.draw('" + element.name + "')\">Open</Button>";
                });
                document.getElementById("totalPoints").innerHTML = "Total points: " + cont;
            });
        },


        getBlueprintsByNameAndAuthor: function (authorName, bpName){
            $.ajax({
                type:'GET',
                url: "http://localhost:8080/blueprints/"+authorName+"/"+bpName
            }).then(function(data) {
                window.Pintado = true;
                var canvas = document.getElementById("canvas");
                var context = canvas.getContext("2d");
                context.beginPath();
                context.clearRect(0, 0, canvas.width, canvas.height);
                for (let index = 0; index < data["points"].length - 1; index++) {
                    console.log(data["points"][index]);
                    context.moveTo(data["points"][index]["x"], data["points"][index]["y"]);
                    console.log(data["points"][index+1]);
                    context.lineTo(data["points"][index+1]["x"], data["points"][index+1]["y"]);
                    context.stroke();
                }

                window.point.map(function (func) {
                                console.log(func.x, func.y);
                    			context.lineTo(func.x, func.y);
                    			context.stroke();
                })

                context.closePath();

            });
        },

        putBluePrint : function(authorName, bluePrintName){
            $.ajax({
                type:'GET',
                url: "http://localhost:8080/blueprints/"+authorName+"/"+bluePrintName
            }).then(function(data) {
                // Agregar los puntos nuevos
                for (let index = 0; index < window.point.length; index++) {
                    data["points"].push(window.point[index]);
                }
                // PUT
                $.ajax({
                    type:'PUT',
                    url: "http://localhost:8080/blueprints/"+authorName+"/"+bluePrintName,
                    data: JSON.stringify(data),
                    contentType: "application/json"
                }).then(function(){
                    window.point = [];
                    app.reload();
                })        
            });
        },

        postBluePrint : function(author, newBluePrintName){
            $.ajax({
                type:'POST',
                url: "http://localhost:8080/blueprints/add/",
                data: JSON.stringify({"author":author,"points":[],"name":newBluePrintName}),
                contentType: "application/json"
            }).then(function() {
                app.reload();
                app.draw(newBluePrintName);
            });
        }


    }
})();


    
