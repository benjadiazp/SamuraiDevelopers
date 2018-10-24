package org.springframework.samples.petclinic.notificacion;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

//@Controller
public class NotificacionController {
	/*
	private final RepositorioNotificaciones repo;

    public NotificacionController(RepositorioNotificaciones repo) {
        this.repo = repo;
    }

    //@GetMapping("/vets.html")
    public String showVetList(Map<String, Object> model) {
        Notificaciones nots = new Notificaciones();
        nots.getNotList().addAll(this.repo.findAll());
        model.put("nots", nots);
        return "vets/vetList";
    }

    //@GetMapping({ "/vets" })
    public @ResponseBody Notificaciones showResourcesVetList() {
        Notificaciones nots = new Notificaciones();
        nots.getNotList().addAll(this.repo.findAll());
        return nots;
    }
    */
}
