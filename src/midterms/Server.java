package midterms;

public class Server implements java.lang.Comparable<Server> {
    private int id;
    private Client client;
    private double meanServiceTime;
    private int serviceTime;
    private int startServiceTime;
    private int stopServiceTime;
    private java.util.Random randomNumberGenerator;
    public Server(){
        id=1;
        client = null;
    }
    public Server(int id, Client client, double mean, int time){
        this.randomNumberGenerator = new java.util.Random();
        this.startServiceTime = time;
        this.id = id;
        this.client = client;
        this.meanServiceTime = mean;
        this.serviceTime = randomNumberGenerator.nextInt((int) Math.ceil(mean));
        this.stopServiceTime = startServiceTime + serviceTime;
    }
    public void setId(int n){
        this.id = n;
    }
    public void setClient(Client client){
        this.client = client;
    }
    public void setMeanServiceTime(double mean){
        this.meanServiceTime = mean;
    }
    public void setServiceTime(int t){

        this.serviceTime = t;
    }
    public void setStartServiceTime(int t){
        this.startServiceTime = t;
    }
    public void setStopServiceTime(int t){
        this.stopServiceTime = t;
    }
    public int getId(){
        return id;
    }
    public Client getClient(){
        return client;
    }
    public double getMeanServiceTime(){
        return meanServiceTime;
    }
    public int getServiceTime(){
        return serviceTime;
    }
    public int getStartServiceTime(){
        return startServiceTime;
    }
    public int getStopServiceTime(){
        return stopServiceTime;
    }
    public boolean isIdle(){
        return (client == null);
    }
    public int compareTo(Server another){
        return (this.toString().compareTo(another.toString()));
    }
    public String toString(){
        return ("S"+id);
    }
}