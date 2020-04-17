var canvas = null;
var imageOriginal = null;
var imageGrayscale = null;
var imageRed = null;
var imageGreen = null;
var imageBlue = null;

function uploadImage() {
  var fileinput = document.getElementById("finput");
  imageOriginal = new SimpleImage(fileinput);
  imageGrayscale = new SimpleImage(fileinput);
  imageRed = new SimpleImage(fileinput);
  imageGreen = new SimpleImage(fileinput);
  imageBlue = new SimpleImage(fileinput);
  
  canvas = document.getElementById("canvas");
  imageOriginal.drawTo(canvas);
}

function makeGray() {
  if(imageGrayscale !== null && imageGrayscale.complete()) {
    doGray();
  } else {
    alert("Image is not loaded");
    return;
  }
  imageGrayscale.drawTo(canvas); 
}

function doGray() {
  for (var pixel of imageGrayscale.values()) {
        var avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue())/3;
        pixel.setRed(avg);
        pixel.setGreen(avg);
        pixel.setBlue(avg);
    }
}

function makeRed() {
  if(imageRed !== null && imageRed.complete()) {
    doRed();
  } else {
    alert("Image is not loaded");
    return;
  }
  imageRed.drawTo(canvas); 
}

function doRed() {
  for (var pixel of imageRed.values()) {
        var avg = (pixel.getRed() + pixel.getGreen()+ pixel.getBlue())/3;
        if(avg<128) {
          pixel.setRed(2 * avg);
          pixel.setGreen(0);
          pixel.setBlue(0);
        } else {
          pixel.setRed(255);
          pixel.setGreen((2 * avg) - 255);
          pixel.setBlue((2 * avg) - 255);
        }  
    }
}

function makeGreen() {
  if(imageGreen !== null && imageGreen.complete()) {
    doGreen();
  } else {
    alert("Image is not loaded");
    return;
  }
  imageGreen.drawTo(canvas); 
}

function doGreen() {
  for (var pixel of imageGreen.values()) {
        var avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue())/3;
        if(avg<128) {
          pixel.setRed(0);
          pixel.setGreen(2 * avg);
          pixel.setBlue(0);
        } else {
          pixel.setRed((2 * avg) - 255);
          pixel.setGreen(255);
          pixel.setBlue((2 * avg) - 255);
        }  
    }
}

function makeBlue() {
  if(imageBlue !== null && imageBlue.complete()) {
    doBlue();
  } else {
    alert("Image is not loaded");
    return;
  }
  imageBlue.drawTo(canvas); 
}

function doBlue() {
  for (var pixel of imageBlue.values()) {
        var avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue())/3;
        if(avg<128) {
          pixel.setRed(0);
          pixel.setGreen(0);
          pixel.setBlue(2 * avg);
        } else {
          pixel.setRed((2 * avg) - 255);
          pixel.setGreen((2 * avg) - 255);
          pixel.setBlue(255);
        }  
    }
}

function resetImage() {
  if(imageOriginal !== null && imageOriginal.complete()) {
   imageOriginal.drawTo(canvas);
  } else {
    alert("Image is not loaded");
    return;
  }
}