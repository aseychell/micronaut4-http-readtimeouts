## Micronaut Http Client - Read Timeout issue replication

This project attempts to replicate an issue with the micronaut HTTP client on version 4.1, when invoked from a scheduled task sometimes ends
in a loop of stuck requests, resulting in read timeouts.  The workaround for this issue was to disable the netty client connection pool
so it hints that the issue lies within the new connection pooling introduced in micronaut 4.x.
