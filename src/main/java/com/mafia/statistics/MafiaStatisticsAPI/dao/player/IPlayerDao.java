package com.mafia.statistics.MafiaStatisticsAPI.dao.player;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.PlayerDto;
import com.mafia.statistics.MafiaStatisticsAPI.pyload.player.Player;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPlayerDao extends JpaRepository<PlayerDto, Long> {

    Boolean existsByNickname(String nickname);

    PlayerDto findByNickname(String nickname);

    Optional<PlayerDto> findByVkId(Long id);

    List<PlayerDto> findAllByGamesTotalNotNull();

    @Query("SELECT NEW com.mafia.statistics.MafiaStatisticsAPI.pyload.player.Player (" +
            "   t.id, " +
            "   t.nickname, " +
            "   t.gamesTotal, " +
            "   t.gender" +
            ") " +
            "FROM PlayerDto AS t " +
            "WHERE t.gamesTotal IS NOT NULL " +
            "ORDER BY t.gamesTotal DESC")
    List<Player> findTopPlayersByGamesTotal(Pageable pageable);

    @Query("SELECT NEW com.mafia.statistics.MafiaStatisticsAPI.pyload.player.Player (" +
            "   t.id, " +
            "   t.nickname, " +
            "   t.gamesTotal, " +
            "   t.gender" +
            ") " +
            "FROM PlayerDto AS t " +
            "WHERE LOWER(t.nickname)       LIKE LOWER(concat('%', :nickname, '%')) AND " +
            "      LOWER(t.customNickname) LIKE LOWER(concat('%', :nickname, '%')) AND " +
            "      t.gamesTotal IS NOT NULL " +
            "ORDER BY t.gamesTotal DESC")
    List<Player> findByNicknameFree(@Param("nickname") String nickname, Pageable pageable);
}
