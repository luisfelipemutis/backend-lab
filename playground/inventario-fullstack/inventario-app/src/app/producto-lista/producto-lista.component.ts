import { ChangeDetectorRef, Component, inject } from '@angular/core';
import { Router } from '@angular/router';
import { Producto } from '../producto';
import { ProductoService } from '../producto.service';
import { FormsModule } from '@angular/forms';

@Component({
  imports: [FormsModule],
  selector: 'app-producto-lista',
  templateUrl: './producto-lista.component.html',
})
export class ProductoListaComponent {

  products!: Producto[];

  // Inyectar el servicio para  realizar peticiones http.
  private productService = inject(ProductoService);
  private enrutador = inject(Router);
  private changeDetector = inject(ChangeDetectorRef);

  ngOnInit() {
    // Cargar los productos al inicializar el componente
    this.getProducts();
  }

  private getProducts(): void {
    this.productService.getListProducts().subscribe(
      {
        next: (data) => {
          this.products = data;
          console.log(this.products);
          this.changeDetector.markForCheck();
        },
        error: (err) => {
          console.error("Error al cargar los productos", err);
        }
      }
    );
  }

  public editarProducto(id: number) {
    this.enrutador.navigate(['/editar-producto', id]);
  }

  public deleteProduct(id: number) {
    this.productService.deleteProduct(id).subscribe({
      next: () => this.getProducts(),
      error: (err) => console.error("Error al eliminar el producto", err)
    });
  }

}
