import { HttpClient } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Producto } from './producto';

@Injectable({
    providedIn: 'root'
})
export class ProductoService {

    private urlBase = 'http://localhost:8080/inventario-app';
    private clientHttp = inject(HttpClient); // Inyectando la dependencia de httpClient

    getListProducts(): Observable<Producto[]> {
        return this.clientHttp.get<Producto[]>(`${this.urlBase}/getProductos`);
    }

    saveProduct(producto: Producto): Observable<Object> {
        return this.clientHttp.post(`${this.urlBase}/agregarProducto`, producto);
    }

    getProductById(id: number) {
        return this.clientHttp.get(`${this.urlBase}/getProducto/${id}`);
    }

    editProduct(id: number, producto: Producto): Observable<Object> {
        return this.clientHttp.put(`${this.urlBase}/editarProducto/${id}`, producto);
    }

    deleteProduct(id: number): Observable<Object> {
        return this.clientHttp.delete(`${this.urlBase}/eliminarProducto/${id}`)
    }
}
