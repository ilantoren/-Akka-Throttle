Akka-Throttle
=============

<h3>Throttle - Implementing flow control with Akka</h3>

Recently while building and testing an implementation of an application that created a
large number of messages and sent them to a remote system I came accross the following
warning:
<br/>
<p style="color:blue">
<code>

[WARN] [08/28/2014 09:12:13.782] [LocalSystem-akka.remote.default-remote-dispatcher-11] [akka.tcp://LocalSystem@127.0.0.1:2473/system/endpointManager/reliableEndpointWriter-akka.tcp%3A%2F%2FSimulation%40127.0.0.1%3A2552-0/endpointWriter] [361391] buffered messages in EndpointWriter for [akka.tcp://Simulation@127.0.0.1:2552]. You should probably implement flow control to avoid flooding the remote connection.

</code>
</p>
<br/>

With hindsight, this is an obvious problem with distributed systems.  One component is not matched in bandwith with another (<a href="http://en.wikipedia.org/wiki/Impedance_matching">
<i>Impedence matching</i></a>).  A concrete example of that would be the storing of events on a webpage into a database.  Overall the only way to solve this problem is to scale (up or out)
the database bandwidth, but for short lived transients this can be solved by buffering the output and serving it up a bit slower.  One solution is to throttle by time
(see <a href="http://letitcrash.com/post/28901663062/throttling-messages-in-akka-2">LetItCrash.com</a>) this is another solution.  It is pretty low-tech, using an in 
memory queue, but it could easily be changed to use either EHCache or Memcached instead.
