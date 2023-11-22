package electricRegistry;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import library.sort.Sort;
import taxcollector.Receipt;
import ui.util.GraphicSupport;
import electricRegistry.entities.DailyConsumption;
import electricRegistry.entities.House;
import electricRegistry.util.Convert;
import electricRegistry.util.HouseWithRealAndNotRealConsumption;
import electricRegistry.util.HouseWithoutConsumptions;

public class Neighborhood {
	private static Neighborhood baseSingleton;
	
	private List<House>	houseList;
	private File houseFile;
	
	private File consumptionsFile;
	
	private Neighborhood() {
		houseList = new LinkedList<House>();
		houseFile = new File("data/house.dat");
		consumptionsFile = new File("data/consumptions.dat");
	}
		
	public static Neighborhood getInstance(){
		if(baseSingleton == null)
			baseSingleton = new Neighborhood();
		return baseSingleton;
	}

	//posibilidad de obtener la lista de casas
	public List<House> getHousesList(){
		return houseList;
	}
	

	public House findHouseByNumber(int number){
		House h = null;
		boolean flag = false;
		Iterator<House> it = houseList.iterator();
		
		while(it.hasNext() && !flag){
			h = it.next();
			if(h.getNumber() == number)
				flag = true;
		}
		
		return flag ? h : null;
	}
	
	public List<House> findHouseCoincidence(String cad){
		List<House> list = new LinkedList<House>();
		
		for(House h : houseList)
			if(String.valueOf(h.getNumber()).contains(cad))
				list.add(h);
		
		return list;
	}
	
	public int totalConsumption(){
		int total = 0;
		
		for(House h : houseList)
			total += h.totalConsumption();
		
		return total;
	}
	
	/*public void llenar(){
		House h = findHouseByNumber(78);
		JOptionPane.showMessageDialog(null, h);
		if(h != null) ponerValores(h);
	}
	
	public void ponerValores(House house){
		Calendar data = Calendar.getInstance();
		data.add(Calendar.DAY_OF_MONTH, -14);
		int initial = 97;
		int tmp = 0;
		Calendar k = null;
		Random rm = new Random();
		JOptionPane.showMessageDialog(null, data.get(Calendar.DAY_OF_MONTH)+"/"+(data.get(Calendar.MONTH)+1)+"/"+data.get(Calendar.YEAR));
		for(int i = 0; i < 31; i++){
			k = (Calendar) data.clone();
			tmp =  rm.nextInt(40) + 1;
			System.out.print(tmp +" ");
			DailyConsumption dc = new DailyConsumption(initial, initial + tmp, k);
			house.getConsumptions().add(dc);
			initial += tmp;
			data.add(Calendar.DAY_OF_MONTH, 1);
		}
	}*/

	
	//salvar los consumos de cada hogar
	public void createConsumptionsFile()throws IOException{
		RandomAccessFile consumptions = new RandomAccessFile(consumptionsFile, "rw");
		consumptions.writeInt(houseList.size());
		for(House h : houseList){
			consumptions.writeInt(h.getNumber());
			consumptions.writeInt(h.getConsumptions().size());
			for(DailyConsumption dC: h.getConsumptions()){
				byte [] consumption = Convert.toBytes(dC);
				consumptions.writeInt(consumption.length);
				consumptions.write(consumption);				
			}
		}
		consumptions.close();
	}
	
	//cargar los datos de los consumos correspondientes a los hogares
	public void loadConsumptionData() throws IOException, ClassNotFoundException{
		RandomAccessFile consumptions = new RandomAccessFile(consumptionsFile, "r");
		
		int size = consumptions.readInt();
		
		while(size > 0){
			int numberHouse = consumptions.readInt();
			House h = findHouseByNumber(numberHouse);
			int count = consumptions.readInt();
			List<DailyConsumption> list = new ArrayList<DailyConsumption>(count);
			while(count > 0){
				int length = consumptions.readInt();
				byte[] b = new byte[length];
				consumptions.read(b);
				DailyConsumption dc = (DailyConsumption)Convert.toObject(b);
				list.add(dc);
				count--;
			}
			if(h != null)
				h.setConsumptions(list);
			size--;
		}
		
		consumptions.close();
	}
	
	//salvar los datos de las casas sin sus consumos en un fichero
	public void createHouseFile() throws IOException{
		RandomAccessFile houses = new RandomAccessFile(houseFile ,"rw");
		houses.writeInt(houseList.size());
		for(House house : houseList){
			HouseWithoutConsumptions  h = new HouseWithoutConsumptions(house);
			byte [] b = Convert.toBytes(h);
			houses.writeInt(b.length);
			houses.write(b);			
		}
		houses.close();
	}
	
