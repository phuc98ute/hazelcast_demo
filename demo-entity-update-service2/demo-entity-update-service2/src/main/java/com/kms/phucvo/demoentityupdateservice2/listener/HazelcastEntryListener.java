package com.kms.phucvo.demoentityupdateservice2.listener;

import com.hazelcast.core.EntryEvent;
import com.hazelcast.core.EntryListener;
import com.hazelcast.map.MapEvent;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HazelcastEntryListener implements EntryListener {
    @Override
    public void entryAdded(EntryEvent entryEvent) {
        log.info("***** Hazelcast add a new entry Map!: "+entryEvent);
    }

    @Override
    public void entryEvicted(EntryEvent entryEvent) {
        log.info("***** Hazelcast add evict an entry Map!: "+entryEvent);
    }

    @Override
    public void entryExpired(EntryEvent entryEvent) {
        log.info("***** Hazelcast an entry expired Map!: "+entryEvent);
    }

    @Override
    public void entryRemoved(EntryEvent entryEvent) {
        log.info("***** Hazelcast remove an entry on Map!: "+entryEvent);
    }

    @Override
    public void entryUpdated(EntryEvent entryEvent) {
        log.info("***** Hazelcast update an entry on Map!: "+entryEvent);
    }

    @Override
    public void mapCleared(MapEvent mapEvent) {
        log.info("***** Hazelcast clear Map!: "+mapEvent);
    }

    @Override
    public void mapEvicted(MapEvent mapEvent) {
        log.info("***** Hazelcast evict Map!: "+mapEvent);
    }
}
