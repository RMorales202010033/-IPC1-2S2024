import { BrowserRouter, Routes, Route, Navigate } from 'react-router-dom';
import Login from '../Login';
import { Admin } from '../Admin';
import { LoadPosts } from '../LoadPosts';
import { Graficos } from '../Graficos';
import Usuario from '../Usuario';
import CreatePost from '../CreatePost';
import Profile from '../Profile';

function Router() {
    return (
        <BrowserRouter>
            <Routes>
                {/* Creación de las distintas rutas que se tendrán (urls) y el componente que se llamará */}
                <Route path='/login' element={<Login />} />
                <Route path='/admin' element={<Admin />} />
                <Route path='/loadPosts' element={<LoadPosts />} />
                <Route path='/graphs' element={<Graficos />} />
                <Route path='/user' element={<Usuario />} />
                <Route path='/createPost' element={<CreatePost />} />
                <Route path='/profile' element={<Profile />} />
                <Route path='/*' element={<Navigate to="/login" />} />
                <Route path='/' element={<Navigate to="/login" />} />
            </Routes>
        </BrowserRouter>
    );
}

export default Router;