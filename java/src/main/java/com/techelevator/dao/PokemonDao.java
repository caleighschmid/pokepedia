package com.techelevator.dao;



import com.techelevator.model.PokemonDetail;

import java.util.List;

public interface PokemonDao {

    List<PokemonDetail> getAllFavoritesByUserId(int id);

    PokemonDetail saveFavorites(PokemonDetail detail, int userId);

    PokemonDetail getFavoriteById(int id);

}
