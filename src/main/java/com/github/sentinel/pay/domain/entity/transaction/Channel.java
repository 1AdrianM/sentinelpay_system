package com.github.sentinel.pay.domain.entity.transaction;

public enum Channel {

        ATM("Automated Teller Machine", true),
                POS("Point of Sale", true),
                ONLINE("Online/E-commerce", false),
                MOBILE_APP("Mobile Application", false),
                PHONE("Phone/IVR", false),
                BRANCH("Bank Branch", true),
                WIRE("Wire Transfer", false),
                ACH("ACH Transfer", false),
                PEER_TO_PEER("P2P Transfer", false);

        private final String description;
        private final boolean cardPresent;

        Channel(String description, boolean cardPresent) {
        this.description = description;
        this.cardPresent = cardPresent;
    }

        public String getDescription() {
        return description;
    }

        public boolean isCardPresent() {
        return cardPresent;
    }

    }

