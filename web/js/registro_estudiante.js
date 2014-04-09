$(document).ready(function() {            
    $('#registrarEstudiante').click(function(event) {
        var nombreVar = $('#nombre_Estudiante').val();
        var correoVar = $('#correo_Estudiante').val();
        var contraseniaVar = $('#contrasenia_Estudiante').val();
        
        $.post('estudiante?operacion=registrar_Estudiante', {
            nombre_Estudiante : nombreVar,
            correo_Estudiante : correoVar,            
            contrasenia_Estudiante : contraseniaVar            
        }, function(respuesta) {                
            if (parseInt(respuesta) === 0) {                
                $('#mensaje').text("Registro exitoso");                
            } else if (parseInt(respuesta) === 1) {                
                $('#mensaje').text("Ya existe ese correo. Intente con otro.");                
            } else {                
                $('#mensaje').text("Hubo un fallo en el registro");
            }
        });
    });
});