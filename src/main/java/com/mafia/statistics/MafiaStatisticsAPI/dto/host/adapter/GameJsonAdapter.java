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

        if (game.has("host") &&
                game.get("host").getAsJsonObject().has("id")) {
            game.addProperty(
                    "hostId",
                    game.get("host").getAsJsonObject().get("id").getAsLong()
            );
            game.remove("host");
        }

        if (game.has("blackPlayerOne") &&
                game.get("blackPlayerOne").getAsJsonObject().has("id")) {
            game.addProperty(
                    "blackPlayerOneId",
                    game.get("blackPlayerOne").getAsJsonObject().get("id").getAsLong()
            );
            game.remove("blackPlayerOne");
        }

        if (game.has("blackPlayerTwo") &&
                game.get("blackPlayerTwo").getAsJsonObject().has("id")) {
            game.addProperty(
                    "blackPlayerTwoId",
                    game.get("blackPlayerTwo").getAsJsonObject().get("id").getAsLong()
            );
            game.remove("blackPlayerTwo");
        }

        if (game.has("donPlayer") &&
                game.get("donPlayer").getAsJsonObject().has("id")) {
            game.addProperty(
                    "donPlayerId",
                    game.get("donPlayer").getAsJsonObject().get("id").getAsLong()
            );
            game.remove("donPlayer");
        }

        if (game.has("sheriffPlayer") &&
                game.get("sheriffPlayer").getAsJsonObject().has("id")) {
            game.addProperty(
                    "sheriffPlayerId",
                    game.get("sheriffPlayer").getAsJsonObject().get("id").getAsLong()
            );
            game.remove("sheriffPlayer");
        }

        if (game.has("firstShootPlayer") &&
                game.get("firstShootPlayer").getAsJsonObject().has("id")) {
            game.addProperty(
                    "firstShootPlayerId",
                    game.get("firstShootPlayer").getAsJsonObject().get("id").getAsLong()
            );
            game.remove("firstShootPlayer");
        }


        if (game.has("players")) {
            JsonArray playersJsonArray = game.get("players").getAsJsonArray();

            List<List<Number>> players = new ArrayList<>();

            playersJsonArray.forEach(playerJsonElement -> {
                JsonObject playerJsonObject = playerJsonElement.getAsJsonObject();

                if (!playerJsonObject.has("player") ||
                        !playerJsonObject.get("player").getAsJsonObject().has("id") ||
                        !playerJsonObject.has("foulsCount"))
                    return;

                Long playerId = playerJsonObject
                        .get("player").getAsJsonObject()
                        .get("id").getAsLong();
                Integer foulsCount = playerJsonObject.get("foulsCount").getAsInt();

                List<Number> player = new ArrayList<>(List.of(playerId, foulsCount));
                players.add(player);
            });

            game.remove("players");
            game.add("players", gson.toJsonTree(players));
        }

        if (game.has("bestPlayers")) {
            JsonArray bestPlayersJsonArray = game.get("bestPlayers").getAsJsonArray();

            List<List<Number>> bestPlayers = new ArrayList<>();

            bestPlayersJsonArray.forEach(playerJsonElement -> {
                JsonObject playerJsonObject = playerJsonElement.getAsJsonObject();

                if (!playerJsonObject.has("player") ||
                        !playerJsonObject.get("player").getAsJsonObject().has("id") ||
                        !playerJsonObject.has("additionalPoints"))
                    return;

                Long playerId = playerJsonObject
                        .get("player").getAsJsonObject()
                        .get("id").getAsLong();
                Float additionalPoints = playerJsonObject.get("additionalPoints").getAsFloat();

                List<Number> player = new ArrayList<>(List.of(playerId, additionalPoints));
                bestPlayers.add(player);
            });

            game.remove("bestPlayers");
            game.add("bestPlayers", gson.toJsonTree(bestPlayers));
        }

        if (game.has("days")) {
            JsonArray daysJsonArray = game.get("days").getAsJsonArray();

            daysJsonArray.forEach(dayJsonElement -> {
                JsonObject dayJsonObject = dayJsonElement.getAsJsonObject();
                if (!dayJsonObject.has("votingMap"))
                    return;

                JsonArray votingMap = dayJsonObject.get("votingMap").getAsJsonArray();

                votingMap.forEach(votingMapJsonElement -> {
                    JsonObject votingMapJsonObject = votingMapJsonElement.getAsJsonObject();

                    if (!votingMapJsonObject.has("player") ||
                            !votingMapJsonObject.get("player").getAsJsonObject().has("id") ||
                            !votingMapJsonObject.has("whoPutToVote") ||
                            !votingMapJsonObject.get("whoPutToVote").getAsJsonObject().has("id"))
                        return;

                    Long playerId = votingMapJsonObject
                            .get("player").getAsJsonObject()
                            .get("id").getAsLong();
                    votingMapJsonObject.remove("player");
                    votingMapJsonObject.addProperty("playerId", playerId);

                    Long whoPutToVoteId = votingMapJsonObject
                            .get("whoPutToVote").getAsJsonObject()
                            .get("id").getAsLong();
                    votingMapJsonObject.remove("whoPutToVote");
                    votingMapJsonObject.addProperty("whoPutToVoteId", whoPutToVoteId);
                });
            });
        }

        return gson.fromJson(game.toString(), JsonElement.class);
    }
}
