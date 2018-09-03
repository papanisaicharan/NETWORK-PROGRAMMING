import java.io.*;
import java.net.*;
import java.lang.Math;
class UDPServerAddition1
{
public static void main(String args[]) throws Exception
{
DatagramSocket serverSocket = new DatagramSocket(9876);
while(true)
{
byte[] receiveOperator = new byte[1024];
byte[] receiveNum1 = new byte[1024];
byte[] receiveNum2 = new byte[1024];
byte[] sendData = new byte[1024];
DatagramPacket receivePacketoperator = new 
DatagramPacket(receiveOperator,receiveOperator.length);
serverSocket.receive(receivePacketoperator);
String operator = new String( receivePacketoperator.getData());
DatagramPacket receivePacketNum1 = new DatagramPacket(receiveNum1,receiveNum1.length);
serverSocket.receive(receivePacketNum1);
String number1 = new String( receivePacketNum1.getData());
DatagramPacket receivePacketNum2 = new DatagramPacket(receiveNum2,receiveNum2.length);
serverSocket.receive(receivePacketNum2);
String number2 = new String( receivePacketNum2.getData());
System.out.println("RECEIVED OPERATOR: " + operator.trim());
System.out.println("RECEIVED number1: " + number1.trim());
System.out.println("RECEIVED number2: " + number2.trim());
InetAddress IPAddress = receivePacketoperator.getAddress();
int port = receivePacketoperator.getPort();
int operation ;
double num1 = 0;
double num2 = 0;
double output = 0;
boolean error = false;
//System.out.println(operator.trim().matches("[0-9]+") +""+ number1.trim().matches("[0-9]+")+""+ 
number2.trim().matches("[0-9]+"));
if(operator.trim().matches("[0-9]+") && number1.trim().matches("[0-9]+") && 
number2.trim().matches("[0-9]+")){
operation = Integer.parseInt(operator.trim());
num1 = Double.parseDouble(number1.trim());
num2 = Double.parseDouble(number2.trim());
}else{
operation = 0;
error = true;
}
switch(operation){
case 1:
output = num1 + num2;
break;
case 2:
output = num1 - num2;
break;
case 3:
output = num1 / num2;
break;
case 4:
output = num1 * num2;
break;
case 5:
output = Math.log(num1)/Math.log(num2);
break;
default :
error = true;
}
String sendoutput = String.valueOf(output);
String errormsg = "Entered format is invalid or invalid operator number";
if(error){
sendData = errormsg.getBytes();
DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, 
IPAddress, port);
serverSocket.send(sendPacket);
}else{
sendData = sendoutput.getBytes();
DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, 
IPAddress, port);
serverSocket.send(sendPacket);
}
}
}
}
