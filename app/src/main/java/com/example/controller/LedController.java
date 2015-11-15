package com.example.controller;

import com.example.LedColor;
import com.example.LedConfiguration;
import com.example.LedInfo;
import com.example.LedManager;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.atomic.AtomicReference;

@SuppressWarnings("unused")
@RestController
class LedController {
    @SuppressWarnings("SameReturnValue")
    @RequestMapping("/")
    public String greeting() {
        return "Hello world!";
    }

    /**
     * Sets LED pattern, returns previous LED pattern
     */
    @RequestMapping(value = "/led_pattern", method = RequestMethod.POST)
    public ResponseEntity<LedPatternModel> update(@RequestBody LedPatternModel ledPattern) {
        if(ledPattern != null) {
            LedPatternModel lastPattern = m_lastPattern;

            if(m_ledManager == null) {
                m_ledManager = new LedManager(LedConfiguration.ConfigA);
            }

            m_ledManager.setLed(new LedInfo(LedColor.Red, 0), ledPattern.red);
            m_ledManager.setLed(new LedInfo(LedColor.Blue, 0), ledPattern.blue);
            m_ledManager.setLed(new LedInfo(LedColor.Green, 0), ledPattern.green);
            m_ledManager.setLed(new LedInfo(LedColor.Yellow, 0), ledPattern.yellow);

            m_lastPattern = ledPattern;

            return new ResponseEntity<>(lastPattern, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    /**
     * Easy to test in web browser to make sure server is operating
     */
    @SuppressWarnings("SameReturnValue")
    @RequestMapping("/light")
    public String light() {
        if(m_ledManager == null) {
            m_ledManager = new LedManager(LedConfiguration.ConfigA);
        }

        m_ledManager.setLed(new LedInfo(LedColor.Red, 0), true);
        m_ledManager.setLed(new LedInfo(LedColor.Blue, 0), true);

        return "OK";
    }

    private LedManager m_ledManager;
    private LedPatternModel m_lastPattern = new LedPatternModel();
}
