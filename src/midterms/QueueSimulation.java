/**
 * Name: Aragon, Danielle John P.
 * 9443-IT212/IT212L-SAMCIS-CIS
 * =========================================================================
 * A typical Service system has servers and clients.
 *
 * An arriving client joins the queue of clients.
 * The server serves a client following First Come First Served discipline
 * =========================================================================
 REQUIRED:
 1.Initialize the simulation parameters:
    -averageInterarrival: Average time between client arrivals
    -simulationTimeDuration: Length of the simulation time
    -meanServiceTime: Mean service time for the server
 2.Create a random number generator for arrival times and service times.
 3.Generate the arrival time for the first client using the random arrival generator.
 4.Create an empty queue to hold the clients.
 5.Initialize the client ID to 1.
 6.Create a server object.
 7.Run the simulation from time = 0 to the simulation time duration.
 8.Check if a client arrives at the current time:
    -If the current time equals the next arrival time:
        =Create a new client object with the current client ID and arrival time.
        =Add the client to the queue of waiting clients.
        =Display the queue of waiting clients and the number of clients in the list.
        =Increment the client ID.
        =Generate the random arrival time for the next client.
 9.Check if the server is idle and there is a client waiting to be served:
    -If the server is idle and the queue of waiting clients is not empty:
        =Remove the first client from the queue.
        =Set the server's client to the client being served.
        =Set the start service time for the server to the current time.
        =Generate a random service time for the server.
        =Calculate the time when the server will become free.
        =Set the stop service time for the server.
        =Display that the server has started serving the client and when it will be free.
        =Display the updated queue of waiting clients.
 10.Check if the server has finished serving a client:
    -If the current time equals the stop service time of the server and the current time is greater than 0:
        =Display that the server has finished serving the client.
        =Set the server's client to null, indicating that it is idle.
 11.Repeat steps 8 to 10 until the simulation time duration is reached.
 12.End the simulation.

 */
package midterms;

import java.util.*;
public class QueueSimulation {
    public static void main(String[] args){
        QueueSimulation simulation;
        try {
            simulation = new QueueSimulation();
            simulation.run();
        } catch (Exception ex){
            ex.printStackTrace();
        }
        System.exit(0);
    }
    public void run(){
        java.util.Random randomArrivalGenerator = new java.util.Random();
        java.util.Random randomServiceTimeGenerator = new java.util.Random();
        // Average time between client arrivals
        int averageInterarrival = 4;
        // Total simulation time duration
        int simulationTimeDuration = 50;
        // Mean service time for clients
        double meanServiceTime = 3;
        // Generate random arrival of the first client
        int nextArrivalTime=randomArrivalGenerator.nextInt(averageInterarrival);
        // Create an ArrayList that will hold Queue of Clients
        Queue<Client> myListOfClients = new LinkedList<Client>();
        int clientId=1;
        // Instantiate the server with no assigned client
        Server server = new Server();
        //Let simulation run from time =0 to a set length of time
        for (int time=0; time <= simulationTimeDuration; time +=1) {
            // check if a client arrives
            if (time == nextArrivalTime) {
                System.out.println("Client " + clientId + " has arrived at time = " + nextArrivalTime + ".");
                // Construct a client object and add the client object to the Queue of waiting clients
                Client newClient = new Client(clientId, nextArrivalTime);
                myListOfClients.add(newClient);

                // Show the Queue of waiting clients and the number of waiting clients
                showList(myListOfClients);
                System.out.println("Number of clients in the list = " + myListOfClients.size());
                // Prepare the id of the next client that will arrive next
                clientId += 1;
                // Generate the random arrival time of the next client
                nextArrivalTime += 1 + randomArrivalGenerator.nextInt(averageInterarrival);
                System.out.println("Next client will arrive at time = " + nextArrivalTime);
            }
            // Check if the server is idle and if there is a client waiting to be served
            if (server.isIdle() && !myListOfClients.isEmpty()){
                // Let the server start serving the first client in the Queue
                Client clientToServe = myListOfClients.remove();
                server.setClient(clientToServe);
                server.setStartServiceTime(time);
                // Generate the random length of time for the server to serve the client
                int serveTime = randomServiceTimeGenerator.nextInt((int) meanServiceTime);
                int timeForServerToBecomeFree = time + serveTime;
                server.setStopServiceTime(timeForServerToBecomeFree);
                System.out.println("At time = "+ time + " Server started serving client "+ clientToServe +".");
                System.out.println("Server will be free at time = " + timeForServerToBecomeFree);
                //Show the updated Queue of waiting clients
                showList(myListOfClients);
            }
            // Check if the server is to finish serving a client
            if ( time == server.getStopServiceTime() && time>0) {
                System.out.println("At time = "+ time + " Server finished serving client "+ server.getClient() + "." );
                // Let the status of the server be idle
                server.setClient(null);
            }
        } // end of for
    } // end of run
    public void showList(Queue<Client> a){
        System.out.print("Queue of Waiting Clients: ");
        for(Client c: a){
            System.out.print(c.toString() + " ");
        }
        System.out.println();
        return;
    }
} // end of class
