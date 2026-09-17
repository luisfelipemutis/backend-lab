import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Producto } from '../producto';
import { ProductoService } from '../producto.service';
import { FormsModule } from '@angular/forms';

@Component({
  imports: [FormsModule],
  selector: 'app-editar-producto',
  templateUrl: './editar-producto.component.html',
})
export class EditarProductoComponent {

  producto: Producto = new Producto();
  id!: number;
  private changeDetector = inject(ChangeDetectorRef);

  private productoService = inject(ProductoService);
  private ruta = inject(ActivatedRoute);
  private enrutador = inject(Router);

  ngOnInit() {
    // Obtiene el id de la url
    this.id = this.ruta.snapshot.params['id'];
    this.productoService.getProductById(this.id).subscribe({
      next: (data) => {
        this.producto = data as Producto;
        console.log("Producto: " + this.producto);
        this.changeDetector.markForCheck();
      },
      error: (errors: any) => console.log(errors)
    })
  }

  onSubmit() {
    this.saveProduct();
  };

  private saveProduct() {
    this.productoService.editProduct(this.id, this.producto).subscribe({
      next: (data) => {
        this.redirectListProduct();
      },
      error: (err) => console.error('Error al editar el producto:', err)
    })
  }

  private redirectListProduct() {
    // Redirigir al inicio después de editar el producto
    this.enrutador.navigate(['/']);
  }
}
