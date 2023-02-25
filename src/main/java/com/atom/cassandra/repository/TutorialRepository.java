package com.atom.cassandra.repository;

import com.atom.cassandra.model.Tutorial;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.List;
import java.util.UUID;

/**
 * Now we can use CassandraRepository’s methods:
 * save(), findOne(), findById(), findAll(), count(), delete(), deleteById()… without implementing these methods.
 * <p>
 * <p>
 * <p>
 * We also define custom finder methods:
 * – findByTitleContaining(): returns all Tutorials which title contains input title.
 * – findByPublished(): returns all Tutorials with published having value as input published.
 *
 * @author Atom
 */

public interface TutorialRepository extends CassandraRepository<Tutorial, UUID> {


    /**
     * <p>
     *     @AllowFiltering annotation allows server-side filtering for findByPublished() method.
     *     Why do we use it?
     *
     *     The method is equivalent to this query:
     *      SELECT * FROM tutorial WHERE published = [true/false];
     * </p>
     * @param published
     * @return
     */
    @AllowFiltering
    List<Tutorial> findByPublished(boolean published);

    List<Tutorial> findByTitleContaining(String title);
}