package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping
public class JSONController {
    @GetMapping("/flights/flight")
    public Flight flight(){
        Person p = new Person();
        p.firstName = "Some name";
        p.lastName = "Some other name";
        Ticket t= new Ticket();
        t.passenger = p;
        t.price = 200;
        Flight f = new Flight();
        f.departs = "2017-04-21 14:34";
        ArrayList<Ticket> l = new ArrayList<Ticket>();
        l.add(t);
        f.tickets = l;
        return f;
    }

    @GetMapping("/flights")
    public List<Flight> flights(){
        Flight f1 = flight();
        Person p = new Person();
        p.firstName = "Some other name";
        p.lastName = null;
        Ticket t= new Ticket();
        t.passenger = p;
        t.price = 400;
        Flight f2 = new Flight();
        f2.departs = "2017-04-21 14:34";
        ArrayList<Ticket> l = new ArrayList<Ticket>();
        l.add(t);
        f2.tickets = l;
        return Arrays.asList(f1,f2);
    }

    private static class Flight{
        private String departs;
        private List<Ticket> tickets;

        public String getDeparts() {
            return departs;
        }

        public void setDeparts(String departs) {
            this.departs = departs;
        }

        public List<Ticket> getTickets() {
            return tickets;
        }

        public void setTickets(List<Ticket> tickets) {
            this.tickets = tickets;
        }
    }

    private static class Ticket{
        private Person passenger;
        private int price;

        public Person getPassenger() {
            return passenger;
        }

        public void setPassenger(Person passenger) {
            this.passenger = passenger;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }
    }

    private static class Person{
    private String firstName;
    private String lastName;

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }
    }
}
