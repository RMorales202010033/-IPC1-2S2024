// Base datos de ejemplo
let dataStudents = [];
let dataPosts = [];
let cantidadPosts = []

// Dependencias a utilizar
const fs = require('fs')

// Variables
const FILENAME = './DB/Usuarios.json';
const FILENAME2 = './DB/Posts.json';
const FILENAME3 = './DB/CantidadPosts.json';

// Verficiar y crear el archivo si no existe
if (!fs.existsSync(FILENAME)) {
    fs.writeFileSync(FILENAME, JSON.stringify(dataStudents));
} else {
    // Si el archivo ya existe, cargar los datos
    const fileData = fs.readFileSync(FILENAME, 'utf8');
    dataStudents = JSON.parse(fileData);
}

if (!fs.existsSync(FILENAME2)) {
    fs.writeFileSync(FILENAME2, JSON.stringify(dataPosts));
} else {
    // Si el archivo ya existe, cargar los datos
    const fileData = fs.readFileSync(FILENAME2, 'utf8');
    dataPosts = JSON.parse(fileData);
}

if (!fs.existsSync(FILENAME3)) {
    fs.writeFileSync(FILENAME3, JSON.stringify(cantidadPosts));
} else {
    // Si el archivo ya existe, cargar los datos
    const fileData = fs.readFileSync(FILENAME3, 'utf8');
    cantidadPosts = JSON.parse(fileData);
}

async function updateDataFile() {
    fs.writeFileSync(FILENAME, JSON.stringify(dataStudents));
}

async function updateDataFile2() {
    fs.writeFileSync(FILENAME2, JSON.stringify(dataPosts));
}

async function updateDataFile3() {
    fs.writeFileSync(FILENAME3, JSON.stringify(cantidadPosts));
}

async function getSaludo(req, res) {
    try {
        let saludo = {
            response: "Hola mundo!"
        };
        return res.status(200).json(saludo);
    } catch (err) {
        console.log('Error al retornar el saludo');
        return res.status(500).json({ response: 'Error interno del servidor.' })
    }
}

async function getStudents(req, res) {
    try {
        return res.status(200).json(dataStudents);
    } catch (err) {
        console.log('Error al retornar el saludo');
        return res.status(500).json({ response: 'Error interno del servidor.' })
    }
}

async function getStudentByCarnet(req, res) {
    try {
        const carnet = req.params.carnet; // retorna el carnet como string
        // Buscamos en la lista si existe algun objeto json con ese carnet, si no existe nos retorna null = false
        // y si encuetra algun ojbeto con ese carnet nos retorna el objeto
        const student = dataStudents.find(student => student.Carnet === carnet);
        if (!student) {
            // Si no se encuentra retornamos este mensaje
            return res.status(404).json({ response: 'Usuario no encontrado.' })
        } else {
            return res.status(200).json(student);
        }
    } catch (err) {
        console.log('Error al retornar el usuario');
        return res.status(500).json({ response: 'Error interno del servidor.' })
    }
}

async function getPosts(req, res) {
    try {
        return res.status(200).json(dataPosts);
    } catch (err) {
        console.log('Error al retornar el saludo');
        return res.status(500).json({ response: 'Error interno del servidor.' })
    }
}

async function getCantidadPosts(req, res) {
    try {
        return res.status(200).json(cantidadPosts);
    } catch (err) {
        console.log('Error al retornar el saludo');
        return res.status(500).json({ response: 'Error interno del servidor.' })
    }
}

async function createStudent(req, res) {
    try {
        // Guardamos el cuerpo de la peticion
        const newStudent = req.body;
        // Agregamos el estudiante a la lista
        dataStudents.push(newStudent);
        updateDataFile();
        // Brindamos un mensaje de confirmacion exitoso
        return res.status(201).json({ response: "Estudiante creado exitosamente!" });
    } catch (err) {
        console.log('Error al retornar el saludo');
        return res.status(500).json({ response: 'Error interno del servidor.' })
    }
}

