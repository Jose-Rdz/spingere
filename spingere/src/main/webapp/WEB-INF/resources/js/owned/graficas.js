/* global GLOBAL */

$(function () {
    $('.ui.dropdown').dropdown();
    
    $('#dClientes').change(function() {
        var selection = this.value;
        var dropdownProyectos = $('#dProyectos');
        var dropdownGraficas = $('#dGraficas');
        
        dropdownProyectos.parent().addClass('disabled');
        dropdownProyectos.empty();
        dropdownProyectos.append('<option value="">Elija un proyecto...</option>');
        dropdownProyectos.prop('selectedIndex', 0);
        dropdownProyectos.parent().removeClass('disabled');
        
        dropdownGraficas.addClass('disabled');
        dropdownGraficas.prop('selectedIndex', 0);
        
        getProyectosCliente(selection).done(function (response) {
            if (!response.isOk) {
                GLOBAL.notify("<i class='icon warning circle red'></i>Error", response.message);
            }
            $.each(response.info, function (i, data) {
                dropdownProyectos.append($('<option></option>').attr('value', data.idProyecto).text(data.proyecto));
            });
        });
    });
    
    $('#dProyectos').change(function() {
        var dropdownGraficas = $('#dGraficas');
        dropdownGraficas.parent().removeClass('disabled');
        dropdownGraficas.prop('selectedIndex', 0);
    });
    
    $('#dGraficas').change(function() {
        getGraficaHtml($('#dClientes').val(), $('#dProyectos').val(), this.value).done(function(response) {
            if (!response.isOk) {
                GLOBAL.notify("<i class='icon warning circle red'></i>Error", response.message);
            }
            $('#displayGrafica').show().html(response.info);
        });
    });
    
});

function getProyectosCliente(idCliente) {
    return $.getJSON(GLOBAL.APP_CONTEXT + '/cat/proyectos-cliente', {
        i : idCliente
    }).fail(function () {
        GLOBAL.notify("<i class='icon warning circle red'></i>Error", 'No se envió la petición, por favor intente nuevamente.');
    });
}

function getGraficaHtml(idCliente, idProyecto, idGrafica) {
    return $.getJSON(GLOBAL.APP_CONTEXT + '/graficas/display', {
        c: idCliente,
        p: idProyecto,
        g: idGrafica
    }).fail(function () {
        GLOBAL.notify("<i class='icon warning circle red'></i>Error", 'No se envió la petición, por favor intente nuevamente.');
    });
}
