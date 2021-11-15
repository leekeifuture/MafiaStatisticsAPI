package com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.impl.statistics;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.NumbersStatisticsDto;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.inter.statistics.INumbersStatisticsMapper;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.statistics.NumbersStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.statistics.Place;

import org.springframework.stereotype.Component;

@Component
public class NumbersStatisticsMapperImpl implements INumbersStatisticsMapper {

    @Override
    public NumbersStatistics dtoToNumbersStatistics(NumbersStatisticsDto numbersStatisticsDto) {
        if (numbersStatisticsDto == null) {
            return null;
        }

        return new NumbersStatistics(
                numbersStatisticsDto.getNickname(),
                numbersStatisticsDto.getFromDate(),
                numbersStatisticsDto.getToDate(),
                numbersStatisticsDto.getGamesTotal(),
                new Place(
                        numbersStatisticsDto.getPlaceOne().getGamesRed(),
                        numbersStatisticsDto.getPlaceOne().getGamesBlack(),
                        numbersStatisticsDto.getPlaceOne().getGamesDon(),
                        numbersStatisticsDto.getPlaceOne().getGamesSheriff(),
                        numbersStatisticsDto.getPlaceOne().getPercentWinRed(),
                        numbersStatisticsDto.getPlaceOne().getPercentWinBlack()
                ),
                new Place(
                        numbersStatisticsDto.getPlaceTwo().getGamesRed(),
                        numbersStatisticsDto.getPlaceTwo().getGamesBlack(),
                        numbersStatisticsDto.getPlaceTwo().getGamesDon(),
                        numbersStatisticsDto.getPlaceTwo().getGamesSheriff(),
                        numbersStatisticsDto.getPlaceTwo().getPercentWinRed(),
                        numbersStatisticsDto.getPlaceTwo().getPercentWinBlack()
                ),
                new Place(
                        numbersStatisticsDto.getPlaceThree().getGamesRed(),
                        numbersStatisticsDto.getPlaceThree().getGamesBlack(),
                        numbersStatisticsDto.getPlaceThree().getGamesDon(),
                        numbersStatisticsDto.getPlaceThree().getGamesSheriff(),
                        numbersStatisticsDto.getPlaceThree().getPercentWinRed(),
                        numbersStatisticsDto.getPlaceThree().getPercentWinBlack()
                ),
                new Place(
                        numbersStatisticsDto.getPlaceFour().getGamesRed(),
                        numbersStatisticsDto.getPlaceFour().getGamesBlack(),
                        numbersStatisticsDto.getPlaceFour().getGamesDon(),
                        numbersStatisticsDto.getPlaceFour().getGamesSheriff(),
                        numbersStatisticsDto.getPlaceFour().getPercentWinRed(),
                        numbersStatisticsDto.getPlaceFour().getPercentWinBlack()
                ),
                new Place(
                        numbersStatisticsDto.getPlaceFive().getGamesRed(),
                        numbersStatisticsDto.getPlaceFive().getGamesBlack(),
                        numbersStatisticsDto.getPlaceFive().getGamesDon(),
                        numbersStatisticsDto.getPlaceFive().getGamesSheriff(),
                        numbersStatisticsDto.getPlaceFive().getPercentWinRed(),
                        numbersStatisticsDto.getPlaceFive().getPercentWinBlack()
                ),
                new Place(
                        numbersStatisticsDto.getPlaceSix().getGamesRed(),
                        numbersStatisticsDto.getPlaceSix().getGamesBlack(),
                        numbersStatisticsDto.getPlaceSix().getGamesDon(),
                        numbersStatisticsDto.getPlaceSix().getGamesSheriff(),
                        numbersStatisticsDto.getPlaceSix().getPercentWinRed(),
                        numbersStatisticsDto.getPlaceSix().getPercentWinBlack()
                ),
                new Place(
                        numbersStatisticsDto.getPlaceSeven().getGamesRed(),
                        numbersStatisticsDto.getPlaceSeven().getGamesBlack(),
                        numbersStatisticsDto.getPlaceSeven().getGamesDon(),
                        numbersStatisticsDto.getPlaceSeven().getGamesSheriff(),
                        numbersStatisticsDto.getPlaceSeven().getPercentWinRed(),
                        numbersStatisticsDto.getPlaceSeven().getPercentWinBlack()
                ),
                new Place(
                        numbersStatisticsDto.getPlaceEight().getGamesRed(),
                        numbersStatisticsDto.getPlaceEight().getGamesBlack(),
                        numbersStatisticsDto.getPlaceEight().getGamesDon(),
                        numbersStatisticsDto.getPlaceEight().getGamesSheriff(),
                        numbersStatisticsDto.getPlaceEight().getPercentWinRed(),
                        numbersStatisticsDto.getPlaceEight().getPercentWinBlack()
                ),
                new Place(
                        numbersStatisticsDto.getPlaceNine().getGamesRed(),
                        numbersStatisticsDto.getPlaceNine().getGamesBlack(),
                        numbersStatisticsDto.getPlaceNine().getGamesDon(),
                        numbersStatisticsDto.getPlaceNine().getGamesSheriff(),
                        numbersStatisticsDto.getPlaceNine().getPercentWinRed(),
                        numbersStatisticsDto.getPlaceNine().getPercentWinBlack()
                ),
                new Place(
                        numbersStatisticsDto.getPlaceTen().getGamesRed(),
                        numbersStatisticsDto.getPlaceTen().getGamesBlack(),
                        numbersStatisticsDto.getPlaceTen().getGamesDon(),
                        numbersStatisticsDto.getPlaceTen().getGamesSheriff(),
                        numbersStatisticsDto.getPlaceTen().getPercentWinRed(),
                        numbersStatisticsDto.getPlaceTen().getPercentWinBlack()
                )
        );
    }
}
