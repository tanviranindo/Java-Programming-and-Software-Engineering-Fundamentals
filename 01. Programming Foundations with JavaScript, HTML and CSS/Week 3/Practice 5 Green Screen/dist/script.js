var fgImage = null;
var bgImage = null;
var fgCanvas = null;
var bgCanvas = null;
var outputCanvas = null;
var greenThreshold = 240;

function loadForegroundImage() {
  var imgFile = document.getElementById("fgfile");
  fgImage = new SimpleImage(imgFile);
  fgCanvas = document.getElementById("fgcan");
  fgImage.drawTo(fgCanvas);
}

function loadBackgroundImage() {
  var imgFile = document.getElementById("bgfile");
  bgImage = new SimpleImage(imgFile);
  bgCanvas = document.getElementById("bgcan");
  bgImage.drawTo(bgCanvas);
}

function doGreenScreen() {
  if (fgImage == null || ! fgImage.complete()) {
    alert("Foreground image is not loaded");
    return;
  }
  if (bgImage == null || ! bgImage.complete()) {
    alert("Background image is not loaded");
    return
  }
 
  var outputImage = new SimpleImage(fgImage.getWidth(), fgImage.getHeight());
  for(var pixel of fgImage.values()) {
    var x = pixel.getX();
    var y = pixel.getY();
    if(pixel.getGreen() > greenThreshold) {
      var bgPixel = bgImage.getPixel(x,y);
      outputImage.setPixel(x,y,bgPixel);
    }
    else {
      outputImage.setPixel(x,y,pixel);
    }
  }
  outputCanvas = document.getElementById("outputcanvas");
  outputImage.drawTo(outputCanvas);
}

function clearCanvas() {
  var fgContext = fgCanvas.getContext("2d");
  fgContext.clearRect(0,0,fgCanvas.width,fgCanvas.height);
  var bgContext = bgCanvas.getContext("2d");
  bgContext.clearRect(0,0,bgCanvas.width, bgCanvas.height);
  var outputContext = outputCanvas.getContext("2d");
  outputContext.clearRect(0,0,outputCanvas.width,outputCanvas.height);
}