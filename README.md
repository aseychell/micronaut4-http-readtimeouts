## Micronaut Http Client - Read Timeout issue replication

This project attempts to replicate an issue with the micronaut HTTP client on version 4.1, when invoked from a scheduled task sometimes ends
in a loop of stuck requests, resulting in read timeouts. The workaround for this issue was to disable the netty client connection pool
so it hints that the issue lies within the new connection pooling introduced in micronaut 4.x.

### Replicating the issue

1. Start the application
2. The scheduled task will run regularly calling the HTTP client.
3. At some point the following exception is observed:

```
2023-09-29T09:04:48.419Z [boundedElastic-1] ERROR reactor.core.publisher.Operators - Operator called default onErrorDropped
reactor.core.Exceptions$ErrorCallbackNotImplemented: io.micronaut.http.client.exceptions.ReadTimeoutException: Read Timeout
Caused by: io.micronaut.http.client.exceptions.ReadTimeoutException: Read Timeout
	at io.micronaut.http.client.exceptions.ReadTimeoutException.<clinit>(ReadTimeoutException.java:26)
	at io.micronaut.http.client.netty.DefaultHttpClient.lambda$exchangeImpl$28(DefaultHttpClient.java:1135)
	at reactor.core.publisher.FluxOnErrorResume$ResumeSubscriber.onError(FluxOnErrorResume.java:94)
	at reactor.core.publisher.SerializedSubscriber.onError(SerializedSubscriber.java:124)
	at reactor.core.publisher.FluxTimeout$TimeoutMainSubscriber.handleTimeout(FluxTimeout.java:295)
	at reactor.core.publisher.FluxTimeout$TimeoutMainSubscriber.doTimeout(FluxTimeout.java:280)
	at reactor.core.publisher.FluxTimeout$TimeoutTimeoutSubscriber.onNext(FluxTimeout.java:419)
	at reactor.core.publisher.FluxOnErrorReturn$ReturnSubscriber.onNext(FluxOnErrorReturn.java:162)
	at reactor.core.publisher.MonoDelay$MonoDelayRunnable.propagateDelay(MonoDelay.java:271)
	at reactor.core.publisher.MonoDelay$MonoDelayRunnable.run(MonoDelay.java:286)
	at reactor.core.scheduler.SchedulerTask.call(SchedulerTask.java:68)
	at reactor.core.scheduler.SchedulerTask.call(SchedulerTask.java:28)
	at java.base/java.util.concurrent.FutureTask.run$$$capture(FutureTask.java:264)
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java)
	at java.base/java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:304)
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1136)
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:635)
	at java.base/java.lang.Thread.run(Thread.java:833)
```

4. If the issue cannot be replicated, starting in debug mode sometimes help to trigger the issue.
