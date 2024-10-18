import React, { useState, useEffect, Fragment } from 'react';
import { useCookies } from 'react-cookie';
import './Styles/Login.css';
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";
import ReporteBar from './Barchar';

export const Graficos = () => {
    // Se declaran los estados iniciales
    const [cantidadPosts, setCantidadPosts] = useState([]);
    const [cookies, setCookie, removeCookie] = useCookies(['student']);
    const navigate = useNavigate();

    // Este método se encarga de ejecutarse cuando la página se termine de renderizar o cargar en otras palabras
    // También se ejecuta cuando el estado validarEliminacion tiene algún cambio, es por eso que de último entre los 
    // corchetes se pone ese estado, para que monitoree si ese estado tiene un cambio de valor
    useEffect(() => {
        // Nos comunicamos con el backend en el endpoint especificado
        fetch(`http://localhost:5000/api/getCantidadPosts`, {
            method: "GET",
        })
            .then((response) => response.json())
            .then((res) => {
                // Imprimimos la respuesta
                console.log(res)
                // Guardamos en un estado la respuesta que en este caso es un array de objetos json
                const publicacionesOrdenadas = [...res].sort((a, b) => b.posts - a.posts);
                setCantidadPosts(publicacionesOrdenadas);
                console.log(publicacionesOrdenadas);
            })
            .catch((error) => console.error(error));
    }, []);

    // Este método se encarga de hacer logout
    const handleLogout = () => {
        removeCookie('student');
        navigate('/login')
    };

    return (
        <Fragment>
            <div style={{ display: "flex", alignItems: "center", height: "10vh", width: "100%", top: "0", backgroundColor: "#212529" }}>
                <div style={{ display: "flex", alignItems: "center", height: "10vh", width: "50%", top: 0, paddingLeft: "5%" }}>
                    <ul style={{ listStyleType: "none", display: "flex", padding: 0, height: "100%", alignItems: "center", margin: "0px" }}>
                        <li style={{ color: "white", marginRight: "35px" }}>
                            {/* El link nos ayuda a navegar entre componentes, parecido al navigate */}
                            <Link style={{ color: "white", textDecoration: "none" }} to="/admin">
                                Lista de usuarios
                            </Link>
                        </li>
                        <li style={{ color: "white", marginRight: "35px" }}>
                            <Link style={{ color: "white", textDecoration: "none" }} to="/loadPosts">
                                Lista de publicaciones
                            </Link>
                        </li>
                        <li style={{ color: "white" }}>
                            <Link style={{ color: "white", textDecoration: "none" }} to="/graphs">
                                Gráficas
                            </Link>
                        </li>
                    </ul>
                </div>
                <div style={{ display: "flex", alignItems: "center", height: "10vh", width: "50%", top: 0, flexDirection: "row-reverse", paddingRight: "5%" }}>
                    <button className="btn btn-outline-info" onClick={handleLogout}>
                        Logout
                    </button>
                </div>
            </div>
            <div style={{ display: "flex", alignItems: "center", height: "90vh", width: "100%", top: "10", justifyContent:"center" }}>
                <ReporteBar BarData={cantidadPosts}/>
            </div>
        </Fragment>
    )
}