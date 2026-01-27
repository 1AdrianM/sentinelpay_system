package com.github.sentinel.pay.domain.transaction;

import com.github.sentinel.pay.domain.entity.shared.Location;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class LocationTest {

    @Test
    void IsTransactionLocationUnusual(){
        Location lastLocation= new Location("San Pedro","Dominican Republic");
        Location usual=new Location("Rome","Italy");
       Assertions.assertTrue(usual.isUnusualComparedTo(lastLocation));

    }
}
