package taxcollector;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import taxcollector.exception.NotDeclaredNeighborhood;
import electricRegistry.util.Convert;
import electricRegistry.util.HouseWithoutConsumptions;

public class TaxCollectorBussines {
	private static TaxCollectorBussines singleton;
	private List<HouseWithoutConsumptions> houses;
	private List<Receipt> receipts;
	
	private TaxCollectorBussines(){
		//Estas inicializaciones dependen del fichero de vecindario que se cargue
		//houses = new LinkedList<HouseWithoutConsumptions>();
		//receipts = new LinkedList<Receipt>();
	}
	
	public static TaxCollectorBussines getInstance(){
		if(singleton == null)
			singleton = new TaxCollectorBussines();
		return singleton;
	}
	
	public Receipt findReceipt(int number){
		Receipt rc = null;
		Iterator<Receipt> it = receipts.iterator();
		boolean flag = false;
		
		while(it.hasNext() && !flag){
			rc = it.next();
			if(rc.getHouseNumber() == number)
				flag = true;
		}
		
		return flag ? rc : null;
	}
	
	public void deleteReceipt(int number) throws NotDeclaredNeighborhood{
		if(receipts == null)
			throw new NotDeclaredNeighborhood("Debe cargar un vecindario");
		else{
			Receipt rc = findReceipt(number);
			receipts.remove(rc);
		}
	}
	
	public void loadDataHouses(File path) throws IOException, ClassNotFoundException{
		RandomAccessFile raf = new RandomAccessFile(path, "r");
		
		int size = raf.readInt();
		
		houses = new ArrayList<HouseWithoutConsumptions>(size);
		receipts = new ArrayList<Receipt>(size);
		
		while(size > 0){
			int length = raf.readInt();
			byte[] b = new byte[length];
			raf.read(b);
			HouseWithoutConsumptions ho = (HouseWithoutConsumptions)Convert.toObject(b);
			houses.add(ho);
			size--;
		}
		
		raf.close();
	}
	
	public List<Receipt> getReceipts() throws NotDeclaredNeighborhood{
		if(receipts == null)
			throw new NotDeclaredNeighborhood("No ha sido declarado un vecindario sobre el cual trabajar");
		return receipts;
	}
	
	public List<HouseWithoutConsumptions> getHouses() throws NotDeclaredNeighborhood{
		if(houses == null)
			throw new NotDeclaredNeighborhood("No ha sido declarado un vecindario sobre el cual trabajar");
		return houses;
	}
	
	public void loadReceipt(File path) throws IOException, NotDeclaredNeighborhood, ClassNotFoundException{
		if(houses == null)
			throw new NotDeclaredNeighborhood("No ha sido declarado un vecindario sobre el cual trabajar");
		else{
			RandomAccessFile raf = new RandomAccessFile(path, "r");
			
			int size = raf.readInt();
			
			if(receipts == null)
				receipts = new ArrayList<Receipt>(size);
			
			while(size > 0){
				int length = raf.readInt();
				byte[] b = new byte[length];
				raf.read(b);
				Receipt rc = (Receipt)Convert.toObject(b);
				receipts.add(rc);
				size--;
			}
			
			raf.close();
		}
	}
	
	public void generateReceipts(File path) throws IOException, NotDeclaredNeighborhood{
		if(receipts == null)
			throw new NotDeclaredNeighborhood("No ha sido declarado un vecindario sobre el cual trabajar");
		else
		{
			RandomAccessFile raf = new RandomAccessFile(path, "rw");
			
			raf.writeInt(receipts.size());
			for(Receipt rc : receipts){
				byte[] b = Convert.toBytes(rc);
				raf.writeInt(b.length);
				raf.write(b);				
			}
			
			raf.close();
		}
	}
}
