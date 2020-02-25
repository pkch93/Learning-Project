package edu.pkch.reactive;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

public class Application {

    public static void main(String[] args) {
        Publisher<Alarm> alarmPublisher = new AlarmPublisher();
        Subscriber<Alarm> alarmSubscriber = new AlarmSubscriber();
        alarmPublisher.subscribe(alarmSubscriber);
    }
}
