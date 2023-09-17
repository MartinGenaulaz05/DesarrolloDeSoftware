package com.utn.Trabajo_Practico_N1;

import com.utn.Trabajo_Practico_N1.Enums.Estado;
import com.utn.Trabajo_Practico_N1.Enums.FormaPago;
import com.utn.Trabajo_Practico_N1.Enums.Tipo;
import com.utn.Trabajo_Practico_N1.Enums.TipoEnvio;
import com.utn.Trabajo_Practico_N1.entidades.*;
import com.utn.Trabajo_Practico_N1.repositorios.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class Application {

	@Autowired
	ClienteRepository clienteRepository;
	@Autowired
	DetallePedidoRepository detallePedidoRepository;
	@Autowired
	DomicilioRepository domicilioRepository;
	@Autowired
	FacturaRepository facturaRepository;
	@Autowired
	PedidoRepository pedidoRepository;
	@Autowired
	ProductoRepository productoRepository;
	@Autowired
	RubroRepository rubroRepository;
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
		System.out.println("Estoy funcionando");
	}

	@Bean
	CommandLineRunner init(){
		return args -> {
			System.out.println("----------Estoy Funcionando----------");
//creo un rubro
			Rubro rubro = Rubro.builder()
					.denominacion("Comida Elaborada")
					.build();
//creo productos
			Producto producto1 = Producto.builder()
					.tipo(Tipo.MANUFACTURADO)
					.tiempoEstimadoCocina(60)
					.denominacion("Milanesa Napolitana")
					.precioVenta(2500)
					.precioCompra(1500)
					.stockActual(15)
					.stockMínimo(8)
					.unidadMedida("Unidades")
					.receta("Carne, pan rallado, salsa, queso")
					.build();
			Producto producto2 = Producto.builder()
					.tipo(Tipo.MANUFACTURADO)
					.tiempoEstimadoCocina(60)
					.denominacion("Tarta de jamon y queso")
					.precioVenta(2000)
					.precioCompra(1000)
					.stockActual(20)
					.stockMínimo(12)
					.unidadMedida("Unidades")
					.receta("Masa para tarta, jamon, queso")
					.build();
//agrego los productos a rubro
			rubro.agregarProducto(producto1);
			rubro.agregarProducto(producto2);
//Guardo rubro
			rubroRepository.save(rubro);
//creo clientes
			Cliente cliente1 = Cliente.builder()
					.nombre("Martin")
					.apellido("Genaulaz")
					.telefono("23235325")
					.email("asjdnads@gmail.com")
					.build();
			Cliente cliente2 = Cliente.builder()
					.nombre("Pedro")
					.apellido("Sanchez")
					.telefono("23223565")
					.email("fkghgfhgh@gmail.com")
					.build();
//creo sus domicilios
			Domicilio domicilio1 = Domicilio.builder()
					.calle("Santa Rosa")
					.numero("16")
					.localidad("Rivadavia")
					.build();
			Domicilio domicilio2 = Domicilio.builder()
					.calle("Millonario")
					.numero("91218")
					.localidad("Madrid")
					.build();
			Domicilio domicilio3 = Domicilio.builder()
					.calle("Flores")
					.numero("123")
					.localidad("Los Arboles")
					.build();
//agrego domicilios
			cliente1.agregarDomicilio(domicilio1);
			cliente1.agregarDomicilio(domicilio2);
			cliente2.agregarDomicilio(domicilio3);
//creo los detalles de los pedidos
			DetallePedido detallePedido1 = DetallePedido.builder()
					.cantidad(3)
					.subtotal(7500)
					.build();
			DetallePedido detallePedido2 = DetallePedido.builder()
					.cantidad(1)
					.subtotal(2000)
					.build();
			DetallePedido detallePedido3 = DetallePedido.builder()
					.cantidad(2)
					.subtotal(5000)
					.build();


			SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
			String fechaString1 = "2023-05-12";
			String fechaString2 = "2023-06-24";
			Date fecha = formatoFecha.parse(fechaString1);
			Date fecha2 = formatoFecha.parse(fechaString2);
//creo los pedidos
			Pedido pedido1 = Pedido.builder()
					.estado(Estado.ENTREGADO)
					.fecha(fecha)
					.tipoEnvio(TipoEnvio.RETIRA)
					.total(7500)
					.build();
			Pedido pedido2= Pedido.builder()
					.estado(Estado.ENTREGADO)
					.fecha(fecha2)
					.tipoEnvio(TipoEnvio.RETIRA)
					.total(7000)
					.build();
//creo las facturas
			Factura factura1 = Factura.builder()
					.fecha(fecha)
					.total(7300)
					.numero(1)
					.descuento(200)
					.formaPago(FormaPago.EFECTIVO)
					.build();
			Factura factura2 = Factura.builder()
					.fecha(fecha2)
					.total(6500)
					.numero(2)
					.descuento(500)
					.formaPago(FormaPago.EFECTIVO)
					.build();
//agrego los detalles al pedido
			pedido1.agregarDetallePedido(detallePedido1);
			pedido2.agregarDetallePedido(detallePedido2);
			pedido2.agregarDetallePedido(detallePedido3);
//agrego los pedidos al cliente
			cliente1.agregarPedido(pedido1);
			cliente2.agregarPedido(pedido2);
			clienteRepository.save(cliente1);
//vinculo el producto con el detalle
			detallePedido1.setProducto(producto1);
			detallePedido2.setProducto(producto2);
			detallePedido3.setProducto(producto1);
//vinculo factura con el pedido
			pedido1.setFactura(factura1);
			pedido2.setFactura(factura2);
//guardo los clientes
			clienteRepository.save(cliente1);
			clienteRepository.save(cliente2);

			//recupero rubros
			Rubro rubroRecuperado = rubroRepository.findById(rubro.getId()).orElse(null);
			if(rubroRecuperado!= null){
				System.out.println("Denominacion " + rubroRecuperado.getDenominacion());
			}
			//recupero los clientes
			Cliente clienteRecuperada1 = clienteRepository.findById(cliente1.getId()).orElse(null);
			if(clienteRecuperada1!= null){
				System.out.println("Nombre " + clienteRecuperada1.getNombre());
				System.out.println("Apellido " + clienteRecuperada1.getApellido());
				System.out.println("Telefono " + clienteRecuperada1.getTelefono());
				System.out.println("Email " + clienteRecuperada1.getEmail());
				clienteRecuperada1.mostrarDomicilio();
				clienteRecuperada1.mostrarPedido();
				pedido1.mostrarfactura();
			}
			Cliente clienteRecuperada2 = clienteRepository.findById(cliente2.getId()).orElse(null);
			if(clienteRecuperada2!= null){
				System.out.println("Nombre " + clienteRecuperada2.getNombre());
				System.out.println("Apellido " + clienteRecuperada2.getApellido());
				System.out.println("Telefono " + clienteRecuperada2.getTelefono());
				System.out.println("Email " + clienteRecuperada2.getEmail());
				clienteRecuperada2.mostrarDomicilio();
				clienteRecuperada2.mostrarPedido();
				pedido2.mostrarfactura();
			}
		};
	}
}


