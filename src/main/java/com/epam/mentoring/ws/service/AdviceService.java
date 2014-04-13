package com.epam.mentoring.ws.service;

import java.util.*;

/**
 * @author Maksim_Yashin@epam.com
 */
public class AdviceService {
    private static final List<String> advices = new LinkedList<>();
    private static final List<String> badAdvices = new LinkedList<>();
    private static final String ULTIMATE_UNIVERSE_ADVICE = "42";
    private static final Date tooYoungDate;

    static {
        badAdvices.add("YOU WILL DIE ALONE");
        badAdvices.add("YOU ARE THROWING YOUR LIFE AWAY");
        advices.add("GO OUTSIDE!");
        advices.add("ASK AGAIN LATER");
        advices.add("THINK FOR YOURSELF");
        advices.add("QUESTION AUTHORITY");
        advices.add("DON'T LEAVE THE HOUSE TODAY");
        badAdvices.add("GIVE UP!");
        advices.add("MARRY AND REPRODUCE");
        badAdvices.add("STAY ASLEEP");
        advices.add("WAKE UP");
        badAdvices.add("WE WILL ALL DIE ONE DAY");
        advices.add("FOLLOW THE WHITE RABBIT");
        advices.add("TODAY WAS A GOOD DAY");
        advices.add("WELCOME HOME");
        advices.add("STAY FOREVER YOUNG");
        advices.add("DON'T CROSS THE STREET");
        advices.add("DON'T STOP");
        advices.add("DON'T LOOK BACK");
        badAdvices.add("THEY KNOW");
        advices.add("DON'T LOOK BACK");
        advices.add("TOMORROW COMES TODAY");
        advices.add("NEVER STOP TRYING");
        advices.add("DIE FOR METAL");
        badAdvices.add("THE CAKE IS A LIE");
        badAdvices.add("TRUST NO ONE");
        badAdvices.add("THEY ARE WATCHING");
        advices.add("RUN");
        badAdvices.add("YOU CAN'T ESCAPE");
        advices.addAll(badAdvices);

        Calendar cal = new GregorianCalendar();
        cal.set(1996, Calendar.JANUARY, 1);
        tooYoungDate = cal.getTime();
    }

    public String getRandomAdvice() {
        return getRandomAdviceFromList(advices);
    }

    public String getAdviceForPerson(Date birthDate, String name) {
        if (tooYoungDate.before(birthDate)){
            return getAdviceToPerson(name, ULTIMATE_UNIVERSE_ADVICE);
        }

        return  getAdviceToPerson(name, getRandomAdviceFromList(badAdvices));
    }

    private String getAdviceToPerson(String name, String advice){
        return name + ", " + advice;
    }
    private String getRandomAdviceFromList(List<String> adviceList){
        Collections.shuffle(adviceList);
        return adviceList.get(0);
    }
}
