package com.esprit.examen.services;
import com.esprit.examen.entities.SecteurActivite;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j

public class SecteurActiviteServiceImplTest {
    @Autowired
    ISecteurActiviteService secteurActiviteService;

    @Test
    public void testAddSecteurActivite() throws ParseException {
        SecteurActivite secteurActivite = new SecteurActivite();
        secteurActivite.setCodeSecteurActivite("1547");
        secteurActivite.setLibelleSecteurActivite("Test Secteur");
        SecteurActivite secteurActiviteAdded = secteurActiviteService.addSecteurActivite(secteurActivite);

        System.out.print("client "+secteurActiviteAdded);
        assertNotNull(secteurActiviteAdded.getIdSecteurActivite());
        assertNotNull(secteurActiviteAdded.getLibelleSecteurActivite());
        assertTrue(secteurActiviteAdded.getLibelleSecteurActivite().length() > 0);
        secteurActiviteService.deleteSecteurActivite(secteurActiviteAdded.getIdSecteurActivite());
    }

    @Test
    public void testRetrieveAllSecteurs() throws ParseException {
        List<SecteurActivite> secteurActiviteList = secteurActiviteService.retrieveAllSecteurActivite();
        int expected = secteurActiviteList.size() + 1;
        SecteurActivite secteurActivite = new SecteurActivite();
        secteurActivite.setCodeSecteurActivite("1547");
        secteurActivite.setLibelleSecteurActivite("Test Secteur");
        SecteurActivite secteurActiviteAdded = secteurActiviteService.addSecteurActivite(secteurActivite);
        assertEquals(expected, secteurActiviteService.retrieveAllSecteurActivite().size());
        secteurActiviteService.deleteSecteurActivite(secteurActiviteAdded.getIdSecteurActivite());
    }

    @Test
    public void testRetrieveSecteurByID() throws ParseException {
        SecteurActivite secteurActivite = new SecteurActivite();
        secteurActivite.setCodeSecteurActivite("1547");
        secteurActivite.setLibelleSecteurActivite("Test Secteur");
        SecteurActivite secteurActiviteAdded = secteurActiviteService.addSecteurActivite(secteurActivite);
        SecteurActivite retrievedSecteurByID = secteurActiviteService.retrieveSecteurActivite(secteurActiviteAdded.getIdSecteurActivite());
        assertEquals(secteurActiviteAdded.getIdSecteurActivite(), retrievedSecteurByID.getIdSecteurActivite());
        secteurActiviteService.deleteSecteurActivite(secteurActiviteAdded.getIdSecteurActivite());
    }
}
