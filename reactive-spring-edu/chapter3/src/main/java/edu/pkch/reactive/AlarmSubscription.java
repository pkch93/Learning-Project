package edu.pkch.reactive;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.LongStream;

public class AlarmSubscription implements Subscription {
    private final Subscriber<? super Alarm> subscriber;
    private final ExecutorService executorService;
    private AtomicInteger count = new AtomicInteger(0);

    public AlarmSubscription(Subscriber<? super Alarm> subscriber) {
        this.subscriber = subscriber;
        this.executorService = Executors.newFixedThreadPool(2);
    }

    @Override
    public void request(long n) {
        System.out.println("request");
        if (n < 0) {
            throw new IllegalArgumentException();
        } else if (count.intValue() >= 10) {
            cancel();
        } else {
            LongStream.rangeClosed(1, n)
                .forEach((idx) -> executorService.execute(() -> {
                    subscriber.onNext(new Alarm(idx));
                    count.incrementAndGet();
                }));

            if (count.intValue() < 10) {
                request(3);
            }
        }
    }

    @Override
    public void cancel() {
        subscriber.onComplete();
    }
}
