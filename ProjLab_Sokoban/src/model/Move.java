package model;

public class Move {
private Worker owner;
private double remainingForce;
public Move(Worker worker) {
	owner=worker;
	remainingForce=owner.GetForce();
}
public void AcceptPoint() {
	//TODO
}
public boolean DecreaseForce(double decr) {
	return (remainingForce-=decr)>=0;
}
public void UpdateWorker(Worker worker) {
	owner=worker;
}
}
