package org.launchcode.mycodingevents.data;

import org.launchcode.mycodingevents.models.Event;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

// Two necessary parameters for CrudRepository to interact with our SQL database
// Extending the CrudRepository interface gives us access to methods to perform all the CRUD operations that we made happen in SQL.
@Repository
public interface EventRepository extends CrudRepository<Event, Integer> {
}
