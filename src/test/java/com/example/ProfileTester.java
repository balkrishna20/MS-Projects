package com.example;

/**
 * Created by rominoushana on 3/29/16.
 */
        import com.example.configuration.RepositoryConfiguration;
        import com.example.repositories.ProfileRepository;
        import com.example.model.Profile;
        import org.junit.Test;
        import org.junit.runner.RunWith;
        import org.springframework.beans.factory.annotation.Autowired;
        import org.springframework.boot.test.SpringApplicationConfiguration;
        import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


        import static org.junit.Assert.assertEquals;
        import static org.junit.Assert.assertNotNull;
        import static org.junit.Assert.assertNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {RepositoryConfiguration.class})
public class ProfileTester {

    private ProfileRepository profileRepository;

    @Autowired
    public void setProfileRepository(ProfileRepository profiletRepository) {
        this.profileRepository = profileRepository;
    }

    @Test
    public void profileSave(){
        //setup product
        Profile profile = new Profile();
        profile.setNumberID(1);
        profile.setFirstName("Test");
        profile.setLastName("User");

        //save product, verify has ID value after save
        assertNull(profile.getNumberID()); //null before save
        profileRepository.save(profile);
        assertNotNull(profile.getNumberID()); //not null after save

        //fetch from DB
        Profile fetchedProfile = profileRepository.findOne(1);

        //should not be null
        assertNotNull(fetchedProfile);

        //should equal
        assertEquals(profile.getId(), fetchedProfile.getId());

        //update description and save
        fetchedProfile.setNumberID(1);
        profileRepository.save(fetchedProfile);

        //get from DB, should be updated
        Profile fetchedUpdatedProfile = profileRepository.findOne(fetchedProfile.getNumberID());
        assertEquals(fetchedProfile.getId(), fetchedUpdatedProfile.getId());

        //verify count of products in DB
        long productCount = profileRepository.count();
        assertEquals(productCount, 1);

        //get all products, list should only have one
        Iterable<Profile> profiles = profileRepository.findAll();

        int count = 0;

        for(Profile p : profiles){
            count++;
        }

        assertEquals(count, 1);
    }

}