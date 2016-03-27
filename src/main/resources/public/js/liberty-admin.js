var imageFlag = 0;

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
        document.getElementById('image' + imageFlag).value = file.name;
        if (imageFlag < 3) {
            imageFlag++;
        } else {
            imageFlag = 0;
        }

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

function checkPayment()
{
    var post_url = 'https://pay139.paysec.by/orderstate/orderstate.cfm';
    var data = $('form[name="check_payment_form"]').serialize();
    turnOnLoadingGlass(true);
    $.ajax({
        type: 'POST',
        url: post_url,
        crossDomain: true,
        data: data,
        success: function(msg) {
            turnOnLoadingGlass(false);
        }
    });
}

function postRequest(data, url, result)
{
    turnOnLoadingGlass(true);
    $.ajax({
        type: 'POST',
        url: url,
        data: data,
        success: function(msg) {
            turnOnLoadingGlass(false);
            return msg;
        }
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

function turnOnLoadingGlass(enable)
{
    if (enable) {
        $('#navbar-header').append('<div id="top-layer"></div>');
    } else {
        $('#top-layer').remove();
    }
}