package edu.pkch.objectpractice;

import edu.pkch.objectpractice.domain.Audience;
import edu.pkch.objectpractice.domain.TicketSeller;

public class Theater {
    private TicketSeller ticketSeller;

    public Theater(TicketSeller ticketSeller) {
        this.ticketSeller = ticketSeller;
    }

    public void enter(Audience audience) {
        ticketSeller.sellTo(audience);
    }
}
