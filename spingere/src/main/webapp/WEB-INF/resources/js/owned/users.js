/* global w2popup, w2ui */

var APP_CONTEXT = '/spingere';

$(function () {
    var token = $("input[name='_csrf']").val();
    var header = "X-CSRF-TOKEN";
    $(document).ajaxSend(function(e, xhr, options) {
        xhr.setRequestHeader(header, token);
    });
    $('.ui.dropdown').dropdown();
    
    usuariosTable();    
    getTipoCliente();        
    
    $('#btnGuardar').on('click', function() {
        console.log('reached');
        saveUser();
        w2ui['grid-clientes'].reload();
    });
    
    $('#btnHideForm').on('click', function() {
        console.log('oculta pantallas');
        $('#divFormaAlta').css('visibility', 'hidden');
        w2ui['grid-clientes'].reload();
    });    
});


function getTipoCliente(){        
    let dropdown = $('#tipoCliente');
    dropdown.empty();
    dropdown.append('<option selected="true" disabled>Seleccione un Cliente</option>');
    dropdown.prop('selectedIndex', 0);
    
    $.getJSON(APP_CONTEXT + '/users/getTipoCliente').done(function(response) {
        if (!response.isOk) {
            notify("<i class='icon warning circle red'></i>Error", response.message);
        }
        $.each(response.info, function(i, data) {
            dropdown.append($('<option></option>').attr('value', data.idCliente).text(data.cliente + " - " + data.razonSocialCliente));
        });
        
    }).fail(function() {
        notify("<i class='icon warning circle red'></i>Error", 'No fue posible obtener los tipos de clientes');
    });
}

function prepareAlta(){
    $('#divFormaAlta').css('visibility', 'visible');
}

function fillFormUser() {
    var recId = w2ui['grid-clientes'].getSelection();
    var registro = w2ui["grid-clientes"].get(recId);
    $('#formUser input[name="usuario"]').val(registro[0].usuario);
    $('#formUser input[name="idUsuario"]').val(registro[0].recid);
    $('#formUser input[name="nombre"]').val(registro[0].nombre);
    $('#formUser input[name="apPaterno"]').val(registro[0].apPaterno);
    $('#formUser input[name="apMaterno"]').val(registro[0].apMaterno); 
    $('#formUser input[name="email"]').val(registro[0].email);     
    $('#formUser input[name="contrasena"]').val(registro[0].contrasena);     
    $('#tipoCliente').dropdown('set selected', registro[0].idCliente); 
}

function usuariosTable() {
    $.getJSON(APP_CONTEXT + '/users/get').done(function(response) {
        if (!response.isOk) {
            notify("<i class='icon warning circle red'></i>Error", response.message);
        }
        
        var gridName = 'grid-clientes';
        $('#' + gridName).w2grid({
            name: gridName,
            show: {
                toolbar: true,
                footer: true,
                toolbarReload: false,
                toolbarColumns: false
            },
            toolbar: {
                items: [
                    { type: 'spacer' },
                    { type: 'break' },
                    { type: 'button', id: 'alta', caption: 'Alta', img: 'w2ui-icon-plus' },
                    { type: 'break' },
                    { type: 'button', id: 'editar', caption: 'Editar', img: 'w2ui-icon-pencil' },
                    { type: 'break' },
                    { type: 'button', id: 'eliminar', caption: 'Eliminar', img: 'w2ui-icon-cross' }
                ],
                onClick: function (target, data) {
                    if (target === "eliminar")
                        console.log("boton eliminar");
                    else if (target === "alta")
                        prepareAlta();
                    else{
                        prepareAlta();
                        fillFormUser();
                    }
                }
            },
            multiSelect: false,
            columns: [
                {field: 'recid', caption: 'Id Usuario', size: '1%', sortable: true, attr: 'align=center'},
                {field: 'idCliente', caption: 'Id Cliente', size: '1%', sortable: true, resizable: true},
                {field: 'contrasena', caption: 'Contrasena', size: '1%', sortable: true, resizable: true},
                {field: 'cliente', caption: 'Cliente', size: '12%', sortable: true, resizable: true},
                {field: 'rolUsuario', caption: 'Rol usuario', size: '12%', sortable: true, resizable: true},
                {field: 'usuario', caption: 'Usuario', size: '12%', sortable: true, resizable: true},
                {field: 'nombre', caption: 'Nombre', size: '12%', sortable: true, resizable: true},
                {field: 'apPaterno', caption: 'Ap Paterno', size: '12%', sortable: true, resizable: true},
                {field: 'apMaterno', caption: 'Ap Materno', size: '12%', sortable: true, resizable: true},
                {field: 'email', caption: 'Email', size: '20%', sortable: true, resizable: true}
            ],
            records: []
            /*,sortData: [{field: 'recid', direction: 'ASC'}]*/
        });
        
        $.each(response.info, function(i, u) {
            w2ui[gridName].add({
                recid: u.usuario.idUsuario,
                idCliente: u.cliente.idCliente,
                contrasena: u.usuario.contrasena,
                cliente: u.cliente.cliente,
                rolUsuario: u.rolUsuario.rolUsuario,
                usuario: u.usuario.usuario,
                nombre: u.usuario.nombre,
                apPaterno: u.usuario.apellidoPaterno,
                apMaterno: u.usuario.apellidoMaterno,
                email: u.usuario.correoUsuario
            });
        });
        w2ui[gridName].hideColumn('recid', 'idCliente', 'contrasena');
    }).fail(function() {
        notify("<i class='icon warning circle red'></i>Error", 'No fue posible obtener la lista de usuarios');
    });
}

function saveUser() {
    $.ajax({
        url: APP_CONTEXT + '/users/save',
        contentType: 'application/json',
        async: false,
        type: "POST",
        data: JSON.stringify({
            idUsuario : $('#formUser input[name="idUsuario"]').val(),
            tipoCliente : $('#tipoCliente').dropdown('get value'),
            usuario : $('#formUser input[name="usuario"]').val(),            
            nombre : $('#formUser input[name="nombre"]').val(),
            apPaterno : $('#formUser input[name="apPaterno"]').val(),
            apMaterno : $('#formUser input[name="apMaterno"]').val(),
            email : $('#formUser input[name="email"]').val(),
            contrasena : $('#formUser input[name="contrasena"]').val()
        }),
        dataType: 'json'
    }).done(function(response) {
        if (!response.isOk) {
            notify("<i class='icon warning circle red'></i>Error", response.message);
            $.each(response.errors, function(k, v) {
                showValidationErros(k, v);
            });
            return;
        }        
        notify("<i class='icon warning circle green'></i>Éxito", response.message);        
        w2ui['grid-clientes'].reload();
        w2ui['grid-clientes'].refresh(); 
    }).fail(function() {
        notify("<i class='icon warning circle red'></i>Error", 'No fue posible enviar la petición...');
    });    
}

function notify(title, msg) {
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

function showValidationErros(fieldName, message) {
    if (fieldName === 'nombre') {
        $('input[name="' + fieldName + '"]').parent().addClass('error');
    }
}
