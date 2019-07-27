package bo;

import model.Version;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import repository.VersionRepository;

@Component
public class VersionBO
{
    @Autowired
    private VersionRepository repository;

    public Version getVersion()
    {
        return this.repository.getVersion();
    }
}
