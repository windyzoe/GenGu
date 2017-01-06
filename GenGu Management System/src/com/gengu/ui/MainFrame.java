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
 * 主窗口的UI
 * @author XUZH
 *
 */
public class MainFrame
{
	// 声明需要外部类刷新的组件
	public JFrame frame;
	public JTable table_1;
	// 声明内部组件
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
	 * 饿汉单例模式,线程安全
	 */
	private static final MainFrame single = new MainFrame();

	/**
	 * 单例模式
	 * @return
	 */
	public static MainFrame getInstance()
	{
		return single;
	}
	/**
	 * Create the application.
	 * 由于需要windowsBuilder来做开发,先把构造器设置为public
	 * 注意之后要改为private(单例模式)
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
		jMenuFile = new JMenu("文件");
		jMIOpen = new JMenuItem("打开");
		jMenuSetting = new JMenu("设置");
		jMenuCustomer = new JMenu("客户");
		jMICreateCustomer = new JMenuItem("新建客户");
		jMIListCustomer = new JMenuItem("客户概览");
		jMenuSupplier = new JMenu("供应商");
		jMICreateSupplier = new JMenuItem("新建供应商");
		jMIListSupplier = new JMenuItem("供应商概览");
		jMenuConfig = new JMenu("配置");
		jMIStorageInfo = new JMenuItem("仓库信息");
		jMIMaterialInfo = new JMenuItem("材料信息");
		jMICarInfo = new JMenuItem("运输车号");
		jMenuHelp = new JMenu("帮助");
		jMICreateInStorage = new JButton("入库记录");
		jMICreateOutStorage = new JButton("出库记录");
		jMICreatePurchase = new JButton("采购记录");
		jMICreateSale = new JButton("销售记录");
		jMIListProfit = new JButton("利润统计");
		jMIListTranPay = new JButton("运费统计");
		jMIListtoragePay = new JButton("仓储费统计");
		jMIListOtherPay = new JButton("其它费用统计");
		jBEditInfo = new JButton("修改记录");
		jBRefresgTab = new JButton("刷新页面");
	}

	/**
	 * 初始化Frame
	 */
	private void initFrame()
	{
		frame = new JFrame();
		frame.setSize(1280, 720);
		// 将界面放在最中央
		frame.setLocation((Constants.SCREEN_WIDTH - frame.getWidth()) / 2, (Constants.SCREEN_HEIGHT - frame.getHeight()) / 2);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("GenGu System");
		frame.setIconImage(new ImageIcon(Constants.PATH_GenGuIcon).getImage());
	}

	/**
	 * 设置布局
	 */
	private void initLayout()
	{
		// 菜单
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		menuBar.add(jMenuFile);
		menuBar.add(jMenuSetting);
		menuBar.add(jMenuCustomer);
		menuBar.add(jMenuSupplier);
		menuBar.add(jMenuConfig);
		menuBar.add(jMenuHelp);

		// 菜单命令
		jMenuFile.add(jMIOpen);
		jMenuCustomer.add(jMICreateCustomer);
		jMenuCustomer.add(jMIListCustomer);
		jMenuConfig.add(jMIStorageInfo);
		jMenuConfig.add(jMIMaterialInfo);
		jMenuConfig.add(jMICarInfo);
		jMenuSupplier.add(jMIListSupplier);
		jMenuSupplier.add(jMICreateSupplier);

		// 工具条
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.NORTH);
		FlowLayout fl_panel = new FlowLayout(FlowLayout.LEFT, 5, 5);
		panel.setLayout(fl_panel);
		JToolBar toolBar = new JToolBar();
		toolBar.setRollover(true);
		panel.add(toolBar);
		JToolBar toolBar_1 = new JToolBar();
		panel.add(toolBar_1);

		// 工具条命令
		toolBar.add(jMICreateInStorage);
		toolBar.add(jMICreateOutStorage);
		toolBar.add(jMICreatePurchase);
		toolBar.add(jMICreateSale);
		toolBar_1.add(jMIListProfit);
		toolBar_1.add(jMIListTranPay);
		toolBar_1.add(jMIListtoragePay);
		toolBar_1.add(jMIListOtherPay);

		// 可左右拉伸面板
		JSplitPane splitPane = new JSplitPane();
		frame.getContentPane().add(splitPane, BorderLayout.CENTER);

		// 可左右拉伸面板加入选项面板和可切换面板
		JPanel panel_1 = new JPanel();
		splitPane.setLeftComponent(panel_1);
		JTabbedPane jtp = new JTabbedPane();
		splitPane.setRightComponent(jtp);

		// 修饰左面板,加入内容
		panel_1.setBorder(new TitledBorder(new TitledBorder(new LineBorder(new Color(130, 135, 144)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "\u9009\u9879",
				TitledBorder.CENTER, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panel_1.add(jBEditInfo);
		panel_1.add(jBRefresgTab);

		// 修饰右切换面板
		jtp.putClientProperty(SubstanceLookAndFeel.TABBED_PANE_CLOSE_BUTTONS_PROPERTY, Boolean.TRUE);

		// 加入tab页
		JScrollPane scrollPane = new JScrollPane();
		JScrollPane scrollPane1 = new JScrollPane();
		JScrollPane scrollPane2 = new JScrollPane();
		jtp.addTab("采购", null, scrollPane, null);
		jtp.addTab("销售", null, scrollPane1, null);
		jtp.addTab("出入库", null, scrollPane2, null);

		// 加入列表
		scrollPane.setViewportView(table_1);
	}

	/**
	 * 初始化列表页
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
	 * 给组件加上事件
	 */
	private void addListeners()
	{
		//采购记录
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
