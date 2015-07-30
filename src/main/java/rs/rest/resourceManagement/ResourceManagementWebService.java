package rs.rest.resourceManagement;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import rs.model.Reservation;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ResourceManagementWebService {

    @RequestMapping(value = "/rest/reservation/{reservationId}", method = RequestMethod.GET)
    public @ResponseBody List<String> getReservation(@PathVariable String reservationId) {

        List<String> strings = new ArrayList<>();
        strings.add("asd");

        return strings;

//        Reservation reservation = new Reservation();
//        reservation.setId(Long.valueOf(reservationId));
//        return reservation;
    }

/*    @RequestMapping(method = RequestMethod.GET)
    public Reservation getReservation(Model model) {
        model.addAttribute("message", "Hello World!");
        return new Reservation();
    }*/
}