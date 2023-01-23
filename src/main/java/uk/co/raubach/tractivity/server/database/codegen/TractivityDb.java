/*
 * This file is generated by jOOQ.
 */
package uk.co.raubach.tractivity.server.database.codegen;


import java.util.Arrays;
import java.util.List;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;

import uk.co.raubach.tractivity.server.database.codegen.tables.Activities;
import uk.co.raubach.tractivity.server.database.codegen.tables.ActivityMeasures;
import uk.co.raubach.tractivity.server.database.codegen.tables.ActivityParticipants;
import uk.co.raubach.tractivity.server.database.codegen.tables.ActivityTypes;
import uk.co.raubach.tractivity.server.database.codegen.tables.Events;
import uk.co.raubach.tractivity.server.database.codegen.tables.Locations;
import uk.co.raubach.tractivity.server.database.codegen.tables.Measures;
import uk.co.raubach.tractivity.server.database.codegen.tables.Participants;
import uk.co.raubach.tractivity.server.database.codegen.tables.SchemaVersion;
import uk.co.raubach.tractivity.server.database.codegen.tables.ViewActivities;
import uk.co.raubach.tractivity.server.database.codegen.tables.ViewActivityParticipantMeasures;
import uk.co.raubach.tractivity.server.database.codegen.tables.ViewEvents;
import uk.co.raubach.tractivity.server.database.codegen.tables.ViewParticipants;


// @formatter:off
/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class TractivityDb extends SchemaImpl {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>tractivity_db</code>
     */
    public static final TractivityDb TRACTIVITY_DB = new TractivityDb();

    /**
     * No further instances allowed
     */
    private TractivityDb() {
        super("tractivity_db", null);
    }


    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        return Arrays.asList(
            Activities.ACTIVITIES,
            ActivityMeasures.ACTIVITY_MEASURES,
            ActivityParticipants.ACTIVITY_PARTICIPANTS,
            ActivityTypes.ACTIVITY_TYPES,
            Events.EVENTS,
            Locations.LOCATIONS,
            Measures.MEASURES,
            Participants.PARTICIPANTS,
            SchemaVersion.SCHEMA_VERSION,
            ViewActivities.VIEW_ACTIVITIES,
            ViewActivityParticipantMeasures.VIEW_ACTIVITY_PARTICIPANT_MEASURES,
            ViewEvents.VIEW_EVENTS,
            ViewParticipants.VIEW_PARTICIPANTS
        );
    }
    // @formatter:on
}