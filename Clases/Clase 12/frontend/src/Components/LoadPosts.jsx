import React, { useState, useEffect, Fragment } from 'react';
import { Modal, Button } from 'react-bootstrap';
import { useCookies } from 'react-cookie';
import './Styles/Login.css';
import Swal from 'sweetalert2';
import { useNavigate } from "react-router-dom";
import { Link } from "react-router-dom";

export const LoadPosts = () => {
    // Se declaran los estados iniciales
    const [posts, setPosts] = useState([]);
    const [fileContent, setFileContent] = useState(null);
    const [cookies, setCookie, removeCookie] = useCookies(['student']);
    const navigate = useNavigate();

    // Este método se encarga de ejecutarse cuando la página se termine de renderizar o cargar en otras palabras
    // También se ejecuta cuando el estado validarEliminacion tiene algún cambio, es por eso que de último entre los 
    // corchetes se pone ese estado, para que monitoree si ese estado tiene un cambio de valor
    useEffect(() => {
        // Nos comunicamos con el backend en el endpoint especificado
        fetch(`http://localhost:5000/api/getPosts`, {
            method: "GET",
        })
            .then((response) => response.json())
            .then((res) => {
                // Imprimimos la respuesta
                console.log(res)
                // Guardamos en un estado la respuesta que en este caso es un array de objetos json
                setPosts(res);
            })
            .catch((error) => console.error(error));
    }, []);

    // Este método se encarga de hacer logout
    const handleLogout = () => {
        removeCookie('student');
        navigate('/login')
    };

    const handleFileUpload = (event) => {
        event.preventDefault();
        const file = event.target.files[0];
        const reader = new FileReader();

        reader.onload = async (e) => {
            try {
                const jsonContent = JSON.parse(e.target.result);
                setFileContent(jsonContent);
                console.log("Archivo JSON cargado:", jsonContent);
                await fetch('http://localhost:5000/api/loadPosts', {
                    method: 'POST',
                    headers: {
                        'Content-Type': 'application/json',
                    },
                    body: JSON.stringify(jsonContent),
                })
                    .then(response => response.json())
                    .then(res => {
                        alert(res.response);
                    })
                    .catch(error => console.error("Error al enviar las publicaciones:", error));
            } catch (error) {
                console.error("Error al parsear el JSON:", error);
            }
        };

        reader.onerror = (e) => {
            console.error("Error leyendo el archivo:", e.target.error);
        };

        if (file) {
            reader.readAsText(file);
        }
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
                    {/* <input type="file" accept=".json" onChange={handleFileUpload} className="btn btn-outline-warning" style={{ marginRight: "5%" }} /> */}
                    <div style={{ display: "flex", justifyContent: "center", alignContent: "center", marginTop: "5%", flexDirection: "column", marginBottom: "5%", marginRight: "5%" }}>
                        <label htmlFor="file-upload2" className="btn btn-outline-warning" style={{ fontSize: "19px", width: "100%", height: "auto", display: "flex", alignItems: "center", justifyContent: "center" }}>
                            <svg xmlns="http://www.w3.org/2000/svg" width="20" height="20" fill="currentColor" className="bi bi-filetype-json" viewBox="0 0 16 16" style={{ marginRight: "2%" }}>
                                <path fillRule="evenodd" d="M14 4.5V11h-1V4.5h-2A1.5 1.5 0 0 1 9.5 3V1H4a1 1 0 0 0-1 1v9H2V2a2 2 0 0 1 2-2h5.5zM4.151 15.29a1.2 1.2 0 0 1-.111-.449h.764a.58.58 0 0 0 .255.384q.105.073.25.114.142.041.319.041.245 0 .413-.07a.56.56 0 0 0 .255-.193.5.5 0 0 0 .084-.29.39.39 0 0 0-.152-.326q-.152-.12-.463-.193l-.618-.143a1.7 1.7 0 0 1-.539-.214 1 1 0 0 1-.352-.367 1.1 1.1 0 0 1-.123-.524q0-.366.19-.639.192-.272.528-.422.337-.15.777-.149.456 0 .779.152.326.153.5.41.18.255.2.566h-.75a.56.56 0 0 0-.12-.258.6.6 0 0 0-.246-.181.9.9 0 0 0-.37-.068q-.324 0-.512.152a.47.47 0 0 0-.185.384q0 .18.144.3a1 1 0 0 0 .404.175l.621.143q.326.075.566.211a1 1 0 0 1 .375.358q.135.222.135.56 0 .37-.188.656a1.2 1.2 0 0 1-.539.439q-.351.158-.858.158-.381 0-.665-.09a1.4 1.4 0 0 1-.478-.252 1.1 1.1 0 0 1-.29-.375m-3.104-.033a1.3 1.3 0 0 1-.082-.466h.764a.6.6 0 0 0 .074.27.5.5 0 0 0 .454.246q.285 0 .422-.164.137-.165.137-.466v-2.745h.791v2.725q0 .66-.357 1.005-.355.345-.985.345a1.6 1.6 0 0 1-.568-.094 1.15 1.15 0 0 1-.407-.266 1.1 1.1 0 0 1-.243-.39m9.091-1.585v.522q0 .384-.117.641a.86.86 0 0 1-.322.387.9.9 0 0 1-.47.126.9.9 0 0 1-.47-.126.87.87 0 0 1-.32-.387 1.55 1.55 0 0 1-.117-.641v-.522q0-.386.117-.641a.87.87 0 0 1 .32-.387.87.87 0 0 1 .47-.129q.265 0 .47.129a.86.86 0 0 1 .322.387q.117.255.117.641m.803.519v-.513q0-.565-.205-.973a1.46 1.46 0 0 0-.59-.63q-.38-.22-.916-.22-.534 0-.92.22a1.44 1.44 0 0 0-.589.628q-.205.407-.205.975v.513q0 .562.205.973.205.407.589.626.386.217.92.217.536 0 .917-.217.384-.22.589-.626.204-.41.205-.973m1.29-.935v2.675h-.746v-3.999h.662l1.752 2.66h.032v-2.66h.75v4h-.656l-1.761-2.676z" />
                            </svg>
                            {" Import"}
                        </label>
                        <input onChange={handleFileUpload} id="file-upload2" type="file" accept=".json" style={{ display: "none" }} />
                    </div>
                </div>
            </div>
            <div style={{ display: "flex", alignItems: "center", height: "90vh", width: "100%", top: "10", overflowY: "auto", height: "auto", marginTop: "5%" }}>
                <div className="table-container" >
                    <table className="table table-bordered text-center">
                        <thead className="table-dark">
                            <tr>
                                <th>Nombre</th>
                                <th>Descripción</th>
                                <th>Imagen</th>
                            </tr>
                        </thead>
                        <tbody>
                            {posts.map((post, index) => (
                                <tr key={index}>
                                    <td>{post.name}</td>
                                    <td>{post.description}</td>
                                    <td><img src={post.image} alt="Post" style={{ maxWidth: "25vh" }} /></td>
                                </tr>
                            ))}
                        </tbody>
                    </table>
                </div>
            </div>
        </Fragment>
    )
}