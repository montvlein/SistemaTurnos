let verOdontologos = async function() {
    let res = await listarOdontologos();
    res.then(lista => {
        console.log(lista)
        let cargarFilaOdontologo = "";
            for (let odontologo of lista){
            cargarFilaOdontologo += `<tr>
                                            <th scope="row">${odontologo.id}</th>
                                            <td>${odontologo.nombre}</td>
                                            <td>${odontologo.apellido}</td>
                                            <td>${odontologo.matricula}</td>
                                      </tr>`
            }
            return `<table class="table table-hover">
              <thead>
              <tr>
                <th scope="col">Id</th>
                <th scope="col">Nombre</th>
                <th scope="col">Apellido</th>
                <th scope="col">Matrícula</th>
                <th scope="col">Acción</th>
              </tr>
              </thead>
              <tbody>
              ${cargarFilaOdontologo}
              </tbody>
            </table>`
    })
}


