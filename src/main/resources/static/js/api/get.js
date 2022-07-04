async function listarPacientes() {
    return fetch("v1/paciente/all")
    .then(response => response.json())
    .then(data => {
        console.log(data)
        return data
        })
}

async function listarOdontologos() {
    return fetch("v1/odontologo/all")
    .then(response => response.json())
    .then(data => {
        console.log(data)
        return data
        })
}

async function listarTurnos() {
    return fetch("v1/turno/all")
    .then(response => response.json())
    .then(data => {
        console.log(data)
        return data
        })
}