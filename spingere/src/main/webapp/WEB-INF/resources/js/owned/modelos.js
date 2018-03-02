/* global GLOBAL, CanvasJS */

$(function() {
    $('.dropdown').dropdown();
    
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
        var dropdownModelos = $('#dModelos');
        dropdownModelos.parent().removeClass('disabled');
        dropdownModelos.prop('selectedIndex', 0);
    });
    
    $('#dModelos').change(function() {
        GLOBAL.showLoader();
        var tipoModelo = $('#dModelos').val();
        getModelo($('#dClientes').val(), $('#dProyectos').val(), tipoModelo).done(function(response) {
            if (!response.isOk) {
                GLOBAL.notify("<i class='icon warning circle red'></i>Error", response.message);
            }
            showModelo(response.info);
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

function getModelo(idCliente, idProyecto, idModelo) {
    return $.getJSON(GLOBAL.APP_CONTEXT + '/modelos/data', {
        c: idCliente,
        p: idProyecto,
        m: idModelo
    }).fail(function () {
        GLOBAL.notify("<i class='icon warning circle red'></i>Error", 'No se envió la petición, por favor intente nuevamente.');
    }).always(function() {
        GLOBAL.hideLoader();
    });
}

function showModelo(datos) {
    var chart = new CanvasJS.Chart("chartContainer", {
        animationEnabled: true,
        title: {text: "Gráfica Normal"},
        axisX: {stripLines: []},
        axisY: {
            title: "Unidades",
            valueFormatString: "#,##0.##",
            suffix: "",
            stripLines: [{
                    value: 0.124,
                    label: "Aceptación"
                }]
        },
        data: [{
                yValueFormatString: "#,##0.## Unidades",
                type: "spline",
                lineDashType: "solid",
                markerSize: 1,
                dataPoints: []
            }]
    });
    
    $.each(datos, function(i, d) {
        if (i === 0) {
            chart.options.axisX.stripLines.push({
                value: d.limiteInferior,
                lineDashType: "dot",
                label: d.limiteInferior
            }, {
                value: d.limiteSuperior,
                lineDashType: "dot",
                label: d.limiteSuperior
            });
            return true;
        }
        chart.options.data[0].dataPoints.push({
            x: d.x,
            y: d.y
        }); 
    });
    
    chart.render();
}
