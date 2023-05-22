#!/usr/bin/env python
from mininet.cli import CLI
from mininet.link import Link,TCLink,Intf
from mininet.net import Mininet
from mininet.node import RemoteController

if '__main__' == __name__:
    net = Mininet(link=TCLink)
    h1 = net.addHost('h1', mac='00:00:00:00:01:00')
    h2 = net.addHost('h2', mac='00:00:00:00:02:00')
    h3 = net.addHost('h3', mac='00:00:00:00:03:00')
    h4 = net.addHost('h4', mac='00:00:00:00:04:00')
    h5 = net.addHost('h5', mac='00:00:00:00:05:00')
    Link(h1, h5)
    Link(h2, h5)
    Link(h3, h5)
    Link(h4, h5)
    net.build()
    h5.cmd("ifconfig h5-eth0 0")
    h5.cmd("ifconfig h5-eth1 0")
    h5.cmd("ifconfig h5-eth2 0")
    h5.cmd('ifconfig h5-eth3 0')
    h5.cmd("brctl addbr br0")
    h5.cmd("brctl addif br0 h5-eth0")
    h5.cmd("brctl addif br0 h5-eth1")
    h5.cmd("brctl addif br0 h5-eth2")
    h5.cmd('brctl addif br0 h5-eth3')
    h5.cmd("ifconfig br0 up")
    h5.cmd(" bridge monitor fdb >> bridge_logs.txt &")
    h1.cmd("tcpdump -n -e -i h1-eth0 >> host1.txt &")
    h2.cmd("tcpdump -n -e -i h2-eth1 >> host1.txt &")
    h3.cmd("tcpdump -n -e -i h3-eth2 >> host1.txt &")
    h4.cmd("tcpdump -n -e -i h4-eth3 >> host1.txt &")
    CLI(net)
    net.stop()