package com.uniritter.upphotos;

import bo.VersionBO;
import model.Version;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import repository.VersionRepository;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@SpringBootTest
public class VersionBOTests {

    @MockBean(name="versionRepository")
    private VersionRepository versionRepository;

    @Autowired
    private VersionBO versionBO;

    @Test
    public void getVersion()
    {
        String versionNumber = "1.0.0";
        Version version = new Version();
        version.setValue(versionNumber);

        when(versionRepository.getVersion()).thenReturn(version);

        Version expectedVersion = versionBO.getVersion();

        assertTrue(expectedVersion != null && expectedVersion.getValue() == versionNumber);
    }

}
