package sv.edu.udb.repository;
import sv.edu.udb.repository.domain.Producto;

//Interfaz encargadas del acceso a los datos
public interface ProductoRepository {
    Producto guardarProducto(final String nombre, final Double precio);
}
