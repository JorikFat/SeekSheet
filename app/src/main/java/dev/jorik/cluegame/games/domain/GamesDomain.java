package dev.jorik.cluegame.games.domain;

import java.util.List;

import dev.jorik.cluegame.application.DestroyUseCase;

public class GamesDomain implements DestroyUseCase {
    private final GamesProvider provider;
    private GamesOutport outport;

    public GamesDomain(GamesProvider provider) {
        this.provider = provider;
        this.outport = outport;
    }

    public List<Game> getGames(){
        return provider.getGames();
    }

    public void selectGame(Game game){
        outport.selectGame(game);
    }

    public void createGame(Game game){
        provider.createGame(game);//todo распараллелить
        outport.createGame(game);
    }

    public void deleteGame(Game game){
        provider.deleteGame(game);
    }

    public void setOutport(GamesOutport outport){
        this.outport = outport;
    }

    @Override
    public void destroy(){
        outport = null;
    }
}
