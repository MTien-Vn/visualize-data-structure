package model.interfaces;

public interface IList {
	void Insert(int pos, int value) throws Exception;
	void Remove(int pos) throws Exception;
	int Find(int value) throws Exception;
}
