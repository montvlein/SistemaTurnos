async function crearPaciente(paciente) {
    return fetch("v1/paciente", {method:'POST', headers: {'Content-Type':'application/json'}, body: JSON.stringify(paciente)})
    .then(response => response.json())
    .then(data => {
        return data
        })
}

async function crearOdontologo(odontologo) {
    return fetch("v1/odontologo",
        {method:'POST',
        headers: {'Content-Type':'application/json'},
        body: JSON.stringify(odontologo)})
    .then(response => response.json())
    .then(data => {
        return data
        })
}

async function crearTurno(turno) {
    return fetch("v1/turno",
        {method:'POST',
        headers: {'Content-Type':'application/json'},
        body: JSON.stringify(turno)})
    .then(response => response.json())
    .then(data => {
        return data
        })
}
