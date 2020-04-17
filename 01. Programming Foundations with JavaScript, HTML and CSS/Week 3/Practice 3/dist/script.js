function doRed() {
  var canvas = document.getElementById("can");
  canvas.style.backgroundColor = "red";
}

function doColor() {
  var canvas = document.getElementById("can");
  var colorinput = document.getElementById("clr");
  canvas.style.backgroundColor = colorinput.value;
}