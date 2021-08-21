package com.ilywebhouse.gpdimobile;

import java.time.LocalTime;
import java.util.HashMap;

/**
 * Genetic Algorithm
 *
 * @author Arjuna Jayasinghe
 * @version 1.0
 */
public class GATest {

	private Schedule scheduleTemplate;

	public void initData() {
		// Grades
		Grade g1 = new Grade("Music");
		Grade g2 = new Grade("Praise and Worship");
		Grade g3 = new Grade("Rebana and Banners");
		Grade g4 = new Grade("Choirs");
		Grade g5 = new Grade("Multimedia");
		Grade g6 = new Grade("Sound System");

		// Musik
		Employee e1 = new Employee("James", g1);
		Employee e2 = new Employee("Harper", g1);
		Employee e3 = new Employee("Mason", g1);
		Employee e4 = new Employee("Evelyn", g1);
		Employee e5 = new Employee("Ella", g1);
		Employee e6 = new Employee("Avery", g1);

		// Worship
		Employee e7 = new Employee("Pdt. Jackson", g2);
		Employee e8 = new Employee("Pdt. Scarlett", g2);
		Employee e9 = new Employee("Pdt. Madison", g2);
		Employee e10 = new Employee("Pdt. Carter", g2);

		// Rebana and banners
		Employee e11 = new Employee("Wyatt", g3);
		Employee e12 = new Employee("Jack", g3);
		Employee e13 = new Employee("Lily", g3);
		Employee e14 = new Employee("Eleanor", g3);

		// Choirs
		Employee e15 = new Employee("Grayson", g4);
		Employee e16 = new Employee("Lillian", g4);
		Employee e17 = new Employee("Addison", g4);
		Employee e18 = new Employee("Aubrey", g4);
		Employee e19 = new Employee("Julian", g4);
		Employee e20 = new Employee("Abraham", g4);
		Employee e27 = new Employee("Jason", g4);
		Employee e28 = new Employee("Rocky", g4);

		// Multimedia
		Employee e21 = new Employee("Leon", g5);
		Employee e22 = new Employee("Cris", g5);
		Employee e23 = new Employee("Jill", g5);

		//Sound System
		Employee e24 = new Employee("Brock", g6);
		Employee e25 = new Employee("Ben", g6);
		Employee e26 = new Employee("Sena", g6);

		// Create schedule template.
		scheduleTemplate = new Schedule();
		HashMap<Employee, Day[]> employeeScheduleDays = new HashMap<>();
		fillDays(employeeScheduleDays, e1);
		fillDays(employeeScheduleDays, e2);
		fillDays(employeeScheduleDays, e3);
		fillDays(employeeScheduleDays, e4);
		fillDays(employeeScheduleDays, e5);
		fillDays(employeeScheduleDays, e6);
		fillDays(employeeScheduleDays, e7);
		fillDays(employeeScheduleDays, e8);
		fillDays(employeeScheduleDays, e9);
		fillDays(employeeScheduleDays, e10);
		fillDays(employeeScheduleDays, e11);
		fillDays(employeeScheduleDays, e12);
		fillDays(employeeScheduleDays, e13);
		fillDays(employeeScheduleDays, e14);
		fillDays(employeeScheduleDays, e15);
		fillDays(employeeScheduleDays, e16);
		fillDays(employeeScheduleDays, e17);
		fillDays(employeeScheduleDays, e18);
		fillDays(employeeScheduleDays, e19);
		fillDays(employeeScheduleDays, e20);
		fillDays(employeeScheduleDays, e21);
		fillDays(employeeScheduleDays, e22);
		fillDays(employeeScheduleDays, e23);
		fillDays(employeeScheduleDays, e24);
		fillDays(employeeScheduleDays, e25);
		fillDays(employeeScheduleDays, e26);
		fillDays(employeeScheduleDays, e27);
		fillDays(employeeScheduleDays, e28);

		scheduleTemplate.setEmployeeScheduleDays(employeeScheduleDays);
		// Staffing.
		HashMap<Grade, Staffing[]> staffingMap = new HashMap<>();
		Staffing[] staffingMusik = new Staffing[]{new Staffing(3), new Staffing(3), new Staffing(3), new Staffing(3), new Staffing(3),new Staffing(3)};
		Staffing[] staffingWorship = new Staffing[]{new Staffing(1), new Staffing(1), new Staffing(1), new Staffing(1), new Staffing(1),new Staffing(1)};
		Staffing[] staffingRebana = new Staffing[]{new Staffing(1), new Staffing(1), new Staffing(1), new Staffing(1), new Staffing(1),new Staffing(1)};
		Staffing[] staffingChoirs = new Staffing[]{new Staffing(2), new Staffing(2), new Staffing(2), new Staffing(2), new Staffing(2),new Staffing(2)};
		Staffing[] staffingMultimedia = new Staffing[]{new Staffing(1), new Staffing(1), new Staffing(1), new Staffing(1), new Staffing(1),new Staffing(1)};
		Staffing[] staffingSound = new Staffing[]{new Staffing(1), new Staffing(1), new Staffing(1), new Staffing(1), new Staffing(1),new Staffing(1)};

		staffingMap.put(g1, staffingMusik);
		staffingMap.put(g2, staffingWorship);
		staffingMap.put(g3, staffingRebana);
		staffingMap.put(g4, staffingChoirs);
		staffingMap.put(g5, staffingMultimedia);
		staffingMap.put(g6, staffingSound);

		scheduleTemplate.setStaffings(staffingMap);

		// Assignments by grades
		Schedule.ListMap<Grade, Assignment> assignmentsByGrade = new Schedule.ListMap<>();
		scheduleTemplate.setAssignmentsByGrade(assignmentsByGrade);
	}


