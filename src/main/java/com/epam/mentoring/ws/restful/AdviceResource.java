package com.epam.mentoring.ws.restful;

import com.epam.mentoring.ws.service.AdviceService;
import org.jboss.logging.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Maksim_Yashin@epam.com
 */
@Path("advice")
public class AdviceResource {

    private static final Logger LOGGER = Logger.getLogger(AdviceResource.class);

    private final AdviceService service;
    private final SimpleDateFormat sdf;

    public AdviceResource() {
        service = new AdviceService();
        sdf = new SimpleDateFormat("dd.MM.yyyy");
    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getRandomAdvice() {
        LOGGER.info("someone asked for random advice!");
        return service.getRandomAdvice();
    }

    @GET
    @Path("/{name}/{birthdate}")
    @Produces(MediaType.TEXT_PLAIN)
    public String getAdvice(@PathParam("name") String name, @PathParam("birthdate") String birthDateString) {
        LOGGER.info("someone asked for advice to " + name + " " + birthDateString);
        Date birthdate;
        try {
            birthdate = sdf.parse(birthDateString);
        } catch (ParseException e) {
            LOGGER.error(e.getMessage());
            birthdate = new Date();
        }
        return service.getAdviceForPerson(birthdate, name);
    }
}
