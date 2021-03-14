package ru.brdby.cinderella.util;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ru.brdby.cinderella.data.domain.Product;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProductMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int i) throws SQLException {
        return new Product(rs.getString("uuid"), rs.getString("name"));
    }
}
