import React, { useState, useEffect, Fragment } from 'react';
import './Styles/Login.css';
import { useCookies } from 'react-cookie';
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";
import profileImage from './Images/profile.png';

function Profile() {
    // Se declaran los estados iniciales
    const [carnet, setCarnet] = useState("");
    const [nombre, setNombre] = useState("");
    const [apellido, setApellido] = useState("");
    const [edad, setEdad] = useState(25);
    const [password, setPassword] = useState("");

    // Se declaran las cookies para acceder y removerlas en este caso
    const [cookies, setCookie, removeCookie] = useCookies(['student']);
    const navigate = useNavigate();

    useEffect(() => {
        const dataUser = cookies.student;
        setCarnet(dataUser.Carnet);
        setNombre(dataUser.Nombre)
        setApellido(dataUser.Apellido)
        setEdad(dataUser.Edad)
        setPassword(dataUser.Password)
    }, [])


    // Este método se encarga de hacer logout
    const handleLogout = () => {
        removeCookie('student');
        navigate('/login')

    };

    const handleUpdate = () => {
        // Creamos el objeto que se va a mandar
        const data = {
            Nombre: nombre,
            Apellido: apellido,
            Edad: edad,
            Password: password
        }
        // console.log(data)
        // Este método se encarga de comunicarse con el backend con un endpoint específico, en este caso /createPost
        fetch(`http://localhost:5000/api/students/${carnet}`, {
            // Se especifica el tipo de método
            method: "PUT",
            // Se parsea a json el cuerpo que se mandará
            body: JSON.stringify(data),
            // Se agregan los encabezados
            headers: {
                "Content-Type": "application/json",
            },
        })
            // Se obtiene la respuesta y se pasa a json
            .then((response) => response.json())
            // Una vez se tiene la respuesta en json se realizará lo siguiente
            .then((res) => {
                // Imprimimos en consola la respuesta
                console.log(res)
                alert(res.response)
                // console.log(res.dataUser)
                setCookie('student', res.dataUser);
            })
            .catch((error) => console.error(error));
    };

    return (
        <Fragment>
            <div style={{ display: "flex", alignItems: "center", height: "10vh", width: "100%", top: "0", backgroundColor: "#212529" }}>
                <div style={{ display: "flex", alignItems: "center", height: "10vh", width: "50%", top: "0", paddingLeft: "5%" }}>
                    <ul style={{ listStyleType: "none", display: "flex", padding: 0, height: "100%", alignItems: "center", margin: "0px" }}>
                        <li style={{ color: "white", marginRight: "35px" }}>
                            {/* El link nos ayuda a navegar entre rutas, igual al navigate */}
                            <Link style={{ color: "white", textDecoration: "none" }} to="/user">
                                Home
                            </Link>
                        </li>
                        <li style={{ color: "white", marginRight: "35px" }}>
                            <Link style={{ color: "white", textDecoration: "none" }} to="/createPost">
                                Create post
                            </Link>
                        </li>
                        <li style={{ color: "white" }}>
                            <Link style={{ color: "white", textDecoration: "none" }} to="/profile">
                                Profile
                            </Link>
                        </li>
                    </ul>
                </div>
                <div style={{ display: "flex", alignItems: "center", height: "10vh", width: "50%", top: "0", flexDirection: "row-reverse", paddingRight: "5%" }}>
                    <button className="btn btn-outline-info" onClick={handleLogout}>
                        Logout
                    </button>
                </div>
            </div>
            <div style={{ display: "flex", alignItems: "center", height: "90vh", width: "100%", top: "10", paddingBottom: "5%", paddingTop: "5%" }}>
                <div className="container-fluid h-100" style={{ marginRight: "20%", marginLeft: "20%", borderRadius: "25px", backgroundColor: "#172B6B", display: "flex", padding: "5%", color: "white", flexDirection: "column", overflowY: "auto", alignItems: "center" }}>
                    <img src={profileImage} alt="Profile" style={{ maxWidth: "30%" }} />
                    <div style={{ display: "flex", alignItems: "center", justifyContent: "left", width: "100%", marginTop: "5%" }}>
                        <h2>Carnet:</h2>
                    </div>
                    <div className="input-group mb-3">
                        <input type="text" className="form-control" placeholder="Carnet" aria-label="Nombre" aria-describedby="basic-addon1" value={carnet} readOnly />
                    </div>
                    <div style={{ display: "flex", alignItems: "center", justifyContent: "left", width: "100%", marginTop: "5%" }}>
                        <h2>Nombre:</h2>
                    </div>
                    <div className="input-group mb-3">
                        <input type="text" className="form-control" placeholder="Nombre" aria-label="Apellido" aria-describedby="basic-addon1" value={nombre} onChange={(e) => setNombre(e.target.value)} />
                    </div>
                    <div style={{ display: "flex", alignItems: "center", justifyContent: "left", width: "100%", marginTop: "5%" }}>
                        <h2>Apellido:</h2>
                    </div>
                    <div className="input-group mb-3">
                        <input type="text" className="form-control" placeholder="Apellido" aria-label="Edad" aria-describedby="basic-addon1" value={apellido} onChange={(e) => setApellido(e.target.value)} />
                    </div>
                    <div style={{ display: "flex", alignItems: "center", justifyContent: "left", width: "100%", marginTop: "5%" }}>
                        <h2>Edad:</h2>
                    </div>
                    <div className="input-group mb-3">
                        <input type="text" className="form-control" placeholder="Edad" aria-label="Carnet" aria-describedby="basic-addon1" value={edad} onChange={(e) => setEdad(e.target.value)} />
                    </div>
                    <button type="button" className="btn btn-success btn-lg" style={{marginTop: "3%"}} onClick={handleUpdate}>Update</button>
                </div>
            </div>
        </Fragment>
    );
}

export default Profile;