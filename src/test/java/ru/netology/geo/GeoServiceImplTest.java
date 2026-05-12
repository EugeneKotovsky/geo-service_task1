package ru.netology.geo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;

import static org.junit.jupiter.api.Assertions.*;

class GeoServiceImplTest {

    @Test
    void geoServiceImpl_ByIp_ReturnsCorrectCountry() {

        GeoService geoServiceReal = new GeoServiceImpl();

        assertEquals(Country.RUSSIA, geoServiceReal.byIp("172.0.32.11").getCountry());
        assertEquals(Country.USA, geoServiceReal.byIp("96.44.183.149").getCountry());

        Location localhostLocation = geoServiceReal.byIp("127.0.0.1");
        assertNotNull(localhostLocation);
        assertNull(localhostLocation.getCountry());
    }
}