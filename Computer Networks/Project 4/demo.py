from mininet.net import Mininet
from mininet.node import Controller, RemoteController, OVSKernelSwitch, UserSwitch
from mininet.cli import CLI
from mininet.log import setLogLevel
from mininet.link import Link, TCLink

def topology():
        net = Mininet( controller=RemoteController, link=TCLink, switch=OVSKernelSwitch )
        # Add hosts and switches
        a1 = net.addHost( 'a1', ip="20.10.172.1/24", mac="00:00:00:00:00:01" )
        a2 = net.addHost( 'a2', ip="20.10.172.2/24", mac="00:00:00:00:00:02" )
        b1 = net.addHost( 'b1', ip="20.10.172.51/24", mac="00:00:00:00:00:03" )
        b2 = net.addHost( 'b2', ip="20.10.172.52/24" mac=)
        b3 = net.addHost( 'b3')
        a = net.addHost('a', mac="00:00:00:00:01:00" )
        b = net.addHost('b', mac="00:00:00:00:02:00")
        c = net.addHost('c', mac="00:00:00:00:03:00")
        s1 = net.addSwitch( 's1')
        c0 = net.addController( 'c0', controller=RemoteController, ip='127.0.0.1', port=6633 )
        net.addLink( r1, s1 )
        net.addLink( h1, s1 )
        net.addLink( h2, s1 )
        net.addLink( h3, s1 )
        net.build()
        c0.start()
        s1.start( [c0] )
        r1.cmd("ifconfig r1-eth0 0")
        r1.cmd("ip addr add 10.0.1.1/24 brd + dev r1-eth0")
        r1.cmd("ip addr add 10.0.2.1/24 brd + dev r1-eth0")
        r1.cmd("ip addr add 10.0.3.1/24 brd + dev r1-eth0")
        r1.cmd("echo 1 > /proc/sys/net/ipv4/ip_forward")
        h1.cmd("ip route add default via 10.0.1.1")
        h2.cmd("ip route add default via 10.0.2.1")
        h3.cmd("ip route add default via 10.0.3.1") 
        s1.cmd("ovs-ofctl add-flow s1 priority=1,arp,actions=flood")
        s1.cmd("ovs-ofctl add-flow s1 priority=65535,ip,dl_dst=00:00:00:00:01:00,actions=output:1")
        s1.cmd("ovs-ofctl add-flow s1 priority=10,ip,nw_dst=10.0.1.0/24,actions=output:2")
        s1.cmd("ovs-ofctl add-flow s1 priority=10,ip,nw_dst=10.0.2.0/24,actions=output:3")
        s1.cmd("ovs-ofctl add-flow s1 priority=10,ip,nw_dst=10.0.3.0/24,actions=output:4")
        print ("*** Running CLI")
        CLI( net )
        print ("*** Stopping network")
        net.stop()

if __name__ == '__main__':

    setLogLevel( 'info' )

    topology() 