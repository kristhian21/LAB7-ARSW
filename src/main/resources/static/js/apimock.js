//@author hcadavid

apimock=(function(){

	var mockdata=[];

	mockdata["johnconnor"]=	[{author:"johnconnor","points":[{"x":150,"y":120},{"x":215,"y":115}],"name":"house"},
	 {author:"johnconnor","points":[{"x":340,"y":240},{"x":15,"y":215}],"name":"gear"}, 
	 {author:"johnconnor","points":[{"x":166,"y":50},{"x":20,"y":20}],"name":"car"},
	 {author:"johnconnor","points":[{"x":10,"y":10},{"x":25,"y":25}],"name":"tower"},
	 {author:"johnconnor","points":[{"x":30,"y":30},{"x":65,"y":65}],"name":"motorcycle"}];
	mockdata["maryweyland"]=[{author:"maryweyland","points":[{"x":140,"y":140},{"x":115,"y":115}],"name":"house2"},
	 {author:"maryweyland","points":[{"x":140,"y":140},{"x":115,"y":115}],"name":"gear2"},
	 {author:"maryweyland","points":[{"x":12,"y":40},{"x":43,"y":21}],"name":"car2"},
	 {author:"maryweyland","points":[{"x":77,"y":70},{"x":57,"y":12}],"name":"tower2"},
	 {author:"maryweyland","points":[{"x":88,"y":90},{"x":85,"y":90}],"name":"motorcycle2"}];


	return {
		getBlueprintsByAuthor:function(authname,callback){
			callback(
				mockdata[authname]
			);
		},

		getBlueprintsByNameAndAuthor:function(authname,bpname,callback){

			callback(
				mockdata[authname].find(function(e){return e.name===bpname})
			);
		}
	}

})();

/*
Example of use:
var fun=function(list){
	console.info(list);
}
apimock.getBlueprintsByAuthor("johnconnor",fun);
apimock.getBlueprintsByNameAndAuthor("johnconnor","house",fun);*/