package solubris.fundingcircle.spring;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = AppConfig.class)
public class ProfileTest {

    @Autowired
    Profile tim;

    @Autowired
    Profile solubris;

    @Autowired
    Map<String, Profile> profileMap;

    @Test
    public void profileCanHaveDifferentPropertyValues() {
        assertThat(tim.getEmail(), not(equalTo(solubris.getEmail())));
    }

    @Test
    public void profileMapCanHaveDifferentPropertyValues() {
        assertThat(profileMap.get("tim").getEmail(), not(equalTo(profileMap.get("solubris").getEmail())));
    }
}
