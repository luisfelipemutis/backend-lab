import { Component, inject } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { Producto } from '../producto';
import { ProductoService } from '../producto.service';

@Component({
  imports: [FormsModule],
  selector: 'app-agregar-producto',
  templateUrl: './agregar-producto.component.html',
})
export class AgregarProductoComponent {

  producto: Producto = new Producto();

  private productoService = inject(ProductoService);
  private enrutador = inject(Router);

  onSubmit() {
    this.saveProduct();
  };

  private saveProduct() {
    this.productoService.saveProduct(this.producto).subscribe(
      {
        next: (data) => {
          this.redirectListProduct();
        },
        error: (err) => {
          console.error('Error al guardar el producto:', err);
        }
      }
    );
  }

  private redirectListProduct() {
    // Redirigir al inicio después de guardar el producto
    this.enrutador.navigate(['/']);
  }
}
