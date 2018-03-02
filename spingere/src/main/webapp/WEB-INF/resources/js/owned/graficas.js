/* global GLOBAL, CanvasJS */

$(function() {
    
    $('.ui.dropdown').dropdown();
    
    var chart = new CanvasJS.Chart("chartContainer", {
        animationEnabled: true,
        theme: "light2", // "light1", "light2", "dark1", "dark2"
        title: {
            text: " "
        },
        axisY: {
            title: " "
        },
        axisX:{
            labelFontSize: 15
        },
        data: [{
                type: "column",
                showInLegend: true,                
                indexLabel: "{y}",
                legendMarkerColor: "grey",
                legendText: "P - Proveedor | C - Cliente | A - Acordada",
                dataPoints: [] // esto es lo que debe de cambiar dinámicamente
                }, 
                {
                type: "spline",
                showInLegend: true,
                markerSize:1, //Tamaño de la marca, para que no se vea el punto. 
                legendMarkerColor: "#C24642",
                labelFontColor: "#C24642",
                legendText: "Tamaño",
                lineColor: "#C24642",
                dataPoints: [] // esto es lo que debe de cambiar dinámicamente
            }]
    });

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
    
    var idProyecto = 0;
    
    $('#dProyectos').change(function() {
        idProyecto = parseInt(this.value);
        var dropdownGraficas = $('#dGraficas');
        dropdownGraficas.parent().removeClass('disabled');
        dropdownGraficas.prop('selectedIndex', 0);
    });
    
    var valorGrafica = 0;
    
    $('#dGraficas').change(function() {
        valorGrafica = parseInt(this.value);
        //console.log("valorGrafica = " + valorGrafica);
        GLOBAL.showLoader();
        getGraficaHtml($('#dClientes').val(), $('#dProyectos').val(), valorGrafica).done(function(response) {
            if (!response.isOk) {
                GLOBAL.notify("<i class='icon warning circle red'></i>Error", response.message);
            }
            
            $('#displayGrafica').show().html(response.info);
            
            if (valorGrafica === 1 || valorGrafica === 2) {
                $('#displayGraficaBarras').show();            
                var dropdownTiposGraficasBarras = $('#dTipoGraficaBarras');
                dropdownTiposGraficasBarras.empty();
                //dropdownTiposGraficasBarras.append('<option value="0">Elija una gráfica</option>');
                dropdownTiposGraficasBarras.prop('selectedIndex', 0);
                dropdownTiposGraficasBarras.parent().removeClass('disabled');
                var tiposGraficas = [];
                if (valorGrafica === 1){
                    tiposGraficas = [{"1":"Tamaño Funcional - CFP",
                            "2":"Tamaño Funcional - CFP - Detalle",   //1
                            //"3":"Tamaño Funcional - CFP - Est.",   //1
                            "4":"Estimación Esfuerzo - HH",
                            "5":"Estimación Esfuerzo - HH - Detalle", //2
                            "6":"Product Delivery Rate - PDR", //3
                            "7":"Proveedor - Fases", // 4
                            "8":"Proveedor - Fases - Detalle", // 4
                            "9":"Cliente - Fases", // 5
                            "10":"Cliente - Fases - Detalle", // 5
                            "11":"Acordada - Fases", //6
                            "12":"Acordada - Fases - Detalle", //6
                            "13":"Medición - Fases",  //7
                            "14":"Medición - Fases - Detalle"}];  //7
                }else {
                    tiposGraficas = [{"15":"Tamaño funcional CFP", //8
                            "16":"Estimación Esfuerzo HH", //9
                            "17":"Esfuerzo por fase"}]; // 10
                }
                $.each(tiposGraficas, function () {
                    $.each(this, function (value, text) {
                        dropdownTiposGraficasBarras.append($('<option></option>').attr('value', value).text(text));
                    });
                });
                //Selecciona la primera opción de gráfica
                dropdownTiposGraficasBarras.prop('selectedIndex', 0);
                //Se invoka para graficar 
                $( "#dTipoGraficaBarras" ).change();
            } else {
                $('#displayGraficaBarras').hide();
                return;
            }
        });
    });
    
    $('#dTipoGraficaBarras').change(function() {
        //console.log("valorGrafica = " + valorGrafica);
        var subtipoGrafica = parseInt(this.value);        
        //console.log("idProyecto = " + idProyecto);
        //console.log("subtipoGrafica = " + subtipoGrafica);
        GLOBAL.showLoader();
        getDatosGraficaBarras(valorGrafica, subtipoGrafica, idProyecto).done(function(response) {
            if (!response.isOk) {
                GLOBAL.notify("<i class='icon warning circle red'></i>Error", response.message);
            } 
            var colors=[];
            chart.options.data[0].type = "column"; 
            chart.options.data[1].type = "spline";             
            chart.options.data[0].legendText= "P - Proveedor | C - Cliente | A - Acordada";
            chart.options.data[1].indexLabel =  " ";
            var valorHorizontal = 0; 
            if (subtipoGrafica === 1){
                valores=["P-Esp", "C-Esp", "A-Esp"];
                colors=["#D5F5E3","#FCF3CF","#85C1E9"];
            } else if (subtipoGrafica === 2){
                valores=["P-Min", "P-Esp", "P-Max"," ", "C-Min", "C-Esp", "C-Max"," ","A-Min", "A-Esp", "A-Max"];
                colors=["#EAFAF1","#D5F5E3","#ABEBC6","#ABEBC6","#FCF3CF","#F9E79F","#F7DC6F","#F7DC6F","#AED6F1","#85C1E9","#5DADE2"];                
            } else if (subtipoGrafica === 4){
                valores=["Prov", "C-Esp", "A-Esp"];
                colors=["#EAFAF1","#F9E79F","#85C1E9"];
            } else if (subtipoGrafica === 5){
                valores=["Prov", " ", "C-Min", "C-Esp", "C-Max", " ", "A-Min", "A-Esp", "A-Max"];                            
                colors=["#EAFAF1","#EAFAF1", "#FCF3CF","#F9E79F","#F7DC6F","#F7DC6F","#AED6F1","#85C1E9","#5DADE2"];
            } else if (subtipoGrafica === 6){
                valores=["Cliente", "Proveedor", "Acordada"];                
                colors=["#F7DC6F","#ABEBC6","#5DADE2"];
            } else if (subtipoGrafica === 7 || subtipoGrafica === 9 || subtipoGrafica === 11 || subtipoGrafica === 13) {
                valores=["R-Esp", "I-Esp", "E-Esp", "C-Esp", "T-Esp","A-Esp"];                
                colors = ["#D5F5E3","#F9E79F","#85C1E9","#F5B7B1","#A9DFBF","#D7DBDD"];
                chart.options.data[0].type = "bar"; 
                chart.options.data[0].legendText= "R - Requerimientos | I - Inicio | E - Elaboración | C - Construcción | T - Transición | A - Admon Proy"; 
                if (subtipoGrafica !== 13){
                    chart.options.data[1].indexLabel =  "{y}";
                    chart.options.data[1].type = "bar"; 
                }                
            } else if (subtipoGrafica === 8 || subtipoGrafica === 10 || subtipoGrafica === 12 || subtipoGrafica === 14){
                valores=["R-Min", "R-Esp", "R-Max", "I-Min", "I-Esp", "I-Max", "E-Min", "E-Esp", "E-Max", "C-Min", "C-Esp", "C-Max", "T-Min", "T-Esp", "T-Max", "A-Min", "A-Esp", "A-Max"];                
                colors = ["#EAFAF1","#D5F5E3","#ABEBC6","#FCF3CF","#F9E79F","#F7DC6F","#AED6F1","#85C1E9","#5DADE2","#FADBD8","#F5B7B1","#F1948A","#D4EFDF","#A9DFBF","#7DCEA0","#E5E7E9","#D7DBDD","#CACFD2"];
                chart.options.data[0].type = "bar"; 
                chart.options.data[0].legendText= "R - Requerimientos | I - Inicio | E - Elaboración | C - Construcción | T - Transición | A - Admon Proy"; 
            } else if (subtipoGrafica === 15){
                valores=["P-Min", "P-Esp", "P-Max","C-Min", "C-Esp", "C-Max"];
                colors=["#EAFAF1","#D5F5E3","#ABEBC6","#FCF3CF","#F9E79F","#F7DC6F"];
            } else if (subtipoGrafica === 16){
                valores=["Cliente","Proveedor","Acordada"];
                colors=["#F7DC6F","#ABEBC6","#5DADE2"];
                chart.options.data[0].legendText= " "; 
            } else if (subtipoGrafica === 17){
                valores=["Req","Análisis y Diseño","Construcción", "Implementación", "Planeación", "Pruebas", "Otro"];
                colors=["#EAFAF1","#D5F5E3","#ABEBC6","#FCF3CF","#F9E79F","#F7DC6F","#AED6F1"];
                chart.options.data[0].legendText= " ";
            }
            //Se limpia la gráfica
            chart.options.data[0].dataPoints = []; 
            chart.options.data[1].dataPoints = []; 
            
            var acumulado = 0.0;
            valorHorizontal = 0; 
            
            var dataGraph1 = []; 
            var dataGraph2 = [];
            
            $.each(response.info, function (i, d) {
                if (subtipoGrafica === 2)  //Tamaño funcional CFP Detalle
                    if (i === 3 || i === 6)
                        dataGraph1.push(" ");                
                if (subtipoGrafica === 5)  //Estimación Esfuerzo HH Detalle
                    if (i === 1 || i === 4 )
                        dataGraph1.push(" ");
                
                if ((subtipoGrafica === 1 && i === 3) || (subtipoGrafica === 4 && i === 3) || (subtipoGrafica === 2 && i === 9)) {
                    valorHorizontal = d.valor;  //Tamaño para la grafica tamaño funcional CFP 
                    chart.options.data[1].legendText= "Tamaño: " + valorHorizontal; 
                } else if (subtipoGrafica === 5 && i === 7) {
                    valorHorizontal = d.valor;  //Est. Medición
                    chart.options.data[1].legendText= "Est. Medición: " + valorHorizontal; 
                } else if ((subtipoGrafica === 7 || subtipoGrafica === 9 || subtipoGrafica === 11) && i >= 6) {
                    dataGraph2.push(d.valor); 
                    chart.options.data[1].legendText= "Medición";
                } else {
                    dataGraph1.push(d.valor);
                }
            }); 
            
            $.each(dataGraph1, function (i, d) {
                chart.options.data[0].dataPoints.push({
                    y: dataGraph1[i],
                    label: valores[i],
                    color: colors[i],
                    indexLabelFontSize: 13
                });
                if (subtipoGrafica === 1 || subtipoGrafica === 2 || subtipoGrafica === 4 || subtipoGrafica === 5) {  //Solo se grafica cuando hay valor horizontal 
                    chart.options.data[1].dataPoints.push({
                        y: valorHorizontal,                    
                        color: "#C24642" //Linea roja
                    });
                }
                acumulado += d.valor;
            });
            if (subtipoGrafica === 7 || subtipoGrafica === 9 || subtipoGrafica === 11){
                $.each(dataGraph2, function (i, d) {
                        chart.options.data[1].dataPoints.push({
                        y: dataGraph2[i],
                        label: valores[i],
                        color: "#C24642", //Linea roja
                        indexLabelFontSize: 13
                    });
                });
            }
            
            if (subtipoGrafica === 16){ //Se agrega la barra de acordada (promedio) de las dos que se envía de la base de datos. 
                var promedio = acumulado / 2.0; 
                chart.options.data[0].dataPoints.push({
                    y: promedio,
                    label: valores[2],
                    color: colors[2]                                                             
                });
            }            
            chart.render();
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
    }).always(function() {
        GLOBAL.hideLoader();
    });
}

function getDatosGraficaBarras(idGrafica, idSubtipoGrafica, idProyecto) {
    return $.getJSON(GLOBAL.APP_CONTEXT + '/graficas/barras', {
        g: idGrafica,
        t: idSubtipoGrafica,
        p: idProyecto
    }).fail(function () {
        GLOBAL.notify("<i class='icon warning circle red'></i>Error", 'No se envió la petición, por favor intente nuevamente.');
    }).always(function() {
        GLOBAL.hideLoader();
    });
}
