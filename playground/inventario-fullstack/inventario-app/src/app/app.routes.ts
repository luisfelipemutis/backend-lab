import { Routes } from '@angular/router';
import { ProductoListaComponent } from './producto-lista/producto-lista.component';
import { AgregarProductoComponent } from './agregar-producto/agregar-producto.component';
import { EditarProductoComponent } from './editar-producto/editar-producto.component';

// responde http://localhost:4200/productos
export const routes: Routes = [

    // Rutas que manejan los diferentes componentes de la aplicación
    {
        path: 'productos', component: ProductoListaComponent
    },
    {
        // Redirige la ruta raíz a /productos
        // rutas: http://localhost:4200/ or http://localhost:4200 redirigen a /productos
        path: '', redirectTo: 'productos', pathMatch: 'full'
    },
    {
        path: 'agregar-producto', component: AgregarProductoComponent
    },
    {
        path: 'editar-producto/:id', component: EditarProductoComponent
    }
];
