package com.estsoft.springproject.service;

import com.estsoft.springproject.domain.dto.Batter;
import com.estsoft.springproject.domain.dto.Pitcher;
import com.estsoft.springproject.domain.dto.PlayerInfo;
import com.estsoft.springproject.repository.BatterRecordMapper;
import com.estsoft.springproject.repository.PitcherRecordMapper;
import com.estsoft.springproject.repository.PlayerMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlayerService {

    private final PlayerMapper playerMapper;
    private final BatterRecordMapper batterRecordMapper;
    private final PitcherRecordMapper pitcherRecordMapper;
    public PlayerInfo getPlayerInfo(Long id) {

        return playerMapper.findById(id);
    }

    public List<PlayerInfo> getPlayersInfoByTeam(Long teamId) {

        return playerMapper.findByTeamId(teamId);
    }

    public Batter getBatterInfoWithAllSeasonRecord(Long id) {

        Batter batter = Batter.builder()
                .playerInfo(playerMapper.findById(id))
                .batterRecordDetails(batterRecordMapper.getBatterRecordDetailOfAllSeason(id))
                .careerRecord(batterRecordMapper.getCareerRecord(id))
                .build();

        return batter;
    }

    public Pitcher getPitcherInfoWithAllSeasonRecord(Long id) {

        Pitcher pitcher = Pitcher.builder()
                .playerInfo(playerMapper.findById(id))
                .pitcherRecordDetails(pitcherRecordMapper.getPitcherRecordDetailOfAllSeason(id))
                .careerRecord(pitcherRecordMapper.getCareerRecord(id))
                .build();

        return pitcher;
    }
}
