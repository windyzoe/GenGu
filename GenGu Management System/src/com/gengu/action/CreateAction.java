package com.gengu.action;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * this class manage the create(Purchase/Customer/Sale/Order) action
 * @author XUZH
 */
public class CreateAction
{
	/**
	 * 饿汉单例模式,线程安全
	 */
	private static final CreateAction single = new CreateAction();
	/**
	 * 单例模式
	 * @return
	 */
	public static CreateAction getInstance()
	{
		return single;
	}
	/**
	 * 私有构造器
	 */
	private CreateAction()
	{
	}
    public class CreatePurchaseOrderActionListener implements ActionListener{  
        public void actionPerformed(ActionEvent e) {
        }
    }
}
