package edu.pkch.reactive;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;

public class AlarmPublisher implements Publisher<Alarm> {

    private Subscriber<? super Alarm> subscriber;

    @Override
    public void subscribe(Subscriber<? super Alarm> subscriber) {
        this.subscriber = subscriber;
        subscriber.onSubscribe(new AlarmSubscription(subscriber));
    }
}