	/**
	 * Creates empty schedule(Day objects for a specific day range) for an employee.
	 *
	 * @param employeeScheduleDays
	 * @param employee
	 */
	private void fillDays(HashMap<Employee, Day[]> employeeScheduleDays, Employee employee) {
		Day[] days = new Day[6];
		for (int i = 0; i < 6; i++) {
			days[i] = new Day();
		}
		employeeScheduleDays.put(employee, days);
	}

	public void runGA() {
		GAConfig cfg = new GAConfig();
		// Create aniInitial population
		Population population = new Population(GAConfig.POPULATION_SIZE, scheduleTemplate, true);
		GeneticAlgorithm ga = new GeneticAlgorithm(scheduleTemplate);
		int evolutionCount = 0;
		double currentBestFitness = population.getBestFitness();
		printSchedule(evolutionCount, population.getFirst());
		int noFitnessChange = 0;
		while (currentBestFitness != 1.0) {
			population = ga.evolve(population);
			evolutionCount++;
			double bestFitness = population.getBestFitness();
			if (bestFitness == currentBestFitness) {
				noFitnessChange++;
			}
			currentBestFitness = bestFitness;
			printSchedule(evolutionCount, population.getFirst());
			// Break the evolution if the evolutionCount has reached the maximum or the fitness has not been changed in last 100 iterations.
			if (noFitnessChange == GAConfig.BREAK_ON_NOFITNESSCHANGE || evolutionCount >= GAConfig.MAX_EVOLUTIONS) {
				break;
			}
		}
	}

	public void printSchedule(int evolutionCount, Schedule finalSchedule) {
		System.out.println("Evolution count : " + evolutionCount);
		finalSchedule.getEmployeeScheduleDays().forEach((e, days) -> {
			if(e.getGrade().getName().equals("Music")){
				System.out.printf("%35s -> ", e);
				for (Day d : days) {
					System.out.printf("%15s |  ", d);
				}
				System.out.println("");
			}

		});
		System.out.println("Fitness : " + finalSchedule.getFitness());
		System.out.print("-----------------------------------------------------------------------------");
		System.out.println("-------------------------------------------------------------");
	}
	
	public static void main(String[] args){
		GATest gaTest = new GATest();
		gaTest.initData();
		gaTest.runGA();
	}
}
