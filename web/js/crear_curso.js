 var correoVar;
 if(typeof(Storage)!=="undefined") {
    correoVar = localStorage.getItem("id");
} else {}

$(document).ready(function() {
    $('#hora_Inicio').html('<option value="0">00:00</option>');
    $('#hora_Inicio').append('<option value="0t">00:30</option>');
    for (var i = 1; i < 10; i++) {
        $('#hora_Inicio').append('<option value="' + i + '">0' + i + ':00</option>');
        $('#hora_Inicio').append('<option value="' + i + 't">0' + i + ':30</option>');
    }
    
    for (var i = 10; i < 24; i++) {
        $('#hora_Inicio').append('<option value="' + i + '">' + i + ':00</option>');
        $('#hora_Inicio').append('<option value="' + i + 't">' + i + ':30</option>');
    }
    
    $('#hora_Final').html('<option value="0">00:00</option>');
    $('#hora_Final').append('<option value="0t">00:30</option>');
    for (var i = 1; i < 10; i++) {
        $('#hora_Final').append('<option value="' + i + '">0' + i + ':00</option>');
        $('#hora_Final').append('<option value="' + i + 't">0' + i + ':30</option>');
    }
    
    for (var i = 10; i < 24; i++) {
        $('#hora_Final').append('<option value="' + i + '">' + i + ':00</option>');
        $('#hora_Final').append('<option value="' + i + 't">' + i + ':30</option>');
    }

});

var crear_curso = function() {
        var posicion = document.getElementById('hora_Inicio').options.selectedIndex;
        var hora_Inicio = document.getElementById('hora_Inicio').options[posicion].text;
        var posicion = document.getElementById('hora_Final').options.selectedIndex;
        var hora_Final = document.getElementById('hora_Final').options[posicion].text;
        var posicion = document.getElementById('tipo_Curso').options.selectedIndex;
        var tipo_curso = document.getElementById('tipo_Curso').options[posicion].text;
        
        $.post('curso?operacion=crear_Curso', {
            correo_Profesor : correoVar,            
            tiempo_Inicio : hora_Inicio,
            tiempo_Final : hora_Final,
            tipo_Curso : tipo_curso
        }, function(respuesta) {          
            if (parseInt(respuesta) === -1) {                
                $('#mensaje').text("La hora final del curso es menor a la hora inicial");                
            } else if (parseInt(respuesta) === 0) {                
                $('#mensaje').text("Se ha creado el curso");                
            } else if (parseInt(respuesta) === 1) {                
                $('#mensaje').text("No se puede crear el curso, ya está dando un curso a esa hora");                
            } else {                
                $('#mensaje').text("Hubo un fallo al intentar crear el curso");
            }
        });
};