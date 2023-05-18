package com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.impl.statistics;

import com.mafia.statistics.MafiaStatisticsAPI.dao.player.IPlayerDao;
import com.mafia.statistics.MafiaStatisticsAPI.dto.player.statistics.actual.CoupleStatisticsDto;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.mapper.inter.statistics.ICoupleStatisticsMapper;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.Player;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.statistics.CoupleStatistics;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class CoupleStatisticsMapperImpl implements ICoupleStatisticsMapper {

    private final IPlayerDao playerDao;

    @Override
    public List<CoupleStatistics> dtoToCoupleStatistics(
            List<CoupleStatisticsDto> coupleStatisticsDto,
            String playerNickname
    ) {
        if (coupleStatisticsDto == null) {
            return null;
        }

        List<CoupleStatistics> coupleStatistics = new ArrayList<>();

        coupleStatisticsDto.forEach(statistics -> {
            String nickname = statistics.getNicknameOfMafiaOne().equals(playerNickname)
                    ? statistics.getNicknameOfMafiaTwo()
                    : statistics.getNicknameOfMafiaOne();

            Player player = playerDao.findMinimalInfoByNickname(nickname);

            coupleStatistics.add(
                    new CoupleStatistics(
                            statistics.getFromDate(),
                            statistics.getToDate(),
                            player,
                            statistics.getGames(),
                            statistics.getWins(),
                            statistics.getCalculatedPercentOfWins()
                    )
            );
        });

        return coupleStatistics;
    }
}
