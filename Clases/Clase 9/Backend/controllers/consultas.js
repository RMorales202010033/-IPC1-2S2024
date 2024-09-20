// Base datos de ejemplo
let dataStudents = [];

async function getSaludo(req, res) {
    try {
        let saludo = {
            response: "Hola mundo!"
        };
        return res.status(200).json(saludo);
    } catch (err) {
        console.log('Error al retornar el saludo');
        return res.status(500).json({response: 'Error interno del servidor.'})
    }
}

async function getStudents(req, res) {
    try {
        return res.status(200).json(dataStudents);
    } catch (err) {
        console.log('Error al retornar el saludo');
        return res.status(500).json({response: 'Error interno del servidor.'})
    }
}

async function createStudent(req, res) {
    try {
        // Guardamos el cuerpo de la peticion
        const newStudent = req.body;
        // Agregamos el estudiante a la lista
        dataStudents.push(newStudent);
        // Brindamos un mensaje de confirmacion exitoso
        return res.status(201).json({response: "Estudiante creado exitosamente!"});
    } catch (err) {
        console.log('Error al retornar el saludo');
        return res.status(500).json({response: 'Error interno del servidor.'})
    }
}

module.exports = {
    getSaludo,
    getStudents,
    createStudent
}