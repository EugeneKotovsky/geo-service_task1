package ru.netology.sender;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MessageSenderImplTest {

    private GeoService geoMock;
    private LocalizationService locMock;
    private MessageSenderImpl sender;

    @BeforeEach
    void setUp() {
        geoMock = mock(GeoService.class);
        locMock = mock(LocalizationService.class);
        sender = new MessageSenderImpl(geoMock, locMock);
    }

    @Test
    void send_RussianIp_ReturnsRussianText() {
        String ip = "172.0.32.11";
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        Location russiaLocation = new Location("Moscow", Country.RUSSIA, null, 0);

        when(geoMock.byIp(ip)).thenReturn(russiaLocation);
        when(locMock.locale(Country.RUSSIA)).thenReturn("Добро пожаловать");

        String result = sender.send(headers);

        assertEquals("Добро пожаловать", result);
        verify(geoMock).byIp(ip);
    }

    @Test
    void send_UsaIp_ReturnsEnglishText() {
        String ip = "96.44.183.149";
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ip);
        Location usaLocation = new Location("New York", Country.USA, null, 0);

        when(geoMock.byIp(ip)).thenReturn(usaLocation);
        when(locMock.locale(Country.USA)).thenReturn("Welcome");

        String result = sender.send(headers);

        assertEquals("Welcome", result);
        verify(geoMock).byIp(ip);
    }



}