package com.gengu.ui;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import org.omg.CORBA.Principal;
import org.pushingpixels.substance.api.SubstanceLookAndFeel;
import org.pushingpixels.substance.api.skin.SubstanceBusinessBlackSteelLookAndFeel;
import org.pushingpixels.substance.api.tabbed.VetoableTabCloseListener;

import javax.swing.UnsupportedLookAndFeelException;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import com.gengu.common.Constants;
import com.gengu.common.ConstantsDB;
import com.gengu.component.CustomIcon;
import com.gengu.component.CustomTable;
import com.gengu.component.FilterPanel;
import com.gengu.component.PagingPanel;
import com.gengu.controller.TableController;
import com.gengu.ui.sum.ProfitDialog;
import com.gengu.ui.sum.TransportCostDialog;
import com.gengu.util.JdbcUtil;

import javax.swing.ImageIcon;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JScrollPane;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Insets;

import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.BoxLayout;
import javax.swing.JSeparator;
import javax.swing.JPopupMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import java.awt.FlowLayout;
import javax.swing.JComboBox;

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
	public CustomTable purchaseTable;
	public CustomTable saleTable;
	public CustomTable warehouseTable;
	// 声明内部组件
	private FilterPanel filterPanel;
	private JTabbedPane jTabbedPane;
	private PagingPanel pagingPanel;
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
	private JMenuItem jMIFactoryInfo;
	private JMenuItem jMIMaterialInfo;
	private JMenuItem jMICarInfo;
	private JButton jBCreateInOutStorage;
	private JButton jBCreatePurchase;
	private JButton jBCreateSale;
	private JButton jBListProfit;
	private JButton jBListTranPay;
	private JButton jBListtoragePay;
	private JButton jBListOtherPay;
	private JButton jBEditInfo;
	private JButton jBRefresgTab;
	private JButton jBDelete;
	private JMenuItem jMIEditInfo;
	private JMenuItem jMIRefresgTab;
	private JMenuItem jMIDelete;

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
	private JPopupMenu popupMenu;


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
		initToolbar();
		addListeners();
		registerTabPaneClose();
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
		jMIFactoryInfo = new JMenuItem("工厂信息");
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
		jMenuConfig.add(jMIFactoryInfo);
		jMenuConfig.add(jMIStorageInfo);
		jMenuConfig.add(jMIMaterialInfo);
		jMenuConfig.add(jMICarInfo);
		jMenuSupplier.add(jMICreateSupplier);
		jMenuSupplier.add(jMIListSupplier);

		// 表格面板
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		jTabbedPane = new JTabbedPane();
		panel.add(jTabbedPane, BorderLayout.CENTER);
		pagingPanel = PagingPanel.getInstance();
		// pagingPanel.setPanel(100);
		panel.add(pagingPanel, BorderLayout.SOUTH);

		// 修饰右切换面板
		jTabbedPane.putClientProperty(SubstanceLookAndFeel.TABBED_PANE_CLOSE_BUTTONS_PROPERTY, Boolean.TRUE);

		// 加入tab页
		JScrollPane scrollPane = new JScrollPane();
		JScrollPane scrollPane1 = new JScrollPane();
		JScrollPane scrollPane2 = new JScrollPane();
		jTabbedPane.addTab("出入库", null, scrollPane2, null);
		jTabbedPane.addTab("采购", null, scrollPane, null);
		jTabbedPane.addTab("销售", null, scrollPane1, null);

		// 加入列表
		scrollPane.setViewportView(purchaseTable);
		scrollPane1.setViewportView(saleTable);

		scrollPane2.setViewportView(warehouseTable);

		// 可左右拉伸面板加入选项面板和可切换面板
		filterPanel= new FilterPanel();
		frame.getContentPane().add(filterPanel, BorderLayout.WEST);
	}

	private void initToolbar()
	{
		// 工具条命令
		jBListProfit = new JButton("利润统计");
		jBListTranPay = new JButton("运费统计");
		jBListtoragePay = new JButton("仓储费统计");
		jBListOtherPay = new JButton("其它统计");
		jBCreateInOutStorage = new JButton("出入库记录");
		jBCreateInOutStorage.setIcon(new CustomIcon(Constants.PATH_StoreIn));
		jBCreatePurchase = new JButton("采购记录");
		jBCreatePurchase.setIcon(new CustomIcon(Constants.PATH_Purchase));
		jBCreateSale = new JButton("销售记录");
		jBCreateSale.setIcon(new CustomIcon(Constants.PATH_Sale));
		jBEditInfo = new JButton("修改");
		jBEditInfo.setIcon(new CustomIcon(Constants.PATH_Modify));
		jBRefresgTab = new JButton("刷新");
		jBRefresgTab.setIcon(new CustomIcon(Constants.PATH_Refresh));
		jBDelete = new JButton("删除");
		jBDelete.setIcon(new CustomIcon(Constants.PATH_delete));
		JToolBar toolBar = new JToolBar();
		toolBar.setFloatable(false);
		frame.getContentPane().add(toolBar, BorderLayout.NORTH);
		toolBar.setRollover(true);
		toolBar.add(jBCreateInOutStorage);
		toolBar.add(jBCreatePurchase);
		toolBar.add(jBCreateSale);

		toolBar.addSeparator();

		toolBar.add(jBEditInfo);
		toolBar.add(jBRefresgTab);
		toolBar.add(jBDelete);

		toolBar.addSeparator();

		toolBar.add(jBListProfit);
		toolBar.add(jBListTranPay);
		toolBar.add(jBListtoragePay);
		toolBar.add(jBListOtherPay);
	}

	/**
	 * 初始化列表页
	 */
	private void initTables()
	{
		// purchaseTable
		purchaseTable = new CustomTable(ConstantsDB.PurchaseHead);

		// saleTable
		saleTable = new CustomTable(ConstantsDB.SaleHead);

		// warehourseTable
		warehouseTable = new CustomTable(ConstantsDB.WareHouseHead);

		// 为table添加右键右键菜单
		popupMenu = new JPopupMenu();
		popupMenu.setBorderPainted(false);
		jMIEditInfo = new JMenuItem("修改");
		jMIEditInfo.setIcon(new CustomIcon(Constants.PATH_Modify));
		jMIRefresgTab = new JMenuItem("刷新");
		jMIRefresgTab.setIcon(new CustomIcon(Constants.PATH_Refresh));
		jMIDelete = new JMenuItem("删除");
		jMIDelete.setIcon(new CustomIcon(Constants.PATH_delete));
		popupMenu.add(jMIEditInfo);
		popupMenu.add(jMIRefresgTab);
		popupMenu.add(jMIDelete);
		addPopup(purchaseTable, popupMenu);
		addPopup(saleTable, popupMenu);
		addPopup(warehouseTable, popupMenu);
	}

	/**
	 * 给组件加上事件
	 */
	/**
	 * 
	 */
	private void addListeners()
	{
		// 出入库记录
		jBCreateInOutStorage.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				CreateInOutWareHousePanel dialog = new CreateInOutWareHousePanel();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
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
		// 销售记录
		jBCreateSale.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				CreateSaleOrderPanel dialog = new CreateSaleOrderPanel();
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
		jMIFactoryInfo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				FactoryInfoDialog dialog =FactoryInfoDialog.getInstance();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		jMICarInfo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				CarInfoDialog dialog = CarInfoDialog.getInstance();
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
		jMICreateCustomer.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				CreateCustomerPanel dialog = new CreateCustomerPanel();
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
		jMIRefresgTab.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				new TableController().refreshControl();
			}
		});
		jMIDelete.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				new TableController().deleteControl();
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
		jMIEditInfo.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int selectSize = MainFrame.getInstance().getCurrentTable().getSelectedRowCount();// 选中的列数
				int columnSize = MainFrame.getInstance().getCurrentTable().getColumnCount();// 列数
				List<String> columnNames = new ArrayList<>();
				for (int i = 0; i < columnSize; i++)
				{
					String strTemp = MainFrame.getInstance().getCurrentTable().getColumnName(i);
					columnNames.add(strTemp);
				}
				String[] titles = columnNames.toArray(new String[columnNames.size()]);
				new ModifyTablePanel(titles, selectSize);// 基于列名生成修改面板
			}
		});
		jBEditInfo.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int selectSize = MainFrame.getInstance().getCurrentTable().getSelectedRowCount();// 选中的列数
				int columnSize = MainFrame.getInstance().getCurrentTable().getColumnCount();// 列数
				List<String> columnNames = new ArrayList<>();
				for (int i = 0; i < columnSize; i++)
				{
					String strTemp = MainFrame.getInstance().getCurrentTable().getColumnName(i);
					columnNames.add(strTemp);
				}
				String[] titles = columnNames.toArray(new String[columnNames.size()]);
				new ModifyTablePanel(titles, selectSize);// 基于列名生成修改面板
			}
		});
		jBListProfit.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				ProfitDialog dialog = new ProfitDialog();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		jBListTranPay.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				TransportCostDialog dialog = new TransportCostDialog();
				dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
				dialog.setVisible(true);
			}
		});
		/*
		 * 重写主窗口关闭事件 主窗口关闭时才会关闭数据库 fix bug 每次与数据库交互时都会重新加载数据库驱动
		 */
		frame.addWindowListener(new WindowAdapter()
		{

			public void windowClosing(WindowEvent e)
			{
				JdbcUtil.shutdownEngine();
				System.exit(0);
			}
		});
		// 标签页切换事件
		jTabbedPane.addChangeListener(new ChangeListener()
		{
			@Override
			public void stateChanged(ChangeEvent e)
			{
				new TableController().updatePaging();
			}
		});
	}

	/**
	 * 注册标签页关闭事件
	 */
	private void registerTabPaneClose()
	{
		jTabbedPane.putClientProperty(SubstanceLookAndFeel.TABBED_PANE_CLOSE_BUTTONS_PROPERTY, Boolean.FALSE);

		// register tab close listener on the specific tabbed pane.
		SubstanceLookAndFeel.registerTabCloseChangeListener(jTabbedPane, new VetoableTabCloseListener()
		{
			public void tabClosing(JTabbedPane tabbedPane, Component tabComponent)
			{
			}

			public void tabClosed(JTabbedPane tabbedPane, Component tabComponent)
			{
			}

			public boolean vetoTabClosing(JTabbedPane tabbedPane, Component tabComponent)
			{
				return true;
				// (JOptionPane.showConfirmDialog(jTabbedPane,
				// "Are you sure you want to close " +
				// tabbedPane.getTitleAt(tabbedPane.indexOfComponent(tabComponent))
				// + "?") != JOptionPane.YES_OPTION);
			}
		});

	}

	public JTabbedPane getTabPane()
	{
		return this.jTabbedPane;
	}

	/**
	 * 获取当前窗口tab页名
	 * 
	 * @return
	 */
	public String getTabName()
	{
		return jTabbedPane.getTitleAt(jTabbedPane.getSelectedIndex());
	}

	/**
	 * 获取当前的Jtable
	 * 
	 * @return
	 */
	public CustomTable getCurrentTable()
	{
		JScrollPane jsPane = (JScrollPane) jTabbedPane.getComponentAt(jTabbedPane.getSelectedIndex());
		CustomTable currentTable = (CustomTable) jsPane.getViewport().getView();
		return currentTable;
	}

	/**
	 * 给某个组件添加右键菜单
	 * 
	 * @param component
	 * @param popup
	 */
	private static void addPopup(Component component, final JPopupMenu popup)
	{
		component.addMouseListener(new MouseAdapter()
		{
			public void mousePressed(MouseEvent e)
			{
				if (e.isPopupTrigger())
				{
					showMenu(e);
				}
			}

			public void mouseReleased(MouseEvent e)
			{
				if (e.isPopupTrigger())
				{
					showMenu(e);
				}
			}

			private void showMenu(MouseEvent e)
			{
				popup.show(e.getComponent(), e.getX(), e.getY());
			}
		});
	}
}
