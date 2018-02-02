/* global GLOBAL */

$(function () {
    var $progressBar    = $('#progressBar');
    var $alertMsg       = $('#alertMsg');
    var $btnSubmit      = $('button[type=submit]');
    var $inputFile      = $('input[type=file]');
    
    $progressBar.progress({
        percent: 0,
        total: 100,
        label: 'ratio',
        text: {
            ratio: '{percent}% leído...'
        }
    });
    
    $btnSubmit.click(function(e) {
        e.preventDefault();

        $(this).addClass('disabled');

        var form = document.forms[0];
        var formData = new FormData(form);

        var ajaxReq = $.ajax({
            url: GLOBAL.APP_CONTEXT + '/carga/xlsx',
            type: 'POST',
            data: formData,
            cache: false,
            contentType: false,
            processData: false,
            xhr: function () {
                var xhr = $.ajaxSettings.xhr(); // XmlHttpRequest object
                xhr.upload.onprogress = function (event) {
                    var perc = Math.round((event.loaded / event.total) * 100);
                    $progressBar.progress('increment', perc);
                };
                return xhr;
            },
            beforeSend: function (xhr) { // reset alert message and progress bar
                GLOBAL.showLoader();
                $alertMsg.text('');
                $progressBar.progress('reset');
                $progressBar.removeClass('success');
                $progressBar.removeClass('error');
            }
        });

        ajaxReq.done(function (msg) { // success of file upload
            $alertMsg.text(msg);
            $inputFile.val('');
            $btnSubmit.removeClass('disabled');
        });

        ajaxReq.fail(function (jqXHR) { // failure of file upload
            $alertMsg.text(jqXHR.responseText);
            $progressBar.progress('reset');
            $progressBar.removeClass('success');
            $progressBar.addClass('error');
            $btnSubmit.removeClass('disabled');
        });
        
        ajaxReq.always(function() {
            GLOBAL.hideLoader();
        });
    });
    
});
