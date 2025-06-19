package com.pluralsight.NorthwindTradersSpringBoot.repository;

import com.pluralsight.NorthwindTradersSpringBoot.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

// @Repository: This means the class is responsible for encapsulating the logic for storing, retrieving, updating, and deleting data
// for a specific domain object. It acts as an intermediary between your business logic and the underlying data
// storage (like a database)
@Repository("JdbcProductDao")
public class JdbcProductDao implements Displayable{
    private DataSource dataSource;

    @Autowired
    public JdbcProductDao(DataSource dataSource) {
        this.dataSource = dataSource;
    }


    @Override
    public void add(Product product) {
        String sql = "INSERT INTO products(ProductName, UnitPrice) VALUES (?, ?)";
        try(Connection connection = dataSource.getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, product.getName());
            preparedStatement.setDouble(2, product.getPrice());
            preparedStatement.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT ProductId, ProductName, UnitPrice FROM products";
        try(Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql)) {
            while(resultSet.next()){
                Product product = new Product();
                product.setProductId(resultSet.getInt("ProductId"));
                product.setName(resultSet.getString("ProductName"));
                product.setPrice(resultSet.getDouble("UnitPrice"));
                products.add(product);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return products;
    }

    @Override
    public void delete(int id) {
        String sql = "DELETE FROM product WHERE ProductId = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(int id, Product updateTerm) {
        String sql = "UPDATE products SET ProductName = ?, UnitPrice = ? WHERE ProductId = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, updateTerm.getName());
            preparedStatement.setDouble(2, updateTerm.getPrice());
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<Product> search(String searchTerm) {
        List<Product> products = new ArrayList<>();
        String sql = "SELECT ProductId, ProductName, UnitPrice FROM products WHERE ProductName = ?";
        try (Connection conn = dataSource.getConnection();
             PreparedStatement preparedStatement = conn.prepareStatement(sql)) {
            preparedStatement.setString(1, searchTerm);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Product product = new Product();
                product.setProductId(resultSet.getInt("ProductId"));
                product.setName(resultSet.getString("ProductName"));
                product.setPrice(resultSet.getDouble("UnitPrice"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }
}
