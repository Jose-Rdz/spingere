/* global w2popup */

var GLOBAL = {
    APP_CONTEXT: '/spingere',
    notify: function(title, msg) {
        w2popup.open({
            title: title,
            body: '<div class="w2ui-centered">' + msg + '</div>',
            buttons: '<button class="ui primary button btn" onclick="w2popup.close();">Aceptar</button>',
            width: 400,
            height: 200,
            overflow: 'hidden',
            color: '#333',
            speed: '0.3',
            opacity: '0.8',
            modal: true,
            showClose: false,
            showMax: false
        });
    }
};

$(function() {
    /*----------------------------------------------------------------------
    TEMPORAL -> header para enviar token de seguridad de usuario autenticado
    -----------------------------------------------------------------------*/
    var token = $("input[name='_csrf']").val();
    var header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function (e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
    
    /*--------------------
    Iniciar Sidebar Menu
    ----------------------*/
    var $sidebar = $('.sidebar');
    
    $sidebar.sidebar({
        dimPage : true,
        transition : 'overlay',
        mobileTransition : 'uncover'
    });
    
    $sidebar.sidebar('attach events', '.launch.button');
});

function showLoader() {
    $('#requestLoader').addClass('active');
}

function hideLoader() {
    $('#requestLoader').removeClass('active');
}
