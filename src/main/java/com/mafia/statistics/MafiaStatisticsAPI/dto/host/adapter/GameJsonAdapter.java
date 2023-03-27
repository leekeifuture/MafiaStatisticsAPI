package com.mafia.statistics.MafiaStatisticsAPI.dto.host.adapter;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.mafia.statistics.MafiaStatisticsAPI.dto.host.Game;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GameJsonAdapter implements JsonSerializer<Game> {

    @Override
    public JsonElement serialize(
            Game src,
            Type typeOfSrc,
            JsonSerializationContext context
    ) {
        Gson gson = new Gson();

        String gameJson = gson.toJson(src);
        JsonObject game = JsonParser.parseString(gameJson).getAsJsonObject();

        game.addProperty(
                "hostId",
                game.get("host").getAsJsonObject().get("id").getAsLong()
        );
        game.remove("host");

        game.addProperty(
                "blackPlayerOneId",
                game.get("blackPlayerOne").getAsJsonObject().get("id").getAsLong()
        );
        game.remove("blackPlayerOne");

        game.addProperty(
                "blackPlayerTwoId",
                game.get("blackPlayerTwo").getAsJsonObject().get("id").getAsLong()
        );
        game.remove("blackPlayerTwo");

        game.addProperty(
                "donPlayerId",
                game.get("donPlayer").getAsJsonObject().get("id").getAsLong()
        );
        game.remove("donPlayer");

        game.addProperty(
                "sheriffPlayerId",
                game.get("sheriffPlayer").getAsJsonObject().get("id").getAsLong()
        );
        game.remove("sheriffPlayer");

        game.addProperty(
                "firstShootPlayerId",
                game.get("firstShootPlayer").getAsJsonObject().get("id").getAsLong()
        );
        game.remove("firstShootPlayer");


        JsonArray playersJsonArray = game.get("players").getAsJsonArray();

        List<List<Number>> players = new ArrayList<>();

        playersJsonArray.forEach(playerJsonElement -> {
            JsonObject playerJsonObject = playerJsonElement.getAsJsonObject();

            Long playerId = playerJsonObject
                    .get("player").getAsJsonObject()
                    .get("id").getAsLong();
            Integer foulsCount = playerJsonObject.get("foulsCount").getAsInt();

            List<Number> player = new ArrayList<>(List.of(playerId, foulsCount));
            players.add(player);
        });

        game.remove("players");
        game.add("players", gson.toJsonTree(players));


        JsonArray bestPlayersJsonArray = game.get("bestPlayers").getAsJsonArray();

        List<List<Number>> bestPlayers = new ArrayList<>();

        bestPlayersJsonArray.forEach(playerJsonElement -> {
            JsonObject playerJsonObject = playerJsonElement.getAsJsonObject();

            Long playerId = playerJsonObject
                    .get("player").getAsJsonObject()
                    .get("id").getAsLong();
            Float additionalPoints = playerJsonObject.get("additionalPoints").getAsFloat();

            List<Number> player = new ArrayList<>(List.of(playerId, additionalPoints));
            bestPlayers.add(player);
        });

        game.remove("bestPlayers");
        game.add("bestPlayers", gson.toJsonTree(bestPlayers));


        JsonArray daysJsonArray = game.get("days").getAsJsonArray();

        daysJsonArray.forEach(dayJsonElement -> {
            JsonObject dayJsonObject = dayJsonElement.getAsJsonObject();
            JsonArray votingMap = dayJsonObject.get("votingMap").getAsJsonArray();

            votingMap.forEach(voting -> {
                Long playerId = voting.getAsJsonObject()
                        .get("player").getAsJsonObject()
                        .get("id").getAsLong();
                voting.getAsJsonObject().remove("player");
                voting.getAsJsonObject().addProperty("playerId", playerId);

                Long whoPutToVoteId = voting.getAsJsonObject()
                        .get("whoPutToVote").getAsJsonObject()
                        .get("id").getAsLong();
                voting.getAsJsonObject().remove("whoPutToVote");
                voting.getAsJsonObject().addProperty("whoPutToVoteId", whoPutToVoteId);
            });
        });

        return gson.fromJson(game.toString(), JsonElement.class);
    }
}
