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
import com.esprit.examen.entities.Operateur;
import com.esprit.examen.repositories.OperateurRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class OpServiceImplTest {
	
	@MockBean
	OperateurRepository ro;
	
	@Autowired
	IOperateurService operateurService;

	
	@Test
	@Order(0)
	public void testAddOperateur() {
		List<Operateur> operateur = operateurService.retrieveAllOperateurs();
		int expected=operateur.size();
		Operateur o = new Operateur();
		
		Operateur savedOperateur= operateurService.addOperateur(o);
		when(ro.save(o)).thenReturn(o);
		assertEquals(expected+1, operateurService.retrieveAllOperateurs().size());
		assertNotNull(savedOperateur.getIdOperateur());
		
		operateurService.deleteOperateur(savedOperateur.getIdOperateur());;
		
	} 
	
	 
	
	@Test
	@Order(1)
	public void testDeleteOperateur() {
		Operateur o = new Operateur();
		Operateur savedOprateur = operateurService.addOperateur(o);
		operateurService.deleteOperateur(savedOprateur.getIdOperateur());
		verify(ro, atLeast(1)).deleteById(savedOprateur.getIdOperateur());
		assertNull(operateurService.retrieveOperateur(savedOprateur.getIdOperateur()));
		assertTrue(savedOprateur.getIdOperateur()== o.getIdOperateur());
	}

}
