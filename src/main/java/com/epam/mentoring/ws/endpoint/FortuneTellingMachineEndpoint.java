package com.epam.mentoring.ws.endpoint;

import com.epam.mentoring.ws.service.AdviceService;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.Date;

/**
 * @author Maksim_Yashin@epam.com
 */
@WebService
public class FortuneTellingMachineEndpoint {
    private AdviceService adviceService;

    private static final Logger LOGGER = Logger.getLogger(FortuneTellingMachineEndpoint.class);

    public FortuneTellingMachineEndpoint(){
        adviceService = new AdviceService();
    }

    @PostConstruct
    private void checkAdviceService(){
        if (adviceService == null){
            LOGGER.error("ADVICE SERVICE IS NOT INITIALIZED!");
        }
    }

    @WebMethod
    public String giveRandomAdvice(){
        return adviceService.getRandomAdvice();
    }

    @WebMethod
    public String giveAdviceToMe(@WebParam(name = "myBirthDate") Date myBirthDate,
                                 @WebParam(name = "myName") String myName){
        return adviceService.getAdviceForPerson(myBirthDate, myName);
    }
}
