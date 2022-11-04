package com.esprit.examen.services;

import static org.junit.Assert.*;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.esprit.examen.entities.CategorieProduit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CategorieServiceImplTest {
	@Autowired
	ICategorieProduitService CategorieService;
	
	@Test
	public void testAddCategorieService() {
		List<CategorieProduit> CategorieProduits = CategorieService.retrieveAllCategorieProduits();
		int expected=CategorieProduits.size();
		CategorieProduit C = new CategorieProduit("55","ff");
		CategorieProduit savedProduit= CategorieService.addCategorieProduit(C);
		assertEquals(expected+1, CategorieService.retrieveAllCategorieProduits().size());
		assertNotNull(savedProduit.getLibelleCategorie());
		CategorieService.deleteCategorieProduit(savedProduit.getIdCategorieProduit());
		
	} 
	
	@Test
	public void testAddCategorieOptimized() {

		CategorieProduit C = new CategorieProduit("55","ff");
		CategorieProduit savedProduit= CategorieService.addCategorieProduit(C);
		assertNotNull(savedProduit.getIdCategorieProduit());
		assertSame("55", savedProduit.getCodeCategorie());
		assertTrue(savedProduit.getLibelleCategorie()=="ff");		
	} 
	 
	@Test
	public void testDeleteStock() {
		CategorieProduit C = new CategorieProduit("55","ff");
		CategorieProduit savedProduit= CategorieService.addCategorieProduit(C);
		CategorieService.deleteCategorieProduit(savedProduit.getIdCategorieProduit());
		assertNull(CategorieService.retrieveCategorieProduit(savedProduit.getIdCategorieProduit()));
	}

}
