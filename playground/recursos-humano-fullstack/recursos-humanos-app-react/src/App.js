import ListadoEmpleados from './empleados/ListadoEmpleados';
import AgregarEmpleado from './empleados/AgregarEmpleado';
import EditarEmpleado from './empleados/EditarEmpleado';
import { BrowserRouter, Routes, Route } from 'react-router-dom';
import Navbar from './templates/Navbar';

function App() {
  return (
    <div className="container">
      <BrowserRouter>
        <Navbar />
        <Routes>
          <Route exact path='/' element={<ListadoEmpleados />} />
          <Route exact path='/agregar' element={<AgregarEmpleado />} />
          <Route exact path='/editar/:id' element={<EditarEmpleado />} />
        </Routes>
      </BrowserRouter>
    </div>
  );
}

export default App;
