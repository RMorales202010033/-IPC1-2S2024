// Importando el framework Express y CORS
const express = require('express')
const cors = require('cors')
const bodyParser = require('body-parser')

// Instanceando objeto express
const app = express()

// Usando middlewares
app.use(express.json())
app.use(bodyParser.json({ limit: '15mb'}))
app.use(cors())

// Importacion de las rutas (Endpoints)
const rutas = require('./routes/router.js')
app.use(rutas)

module.exports = app;