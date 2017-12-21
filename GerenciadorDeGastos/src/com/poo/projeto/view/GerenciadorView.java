package com.poo.projeto.view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.poo.projeto.controller.GerenciadorController;
import com.poo.projeto.model.GastoTableModel;
import com.poo.projeto.model.exceptions.DataInvaliadaException;
import com.poo.projeto.model.exceptions.PrecoInvaliadoException;

import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import com.toedter.calendar.JDateChooser;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class GerenciadorView extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private GastoTableModel tableModel;
	private GerenciadorController controller;
	private JLabel lblValorParcial;
	private JTextField txtPreco;
	private JTextArea txtDescricao;
	private JDateChooser dateGasto;
	private JDateChooser dateChooser1;
	private JDateChooser dateChooser2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
				} catch (ClassNotFoundException e1) {

					e1.printStackTrace();
				} catch (InstantiationException e1) {

					e1.printStackTrace();
				} catch (IllegalAccessException e1) {

					e1.printStackTrace();
				} catch (UnsupportedLookAndFeelException e1) {

					e1.printStackTrace();
				}
				try {
					GerenciadorView frame = new GerenciadorView();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GerenciadorView() {
		super("Gerenciador de Gastos");
		try {
			controller = new GerenciadorController();
		} catch (SQLException e) {

			e.printStackTrace();
		}
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(100, 149, 237));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(297, 10, 300, 230);

		table = new JTable();
		tableModel = new GastoTableModel();
		tableModel.changeGastoTableModel(controller.getGastos());
		table.setModel(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(80);
		table.getColumnModel().getColumn(2).setPreferredWidth(240);
		tableModel.fireTableDataChanged();
		table.setShowVerticalLines(false);
		table.setShowHorizontalLines(false);
		table.setShowGrid(false);

		scrollPane.setViewportView(table);
		contentPane.add(scrollPane);

		lblValorParcial = new JLabel("000.00");
		lblValorParcial.setFont(new Font("Arial", Font.PLAIN, 18));
		lblValorParcial.setBounds(522, 251, 75, 29);
		contentPane.add(lblValorParcial);

		JLabel lblDescParcial = new JLabel("Valor Parcial:");
		lblDescParcial.setFont(new Font("Arial", Font.PLAIN, 18));
		lblDescParcial.setBounds(412, 251, 106, 29);
		contentPane.add(lblDescParcial);

		dateChooser2 = new JDateChooser();
		dateChooser2.setBounds(385, 373, 87, 20);
		contentPane.add(dateChooser2);

		JLabel lblDataFinal = new JLabel("Data Final:");
		lblDataFinal.setFont(new Font("Arial", Font.PLAIN, 17));
		lblDataFinal.setBounds(297, 374, 87, 19);
		contentPane.add(lblDataFinal);

		JLabel lblDataInicial = new JLabel("Data Inicial:");
		lblDataInicial.setFont(new Font("Arial", Font.PLAIN, 17));
		lblDataInicial.setBounds(297, 343, 87, 19);
		contentPane.add(lblDataInicial);

		dateChooser1 = new JDateChooser();
		dateChooser1.setBounds(385, 342, 87, 20);
		contentPane.add(dateChooser1);

		JButton btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					controller.filtrarGastos(dateChooser1.getDate(), dateChooser2.getDate());
					tableModel.changeGastoTableModel(controller.getGastos());
				} catch (DataInvaliadaException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				} catch (SQLException e) {

					e.printStackTrace();
				}
				atualizaValorParcial(controller.getValorTotal());

			}
		});
		btnFiltrar.setBounds(495, 343, 89, 23);
		contentPane.add(btnFiltrar);

		JLabel lblFiltrarPorDatas = new JLabel("Filtrar Por Datas");
		lblFiltrarPorDatas.setFont(new Font("Arial", Font.PLAIN, 18));
		lblFiltrarPorDatas.setBounds(297, 313, 175, 29);
		contentPane.add(lblFiltrarPorDatas);

		JButton btnAdicionarGasto = new JButton("Adicionar");
		btnAdicionarGasto.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					controller.addGasto(txtPreco.getText(), dateGasto.getDate(), txtDescricao.getText());
					tableModel.changeGastoTableModel(controller.getGastos());
				} catch (SQLException e) {
					e.printStackTrace();
				} catch (DataInvaliadaException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				} catch (PrecoInvaliadoException e) {
					JOptionPane.showMessageDialog(null, e.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
				}
				atualizaValorParcial(controller.getValorTotal());
				zerarCampos();
			}
		});
		btnAdicionarGasto.setBounds(94, 217, 89, 23);
		contentPane.add(btnAdicionarGasto);

		txtDescricao = new JTextArea();
		txtDescricao.setBounds(35, 85, 199, 120);
		contentPane.add(txtDescricao);

		JLabel lblPreco = new JLabel("Pre\u00E7o:");
		lblPreco.setFont(new Font("Arial", Font.PLAIN, 17));
		lblPreco.setBounds(35, 17, 49, 19);
		contentPane.add(lblPreco);

		txtPreco = new JTextField();
		txtPreco.setText("00.00");
		txtPreco.setBounds(35, 38, 49, 20);
		contentPane.add(txtPreco);
		txtPreco.setColumns(10);

		dateGasto = new JDateChooser();
		dateGasto.setBounds(147, 38, 87, 20);
		contentPane.add(dateGasto);

		JLabel lblData = new JLabel("Data:");
		lblData.setFont(new Font("Arial", Font.PLAIN, 17));
		lblData.setBounds(147, 17, 49, 19);
		contentPane.add(lblData);

		JButton btnExcluir = new JButton("Excluir");
		btnExcluir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				int linha = table.getSelectedRow();
				if (linha != -1) {
					int index = tableModel.getIdAt(linha);
					tableModel.removeRow(linha);
					try {
						controller.removerGasto(index);
					} catch (SQLException e) {

						e.printStackTrace();
					}
					atualizaValorParcial(controller.getValorTotal());
				}
			}
		});
		btnExcluir.setBounds(320, 251, 64, 23);
		contentPane.add(btnExcluir);

		JButton btnZerarFiltro = new JButton("Zerar Filtro");
		btnZerarFiltro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					controller.filtrarGastos();
					tableModel.changeGastoTableModel(controller.getGastos());
				} catch (SQLException e) {

					e.printStackTrace();
				}
				atualizaValorParcial(controller.getValorTotal());
				zerarFiltros();
			}
		});
		btnZerarFiltro.setBounds(495, 370, 89, 23);
		contentPane.add(btnZerarFiltro);

		atualizaValorParcial(controller.getValorTotal());
	}

	public void atualizaValorParcial(double valor) {
		lblValorParcial.setText(String.format("%.2f", valor));
	}

	public void zerarCampos() {
		txtPreco.setText("");
		dateGasto.setCalendar(null);
		txtDescricao.setText("");
	}

	public void zerarFiltros() {
		dateChooser1.setCalendar(null);
		dateChooser2.setCalendar(null);
	}
}
