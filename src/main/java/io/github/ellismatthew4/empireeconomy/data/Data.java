package io.github.ellismatthew4.empireeconomy.data;

import java.util.HashMap;
import java.util.Map;

public class Data {
    public Map<String, Integer> currency;
    public Boolean challengeActive;
    public String emperor;
    public String challenger;

    public void init() {
        if (currency == null) {
            currency = new HashMap<>();
        }
        if (challengeActive == null) {
            challengeActive = false;
        }
    }
}
