package com.utn.Trabajo_Practico_N1;

import com.utn.Trabajo_Practico_N1.Enums.Estado;
import com.utn.Trabajo_Practico_N1.Enums.TipoEnvio;
import com.utn.Trabajo_Practico_N1.entidades.Cliente;
import com.utn.Trabajo_Practico_N1.entidades.DetallePedido;
import com.utn.Trabajo_Practico_N1.entidades.Domicilio;
import com.utn.Trabajo_Practico_N1.entidades.Pedido;
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
			Cliente cliente = Cliente.builder()
					.nombre("Martin")
					.apellido("Genaulaz")
					.telefono("23235325")
					.email("asjdnads@gmail.com")
					.build();
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

			SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
			String fechaString1 = "2023-05-12";
			String fechaString2 = "2023-06-24";
			Date fecha = formatoFecha.parse(fechaString1);
			Date fecha2 = formatoFecha.parse(fechaString2);

			Pedido pedido1 = Pedido.builder()
					.estado(Estado.ENTREGADO)
					.fecha(fecha)
					.tipoEnvio(TipoEnvio.RETIRA)
					.total(5242.75)
					.build();
			Pedido pedido2= Pedido.builder()
					.estado(Estado.PREPARACION)
					.fecha(fecha2)
					.tipoEnvio(TipoEnvio.RETIRA)
					.total(2557.25)
					.build();

			DetallePedido detallePedido = DetallePedido.builder()
					.cantidad(5)
					.subtotal(2034.50)
					.build();

			clienteRepository.save(cliente);

			Cliente clienteRecuperada = clienteRepository.findById(cliente.getId()).orElse(null);
			if(clienteRecuperada!= null){
				System.out.println("Nombre " + clienteRecuperada.getNombre());
				System.out.println("Apellido " + clienteRecuperada.getApellido());
				System.out.println("Telefono " + clienteRecuperada.getTelefono());
				System.out.println("Email " + clienteRecuperada.getEmail());

			}
		};
	}
}


