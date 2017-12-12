package pl.edu.agh.iet.dts.ui.persistence.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.edu.agh.iet.dts.ui.persistence.domain.AggregatedPositions;

/**
 * @author Bartłomiej Grochal
 */
public interface AggregatedPositionsRepository extends MongoRepository<AggregatedPositions, String> { }
