 var correoVar;
 if(typeof(Storage)!=="undefined") {
    correoVar = localStorage.getItem("id");
} else {}
$(document).ready(function() {
    //alert($('#1.gamil.com').val());
    //$('#hora_Inicio').append('<option value="0t">00:30</option>');
    /*var t = "1@gmail.com";
    $('#alumnos_tabla').html('<tr>');
    $('#alumnos_tabla').append('<td>'+
                    'Pancho el Golpeado López'+
                '</td>'+
                '<td>Principiante</td>'+
                '<td>5:00 - 6:00</td>'+
                '<td>'+
                  '<a href="#" data-dropdown="drop_1" class="button dropdown tiny">Acción</a><br>'+
                  '<ul id="drop_1" data-dropdown-content class="f-dropdown">'+
                      '<li><input type="button" class="button tiny alert" onclick="aceptar_estudiante(' + t + ', 47)" value="Aceptar"/></li> </ul> </td> </tr>');    
    */

    for (var i = 1; i < 20; i++) {
        var renglon = document.createElement('tr');
        renglon.setAttribute("id",i);
        var d = document.createElement('td');
        var texto = document.createTextNode('Principiante');
        d.appendChild(texto);
        renglon.appendChild(d);
        
        var d = document.createElement('td');
        var texto1 = document.createTextNode('5:00 - 6:00');
        d.appendChild(texto1);
        renglon.appendChild(d);
        
        var d = document.createElement('td');
        var input = document.createElement('input');
        input.setAttribute('type', 'button');
        input.setAttribute('class', 'button tiny alert');
        input.setAttribute('onclick', 'eliminar_curso(' + i + ')');
        input.setAttribute('value', 'Eliminar');
        d.appendChild(input);
        renglon.appendChild(d);
        
        document.getElementById('tabla_eliminar_curso').appendChild(renglon);
    }
});

var aceptar_estudiante = function(correoEst, id) {
    $.post('profesor?operacion=aceptar_Estudiante', {
        correo_Estudiante : correoEst,
        id_Curso : id
    }, function (respuesta) {
        if (parseInt(respuesta) === 6) {
            $('#mensaje').text("El estudiante ha sido aceptado para el curso");
            $(document).ready(function() {
                $('#alumnos_tabla').html('');
                $('#alumnos_tabla').remove("#" + id);
            });
        } else if (parseInt(respuesta) === 7) {
            $('#mensaje').text("No se pudo aceptar al estudiante");
        } else {
            $('#mensaje').text("Hubo un problema con la base de datos. Intentalo de nuevo.");
        }
    });
};

var eliminar_curso = function(id) {  
    $.post('curso?operacion=eliminar_Curso', {
        id_Curso : id
    }, function (respuesta) {
        if (parseInt(respuesta) === 4) {
            $('#mensaje').text("Se ha eliminado el curso");
            $(document).ready(function() {
                //$('#tabla_eliminar_curso').html('');
                var d = document.getElementById("tabla_eliminar_curso");
                var d_nested = document.getElementById(id);
                d.removeChild(d_nested);
            });
        } else if (parseInt(respuesta) === 5) {
            $('#mensaje').text("No se pudo eliminar el curso");
        } else {
            $('#mensaje').text("Hubo un problema con la base de datos. Intentalo de nuevo.");
        }
    });
};