package com.maxtrain.service;

import javax.persistence.EntityNotFoundException;

import org.springframework.dao.DataAccessException;

import com.maxtrain.model.Product;
import com.maxtrain.repository.ProductRepository;

public class ProductService {

    private ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
	this.productRepository = productRepository;
    }

    /**
     * Adds a new Product to the repository and returns the potentially updated
     * Product.
     * 
     * Returns <code>null</code> if there is an error adding the Product.
     * 
     * @param product Product to add
     * @return See above
     */
    public Product addNewProduct(Product product) {
	try {
	    return productRepository.save(product);
	} catch (DataAccessException ignored) {
	    // Error storing the product. Return null
	    return null;
	}
    }

    /**
     * Returns the Product with the given <code>id</code>.
     * 
     * Returns <code>null</code> if no Product exists with <code>id</code>.
     * 
     * @param id
     * @return See above
     */
    public Product getExistingProduct(int id) {
	try {
	    return productRepository.getOne(id);
	} catch (EntityNotFoundException ignored) {
	    return null;
	}
    }
}
