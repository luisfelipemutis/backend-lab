import React, { useState } from 'react'
import axios from 'axios';
import { useNavigate } from 'react-router-dom';

export default function AgregarEmpleado() {

    let navigation = useNavigate();

    const [empleado, setEmpleado] = useState({
        nombre: '',
        departamento: '',
        sueldo: ''
    });

    const { nombre, departamento, sueldo } = empleado;

    const onInputChange = (e) => {
        // Actualiza el estado del empleado cuando cambia un campo del formulario
        // Spread operator para mantener los valores existentes y actualizar solo el campo que cambió
        setEmpleado({ ...empleado, [e.target.name]: e.target.value });
    };

    const onSubmit = async (e) => {
        // Evita que los parametros se envien en la url 
        e.preventDefault();
        const urlBase = 'http://localhost:8080/rh-app/empleados';
        await axios.post(urlBase, empleado);
        navigation('/'); // Redirige a la página principal después de agregar el empleado
    }

    return (
        <div className="container">
            <div className="container text-center" style={{ margin: '30px' }}>
                <h3>Agregar Empleado</h3>
            </div>
            <form onSubmit={(e) => onSubmit(e)}>
                <div className="mb-3">
                    <label htmlFor="nombre" className="form-label">Nombre Empleado</label>
                    <input type="text" className="form-control" id="nombre"
                        name="nombre" required
                        value={nombre}
                        onChange={(e) => onInputChange(e)}
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="departamento" className="form-label">Departamento</label>
                    <input type="text" className="form-control" id="departamento" name="departamento"
                        value={departamento}
                        onChange={(e) => onInputChange(e)}
                    />
                </div>
                <div className="mb-3">
                    <label htmlFor="sueldo" className="form-label">Sueldo</label>
                    <input type="number" step="any" className="form-control" id="sueldo" name="sueldo"
                        value={sueldo}
                        onChange={(e) => onInputChange(e)}
                    />
                </div>

                <div className="text-center">
                    <button type="submit" className="btn btn-warning btn-sm me-3">Agregar</button>
                    <a href="/" className="btn btn-danger btn-sm">Cancelar</a>
                </div>

            </form>
        </div>
    )
}