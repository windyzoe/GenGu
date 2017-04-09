package com.gengu.ui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.gengu.action.ExportAction;
import com.gengu.common.Constants;

import javax.swing.JComboBox;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField; 

public class ExportDialog extends JDialog
{

	private final JPanel contentPanel = new JPanel();
	private JPanel panel;
	private JTextField textField;
	private JButton btnNewButton;
	private JButton okButton;
	private JButton cancelButton;
	private JComboBox comboBox;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		try
		{
			ExportDialog dialog = new ExportDialog();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ExportDialog()
	{
		initLayout();
		addListeners();
	}

	private void initLayout()
	{
		setTitle("选择导出目录");
		setModal(true);
		setBounds(100, 100, 477, 206);
		setLocation((Constants.SCREEN_WIDTH - this.getWidth()) / 2, (Constants.SCREEN_HEIGHT - this.getHeight()) / 2);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BoxLayout(contentPanel, BoxLayout.Y_AXIS));
		{
			panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			contentPanel.add(panel);
			{
				JLabel lblNewLabel = new JLabel("\u62A5\u8868\u9009\u62E9 : ");
				panel.add(lblNewLabel);
			}
			{
				comboBox = new JComboBox();
				panel.add(comboBox);
				comboBox.addItem("出入库");
				comboBox.addItem("采购");
				comboBox.addItem("销售");
			}
		}
		{
			JPanel panel_1 = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel_1.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			contentPanel.add(panel_1);
			{
				JLabel lblNewLabel_1 = new JLabel("\u9009\u62E9\u76EE\u5F55 : ");
				panel_1.add(lblNewLabel_1);
			}
			{
				textField = new JTextField();
				panel_1.add(textField);
				textField.setColumns(30);
				textField.setText("C:\\");
				textField.setEditable(false);
			}
			{
				btnNewButton = new JButton("..");
				panel_1.add(btnNewButton);
			}
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
				cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	private void addListeners()
	{
		ExportDialog dialog = this;
		btnNewButton.addActionListener(new ActionListener()
		{

			@Override
			public void actionPerformed(ActionEvent e)
			{
				JFileChooser jfc = new JFileChooser();
				jfc.setDialogTitle("请选择要导出目录");
				jfc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
				// jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
				// jfc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
				int result = jfc.showOpenDialog(dialog);
				File file = null;
				if (JFileChooser.APPROVE_OPTION == result)
				{
					file = jfc.getSelectedFile();
					if (!file.isDirectory())
					{
						JOptionPane.showMessageDialog(null, "你选择的目录不存在");
						return;
					}
					String path = file.getAbsolutePath();
					dialog.setSavePath(path);
				} else
				{
					return;
				}
			}
		});
		okButton.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				ExportAction exportAction =new ExportAction(getSavePath(),getSelectType());
				exportAction.exportAction();
				dialog.dispose();
			}
		});
	}
	public void setSavePath(String strPath)
	{
		textField.setText(strPath);
	}
	private String getSavePath()
	{
		return textField.getText();
	}
	private String getSelectType()
	{
		return comboBox.getSelectedItem().toString();
	}
}
