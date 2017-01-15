package com.gengu.ui;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.apache.derby.impl.sql.catalog.SYSTABLEPERMSRowFactory;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel;

import javax.swing.UnsupportedLookAndFeelException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import com.gengu.common.Constants;
import com.gengu.common.ConstantsDB;
import com.gengu.component.CustomIcon;
import com.gengu.component.PagingPanel;
import com.gengu.controller.TableController;
import com.gengu.util.JdbcUtil;

import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JFormattedTextField;
import javax.swing.Box;
import javax.swing.JToggleButton;

/**
 * 主窗口的UI
 * 
 * @author XUZH
 *
 */
public class MainFrame
{
	// 声明需要外部类刷新的组件
	public JFrame frame;
	public JTable purchaseTable;
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
	private JButton jBCreateInStorage;
	private JButton jBCreateOutStorage;
	private JButton jBCreatePurchase;
	private JButton jBCreateSale;
	private JButton jBListProfit;
	private JButton jBListTranPay;
	private JButton jBListtoragePay;
	private JButton jBListOtherPay;
	private JButton jBEditInfo;
	private JButton jBRefresgTab;
	private JButton jBDelete;
	private JTabbedPane jTabbedPane;
	private PagingPanel pagingPanel;

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
	 * 
	 * @return
	 */
	public static MainFrame getInstance()
	{
		return single;
	}

	/**
	 * Create the application. 由于需要windowsBuilder来做开发,先把构造器设置为public
	 * 注意之后要改为private(单例模式)
	 * 
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
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
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

		// 表格面板
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		jTabbedPane = new JTabbedPane();
		panel.add(jTabbedPane, BorderLayout.CENTER);
		pagingPanel=PagingPanel.getInstance();
		//pagingPanel.setPanel(100);
		panel.add(pagingPanel, BorderLayout.SOUTH);

		// 修饰右切换面板
		jTabbedPane.putClientProperty(SubstanceLookAndFeel.TABBED_PANE_CLOSE_BUTTONS_PROPERTY, Boolean.TRUE);

		// 加入tab页
		JScrollPane scrollPane = new JScrollPane();
		JScrollPane scrollPane1 = new JScrollPane();
		JScrollPane scrollPane2 = new JScrollPane();
		jTabbedPane.addTab("采购", null, scrollPane, null);
		jTabbedPane.addTab("销售", null, scrollPane1, null);
		jTabbedPane.addTab("出入库", null, scrollPane2, null);

		// 加入列表
		scrollPane.setViewportView(purchaseTable);

		// 可左右拉伸面板加入选项面板和可切换面板
		JPanel panel_1 = new JPanel();
		frame.getContentPane().add(panel_1, BorderLayout.WEST);

		// 修饰左面板,加入内容
		panel_1.setBorder(new TitledBorder(new TitledBorder(new LineBorder(new Color(130, 135, 144)), "", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)), "统计", TitledBorder.CENTER,
				TitledBorder.TOP, null, new Color(0, 0, 0)));
		panel_1.setLayout(new BoxLayout(panel_1, BoxLayout.Y_AXIS));

		jBListProfit = new JButton("利润统计");
		panel_1.add(jBListProfit);
		jBListTranPay = new JButton("运费统计");
		panel_1.add(jBListTranPay);
		jBListtoragePay = new JButton("仓储费统计");
		panel_1.add(jBListtoragePay);
		jBListOtherPay = new JButton("其它统计");
		panel_1.add(jBListOtherPay);
		jBCreateInStorage = new JButton("入库记录");
		jBCreateInStorage.setIcon(new CustomIcon(Constants.PATH_StoreIn));
		jBCreateOutStorage = new JButton("出库记录");
		jBCreateOutStorage.setIcon(new CustomIcon(Constants.PATH_StoreOut));
		jBCreatePurchase = new JButton("采购记录");
		jBCreatePurchase.setIcon(new CustomIcon(Constants.PATH_Purchase));
		jBCreateSale = new JButton("销售记录");
		jBCreateSale.setIcon(new CustomIcon(Constants.PATH_Sale));
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		frame.getContentPane().add(toolBar, BorderLayout.NORTH);
		toolBar.setRollover(true);

		// 工具条命令
		toolBar.add(jBCreateInStorage);
		toolBar.add(jBCreateOutStorage);
		toolBar.add(jBCreatePurchase);
		toolBar.add(jBCreateSale);
		jBEditInfo = new JButton("修改记录");
		jBEditInfo.setIcon(new CustomIcon(Constants.PATH_Modify));
		toolBar.add(jBEditInfo);
		jBRefresgTab = new JButton("刷新页面");
		jBRefresgTab.setIcon(new CustomIcon(Constants.PATH_Refresh));
		toolBar.add(jBRefresgTab);
		
		jBDelete = new JButton("删除记录");
		jBDelete.setIcon(new CustomIcon(Constants.PATH_delete));
		toolBar.add(jBDelete);
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
		DefaultTableCellRenderer tcr = new DefaultTableCellRenderer();// 设置table内容居中
		tcr.setHorizontalAlignment(SwingConstants.CENTER);
		purchaseTable = new JTable(model);
		purchaseTable.setDefaultRenderer(Object.class, tcr);
	}

	/**
	 * 给组件加上事件
	 */
	private void addListeners()
	{
		// 采购记录
		jBCreatePurchase.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				CreatePurchaseOrderPanel dialog = new CreatePurchaseOrderPanel();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		jMIMaterialInfo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				MaterialInfoPanel dialog = MaterialInfoPanel.getInstance();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		jMICreateSupplier.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				CreateSupplierPanel dialog = new CreateSupplierPanel();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		jBRefresgTab.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				new TableController().refreshControl();
			}
		});
		jBDelete.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				new TableController().deleteControl();
			}
		});
		jBEditInfo.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				new TableController().modifyControl();
			}
		});
		frame.addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent e)
			{
				JdbcUtil.shutdownEngine();
				System.exit(0);
			}
		});
	}

	public JTabbedPane getTabPane()
	{
		return this.jTabbedPane;
	}
}
