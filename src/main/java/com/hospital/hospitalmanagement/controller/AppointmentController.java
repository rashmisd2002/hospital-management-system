package com.hospital.hospitalmanagement.controller;

import com.hospital.hospitalmanagement.model.Appointment;
import com.hospital.hospitalmanagement.model.Doctor;
import com.hospital.hospitalmanagement.model.Patient;
import com.hospital.hospitalmanagement.repository.AppointmentRepository;
import com.hospital.hospitalmanagement.repository.DoctorRepository;
import com.hospital.hospitalmanagement.repository.PatientRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Controller
@RequestMapping("/appointments")
public class AppointmentController {

    private final AppointmentRepository appointmentRepo;
    private final DoctorRepository doctorRepo;
    private final PatientRepository patientRepo;

    public AppointmentController(AppointmentRepository appointmentRepo,
                                 DoctorRepository doctorRepo,
                                 PatientRepository patientRepo) {
        this.appointmentRepo = appointmentRepo;
        this.doctorRepo = doctorRepo;
        this.patientRepo = patientRepo;
    }

    @GetMapping
    public String listAppointments(Model model) {
        model.addAttribute("appointments", appointmentRepo.findAll());
        model.addAttribute("doctors", doctorRepo.findAll());
        model.addAttribute("patients", patientRepo.findAll());
        model.addAttribute("appointment", new Appointment());
        return "appointments";
    }

    @PostMapping("/add")
    public String addAppointment(@RequestParam Long doctorId,
                                 @RequestParam Long patientId,
                                 @RequestParam String appointmentDate,
                                 @RequestParam String appointmentTime) {

        Doctor doctor = doctorRepo.findById(doctorId)
                .orElseThrow(() -> new IllegalArgumentException("Doctor not found: " + doctorId));
        Patient patient = patientRepo.findById(patientId)
                .orElseThrow(() -> new IllegalArgumentException("Patient not found: " + patientId));

        LocalDate date = LocalDate.parse(appointmentDate);   // expects yyyy-MM-dd (HTML date input gives this)
        LocalTime time = LocalTime.parse(appointmentTime);   // expects HH:mm (HTML time input gives this)

        Appointment a = new Appointment();
        a.setDoctor(doctor);
        a.setPatient(patient);
        a.setAppointmentDate(date);
        a.setAppointmentTime(time);
        a.setStatus("Scheduled");

        appointmentRepo.save(a);
        return "redirect:/appointments";
    }
}
