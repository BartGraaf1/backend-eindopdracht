package com.backendeindopdracht.bartdegraaf.service;

import com.backendeindopdracht.bartdegraaf.exceptions.NotFoundException;
import com.backendeindopdracht.bartdegraaf.exceptions.RecordNotFoundException;
import com.backendeindopdracht.bartdegraaf.model.CarPartUsed;
import com.backendeindopdracht.bartdegraaf.model.RepairAction;
import com.backendeindopdracht.bartdegraaf.model.RepairEvent;
import com.backendeindopdracht.bartdegraaf.repository.CarPartUsedRepository;
import com.backendeindopdracht.bartdegraaf.repository.RepairActionRepository;
import com.backendeindopdracht.bartdegraaf.repository.RepairEventRepository;
import com.backendeindopdracht.bartdegraaf.repository.CarRepository;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.Optional;

@Service
public class RepairEventServiceImpl implements RepairEventService {
    private final RepairEventRepository repairEventRepository;
    private final CarRepository carRepository;
    private final RepairActionRepository repairActionRepository;
    private final CarPartUsedRepository carPartUsedRepository;

    @Autowired
    public RepairEventServiceImpl(RepairEventRepository repairEventRepository, CarRepository carRepository, RepairActionRepository repairActionRepository, CarPartUsedRepository carPartUsedRepository) {
        this.repairEventRepository = repairEventRepository;
        this.carRepository = carRepository;
        this.repairActionRepository = repairActionRepository;
        this.carPartUsedRepository = carPartUsedRepository;
    }

    @Override
    public RepairEvent saveRepairEvent(RepairEvent toRepairEvent, Long carId) {
        var optionalCar = carRepository.findById(carId);

        if (optionalCar.isEmpty()) {
            throw new NotFoundException();
        }

        var car = optionalCar.get();
        var repairEvent = new RepairEvent();
        repairEvent.setId(toRepairEvent.getId());
        repairEvent.setCar(car);
        repairEvent.setComment(toRepairEvent.getComment());
        repairEvent.setDateOfEvent(toRepairEvent.getDateOfEvent());
        repairEvent.setRoutineService(toRepairEvent.getRoutineService());
        repairEvent.setIsEventPayed(toRepairEvent.getIsEventPayed());
        repairEvent.setIsEventApproved(toRepairEvent.getIsEventApproved());

        return repairEventRepository.save(repairEvent);
    }


    @Override
    public RepairEvent updateRepairEvent(RepairEvent toRepairEvent, Long carId) {
        var optionalCar = carRepository.findById(carId);

        if (optionalCar.isEmpty()) {
            throw new NotFoundException();
        }

        var car = optionalCar.get();
        var repairEvent = new RepairEvent();
        repairEvent.setId(toRepairEvent.getId());
        repairEvent.setCar(car);
        repairEvent.setComment(toRepairEvent.getComment());
        repairEvent.setDateOfEvent(toRepairEvent.getDateOfEvent());
        repairEvent.setRoutineService(toRepairEvent.getRoutineService());
        repairEvent.setIsEventPayed(toRepairEvent.getIsEventPayed());
        repairEvent.setIsEventApproved(toRepairEvent.getIsEventApproved());

        Optional<RepairEvent> optionalRepairEventToUpdate = repairEventRepository.findById(repairEvent.getId());
        if(optionalRepairEventToUpdate.isPresent()) {
            return repairEventRepository.save(repairEvent);
        } else {
            throw new RecordNotFoundException("Repair event does not exist");
        }
    }

    @Override
    public void deleteRepairEvent(Long id) {
        repairEventRepository.deleteById(id);
    }

    @Override
    public List<RepairEvent> getRepairEvents() {
        return repairEventRepository.findAll();
    }

    @Override
    public RepairEvent getRepairEvent(Long id) {
        Optional<RepairEvent> optionalRepairEvent = repairEventRepository.findById(id);
        if(optionalRepairEvent.isPresent()) {
            return repairEventRepository.getById(id);
        }else{
            throw new RecordNotFoundException("Car issue does not exist");
        }
    }

    @Override
    public String getRepairEventInvoice(Long id) throws FileNotFoundException {
        var totalPrice = 0.00;
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        Optional<RepairEvent> optionalRepairEvent = repairEventRepository.findById(id);
        if(optionalRepairEvent.isPresent()) {
            var event = repairEventRepository.getById(id);
            var car = event.getCar();
            var customer = car.getCustomer();

            Document document = new Document();
            String filePath = "files/invoice" + id + ".pdf";
            PdfWriter.getInstance(document, new FileOutputStream(filePath));

            document.open();
            Font fontHeader = FontFactory.getFont(FontFactory.COURIER, 16, Font.BOLD, Color.BLACK);
            Font fontRegular = FontFactory.getFont(FontFactory.COURIER, 12, Color.BLACK);
            Font fontRegularBold = FontFactory.getFont(FontFactory.COURIER, 12, Font.BOLD, Color.BLACK);

            document.add(new Chunk("Invoice for: " + car.getLicensePlate(), fontHeader).setUnderline(0.1f, -2f));
            document.add(new Paragraph("\n"));
            document.add(new Paragraph("Customer: " + customer.getFirstname() + " " + customer.getLastname(), fontRegular));
            document.add(new Paragraph("Email: " + customer.getEmailAddress(), fontRegular));
            document.add(new Paragraph("Phone number: " + customer.getPhoneNumber(), fontRegular));
            document.add(new Paragraph("Date of service: " + formatter.format(event.getDateOfEvent()), fontRegular));
            document.add(new Paragraph("\n"));

            if(event.getRoutineService()){
                totalPrice += 40.0;
                document.add(new Paragraph("Cost for routine service: 40,0", fontRegular));
                document.add(new Paragraph("\n"));
            }

            document.add(new Paragraph("Service description: " + event.getComment(), fontRegular));
            document.add(new Paragraph("\n"));

            document.add(new Paragraph("Things done to the car: ", fontRegularBold));


            List<RepairAction> repairActions = repairActionRepository.getByRepairEventId(id);
            for (RepairAction repairAction: repairActions) {

                document.add(new Paragraph("\tDescription: " + repairAction.getDescription(), fontRegular));
                document.add(new Paragraph("\tCost: " + repairAction.getCost(), fontRegular));
                document.add(new Paragraph("\tParts used: ", fontRegularBold));

                totalPrice += repairAction.getCost();

                List<CarPartUsed> carPartUseds = carPartUsedRepository.getByRepairActionId(repairAction.getId());
                for (CarPartUsed carPartUsed: carPartUseds) {
                    var carPartPrice = carPartUsed.getCarPartStock().getPrice();
                    var carPartAmount = carPartUsed.getAmountUsed();
                    var carPartDescription = carPartUsed.getCarPartStock().getDescription();

                    document.add(new Paragraph("\t\t\tPart used: " + carPartDescription, fontRegular));
                    document.add(new Paragraph("\t\t\tPart price: " + carPartPrice, fontRegular));
                    document.add(new Paragraph("\t\t\tAmount used: " + carPartAmount, fontRegular));
                    document.add(new Paragraph("\n"));

                    totalPrice += carPartPrice * carPartAmount;
                }
            }

            document.add(new Paragraph("\n\n"));
            document.add(new Paragraph("Total price: " + totalPrice, fontHeader));

            document.close();

            return filePath;
        }else{
            throw new RecordNotFoundException("Repair event does not exist");
        }
    }
}
