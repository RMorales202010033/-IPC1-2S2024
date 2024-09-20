const express = require('express')
const router = express.Router()

// Importacion de los controladores (Acciones que se van a realizar)
const {
    getSaludo,
    getStudents,
    createStudent
} = require('../controllers/consultas')

// Endpoints con sus tipos de peticiones
router.get('/api', getSaludo)
router.get('/api/students', getStudents)
router.post('/api/students', createStudent)

module.exports = router;