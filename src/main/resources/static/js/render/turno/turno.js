let listaPacientesPorDNI = '<option selected>Selecciona un paciente</option><option value="1">One</option><option value="2">Two</option><option value="3">Three</option>'
let listaOdontologosPorApellido = '<option selected>Selecciona un paciente</option><option value="1">One</option><option value="2">Two</option><option value="3">Three</option>'
let form_crearTurno = `<form><div class="row"><div class="col"><div class="form-floating"><input type="text" class="form-control" id="crearTurno_buscarPacientePorDNI" placeholder="11222333"><label for="crearTurno_buscarPacientePorDNI">Buscar paciente por DNI</label></div><select class="form-select" size="5">${listaPacientesPorDNI}</select></div><div class="col"><div class="form-floating"><input type="text" class="form-control" id="crearTurno_buscarOdontologoPorApellido" placeholder="Apellido"><label for="crearTurno_buscarOdontologoPorApellido">Buscar odontologo por apellido</label></div><select class="form-select" size="5">${listaOdontologosPorApellido}</select></div></div><div class="form-floating my-3"><input type="datetime-local" class="form-control" id="crearTurno_seleccionarFecha" placeholder="Apellido"><label for="crearTurno_seleccionarFecha">Seleccionar fecha y hora para el turno</label></div><input type="submit" value="crear Turno"></form>`

let verTurnos = `<table class="table table-hover w-100">
<thead>
<tr>
  <th scope="col">Id</th>
  <th scope="col">Fecha</th>
  <th scope="col">Paciente</th>
  <th scope="col">Odontologo</th>
  <th scope="col">Acción</th>
</tr>
</thead>
<tbody>
<tr>
  <th scope="row"><strong>Loading...</strong></th>
  <td class="spinner-border ms-auto" role="status" aria-hidden="true"></td>
  <td class="spinner-border ms-auto" role="status" aria-hidden="true"></td>
  <td class="spinner-border ms-auto" role="status" aria-hidden="true"></td>
  <td>
    <button class="btn btn-primary" type="button" disabled>
      <span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span>
      <span class="visually-hidden">Loading...</span>
    </button>
  </td>
</tr>
</tbody>
</table>`