package com.lukegjpotter.bikeracingireland.model.entity;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Relation;

import java.util.List;

/**
 * Created by lukegjpotter on 04/12/2017.
 */

public class BikeRaceWithStageDetails {

    @Embedded
    public BikeRaceEntity bikeRaceEntity;

    @Relation(parentColumn = "id", entityColumn = "id", entity = StageDetailEntity.class)
    public List<StageDetailEntity> stageDetails;
}
