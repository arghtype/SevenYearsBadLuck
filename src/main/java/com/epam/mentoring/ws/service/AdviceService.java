package com.epam.mentoring.ws.service;

import java.util.*;

/**
 * @author Maksim_Yashin@epam.com
 */
public class AdviceService {
    private static final List<String> neutral;
    private static final List<String> badLuck;
    private static final String ULTIMATE_UNIVERSE_ADVICE = "42";
    private static final Date tooYoungDate;
    private final Random rnd;

    static {
        badLuck = Arrays.asList(
                "YOU WILL DIE ALONE",
                "YOU ARE THROWING YOUR LIFE AWAY",
                "GIVE UP!",
                "STAY ASLEEP",
                "WE WILL ALL DIE ONE DAY",
                "THEY KNOW",
                "THE CAKE IS A LIE",
                "TRUST NO ONE",
                "THEY ARE WATCHING",
                "YOU CAN'T ESCAPE",
                "NO ONE LOVES YOU",
                "YOU ARE NOT A SMART MAN, AREN'T YOU?"
        );
        neutral = Arrays.asList(
                "GO OUTSIDE!",
                "ASK AGAIN LATER",
                "THINK FOR YOURSELF",
                "QUESTION AUTHORITY",
                "DON'T LEAVE THE HOUSE TODAY",
                "MARRY AND REPRODUCE",
                "WAKE UP",
                "FOLLOW THE WHITE RABBIT",
                "TODAY WAS A GOOD DAY",
                "WELCOME HOME",
                "STAY FOREVER YOUNG",
                "DON'T CROSS THE STREET",
                "DON'T STOP",
                "DON'T LOOK BACK",
                "DON'T LOOK BACK",
                "TOMORROW COMES TODAY",
                "NEVER STOP TRYING",
                "DIE FOR METAL",
                "RUN"
        );

        Calendar cal = new GregorianCalendar();
        cal.set(1996, Calendar.JANUARY, 1);
        tooYoungDate = cal.getTime();
    }

    public AdviceService() {
        rnd = new Random();
    }

    public String getRandomAdvice() {
        return getRandomAdviceFromList(neutral);
    }

    public String getAdviceForPerson(Date birthDate, String name) {
        if (tooYoungDate.before(birthDate)) {
            return getAdviceToPerson(name, ULTIMATE_UNIVERSE_ADVICE);
        }
        return getAdviceToPerson(name, getRandomAdviceFromList(badLuck));
    }

    private String getAdviceToPerson(String name, String advice) {
        return name + ", " + advice;
    }

    private String getRandomAdviceFromList(List<String> adviceList) {
        return adviceList.get(rnd.nextInt(adviceList.size()));
    }
}
