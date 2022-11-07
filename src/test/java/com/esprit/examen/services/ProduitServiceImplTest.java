package com.esprit.examen.services;

import com.esprit.examen.entities.Produit;
import com.esprit.examen.repositories.ProduitRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@Slf4j
@ExtendWith(MockitoExtension.class)
@SpringBootTest
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProduitServiceImplTest {

    @Mock
    ProduitRepository produitRepo;

    @InjectMocks
    private ProduitServiceImpl produitService;

    private Produit p = Produit.builder().idProduit((long)1).codeProduit("01234UXXU").libelleProduit("Produit 1").prix(10.14f).dateCreation(new Date()).build();
    private Produit p2 = Produit.builder().idProduit((long)1).codeProduit("01234UXXU").libelleProduit("Produit 1 Updated").prix(10.14f).dateCreation(new Date()).build();
    List<Produit> list_produit = new ArrayList<Produit>(){
        {
            add(Produit.builder().idProduit((long)2).codeProduit("01234AABX").libelleProduit("Produit 2").prix(12.14f).dateCreation(new Date()).build());
            add(Produit.builder().idProduit((long)3).codeProduit("ABC123456").libelleProduit("Produit 3").prix(15.14f).dateCreation(new Date()).build());
            add(Produit.builder().idProduit((long)4).codeProduit("123456ABC").libelleProduit("Produit 4").prix(20.14f).dateCreation(new Date()).build());
            add(Produit.builder().idProduit((long)5).codeProduit("ABCDEFFFF").libelleProduit("Produit 5").prix(25.14f).dateCreation(new Date()).build());
        }
    };

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void test1_addProduit(){
        Mockito.when(produitRepo.save(p)).then( e -> {
            list_produit.add(p);
            log.info(list_produit.toString());
            return p;
        });
        Produit produit = produitService.addProduit(p);
        assertNotNull(produit);
        log.info("\n METHODE(addProduit):\n"+produit.toString()+"\n");
    }
    @Test
    public void test2_retrieveProduit(){
        Mockito.when(produitRepo.findById(Mockito.anyLong())).thenReturn(Optional.of(p));
        Produit produit = produitService.retrieveProduit((long) 1);
        assertNotNull(produit);
        log.info("\n METHODE(retrieveProduit):\n"+produit.toString()+"\n");
    }
    @Test
    public void test3_updateProduit(){
        assertNotEquals(p, p2);
        Mockito.when(produitRepo.save(p2)).then( e -> {
            p = p2;
            return p;
        });
        Produit updated = produitService.updateProduit(p2);
        log.info("\n METHODE(updateProduit):\n"+updated.toString());
    }
    @Test
    public void test4_retrieveAllProduit(){
        Mockito.when(produitRepo.findAll()).thenReturn(list_produit);
        List<Produit> liste = produitService.retrieveAllProduits();
        assertNotNull(liste);
        log.info("\n METHODE(retrieveAllProduit):\n"+liste+"\n");
    }
    @Test
    public void test5_deleteProduit(){
        Mockito.doNothing().when(produitRepo).deleteById(Mockito.anyLong());
        produitService.deleteProduit((long) 2);
        assertNotNull(list_produit);
        log.info("\n METHODE(retrieveAllProduit): PRODUIT DELETED !!!!\n");
    }
}