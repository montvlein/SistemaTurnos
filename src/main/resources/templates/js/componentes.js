const title = document.querySelector("#SeccionTitulo")
const accion = document.querySelector("#SeccionAccion")
const contenido = document.querySelector("#SeccionContenido")
const btnMenuCrearOdontologo = document.querySelector("#btnMenuCrearOdontologo")
const btnMenuListarOdontologos = document.querySelector("#btnMenuListarOdontologos")
const btnMenuCrearPaciente = document.querySelector("#btnMenuCrearPaciente")
const btnMenuListarPacientes = document.querySelector("#btnMenuListarPacientes")
const btnMenuCrearTurno = document.querySelector("#btnMenuCrearTurno")
const btnMenuListarTurnos = document.querySelector("#btnMenuListarTurnos")
const Menu = [
{titulo:'Odontologo',accion:btnMenuCrearOdontologo, contenido:'crear Odontologo'},
{titulo:'Paciente',accion:btnMenuCrearPaciente, contenido:'crear paciente'},
{titulo:'Turno',accion:btnMenuCrearTurno, contenido:crearTurno},
{titulo:'Odontologo',accion:btnMenuListarOdontologos, contenido:'ver odontologos'},
{titulo:'Paciente',accion:btnMenuListarPacientes, contenido:'ver pacientes'},
{titulo:'Turno',accion:btnMenuListarTurnos, contenido:verTurnos}
]