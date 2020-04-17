function doRed() {
  var canvas = document.getElementById("can1");
  canvas.style.backgroundColor="red";
   var context = canvas.getContext("2d");
  
    context.fillStyle = "yellow";
    context.fillRect(10,10,30,30);
    context.fillRect(10,50,30,30);
    context.fillRect(50,10,30,30);
  
    context.fillStyle = "black";
    context.font="12px Arial";
    context.fillText ("Hello", 10, 25);
 }


function doOrange() {
  var canvas = document.getElementById("can2");
  canvas.style.backgroundColor = "blue";
  var context = canvas.getContext("2d");
  context.clearRect(0,0,canvas.width,canvas.height);
  canvas.style.backgroundColor = "orange";
}