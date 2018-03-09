package model;

public interface Field {
	void Accept(Placeholder obj,Direction dir);
	void Check(Direction dir);

}
