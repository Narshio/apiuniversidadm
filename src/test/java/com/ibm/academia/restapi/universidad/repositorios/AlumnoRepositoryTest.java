package com.ibm.academia.restapi.universidad.repositorios;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.ibm.academia.restapi.universidad.datos.DatosDummy;
import com.ibm.academia.restapi.universidad.modelo.entidades.Alumno;
import com.ibm.academia.restapi.universidad.modelo.entidades.Carrera;
import com.ibm.academia.restapi.universidad.modelo.entidades.Persona;

@DataJpaTest
public class AlumnoRepositoryTest 
{
	@Autowired
	@Qualifier("repositorioAlumno")
	private PersonaRepository alumnoRepository;
	
	@Autowired
	private CarreraRepository carreraRepository;
	
	@Test
	@DisplayName("Test: Buscar alumnos por nombre carrera")
	void buscarAlumnoPorNombreCarrera()
	{
		//Given
		Iterable<Persona> personas = alumnoRepository.saveAll(
                Arrays.asList(
                		DatosDummy.alumno01(),
                		DatosDummy.alumno02(),
                		DatosDummy.alumno03())
        );
		
		Carrera carrera = carreraRepository.save(DatosDummy.carrera01());
		personas.forEach(alumno -> ((Alumno)alumno).setCarrera(carrera));
		alumnoRepository.saveAll(personas);
		
		//When
		String carreraNombre = "Ingenieria en Sistemas";
		List<Persona> expected =  (List<Persona>) ((AlumnoRepository)alumnoRepository).buscarAlumnosPorNombreCarrera(carreraNombre);
		
		//Then
		assertThat(expected.size() == 3).isTrue();	
	}
	
	@Test
	@DisplayName("Test: Buscar alumnos por nombre carrera sin valores")
    void buscarAlumnosPorNombreCarreraSinValores() 
	{
        //Given
        Iterable<Persona> personas = alumnoRepository.saveAll(
                Arrays.asList(
                		DatosDummy.alumno01(),
                		DatosDummy.alumno02(),
                		DatosDummy.alumno03())
        );

        Carrera carrera = carreraRepository.save(DatosDummy.carrera01());
        personas.forEach(alumno -> ((Alumno)alumno).setCarrera(carrera));
        alumnoRepository.saveAll(personas);

        //When
        String carreraNombre = "Ingenieria en Alimentos";
        List<Persona> expected = (List<Persona>) ((AlumnoRepository) alumnoRepository).buscarAlumnosPorNombreCarrera(carreraNombre);

        //Then
        assertThat(expected.isEmpty()).isTrue();
    }
}
