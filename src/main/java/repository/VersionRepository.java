package repository;

import model.Version;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface VersionRepository extends JpaRepository<Version, Long>
{
    @Query("SELECT V \n"+
            " FROM Version V ")
    Version getVersion();
}