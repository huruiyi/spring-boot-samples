依次修改server.port, 并启动
    *ClientApplication0，
    *ClientApplication1，
    *ClientApplication2，可模拟高可用
 
server.port=4444
SpringCloudEurekaClientApplication0

server.port=5555
SpringCloudEurekaClientApplication1

server.port=6666
SpringCloudEurekaClientApplication2
