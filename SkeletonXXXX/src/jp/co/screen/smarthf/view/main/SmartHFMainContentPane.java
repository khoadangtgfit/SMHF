/*
 * Copyright (C) 2008-2012 Dainippon Screen Mfg. Co., Ltd.
 * CONFIDENTIAL Proprietary to Dainippon Screen Mfg. Co., Ltd.
 * 
 * 本プログラムの著作権は大日本スクリーン製造株式会社に帰属するものであり、
 * 同社はこれを営業秘密として管理するものです。従い、本プログラムの全て、
 * 一部にかかわらず、その複製、頒布を行うことは、同社の事前の書面による
 * 承諾がない限り固く禁じられるものです。
 * 
 * The copyright of this program shall belong to
 * Dainippon Screen Mfg. Co., Ltd.("SCREEN") as a "work made for hire."
 * Also, SCREEN will treat this program as its trade secret. Accordingly,
 * no one is allowed to copy and/or distribute this program, as a whole or
 * in part, without obtaining SCREEN' prior permission to do so in writing.
 */

package jp.co.screen.smarthf.view.main;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import jp.co.screen.equios.ui.common.bean.table.JXTableEx;
import jp.co.screen.equios.ui.common.bean.table.TableDefaultInfo;
import jp.co.screen.equios.ui.common.bean.table.TableViewport;
import jp.co.screen.equios.ui.common.bean.table.renderer.DefaultTableRendererEx;
import jp.co.screen.equios.ui.common.util.comparator.XPStringComparator;
import jp.co.screen.equios.ui.common.util.guiproperties.GUIPropHolder;
import jp.co.screen.equios.ui.common.util.guiproperties.GUIProperties;
import jp.co.screen.smarthf.common.resource.LangKey;
import jp.co.screen.smarthf.common.resource.SmartHFResource;
import jp.co.screen.smarthf.model.SmartHFDataModel;
import jp.co.screen.smarthf.model.SmartHFTableModel;
import jp.co.screen.smarthf.properrty.SmartHFPropertyFrm;

import org.apache.log4j.Logger;

/**
 * Class Description
 * @author syptn
 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
 */

public class SmartHFMainContentPane extends JPanel{
	
    private static Logger LOGGER=Logger.getLogger(SmartHFMainContentPane.class);
	
    private JXTableEx mTable;
    
    private TableDefaultInfo mTblDefaultInfo;
    
    private SmartHFTableModel mTblModel;
    
    private JScrollPane mScrollPane;
    
    private String LBRS_PROP = "LBRS_PROP";
    
    private SmartHFManagerPnl mSmartHFMngPanel;
    /**
     * 
     */
	public SmartHFMainContentPane(SmartHFManagerPnl mSmartHFMngPanel){
		super();
		this.mSmartHFMngPanel = mSmartHFMngPanel;
		init();
	}
	
	/**
	 * 
	 * Method description
	 * @author syptn
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
	private void init(){
		this.setLayout(new BorderLayout());
		this.add(getScrollPane(),BorderLayout.CENTER);
	}

	/**
	 * 
	 * Method description
	 * @return
	 * @author syptn
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
	private JScrollPane getScrollPane(){
		if(this.mScrollPane == null){
			TableViewport viewPortr = new TableViewport();
			viewPortr.setView(getTable());
			this.mScrollPane = new JScrollPane();
			this.mScrollPane.setViewport(viewPortr);
		}
		
		return mScrollPane;
	}
	
	/**
	 * main table
	 * 
	 * @return
	 * @author syptn
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
	public JXTableEx getTable(){
		if(this.mTable == null){
			this.mTable = new JXTableEx(getTblModel(), getGUIProp(), getTblDefaultInfo(), SmartHFResource.getInstance());
		}
		mTable.addMouseListener(new MouseAdapter() {
		  @Override
	    public void mouseClicked(MouseEvent inEvent) {
	      int selectedRowsCount = mTable.getSelectedRowCount();
	      mSmartHFMngPanel.updateStateButton(selectedRowsCount);
	      if(inEvent.getClickCount()==2) {
	        new SmartHFPropertyFrm(mSmartHFMngPanel, true);
	      }
	    }
		
		});
		return mTable;
	}
	
	/**
	 * table model
	 * 
	 * @return
	 * @author syptn
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
	public SmartHFTableModel getTblModel(){
		
		if(this.mTblModel == null){
			this.mTblModel = new SmartHFTableModel();
			
			for(int loop=0 ; loop<getTblDefaultInfo().getColumnDataCount() ; loop++){
				mTblModel.addColumn(getTblDefaultInfo().getColumnData(loop).getName()) ;    
            }
		}
		
		return this.mTblModel;
	}
	
	/**
	 * table default info
	 * 
	 * @return
	 * @author syptn
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
	private TableDefaultInfo getTblDefaultInfo(){
		if(this.mTblDefaultInfo == null){
			this.mTblDefaultInfo = new TableDefaultInfo(LBRS_PROP);
			mTblDefaultInfo.addColumnData(LangKey.WORD_HFNAME, 150, true,
          new DefaultTableRendererEx(), new XPStringComparator());
      mTblDefaultInfo.addColumnData(LangKey.WORD_RULE, 250, true,
          new DefaultTableRendererEx(), new XPStringComparator());
    
		}
		
		return mTblDefaultInfo;
	}
 
	/**
	 * get GUI prop
	 * 
	 * @return
	 * @author syptn
	 * @since EQUIOS V2.00EQ001T1 EQF#C320-003
	 */
    public GUIProperties getGUIProp(){
    	return GUIPropHolder.getGUIProp( LBRS_PROP );
    }
    
    public SmartHFDataModel getSelectedRow() {
      int selectedRowIdx=mTable.getSelectedRow();
      if(selectedRowIdx!=-1) {
        int dataIdx=mTable.convertRowIndexToModel(selectedRowIdx);
        return mTblModel.getRowValue(dataIdx);
      }
      return null;
    }
}
