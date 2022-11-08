package com.esprit.examen.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.atLeast;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.CategorieProduit;
import com.esprit.examen.repositories.CategorieProduitRepository;

import org.mockito.junit.jupiter.MockitoExtension;

@SpringBootTest
@RunWith(SpringRunner.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(MockitoExtension.class)
//webhooktest
public class CategorieServiceImplTest {

	@MockBean
    CategorieProduitRepository m;
	@Autowired
	
	ICategorieProduitService CategorieService;
	

	private CategorieProduit P1 = new CategorieProduit(1L,"55","44");
	private CategorieProduit P2 = new CategorieProduit(1L,"55","44");
	  


	@Test
	public void testAddCategorieProduit() {

		when(m.save(P1)).thenReturn(P1);
		assertNotNull(P1);
		assertEquals(P1, CategorieService.addCategorieProduit(P1)); 
		
		
		
	} 
    
    @Test 
    public void retrieveAllCategorieProduit() {
    	when(CategorieService.retrieveAllCategorieProduits()).thenReturn(Stream
    			.of(P1,P2)
    			.collect(Collectors.toList()));
    	assertEquals(2,CategorieService.retrieveAllCategorieProduits().size());
    	System.out.println("CategorieProduit retrived");
    }
    
   
    
    @Test
    public void DeleteCategorieProduit() {
    	m.save(P1);
    	CategorieService.deleteCategorieProduit(P1.getIdCategorieProduit());

		

    	verify(m, atLeast(1)).deleteById(P1.getIdCategorieProduit());
    	System.out.println("CategorieProduit deleted");
    	
    }

    
    @Test 
    public void UpdateCategorieProduit() {
    	when(CategorieService.save(P1)).thenReturn(P1);
    	assertNotNull(P1);
    	assertEquals(P1, CategorieService.updateCategorieProduit(P1));
    	System.out.println("CategorieProduit updated");
    }
   
     @Test
    public void retrieveCategorieProduit() {
    	when(m.findById(P1.getIdCategorieProduit())).thenReturn(Optional.of(P1));
    	assertEquals(P1, CategorieService.retrieveCategorieProduit(P1.getIdCategorieProduit()));
    	System.out.println("CategorieProduit Retrived");
    }
}

	