	//cargar los datos de los hogares
	public void loadHouseData() throws IOException, ClassNotFoundException{
		RandomAccessFile houses = new RandomAccessFile(houseFile ,"r");
		houseList.clear();
		
		int size = houses.readInt();
		while(size > 0){
			int  length = houses.readInt();
			byte[] b = new byte[length];
			houses.read(b);
			HouseWithoutConsumptions h = (HouseWithoutConsumptions)Convert.toObject(b);
			houseList.add(new House(h));
			size--;
		}
		
		houses.close();
	}
	
	//obtener listado de recibos a partir del fichero
	private LinkedList<Receipt> listOfReceipt(String addressFileReceipt)throws IOException, ClassNotFoundException{
		LinkedList<Receipt> receiptList = new LinkedList<Receipt> ();
		RandomAccessFile file = new RandomAccessFile(addressFileReceipt, "r");
		
		int countEntryBlock = file.readInt();
		for(int i = 0; i < countEntryBlock; i++){
			int receiptDimension = file.readInt();
			byte [] receipt = new byte [receiptDimension];
			file.read(receipt);
			Receipt r = (Receipt)Convert.toObject(receipt);
			receiptList.add(r);
		}
		
		file.close();
		
		return receiptList;
	}
	
	//buscar el recibo de una casa dado su numero y la lista de recibos
	private Receipt findReceiptOfHouse(LinkedList<Receipt> receipts, int houseNumber) throws IOException, ClassNotFoundException{
		Receipt receipt = null;
		boolean found = false;

		Iterator<Receipt> iterator = receipts.iterator();
		
		while(iterator.hasNext() && !found){
			receipt = iterator.next();
			if(receipt.getHouseNumber() == houseNumber)
				found = true;		
		}		
		
		return found ? receipt : null;		
	}
	
	//obtener un fichero binario con las casas que tienen su consumo alterado
	public List<House> createFileWithHighConsumptions(String addressFileReceipt)throws IOException, ClassNotFoundException{
		RandomAccessFile houses = new RandomAccessFile("reportes/ConsumoAlterado.dat", "rw");
		LinkedList<Receipt> receipts = listOfReceipt(addressFileReceipt);
		
		List<House> list = new LinkedList<House>();
		
		int countBlocks = 0;
		houses.writeInt(countBlocks);
		for(House h: houseList){
			Receipt r = findReceiptOfHouse(receipts, h.getNumber());
			if(r != null)
				if(r.getPayment() > h.valueOfConsumptions()){
					list.add(h);
					countBlocks ++;
					HouseWithRealAndNotRealConsumption house = new HouseWithRealAndNotRealConsumption(h, h.valueOfConsumptions(), r.getPayment());
					byte [] b = Convert.toBytes(house);
					houses.writeInt(b.length);
					houses.write(b);
				}			
		}
		houses.seek(0);
		houses.writeInt(countBlocks);
		houses.close();
		
		return list;
	}
	
	//buscar la posicion de la casa con menos consumo en una lista de casas
	private int indexMinValueOfConsumption(List<House> houses){
		int indexOfMinValue = 0;
		
		for(int i = 1; i < houses.size(); i++)
			if(houses.get(i).valueOfConsumptions() < houses.get(indexOfMinValue).valueOfConsumptions()){
				indexOfMinValue = i;
			}
		
		return indexOfMinValue;
	}
	
	//buscar las 10 casas con mas consumo
	private List<House> housesWithMoreConsumption (){
		List<House> moreConsumptions = new ArrayList<House>(10);
		Iterator<House> itHouse = houseList.iterator();
		House house = null;
		int c = 0;
		
		while(itHouse.hasNext() && c < 10){
			house = itHouse.next();
			moreConsumptions.add(house);
			c++;
		}
		
		
		while(itHouse.hasNext()){
			house = itHouse.next();
			int indexOfMin = indexMinValueOfConsumption(moreConsumptions);
			if(house.valueOfConsumptions()> moreConsumptions.get(indexOfMin).valueOfConsumptions())
				moreConsumptions.set(indexOfMin, house);			
		}
		return moreConsumptions;		
	}
	
	// crear fichero texto con las 10 casas con mas consumo ordenadas de acuerdo a la cantidad de habitantes
	public House[] createHousesTextFile()throws IOException, ClassNotFoundException{
		List<House> list =  housesWithMoreConsumption();
		House[] houses =  new House[list.size()];
		list.toArray(houses);
		House.sortBypopulation();
		Sort.mergeSort_R(houses);
		
		File textFile = new File("reportes/10CasasConMasConsumo.txt");
		PrintWriter pw = new PrintWriter(textFile);
		for(House h : houses){
			//pw.println(houses[i].getNumber()+" "+houses[i].getAddress()+" "+ houses[i].getPopulation()+" "+houses[i].valueOfConsumptions());
			pw.println("Número: \t"+h.getNumber());
			pw.println("Habitantes: \t"+h.getPopulation());
			pw.println("Dirección: \t"+h.getAddress());
			pw.println("Consumo: \t"+GraphicSupport.roundTwoDecimal(h.valueOfConsumptions()));
			pw.println();
		}
		
		pw.close();	
		
		return houses;
	}
}