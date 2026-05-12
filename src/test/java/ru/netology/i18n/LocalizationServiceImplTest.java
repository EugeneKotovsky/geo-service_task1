package ru.netology.i18n;

import org.junit.jupiter.api.Test;
import ru.netology.entity.Country;

import static org.junit.jupiter.api.Assertions.*;

class LocalizationServiceImplTest {

    @Test
    void localizationServiceImpl_Locale_ReturnsCorrectText() {
        LocalizationServiceImpl locService = new LocalizationServiceImpl();

        assertEquals("Добро пожаловать", locService.locale(Country.RUSSIA));
        assertEquals("Welcome", locService.locale(Country.USA));
        assertEquals("Welcome", locService.locale(Country.BRAZIL));
    }
}