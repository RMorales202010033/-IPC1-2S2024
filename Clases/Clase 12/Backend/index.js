// Importacion del modulo app
const app = require('./app.js');

// Puerto en el que se va a levantar el servidor
const port = 5000;

// Levantar el servidor, escuchando el puerto especificado
app.listen(port, () => {
    console.log(`Servidor escuchando en el puerto: ${port}`)
})