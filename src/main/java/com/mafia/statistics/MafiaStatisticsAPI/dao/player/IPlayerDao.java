package com.mafia.statistics.MafiaStatisticsAPI.dao.player;

import com.mafia.statistics.MafiaStatisticsAPI.dto.player.Player;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPlayerDao extends JpaRepository<Player, Long> {

    Boolean existsByNickname(String nickname);

    Player findByNickname(String nickname);

    Optional<Player> findByVkId(Long id);

    List<Player> findAllByGamesTotalNotNull();

    @Query("SELECT NEW Player (t.id, t.nickname, t.gender, t.gamesTotal) " +
            "FROM Player AS t " +
            "WHERE t.gamesTotal IS NOT NULL " +
            "ORDER BY t.gamesTotal DESC")
    List<Player> findTopPlayersByGamesTotal(Pageable pageable);
}
