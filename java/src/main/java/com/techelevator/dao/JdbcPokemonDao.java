package com.techelevator.dao;


import com.techelevator.model.PokemonDetail;
import com.techelevator.model.Sprite;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcPokemonDao implements PokemonDao{

    private JdbcTemplate template;

    public JdbcPokemonDao(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
    }

    @Override
    public List<PokemonDetail> getAllFavoritesByUserId(int id) {

        List<PokemonDetail> list = new ArrayList<>();

        String sql = "SELECT p.id, api_id, name, base_experience, height, weight, back_url, front_url FROM pokemon p " +
                "JOIN users_pokemon up ON p.id = up.pokemon_id WHERE " +
                "user_id = ? ORDER BY p.api_id ASC";

        SqlRowSet results = template.queryForRowSet(sql, id);
        while (results.next()){
            list.add(mapRowToPokemonDetail(results));
        }
        return list;
    }

    @Override
    public PokemonDetail saveFavorites(PokemonDetail detail, int userId) {
        String sql = "INSERT INTO pokemon (api_id, name, base_experience, height, weight, back_url, front_url) " +
                "VALUES(?, ?, ?, ?, ?, ?, ?) ON CONFLICT DO NOTHING RETURNING id";

        int id;

        try {
            id = template.queryForObject(sql, int.class, detail.getApiId(),
                    detail.getName(), detail.getBaseExperience(), detail.getHeight(),
                    detail.getWeight(), detail.getSprite().getBackDefault(),
                    detail.getSprite().getFrontDefault());
        } catch (EmptyResultDataAccessException e) {
            sql = "SELECT id FROM pokemon WHERE api_id = ?";
            id = template.queryForObject(sql, Integer.class, detail.getApiId());
        } catch (NullPointerException n) {
            sql = "SELECT id FROM pokemon WHERE api_id = ?";
            id = template.queryForObject(sql, Integer.class, detail.getApiId());
        }

        sql = "INSERT INTO users_pokemon (pokemon_id, user_id) VALUES (?, ?)";
        template.update(sql, id, userId);
        return null;
    }

    @Override
    public PokemonDetail getFavoriteById(int id) {
        return null;
    }

    private PokemonDetail mapRowToPokemonDetail(SqlRowSet rs){
        PokemonDetail detail = new PokemonDetail();
        detail.setBaseExperience(rs.getInt("base_experience"));
        detail.setHeight(rs.getInt("height"));
        detail.setId(rs.getInt("id"));
        detail.setName((rs.getString("name")));
        detail.setWeight(rs.getInt("weight"));
        detail.setApiId(rs.getInt("api_id"));
        Sprite sprite = new Sprite();
        sprite.setBackDefault(rs.getString("back_url"));
        sprite.setFrontDefault(rs.getString("front_url"));
        detail.setSprite(sprite);
        return detail;
    }
}
