package uniandes.dpoo.taller2.procesamiento;
import java.util.ArrayList;




import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Restaurante {
	
	private  ArrayList<Ingrediente> ingredientes;
	private  ArrayList<Pedido> pedidos;
	private Pedido pedidoEnCurso;
	private ArrayList<Producto> menuBase;
	private ArrayList<Combo> combos;

	
	public Restaurante() {

	}
	
	public Restaurante(ArrayList<Ingrediente> ingredientes,ArrayList<Producto> menuBase,ArrayList<Combo> combos) {
		this.combos=combos;
		this.pedidos= new ArrayList<Pedido>();
		this.ingredientes=ingredientes;
		this.menuBase=menuBase;
		
	}
	
	public void iniciarPedido(String nombreCliente,String direccionCliente) {
		 pedidoEnCurso = new Pedido(direccionCliente,nombreCliente);	
	}
	public void	cerrarYGuardarPedido(){
	pedidos.add(pedidoEnCurso);
	File file = new File("./data/factura.txt");
	pedidoEnCurso.guardarFactura(file);
	}
	
	public Pedido getPedidoEnCurso() {
		return pedidoEnCurso;
		
	}
	
	public ArrayList<Producto> getMenuBase(){
		return menuBase;
	}
	
	public ArrayList<Ingrediente> getIngredientes(){
		return ingredientes;
	}
	
	public ArrayList<Combo> getCombos(){
		return combos;
	}
	
	public Restaurante cargarInformacion(File archivoIngredientes,File archivoMenu,File archivoCombos) {
		cargarMenu(archivoMenu);
		cargarCombos(archivoCombos);
		cargarIngredientes(archivoIngredientes);
		Restaurante rest = new Restaurante(ingredientes,menuBase,combos);
		return rest;
	}
	
	private void cargarCombos(File archivoCombos) {
		try
		{
			combos = LoaderRestaurante.leerInfoArchivoCombos(archivoCombos,menuBase);
			System.out.println("Se cargó Combos ");
		}
		catch (FileNotFoundException e)
		{
			System.out.println("ERROR: el archivo indicado no se encontró.");
		}
		catch (IOException e)
		{
			System.out.println("ERROR: hubo un problema leyendo el archivo.");
			System.out.println(e.getMessage());
		}
	}
	
	private void cargarMenu(File archivoMenu) {

		try
		{
			menuBase = LoaderRestaurante.leerInfoArchivoProductosMenu(archivoMenu);
			System.out.println("Se cargó menu ");
		}
		catch (FileNotFoundException e)
		{
			System.out.println("ERROR: el archivo indicado no se encontró.");
		}
		catch (IOException e)
		{
			System.out.println("ERROR: hubo un problema leyendo el archivo.");
			System.out.println(e.getMessage());
		}
	}
	
	private void cargarIngredientes(File archivoIngredientes) {
		try
		{
			ingredientes = LoaderRestaurante.leerInfoArchivoIngredientes(archivoIngredientes);
			System.out.println("Se cargó Ingredientes ");
		}
		catch (FileNotFoundException e)
		{
			System.out.println("ERROR: el archivo indicado no se encontró.");
		}
		catch (IOException e)
		{
			System.out.println("ERROR: hubo un problema leyendo el archivo.");
			System.out.println(e.getMessage());
		}

	}
	
	
	}

