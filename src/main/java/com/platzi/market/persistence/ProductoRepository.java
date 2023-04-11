package com.platzi.market.persistence;

import com.platzi.market.domain.Product;
import com.platzi.market.domain.repository.ProductRepository;
import com.platzi.market.persistence.crud.ProductoCrudRepository;
import com.platzi.market.persistence.entity.Producto;
import com.platzi.market.persistence.mapper.ProductMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public class ProductoRepository implements ProductRepository {
    private ProductoCrudRepository productoCrudRepository;
    private ProductMapper mapper;


    public List<Product> getAll(){
        List<Producto> productos = (List<Producto>) productoCrudRepository.findAll();

        return mapper.toProduct(productos);
    }


    public Optional<List<Product>> getByCategory(int categoryId){
        List<Producto> productos = productoCrudRepository.findByIdCategoriaOrderByNombreAsc(categoryId);
        return Optional.of(mapper.toProduct(productos));
    }
    public Optional<List<Product>> getScarseProduct(int quality){
        Optional<List<Producto>> productos= productoCrudRepository.findByCantidadStockLessThanAndEstado(quality,true);
        return productos.map(prods -> mapper.toProduct(prods));
    }

    public Optional<Product> getProduct(int ProductId){

        return productoCrudRepository.findById(ProductId).map(prods -> mapper.toProduct(prods));
    }

    public Product save(Product product){
        Producto producto = mapper.toProduct(product);
        return mapper.toProduct(productoCrudRepository.save(producto));

    }

    public void delete (int idProducto){
        productoCrudRepository.deleteById(idProducto);
    }
}
