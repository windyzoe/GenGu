package com.gengu.ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel;

import javax.swing.UnsupportedLookAndFeelException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import javax.swing.JTree;
import javax.swing.BoxLayout;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JRadioButton;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import java.awt.GridLayout;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextField;
import com.jgoodies.forms.layout.FormLayout;
import com.gengu.common.Constants;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.FormSpecs;
import com.jgoodies.forms.layout.RowSpec;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JInternalFrame;
import javax.swing.JLayeredPane;
import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JDesktopPane;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import javax.swing.border.EtchedBorder;
import javax.swing.border.MatteBorder;
import javax.swing.JList;

/**
 * 
 * @author XUZH
 *
 */
public class MainFrame
{
	// ������Ҫ�ⲿ��ˢ�µ����
	public JFrame frame;
	public JTable table_1;
	// �����ڲ����
	private JMenu jMenuFile;
	private JMenu jMenuSetting;
	private JMenu jMenuCustomer;
	private JMenu jMenuConfig;
	private JMenu jMenuHelp;
	private JMenuItem jMIOpen;
	private JMenuItem jMICreateCustomer;
	private JMenuItem jMIListCustomer;
	private JMenuItem jMIStorageInfo;
	private JMenuItem jMIMaterialInfo;
	private JMenuItem jMICarInfo;
	private JMenuItem jMICreateInStorage;
	private JMenuItem jMICreateOutStorage;
	private JMenuItem jMICreatePurchase;
	private JMenuItem jMICreateSale;
	private JMenuItem jMIListProfit;
	private JMenuItem jMIListTranPay;
	private JMenuItem jMIStoragePay;
	private JMenuItem jMIOtherPay;
	private JButton jBEditInfo;

	/**
	 * Launch the application.
	 * 
	 * @throws UnsupportedLookAndFeelException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 */
	public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, UnsupportedLookAndFeelException
	{
		JFrame.setDefaultLookAndFeelDecorated(true);
		JDialog.setDefaultLookAndFeelDecorated(true);
		SwingUtilities.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					UIManager.setLookAndFeel(new SubstanceBusinessBlackSteelLookAndFeel());
					MainFrame window = new MainFrame();
					window.frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainFrame()
	{
		initFrame();
		initializeComponent();
		initTables();
		initLayout();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initializeComponent()
	{

		jMenuFile = new JMenu("�ļ�");
		jMIOpen = new JMenuItem("��");
		jMenuSetting = new JMenu("����");
		jMenuCustomer = new JMenu("�ͻ�");
		jMICreateCustomer = new JMenuItem("�½��ͻ�");
		jMIListCustomer = new JMenuItem("�ͻ�����");
		jMenuConfig = new JMenu("����");
		jMIStorageInfo = new JMenuItem("�ֿ���Ϣ");
		jMIMaterialInfo = new JMenuItem("������Ϣ");
		jMICarInfo = new JMenuItem("���䳵��");
		jMenuHelp = new JMenu("����");
		jMICreateInStorage = new JMenuItem("����¼");
		jMICreateOutStorage = new JMenuItem("�����¼");
		jMICreatePurchase = new JMenuItem("�ɹ���¼");
		jMICreateSale = new JMenuItem("���ۼ�¼");
		jMIListProfit = new JMenuItem("����ͳ��");
		jMIListTranPay = new JMenuItem("�˷�ͳ��");
		jMIStoragePay = new JMenuItem("�ִ���ͳ��");
		jMIOtherPay = new JMenuItem("��������ͳ��");
		jBEditInfo = new JButton("�޸ļ�¼");
	}

	private void initFrame()
	{
		// ��õ�ǰ��Ļ�ֱ���
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		frame = new JFrame();
		frame.setSize(800, 600);
		// ���������������
		frame.setLocation((screenSize.width - frame.getWidth()) / 2, (screenSize.height - frame.getHeight()) / 2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		frame.setTitle("GenGu System");
		frame.setIconImage(new ImageIcon(Constants.PATH_GenGuIcon).getImage());
	}

	/**
	 * ���ò���
	 */
	private void initLayout()
	{
		// �˵�
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		menuBar.add(jMenuFile);
		menuBar.add(jMenuSetting);
		menuBar.add(jMenuCustomer);
		menuBar.add(jMenuConfig);
		menuBar.add(jMenuHelp);

		// �˵�����
		jMenuFile.add(jMIOpen);
		jMenuCustomer.add(jMICreateCustomer);
		jMenuCustomer.add(jMIListCustomer);
		jMenuConfig.add(jMIStorageInfo);
		jMenuConfig.add(jMIMaterialInfo);
		jMenuConfig.add(jMICarInfo);

		// ������
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		FlowLayout fl_panel = new FlowLayout(FlowLayout.LEFT, 5, 5);
		panel.setLayout(fl_panel);
		JToolBar toolBar = new JToolBar();
		panel.add(toolBar);
		JToolBar toolBar_1 = new JToolBar();
		panel.add(toolBar_1);

		// ����������
		toolBar.add(jMICreateInStorage);
		toolBar.add(jMICreateOutStorage);
		toolBar.add(jMICreatePurchase);
		toolBar.add(jMICreateSale);
		toolBar_1.add(jMIListProfit);
		toolBar_1.add(jMIListTranPay);
		toolBar_1.add(jMIStoragePay);
		toolBar_1.add(jMIOtherPay);

		// �������������
		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);

		// ����������������ѡ�����Ϳ��л����
		JPanel panel_1 = new JPanel();
		splitPane.setLeftComponent(panel_1);
		JTabbedPane jtp = new JTabbedPane();
		splitPane.setRightComponent(jtp);

		// ���������,��������
		panel_1.setBorder(new TitledBorder(new TitledBorder(new LineBorder(new Color(130, 135, 144)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "\u9009\u9879",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_1.add(jBEditInfo);

		// �������л����
		jtp.putClientProperty(SubstanceLookAndFeel.TABBED_PANE_CLOSE_BUTTONS_PROPERTY, Boolean.TRUE);

		// ����tabҳ
		JScrollPane scrollPane = new JScrollPane();
		JScrollPane scrollPane1 = new JScrollPane();
		JScrollPane scrollPane2 = new JScrollPane();
		jtp.addTab("�ɹ�", null, scrollPane, null);
		jtp.addTab("����", null, scrollPane1, null);
		jtp.addTab("�����", null, scrollPane2, null);

		// �����б�
		scrollPane.setViewportView(table_1);
	}

	private void initTables()
	{
		String[] headers =
		{ "�ɹ�����", "¼������", "��������", "������λ", "���", "�ƺ�", "����", "����", "����", "�ܼ�", "����", "�����ַ", "�Ƿ�����", "����/����", "�������" };
		DefaultTableModel model = new DefaultTableModel(null, headers)
		{
			public boolean isCellEditable(int row, int column)
			{    
				return false;
			}
		};
		table_1 = new JTable(model);
	}
}
