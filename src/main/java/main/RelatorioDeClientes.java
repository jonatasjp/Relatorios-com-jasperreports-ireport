package main;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.Carro;
import bean.Cliente;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class RelatorioDeClientes {
	public static void main(String[] args) throws JRException, SQLException {

		System.out.println("Gerando relatório...");
		// lista com os nossos clientes
		List<Cliente> clientes = montarClientes();;

		// compilacao do JRXML
		JasperReport report = JasperCompileManager.compileReport("relatorios/Relatorio_1.jrxml");

		// preenchimento do relatorio, note que o metodo recebe 3 parametros:
		// 1 - o relatorio
		// 2 - um Map, com parametros que sao passados ao relatorio
		// no momento do preenchimento. No nosso caso eh null, pois nao
		// estamos usando nenhum parametro
		Map<String, Object> parametros = new HashMap<String, Object>();
		parametros.put("nomeRelatorio", "Relatório de clientes");
		parametros.put("data", new Date().toString());
		//
		// 3 - o data source. Note que nao devemos passar a lista diretamente,
		// e sim "transformar" em um data source utilizando a classe
		// JRBeanCollectionDataSource
		JasperPrint print = JasperFillManager.fillReport(report, parametros, new JRBeanCollectionDataSource(clientes));

		// exportacao do relatorio para outro formato, no caso PDF
		JasperExportManager.exportReportToPdfFile(print, "relatorios/RelatorioClientes.pdf");

		System.out.println("Relatório gerado.");
	}

	private static List<Cliente> montarClientes() {
		List<Cliente> clientes = new ArrayList<Cliente>();

		List<Carro> carros = new ArrayList<Carro>();
		Carro a = new Carro("Fiat Uno");
		Carro b = new Carro("Corsa");
		Carro c = new Carro("Golf");
		carros.add(a);
		carros.add(b);
		carros.add(c);

		List<Carro> carros1 = new ArrayList<Carro>();
		Carro a1 = new Carro("Uno");
		Carro b1 = new Carro("gol");
		Carro c1 = new Carro("Golf");
		carros1.add(a1);
		carros1.add(b1);
		carros1.add(c1);

		Cliente cliente1 = new Cliente();
		cliente1.setNome("Jonatas Luis");
		cliente1.setTelefone("8398600-0000");
		cliente1.setEmail("jonatasluis.unipe@gmail.com");
		cliente1.setCarros(carros);

		Cliente cliente2 = new Cliente();
		cliente2.setNome("André luiz");
		cliente2.setTelefone("8398888-8888");
		cliente2.setEmail("andreluiz@gmail.com");
		cliente2.setCarros(carros);

		Cliente cliente3 = new Cliente();
		cliente3.setNome("Fabricio Silva");
		cliente3.setTelefone("8398445-5464");
		cliente3.setEmail("fabriciosilva@gmail.com");
		cliente3.setCarros(carros1);

		clientes.add(cliente1);
		clientes.add(cliente2);
		clientes.add(cliente3);

		return clientes;
	}
}
