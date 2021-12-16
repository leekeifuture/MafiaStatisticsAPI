package com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.impl.statistics;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.NumbersStatisticsDto;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.inter.statistics.INumbersStatisticsMapper;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.statistics.NumbersStatistics;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.statistics.Place;
import com.mafia.statistics.MafiaStatisticsAPI.util.DigitsUtil;

import org.springframework.stereotype.Component;

@Component
public class NumbersStatisticsMapperImpl implements INumbersStatisticsMapper {

    @Override
    public NumbersStatistics dtoToNumbersStatistics(NumbersStatisticsDto numbersStatisticsDto) {
        if (numbersStatisticsDto == null) {
            return null;
        }

        return new NumbersStatistics(
                numbersStatisticsDto.getFromDate(),
                numbersStatisticsDto.getToDate(),
                new Place(
                        numbersStatisticsDto.getPlaceOne().getGamesRed(),
                        numbersStatisticsDto.getPlaceOne().getGamesBlack(),
                        numbersStatisticsDto.getPlaceOne().getGamesDon(),
                        numbersStatisticsDto.getPlaceOne().getGamesSheriff(),
                        DigitsUtil.roundDouble(
                                numbersStatisticsDto.getPlaceOne().getPercentWinRed(),
                                1
                        ),
                        DigitsUtil.roundDouble(
                                numbersStatisticsDto.getPlaceOne().getPercentWinBlack(),
                                1
                        )
                ),
                new Place(
                        numbersStatisticsDto.getPlaceTwo().getGamesRed(),
                        numbersStatisticsDto.getPlaceTwo().getGamesBlack(),
                        numbersStatisticsDto.getPlaceTwo().getGamesDon(),
                        numbersStatisticsDto.getPlaceTwo().getGamesSheriff(),
                        DigitsUtil.roundDouble(
                                numbersStatisticsDto.getPlaceTwo().getPercentWinRed(),
                                1
                        ),
                        DigitsUtil.roundDouble(
                                numbersStatisticsDto.getPlaceTwo().getPercentWinBlack(),
                                1
                        )
                ),
                new Place(
                        numbersStatisticsDto.getPlaceThree().getGamesRed(),
                        numbersStatisticsDto.getPlaceThree().getGamesBlack(),
                        numbersStatisticsDto.getPlaceThree().getGamesDon(),
                        numbersStatisticsDto.getPlaceThree().getGamesSheriff(),
                        DigitsUtil.roundDouble(
                                numbersStatisticsDto.getPlaceThree().getPercentWinRed(),
                                1
                        ),
                        DigitsUtil.roundDouble(
                                numbersStatisticsDto.getPlaceThree().getPercentWinBlack(),
                                1
                        )
                ),
                new Place(
                        numbersStatisticsDto.getPlaceFour().getGamesRed(),
                        numbersStatisticsDto.getPlaceFour().getGamesBlack(),
                        numbersStatisticsDto.getPlaceFour().getGamesDon(),
                        numbersStatisticsDto.getPlaceFour().getGamesSheriff(),
                        DigitsUtil.roundDouble(
                                numbersStatisticsDto.getPlaceFour().getPercentWinRed(),
                                1
                        ),
                        DigitsUtil.roundDouble(
                                numbersStatisticsDto.getPlaceFour().getPercentWinBlack(),
                                1
                        )
                ),
                new Place(
                        numbersStatisticsDto.getPlaceFive().getGamesRed(),
                        numbersStatisticsDto.getPlaceFive().getGamesBlack(),
                        numbersStatisticsDto.getPlaceFive().getGamesDon(),
                        numbersStatisticsDto.getPlaceFive().getGamesSheriff(),
                        DigitsUtil.roundDouble(
                                numbersStatisticsDto.getPlaceFive().getPercentWinRed(),
                                1
                        ),
                        DigitsUtil.roundDouble(
                                numbersStatisticsDto.getPlaceFive().getPercentWinBlack(),
                                1
                        )
                ),
                new Place(
                        numbersStatisticsDto.getPlaceSix().getGamesRed(),
                        numbersStatisticsDto.getPlaceSix().getGamesBlack(),
                        numbersStatisticsDto.getPlaceSix().getGamesDon(),
                        numbersStatisticsDto.getPlaceSix().getGamesSheriff(),
                        DigitsUtil.roundDouble(
                                numbersStatisticsDto.getPlaceSix().getPercentWinRed(),
                                1
                        ),
                        DigitsUtil.roundDouble(
                                numbersStatisticsDto.getPlaceSix().getPercentWinBlack(),
                                1
                        )
                ),
                new Place(
                        numbersStatisticsDto.getPlaceSeven().getGamesRed(),
                        numbersStatisticsDto.getPlaceSeven().getGamesBlack(),
                        numbersStatisticsDto.getPlaceSeven().getGamesDon(),
                        numbersStatisticsDto.getPlaceSeven().getGamesSheriff(),
                        DigitsUtil.roundDouble(
                                numbersStatisticsDto.getPlaceSeven().getPercentWinRed(),
                                1
                        ),
                        DigitsUtil.roundDouble(
                                numbersStatisticsDto.getPlaceSeven().getPercentWinBlack(),
                                1
                        )
                ),
                new Place(
                        numbersStatisticsDto.getPlaceEight().getGamesRed(),
                        numbersStatisticsDto.getPlaceEight().getGamesBlack(),
                        numbersStatisticsDto.getPlaceEight().getGamesDon(),
                        numbersStatisticsDto.getPlaceEight().getGamesSheriff(),
                        DigitsUtil.roundDouble(
                                numbersStatisticsDto.getPlaceEight().getPercentWinRed(),
                                1
                        ),
                        DigitsUtil.roundDouble(
                                numbersStatisticsDto.getPlaceEight().getPercentWinBlack(),
                                1
                        )
                ),
                new Place(
                        numbersStatisticsDto.getPlaceNine().getGamesRed(),
                        numbersStatisticsDto.getPlaceNine().getGamesBlack(),
                        numbersStatisticsDto.getPlaceNine().getGamesDon(),
                        numbersStatisticsDto.getPlaceNine().getGamesSheriff(),
                        DigitsUtil.roundDouble(
                                numbersStatisticsDto.getPlaceNine().getPercentWinRed(),
                                1
                        ),
                        DigitsUtil.roundDouble(
                                numbersStatisticsDto.getPlaceNine().getPercentWinBlack(),
                                1
                        )
                ),
                new Place(
                        numbersStatisticsDto.getPlaceTen().getGamesRed(),
                        numbersStatisticsDto.getPlaceTen().getGamesBlack(),
                        numbersStatisticsDto.getPlaceTen().getGamesDon(),
                        numbersStatisticsDto.getPlaceTen().getGamesSheriff(),
                        DigitsUtil.roundDouble(
                                numbersStatisticsDto.getPlaceTen().getPercentWinRed(),
                                1
                        ),
                        DigitsUtil.roundDouble(
                                numbersStatisticsDto.getPlaceTen().getPercentWinBlack(),
                                1
                        )
                )
        );
    }
}
