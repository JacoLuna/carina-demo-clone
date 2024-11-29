package com.zebrunner.carina.demo;

import com.zebrunner.carina.api.APIMethodPoller;
import com.zebrunner.carina.core.IAbstractTest;
import com.zebrunner.carina.core.registrar.ownership.MethodOwner;
import com.zebrunner.carina.core.registrar.tag.Priority;
import com.zebrunner.carina.core.registrar.tag.TestPriority;
import com.zebrunner.carina.demo.api.entity.DeleteEntityMethod;
import com.zebrunner.carina.demo.api.entity.GetEntityMethods;
import com.zebrunner.carina.demo.api.entity.PostEntityMethod;
import com.zebrunner.carina.demo.api.entity.PutEntityMethod;
import com.zebrunner.carina.demo.api.user.DeleteUserMethod;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.invoke.MethodHandles;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class APIEntityTest implements IAbstractTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Test()
    @MethodOwner(owner = "jaco")
    public void testCreateEntity() throws Exception {
        LOGGER.info("POST test");
        setCases("4555,54545");
        PostEntityMethod api = new PostEntityMethod();
        api.setProperties("api/entities/_post/postEntity.properties");

        AtomicInteger counter = new AtomicInteger(0);

        api.callAPIWithRetry()
                .withLogStrategy(APIMethodPoller.LogStrategy.ALL)
                .peek(rs -> counter.getAndIncrement())
                .until(rs -> counter.get() == 4)
                .pollEvery(1, ChronoUnit.SECONDS)
                .stopAfter(10, ChronoUnit.SECONDS)
                .execute();
        api.validateResponse();
    }

    @Test()
    @MethodOwner(owner = "jaco")
    public void testGetEntities() {
        LOGGER.info("GET test");
        GetEntityMethods getEntityMethods = new GetEntityMethods();
        getEntityMethods.callAPIExpectSuccess();
//        getEntityMethods.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        getEntityMethods.validateResponseAgainstSchema("api/entities/_get/rs.schema");
    }

    @Test()
    @MethodOwner(owner = "jaco")
    @TestPriority(Priority.P1)
    public void testDeleteEntities() {
        LOGGER.info("DELETE test");
        DeleteEntityMethod deleteEntityMethod = new DeleteEntityMethod();
        deleteEntityMethod.setProperties("api/entities/_delete/deleteEntity.properties");
        deleteEntityMethod.callAPIExpectSuccess();
        deleteEntityMethod.validateResponse();
    }

    @Test()
    @MethodOwner(owner = "jaco")
    public void testUpdateEntity() throws Exception {
        LOGGER.info("PUT test");
        PutEntityMethod api = new PutEntityMethod();
        api.setProperties("api/entities/_put/putEntity.properties");

        AtomicInteger counter = new AtomicInteger(0);

        api.callAPIWithRetry()
                .withLogStrategy(APIMethodPoller.LogStrategy.ALL)
                .peek(rs -> counter.getAndIncrement())
                .until(rs -> counter.get() == 4)
                .pollEvery(1, ChronoUnit.SECONDS)
                .stopAfter(10, ChronoUnit.SECONDS)
                .execute();


        api.validateResponse();
    }
}
