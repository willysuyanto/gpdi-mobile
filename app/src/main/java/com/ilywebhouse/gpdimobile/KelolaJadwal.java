package com.ilywebhouse.gpdimobile;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class KelolaJadwal extends AppCompatActivity {

    private Toolbar toolbar;
    private Button generate, add;

    TextView tvPop, tvMut, tvCross, tvTour, tvElit;

    ProgressDialog dialog;
    FirebaseFirestore fs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kelola_jadwal);

        Bundle bundle = getIntent().getExtras();
        String toolbarTitle = bundle.getString("menu");

        fs = FirebaseFirestore.getInstance();

        generate = findViewById(R.id.btn_generate);
        add = findViewById(R.id.btn_add);

        GAConfig cf = new GAConfig();

        tvCross = findViewById(R.id.cros);
        tvElit = findViewById(R.id.elit);
        tvMut = findViewById(R.id.mut);
        tvPop = findViewById(R.id.pop);
        tvTour = findViewById(R.id.tour);

        tvPop.setText(String.valueOf(GAConfig.POPULATION_SIZE));
        tvCross.setText(String.valueOf(GAConfig.CROSSOVER_RATE));
        tvMut.setText(String.valueOf(GAConfig.MUTATION_RATE));
        tvTour.setText(String.valueOf(GAConfig.TOURNAMENT_SIZE));
        tvElit.setText(String.valueOf(GAConfig.ELITE_SCHEDULES));

        toolbar = findViewById(R.id.my_toolbar);
        toolbar.setTitle(toolbarTitle);
        toolbar.setNavigationOnClickListener(v -> {
            finish();
        });

        initData();

        add.setOnClickListener(v -> {
            Intent intent = new Intent(KelolaJadwal.this, AddPeserta.class);
            startActivity(intent);
        });

        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog = ProgressDialog.show(KelolaJadwal.this, "Mohon Tunggu",
                        "Sedang Membuat Jadwal", true);
                runGA();
            }
        });

    }

    private Schedule scheduleTemplate;

    public void initData() {
        fs.collection("Peserta").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()){
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
                    task.getResult().forEach(queryDocumentSnapshot -> {
                        Log.d("onComplete: ", queryDocumentSnapshot.getString("bagian"));
                        switch (queryDocumentSnapshot.getString("bagian")) {
                            case "Music":
                                Log.d(queryDocumentSnapshot.getString("bagian"), queryDocumentSnapshot.getString("nama"));
                                fillDays(employeeScheduleDays, new Employee(queryDocumentSnapshot.getString("nama"), g1));
                                break;
                            case "Praise and Worship":
                                fillDays(employeeScheduleDays, new Employee(queryDocumentSnapshot.getString("nama"), g2));
                                break;
                            case "Rebana and Banners":
                                fillDays(employeeScheduleDays, new Employee(queryDocumentSnapshot.getString("nama"), g3));
                                break;
                            case "Choir":
                                fillDays(employeeScheduleDays, new Employee(queryDocumentSnapshot.getString("nama"), g4));
                                break;
                            case "Multimedia":
                                fillDays(employeeScheduleDays, new Employee(queryDocumentSnapshot.getString("nama"), g5));
                                break;
                            case "Sound System":
                                fillDays(employeeScheduleDays, new Employee(queryDocumentSnapshot.getString("nama"), g6));
                                break;
                        }
                    });
                    scheduleTemplate.setEmployeeScheduleDays(employeeScheduleDays);


                    Log.d("initData: ", scheduleTemplate.getEmployeeScheduleDays().toString());

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
                    assignmentsByGrade.add(g1, new Assignment("Full"));

                    assignmentsByGrade.add(g2, new Assignment("Full"));

                    assignmentsByGrade.add(g3, new Assignment("Full"));

                    assignmentsByGrade.add(g4, new Assignment("Full"));

                    assignmentsByGrade.add(g5, new Assignment("Full"));

                    assignmentsByGrade.add(g6, new Assignment("Full"));

                    scheduleTemplate.setAssignmentsByGrade(assignmentsByGrade);
                    Log.d("Initial Data", scheduleTemplate.getEmployeeScheduleDays().toString());

                }
            }
        });

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



    void runGA() {
        Population population = new Population(GAConfig.POPULATION_SIZE, scheduleTemplate, true);
        GeneticAlgorithm ga = new GeneticAlgorithm(scheduleTemplate);
        int evolutionCount = 0;
        double currentBestFitness = population.getBestFitness();
        //printSchedule(evolutionCount, population.getFirst());
        int noFitnessChange = 0;
        while (currentBestFitness != 1.0) {
            population = ga.evolve(population);
            evolutionCount++;
            double bestFitness = population.getBestFitness();
            if (bestFitness == currentBestFitness) {
                noFitnessChange++;
            }
            currentBestFitness = bestFitness;
            //printSchedule(evolutionCount, population.getFirst());
            // Break the evolution if the evolutionCount has reached the maximum or the fitness has not been changed in last 100 iterations.
            if (noFitnessChange == GAConfig.BREAK_ON_NOFITNESSCHANGE || evolutionCount >= GAConfig.MAX_EVOLUTIONS) {
                Toast.makeText(KelolaJadwal.this, "Gagal Membuat Jadwal! generasi ke:"+evolutionCount, Toast.LENGTH_SHORT).show();
                break;
            }
            if(currentBestFitness==1.0){
                dialog.hide();
                Map<String, Object> empMus = new HashMap<>();
                Map<String, Object> empPra = new HashMap<>();
                Map<String, Object> empReb = new HashMap<>();
                Map<String, Object> empCho = new HashMap<>();
                Map<String, Object> empMul = new HashMap<>();
                Map<String, Object> empSou = new HashMap<>();

                Map<String, Object> emp = new HashMap<>();

//                Grade g1 = new Grade("Music");
//                Grade g2 = new Grade("Praise and Worship");
//                Grade g3 = new Grade("Rebana and Banners");
//                Grade g4 = new Grade("Choirs");
//                Grade g5 = new Grade("Multimedia");
//                Grade g6 = new Grade("Sound System");
                AtomicInteger dcount = new AtomicInteger(1);
                //Map<String, Object> jad = new HashMap<>();
                List<String> list = new ArrayList<>();
                population.getFirst().getEmployeeScheduleDays().forEach((e, days) -> {
                    Map<String, Object> jad;
                    switch (e.getGrade().getName()) {
                        case "Music":
                            jad = new HashMap<>();
                            for (Day d : days) {
                                jad.put("d" + dcount, d.toString());
                                dcount.getAndIncrement();
                                if(d.getAssignment()!=null){
                                Log.d("pegawai: "+e.getName(), d.toString());
                                }
                            }
                            empMus.put(e.getName(), jad);
                            dcount.set(1);
                            break;
                        case "Praise and Worship":
                            jad = new HashMap<>();
                            for (Day d : days) {
                                jad.put("d" + dcount, d.toString());
                                dcount.getAndIncrement();
                            }
                            empPra.put(e.getName(), jad);
                            dcount.set(1);
                            break;
                        case "Rebana and Banners":
                            jad = new HashMap<>();
                            for (Day d : days) {
                                jad.put("d" + dcount, d.toString());
                                dcount.getAndIncrement();
                            }
                            empReb.put(e.getName(), jad);
                            dcount.set(1);
                            break;
                        case "Choirs":
                            jad = new HashMap<>();
                            for (Day d : days) {
                                jad.put("d" + dcount, d.toString());
                                dcount.getAndIncrement();
                            }
                            empCho.put(e.getName(), jad);
                            dcount.set(1);
                            break;
                        case "Multimedia":
                            jad = new HashMap<>();
                            for (Day d : days) {
                                jad.put("d" + dcount, d.toString());
                                dcount.getAndIncrement();
                            }
                            empMul.put(e.getName(), jad);
                            dcount.set(1);
                            break;
                        case "Sound System":
                            jad = new HashMap<>();
                            for (Day d : days) {
                                jad.put("d" + dcount, d.toString());
                                dcount.getAndIncrement();
                            }
                            empSou.put(e.getName(), jad);
                            dcount.set(1);
                            break;
                    }
                });

                emp.put("Music", empMus);
                emp.put("Praise and Worship", empPra);
                emp.put("Rebana and Banners", empReb);
                emp.put("Choir", empCho);
                emp.put("Multimedia", empMul);
                emp.put("Sound System", empSou);

                double finalCurrentBestFitness = currentBestFitness;
                int finalEvolutionCount = evolutionCount;
                fs.collection("jadwal").document("assign").set(emp).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(KelolaJadwal.this, "Berhasil Membuat Jadwal! fitness :" + finalCurrentBestFitness +" generasi ke :"+ finalEvolutionCount, Toast.LENGTH_SHORT).show();
                        }
                    }
                });
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

}