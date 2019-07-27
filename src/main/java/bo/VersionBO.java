package bo;

import model.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.VersionRepository;
import javax.persistence.EntityNotFoundException;

@Component
public class VersionBO
{
    @Autowired
    private VersionRepository repository;

    public Version getVersion()
    {
        return this.repository.GetVersion();
    }
}
