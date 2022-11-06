package com.esprit.examen.services;

import static org.junit.Assert.*;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.annotation.Order;
import org.springframework.test.context.junit4.SpringRunner;
import com.esprit.examen.entities.*;
import com.esprit.examen.repositories.FournisseurRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class FournisseurServiceImplTest {
	
	@MockBean
	FournisseurRepository fo;
	
	@Autowired
	IFournisseurService fournisseurService;

	
	@Test
	@Order(0)
	public void testAddFournisseur() {
		List<Fournisseur> fournisseur = fournisseurService.retrieveAllFournisseurs();
		int expected=fournisseur.size();
		Fournisseur f = new Fournisseur("test","test");
		
		Fournisseur savedFournisseur= fournisseurService.addFournisseur(f);
		when(fo.save(f)).thenReturn(f);
		assertEquals(expected+1, fournisseurService.retrieveAllFournisseurs().size());
		assertNotNull(savedFournisseur.getCode());
		
		fournisseurService.deleteFournisseur(savedFournisseur.getIdFournisseur());;
		
	} 
	
	@Test
	@Order(2)
	public void testAddStockOptimized() {

		Fournisseur f = new Fournisseur("test","test");
		Fournisseur savedFournisseur= fournisseurService.addFournisseur(f);
		assertNotNull(savedFournisseur.getIdFournisseur());
		assertSame(f.getCode(), savedFournisseur.getCode());
		assertTrue(savedFournisseur.getCode() == f.getCode());
		when(fo.findById(savedFournisseur.getIdFournisseur())).thenReturn(Optional.of(f));
		fournisseurService.deleteFournisseur(savedFournisseur.getIdFournisseur());
		
		} 
	
	@Test
	@Order(1)
	public void testDeleteStock() {
		Fournisseur f = new Fournisseur("test","test");
		Fournisseur savedFournisseur = fournisseurService.addFournisseur(f);
		fournisseurService.deleteFournisseur(savedFournisseur.getIdFournisseur());
		verify(fo, atLeast(1)).deleteById(savedFournisseur.getIdFournisseur());
		assertNull(fournisseurService.retrieveFournisseur(savedFournisseur.getIdFournisseur()));
		assertTrue(savedFournisseur.getIdFournisseur()== f.getIdFournisseur());
	}

}
