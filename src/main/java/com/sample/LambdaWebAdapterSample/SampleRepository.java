package com.sample.LambdaWebAdapterSample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Repository
public class SampleRepository {

    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public SampleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Map<String, Object> findById(int id) {

        String sql = "SELECT * FROM user WHERE id = %d".formatted(id);

        return jdbcTemplate.queryForMap(sql);
    }
}
