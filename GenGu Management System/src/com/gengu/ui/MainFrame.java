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
import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import com.gengu.common.Constants;
import com.gengu.common.ConstantsDB;

import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

/**
 * �����ڵ�UI
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
	private JMenu jMenuSupplier;
	private JMenu jMenuConfig;
	private JMenu jMenuHelp;
	private JMenuItem jMIOpen;
	private JMenuItem jMICreateCustomer;
	private JMenuItem jMIListCustomer;
	private JMenuItem jMICreateSupplier;
	private JMenuItem jMIListSupplier;
	private JMenuItem jMIStorageInfo;
	private JMenuItem jMIMaterialInfo;
	private JMenuItem jMICarInfo;
	private JButton jMICreateInStorage;
	private JButton jMICreateOutStorage;
	private JButton jMICreatePurchase;
	private JButton jMICreateSale;
	private JButton jMIListProfit;
	private JButton jMIListTranPay;
	private JButton jMIListtoragePay;
	private JButton jMIListOtherPay;
	private JButton jBEditInfo;
	private JButton jBRefresgTab;
	

	/**
	 * Launch the application.
	 * 
	 * @throws UnsupportedLookAndFeelException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 * @throws ClassNotFoundException
	 * 
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
					MainFrame window = getInstance();
					window.frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}
	/**
	 * ��������ģʽ,�̰߳�ȫ
	 */
	private static final MainFrame single = new MainFrame();

	/**
	 * ����ģʽ
	 * @return
	 */
	public static MainFrame getInstance()
	{
		return single;
	}
	/**
	 * Create the application.
	 * ������ҪwindowsBuilder��������,�Ȱѹ���������Ϊpublic
	 * ע��֮��Ҫ��Ϊprivate(����ģʽ)
	 * @wbp.parser.entryPoint
	 */
	public MainFrame()
	{
		initFrame();
		initializeComponent();
		initTables();
		initLayout();
		addListeners();
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
		jMenuSupplier = new JMenu("��Ӧ��");
		jMICreateSupplier = new JMenuItem("�½���Ӧ��");
		jMIListSupplier = new JMenuItem("��Ӧ�̸���");
		jMenuConfig = new JMenu("����");
		jMIStorageInfo = new JMenuItem("�ֿ���Ϣ");
		jMIMaterialInfo = new JMenuItem("������Ϣ");
		jMICarInfo = new JMenuItem("���䳵��");
		jMenuHelp = new JMenu("����");
		jMICreateInStorage = new JButton("����¼");
		jMICreateOutStorage = new JButton("�����¼");
		jMICreatePurchase = new JButton("�ɹ���¼");
		jMICreateSale = new JButton("���ۼ�¼");
		jMIListProfit = new JButton("����ͳ��");
		jMIListTranPay = new JButton("�˷�ͳ��");
		jMIListtoragePay = new JButton("�ִ���ͳ��");
		jMIListOtherPay = new JButton("��������ͳ��");
		jBEditInfo = new JButton("�޸ļ�¼");
		jBRefresgTab = new JButton("ˢ��ҳ��");
	}

	/**
	 * ��ʼ��Frame
	 */
	private void initFrame()
	{
		frame = new JFrame();
		frame.setSize(1280, 720);
		// ���������������
		frame.setLocation((Constants.SCREEN_WIDTH - frame.getWidth()) / 2, (Constants.SCREEN_HEIGHT - frame.getHeight()) / 2);
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
		menuBar.add(jMenuSupplier);
		menuBar.add(jMenuConfig);
		menuBar.add(jMenuHelp);

		// �˵�����
		jMenuFile.add(jMIOpen);
		jMenuCustomer.add(jMICreateCustomer);
		jMenuCustomer.add(jMIListCustomer);
		jMenuConfig.add(jMIStorageInfo);
		jMenuConfig.add(jMIMaterialInfo);
		jMenuConfig.add(jMICarInfo);
		jMenuSupplier.add(jMIListSupplier);
		jMenuSupplier.add(jMICreateSupplier);

		// ������
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		FlowLayout fl_panel = new FlowLayout(FlowLayout.LEFT, 5, 5);
		panel.setLayout(fl_panel);
		JToolBar toolBar = new JToolBar();
		toolBar.setRollover(true);
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
		toolBar_1.add(jMIListtoragePay);
		toolBar_1.add(jMIListOtherPay);

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
		panel_1.add(jBRefresgTab);

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

	/**
	 * ��ʼ���б�ҳ
	 */
	private void initTables()
	{
		DefaultTableModel model = new DefaultTableModel(null, ConstantsDB.PurchaseHead)
		{
			public boolean isCellEditable(int row, int column)
			{
				return false;
			}
		};
		table_1 = new JTable(model);
	}

	/**
	 * ����������¼�
	 */
	private void addListeners()
	{
		//�ɹ���¼
		jMICreatePurchase.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				CreatePurchaseOrderPanel dialog =CreatePurchaseOrderPanel.getInstance();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		jMIMaterialInfo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MaterialInfoPanel dialog =MaterialInfoPanel.getInstance();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
	}
}
