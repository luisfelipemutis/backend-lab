import React, { useState, useEffect } from 'react'
import axios from 'axios'
import { NumericFormat } from 'react-number-format';
import { Link } from 'react-router-dom';

export default function ListadoEmpleados() {

    const urlBase = "http://localhost:8080/rh-app/empleados";

    const [empleados, setEmpleados] = useState([]);

    // Se coloca [] para que el useEffect se ejecute solo una vez al montar el componente.
    useEffect(() => {
        loadEmployees();
    }, []);

    const loadEmployees = async () => {
        const response = await axios.get(urlBase);
        console.log("Resultado de la consulta a la API(obtener empleados): ");
        console.log(response.data);
        setEmpleados(response.data);
    }

    const deleteEmployee = async (id) => {
        await axios.delete(`${urlBase}/${id}`);
        loadEmployees(); // Recarga la lista de empleados después de eliminar uno
    }

    return (
        <div className="container">
            <div className="container text-center" style={{ margin: '30px' }}>
                <h3>Sistema de Recursos Humanos</h3>
            </div>

            {/* Tabla empleados */}
            <table className="table table-striped table-hover align-middle table-bordered">
                <thead className="table-dark text-center">
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Empleado</th>
                        <th scope="col">Departamento</th>
                        <th scope="col">Sueldo</th>
                        <th scope="col">Acciones</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        // Se itera sobre el arreglo de empleados
                        empleados.map((employee, index) => (
                            <tr key={index}>
                                <th scope="row">{employee.id}</th>
                                <td>{employee.nombre}</td>
                                <td>{employee.departamento}</td>
                                <td>
                                    <NumericFormat value={employee.sueldo} displayType={'text'} thousandSeparator=',' prefix={'$'}
                                        decimalScale={2} fixedDecimalScale />
                                </td>
                                <td className="text-center">
                                    <div>
                                        <Link to={`/editar/${employee.id}`} className="btn btn-warning btn-sm ms-3">Editar</Link>
                                        <button className="btn btn-danger btn-sm ms-3" onClick={() => deleteEmployee(employee.id)}>Eliminar</button>
                                    </div>
                                </td>
                            </tr>
                        ))
                    }
                </tbody>
            </table>
        </div>
    )
}
