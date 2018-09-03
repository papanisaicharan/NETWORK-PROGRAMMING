//client
import java.io.*;
import java.net.*;
class UDPClientAddition1
{
public static void main(String args[]) throws Exception
{
BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));
DatagramSocket clientSocket = new DatagramSocket();
InetAddress IPAddress = InetAddress.getByName("localhost");
System.out.println("------------------------Please enter any of the following-------------- ----------");
System.out.println("---------------------------------------------------------------------------------");
System.out.println(" 1. For ADDITION -------> 1");
System.out.println(" 2. For SUBTRACTION -------> 2");
System.out.println(" 3. For DIVISION -------> 3");
System.out.println(" 4. For MULTIPLICATION -------> 4");
System.out.println(" 5. For LOG -------> 5");
System.out.println("---------------------------------------------------------------------------------");
boolean repeat = true;
while(repeat){
byte[] sendOperator = new byte[1024];
byte[] sendnum1 = new byte[1024];
byte[] sendnum2 = new byte[1024];
byte[] receiveData = new byte[1024];
//collecting operator
System.out.print("Enter the operator and press enter : ");
String operator = inFromUser.readLine();
sendOperator = operator.getBytes();
//collecting number1
System.out.print("Enter the Number and press enter : ");
String num1 = inFromUser.readLine();
sendnum1 = num1.getBytes();
//collecting number2
System.out.print("Enter the Number(if it is for log,then this value is for base) and press enter : ");
String num2 = inFromUser.readLine();
sendnum2 = num2.getBytes();
//sending operator
DatagramPacket sendPacketoperator = new DatagramPacket(sendOperator, 
sendOperator.length,IPAddress,9876);
clientSocket.send(sendPacketoperator);
//sending operator
DatagramPacket sendPacketNum1 = new DatagramPacket(sendnum1, 
sendnum1.length,IPAddress,9876);
clientSocket.send(sendPacketNum1);
//sending operator
DatagramPacket sendPacketNum2 = new DatagramPacket(sendnum2, 
sendnum2.length,IPAddress,9876);
clientSocket.send(sendPacketNum2);
//receiving output
DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
clientSocket.receive(receivePacket);
String modifiedSentence = new String(receivePacket.getData());
System.out.println(" FROM SERVER : " + modifiedSentence.trim());
System.out.println("---------------------------------------------------------------------------------");
System.out.print("Do you want to continue with other operation y/n:");
String flag = inFromUser.readLine();
if(flag.trim().equals("Y") || flag.trim().equals("y")){
repeat = true;

}else{
repeat = false;
}
System.out.println("---------------------------------------------------------------------------------");
}
clientSocket.close();
}
}