async function createPost(req, res) {
    try {
        // Guardamos el cuerpo de la peticion
        const newPost = req.body;
        const carnet = newPost.carnet;
        // Agregamos el estudiante a la lista
        dataPosts.push(newPost);
        updateDataFile2();

        const usuario = cantidadPosts.find(p => p.carnet === carnet);
        if (usuario) {
            usuario.posts += 1;
        } else {
            cantidadPosts.push({ carnet: carnet, posts: 1 });
        }
        updateDataFile3();

        // Brindamos un mensaje de confirmacion exitoso
        return res.status(201).json({ response: "Publicación creada exitosamente!" });
    } catch (err) {
        console.log('Error al retornar el saludo');
        return res.status(500).json({ response: 'Error interno del servidor.' })
    }
}

async function loadPosts(req, res) {
    try {
        // Guardamos el cuerpo de la peticion
        const newPost = req.body;
        // console.log(newPost)
        // Agregamos el estudiante a la lista
        newPost.forEach(post => {
            dataPosts.push(post);
            // console.log(post)
        });
        updateDataFile2();
        // Brindamos un mensaje de confirmacion exitoso
        return res.status(201).json({ response: "Publicaciones cargadas exitosamente!" });
    } catch (err) {
        console.log('Error al retornar el saludo');
        return res.status(500).json({ response: 'Error interno del servidor.' })
    }
}

async function login(req, res) {
    try {
        const data = req.body;
        console.log(data);

        const student = dataStudents.find(student => {
            console.log(student.Carnet);
            console.log(student.Password);
            if (student.Carnet === data.Carnet && student.Password === data.Password) {
                return student;
            }
        });
        if (!student) {
            // Si no se encuentra retornamos este mensaje
            const response = {
                success: false,
                user: null
            }
            return res.status(404).json({ response: response })
        } else {
            const response = {
                success: true,
                user: student
            }
            return res.status(200).json(response);
        }
    } catch (err) {
        console.log('Error al retornar el saludo');
        return res.status(500).json({ response: 'Error interno del servidor.' })
    }
}

async function updateStudent(req, res) {
    try {
        // Obtenemos el carnet del objeto que se va a actualizar
        const carnet = req.params.carnet; // retorna el carnet como string
        // Obtenemos el cuerpo de la peticion en el cual vienen los nuevos valores
        const updatedStudent = req.body;
        // Buscamos en la lista si existe algun objeto json con ese carnet, si no existe nos retorna -1
        // y si encuetra algun ojbeto con ese carnet nos retorna el indice donde se encuentra
        const index = dataStudents.findIndex(student => student.Carnet === carnet);
        if (index === - 1) {
            // Si no se encuentra retornamos este mensaje
            return res.status(404).json({ response: 'Usuario no encontrado.' })
        } else {
            // Si se encuentra el objeto, editamos sus atributos haciendo uso del indice
            dataStudents[index].Nombre = updatedStudent.Nombre;
            dataStudents[index].Apellido = updatedStudent.Apellido;
            dataStudents[index].Edad = updatedStudent.Edad;
            dataStudents[index].Password = updatedStudent.Password;
            updateDataFile();
            return res.status(200).json({ response: "Usuario actualizado correctamente.", dataUser: dataStudents[index] });
        }
    } catch (err) {
        console.log('Error al retornar el usuario');
        return res.status(500).json({ response: 'Error interno del servidor.' })
    }
}

async function deleteStudent(req, res) {
    try {
        // Obtenemos el carnet del objeto que se va a actualizar
        const carnet = req.params.carnet; // retorna el carnet como string
        // Buscamos en la lista si existe algun objeto json con ese carnet, si no existe nos retorna -1
        // y si encuetra algun ojbeto con ese carnet nos retorna el indice donde se encuentra
        const index = dataStudents.findIndex(student => student.Carnet === carnet);
        if (index === - 1) {
            // Si no se encuentra retornamos este mensaje
            return res.status(404).json({ response: 'Usuario no encontrado.' })
        } else {
            // Eliminamos el usuario
            dataStudents.splice(index, 1);
            updateDataFile();
            return res.status(200).json({ response: "Usuario eliminado correctamente." });
        }
    } catch (err) {
        console.log('Error al retornar el usuario');
        return res.status(500).json({ response: 'Error interno del servidor.' })
    }
}

module.exports = {
    getSaludo,
    getStudents,
    getStudentByCarnet,
    getPosts,
    getCantidadPosts,
    createStudent,
    createPost,
    loadPosts,
    login,
    updateStudent,
    deleteStudent
}