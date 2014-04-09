$(document).ready(function() {
    $('#registrarProfesor').click(function(event) {
        var nombreVar = $('#nombre_Profesor').val();
        var correoVar = $('#correo_Profesor').val();
        var contraseniaVar = $('#contrasenia_Profesor').val();
                        

        $.post('profesor?operacion=registrar_Profesor', {
            nombre_Profesor : nombreVar,
            correo_Profesor : correoVar,
            contrasenia_Profesor : contraseniaVar
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
