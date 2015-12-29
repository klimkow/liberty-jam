function redirect(href)
{
    window.location = href;
}

function uploadImages()
{
    var elements = document.getElementsByClassName("img-to-download");
    for(var i=0; i<elements.length; i++) {
        uploadFile(elements[i].file);
    }
}

var fileInput = $('#file-field');
var imgList = $('div#img-list');
var dropBox = $('#img-container');

fileInput.bind({
    change: function() {
        displayFiles(this.files);
    }
});

dropBox.bind({
    dragenter: function() {
        $(this).addClass('highlighted');
        return false;
    },
    dragover: function() {
        return false;
    },
    dragleave: function() {
        $(this).removeClass('highlighted');
        return false;
    },
    drop: function(e) {
        var dt = e.originalEvent.dataTransfer;
        displayFiles(dt.files);
        return false;
    }
});


function displayFiles(files) {
    $.each(files, function(i, file) {
        if (!file.type.match(/image.*/) || files.size > 3) {
            return true;
        }
        document.getElementById('image_main').value = file.name;
        var li = $('<div id="img" class="img-to-download" style="float: left; padding: 2px 7px;"/>').appendTo(imgList);
        $('<div/>').text(file.name).appendTo(li);
        var img = $('<img/>').appendTo(li);
        li.get(0).file = file;

        var reader = new FileReader();
        reader.onload = (function(aImg) {
            return function(e) {
                aImg.attr('src', e.target.result);
                aImg.attr('width', 100);
            };
        })(img);

        reader.readAsDataURL(file);
    });
}

function uploadFile(file) {

    var formData = new FormData();
    formData.append("file", file);

    var xhr = new XMLHttpRequest();
    //xhr.upload.addEventListener("progress", uploadProgress, false);
    //xhr.addEventListener("load", uploadComplete, false);
    xhr.open("POST", "b_image_upload", false);
    xhr.send(formData);
}

function turnOnLoadingGlass()
{
    $('#navbar-header').append('<div id="top-layer"></div>');
}