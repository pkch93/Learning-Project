package edu.pkch.reactive;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.List;

public class AlarmSubscriber implements Subscriber<Alarm> {
    public static final int MAX_QUEUE_SIZE = 10;
    private List<Alarm> alarms = new ArrayList<>();
    private Subscription subscription;

    @Override
    public void onSubscribe(Subscription subscription) {
        this.subscription = subscription;
        subscription.request(3);
    }

    @Override
    public void onNext(Alarm alarm) {
        alarms.add(alarm);
        System.out.println("add alarm");
    }

    @Override
    public void onError(Throwable t) {
        System.out.println("error");
    }

    @Override
    public void onComplete() {
        System.out.println("stream complete");
    }
}
