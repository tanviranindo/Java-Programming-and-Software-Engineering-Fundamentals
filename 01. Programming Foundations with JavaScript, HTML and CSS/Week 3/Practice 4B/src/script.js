var imageOriginal;
var imageGrayscale;

function upload() {
  var fileinput = document.getElementById("finput");
  imageOriginal = new SimpleImage(fileinput);
  imageGrayscale = new SimpleImage(fileinput);
  var canvas = document.getElementById("can1");
  imageOriginal.drawTo(canvas);
}

function makeGray() {
    for (var pixel of imageGrayscale.values()) {
        var avg = (pixel.getRed() + pixel.getGreen() + pixel.getBlue())/3;
        pixel.setRed(avg);
        pixel.setGreen(avg);
        pixel.setBlue(avg);
    }
    var imgcanvas = document.getElementById("can2");
    imageGrayscale.drawTo(imgcanvas);
}