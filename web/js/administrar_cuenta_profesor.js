 var correoVar;
 if(typeof(Storage)!=="undefined") {
    correoVar = localStorage.getItem("id");
} else {}

$(document).ready(function() {
    $('#subir').click(function(event) {
        var nombreVar = $('#nombre_Profesor').val();
        var correoVar1 = $('#correo_Profesor').val();
        var contraseniaVar = $('#contrasenia_Profesor').val();
        $.post('profesor?operacion=editar_Profesor', {
            correo_Profesor : correoVar,
            nuevo_nombre_Profesor : nombreVar,
            nuevo_correo_Profesor : correoVar1,
            nuevo_contrasenia_Profesor : contraseniaVar
        }, function (respuesta) {
            var confirmacion = parseInt(respuesta);
            if (confirmacion === 2) {
                $('#mensaje').text("Datos modificados");
                
                if (correoVar1.trim() == "") {
                    
                } else {
                    localStorage.setItem("id", correoVar1);
                }
            } else if (confirmacion === 3) {
                $('#mensaje').text("No se pueden modificar los datos, intenta con otro correo");
            } else {
                $('#mensaje').text("Hubo un problema al editar la información. Intentalo de nuevo.");
            }
        });
    });
                    
    $('#borrar').click(function(event) {
        $.post('profesor?operacion=eliminar_Profesor', {
            correo_Profesor : correoVar
            }, function(respuesta) {
            var confirmacion = parseInt(respuesta);

            if (confirmacion === 4) {
                localStorage.removeItem("id");
                localStorage.removeItem("tipo");
                $('#mensaje').text("Lo sentimos, esperamos que regreses.");
                location.href="index.html";
            } else if(confirmacion === 5) {
                $('#mensaje').text("No se ha podido borrar la cuenta");
            } else {
                $('#mensaje').text("Hubo un problema al borrar la cuenta. Intentalo después.");
            }
        });
    });
});
