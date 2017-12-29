/* global w2popup, w2ui, GLOBAL */

$(function () {
    $('.ui.dropdown').dropdown();
    
    usuariosTable();
    getTipoCliente();

    $('#btnGuardar').on('click', function () {
        saveUser();
        w2ui['grid-clientes'].reload();
    });
});

function getTipoCliente() {
    var dropdown = $('#tipoCliente');
    dropdown.empty();
    dropdown.append('<option selected="true" disabled>Seleccione un Cliente</option>');
    dropdown.prop('selectedIndex', 0);

    $.getJSON(GLOBAL.APP_CONTEXT + '/cat/tipo-cliente').done(function (response) {
        if (!response.isOk) {
            GLOBAL.GLOBAL.notify("<i class='icon warning circle red'></i>Error", response.message);
        }
        $.each(response.info, function (i, data) {
            dropdown.append($('<option></option>').attr('value', data.idCliente).text(data.cliente + " - " + data.razonSocialCliente));
        });
    }).fail(function () {
        GLOBAL.notify("<i class='icon warning circle red'></i>Error", 'No fue posible obtener los tipos de clientes');
    });
}

function fillFormUser() {
    var $gridClientes = w2ui['grid-clientes'];
    var recId = $gridClientes.getSelection();
    var registro = $gridClientes.get(recId)[0];
    $('#formUser input[name="usuario"]').val(registro.usuario);
    $('#formUser input[name="idUsuario"]').val(registro.recid);
    $('#formUser input[name="nombre"]').val(registro.nombre);
    $('#formUser input[name="apPaterno"]').val(registro.apPaterno);
    $('#formUser input[name="apMaterno"]').val(registro.apMaterno);
    $('#formUser input[name="email"]').val(registro.email);
    $('#formUser input[name="contrasena"]').val(registro.contrasena);
    $('#tipoCliente').dropdown('set selected', registro.idCliente);
}

function usuariosTable() {
    GLOBAL.showLoader();
    $.getJSON(GLOBAL.APP_CONTEXT + '/cat/cliente-usuario').done(function (response) {
        if (!response.isOk) {
            GLOBAL.notify("<i class='icon warning circle red'></i>Error", response.message);
        }

        var gridName = 'grid-clientes';
        
        $('#' + gridName).w2grid({
            name: gridName,
            header: 'Usuarios',
            show: {
                toolbar: true,
                header: true,
                footer: true,
                toolbarReload: false,
                toolbarColumns: false
            },
            toolbar: {
                items: [
                    {type: 'spacer'},
                    {type: 'break'},
                    {type: 'button', id: 'alta', caption: 'Alta', img: 'w2ui-icon-plus'},
                    {type: 'break'},
                    {type: 'button', id: 'editar', caption: 'Editar', img: 'w2ui-icon-pencil', disabled: true},
                    {type: 'break'},
                    {type: 'button', id: 'eliminar', caption: 'Eliminar', img: 'w2ui-icon-cross', disabled: true}
                ],
                onClick: function (target, data) {
                    var $formAlta = $('#divFormaAlta');
                    if (target === "eliminar") {
                        console.log("boton eliminar");
                    } else if (target === "alta") {
                        $formAlta.show();
                    } else {
                        $formAlta.show();
                        fillFormUser();
                    }
                }
            },
            multiSelect: false,
            columns: [
                {field: 'recid', caption: 'Id Usuario', size: '1%', hidden: true},
                {field: 'idCliente', caption: 'Id Cliente', size: '1%', hidden: true},
                {field: 'cliente', caption: 'Cliente', size: '12%', sortable: true, resizable: true},
                {field: 'rolUsuario', caption: 'Rol usuario', size: '12%', sortable: true, resizable: true},
                {field: 'usuario', caption: 'Usuario', size: '12%', sortable: true, resizable: true},
                {field: 'nombre', caption: 'Nombre', size: '12%', sortable: true, resizable: true},
                {field: 'apPaterno', caption: 'Ap Paterno', size: '12%', sortable: true, resizable: true},
                {field: 'apMaterno', caption: 'Ap Materno', size: '12%', sortable: true, resizable: true},
                {field: 'email', caption: 'Email', size: '20%', sortable: true, resizable: true}
            ],
            records: [],
            onSelect: function(e) {
                e.done(function() {
                    this.toolbar.enable('editar');
                    this.toolbar.enable('eliminar');
                });
            },
            onUnselect: function (e) {
                e.done(function() {
                    this.toolbar.disable('editar');
                    this.toolbar.disable('eliminar');
                });
            }
        });

        var $gridToolbar = w2ui[gridName].toolbar;
        var records = response.info;
        
        if (records.length <= 0) {
            $gridToolbar.disable('editar');
            $gridToolbar.disable('eliminar');
            return;
        } else {
            $gridToolbar.enable('editar');
            $gridToolbar.enable('eliminar');
        }

        $.each(records, function (i, u) {
            w2ui[gridName].add({
                recid: u.usuario.idUsuario,
                idCliente: u.cliente.idCliente,
                cliente: u.cliente.cliente,
                rolUsuario: u.rolUsuario.rolUsuario,
                usuario: u.usuario.usuario,
                nombre: u.usuario.nombre,
                apPaterno: u.usuario.apellidoPaterno,
                apMaterno: u.usuario.apellidoMaterno,
                email: u.usuario.correoUsuario
            });
        });
        
    }).fail(function() {
        GLOBAL.notify("<i class='icon warning circle red'></i>Error", 'No fue posible obtener la lista de usuarios');
    }).always(function() {
        GLOBAL.hideLoader();
    });
}

function saveUser() {
    $.ajax({
        url: GLOBAL.APP_CONTEXT + '/users/save',
        contentType: 'application/json',
        async: false,
        type: "POST",
        data: JSON.stringify({
            idUsuario: $('#formUser input[name="idUsuario"]').val(),
            tipoCliente: $('#tipoCliente').dropdown('get value'),
            usuario: $('#formUser input[name="usuario"]').val(),
            nombre: $('#formUser input[name="nombre"]').val(),
            apPaterno: $('#formUser input[name="apPaterno"]').val(),
            apMaterno: $('#formUser input[name="apMaterno"]').val(),
            email: $('#formUser input[name="email"]').val(),
            contrasena: $('#formUser input[name="contrasena"]').val()
        }),
        dataType: 'json'
    }).done(function (response) {
        if (!response.isOk) {
            GLOBAL.notify("<i class='icon warning circle red'></i>Error", response.message);
            $.each(response.errors, function (k, v) {
                showValidationErros(k, v);
            });
            return;
        }
        GLOBAL.notify("<i class='icon warning circle green'></i>Éxito", response.message);
        var $gridClientes = w2ui['grid-clientes'];
        $gridClientes.reload();
        $gridClientes.refresh();
    }).fail(function () {
        GLOBAL.notify("<i class='icon warning circle red'></i>Error", 'No fue posible enviar la petición...');
    });
}

function showValidationErros(fieldName, message) {
    if (fieldName === 'nombre') {
        var $parent = $('input[name="' + fieldName + '"]').parent();
        $parent.addClass('error');
        $parent.append('<div class="ui basic red pointing prompt label transition visible">' + message + '</div>');
    }
}
