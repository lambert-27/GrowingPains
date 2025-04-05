package test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import crud.ProductCrud;
import model.DisplayItem;

import java.sql.SQLException;
import java.util.List;

class ProductCrudTest {

    @Test
    void testGetAllProducts() throws SQLException {
        ProductCrud productCrud = new ProductCrud();
        List<DisplayItem> products = productCrud.getAllProducts();
        assertFalse(products.isEmpty(), "Cannot display an empty list of products!");
    }
}