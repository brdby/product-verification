package ru.brdby.cinderella.data.repository;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.brdby.cinderella.data.domain.Product;
import ru.brdby.cinderella.util.ProductMapper;

@Repository
@RequiredArgsConstructor
public class JdbcProductRepository implements ProductRepository{

    private final JdbcTemplate jdbcTemplate;
    private final ProductMapper productMapper;

    @Override
    public Product findOne(String uuid) {
        return jdbcTemplate.queryForObject("SELECT uuid, name FROM Product WHERE uuid=?", productMapper, uuid);
    }

    @Override
    public void save(Product product) {
        jdbcTemplate.update("INSERT INTO Product (uuid, name) VALUES (?,?)", product.getUUID(), product.getName());
    }

}
