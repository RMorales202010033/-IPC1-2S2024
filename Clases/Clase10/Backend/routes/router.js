const express = require('express')
const router = express.Router()

// Importacion de los controladores (Acciones que se van a realizar)
const {
    getSaludo,
    getStudents,
    getStudentByCarnet,
    createStudent,
    login,
    updateStudent,
    deleteStudent
} = require('../controllers/consultas')

// Endpoints con sus tipos de peticiones
router.get('/api', getSaludo)
router.get('/api/students', getStudents)
router.get('/api/students/:carnet', getStudentByCarnet)
router.post('/api/students', createStudent)
router.post('/api/login', login)
router.put('/api/students/:carnet', updateStudent)
router.delete('/api/students/:carnet', deleteStudent)

module.exports = router;