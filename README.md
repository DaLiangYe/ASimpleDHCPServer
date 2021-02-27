# ASimpleDHCPServer

DHCP，全称是Dynamic Host Configuration Protocol（动态主机配置协议），会根据主机的MAC地址等其他信息来动态分配IP地址。每一个主机在不同时刻分配到的IP地址并不是确定的。

假设一台路由设备拥有6.0.6.0到6.0.6.255区间的256个IP地址，现需要按照如下规则进行分配IP地址。识别不同主机的标识是主机的MAC地址。当主机向路由器申请IP地址时，需要提供主机的MAC地址（物理地址）给路由器以供使用。

该项目旨在实现一个简单的DHCP服务，以供学习和实验研究。
————————————————
版权声明：本文为CSDN博主「Mr. 良爷」的原创文章，遵循CC 4.0 BY-SA版权协议，转载请附上原文出处链接及本声明。
原文链接：https://blog.csdn.net/liangcheng0523/article/details/114174767